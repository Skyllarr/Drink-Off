#!/usr/bin/env bash

set -eu

SCRIPTS_DIR="$(dirname "$(readlink -f "$0")")"

mvn -f "`realpath "$SCRIPTS_DIR/../web/pom.xml"`" tomcat7:run
