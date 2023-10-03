//
// For guidance on how to create routes see:
// https://prototype-kit.service.gov.uk/docs/create-routes
//

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
  { title: 'Origin of the animal or product' },
  { title: 'Origin of the import' },
  { title: 'How do you wnt to add your commodity details' },
  { title: 'Commodity picker' },
  { title: 'Commodity species' },
  { title: 'Variety and class' },
  { title: 'Commodity summary' },
  { title: 'About the consignment (purpose)' },
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

function getPage(url) {
  return pages.find(page => page.url === url)
}

// Add your routes here
router.get('/', (req, res) => {
  const data = pages.map(page => ({
    title: { text: page.title },
    status: page.url
      ? { tag: { text: "Complete", classes: "govuk-tag--blue" } }
      : { tag: { text: "Incomplete", classes: "govuk-tag--grey" } },
    href: page.url
  }))
  res.render('index', { data })
})

pages.filter(page => page.url).forEach(page => {
  router.get(page.url, (req, res) => {
    res.render('questionPage', { pageName: page.title, components: page.components})
  })
})
