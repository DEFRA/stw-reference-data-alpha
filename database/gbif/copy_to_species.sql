INSERT INTO species
SELECT NULL           as eppo,
       NULL           as simple_name,
       taxon_rank     as rank,
       class,
       family,
       genus,
       canonical_name as species,
       'GBIF'            source_name,
       taxon_id       as source_id
FROM taxon
WHERE genus IN ('Bison', 'Bos')
  AND taxon.canonical_name IS NOT NULL
