FROM openjdk:8
EXPOSE 9091
ADD target/user-service-0.0.1.jar user-service-0.0.1.jar
ENTRYPOINT ["java", "-jar", "/user-service-0.0.1.jar"] 