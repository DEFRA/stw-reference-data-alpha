module.exports = Object.freeze({
  title: 'Which animal or product are you importing?',
  url: '/which-products-you-are-importing',
  components: [
    {
      type: 'details',
      label: 'What to do if your animal or product is not shown',
      hint: 'If your animal or product does not appear on this list then you will need to use the commodity selector <a href="/">here</a> . There is IPAFFS guidance available if you need further assistance.'
    },
    {
      type: 'select',
      name: 'productImporting',
      items: [
        { value: '', text: 'Please choose', default: true },
        { value: '61_188306', text: 'Bees – Bumble Bees' },
        { value: '60_8433', text: 'Bees - Honey Bees' },
        { value: '3_113311', text: 'Birds of Prey - Falcons' }
      ]
    }
  ]
})
