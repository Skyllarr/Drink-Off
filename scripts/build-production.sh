#!/usr/bin/env bash

set -eu

SCRIPTS_DIR="$(dirname "$(readlink -f "$0")")"
DRINK_OFF_DIR="`realpath "$SCRIPTS_DIR/"..`"
WEB_MAIN_DIR="`realpath "$DRINK_OFF_DIR/web/src/main"`"
FRONTEND_DIR="$WEB_MAIN_DIR/frontend"
WEB_RESOURCES_DIR="$WEB_MAIN_DIR/resources"

"$SCRIPTS_DIR/clean.sh"

yarn --cwd "$FRONTEND_DIR" install
yarn build-css

GENERATE_SOURCEMAP=false yarn --cwd "$FRONTEND_DIR" build # yarn run build
cp -R "$FRONTEND_DIR/build/". "$WEB_MAIN_DIR/webapp"

cp "$WEB_RESOURCES_DIR"/import.prod.sql "$WEB_RESOURCES_DIR"/import.sql
mvn -f "$DRINK_OFF_DIR/pom.xml" install
