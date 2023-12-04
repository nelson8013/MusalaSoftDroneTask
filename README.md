# Drone Medication Delivery Service

## PREREQUISITES
- Ensure MySQL is running locally on port 3306.

## Build Instructions
1. Clone the repository.
2. Run the schema (drone-service.sql) script located in the project root directory:
3. Navigate to the project directory.
4. Run the following command to build the project:
    ```bash
    ./mvnw clean install
    ```
## Run Instructions
1. Make sure MySQL is running.
2. Update the database configuration in `application.properties`, especially properties related to database connection.
3. Run the following command to start the application:
    ```bash 
    ./mvnw spring-boot:run
    ```

## Test Instructions
- Run the following command to execute JUnit tests:
    ```bash
    ./mvnw test
    ```

## API Endpoints
- Refer to `DroneController.java` for available endpoints and their usages.
