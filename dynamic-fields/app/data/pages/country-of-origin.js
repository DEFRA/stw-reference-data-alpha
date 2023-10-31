module.exports = Object.freeze({
  url: '/country-of-origin',
  title: 'Origin of the animal or product',
  secondaryTitle: 'About the consignment',
  nextPage: '/origin-of-the-import',
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
})
