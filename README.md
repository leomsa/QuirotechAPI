# QuiroTech API

## Overview
The QuiroTech API is a RESTful web service developed in Java 17 with the Spring Boot framework. It provides functionalities for managing patient records in a chiropractic clinic, including CRUD operations (Create, Read, Update, Delete).

## Features
- **Spring Boot Framework**: Simplifies Java application setup and development.
- **Spring Data JPA**: Enables easy interaction with the database using Java Persistence API.
- **H2 In-Memory Database**: Provides temporary storage for patient data during application runtime.
- **RESTful Endpoints**: Well-defined endpoints for managing patients following RESTful design principles.
- **Input Validation and Error Handling**: Ensures data integrity and provides informative error messages.

## Technologies Used
- Java 17
- Spring Boot
- Spring Data JPA
- H2 Database (in-memory)

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
     "id": "f4a04c5b-40d7-4e0f-b217-59f23d694fba",
     "username": "LeoMSA",
     "name": "Leonardo Machado dos Santos",
     "cpf": "00000000000",
     "bornDate": "11-04-1996",
     "gender": "M",
     "password": "$2a$12$VBTpL7swrbrDePcUemFlsOyZo2QZ/2VgeFijtMKZaLrG6PHsxSfAS",
     "contact": [
         {
             "id": 1,
             "contactValue": "leonardo@gmail.com",
             "contactType": "EMAIL"
         },
         {
             "id": 2,
             "contactValue": "51985064520",
             "contactType": "PHONE_NUMBER"
         }
     ],
     "address": {
         "id": 1,
         "address": "santana",
         "houseNumber": "582",
         "details": "Apt 102, BL c",
         "city": "Canoas",
         "district": "RS",
         "zipCode": "93285222"
     },
     "createdAt": "2024-01-28T19:10:59.881221"
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

- **Repository:** [Frontend Repository URL](https://github.com/leomsa/Quirotech_Fe/blob/main/README.md)

Please refer to the frontend repository for more details on the frontend implementation, setup instructions, and how to contribute to the frontend development.


## Author
Leonardo Machado dos Santos
