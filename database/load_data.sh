#!/usr/bin/env bash

echo 'Creating tables...'
sqlcmd -S "tcp:127.0.0.1,1433" -U SA -P Password1 -i create_tables.sql
sqlcmd -S "tcp:127.0.0.1,1433" -U SA -P Password1 -i eppo/sqlschema.sql

echo 'Loading DIT commodity data...'
docker cp dit/dit_commodities_output.csv mssql:/tmp
sqlcmd -S "tcp:127.0.0.1,1433" -U SA -P Password1 -Q "BULK INSERT reference_data.dbo.commodity_code FROM '/tmp/dit_commodities_output.csv' WITH (FORMAT = 'CSV');"

echo 'Loading GBIF taxon data...'
bcp reference_data.dbo.taxon IN gbif/Taxon.tsv -f gbif/taxon.fmt -S "tcp:127.0.0.1,1433" -U SA -P Password1 -F 2
echo 'Loading GBIF vernacular_name data...'
bcp reference_data.dbo.vernacular_name IN gbif/VernacularName.tsv -f gbif/vernacular_name.fmt -S "tcp:127.0.0.1,1433" -U SA -P Password1 -F 2
echo 'Copying GBIF to species table...'
sqlcmd -S "tcp:127.0.0.1,1433" -U SA -P Password1 -i gbif/copy_to_species.sql

echo 'Loading EPPO authorities data...'
sqlcmd -S "tcp:127.0.0.1,1433" -U SA -P Password1 -i eppo/authorities.sql
echo 'Loading EPPO codes data...'
sqlcmd -S "tcp:127.0.0.1,1433" -U SA -P Password1 -i eppo/codes.sql
echo 'Loading EPPO countries data...'
sqlcmd -S "tcp:127.0.0.1,1433" -U SA -P Password1 -i eppo/countries.sql
echo 'Loading EPPO datatypes data...'
sqlcmd -S "tcp:127.0.0.1,1433" -U SA -P Password1 -i eppo/datatypes.sql
echo 'Loading EPPO langs data...'
sqlcmd -S "tcp:127.0.0.1,1433" -U SA -P Password1 -i eppo/langs.sql
echo 'Loading EPPO links data...'
docker cp eppo/links.csv mssql:/tmp
sqlcmd -S "tcp:127.0.0.1,1433" -U SA -P Password1 -i "BULK INSERT reference_data.dbo.t_links FROM '/tmp/links.csv' WITH (FORMAT = 'CSV');"
echo 'Loading EPPO names data...'
docker cp eppo/names.csv mssql:/tmp
docker cp eppo/names.fmt mssql:/tmp
sqlcmd -S "tcp:127.0.0.1,1433" -U SA -P Password1 -i "BULK INSERT reference_data.dbo.t_names FROM '/tmp/names.csv' WITH (FORMAT = 'CSV', FORMATFILE = '/tmp/names.fmt');"
echo 'Copying EPPO to species table...'
sqlcmd -S "tcp:127.0.0.1,1433" -U SA -P Password1 -i eppo/copy_to_species.sql
