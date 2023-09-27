#!/usr/bin/env bash

S=${SQL_SERVER:-'tcp:127.0.0.1,1434'}
U=${SQL_USERNAME:-'SA'}
P=${SQL_PASSWORD:-'Password1'}

echo 'Creating tables...'
sqlcmd -S $S -U $U -P $P -d reference_data -i create_tables.sql
sqlcmd -S $S -U $U -P $P -d reference_data -i eppo/sqlschema.sql

echo 'Loading DIT commodity data...'
bcp commodity_code IN dit/dit_commodities_output.csv -f dit/commodity_code.fmt -S $S -U $U -P $P -d reference_data

echo 'Loading GBIF taxon data...'
bcp taxon IN gbif/Taxon.tsv -f gbif/taxon.fmt -S $S -U $U -P $P -d reference_data -F 2
echo 'Loading GBIF vernacular_name data...'
bcp vernacular_name IN gbif/VernacularName.tsv -f gbif/vernacular_name.fmt -S $S -U $U -P $P -d reference_data -F 2
echo 'Copying GBIF to species table...'
sqlcmd -S $S -U $U -P $P -d reference_data -i gbif/copy_to_species.sql

echo 'Loading EPPO authorities data...'
sqlcmd -S $S -U $U -P $P -d reference_data -i eppo/authorities.sql
echo 'Loading EPPO codes data...'
sqlcmd -S $S -U $U -P $P -d reference_data -i eppo/codes.sql
echo 'Loading EPPO countries data...'
sqlcmd -S $S -U $U -P $P -d reference_data -i eppo/countries.sql
echo 'Loading EPPO datatypes data...'
sqlcmd -S $S -U $U -P $P -d reference_data -i eppo/datatypes.sql
echo 'Loading EPPO langs data...'
sqlcmd -S $S -U $U -P $P -d reference_data -i eppo/langs.sql
echo 'Loading EPPO links data...'
bcp t_links IN eppo/links.csv -f eppo/links.fmt -S $S -U $U -P $P -d reference_data
echo 'Loading EPPO names data...'
bcp t_names IN eppo/names.csv -f eppo/names.fmt -S $S -U $U -P $P -d reference_data
echo 'Copying EPPO to species table...'
sqlcmd -S $S -U $U -P $P -d reference_data -i eppo/copy_to_species.sql

echo 'Loading certificate data...'
sqlcmd -S $S -U $U -P $P -d reference_data -i certificate.sql

echo 'Loading species data...'
sqlcmd -S $S -U $U -P $P -d reference_data -i species.sql

echo 'Loading commodity type data...'
sqlcmd -S $S -U $U -P $P -d reference_data -i commodity_type.sql

echo 'Loading commodity data...'
sqlcmd -S $S -U $U -P $P -d reference_data -i commodity.sql

echo 'Loading class data...'
sqlcmd -S $S -U $U -P $P -d reference_data -i class.sql

echo 'Loading variety data...'
sqlcmd -S $S -U $U -P $P -d reference_data -i variety.sql

echo 'Loading inspection responsibility data...'
sqlcmd -S $S -U $U -P $P -d reference_data -i inspection_responsibility.sql

echo 'Loading hmi marketing data...'
sqlcmd -S $S -U $U -P $P -d reference_data -i hmi_marketing.sql

echo 'Loading commodity group data...'
sqlcmd -S $S -U $U -P $P -d reference_data -i commodity_group.sql

echo 'Creating view...'
sqlcmd -S $S -U $U -P $P -d reference_data -i create_view.sql
