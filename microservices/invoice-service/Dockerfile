FROM openjdk:21-jdk-oracle
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8096
ENTRYPOINT ["java", "-jar", "/app.jar"]