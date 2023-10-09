const Joi = require("joi")
const _ = require("lodash")

const check = (body, components) => {
    const enrichedBody = enrichBody(body)
    const schema = Joi.object(createSchema(components)).options({ allowUnknown: true })
    return schema.validate(enrichedBody, { abortEarly: false })
}
const enrichBody = body => {
    return {...body, date: body['date-day'] + '-' + body['date-month'] + '-' + body['date-year'] }
}

const createSchema = components => {
    const validationFields = components.map(component => {
        return { name: component.name, value: createJoi(component.validation)}
    })
    return validationFields.reduce(
        (obj, item) => Object.assign(obj, { [item.name]: item.value }), {});
}

const createJoi = validation => {
    if(!validation?.data){
        return Joi.any()
    }
    let joi = createJoiBaseType(validation.data)
    joi = updateJoiForDataConstraints(joi, validation.types)
    return overrideMessage(joi, validation.message)
}

const overrideMessage = (joi, message) => {
    if (message) {
        return joi.message(message)
    }
}

const createJoiBaseType = data => {
    if (data === 'string' || data === 'regex')
        return Joi.string()
    else if (data === 'number')
        return Joi.number()
    else if (data === 'object')
        return Joi.object()
    else if (data === 'date')
        return Joi.date()
}

const updateJoiForDataConstraints = (joi, types) => {
    let val = joi
    types.forEach(type => {
        if (type.min) {
            val = val.min(type.min)
        }
        else if (type.max) {
            val = val.max(type.max)
        }
        else if (type.greater) {
            val = val.greater(type.greater)
        }
        else if (type.iso) {
            val = val.iso()
        }
        else if (type.regex) {
            val = val.regex(RegExp(type.regex))
        }
    })
    return val
}

module.exports = {
    check
}
