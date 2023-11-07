const certTypeMap = new Map([
  ['CHEDD', 'CED']
])

const notification = req => {
  const body = req.body
  let data = req.session.data
  let notification = data.notification

  if (!data.notification) {
    notification = init()
  }

  switch(req.url) {
    case '/what-are-you-importing':
      notification.type = certTypeMap.get(body.certificateType)
      break
    case '/country-of-origin':
      notification.partOne = {
        ...notification.partOne,
        commodities: {
          countryOfOrigin: body.countryOfOrigin,
          consignedCountry: body.countryOfOrigin
        }
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
      notification.partOne = {
        ...notification.partOne,
        commodities: getCommodities(body)
      }
      break
    case '/purpose':
      notification = {
        ...notification,
        purpose: getPurpose(body)
      }
      break
  }
  data.notification = notification
}

const init = () => {
  return {
    id: 999999,
    referenceNumber: 'DRAFT.GB.2023.999999',
    version: 0,
    lastUpdated: new Date().toISOString(),
    lastUpdatedBy: {
      displayName: 'Mark Admin-Tester',
      userId: '79f6dc68-2144-e911-a96a-000d3a29ba60'
    },
    status: 'DRAFT',
    isHighRiskEuImport: false,
    partOne: {
      personResponsible: {
        name: 'Mark Admin-Tester',
          companyId: '767ceb6a-2144-e911-a96c-000d3a29b5de',
          companyName: 'Defra Test BIP',
          country: 'GB',
          tracesID: 1001,
          phone: '020 8225 7295',
          email: 'DefraTestBIP@anthunt3.33mail.com',
          contactId: '79f6dc68-2144-e911-a96a-000d3a29ba60'
      }
    }
  }
}

const getCommodities = body => {
  return {
    commodityComplement: [{
      commodityID: getCommodityCode(body),
      commodityDescription: getCommodityDescription(body),
      complementID: 1,
      speciesType: '2',
      speciesClass: '14357'
    }],
    complementParameterSet: [{
        uniqueComplementID: 'a288d00f-4f03-4f1f-b024-0fd72553b145',
        complementID: 1,
        keyDataPair: [
          {}
        ]
    }]
  }
}

const getPurpose = body => {
  if (body.transfer) {
    return {
      purposeGroup: body.purpose,
      finalBIP: body.transfer
    }
  }
  return {
    purposeGroup: body.purpose
  }
}

const getCommodityCode = body => {
  const commodity = getSelectedCommodity(body)
  return commodity.split(' - ')[0]
}

const getCommodityDescription = body => {
  const commodity = getSelectedCommodity(body)
  return commodity.split(' - ')[1]
}

const getSelectedCommodity = body => {
  delete body.action
  const largestKey = Object.keys(body).reduce((a, b) => {
    return body[a].split(' - ')[0].length > body[b].split(' - ')[0].length ? a : b
  })
  return body[largestKey]
}

module.exports = {
  notification
}
