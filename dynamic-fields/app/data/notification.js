const updateNotification = req => {
  const body = req.body
  let data = req.session.data
  if (!data.notification) {
    data.notification = {}
  }

  data.notification.commodityCode = getCommodityCode(body)
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
