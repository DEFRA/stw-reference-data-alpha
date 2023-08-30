# Reference Data Orchestration

This project holds the Azure Functions for Reference Data Orchestration.

A `local.settings.json` must be created in the root to run the functions locally:

```json
{
  "IsEncrypted": false,
  "Values": {
    "AzureWebJobsStorage": "",
    "FUNCTIONS_WORKER_RUNTIME": "java",
    "JAVA_HOME": "<Path to Java 17>"
  }
}
```

## Run via CLI

```shell
mvn package azure-functions:run
```

## Run via IntelliJ

Add new configuration "Azure Functions > Run Functions" and fill required fields. Make sure the 
"App Settings" mirror what is in `local.settings.json`.

## Deploy

```shell
mvn package azure-functions:deploy
```
