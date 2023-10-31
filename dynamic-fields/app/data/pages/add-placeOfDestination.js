module.exports = Object.freeze({
  url: '/add-placeOfDestination',
  title: 'Add place of destination',
  nextPage: '/traders',
  components: [
    {
      type: 'text',
      name: 'placeOfDestinationName',
      label: 'Place of destination name'
    },
    {
      type: 'text',
      name: 'placeOfDestinationAddressLine1',
      label: 'Address line 1'
    },
    {
      type: 'text',
      name: 'placeOfDestinationAddressLine2',
      label: 'Address line 2 (optional)'
    },
    {
      type: 'text',
      name: 'placeOfDestinationAddressLine3',
      label: 'Address line 3 (optional)'
    },
    {
      type: 'text',
      name: 'placeOfDestinationCity',
      label: 'City or town'
    },
    {
      type: 'text',
      name: 'placeOfDestinationPostcode',
      label: 'Postcode or ZIP code'
    },
    {
      type: 'text',
      name: 'placeOfDestinationTelephoneNumber',
      label: 'Telephone number'
    },
    {
      type: 'select',
      name: 'placeOfDestinationCountry',
      label: 'Country',
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
      type: 'text',
      name: 'placeOfDestinationEmailAddress',
      label: 'Email address'
    }
  ]
})
