FROM mysql:5.5

RUN apt-get update;
RUN apt-get install -y curl
RUN apt-get install -y openjdk-7-jdk
RUN curl -L http://mirrors.muzzy.it/apache/tomcat/tomcat-7/v7.0.59/bin/apache-tomcat-7.0.59.tar.gz -o /tmp/apache-tomcat-7.0.59.tar.gz
http://apache.panu.it/tomcat/tomcat-7/v7.0.59/bin/apache-tomcat-7.0.59.zip -o /tmp/apache-tomcat-7.0.59.zip
RUN tar -C/ -xzf /tmp/apache-tomcat-7.0.59.tar.gz
RUN rm /tmp/apache-tomcat-7.0.59.tar.gz

ADD target/tlapi.war /apache-tomcat-7.0.59/webapps/
ADD bootstrap-db.sql /

ADD tlapi-entrypoint.sh /
ENTRYPOINT ["/tlapi-entrypoint.sh"]
EXPOSE 3306
EXPOSE 8080

ENV MYSQL_ROOT_PASSWORD root
ENV MYSQL_USER tlapi
ENV MYSQL_PASSWORD tlapi
ENV MYSQL_DATABASE tlapi
