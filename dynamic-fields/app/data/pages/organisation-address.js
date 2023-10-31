module.exports = Object.freeze({
  title: 'Organisation address',
  url: '/organisation-address',
  components: [
    {
      type: 'radio',
      name: 'organisationAddress',
      label: 'Organisation Address',
      hint: 'Choose your organisation branch address',
      items: [
        {
          value: 'cc7a4c6e-393b-4c22-b4bc-22534991baa0',
          text: 'Animal and Plant Health Agency\n' +
            'Woodham Lane\n' +
            'New Haw\n' +
            'Surrey\n' +
            'Addlestone\n' +
            'KT15 3NB\n' +
            'United Kingdom of Great Britain and Northern Ireland'
        },
        {
          value: '11fa26f4-0bbd-4b22-a31d-5a6cceb2bee7',
          text: '960 Bergnaum Points\n' +
            'Apt. 810\n' +
            'Nevada\n' +
            'West Miracle\n' +
            '94533\n' +
            'Brazil'
        },
        {
          value: '7a9e132c-07e9-4d9f-8c99-3d142ccbaa2d',
          text: 'address-line-1\n' +
            'city-or-town\n' +
            'Puerto Rico'
        }
      ]
    }
  ]
})
