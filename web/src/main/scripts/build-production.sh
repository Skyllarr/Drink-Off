#!/usr/bin/env bash

set -eu

SCRIPTS_DIR="$(dirname "$(readlink -f "$0")")"

MAIN_DIR="`realpath "$SCRIPTS_DIR/"..`"
DRINK_OFF_DIR="`realpath "$MAIN_DIR/"../../..`"

FRONTEND_DIR="$MAIN_DIR/frontend"
WEBAPP_DIR="$MAIN_DIR/webapp"

cd "$FRONTEND_DIR"

yarn install

GENERATE_SOURCEMAP=false yarn build # yarn run build

rm -rf "$WEBAPP_DIR"
cp -R "$FRONTEND_DIR/build/". "$WEBAPP_DIR"

cd "$DRINK_OFF_DIR"
mvn install
