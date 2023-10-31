module.exports = Object.freeze({
  url: '/commodity-species',
  title: 'Commodity species',
  nextPage: [
    {
      conditions: {
        addAnother: ['yes']
      },
      url: '/commodity-picker'
    },
    {
      url: '/purpose'
    }
  ],
  components: [
    {
      type: 'select',
      name: 'typeOfCommodity',
      label: 'Type of commodity',
      items: [ // We'd want this to be driven from reference data, not static
        { value: 'domestic', text: 'Domestic' },
        { value: 'farmed', text: 'Farmed game' },
        { value: 'wild', text: 'Wild game' }
      ]
    },
    {
      type: 'checkbox',
      name: 'speciesOfCommodity',
      label: 'Select species of commodity',
      hint: 'Select all that apply',
      items: [
        { value: 'Bison bison', text: 'Bison bison' },
        { value: 'Bos taurus', text: 'Bos taurus' },
        { value: 'Bubalus bubalis', text: 'Bubalus bubalis' }
      ],
      conditions: {
        certificateType: ['CHEDA']
      }
    },
    {
      type: 'radio',
      name: 'addAnother',
      label: 'Do you want to add another commodity?',
      items: [
        { value: 'yes', text: 'Yes' },
        { value: 'no', text: 'No' }
      ],
      conditions: {
        certificateType: ['CHEDA']
      }
    },
    {
      type: 'commoditySpecies',
      name: 'commodity',
      label: 'Commodity',
      items: [
        // This is to demonstrate data in the rows
        {
          text: '05010000'
        },
        {
          text: 'Human hair, unworked, whether or not washed or scoured; waste of human hair'
        }
      ],
      conditions: {
        certificateType: ['CHEDD']
      }
    }
  ]
})
