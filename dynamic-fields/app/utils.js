const createHtmlRow = (component, req, res) => {
  return component.items?.map(item => {
    return item.components.map(component =>{
      return renderComponents(res, [component])
    })
  })
}

const cloneThenUpdateLastRow = (component, req, res) => {
  const lastRow = component.items[component.items.length - 1]
  const renderedLastRow= lastRow.components.map(component =>{
    return renderComponents(res, [component])
  })

  const clonedItems = component.items.slice()
  clonedItems.pop()
  clonedItems.push(renderedLastRow)
  return clonedItems
}

function renderComponents(res, components) {
  const nunjucks = res.app.get('nunjucksEnv')
  return {
    html: nunjucks.render('components/renderer.njk', {nested: true, components})
  }
}

module.exports = {
  cloneThenUpdateLastRow,
  createHtmlRow,
  renderComponents
}
