FROM openjdk:8
EXPOSE 9091
ADD target/user-service-0.0.2.jar user-service-0.0.2.jar
ENTRYPOINT ["java", "-jar", "/user-service-0.0.2.jar"] 