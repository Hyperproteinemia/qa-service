FROM openjdk:8-jdk-alpine

VOLUME /tmp

EXPOSE 8083

ARG JAR_FILE=target/qa-service-1.0.jar
ADD ${JAR_FILE} qa-service.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","qa-service.jar"]