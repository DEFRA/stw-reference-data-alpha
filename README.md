# Reference Data Orchestration

This project holds the Reference Data Orchestration.

## Azure Functions

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

## Database

The `database` directory contains a MSSQL Docker container and scripts to create tables and load
data.

Start the container:

```shell
./run.sh
```

Download
the [GBIF backbone](https://hosted-datasets.gbif.org/datasets/backbone/current/backbone.zip) and
copy the `Taxon.tsv` and `VernacularName.tsv` files into `database/gbif`.

Create the tables and load data:

```shell
./load_data.sh
```
