WITH hierachy AS (
  -- Anchor
  SELECT c.codeid,
         c.eppocode,
         c.dtcode,
         c.status,
         c.c_date,
         c.m_date,
         l.codeid_parent,
         CAST('kingdom' AS nvarchar(50)) AS rank,
         c.codeid AS kingdom,
         NULL AS class,
         NULL AS family,
         NULL AS genus,
         NULL AS species
  FROM t_codes c
       LEFT JOIN t_links l on c.codeid = l.codeid
  WHERE c.status = 'A'
    AND l.codeid IS NULL
    AND LEFT(c.eppocode, 1) = '1'
    AND RIGHT(c.eppocode, 1) = 'K'

  UNION ALL

  -- Recursive
  SELECT c.codeid,
         c.eppocode,
         c.dtcode,
         c.status,
         c.c_date,
         c.m_date,
         l.codeid_parent,
         CAST(IIF(LEFT(c.eppocode, 1) = '1', (
           CASE RIGHT(c.eppocode, 1)
             WHEN 'G' THEN 'genus'
             WHEN 'S' THEN 'subfamily'
             WHEN 'F' THEN 'family'
             WHEN 'O' THEN 'order'
             WHEN 'D' THEN 'category'
             WHEN 'C' THEN 'class'
             WHEN 'Q' THEN 'subphylum'
             WHEN 'P' THEN 'phylum'
             WHEN 'K' THEN 'kingdom'
             END
           ), 'species') AS nvarchar(50)) AS rank,
         h.kingdom as kingdom,
         IIF(LEFT(c.eppocode, 1) = '1' AND RIGHT(c.eppocode, 1) = 'C', c.codeid, h.class) as class,
         IIF(LEFT(c.eppocode, 1) = '1' AND RIGHT(c.eppocode, 1) = 'F', c.codeid, h.family) as family,
         IIF(LEFT(c.eppocode, 1) = '1' AND RIGHT(c.eppocode, 1) = 'G', c.codeid, h.genus) as genus,
         IIF(LEFT(c.eppocode, 1) != '1', c.codeid, h.species) as species
  FROM hierachy h
       JOIN t_links l on h.codeid = l.codeid_parent
       JOIN t_codes c on l.codeid = c.codeid),
     simple_name AS (
       SELECT *
       FROM t_names
       WHERE codelang = 'en'
         AND nameid IN (SELECT MAX(nameid) FROM t_names GROUP BY codelang, codeid)
     )
INSERT INTO species
SELECT h.eppocode as eppo,
       simple_name.fullname as simple_name,
       h.rank as rank,
       kingdom_name.fullname as kingdom,
       class_name.fullname as class,
       family_name.fullname as family,
       genus_name.fullname as genus,
       species_name.fullname as species,
       'EPPO'            source_name,
       h.codeid       as source_id
FROM hierachy h
     LEFT JOIN t_names kingdom_name on h.kingdom = kingdom_name.codeid AND kingdom_name.codelang = 'la' AND kingdom_name.preferred = 1
     LEFT JOIN t_names class_name on h.class = class_name.codeid AND class_name.codelang = 'la' AND class_name.preferred = 1
     LEFT JOIN t_names family_name on h.family = family_name.codeid AND family_name.codelang = 'la' AND family_name.preferred = 1
     LEFT JOIN t_names genus_name on h.genus = genus_name.codeid AND genus_name.codelang = 'la' AND genus_name.preferred = 1
     LEFT JOIN t_names species_name on h.species = species_name.codeid AND species_name.codelang = 'la' AND species_name.preferred = 1
     LEFT JOIN simple_name on h.species = simple_name.codeid
WHERE h.dtcode = 'PFL'
ORDER BY h.codeid;
