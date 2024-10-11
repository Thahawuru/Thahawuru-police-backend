# Use an official OpenJDK image with Java 21
FROM eclipse-temurin:21-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the project JAR file to the container
COPY target/police-backend.jar ./app.jar

# Expose the application's port
EXPOSE 6000

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
