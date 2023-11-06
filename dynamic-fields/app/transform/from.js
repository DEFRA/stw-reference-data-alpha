const notification = req => {
  const notification = req.session.data.notification
  const commoditySpecies = getCommoditySpecies(notification)
  return {commoditySpecies}
}

const getCommoditySpecies = notification=> {
  return notification?.partOne?.commodities?.commodityComplement.map(complement => {
    return [
      {text: complement.commodityID},
      {text: complement.commodityDescription}
    ]
  })
}

module.exports = {
  notification
}
