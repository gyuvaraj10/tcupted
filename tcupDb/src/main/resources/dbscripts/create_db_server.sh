#! /usr/bin/env bash
echo "Checking if the postgres image is available"
CONT_NAME=tcupted_db
if [ $(docker images |grep postgres |wc -l) == 1 ]
 then
    echo "Postgres image is available. Not pulling the image from the docker hub"
 else
    docker pull postgres
fi
echo "container name is "  $CONT_NAME
if [ $(docker ps |grep postgres |grep $CONT_NAME |wc -l) == 1 ]
  then
     echo "Postgres instance is running on your machine"
  else
     docker run --name $CONT_NAME -e POSTGRES_PASSWORD=tcupted -d postgres
fi

echo "Docker running images are"
docker ps|grep $CONT_NAME