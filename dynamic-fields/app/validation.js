const Joi = require("joi")
const _ = require("lodash")

const check = (body, components) => {
    const schema = Joi.object(createSchema(components)).options({ allowUnknown: true })
    return schema.validate(body, { abortEarly: false })
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

const overrideMessage = (joi, message) => {
    return message ? joi.message(message) : joi
}

module.exports = {
    check
}
