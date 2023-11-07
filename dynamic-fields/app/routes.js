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
        // The commodities data would come from the notification
        const chedaCommodities = [
          {
            code: '0101',
            description: 'Live horses, asses, mules and hinnies',
            species: ['Equus asinus']
          },
          {
            code: '0102',
            description: 'Live bovine animals',
            species: ['Bison bison, Domestic', 'Bos taurus, Domestic']
          }
        ]
        const chedpCommodities = [
          {
            code: '020130',
            description: 'Boneless',
            species: ['Bison bison, Domestic', 'Bos taurus, Domestic']
          },
          {
            code: '02041000',
            description: ' \tCarcases and half-carcases of lamb, fresh or chilled',
            species: ['Ovis aries, Domestic']
          }
        ]
        // The components are the metadata part
        const chedaComponents = [
          {
            type: 'text',
            name: 'numberOfAnimals',
            label: 'Number of animals'
          },
          {
            type: 'text',
            name: 'numberOfPackages',
            label: 'Number of packages'
          }
        ]
        const chedpComponents = [
          {
            type: 'text',
            name: 'netWeight',
            label: 'Net weight (kg/units)'
          },
          {
            type: 'text',
            name: 'numberOfPackages',
            label: 'Number of packages'
          },
          {
            type: 'select',
            name: 'typeOfPackage',
            label: 'Type of package',
            items: [
              { value: '', text: 'Select type of package' },
              { value: 'bag', text: 'Bag' },
              { value: 'bale', text: 'Bale' },
              { value: 'balloonProtected', text: 'Baloon protected' },
              { value: 'block', text: 'Block' },
              { value: 'box', text: 'Box' },
              { value: 'can', text: 'Can' },
              { value: 'carton', text: 'Carton' }
            ]
          }
        ]

        const commodities = req.session.data.certificateType === 'CHEDA' ? chedaCommodities : chedpCommodities

        // This represents calling some service to get the correct components for current
        // notification state
        const components = req.session.data.certificateType === 'CHEDA' ? chedaComponents : chedpComponents

        return res.render('pages/commodityDetails', {
          pageName: page.title,
          commodityRows: commodities.map(commodity => ([
            { text: commodity.code },
            { text: commodity.description },
            { html: '<a href="#" class="govuk-link">Remove</a>' }
          ])),
          commodityTables: commodities.map(commodity => ({
            caption: commodity.description,
            head: [
              { text: 'Species, type, class and family' },
              ...components.map(({ label }) => ({ text: label }))
            ],
            rows: commodity.species.map(species => ([
              { text: species },
              ...components.map(component => ({
                html: res.app.get('nunjucksEnv').render('components/renderer', {
                  components: [{
                    labelHidden: true,
                    formGroupClasses: 'govuk-!-margin-bottom-0',
                    ...component
                  }]
                })
              }))
            ]))
          }))
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
