module.exports = Object.freeze({
  url: '/origin-of-the-import',
  title: 'Origin of the import',
  secondaryTitle: 'About the consignment',
  nextPage: [
    {
      conditions: {
        certificateType: ['CHEDPP']
      },
      url: '/input-method'
    },
    { // No conditions means default option
      url: '/commodity-picker'
    }
  ],
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
    },
    {
      type: 'radio',
      name: 'requireRegionOfOrigin',
      label: 'Does the consignment require a region of origin code?',
      hint: 'The code will be on the health certificate if required.',
      items: [
        {
          value: true,
          text: 'Yes',
          components: [
            {
              type: 'text',
              name: 'regionOfOrigin',
              label: 'Enter the region of origin code'
            }
          ]
        },
        {
          value: false,
          text: 'No',
          default: true
        }
      ],
      conditions: {
        certificateType: ['CHEDA']
      }
    },
    {
      type: 'radio',
      name: 'requireRegionOfOrigin',
      label: 'Does your consignment require a region code?',
      items: [
        {
          value: true,
          text: 'Yes',
          components: [
            {
              type: 'text',
              name: 'regionOfOrigin',
              label: 'Enter the region code'
            }
          ]
        },
        {
          value: false,
          text: 'No',
          default: true
        }
      ],
      conditions: {
        certificateType: ['CHEDP']
      }
    }, // The label and hints change between CHEDA and CHEDP, so it's duplicated, dunno if there is a nicer way?
    {
      type: 'select',
      name: 'consignmentCountry',
      label: 'Country from where consigned',
      items: [
        { value: '', text: 'Select a country', default: true },
        { value: 'AF', text: 'Afghanistan' },
        { value: 'AX', text: 'Aland Islands' },
        { value: 'AL', text: 'Albania' },
        { value: 'DZ', text: 'Algeria' },
        { value: 'AS', text: 'American Samoa' },
        { value: 'AD', text: 'Andorra' },
        { value: 'AO', text: 'Angola' }
      ],
      conditions: {
        certificateType: ['CHEDP', 'CHEDD', 'CHEDPP']
      }
    },
    {
      type: 'radio',
      name: 'conform',
      label: 'Does this consignment conform to regulatory requirements?',
      items: [
        {
          value: true,
          text: 'Yes'
        },
        {
          value: false,
          text: 'No'
        }
      ],
      conditions: {
        certificateType: ['CHEDP']
      }
    },
    {
      type: 'radio',
      name: 'needMeansOfTranport',
      label: 'Do you need to provide details for means of transport after Border Control Post (BCP)?',
      items: [
        {
          value: true,
          text: 'Yes'
        },
        {
          value: false,
          text: 'No'
        }
      ],
      conditions: {
        certificateType: ['CHEDP']
      }
    },
    {
      type: 'text',
      name: 'internalReferenceNumber',
      label: 'Your internal reference number for this consignment (optional)',
      hint: 'Enter any internal reference number you want to use to identify this consignment, or leave blank.',
      conditions: {
        certificateType: ['CHEDA', 'CHEDD']
      }
    },
    {
      type: 'text',
      name: 'internalReferenceNumber',
      label: 'Add a reference number for this consignment (optional)',
      hint: 'This can be whatever internal reference you use for the consignment.',
      conditions: {
        certificateType: ['CHEDP', 'CHEDPP']
      }
    }
  ]
})
