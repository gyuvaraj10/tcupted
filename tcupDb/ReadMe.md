# TCUP DB Documentation

## Pre-requisites for running script:
The pre-requisites are built on mac os. 
 
1. Make sure you have brew installed. To install brew on your mac follow the steps described at the brew's official website. 
   please refer https://brew.sh/

2. Make sure docker is available on your mac. You can install docker using the brew.
       ```bash
        brew install docker
       ```


## Running the shell scripts available
Make sure you have set the executable permissions before running this script. There are 2 scripts available "create_db_server.sh", "create_db.sh"
  a. create_db_server.sh: This script will install the server and run it in the docker contains.
  b. create_db.sh: This script will create the database in the above created running container

Tips: 
  1. To know the running docker containers, just run "docker ps" which will list the running container details
  2. "docker images" command list/displays all the docker images available in your machine
  3. To kill a running docker container run "docker kill <CONTAINER_ID>"
  4. To remove a created container run "docker rm <CONTAINER_ID"
    
   