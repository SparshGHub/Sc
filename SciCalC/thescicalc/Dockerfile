# Stage 1: Build the JAR using Maven
FROM maven:3.9.6-eclipse-temurin-11 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run the application using OpenJDK
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/thescicalc-*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

```
# Stage 1: Build the JAR using Maven
FROM maven:3.9.6-eclipse-temurin-11 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run the application using OpenJDK
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/thescicalc-*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
```

**How it works:**
- The first stage uses a Maven image to build your project and create the JAR.
- The second stage copies the JAR from the build stage into a lightweight Java runtime image and sets the entrypoint.

**Usage:**
1. Place this Dockerfile in your project root.
2. Build the Docker image:
   ```bash
   docker build -t thescicalc:latest .
   ```
3. Run the container:
   ```bash
   docker run --rm thescicalc:latest
   ```

This approach ensures your app is always built in a clean, reproducible environment and is ready to run anywhere Docker is supported.
