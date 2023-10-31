module.exports = Object.freeze({
  title: 'Accompanying documents',
  secondaryTitle: 'Documents',
  url: '/accompanying-documents',
  components: [
    {
      type: 'documents',
      name: 'documents',
      label: 'Additional documents',
      items: [
        // This is to demonstrate data in the rows
        [{
          text: 'Veterinary health certificate'
        },
          {
            text: '1234'
          },
          {
            text: '23 April 2023'
          },
          {
            html: '<a href="/">Remove</a>'
          }
        ],
        //////////////////////////////////////////
        {
          components: [
            {
              type: 'select',
              name: 'documentType',
              items: [
                { value: '', text: 'Select document type', default: true },
                { value: 'veterinaryHealthCertificate', text: 'Veterinary health certificate' },
                { value: 'airWaybill', text: 'Air waybill' },
                { value: 'importPermit', text: 'Import permit' }
              ]
            },
            {
              type: 'text',
              name: 'documentReference'
            },
            {
              type: 'date',
              name: 'date'
            },
            {
              type: 'link',
              name: 'addAttachment',
              label: 'Add attachment'
            }
          ]
        }
      ]
    }
  ]
})
