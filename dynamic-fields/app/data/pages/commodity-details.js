module.exports = Object.freeze({
  title: 'Commodity details',
  url: '/commodity-details',
  components: [
    {
      type: 'array',
      of: 'commodityCode',
      components: [
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
    }
  ]
})
