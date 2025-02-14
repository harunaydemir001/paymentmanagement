FROM openjdk:21-jdk-oracle
ARG JAR_FILE=target/*.jar
COPY ./target/account-management-service-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
