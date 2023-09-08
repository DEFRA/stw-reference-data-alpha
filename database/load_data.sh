#!/usr/bin/env bash

echo "Waiting for database to be ready"
while ! sqlcmd -S "tcp:127.0.0.1,1434" -U SA -P Password1 -Q "SELECT @@VERSION" &>/dev/null; do
  echo "Database not ready, sleeping for 10 seconds..."
  sleep 10
done
echo "Database is ready"

echo 'Creating database...'
sqlcmd -S "tcp:127.0.0.1,1434" -U SA -P Password1 -Q "CREATE DATABASE reference_data"
sqlcmd -S "tcp:127.0.0.1,1434" -U SA -P Password1 -Q "EXEC sp_defaultdb @loginame='SA', @defdb='reference_data'"

echo 'Creating tables...'
sqlcmd -S "tcp:127.0.0.1,1434" -U SA -P Password1 -i create_tables.sql
sqlcmd -S "tcp:127.0.0.1,1434" -U SA -P Password1 -i eppo/sqlschema.sql

echo 'Loading DIT commodity data...'
docker cp dit/dit_commodities_output.csv ref-data-db:/tmp
sqlcmd -S "tcp:127.0.0.1,1434" -U SA -P Password1 -Q "BULK INSERT commodity_code FROM '/tmp/dit_commodities_output.csv' WITH (FORMAT = 'CSV')"

echo 'Loading GBIF taxon data...'
bcp taxon IN gbif/Taxon.tsv -f gbif/taxon.fmt -S "tcp:127.0.0.1,1434" -U SA -P Password1 -F 2
echo 'Loading GBIF vernacular_name data...'
bcp vernacular_name IN gbif/VernacularName.tsv -f gbif/vernacular_name.fmt -S "tcp:127.0.0.1,1434" -U SA -P Password1 -F 2
echo 'Copying GBIF to species table...'
sqlcmd -S "tcp:127.0.0.1,1434" -U SA -P Password1 -i gbif/copy_to_species.sql

echo 'Loading EPPO authorities data...'
sqlcmd -S "tcp:127.0.0.1,1434" -U SA -P Password1 -i eppo/authorities.sql
echo 'Loading EPPO codes data...'
sqlcmd -S "tcp:127.0.0.1,1434" -U SA -P Password1 -i eppo/codes.sql
echo 'Loading EPPO countries data...'
sqlcmd -S "tcp:127.0.0.1,1434" -U SA -P Password1 -i eppo/countries.sql
echo 'Loading EPPO datatypes data...'
sqlcmd -S "tcp:127.0.0.1,1434" -U SA -P Password1 -i eppo/datatypes.sql
echo 'Loading EPPO langs data...'
sqlcmd -S "tcp:127.0.0.1,1434" -U SA -P Password1 -i eppo/langs.sql
echo 'Loading EPPO links data...'
docker cp eppo/links.csv ref-data-db:/tmp
sqlcmd -S "tcp:127.0.0.1,1434" -U SA -P Password1 -Q "BULK INSERT t_links FROM '/tmp/links.csv' WITH (FORMAT = 'CSV')"
echo 'Loading EPPO names data...'
docker cp eppo/names.csv ref-data-db:/tmp
docker cp eppo/names.fmt ref-data-db:/tmp
sqlcmd -S "tcp:127.0.0.1,1434" -U SA -P Password1 -Q "BULK INSERT t_names FROM '/tmp/names.csv' WITH (FORMAT = 'CSV', FORMATFILE = '/tmp/names.fmt')"
echo 'Copying EPPO to species table...'
sqlcmd -S "tcp:127.0.0.1,1434" -U SA -P Password1 -i eppo/copy_to_species.sql

echo 'Loading certificate data...'
sqlcmd -S "tcp:127.0.0.1,1434" -U SA -P Password1 -i certificate.sql

echo 'Loading species data...'
sqlcmd -S "tcp:127.0.0.1,1434" -U SA -P Password1 -i species.sql

echo 'Loading commodity data...'
sqlcmd -S "tcp:127.0.0.1,1434" -U SA -P Password1 -i commodity.sql
