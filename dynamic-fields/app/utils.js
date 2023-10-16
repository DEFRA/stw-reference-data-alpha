const createVarietyAndClassSelectRow = (component, req, res) => {
  const lastItem = component.items[component.items.length - 1]
  const selectVariety = renderComponents(res, [lastItem.components[0]])
  const selectClass = renderComponents(res, [lastItem.components[1]])
  const emptyCell = lastItem.components[2]

  const modifiedItems = component.items.slice()
  modifiedItems.pop()
  modifiedItems.push([selectVariety, selectClass, emptyCell])
  return modifiedItems
}

function renderComponents(res, components) {
  const nunjucks = res.app.get('nunjucksEnv')
  return {
    html: nunjucks.render('components/renderer.njk', {nested: true, components})
  }
}

module.exports = {
  createVarietyAndClassSelectRow,
  renderComponents
}
