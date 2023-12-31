-- Prior to doing any testing(cheda or chedpp) make sure you truncate the following tables for RDS
-- DELETE FROM dbo.certificates;
-- DELETE FROM dbo.certification_nomenclature;
-- DELETE FROM dbo.certification_requirement;
-- DELETE FROM dbo.commodity_class;
-- DELETE FROM dbo.commodity_eppo_variety;
-- DELETE FROM dbo.commodity_group;
-- DELETE FROM dbo.commodity_group_commodity;
-- DELETE FROM dbo.commodity_nomenclature;
-- DELETE FROM dbo.hmi_marketing;
-- DELETE FROM dbo.inspection_responsibility;
-- DELETE FROM dbo.species;

-------------------------------------------------------------------------------------------------------------------------------------

-- Insert a new commodity = CHEDA, Heifers, Bos taurus
INSERT INTO reference_data.dbo.commodity (certificate, commodity_code, species, commodity_type, effective_from, effective_to) VALUES
                                          (640, 94048, 947057, 1, N'2023-09-18 10:12:53.000', null);
-- Do a sync
-- In the frontend...
-- Check that Heifers (female bovines that have never calved) is visible and select it (01022110)
-- Check that species is Bos taurus
-------------------------------------------------------------------------------------------------------------------------------------

-- Update the species on Heifers to Bos indicus
UPDATE commodity SET effective_to = '2023-09-18 10:30:40.000' WHERE commodity_code = 94048;
INSERT INTO reference_data.dbo.commodity (certificate, commodity_code, species, commodity_type, effective_from, effective_to) VALUES
                                          (640, 94048, 947058, 1, N'2023-09-18 11:52:08.000', null);
-- Do a sync
-- In the frontend
-- Check that species is Bos indicus
-------------------------------------------------------------------------------------------------------------------------------------

-- Remove the Heifers commodity
UPDATE commodity SET effective_to = '2023-09-18 10:30:40.000' WHERE commodity_code = 94048;
-- Do a sync
-- In the frontend...
-- See that Heifers is no longer visible in the tree
-------------------------------------------------------------------------------------------------------------------------------------

-- Updating commodity description for Heifers
-- Expire the old rows
UPDATE commodity_code SET effective_to = '2023-09-18 16:52:27.000' WHERE id = 94048;
UPDATE commodity SET effective_to = '2023-09-18 16:52:27.000' WHERE commodity_code = 94048;
-- Make new row in the commodity_code table with the new description
INSERT INTO reference_data.dbo.commodity_code (id, code, suffix, description, parent_id, effective_from, effective_to, source_name, source_id) VALUES
                                               (9994048, N'0102211000', N'80', N'new heifers description', 94047, N'2023-09-18 16:52:27.000', null, N'CUSTOM', N'1');
-- Add new commodity
INSERT INTO reference_data.dbo.commodity (certificate, commodity_code, species, commodity_type, effective_from, effective_to) VALUES
                                          (640, 9994048, 947057, 1, N'2023-09-18 16:55:31.000', null);
-- Do a sync
-- In the frontend
-- Check that `new heifers description`
-- Check that species is Bos taurus
