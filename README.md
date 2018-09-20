# Drink Off

Web application for company registration.

## Prerequisites

Install [mvn](https://maven.apache.org/) and [yarn](https://yarnpkg.com/en/)

## User Guide

```bash
./build-prod.sh
./run.sh
```

Visit the app at [localhost:8080](http://localhost:8080) 

## Developer Guide

Build and run the main server

```bash
./build-dev.sh
./run.sh
```

Run devel webpack server which forwards API requests to the main server

```bash
cd web/src/main/frontend 
yarn install
yarn build-css # run again when changing scss/less files only
yarn start
```
