#!/usr/bin/env bash
echo "Creating the Mongo DB server"

IMG_NAME=mongo

if [ $(docker images |grep $IMG_NAME |wc -l) == 1 ]
 then
    echo "Docker MongoDB image is available. Not pulling the image from the docker hub"
 else
    docker pull $IMG_NAME
fi
echo "Running the Mongo Docker container"
docker run --name tcupted  -d mongo