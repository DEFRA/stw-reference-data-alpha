module.exports = Object.freeze({
  pages: [
    {
      url: '/what-are-you-importing',
      title: 'What are you importing',
      secondaryTitle: 'About the consignment',
      nextPage: '/country-of-origin',
      components: [
        {
          type: 'radio',
          name: 'certificateType',
          label: 'What are you importing',
          items: [
            {
              value: 'CHEDA',
              text: 'Live animals'
            },
            {
              value: 'CHEDP',
              text: 'Products of animal origin'
            },
            {
              value: 'CHEDD',
              text: 'High risk food and feed of non-animal origin'
            },
            {
              value: 'CHEDPP',
              text: 'Plants, plant products and other objects'
            }
          ]
        }
      ]
    },
    {
      url: '/country-of-origin',
      title: 'Origin of the animal or product',
      secondaryTitle: 'About the consignment',
      nextPage: '/origin-of-the-import',
      components: [
        {
          type: 'select',
          name: 'countryOfOrigin',
          label: 'Country of origin',
          items: [
            {value: '', text: 'Select a country', default: true},
            {value: 'AF', text: 'Afghanistan'},
            {value: 'AX', text: 'Aland Islands'},
            {value: 'AL', text: 'Albania'},
            {value: 'DZ', text: 'Algeria'},
            {value: 'AS', text: 'American Samoa'},
            {value: 'AD', text: 'Andorra'},
            {value: 'AO', text: 'Angola'}
          ]
        }
      ]
    },
    {
      url: '/origin-of-the-import',
      title: 'Origin of the import',
      secondaryTitle: 'About the consignment',
      nextPage: [
        {
          conditions: {
            certificateType: ['CHEDPP']
          },
          url: '/input-method'
        },
        { // No conditions means default option
          url: '/purpose'
        }
      ],
      components: [
        {
          type: 'select',
          name: 'countryOfOrigin',
          label: 'Country of origin',
          items: [
            {value: '', text: 'Select a country', default: true},
            {value: 'AF', text: 'Afghanistan'},
            {value: 'AX', text: 'Aland Islands'},
            {value: 'AL', text: 'Albania'},
            {value: 'DZ', text: 'Algeria'},
            {value: 'AS', text: 'American Samoa'},
            {value: 'AD', text: 'Andorra'},
            {value: 'AO', text: 'Angola'}
          ]
        },
        {
          type: 'radio',
          name: 'requireRegionOfOrigin',
          label: 'Does the consignment require a region of origin code?',
          hint: 'The code will be on the health certificate if required.',
          items: [
            {
              value: true,
              text: 'Yes',
              components: [
                {
                  type: 'text',
                  name: 'regionOfOrigin',
                  label: 'Enter the region of origin code'
                }
              ]
            },
            {
              value: false,
              text: 'No',
              default: true
            }
          ],
          conditions: {
            certificateType: ['CHEDA']
          }
        },
        {
          type: 'radio',
          name: 'requireRegionOfOrigin',
          label: 'Does your consignment require a region code?',
          items: [
            {
              value: true,
              text: 'Yes',
              components: [
                {
                  type: 'text',
                  name: 'regionOfOrigin',
                  label: 'Enter the region code'
                }
              ]
            },
            {
              value: false,
              text: 'No',
              default: true
            }
          ],
          conditions: {
            certificateType: ['CHEDP']
          }
        }, // The label and hints change between CHEDA and CHEDP, so it's duplicated, dunno if there is a nicer way?
        {
          type: 'select',
          name: 'consignmentCountry',
          label: 'Country from where consigned',
          items: [
            {value: '', text: 'Select a country', default: true},
            {value: 'AF', text: 'Afghanistan'},
            {value: 'AX', text: 'Aland Islands'},
            {value: 'AL', text: 'Albania'},
            {value: 'DZ', text: 'Algeria'},
            {value: 'AS', text: 'American Samoa'},
            {value: 'AD', text: 'Andorra'},
            {value: 'AO', text: 'Angola'}
          ],
          conditions: {
            certificateType: ['CHEDP', 'CHEDD', 'CHEDPP']
          }
        },
        {
          type: 'radio',
          name: 'conform',
          label: 'Does this consignment conform to regulatory requirements?',
          items: [
            {
              value: true,
              text: 'Yes'
            },
            {
              value: false,
              text: 'No'
            }
          ],
          conditions: {
            certificateType: ['CHEDP']
          }
        },
        {
          type: 'radio',
          name: 'needMeansOfTranport',
          label: 'Do you need to provide details for means of transport after Border Control Post (BCP)?',
          items: [
            {
              value: true,
              text: 'Yes'
            },
            {
              value: false,
              text: 'No'
            }
          ],
          conditions: {
            certificateType: ['CHEDP']
          }
        },
        {
          type: 'text',
          name: 'internalReferenceNumber',
          label: 'Your internal reference number for this consignment (optional)',
          hint: 'Enter any internal reference number you want to use to identify this consignment, or leave blank.',
          conditions: {
            certificateType: ['CHEDA', 'CHEDD']
          }
        },
        {
          type: 'text',
          name: 'internalReferenceNumber',
          label: 'Add a reference number for this consignment (optional)',
          hint: 'This can be whatever internal reference you use for the consignment.',
          conditions: {
            certificateType: ['CHEDP', 'CHEDPP']
          }
        }
      ]
    },
    {
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
    },
    {title: 'Commodity picker'},
    {
      url: '/commodity-species',
      title: 'Commodity species',
      nextPage: [
        {
          conditions: {
            addAnother: ['yes']
          },
          url: '/commodity-picker'
        },
        {
          url: '/purpose'
        }
      ],
      components: [
        {
          type: 'select',
          name: 'typeOfCommodity',
          label: 'Type of commodity',
          items: [ // We'd want this to be driven from reference data, not static
            {value: 'domestic', text: 'Domestic'},
            {value: 'farmed', text: 'Farmed game'},
            {value: 'wild', text: 'Wild game'}
          ]
        },
        {
          type: 'checkbox',
          name: 'speciesOfCommodity',
          label: 'Select species of commodity',
          hint: 'Select all that apply',
          items: [
            {value: 'Bison bison', text: 'Bison bison'},
            {value: 'Bos taurus', text: 'Bos taurus'},
            {value: 'Bubalus bubalis', text: 'Bubalus bubalis'}
          ]
        },
        {
          type: 'radio',
          name: 'addAnother',
          label: 'Do you want to add another commodity?',
          items: [
            {value: 'yes', text: 'Yes'},
            {value: 'no', text: 'No'}
          ]
        }
      ]
    },
    {
      url: '/variety-and-class',
      title: 'Variety and class of commodity',
      secondaryTitle: 'Description of the goods',
      components: [{
        type: 'varietyAndClass',
        label: 'Variety and class of commodity',
        items: [
          // This is to demonstrate data in the rows
          [{
            text: 'Altess'
          },
          {
            text: 'Class I'
          },
          {
            html: '<a href="/">Remove</a>'
          }
          ],
          [{
            text: 'Aporo'
          },
          {
            text: 'Class I'
          },
          {
            html: '<a href="/">Remove</a>'
          }],
          //////////////////////////////////////////
          {
            components: [
            {
              type: 'select',
              name: 'variety',
              items: [{
                text: 'Select variety',
                default: true
              },
              {
                text: 'Altess',
                value: 'Altess'
              },
              {
                text: 'Aporo',
                value: 'Aporo'
              },
              {
                text: 'Braeburn',
                value: 'Aporo'
              }]
            },
            {
              type: 'select',
              name: 'class',
              items: [{
                text: 'Select class',
                default: true
              },
              {
                text: 'Class I',
                value: 'Class I'
              },
              {
                text: 'Class II',
                value: 'Class II'
              },
              {
                text: 'Extra Class',
                value: 'Extra Class'
              }]
            },
            {
              text:''
            }
            ]
          }
        ]
      }]
    },
    {title: 'Commodity summary'},
    {
      url: '/purpose',
      title: 'About the consignment (purpose)',
      secondaryTitle: 'Purpose of the consignment',
      components: [
        {
          type: 'radio',
          name: 'top-level',
          label: 'What is the purpose of the consignment?',
          items: [
            {
              text: 'For internal market',
              value: 'internal-market'
            },
            {
              text: 'Transhipment / Onward travel',
              value: 'transhipment',
              components: [
                {
                  type: 'select',
                  name: 'bcp',
                  label: 'Exit border control post',
                  items: [{
                    text: 'Select border control post',
                    default: true
                  },
                    {
                      text: 'Belfast Pharmaceuticals - TESTY',
                      value: 'TESTY'
                    },
                    {
                      text: 'Edinburgh Airport (animals) - GBEDI4',
                      value: 'GBEDI4'
                    }]
                },
                {
                  type: 'select',
                  name: 'destination',
                  label: 'Destination country',
                  items: [
                    {
                      text: 'Select destination country',
                      default: true
                    },
                    {
                      text: 'Afghanistan',
                      value: 'AF'
                    },
                    {
                      text: 'Aland Islands',
                      value: 'AX'
                    }]
                }
              ]
            },
            {
              text: 'Transit',
              value: 'transit'
            },
            {
              text: 'Private import',
              value: 'private-import'
            },
            {
              text: 'Transfer to',
              value: 'transfer-to'
            },
            {
              text: 'For import re-conformity check',
              value: 'import-re-conformity-check'
            }
          ]
        }
      ]
    },
    {title: 'Commodity details'},
    {title: 'Commodity details bulk'},
    {
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
          ]
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
          ]
        },
        {
          type: 'radio',
          name: 'animalsUnweaned',
          label: 'Does the consignment contain any unweaned animals?',
          items: [{
            text: 'Yes',
            value: 'yes'
          },
            {
              text: 'No',
              value: 'no'
            }]
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
              name: 'sealNumber',
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
      ],
      conditions: {
        certificateType: ['CHEDA']
      }
    },
    {
      url: '/means-to-transport',
      title: 'Transport to the Border Control Post (BCP)',
      nextPage: '/route',
      components: [
        {
          type: 'select',
          label: 'Entry border control post',
          name: 'entry-border-control-post',
          items: [{
            text: 'Entry border control post',
            default: true
          },
            {
              text: 'Belfast Port - GBBEL4',
              value: 'GBBEL4'
            },
            {
              text: 'Bristol - GBBRS1',
              value: 'GBBRS1'
            },
            {
              text: 'Doncaster Sheffield Airport - GBDSA4P',
              value: 'GBDSA4P'
            }]
        },
        {
          type: 'select',
          label: 'Means of transport to the BCP',
          name: 'means-of-transport',
          items: [{
            text: 'Select means of transport after the BCP',
            default: true
          },
            {
              text: 'Airplane',
              value: 'plane'
            },
            {
              text: 'Railway',
              value: 'rail'
            }]
        },
        {
          type: 'text',
          name: 'identification',
          label: 'Identification',
          hint: 'Flight number, vessel name or vehicle registration',
          validation: {
            data: 'string',
            types: [{
              min: 3
            }, {
              max: 10
            }]
          }
        },
        {
          type: 'text',
          name: 'document',
          label: 'Document',
          hint: 'Air Waybill, Bill of lading or ship manifest',
          validation: {
            data: 'regex',
            types: [{
              regex: '^[\\w\\s]+$'
            }],
            message: 'This is a regex failure'
          }
        },
        {
          type: 'date',
          name: 'date',
          label: 'Estimated arrival at BCP',
          hint: 'For example, 27 3 2023',
        },
        {
          type: 'time',
          name: 'time',
          label: 'Time of estimated arrival',
          items: [
            {
              text: 'Hour',
              value: ''
            },
            {
              text: 'Minutes',
              value: ''
          }],
        }
      ]
    },
    {
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
    },
    {title: 'Nominated contacts'},
    {
      title: 'Accompanying documents',
      secondaryTitle: 'Documents',
      url: '/accompanying-documents',
      components: [
        {
          type: 'documents',
          name: 'documents',
          label: 'Additional documents',
          items: [
            // This is to demonstrate data in the rows
            [{
              text: 'Veterinary health certificate'
            },
            {
              text: '1234'
            },
            {
              text: '23 April 2023'
            },
            {
              html: '<a href="/">Remove</a>'
            }
            ],
            //////////////////////////////////////////
            {
              components: [
              {
                type: 'text',
                name: 'placeOfDestinationName',
              },
              {
                type: 'text',
                name: 'placeOfDestinationAddressLine1',
              },
              {
                type: 'text',
                name: 'placeOfDestinationAddressLine1',
              },
              {
                type: 'text',
                name: 'placeOfDestinationAddressLine1',
              }
            ]
          }
          ]
        }
      ]
    },
    {title: 'Approved establishment of origin'},
    {title: 'Animal identification details'},
    {
      url: '/traders',
      title: 'Consignor or exporter, consignee, importer and place of destination',
      nextPage: '/transport',
      components: [
        {
          type: 'trader',
          name: 'consignor',
          label: 'Consignor or exporter'
        },
        {
          type: 'trader',
          name: 'consignee',
          label: 'Consignee'
        },
        {
          type: 'trader',
          name: 'Importer',
          label: 'Importer'
        },
        {
          type: 'trader',
          name: 'placeOfDestination',
          label: 'Place of destination'
        }
      ]
    },
    {
      url: '/add-consignor',
      title: 'Add consignor',
      nextPage: '/traders',
      components: [
        {
          type: 'text',
          name: 'consignorName',
          label: 'Consignor name'
        },
        {
          type: 'text',
          name: 'consignorAddressLine1',
          label: 'Address line 1'
        },
        {
          type: 'text',
          name: 'consignorAddressLine2',
          label: 'Address line 2 (optional)'
        },
        {
          type: 'text',
          name: 'consignorAddressLine3',
          label: 'Address line 3 (optional)'
        },
        {
          type: 'text',
          name: 'consignorCity',
          label: 'City or town'
        },
        {
          type: 'text',
          name: 'consignorPostcode',
          label: 'Postcode or ZIP code'
        },
        {
          type: 'text',
          name: 'consignorTelephoneNumber',
          label: 'Telephone number'
        },
        {
          type: 'select',
          name: 'consignorCountry',
          label: 'Country',
          items: [
            {value: '', text: 'Select a country', default: true},
            {value: 'AF', text: 'Afghanistan'},
            {value: 'AX', text: 'Aland Islands'},
            {value: 'AL', text: 'Albania'},
            {value: 'DZ', text: 'Algeria'},
            {value: 'AS', text: 'American Samoa'},
            {value: 'AD', text: 'Andorra'},
            {value: 'AO', text: 'Angola'}
          ]
        },
        {
          type: 'text',
          name: 'consignorEmailAddress',
          label: 'Email address'
        }
      ]
    },
    {
      url: '/add-consignee',
      title: 'Add consignee',
      nextPage: '/traders',
      components: [
        {
          type: 'text',
          name: 'consigneeName',
          label: 'Consignee name'
        },
        {
          type: 'text',
          name: 'consigneeAddressLine1',
          label: 'Address line 1'
        },
        {
          type: 'text',
          name: 'consigneeAddressLine2',
          label: 'Address line 2 (optional)'
        },
        {
          type: 'text',
          name: 'consigneeAddressLine3',
          label: 'Address line 3 (optional)'
        },
        {
          type: 'text',
          name: 'consigneeCity',
          label: 'City or town'
        },
        {
          type: 'text',
          name: 'consigneePostcode',
          label: 'Postcode or ZIP code'
        },
        {
          type: 'text',
          name: 'consigneeTelephoneNumber',
          label: 'Telephone number'
        },
        {
          type: 'select',
          name: 'consigneeCountry',
          label: 'Country',
          items: [
            {value: '', text: 'Select a country', default: true},
            {value: 'AF', text: 'Afghanistan'},
            {value: 'AX', text: 'Aland Islands'},
            {value: 'AL', text: 'Albania'},
            {value: 'DZ', text: 'Algeria'},
            {value: 'AS', text: 'American Samoa'},
            {value: 'AD', text: 'Andorra'},
            {value: 'AO', text: 'Angola'}
          ]
        },
        {
          type: 'text',
          name: 'consigneeEmailAddress',
          label: 'Email address'
        }
      ]
    },
    {
      url: '/add-importer',
      title: 'Add importer',
      nextPage: '/traders',
      components: [
        {
          type: 'text',
          name: 'importerName',
          label: 'Importer name'
        },
        {
          type: 'text',
          name: 'importerAddressLine1',
          label: 'Address line 1'
        },
        {
          type: 'text',
          name: 'importerAddressLine2',
          label: 'Address line 2 (optional)'
        },
        {
          type: 'text',
          name: 'importerAddressLine3',
          label: 'Address line 3 (optional)'
        },
        {
          type: 'text',
          name: 'importerCity',
          label: 'City or town'
        },
        {
          type: 'text',
          name: 'importerPostcode',
          label: 'Postcode or ZIP code'
        },
        {
          type: 'text',
          name: 'importerTelephoneNumber',
          label: 'Telephone number'
        },
        {
          type: 'select',
          name: 'importerCountry',
          label: 'Country',
          items: [
            {value: '', text: 'Select a country', default: true},
            {value: 'AF', text: 'Afghanistan'},
            {value: 'AX', text: 'Aland Islands'},
            {value: 'AL', text: 'Albania'},
            {value: 'DZ', text: 'Algeria'},
            {value: 'AS', text: 'American Samoa'},
            {value: 'AD', text: 'Andorra'},
            {value: 'AO', text: 'Angola'}
          ]
        },
        {
          type: 'text',
          name: 'importerEmailAddress',
          label: 'Email address'
        }
      ]
    },
    {
      url: '/add-placeOfDestination',
      title: 'Add place of destination',
      nextPage: '/traders',
      components: [
        {
          type: 'text',
          name: 'placeOfDestinationName',
          label: 'Place of destination name'
        },
        {
          type: 'text',
          name: 'placeOfDestinationAddressLine1',
          label: 'Address line 1'
        },
        {
          type: 'text',
          name: 'placeOfDestinationAddressLine2',
          label: 'Address line 2 (optional)'
        },
        {
          type: 'text',
          name: 'placeOfDestinationAddressLine3',
          label: 'Address line 3 (optional)'
        },
        {
          type: 'text',
          name: 'placeOfDestinationCity',
          label: 'City or town'
        },
        {
          type: 'text',
          name: 'placeOfDestinationPostcode',
          label: 'Postcode or ZIP code'
        },
        {
          type: 'text',
          name: 'placeOfDestinationTelephoneNumber',
          label: 'Telephone number'
        },
        {
          type: 'select',
          name: 'placeOfDestinationCountry',
          label: 'Country',
          items: [
            {value: '', text: 'Select a country', default: true},
            {value: 'AF', text: 'Afghanistan'},
            {value: 'AX', text: 'Aland Islands'},
            {value: 'AL', text: 'Albania'},
            {value: 'DZ', text: 'Algeria'},
            {value: 'AS', text: 'American Samoa'},
            {value: 'AD', text: 'Andorra'},
            {value: 'AO', text: 'Angola'}
          ]
        },
        {
          type: 'text',
          name: 'placeOfDestinationEmailAddress',
          label: 'Email address'
        }
      ]
    },
    {title: 'Transporter'},
    {
      url: '/means-of-transport',
      title: 'Means of transport after Border Control Post (BCP)',
      nextPage: '/route',
      components: [
        {
          type: 'select',
          label: 'Means of transport after the BCP',
          name: 'means-of-transport',
          items: [{
            text: 'Select means of transport after the BCP',
            default: true
          },
            {
              text: 'Airplane',
              value: 'plane'
            },
            {
              text: 'Railway',
              value: 'rail'
            }]
        },
        {
          type: 'text',
          name: 'identification',
          label: 'Identification',
          hint: 'Flight number, vessel name or vehicle registration',
          validation: {
            data: 'string',
            types: [{
              min: 3
            }, {
              max: 10
            }]
          }
        },
        {
          type: 'text',
          name: 'document',
          label: 'Document',
          hint: 'Air Waybill, Bill of lading or ship manifest',
          validation: {
            data: 'regex',
            types: [{
              regex: '^[\\w\\s]+$'
            }],
            message: 'This is a regex failure'
          }
        },
        {
          type: 'date',
          name: 'date',
          label: 'Date and time of departure',
          hint: 'For example, 15 8 2020',
        },
        {
          type: 'time',
          name: 'time',
          hint: '24 hour format',
          items: [
            {
              text: 'Hour',
              value: ''
            },
            {
              text: 'Minutes',
              value: ''
            }],
        }
      ]
    },
    {title: 'Route'},
    {
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
    },
    {title: 'Do you know the commodity code'},
    {
      title: 'Which animal or product are you importing?',
      url: '/which-products-you-are-importing',
      components: [
        {
          type: 'details',
          label: 'What to do if your animal or product is not shown',
          hint: 'If your animal or product does not appear on this list then you will need to use the commodity selector <a href="/">here</a> . There is IPAFFS guidance available if you need further assistance.',
        },
        {
          type: 'select',
          name: 'productImporting',
          items: [
            {value: '', text: 'Please choose', default: true},
            {value: '61_188306', text: 'Bees – Bumble Bees'},
            {value: '60_8433', text: 'Bees - Honey Bees'},
            {value: '3_113311', text: 'Birds of Prey - Falcons'}
          ]
        }
      ]
    },
    {title: 'How many animals or products are you importing?'},
    {title: 'Commodity quantity'},
    {title: 'Enter ant identification details you have for the animal or product'},
    {title: 'When are you planning to import the animal or product'},
    {title: 'What is the reason for the movement of this animal or product'},
    {title: 'County parish holding number'},
    {title: 'Transport details'}
  ]
})