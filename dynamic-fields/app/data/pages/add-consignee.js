module.exports = Object.freeze({
  url: '/add-consignee',
  title: 'Add consignee',
  nextPage: '/traders',
  components: [
    {
      type: 'text',
      name: 'consigneeName',
      label: 'Consignee name'
    },
    {
      type: 'text',
      name: 'consigneeAddressLine1',
      label: 'Address line 1'
    },
    {
      type: 'text',
      name: 'consigneeAddressLine2',
      label: 'Address line 2 (optional)'
    },
    {
      type: 'text',
      name: 'consigneeAddressLine3',
      label: 'Address line 3 (optional)'
    },
    {
      type: 'text',
      name: 'consigneeCity',
      label: 'City or town'
    },
    {
      type: 'text',
      name: 'consigneePostcode',
      label: 'Postcode or ZIP code'
    },
    {
      type: 'text',
      name: 'consigneeTelephoneNumber',
      label: 'Telephone number'
    },
    {
      type: 'select',
      name: 'consigneeCountry',
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
      name: 'consigneeEmailAddress',
      label: 'Email address'
    }
  ]
})
