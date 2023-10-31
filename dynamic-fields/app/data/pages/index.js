const accompanyingDocuments = require('./accompanying-documents')
const addConsignee = require('./add-consignee')
const addConsignor = require('./add-consignor')
const addImporter = require('./add-importer')
const addPlaceOfDestination = require('./add-placeOfDestination')
const additionalDetails = require('./additional-details')
const animalIdentificationDetails = require('./animal-identification-details')
const approvedEstablishmentOfOrigin = require('./approved-establishment-of-origin')
const commodityDetails = require('./commodity-details')
const commodityDetailsBulk = require('./commodity-details-bulk')
const commodityPicker = require('./commodity-picker')
const commodityQuantity = require('./commodity-quantity')
const commoditySpecies = require('./commodity-species')
const commoditySummary = require('./commodity-summary')
const contactDetails = require('./contact-details')
const countryOfOrigin = require('./country-of-origin')
const countyParishHoldingNumber = require('./county-parish-holding-number')
const declaration = require('./declaration')
const doYouKnowTheCommodityCode = require('./do-you-know-the-commdoity-code')
const howManyAnimals = require('./how-many-animals')
const identificationDetails = require('./identification-details')
const inputMethod = require('./input-method')
const meansOfTransport = require('./means-of-transport')
const nominatedContacts = require('./nominated-contacts')
const organisationAddress = require('./organisation-address')
const originOfTheImport = require('./origin-of-the-import')
const purpose = require('./purpose')
const reasonForMovement = require('./reason-for-movement')
const route = require('./route')
const traders = require('./traders')
const transportDetails = require('./transport-details')
const transportToBcp = require('./transport-to-bcp')
const transporter = require('./transporter')
const varietyAndClass = require('./variety-and-class')
const whatAreYouImporting = require('./what-are-you-importing')
const whenAreYouImporting = require('./when-are-you-importing')
const whichProductsAreYouImport = require('./which-products-are-you-importing')

module.exports = Object.freeze({
  pages: [
    whatAreYouImporting,
    countryOfOrigin,
    originOfTheImport,
    inputMethod,
    commodityPicker,
    commoditySpecies,
    varietyAndClass,
    commoditySummary,
    purpose,
    commodityDetails,
    commodityDetailsBulk,
    additionalDetails,
    transportToBcp,
    contactDetails,
    nominatedContacts,
    accompanyingDocuments,
    approvedEstablishmentOfOrigin,
    animalIdentificationDetails,
    traders,
    addConsignor,
    addConsignee,
    addImporter,
    addPlaceOfDestination,
    transporter,
    meansOfTransport,
    route,
    organisationAddress,
    doYouKnowTheCommodityCode,
    whichProductsAreYouImport,
    howManyAnimals,
    commodityQuantity,
    identificationDetails,
    whenAreYouImporting,
    reasonForMovement,
    countyParishHoldingNumber,
    transportDetails,
    declaration
  ]
})
