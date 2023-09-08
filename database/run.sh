#!/usr/bin/env bash

docker run -e "ACCEPT_EULA=Y" -e "MSSQL_SA_PASSWORD=Password1" -p 1434:1433 -d --name ref-data-db mcr.microsoft.com/mssql/server:2022-latest
