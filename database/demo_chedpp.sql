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

-- Insert a new commodity = Quinces
INSERT INTO reference_data.dbo.commodity (certificate, commodity_code, species, commodity_type, effective_from, effective_to) VALUES
                                          (851, 94496, 3067810, null, N'2023-09-18 10:12:53.000', null);
-- Do a sync
-- In the frontend...
-- Check that Quinces
-- Check that species is Malus domestica
-------------------------------------------------------------------------------------------------------------------------------------

-- Update the species on Quinces
UPDATE commodity SET effective_to = '2023-09-18 10:30:40.000' WHERE commodity_code = 94496;
INSERT INTO reference_data.dbo.commodity (certificate, commodity_code, species, commodity_type, effective_from, effective_to) VALUES
                                          (851, 94496, 3067785, 1, N'2023-09-18 11:52:08.000', null);
-- Do a sync
-- In the frontend...
-- Check that species is Malus florentina
-------------------------------------------------------------------------------------------------------------------------------------

-- Remove the new commodity
UPDATE commodity SET effective_to = '2023-09-18 10:30:40.000' WHERE commodity_code = 94496;
-- Do a sync
-- In the frontend...
-- See that Quinces is no longer visible in the tree
-------------------------------------------------------------------------------------------------------------------------------------

-- Updating commodity description
-- Expire the old rows
UPDATE commodity_code SET effective_to = '2023-09-18 16:52:27.000' WHERE id = 94496;
UPDATE commodity SET effective_to = '2023-09-18 16:52:27.000' WHERE commodity_code = 94496;
-- Make new row in the commodity_code table
INSERT INTO reference_data.dbo.commodity_code (id, code, suffix, description, parent_id, effective_from, effective_to, source_name, source_id) VALUES
                                                (9994496, N'0808400000', N'80', N'new quinces description', 31254, N'2023-09-18 16:52:27.000', null, N'CUSTOM', N'1');
-- Add new commodity
INSERT INTO reference_data.dbo.commodity (certificate, commodity_code, species, commodity_type, effective_from, effective_to) VALUES
                                          (851, 9994496, 3067810, 1, N'2023-09-18 16:55:31.000', null);
-- Do a sync
-- In the frontend
-- Check that `new quinces description`
-- Check that species is Malus domestica