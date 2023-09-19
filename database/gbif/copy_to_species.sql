INSERT INTO species
SELECT NULL           as eppo,
       NULL           as simple_name,
       taxon_rank     as rank,
       kingdom,
       class,
       family,
       genus,
       canonical_name as species,
       'GBIF'            source_name,
       taxon_id       as source_id
FROM taxon
WHERE kingdom = 'Animalia'
  AND canonical_name IS NOT NULL
  AND taxon_rank = 'species'
ORDER BY taxon_id
