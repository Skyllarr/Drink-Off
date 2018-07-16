#!/usr/bin/env bash

set -eu

SCRIPTS_DIR="$(dirname "$(readlink -f "$0")")"

MAIN_DIR="`realpath "$SCRIPTS_DIR/"..`"
DRINK_OFF_DIR="`realpath "$MAIN_DIR/"../../..`"
WEBAPP_DIR="$MAIN_DIR/webapp"

rm -rf "$WEBAPP_DIR"
