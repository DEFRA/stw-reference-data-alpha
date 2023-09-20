# Reference Data Orchestration

This project holds the Reference Data Orchestration.

## Database

The `database` directory contains a MSSQL Docker container and scripts to create tables and load
data.

Start the container:

```shell
./run.sh
```

Download
the [2023 GBIF backbone](https://hosted-datasets.gbif.org/datasets/backbone/2023-08-28/backbone.zip)
and copy the `Taxon.tsv` and `VernacularName.tsv` files into `database/gbif`.

Create the tables and load data:

```shell
./load_data.sh
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

## Requests

The `requests` directory contains `.http` files which can be used to send requests via the IntelliJ
[HTTP Client plugin](https://www.jetbrains.com/help/idea/http-client-in-product-code-editor.html).
