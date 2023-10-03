//
// For guidance on how to create routes see:
// https://prototype-kit.service.gov.uk/docs/create-routes
//
const _ = require('lodash')
const govukPrototypeKit = require('govuk-prototype-kit')
const router = govukPrototypeKit.requests.setupRouter()

const pages = [
  {
    url: '/what-are-you-importing',
    title: 'What are you importing',
    components: [
      {
        type: 'radio',
        name: 'importing',
        label: 'What are you importing',
        items: [
          {
            value: 'CHEDA',
            text: 'Live animals'
          },
          {
            value: 'CHEDP',
            text: 'Products of animal origin'
          },
          {
            value: 'CHEDD',
            text: 'High risk food and feed of non-animal origin'
          },
          {
            value: 'CHEDPP',
            text: 'Plants, plant products and other objects'
          }
        ]
      }
    ]
  },
  {
    url: '/country-of-origin',
    title: 'Origin of the animal or product',
    components: [
      {
        type: 'select',
        name: 'countryOfOrigin',
        label: 'Country of origin',
        items: [
          { value: '', text: 'Select a country', default: true },
          { value: 'AF', text: 'Afghanistan' },
          { value: 'AX', text: 'Aland Islands' },
          { value: 'AL', text: 'Albania' },
          { value: 'DZ', text: 'Algeria' },
          { value: 'AS', text: 'American Samoa' },
          { value: 'AD', text: 'Andorra' },
          { value: 'AO', text: 'Angola' }
        ]
      }
    ]
  },
  {
    url: '/origin-of-the-import',
    title: 'Origin of the import',
    components: [
      {
        type: 'select',
        name: 'countryOfOrigin',
        label: 'Country of origin',
        items: [
          { value: '', text: 'Select a country', default: true },
          { value: 'AF', text: 'Afghanistan' },
          { value: 'AX', text: 'Aland Islands' },
          { value: 'AL', text: 'Albania' },
          { value: 'DZ', text: 'Algeria' },
          { value: 'AS', text: 'American Samoa' },
          { value: 'AD', text: 'Andorra' },
          { value: 'AO', text: 'Angola' }
        ]
      },
      {
        type: 'radio',
        name: 'requireRegionOfOrigin',
        label: 'Does the consignment require a region of origin code?',
        items: [
          {
            value: true,
            text: 'Yes',
            components: [
              {
                type: 'text',
                name: 'regionOfOrigin',
                label: 'Enter the region of origin code'
              }
            ]
          },
          {
            value: false,
            text: 'No'
          }
        ]
      },
      {
        type: 'text',
        name: 'internalReferenceNumber',
        label: 'Your internal reference number for this consignment (optional)'
      }
    ]
  },
  {
    url: '/input-method',
    title: 'How do you want to add your commodity details',
    components: [
      {
        type: 'radio',
        name: 'inputMethod',
        label: 'How do you want to add your commodity details?',
        items: [
          {
            value: 'manual',
            text: 'Manual entry',
            hint: 'Enter one commodity line at a time.'
          },
          {
            value: 'csv',
            text: 'Upload from a CSV file',
            hint: 'Add all details at once, by uploading a file you can prepare with most ' +
              'spreadsheet software. Recommended for consignments with many commodity lines.'
          }
        ]
      }
    ]
  },
  { title: 'Commodity picker' },
  { title: 'Commodity species' },
  { title: 'Variety and class' },
  { title: 'Commodity summary' },
  {
    url: '/purpose',
    title: 'About the consignment (purpose)',
    components: [
      {
        type: 'radio',
        name: 'top-level',
        label: 'What is the purpose of the consignment?',
        items: [{
            text: 'For internal market',
            value: 'internal-market'
          },
          {
            text: 'Transhipment / Onward travel',
            value: 'transhipment',
            components: [
              {
                type: 'dropdown',
                name: 'bcp',
                text: 'Exit border control post',
                items: [{
                  text: 'Select border control post',
                  selected: true
                },
                {
                  text: 'Belfast Pharmaceuticals - TESTY',
                  value: 'TESTY'
                },
                {
                  text: 'Edinburgh Airport (animals) - GBEDI4',
                  value: 'GBEDI4'
                }]
              },
              {
                type: 'dropdown',
                name: 'destination',
                text: 'Destination country',
                items: [{
                  text: 'Select destination country',
                  selected: true
                },
                  {
                    text: 'Afghanistan',
                    value: 'AF'
                  },
                  {
                    text: 'Aland Islands',
                    value: 'AX'
                  }]
              }
            ]
          },
          {
            text: 'Transit',
            value: 'transit'
          },
          {
            text: 'Private import',
            value: 'private-import'
          },
          {
            text: 'Transfer to',
            value: 'transfer-to'
          },
          {
            text: 'For import re-conformity check',
            value: 'import-re-conformity-check'
          }
        ]
      }
    ]
  },
  { title: 'Commodity details' },
  { title: 'Commodity details bulk' },
  { title: 'Additional details' },
  { title: 'Transport to the BCP' },
  {
    url: '/contact-details',
    title: 'Contact details',
    components: [
      {
        type: 'text',
        name: 'name',
        label: 'Name'
      },
      {
        type: 'text',
        name: 'email',
        label: 'Email address'
      },
      {
        type: 'text',
        name: 'mobile',
        label: 'Mobile number'
      },
      {
        type: 'text',
        name: 'agent',
        label: 'Agent'
      }
    ]
  },
  { title: 'Nominated contacts' },
  { title: 'Accompanying documents' },
  { title: 'Approved establishment of origin' },
  { title: 'Adminal identification details' },
  { title: 'Consignor or exporter, consignee, importer and place of destination' },
  { title: 'Transporter' },
  { title: 'Means of transport after BCP' },
  { title: 'Route' },
  { title: 'Organisation address' },
  { title: 'Do you know the commodity code' },
  { title: 'Which animal or product are you importing' },
  { title: 'How many animals or products are you importing' },
  { title: 'Commodity quantity' },
  { title: 'Enter ant identification details you have for the animal or product' },
  { title: 'When are you planning to import the animal or product' },
  { title: 'What is the reason for the movement of this animal or product' },
  { title: 'County parish holding number' },
  { title: 'Transport details' }
]

// Add your routes here
router.get('/', (req, res) => {
  const data = pages.map(page => ({
    title: { text: page.title },
    href: page.url
  }))
  res.render('index', { data })
})

pages.filter(page => page.url).forEach(page => {
  router.get(page.url, (req, res) => {
    if (page.title === 'About the consignment (purpose)') {
      enrichComponents(res, page)
      return res.render('questionPage', { pageName: page.title, components: page.components})
    }
    res.render('questionPage', {
      pageName: page.title,
      components: page.components.map(component => ({
        ...component,
        items: component.items?.map(item => ({
          ...item,
          hint: {
            text: item.hint
          }
        }))
      }))
    })
  })
})

const enrichComponents = (res, page) => {
  const nunjucks = res.app.get('nunjucksEnv')
  const item = _.chain(page.components)
      .map(i => i.items)
      .flatten()
      .filter(i => i.value === 'transhipment')
      .first()
      .value()
  item.conditional = {
    html: nunjucks.render('components/transhipment.njk', { components: item.components} )
  }
}
