module.exports = Object.freeze({
  url: '/add-consignor',
  title: 'Add consignor',
  nextPage: '/traders',
  components: [
    {
      type: 'text',
      name: 'consignorName',
      label: 'Consignor name'
    },
    {
      type: 'text',
      name: 'consignorAddressLine1',
      label: 'Address line 1'
    },
    {
      type: 'text',
      name: 'consignorAddressLine2',
      label: 'Address line 2 (optional)'
    },
    {
      type: 'text',
      name: 'consignorAddressLine3',
      label: 'Address line 3 (optional)'
    },
    {
      type: 'text',
      name: 'consignorCity',
      label: 'City or town'
    },
    {
      type: 'text',
      name: 'consignorPostcode',
      label: 'Postcode or ZIP code'
    },
    {
      type: 'text',
      name: 'consignorTelephoneNumber',
      label: 'Telephone number'
    },
    {
      type: 'select',
      name: 'consignorCountry',
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
      name: 'consignorEmailAddress',
      label: 'Email address'
    }
  ]
})
