module.exports = Object.freeze({
  url: '/purpose',
  title: 'About the consignment (purpose)',
  secondaryTitle: 'Purpose of the consignment',
  nextPage: '/',
  components: [
    {
      type: 'radio',
      name: 'purpose',
      label: 'What is the purpose of the consignment?',
      items: [
        {
          text: 'For internal market',
          value: 'internal-market'
        },
        {
          text: 'Transhipment / Onward travel',
          value: 'transhipment',
          components: [
            {
              type: 'radio',
              name: 'code07',
              items: [{
                text: 'Select border control post',
                default: true
              },
                {
                  text: 'Belfast Pharmaceuticals - TESTY',
                  value: 'TESTY'
                },
                {
                  text: 'Edinburgh Airport (animals) - GBEDI4',
                  value: 'GBEDI4'
                }]
            },
            {
              type: 'select',
              name: 'destination',
              label: 'Destination country',
              items: [
                {
                  text: 'Select destination country',
                  default: true
                },
                {
                  text: 'Afghanistan',
                  value: 'AF'
                },
                {
                  text: 'Aland Islands',
                  value: 'AX'
                }]
            }
          ]
        },
        {
          text: 'Transit',
          value: 'transit'
        },
        {
          text: 'Private import',
          value: 'private-import'
        },
        {
          text: 'Transfer to',
          value: 'transfer-to'
        },
        {
          text: 'For import re-conformity check',
          value: 'import-re-conformity-check'
        }
      ],
      conditions: {
        certificateType: ['CHEDP', 'CHEDA', 'CHEDPP']
      }
    },
    {
      type: 'radio',
      name: 'purpose',
      label: 'What is the purpose of the consignment?',
      items: [
        {
          text: 'For internal market',
          value: 'For import'
        },
        {
          text: 'For transfer to',
          value: 'For Transhipment to',
          components: [
          {
              type: 'select',
              name: 'transfer',
              label: 'Exit Border Control Post',
              items: [{
                text: 'Select Border Control Post',
                default: true
              },
              {
                text: 'Belfast Pharmaceuticals - TESTY',
                value: 'TESTY'
              },
              {
                text: 'Edinburgh Airport (animals) - GBEDI4',
                value: 'GBEDI4'
              }]
            }
          ]
        }
      ],
      conditions: {
        certificateType: ['CHEDD']
      }
    }
  ]
})
