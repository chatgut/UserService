FROM eclipse-temurin:20-jre-alpine

COPY target/UserService-0.0.1-SNAPSHOT.jar 0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "0.0.1-SNAPSHOT.jar"]