module.exports = Object.freeze({
  url: '/purpose',
  title: 'About the consignment (purpose)',
  secondaryTitle: 'Purpose of the consignment',
  components: [
    {
      type: 'radio',
      name: 'top-level',
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
      ]
    }
  ]
})
