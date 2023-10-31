module.exports = Object.freeze({
  url: '/means-of-transport',
  title: 'Means of transport after Border Control Post (BCP)',
  nextPage: '/route',
  components: [
    {
      type: 'select',
      label: 'Means of transport after the BCP',
      name: 'means-of-transport',
      items: [{
        text: 'Select means of transport after the BCP',
        default: true
      },
        {
          text: 'Airplane',
          value: 'plane'
        },
        {
          text: 'Railway',
          value: 'rail'
        }]
    },
    {
      type: 'text',
      name: 'identification',
      label: 'Identification',
      hint: 'Flight number, vessel name or vehicle registration',
      validation: {
        data: 'string',
        types: [{
          min: 3
        }, {
          max: 10
        }]
      }
    },
    {
      type: 'text',
      name: 'document',
      label: 'Document',
      hint: 'Air Waybill, Bill of lading or ship manifest',
      validation: {
        data: 'regex',
        types: [{
          regex: '^[\\w\\s]+$'
        }],
        message: 'This is a regex failure'
      }
    },
    {
      type: 'date',
      name: 'date',
      label: 'Date and time of departure',
      hint: 'For example, 15 8 2020'
    },
    {
      type: 'time',
      name: 'time',
      hint: '24 hour format',
      items: [
        {
          text: 'Hour',
          value: ''
        },
        {
          text: 'Minutes',
          value: ''
        }]
    }
  ]
})
