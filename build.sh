#!/bin/bash

# Define the directories
NPM_DIR="./frontend/"
MVN_DIR="."

# Run npm build
echo "Running npm build in ${NPM_DIR}..."
npm --prefix "$NPM_DIR" run build

# Check if npm build was successful
if [ $? -eq 0 ]; then
    echo "npm build succeeded. Running mvnw package in ${MVN_DIR}..."

    # Check if mvnw exists and is executable
    if [ -x "${MVN_DIR}/mvnw" ]; then
        "${MVN_DIR}/mvnw" -f "${MVN_DIR}/pom.xml" package

        # Check if mvnw package was successful
        if [ $? -eq 0 ]; then
            echo "mvnw package succeeded."
        else
            echo "mvnw package failed."
            exit 1
        fi
    else
        echo "mvnw file does not exist or is not executable in ${MVN_DIR}."
        exit 1
    fi
else
    echo "npm build failed."
    exit 1
fi
