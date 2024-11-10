# Use an official Java runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the Maven build output (jar file) to the container
COPY target/Foyer-2.0.3-SNAPSHOT.jar app.jar

# Make port 8111 available to the world outside this container
EXPOSE 8087

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
