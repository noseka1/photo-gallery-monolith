#!/bin/bash

# This script rebuilds the monolithic application including
# all its components.
#
# It is assumed that you cloned the component git repos so
# that they are located under the same parent directory.

set -e

# absolute path to this script
SCRIPT=$(readlink -f $0)

# absolute path of the directory this script is in
SCRIPT_PATH=$(dirname "$SCRIPT")

cd "$SCRIPT_PATH"

COMPONENTS="
photo-gallery-common
photo-gallery-photo
photo-gallery-like
photo-gallery-query
"

for COMP in $COMPONENTS; do
  pushd ../$COMP
  mvn clean install
  popd
done

mvn clean package
