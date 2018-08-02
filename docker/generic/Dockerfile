FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
ENV EUREKA_HOST=localhost
ENV CLOUD_CONFIG_HOST=localhost
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]


