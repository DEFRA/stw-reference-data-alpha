module.exports = Object.freeze({
  url: '/traders',
  title: 'Consignor or exporter, consignee, importer and place of destination',
  nextPage: '/transport',
  components: [
    {
      type: 'trader',
      name: 'consignor',
      label: 'Consignor or exporter'
    },
    {
      type: 'trader',
      name: 'consignee',
      label: 'Consignee'
    },
    {
      type: 'trader',
      name: 'Importer',
      label: 'Importer'
    },
    {
      type: 'trader',
      name: 'placeOfDestination',
      label: 'Place of destination'
    }
  ]
})
