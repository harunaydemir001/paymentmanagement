FROM openjdk:21-jdk-oracle
ARG JAR_FILE=target/config-server-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "/app.jar"]
