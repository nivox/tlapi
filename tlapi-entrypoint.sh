#!/bin/bash

echo "Starting MySql..."
/entrypoint.sh mysqld &
sleep 5
echo "Initializing database"
mysql --password=root tlapi < /bootstrap-db.sql
if [ $? != 0 ]; then
    echo >&2 "Error bootstrapping database!"
    exit 1
fi

sh /apache-tomcat-7.0.59/bin/catalina.sh run
