module.exports = Object.freeze({
  url: '/transport-to-bcp',
  title: 'Transport to the Border Control Post (BCP)',
  nextPage: '/route',
  components: [
    {
      type: 'select',
      label: 'Entry border control post',
      name: 'entry-border-control-post',
      items: [{
        text: 'Entry border control post',
        default: true
      },
        {
          text: 'Belfast Port - GBBEL4',
          value: 'GBBEL4'
        },
        {
          text: 'Bristol - GBBRS1',
          value: 'GBBRS1'
        },
        {
          text: 'Doncaster Sheffield Airport - GBDSA4P',
          value: 'GBDSA4P'
        }]
    },
    {
      type: 'select',
      label: 'Means of transport to the BCP',
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
      label: 'Estimated arrival at BCP',
      hint: 'For example, 27 3 2023'
    },
    {
      type: 'time',
      name: 'time',
      label: 'Time of estimated arrival',
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
