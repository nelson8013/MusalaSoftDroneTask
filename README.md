# Drone Medication Delivery Service

## PREREQUISITES
- Ensure MySQL is running locally on port 3306.

## Build Instructions
1. Clone the repository.
2. Run the schema (drone-service.sql) script located in the project root directory:
     ```bash
    mysql -u <your_username> -p <your_password> < drone-service.sql
    ```
   Replace `<your_username>` and `<your_password>` with your MySQL username
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
- Here's the documentation for the endpoints.

## Drone Controller

### Get Drone
Retrieve information about a specific drone.

- **Endpoint:** `GET /drone/{droneId}`
- **Parameters:**
    - `droneId` (Long) - ID of the drone.
- **Response:**
    - Status: 200 OK
    - Body: Drone details.

### Get All Drones
Retrieve information about all drones.

- **Endpoint:** `GET /drones`
- **Response:**
    - Status: 200 OK
    - Body: List of drones.

### Register Drone
Register a new drone.

- **Endpoint:** `POST /register-drone`
- **Request Body:**
    - `DroneRequest` - Details of the drone.
- **Response:**
    - Status: 201 Created
    - Body: Details of the registered drone.

### Load Medication
Load a single medication into a drone.

- **Endpoint:** `POST /load-medication/{droneId}`
- **Parameters:**
    - `droneId` (Long) - ID of the drone.
- **Request Body:**
    - `Medication` - Details of the medication.
- **Response:**
    - Status: 204 No Content

### Load Multiple Medications
Load multiple medications into a drone.

- **Endpoint:** `POST /load-medications/{droneId}`
- **Parameters:**
    - `droneId` (Long) - ID of the drone.
- **Request Body:**
    - List of `Medication` - Details of the medications.
- **Response:**
    - Status: 204 No Content

### Get Available Drones for Loading
Retrieve a list of available drones for loading.

- **Endpoint:** `GET /get-available-drones-for-loading`
- **Response:**
    - Status: 200 OK
    - Body: List of available drones.

### Get Battery Level
Retrieve the battery level of a drone.

- **Endpoint:** `GET /get-battery-level/{droneId}`
- **Parameters:**
    - `droneId` (Long) - ID of the drone.
- **Response:**
    - Status: 200 OK
    - Body: Battery level of the drone.

### Get Loaded Medication from Drone
Retrieve a list of medications loaded into a drone.

- **Endpoint:** `GET /get-loaded-medication-from-drone/{droneId}`
- **Parameters:**
    - `droneId` (Long) - ID of the drone.
- **Response:**
    - Status: 200 OK
    - Body: List of loaded medications.

## Data Transfer Objects

### Drone Request
Details for registering a new drone.

- `serialNumber` (String) - Serial number of the drone.
- `model` (String) - Model of the drone.
- `weightLimit` (Double) - Weight limit of the drone.
- `batteryCapacity` (Integer) - Battery capacity of the drone.
- `state` (DroneState) - Current state of the drone.

### Medication Request
Details for creating a new medication.

- `name` (String) - Name of the medication.
- `code` (String) - Code of the medication.
- `weight` (Double) - Weight of the medication.
- `image` (String) - Image URL of the medication.
