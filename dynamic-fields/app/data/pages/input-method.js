module.exports = Object.freeze({
  url: '/input-method',
  title: 'How do you want to add your commodity details',
  nextPage: '/purpose',
  components: [
    {
      type: 'radio',
      name: 'inputMethod',
      label: 'How do you want to add your commodity details?',
      items: [
        {
          value: 'manual',
          text: 'Manual entry',
          hint: 'Enter one commodity line at a time.'
        },
        {
          value: 'csv',
          text: 'Upload from a CSV file',
          hint: 'Add all details at once, by uploading a file you can prepare with most ' +
            'spreadsheet software. Recommended for consignments with many commodity lines.'
        }
      ]
    }
  ]
})
