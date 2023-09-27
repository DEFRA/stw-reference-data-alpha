#!/usr/bin/env bash

docker run -e "ACCEPT_EULA=Y" -e "MSSQL_SA_PASSWORD=Password1" -p 1434:1433 -d --name ref-data-db mcr.microsoft.com/mssql/server:2022-latest

echo "Waiting for server to be ready"
while ! sqlcmd -S 'tcp:127.0.0.1,1434' -U 'SA' -P 'Password1' -Q "SELECT @@VERSION" &>/dev/null; do
  echo "Server not ready, sleeping for 10 seconds..."
  sleep 10
done
echo "Server is ready"

echo 'Creating database...'
sqlcmd -S 'tcp:127.0.0.1,1434' -U 'SA' -P 'Password1' -Q "CREATE DATABASE reference_data"
sqlcmd -S 'tcp:127.0.0.1,1434' -U 'SA' -P 'Password1' -Q "EXEC sp_defaultdb @loginame='SA', @defdb='reference_data'"

./load_data.sh
