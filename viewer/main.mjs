import * as d3 from 'https://cdn.jsdelivr.net/npm/d3@7/+esm'

const response = await fetch('http://localhost:9001/commodities')
const data = await response.json()
console.log(data)

// https://observablehq.com/@d3/force-directed-graph/2?intent=fork
// https://d3js.org/d3-force

// Two output lists: nodes, links
const nodes = []
const links = []
// One stack starting with the elements in data
const stack = data.data
// While stack not empty pop from the stack
while (stack.length) {
  const element = stack.shift()
  const id = `${element.type}-${element.id}`
  //   If already exists in nodes: return
  if (!nodes.some(n => n.id === id)) {
    //   Add element to nodes
    nodes.push({
      id,
      type: element.type,
      name: getName(element),
      attributes: element.attributes
    })
    //   Look at relationships: add to links and elements into the stack
    if (element.relationships) {
      for (const [k, v] of Object.entries(element.relationships)) {
        const { type: otherType, id: otherId } = v.data
        links.push({
          source: id,
          target: `${otherType}-${otherId}`
        })
        stack.push(findIncluded(otherType, otherId))
      }
    }
  }
}

function findIncluded (type, id) {
  return data.included.find(e => e.type === type && e.id === id)
}

function getName(element) {
  switch(element.type) {
    case 'commodity':
      return `Commodity ${element.id}`
    case 'certificate':
      return element.attributes.name
    case 'commodity_code':
      return element.attributes.code.replaceAll(/(00)*$/g, '')
    case 'species':
      return element.attributes.species
  }
}

// Sources
// https://observablehq.com/@d3/mobile-patent-suits
// https://observablehq.com/@d3/force-directed-graph/2

// Specify the dimensions of the chart.
const width = window.innerWidth
const height = window.innerHeight

// Specify the color scale.
const color = d3.scaleOrdinal(d3.schemeCategory10)

// Create a simulation with several forces.
const simulation = d3.forceSimulation(nodes)
.force('link', d3.forceLink(links).id(d => d.id).distance(10))
.force('charge', d3.forceManyBody().strength(-500))
.force('center', d3.forceCenter(width / 2, height / 2))
.force('x', d3.forceX(width / 2))
.force('y', d3.forceY(width / 2))
// .force('collision', d3.forceCollide(d => d.name.length * 3))
.alphaDecay(0.01)
.on('tick', ticked)

// Create the SVG container.
const svg = d3.create('svg')
.attr('width', width)
.attr('height', height)
.attr('viewBox', [0, 0, width, height])
.attr('style', 'max-width: 100%; height: auto;')

// Add a line for each link, and a circle for each node.
const link = svg.append('g')
.attr('stroke', '#999')
.attr('stroke-opacity', 0.6)
.attr('stroke-width', 1)
.selectAll()
.data(links)
.join('line')

const colourMap = new Map([
  ['commodity', 1],
  ['certificate', 2],
  ['commodity_code', 3],
  ['species', 4]
])

// Root node for each point
const node = svg.append('g')
.selectAll()
.data(nodes)
.join('g')

// Draw circle
node.append('circle')
.attr('stroke', '#fff')
.attr('stroke-width', 1.5)
.attr('r', 5)
.attr('fill', d => color(colourMap.get(d.type)))

// Add a tooltip on hover
node.append('title')
.text(d => d.id)

// Add text
node.append('text')
.attr('x', 8)
.attr('y', '0.25em')
.text(d => d.name)
.clone(true).lower()
.attr('fill', 'none')
.attr('stroke', 'white')
.attr('stroke-width', 3)

// Add a drag behavior.
node.call(d3.drag()
.on('start', dragstarted)
.on('drag', dragged)
.on('end', dragended))

// Set the position attributes of links and nodes each time the simulation ticks.
function ticked () {
  link
    .attr('x1', d => d.source.x)
    .attr('y1', d => d.source.y)
    .attr('x2', d => d.target.x)
    .attr('y2', d => d.target.y)

  node
    .attr('transform', d => `translate(${d.x},${d.y})`)
}

// Reheat the simulation when drag starts, and fix the subject position.
function dragstarted (event) {
  if (!event.active) {
    simulation.alphaTarget(0.3).restart()
  }
  event.subject.fx = event.subject.x
  event.subject.fy = event.subject.y
}

// Update the subject (dragged node) position during drag.
function dragged (event) {
  event.subject.fx = event.x
  event.subject.fy = event.y
}

// Restore the target alpha so the simulation cools after dragging ends.
// Unfix the subject position now that it’s no longer being dragged.
function dragended (event) {
  if (!event.active) {
    simulation.alphaTarget(0)
  }
  event.subject.fx = null
  event.subject.fy = null
}

// Append the SVG element.
container.append(svg.node())
