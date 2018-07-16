#!/usr/bin/env bash

set -eu

SCRIPTS_DIR="$(dirname "$(readlink -f "$0")")"
WEB_DIR="`realpath "$SCRIPTS_DIR/"../../../`"

cd "$WEB_DIR"
mvn tomcat7:run
