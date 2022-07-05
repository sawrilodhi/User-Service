FROM openjdk:8
EXPOSE 9091
ADD sawri/User-Service-0.0.1-SNAPSHOT.jar User-Service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "User-Service-0.0.1-SNAPSHOT.jar"] 