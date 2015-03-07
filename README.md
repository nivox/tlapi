# tlapi
This project implements a Twitter like API using Spring MVC and Spring jdbc support.

## Building and running
The project is mavenized and can be build by running
```bash
  mvn clean package
```
which will produce the file `target/tlapi.war` that you can deploy to an application container (tested with Tomcat 7).

In order to configure the persistency layer customize the file `src/main/resource/tlapi.properties` specifying:
- tlapi.db.host: hostname of the MySQL server
- tlapi.db.port: MySQL server port
- tlapi.sb.name: Name of the tlapi database
- tlapi.db.user: Database user
- tlapi.db.password: Database password

You can use the sequel script `bootstrap-db.sql` to create the database structure and populate it with sample data.
Once the application is deployed you can run the script `test.py` to test the code and have an overview of the implemented feature.
The script must be started providing the host and the port of the application container hosting tlapi. For instance if you deployed `tlapi.war`on a default Tomcat installation on you local machine you would invoke:
```bash
python test.py localhost 8080
```

(Please note that the script depends on the `requests`package which you can install via pip:
```bash
pip install requests
```

##Standalone docker container
In order to make the testing of the software as painless as possible, a *batteries included* docker image has been prepared.
You can fetch it and running it using these commands:
```bash
docker pull nivox\tlapi
docker run -p 8080:8080 nivox/tlap
````

This will start a container hosting MySQL and Tomcat, initialize the database and deploy the software on the application container.
As soon as the startup is completed you should be able to start the test script:
```bash
python test.py <dockerhost> 8080
```
