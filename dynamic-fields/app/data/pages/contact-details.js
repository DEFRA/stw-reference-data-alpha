module.exports = Object.freeze({
  url: '/contact-details',
  title: 'Contact details',
  components: [
    {
      type: 'text',
      name: 'name',
      label: 'Name'
    },
    {
      type: 'text',
      name: 'email',
      label: 'Email address'
    },
    {
      type: 'text',
      name: 'mobile',
      label: 'Mobile number'
    },
    {
      type: 'text',
      name: 'agent',
      label: 'Agent'
    }
  ],
  conditions: {
    certificateType: ['CHEDPP']
  }
})
