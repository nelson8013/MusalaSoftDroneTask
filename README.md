# Drone Medication Delivery Service

![image](https://builtin.com/cdn-cgi/image/f=auto,quality=80,width=752,height=435/https://builtin.com/sites/www.builtin.com/files/styles/byline_image/public/2022-09/package-drone-delivery-companies.png)


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
    ![image](https://github.com/nelson8013/MusalaSoftDroneTask/assets/12644704/2814801d-1db3-45b4-bc77-752af895c6ff)

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
    
  ![image](https://github.com/nelson8013/MusalaSoftDroneTask/assets/12644704/b3304516-8504-4adf-8b34-080c50e58a8f)

- **Response:**
  - Status: 204 No Content

### Load Multiple Medications
Load multiple medications into a drone.

- **Endpoint:** `POST /load-medications/{droneId}`
- **Parameters:**
  - `droneId` (Long) - ID of the drone.
- **Request Body:**
  - List of `Medication` - Details of the medications.
    ![image](https://github.com/nelson8013/MusalaSoftDroneTask/assets/12644704/b384a381-f125-4257-948a-78d158a5b8fe)

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



### SIDE NOTE
- Once you start or run the application and see this:

![image](https://github.com/nelson8013/MusalaSoftDroneTask/assets/12644704/aea2b6b0-c9e5-407c-bd5a-b0008e0ba82c)
- The application is ready

### POSTMAN SCREENS
- Get All Drones
  ![image](https://github.com/nelson8013/MusalaSoftDroneTask/assets/12644704/044ec4e0-044f-473d-ad6b-0ecc3eb18e10)

- Get A Single Drone
  ![image](https://github.com/nelson8013/MusalaSoftDroneTask/assets/12644704/db2cfe4d-ddfc-41f5-af1c-f83046900546)

- Register a Drone
  ![image](https://github.com/nelson8013/MusalaSoftDroneTask/assets/12644704/6007ea54-fabc-42bd-af8b-b64328bca46a)

- Load Medication
  ![image](https://github.com/nelson8013/MusalaSoftDroneTask/assets/12644704/e6047f83-b13a-4253-955c-2ae7de095af1)

- Load multiple Medications on a drone
  ![image](https://github.com/nelson8013/MusalaSoftDroneTask/assets/12644704/2744242b-2bf0-4d6c-9a4e-97377d9e568b)

- Get available drones for loading
  ![image](https://github.com/nelson8013/MusalaSoftDroneTask/assets/12644704/23ac36c1-cdbf-4996-bae6-6d453e2e6925)


