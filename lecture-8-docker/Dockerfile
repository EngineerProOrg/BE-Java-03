FROM --platform=linux/amd64 eclipse-temurin:17-jdk-alpine
RUN apk add curl
VOLUME /data/db
COPY build/libs/example-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]