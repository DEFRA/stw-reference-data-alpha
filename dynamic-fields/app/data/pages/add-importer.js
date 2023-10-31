module.exports = Object.freeze({
  url: '/add-importer',
  title: 'Add importer',
  nextPage: '/traders',
  components: [
    {
      type: 'text',
      name: 'importerName',
      label: 'Importer name'
    },
    {
      type: 'text',
      name: 'importerAddressLine1',
      label: 'Address line 1'
    },
    {
      type: 'text',
      name: 'importerAddressLine2',
      label: 'Address line 2 (optional)'
    },
    {
      type: 'text',
      name: 'importerAddressLine3',
      label: 'Address line 3 (optional)'
    },
    {
      type: 'text',
      name: 'importerCity',
      label: 'City or town'
    },
    {
      type: 'text',
      name: 'importerPostcode',
      label: 'Postcode or ZIP code'
    },
    {
      type: 'text',
      name: 'importerTelephoneNumber',
      label: 'Telephone number'
    },
    {
      type: 'select',
      name: 'importerCountry',
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
      name: 'importerEmailAddress',
      label: 'Email address'
    }
  ]
})
