#! /usr/bin/env bash
echo "Checking if the postgres image is available"
CONT_NAME=tcupted_db
DB_NAME=tcup
USERNAME=tcup
PASSWORD=tcup
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
     docker run --name $CONT_NAME -e POSTGRES_USER=$USERNAME POSTGRES_PASSWORD=PASSWORD -d localhost -p 5432
fi

echo "Docker running images are"
docker ps|grep $CONT_NAME

echo "Connecting to the running docker instance to create the database"

docker run -it --rm --link $CONT_NAME:postgres postgres psql -h postgres -U USERNAME
# -W force createdb to prompt for a password before connecting to a databse
createdb -e -l en-GB -h localhost -p 5432 -U $USERNAME -W $DB_NAME