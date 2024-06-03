#!/bin/bash

DOCKER_COMPOSE_DIR="./docker"

echo "Building application..."
./build.sh

if [ $? -eq 0 ]; then
    echo "Building succeeded. Running deploying on docker..."

    sudo docker compose -f "${DOCKER_COMPOSE_DIR}/docker-compose.yml" up -d

    if [ $? -eq 0 ]; then
        echo "docker-compose up succeeded."
    else
        echo "docker-compose up failed."
        exit 1
    fi
else
    echo "build.sh failed."
    exit 1
fi