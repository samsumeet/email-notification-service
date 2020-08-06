FROM adoptopenjdk/openjdk11:latest
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} email-notification-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/email-notification-0.0.1-SNAPSHOT.jar"]