-- Adding new commodities
INSERT INTO reference_data.dbo.commodity (certificate, commodity_code, species, commodity_type, effective_from, effective_to) VALUES (640, 94051, 947137, 1, N'2023-01-01 00:00:00.000', null);
INSERT INTO reference_data.dbo.commodity (certificate, commodity_code, species, commodity_type, effective_from, effective_to) VALUES (640, 94051, 1763388, 2, N'2023-01-01 00:00:00.000', null);
INSERT INTO reference_data.dbo.commodity (certificate, commodity_code, species, commodity_type, effective_from, effective_to) VALUES (640, 94049, 947057, 1, N'2023-01-01 00:00:00.000', null);
INSERT INTO reference_data.dbo.commodity (certificate, commodity_code, species, commodity_type, effective_from, effective_to) VALUES (853, 27811, 947137, 1, N'2023-01-01 00:00:00.000', null);
INSERT INTO reference_data.dbo.commodity (certificate, commodity_code, species, commodity_type, effective_from, effective_to) VALUES (853, 27811, 947057, 1, N'2023-01-01 00:00:00.000', null);
INSERT INTO reference_data.dbo.commodity (certificate, commodity_code, species, commodity_type, effective_from, effective_to) VALUES (851, 81147, 3067773, null, N'2023-01-01 00:00:00.000', null);
INSERT INTO reference_data.dbo.commodity (certificate, commodity_code, species, commodity_type, effective_from, effective_to) VALUES (851, 81147, 3067810, null, N'2023-01-01 00:00:00.000', null);
INSERT INTO reference_data.dbo.commodity (certificate, commodity_code, species, commodity_type, effective_from, effective_to) VALUES (851, 81147, 3067785, null, N'2023-01-01 00:00:00.000', null);
INSERT INTO reference_data.dbo.commodity (certificate, commodity_code, species, commodity_type, effective_from, effective_to) VALUES (851, 99427, 3067773, null, N'2023-01-01 00:00:00.000', null);
INSERT INTO reference_data.dbo.commodity (certificate, commodity_code, species, commodity_type, effective_from, effective_to) VALUES (851, 99427, 3067810, null, N'2023-01-01 00:00:00.000', null);
INSERT INTO reference_data.dbo.commodity (certificate, commodity_code, species, commodity_type, effective_from, effective_to) VALUES (851, 30242, 3075868, null, N'2023-01-01 00:00:00.000', null);
INSERT INTO reference_data.dbo.commodity (certificate, commodity_code, species, commodity_type, effective_from, effective_to) VALUES (678, 30242, 3075868, null, N'2023-01-01 00:00:00.000', null);

-- Updating a commodity name - Set the effectiveTo date in the past and create a new commodity and species
-- Working for Heifers (female bovines that have never calved)!
-- update v_combined set effective_to = N'2023-06-01 00:00:00.000' where id = 3;
-- INSERT INTO reference_data.dbo.commodity (certificate, commodity_code, species, commodity_type, effective_from, effective_to) VALUES (640, 94048, 947059, 1, N'2023-01-01 00:00:00.000', null);

-- Removing a commodity - Set the effectiveTo date in the past.
-- Its NOT working properly!
-- RDS rows seem to be being removed from the appropriate tables but you can see the commodity in the picker and select it but you cant move onto the next page!
-- update v_combined set effective_to = N'2023-06-01 00:00:00.000' where id = 1;
-- Set it back to retest it
-- update v_combined set effective_to = null where id = 1;
