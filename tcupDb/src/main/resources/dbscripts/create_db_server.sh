#! /usr/bin/env bash
echo "Checking if the postgres image is available"
CONT_NAME=tcupted_db
DB_NAME=tcup
USERNAME=tcup
PASSWORD=tcup
IMG_NAME=postgres
if [ $(docker images |grep $IMG_NAME |wc -l) == 1 ]
 then
    echo "Postgres image is available. Not pulling the image from the docker hub"
 else
    docker pull $IMG_NAME
fi
echo "container name is "  $CONT_NAME
if [ $(docker ps |grep postgres |grep $CONT_NAME |wc -l) == 1 ]
  then
     echo "Postgres instance is running on your machine"
  else
     echo "Running the postgres instance"
     docker run -p 127.0.0.1:5432:5432 --name $CONT_NAME -e POSTGRES_PASSWORD=$PASSWORD -e POSTGRES_USER=$USERNAME -d $IMG_NAME -p 5432
     sleep 5
fi
echo "Docker running images are"
docker ps|grep $CONT_NAME