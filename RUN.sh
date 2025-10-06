#!/bin/bash

# Script: run_scicalc.sh
# Purpose: Run the Scientific Calculator Docker container interactively

APP_NAME="scicalc"
IMAGE_NAME="sparshdockerman/scicalc:latest"
HOST_PORT=9090
CONTAINER_PORT=8080

# Check if Docker is installed
if ! command -v docker &> /dev/null; then
  echo "Docker is not installed. Please install Docker first."
  exit 1
fi

# Stop and remove any existing container with the same name
if [ "$(docker ps -aq -f name=$APP_NAME)" ]; then
  echo "Stopping and removing existing container '$APP_NAME'..."
  docker stop $APP_NAME >/dev/null 2>&1
  docker rm $APP_NAME >/dev/null 2>&1
fi

# Run the container interactively
echo "Starting Scientific Calculator container..."
docker run -it --name $APP_NAME -p $HOST_PORT:$CONTAINER_PORT $IMAGE_NAME

