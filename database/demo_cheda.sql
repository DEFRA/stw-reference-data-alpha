-- Do the initial sync

-- Insert a new commodity
INSERT INTO reference_data.dbo.commodity (certificate, commodity_code, species, commodity_type, effective_from, effective_to) VALUES (640, 94048, 947057, 1, N'2023-09-18 10:12:53.000', null);

-- Do a sync
-- See that it can be entered using the frontend

-- Update the species on Heifers
UPDATE commodity SET effective_to = '2023-09-18 10:30:40.000' WHERE commodity_code = 94048;
INSERT INTO reference_data.dbo.commodity (certificate, commodity_code, species, commodity_type, effective_from, effective_to) VALUES (640, 94048, 947058, 1, N'2023-09-18 11:52:08.000', null);

-- Do a sync
-- See that it can be entered using the frontend

-- Remove the new commodity
UPDATE commodity SET effective_to = '2023-09-18 10:30:40.000' WHERE commodity_code = 94048;

-- Do a sync
-- See that it is no longer selectable from the tree

-- Updating commodity description
-- Expire old row
UPDATE commodity_code SET effective_to = '2023-09-18 16:52:27.000' WHERE id = 94048;
-- Make new row in the commodity_code table
INSERT INTO reference_data.dbo.commodity_code (id, code, suffix, description, parent_id, effective_from, effective_to, source_name, source_id) VALUES (9994048, N'0102211000', N'80', N'new heifers description', 94047, N'2023-09-18 16:52:27.000', null, N'CUSTOM', N'1');
-- Expire old commodity row
UPDATE commodity SET effective_to = '2023-09-18 16:52:27.000' WHERE commodity_code = 94048;
-- Add new commodity
INSERT INTO reference_data.dbo.commodity (certificate, commodity_code, species, commodity_type, effective_from, effective_to) VALUES (640, 9994048, 947057, 1, N'2023-09-18 16:55:31.000', null);

-- Do a sync
-- See that new commodity description has changed.