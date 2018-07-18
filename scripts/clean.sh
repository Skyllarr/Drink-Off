#!/usr/bin/env bash

set -eu

SCRIPTS_DIR="$(dirname "$(readlink -f "$0")")"
WEB_MAIN_DIR="`realpath "$SCRIPTS_DIR/../web/src/main"`"

rm -rf "$WEB_MAIN_DIR/webapp"
rm -f "$WEB_MAIN_DIR/resources/import.sql"
