FROM tomcat:8.0-jre8

LABEL maintainer="Mike Oliver"

ADD target/FrontControllerDemo.war /usr/local/tomcat/webapps/

EXPOSE 8080

CMD ["catalina.sh", "run"]