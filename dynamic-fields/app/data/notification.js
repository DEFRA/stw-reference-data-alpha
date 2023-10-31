const updateNotification = req => {
  const body = req.body
  let data = req.session.data
  let notification = data.notification

  if (!notification) {
    notification = {}
  }
  switch(req.url) {
    case '/what-are-you-importing':
      notification.type = body.certificateType
      break
    case '/country-of-origin':
      notification.partOne = {
        countryOfOrigin: body.countryOfOrigin,
        consignedCountry: body.countryOfOrigin
      }
      break
    case '/origin-of-the-import':
      notification.isHighRiskEuImport = false
      notification.partOne = {
        ...notification.partOne,
        consignedCountry: body.consignmentCountry,
        importerLocalReferenceNumber: body.internalReferenceNumber
      }
      break
    case '/commodity-picker':
      notification.commodityCode = getCommodityCode(body)
      break
  }
}

const getCommodityCode = body => {
  delete body.action
  const largestKey= Object.keys(body).reduce((a, b) => {
    return body[a].length > body[b].length ? a : b
  })
  return body[largestKey]
}

module.exports = {
  updateNotification
}
