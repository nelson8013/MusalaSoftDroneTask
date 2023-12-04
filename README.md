# Drone Medication Delivery Service

## PREREQUISITES
- You should have MySQL running locally on port 3306

## Build Instructions
- Clone the repository
- Run the schema(drone-service.sql) in the root directory.
- Navigate to the project directory
- Run `./mvnw clean install` to build the project

## Run Instructions
- Make sure MySQL is running
- Update database configuration in `application.properties`
- Run `./mvnw spring-boot:run` to start the application

## Test Instructions
- Run `./mvnw test` to execute JUnit tests

## API Endpoints
- Refer to `DroneController.java` for available endpoints and their usages
