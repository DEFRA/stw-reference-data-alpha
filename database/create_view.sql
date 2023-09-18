CREATE VIEW v_combined AS
SELECT c.id,
       c.certificate    AS certId,
       cert.name        AS cert,
       c.commodity_code AS codeId,
       cc.code,
       cc.description,
       c.species        AS speciesId,
       s.species,
       c.commodity_type AS typeId,
       ct.name          AS commodityType,
       c.effective_from AS effectiveFrom,
       c.effective_to   AS effectiveTo
FROM commodity c
LEFT JOIN certificate cert ON c.certificate = cert.id
LEFT JOIN commodity_code cc ON c.commodity_code = cc.id
LEFT JOIN species s ON c.species = s.id
LEFT JOIN commodity_type ct ON c.commodity_type = ct.id;
