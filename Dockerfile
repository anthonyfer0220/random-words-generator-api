FROM eclipse-temurin:17-jdk

RUN mkdir /opt/app

COPY target/random-words-generator-api-0.0.1-SNAPSHOT.jar /opt/app/japp.jar

ENTRYPOINT [ "java", "-jar", "/opt/app/japp.jar" ]