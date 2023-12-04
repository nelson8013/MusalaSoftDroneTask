# Drone Medication Delivery Service

![image]([https://uavsystemsinternational.com/cdn/shop/products/AureliaX8MAXFrontView_x314@2x.jpg?v=1669653288](https://www.cnet.com/a/img/resize/edb9e05d5873cd1d891a388bf151bfbadc0be916/hub/2023/01/04/119ad311-078f-4f38-b64f-9625e51c4707/20221121-zipline-drone-drop-02.jpg?auto=webp&fit=crop&height=675&width=1200)

## PREREQUISITES
- You'll need an API client like Postman or Thunder client.
- The project is to be run locally.


## Build Instructions
1. Clone the repository.
2. Navigate to the project directory [DroneService].
   ```bash 
    ./mvnw clean install
    ```

## Run Instructions
1. Run the following command to start the application:
    ```bash 
    ./mvnw spring-boot:run
    ```

## Test Instructions
- Run the following command to execute JUnit tests:
    ```bash
    ./mvnw test
    ```

## API Endpoints
- Use Postman to access the endpoints
- BASE URL: `http://127.0.0.1:1989/api/v1`


## Drone Controller Documentation

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


### SIDE NOTE
- Once you start or run the application and see this:

![image](https://github.com/nelson8013/MusalaSoftDroneTask/assets/12644704/aea2b6b0-c9e5-407c-bd5a-b0008e0ba82c)
- The application is ready

