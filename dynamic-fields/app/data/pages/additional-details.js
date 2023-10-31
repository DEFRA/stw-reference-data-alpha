module.exports = Object.freeze({
  url: '/additional-details',
  title: 'Additional details',
  secondaryTitle: 'Description of the goods',
  nextPage: '/transport-to-bcp',
  components: [
    {
      type: 'totals',
      name: 'animalsTotals',
      label: 'Total',
      items: [
        {
          text: 'Number of animals'
        },
        {
          text: 'Number of packages'
        }
      ],
      conditions: {
        certificateType: ['CHEDA']
      }
    },
    {
      type: 'radio',
      name: 'animalsCertifiedFor',
      label: 'What are the animals certified for?',
      items: [
        {
          value: 'approved',
          text: 'Approved bodies'
        },
        {
          value: 'breeding',
          text: 'Breeding and/or production'
        },
        {
          value: 'pets',
          text: 'Pets'
        },
        {
          value: 'registeredEquidae',
          text: 'Registered equidae'
        },
        {
          value: 'slaughter',
          text: 'Slaughter'
        },
        {
          value: 'other',
          text: 'Other'
        }
      ],
      conditions: {
        certificateType: ['CHEDA']
      }
    },
    {
      type: 'radio',
      name: 'animalsUnweaned',
      label: 'Does the consignment contain any unweaned animals?',
      items: [
        {
          text: 'Yes',
          value: 'yes'
        },
        {
          text: 'No',
          value: 'no'
        }
      ],
      conditions: {
        certificateType: ['CHEDA']
      }
    },
    {
      type: 'totals',
      name: 'plantTotals',
      label: 'Total',
      items: [
        {
          text: 'Net weight of the consignment (kg)'
        },
        {
          text: 'Number of packages of the consignment'
        }
      ],
      conditions: {
        certificateType: ['CHEDPP']
      }
    },
    {
      type: 'text',
      name: 'totalGrossWeight',
      label: 'Total gross weight (kg/units)',
      conditions: {
        certificateType: ['CHEDP', 'CHEDD', 'CHEDPP']
      }
    },
    {
      type: 'text',
      name: 'totalGrossVolume',
      label: 'Total gross volume (optional)',
      conditions: {
        certificateType: ['CHEDPP']
      }
    },
    {
      type: 'select',
      name: 'totalGrossVolumeUnit',
      label: 'Unit',
      items: [
        { value: '', text: 'Select unit' },
        { value: 'liters', text: 'Liters' },
        { value: 'metersCubed', text: 'Meters cubed' }
      ]
    },
    {
      type: 'radio',
      name: 'commodityIntendedFor',
      label: 'Commodity intended for',
      hint: 'find this on the health or phytosanitary certificate (also known as the \'official certificate\') if you have one.',
      items: [
        {
          value: 'feedingStuff',
          text: 'Feeding stuff'
        },
        {
          value: 'furtherProcess',
          text: 'Further process'
        },
        {
          value: 'humanConsumption',
          text: 'Human consumption'
        },
        {
          value: 'other',
          text: 'Other'
        }
      ],
      conditions: {
        certificateType: ['CHEDD']
      }
    },
    {
      type: 'radio',
      name: 'temperature',
      label: 'Temperature',
      items: [
        {
          text: 'Ambient',
          value: 'ambient',
          conditions: {
            'notification.commodityCode': ['07011000']
          }
        },
        {
          text: 'Chilled',
          value: 'chilled'
        },
        {
          text: 'Frozen',
          value: 'frozen'
        }
      ],
      conditions: {
        certificateType: ['CHEDP', 'CHEDD']
      }
    },
    {
      type: 'radio',
      name: 'animalsShippingContainers',
      label: 'Will your goods be imported in shipping containers or road trailers?',
      items: [{
        text: 'Yes',
        value: 'yes',
        components: [{
          type: 'text',
          label: 'Container or trailer number',
          name: 'containerNumber',
          hint: 'Enter the container\'s identification number, or the trailer\'s registration number or number plate.'
        },
          {
            type: 'text',
            label: 'Seal number',
            name: 'sealNumber'
          },
          {
            type: 'checkbox',
            label: 'Official seal',
            name: 'officialSeal',
            items: [{
              text: ' ',
              value: 'officialSeal'
            }]
          }]
      },
        {
          text: 'No',
          value: 'no'
        }]
    }
  ]
})
