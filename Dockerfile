# Use the official Maven image to build the project
FROM maven:3.9.9-eclipse-temurin-22-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml file and the source code into the container
COPY pom.xml .
COPY src ./src

# Run Maven to build the project and create the JAR file
RUN mvn clean package -DskipTests

# Use a minimal Java image to run the application
FROM eclipse-temurin:21-jdk-alpine

# Set the working directory for the application
WORKDIR /app

# Copy the JAR file from the build image
COPY --from=build /app/target/police-backend.jar ./app.jar

# Expose the application's port
EXPOSE 8080

# Define the command to run the application
CMD ["java", "-jar", "app.jar"]
