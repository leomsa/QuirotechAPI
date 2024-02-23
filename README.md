# QuiroTech API

## Overview
The QuiroTech API is a RESTful web service developed in Java 17 with the Spring Boot framework. It provides functionalities for managing patient records in a chiropractic clinic, including CRUD operations (Create, Read, Update, Delete).

## Features
- **Spring Boot Framework**: Simplifies Java application setup and development.
- **Spring Data JPA**: Enables easy interaction with the database using Java Persistence API.
- **MySQL Database**: Provides persistent storage for patient data.
- **Docker-compose**: Container for remote access.
- **RESTful Endpoints**: Well-defined endpoints for managing patients following RESTful design principles.
- **Input Validation and Error Handling**: Ensures data integrity and provides informative error messages.

## Technologies Used
- Java 17
- Spring Boot
- Spring Data JPA
- MySQL Database (Docker-compose container for remote access)

## Usage
1. **Clone the Repository**: 
2. **Import into IDE**: Import the project into your preferred Java IDE.
3. **Configure Database**: Set up the connection to the H2 in-memory database in the Spring configuration file (`application.properties`).
4. **Run the Application**: Start the Spring Boot application locally.
5. **Test Endpoints**: Use tools like Postman to test the API endpoints.

## Endpoints

### Create Patient
- **URL**: `/patient/create`
- **Method**: POST
- **Description**: Creates a new patient record.
- **Request Body**: Patient object (JSON)
 ```json
 {
     "username": "LeoMSA",
     "name": "Leonardo Machado dos Santos",
     "cpf": "03436828065",
     "bornDate": "11-04-1996",
     "gender": "M",
     "password": "452563",
     "contact": [
         {
             "contactValue": "leonardo@gmail.com",
             "contactType": "EMAIL"
         },
         {
             "contactValue": "51985064520",
             "contactType": "PHONE_NUMBER"
         }
     ],
     "address": {
         "address": "santana",
         "houseNumber": "582",
         "details": "Apt 102, BL c",
         "city": "Canoas",
         "district": "RS",
         "zipCode": "93285222"
     }

 ```
- **Response**: ResponseEntity

### Get Patient by CPF
- **URL**: `/patient/user`
- **Method**: GET
- **Description**: Retrieves a patient record by CPF.
- **Request Parameter**: `cpf` (String)
- **Response**: PatientDTO object (JSON)

### Get All Patients
- **URL**: `/patient/allpatients`
- **Method**: GET
- **Description**: Retrieves a list of all patients.
- **Response**: List of Patient objects (JSON)

### Update Patient
- **URL**: `/patient/update`
- **Method**: PUT
- **Description**: Updates an existing patient record by ID.
- **Request Parameter**: `id` (UUID)
- **Request Body**: Patient object (JSON)
 ```json
 {
     "name": "Updated Name",
     "cpf": "00000000000",
     "bornDate": "11-04-1996",
     "gender": "M",
     // Other patient attributes
 }
 ```
- **Response**: ResponseEntity\<Patient\>

### Delete Patient
- **URL**: `/patient/delete`
- **Method**: DELETE
- **Description**: Deletes a patient record by CPF and ID.
- **Request Parameters**: `cpf` (String), `id` (UUID)
--------------------------------------------------------------------------------------------------------------------------------------------
## Frontend Repository

The frontend for this project is available in a separate repository. You can find the frontend code and related documentation at the following repository:

- **Repository:** [Frontend Repository URL](https://github.com/leomsa/Quirotech_Fe)

Please refer to the frontend repository for more details on the frontend implementation, setup instructions, and how to contribute to the frontend development.


## Author
Leonardo Machado dos Santos
