# docker file for the spring boot application
FROM openjdk:17
SHELL ["mvn install -DskipTests"]
ADD target/feedbacks-api-docker.jar feedbacks.jar
ENTRYPOINT ["java", "-jar", "feedbacks.jar"]
EXPOSE 8080