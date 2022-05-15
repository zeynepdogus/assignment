FROM adoptopenjdk:11-jre-hotspot
COPY . /information-service
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} information-service/target/microservice.jar
COPY information-service/src/main/resources/create-db.sql /docker-entrypoint-initdb.d/
ENTRYPOINT ["java", "-jar", "./information-service/target/microservice.jar"]