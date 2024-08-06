# Use the Maven image to build the application
FROM maven:latest AS builder

WORKDIR /usr/src/app
COPY . /usr/src/app
RUN mvn clean package -DskipTests

# Use the OpenJDK image to run the application
FROM openjdk:17

WORKDIR /opt/app

# Copy the JAR file from the Maven build stage
COPY --from=builder /usr/src/app/target/translator-0.0.1-SNAPSHOT.jar /opt/app/

# Copy the schema.sql file from the Maven build stage
COPY --from=builder /usr/src/app/src/main/resources/schema.sql /opt/app/

# Run the application
ENTRYPOINT ["java", "-jar", "translator-0.0.1-SNAPSHOT.jar"]
