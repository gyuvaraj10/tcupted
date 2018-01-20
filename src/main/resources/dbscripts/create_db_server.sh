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
if [ $(docker ps -a |grep postgres |grep $CONT_NAME| grep "Exited" |wc -l) == 1 ]
  then
     echo "Postgres instance is not running on your machine"
     docker rm /$CONT_NAME
  else
     echo "Running the postgres instance is running on your machine hence not starting it"
     docker stop $CONT_NAME
fi

echo "Running the docker container"
docker run -p 127.0.0.1:5432:5432 --name $CONT_NAME -e POSTGRES_PASSWORD=$PASSWORD -e POSTGRES_USER=$USERNAME -d $IMG_NAME -p 5432
sleep 5
echo "Docker running images are"
docker ps|grep $CONT_NAME

echo "copying the sql file into the container"
docker cp ../dbqueries/create_schema.sql $CONT_NAME:/tmp/create_schema.sql

echo "Attempting to create the data base schemas and the tables"
docker exec -it $CONT_NAME "psql --echo-all --host=127.0.0.1 --port=5432 --dbname=postgres --username=tcup -f /tmp/create_schema.sql" -U tcup