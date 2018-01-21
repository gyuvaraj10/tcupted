# TCUP DB Documentation
We use docker to create the postgres database server. Using the script "create_db_server.sh" will create a postgres database server running as a docker container.
The following are the default values for the postgres database server running.

DB name: postgres
User name: tcup
Password: tcup
host name: localhost
port: 5432

The default database name will be "postgres" and the user will be "tcup"

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
    
   
   
http://blog.netgloo.com/2014/10/27/using-mysql-in-spring-boot-via-spring-data-jpa-and-hibernate/

https://examples.javacodegeeks.com/enterprise-java/spring/boot/spring-boot-and-angularjs-integration-tutorial/