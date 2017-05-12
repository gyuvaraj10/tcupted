#!/bin/bash
docker exec -it tcupted_db psql -U tcup
# -W force createdb to prompt for a password before connecting to a databse
createdb -e -l en-GB -h localhost -p port -U username -W db_name

