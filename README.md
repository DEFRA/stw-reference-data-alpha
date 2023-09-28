# Reference Data Orchestration

This project holds the Reference Data Orchestration.

## Database

The `database` directory contains a MSSQL Docker container and scripts to create tables and load
data.

Download
the [2023 GBIF backbone](https://hosted-datasets.gbif.org/datasets/backbone/2023-08-28/backbone.zip)
and copy the `Taxon.tsv` and `VernacularName.tsv` files into `database/gbif`.

Start the database:

```shell
./run.sh
```

## Functions

The `functions` directory contains Azure Functions for the orchestration service.

### Setup

A `local.settings.json` must be created in the root to run the functions locally:

```json
{
  "IsEncrypted": false,
  "Values": {
    "AzureWebJobsStorage": "",
    "FUNCTIONS_WORKER_RUNTIME": "java",
    "JAVA_HOME": "<Path to Java 17>",
    "MAIN_CLASS": "org.defra.orchestration.ReferenceDataOrchestrationApplication",
    "JAVA_TOOL_OPTIONS": "--add-opens=java.base/java.time=ALL-UNNAMED",
    "MDM_BASE_URL": "http://localhost:9001",
    "IPAFFS_BASE_URL": "http://localhost:8080"
  }
}
```

### Run via CLI

```shell
mvn package azure-functions:run
```

### Run via IntelliJ

Add new configuration "Azure Functions > Run Functions" and fill required fields. Make sure the
"App Settings" mirror what is in `local.settings.json`.

### Deploy

```shell
mvn package azure-functions:deploy
```

## MDM

The `mdm` directory contains a Spring Boot application which serves requests to the database.

### Run via CLI

```shell
mvn spring-boot:run
```

### Run via IntelliJ

Open `MdmApplication` and run the class.

### Deploy

```shell
mvn package azure-spring-apps:deploy
```

## Viewer

The `viewer` directory contains a browser application to view the structure returned by the stub
MDM.

The files must be served by a web server, e.g. from the `viewer` directory run:

```shell
npx serve -p 9002
```

## Demo

1. Deploy reference data loader into a pool
2. Single component deploy frontend notification into that pool
3. Set `REFERENCE_DATA_LOADER_SERVICE_RDS_API_URL=https://ref-data-orchestration-function-app.azurewebsites.net/api` on the reference data loader configuration
4. Set `IPAFFS_BASE_URL` to the reference data URL of the deployed pool in the reference data orchestration function configuration e.g. `https://referencedataloader-microservice-13.azurewebsites.net`
5. Truncate the tables to be synced - this needs to be done with DELETE statement not TRUNCATE and can be done with our AD logins
6. Trigger the sync through the notify URL on the function
