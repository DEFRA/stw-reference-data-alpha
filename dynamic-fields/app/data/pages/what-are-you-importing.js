module.exports = Object.freeze({
  url: '/what-are-you-importing',
  title: 'What are you importing',
  secondaryTitle: 'About the consignment',
  nextPage: '/country-of-origin',
  components: [
    {
      type: 'radio',
      name: 'certificateType',
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
})
