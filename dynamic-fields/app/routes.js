//
// For guidance on how to create routes see:
// https://prototype-kit.service.gov.uk/docs/create-routes
//

const _ = require('lodash')

const govukPrototypeKit = require('govuk-prototype-kit')
const router = govukPrototypeKit.requests.setupRouter()

const validation = require('./validation')
const {
  renderComponents,
  cloneThenUpdateLastRow,
  submissionDate
} = require("./utils")
const {pages} = require('./data/pages')
const {updateNotification} = require("./data/notification");

// Add your routes here
router.get('/', (req, res) => {
  const pageLinks = pages
    .filter(page => !['/add-consignor', '/add-consignee', '/add-importer', '/add-placeOfDestination', '/declaration'].includes(page.url))
    .map(page => ({
      title: {text: shouldShow(page, req.session.data) ? page.title : `${page.title} (Hidden for ${req.session.data.certificateType})`},
      href: shouldShow(page, req.session.data) ? page.url : null
    }))
  res.render('index', {pageLinks})
})

router.post('/', (req, res) => {
  res.redirect('/')
})

pages.filter(page => page.url).forEach(page => {
  router.get(page.url, (req, res) => {
    switch (page.url) {
      case '/declaration':
        return res.render('pages/declaration', {
          pageName: page.title,
          submissionDate: submissionDate()
        })
      case '/commodity-details':
        return res.render('pages/commodityDetails', {
          pageName: page.title,
          commodityRows: [
            [
              { text: '0101' },
              { text: 'Live horses, asses, mules and hinnies' },
              { html: '<a href="#" class="govuk-link">Remove</a>' }
            ],
            [
              { text: '0102' },
              { text: 'Live bovine animals' },
              { html: '<a href="#" class="govuk-link">Remove</a>' }
            ]
          ],

        })
      default:
        return res.render('questionPage', {
          pageName: page.title,
          secondaryTitle: page.secondaryTitle,
          components: enrichComponents(page.components, req, res)
        })
    }
  })

  router.post(page.url, (req, res) => {
    const action = req.session.data.action
    delete req.session.data.action

    updateNotification(req)

    if (action === "continue") {
      const result = validation.check(req.body, page.components)
      if (result.error) {
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
      switch (component.type) {
        case 'trader':
          enriched.isTraderEntered = data[`${component.name}Name`]?.length > 0
          enriched.value = {
            name: data[`${component.name}Name`],
            address: data[`${component.name}AddressLine1`],
            country: data[`${component.name}Country`]
          }
          break
        case 'totals':
          enriched.items = getRows(enriched.items)
          break
        case 'varietyAndClass':
          enriched.items = cloneThenUpdateLastRow(component, req, res)
          break
        case 'documents':
          enriched.items = cloneThenUpdateLastRow(component, req, res)
          break
        case 'array':
          enriched.html = ''
          const testData = ['0101', '0102']
          testData.forEach(item => {
            const nunjucks = res.app.get('nunjucksEnv')
            enriched.html += nunjucks.render('components/array.njk', {
              arrayItemTitle: item,
              components: enrichComponents(component.components, req, res)
            })
          })
      }
      return enriched
    })
}

const getRows = items => {
  return items.map(item => {
    return [
      {text: item.text},
      {text: item.value ? item.value : ''}
    ]
  })
}

function shouldShow(component, data) {
  return !component.conditions ||
    Object.entries(component.conditions).every(([key, values]) => values.includes(_.get(data, key)))
}

function getValue(component, data) {
  return data[component.name] ?? component.items?.find(item => item.default)?.value?.toString()
}

function getItems(component, req, res) {
  return component.items
  ?.filter(item => shouldShow(item, req.session.data))
  .map(item => ({
    ...item,
    value: item.value?.toString(),
    hint: {
      text: item.hint
    },
    conditional: item.components ? renderComponents(res, enrichComponents(item.components, req, res)) : null
  }))
}

const errors = result => {
  const errors = result.error?.details.map(detail => ({
    field: detail.path,
    description: detail.message
  }))
  return errors.reduce(
    (obj, item) => Object.assign(obj, {[item.field]: {'description': item.description}}), {});
}
