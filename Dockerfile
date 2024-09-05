# Use an official Gradle image to build the project
FROM gradle:7.6.0-jdk17 AS build

# Set the working directory
WORKDIR /app

# Copy the Gradle wrapper and build files
COPY build.gradle settings.gradle /app/
COPY src /app/src

# Build the project, omitting tests
RUN gradle build --no-daemon -x test

# Use an official OpenJDK runtime image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Copy the .env file
COPY .env /app/.env

# Expose the port the app runs on
EXPOSE 8080

# Set the environment variables from the .env file
ENV CONNECTION_URL=${CONNECTION_URL}
ENV USER=${USER}
ENV PASSWORD=${PASSWORD}

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
