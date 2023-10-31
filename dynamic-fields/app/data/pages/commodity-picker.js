module.exports = Object.freeze({
  title: 'Commodity picker',
  url: '/commodity-picker',
  nextPage: '/commodity-species',
  components: [{
    type: 'radio',
    name: 'top-level',
    label: 'Commodity',
    hint: 'Find the commodity in the commodity tree',
    items: [
      {
        value: '05',
        html: '<b>05</b> - PRODUCTS OF ANIMAL ORIGIN, NOT ELSEWHERE SPECIFIED OR INCLUDED'
      },
      {
        value: '07',
        html: '<b>07</b> - EDIBLE VEGETABLES AND CERTAIN ROOTS AND TUBERS',
        components: [
          {
            type: 'radio',
            name: 'code07',
            items: [{
              html: '<b>07 01</b> - Potatoes, fresh or chilled',
              value: '0701',
              components: [{
                type: 'radio',
                name: 'code0701',
                items: [
                  {
                    value: '07011000',
                    html: '<b>07 01 10 00</b> - Seed'
                  },
                  {
                    value: '070190',
                    html: '<b>07 01 90</b> - Other',
                    components: [{
                      type: 'radio',
                      name: 'code070190',
                      items: [
                        {
                          value: '07019010',
                          html: '<b>07 01 90 10</b> - For the manufacture of starch'
                        },
                        {
                          value: '07019050',
                          html: '<b>07 01 90 50</b> - New, from 1 January to 30 June'
                        },
                        {
                          value: '07019090',
                          html: '<b>07 01 90 90</b> - Other <b>(or select)</b>'
                        }
                      ]
                    }]
                  }
                ]
              }]
            },
              {
                value: '07020000',
                html: '<b>07 02 00 00</b> - Tomatoes, fresh or chilled <b>(or select)</b>'
              }]
          }
        ]
      }
    ]
  }]
})
