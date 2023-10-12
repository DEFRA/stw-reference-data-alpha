//
// For guidance on how to create routes see:
// https://prototype-kit.service.gov.uk/docs/create-routes
//

const govukPrototypeKit = require('govuk-prototype-kit')
const router = govukPrototypeKit.requests.setupRouter()

const validation = require('./validation')

const {
  pages
} = require('./data/pages')

// Add your routes here
router.get('/', (req, res) => {
  const pageLinks = pages
  .filter(page => !['/add-consignor', '/add-consignee', '/add-importer', '/add-placeOfDestination'].includes(page.url))
  .map(page => ({
    title: { text: shouldShow(page, req.session.data) ? page.title : `${page.title} (Hidden for ${req.session.data.certificateType})` },
    href: shouldShow(page, req.session.data) ? page.url : null
  }))
  res.render('index', { pageLinks })
})

router.post('/', (req, res) => {
  res.redirect('/')
})

pages.filter(page => page.url).forEach(page => {
  router.get(page.url, (req, res) => {
    const context = {
      pageName: page.title,
      secondaryTitle: page.secondaryTitle,
      components: enrichComponents(page.components, req, res)
    }
    res.render('questionPage', context)
  })

  router.post(page.url, (req, res) => {
    const action = req.session.data.action
    delete req.session.data.action
    if (action === "continue") {
      const result = validation.check(req.body, page.components)
      if(result.error) {
        const context = {
          pageName: page.title,
          components: enrichComponents(page.components, req, res),
          errors: errors(result)
        }
        return res.render('questionPage', context)
      }

      const nextPage = typeof page.nextPage === 'string'
        ? page.nextPage
        : page.nextPage.find(option => shouldShow(option, req.session.data)).url
      res.redirect(nextPage)
    } else {
      res.redirect('/')
    }
  })
})

function enrichComponents(components, req, res) {
  const data = req.session.data
  return components
  .filter(component => shouldShow(component, data))
  .map(component => {
    const enriched = {
      ...component,
      value: getValue(component, data),
      items: getItems(component, req, res)
    }
    if (component.type === 'trader') {
      enriched.isTraderEntered = data[`${component.name}Name`]?.length > 0
      enriched.value = {
        name: data[`${component.name}Name`],
        address: data[`${component.name}AddressLine1`],
        country: data[`${component.name}Country`]
      }
    }
    if (component.type === 'totals') {
      enriched.items = getRows(enriched.items)
    }
    return enriched
  })
}

const getRows = items => {
  return items.map(item => {
    return [
      { text: item.text},
      { text: item.value ? item.value : '' }
    ]
  })
}

function shouldShow (component, data) {
  return !component.conditions ||
    Object.entries(component.conditions).every(([key, values]) => values.includes(data[key]))
}

function getValue (component, data) {
  return data[component.name] ?? component.items?.find(item => item.default)?.value?.toString()
}

function getItems (component, req, res) {
  return component.items?.map(item => ({
    ...item,
    value: item.value?.toString(),
    hint: {
      text: item.hint
    },
    conditional: item.components ? renderComponents(res, enrichComponents(item.components, req, res)) : null
  }))
}

function renderComponents (res, components) {
  const nunjucks = res.app.get('nunjucksEnv')
  return {
    html: nunjucks.render('components/renderer.njk', { nested: true, components })
  }
}

const errors = result => {
  const errors = result.error?.details.map(detail => ({
    field: detail.path,
    description: detail.message
  }))
  return errors.reduce(
      (obj, item) => Object.assign(obj, { [item.field]: {'description' : item.description }}), {});
}
