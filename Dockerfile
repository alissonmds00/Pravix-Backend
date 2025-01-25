# Use a compatible Maven image to compile the project
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Use a compatible OpenJDK image to run the application
FROM openjdk:21
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 555
ENTRYPOINT ["java", "-jar", "app.jar"]