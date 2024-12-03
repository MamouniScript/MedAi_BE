FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ./target/med-ai-be-0.0.1-SNAPSHOT.jar dep_app.jar
#LABEL authors="Utilisateur"

ENTRYPOINT ["java", "-jar" ,"/dep_app.jar"]