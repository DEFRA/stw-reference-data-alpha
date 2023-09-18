import fs from 'fs'
import { parse, stringify } from 'csv/sync'

function replaceNa (value) {
  return value === '#NA' ? null : value;
}

const input = fs.readFileSync('./dit_commodities.csv')
let records = parse(input, { bom: true, columns: true })
records = records.map(record => ({
  id: replaceNa(record.commodity__sid),
  code: replaceNa(record.commodity__code),
  suffix: replaceNa(record.commodity__suffix),
  description: replaceNa(record.commodity__description),
  parent_id: replaceNa(record.parent__sid),
  effective_from: replaceNa(record.commodity__validity_start),
  effective_to: replaceNa(record.commodity__validity_end),
  source_name: 'DIT',
  source_id: replaceNa(record.commodity__sid)
}));
records = records.map(record => ({
  ...record,
  parent_id: records.find(r => r.id === record.parent_id)?.id
}));
fs.writeFileSync('./dit_commodities_output.csv', stringify(records, { header: false, quoted: true }));
