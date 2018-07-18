#!/usr/bin/env bash

set -eu

SCRIPTS_DIR="$(dirname "$(readlink -f "$0")")"
DRINK_OFF_DIR="`realpath "$SCRIPTS_DIR/"..`"
WEB_MAIN_DIR="`realpath "$DRINK_OFF_DIR/web/src/main"`"
WEB_RESOURCES_DIR="$WEB_MAIN_DIR/resources"

"$SCRIPTS_DIR/clean.sh"

cp "$WEB_RESOURCES_DIR"/import.dev.sql "$WEB_RESOURCES_DIR"/import.sql

mvn -f "$DRINK_OFF_DIR/pom.xml" install
