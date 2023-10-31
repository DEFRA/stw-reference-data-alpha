module.exports = Object.freeze({
  url: '/variety-and-class',
  title: 'Variety and class of commodity',
  secondaryTitle: 'Description of the goods',
  components: [{
    type: 'varietyAndClass',
    label: 'Variety and class of commodity',
    items: [
      // This is to demonstrate data in the rows
      [{
        text: 'Altess'
      },
        {
          text: 'Class I'
        },
        {
          html: '<a href="/">Remove</a>'
        }
      ],
      [{
        text: 'Aporo'
      },
        {
          text: 'Class I'
        },
        {
          html: '<a href="/">Remove</a>'
        }],
      //////////////////////////////////////////
      {
        components: [
          {
            type: 'select',
            name: 'variety',
            items: [{
              text: 'Select variety',
              default: true
            },
              {
                text: 'Altess',
                value: 'Altess'
              },
              {
                text: 'Aporo',
                value: 'Aporo'
              },
              {
                text: 'Braeburn',
                value: 'Aporo'
              }]
          },
          {
            type: 'select',
            name: 'class',
            items: [{
              text: 'Select class',
              default: true
            },
              {
                text: 'Class I',
                value: 'Class I'
              },
              {
                text: 'Class II',
                value: 'Class II'
              },
              {
                text: 'Extra Class',
                value: 'Extra Class'
              }]
          },
          {
            text: ''
          }
        ]
      }
    ]
  }]
})
