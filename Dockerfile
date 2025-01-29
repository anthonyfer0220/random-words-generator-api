FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY . /app

RUN ./mvnw clean package

ENTRYPOINT [ "java", "-jar", "/app/target/random-words-generator-api-0.0.1-SNAPSHOT.jar" ]