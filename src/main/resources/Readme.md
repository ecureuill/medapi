**Readme - Repository https://github.com/ecureuill/medapi**

# Medapi - Appointment Scheduling API

Welcome to the Medapi repository!

This project is a result of the Alura`s java backend curriculum.  

Medapi is an API designed for appointment scheduling in the medical domain. It provides functionalities to manage appointments between patients and healthcare providers.

The project aims to follow the best practices of RESTful API design, and create a well-organized and structured code. It leverages exception handling for proper error responses, uses appropriate HTTP methods for CRUD operations, and adheres to naming conventions.

Unit tests are present in the project.

The API endpoints are well-documented using Swagger/OpenAPI, making it easy to explore and understand the available functionalities

## Technologies Used

Medapi is built using a range of technologies, each serving a specific purpose to achieve the desired functionalities:

- Spring Boot: Speeds up the development process and provides a robust foundation for building production-ready applications.
- Spring Data JPA: Simplifies database access and manipulation by providing an easy-to-use repository pattern.
- Spring Security: Ensures secure access to API endpoints, protecting sensitive data and preventing unauthorized access.
- JWT (JSON Web Tokens): Enables token-based authentication, allowing for stateless and secure communication between clients and the API.
- Swagger/OpenAPI: Generates API documentation, making it easier for developers to understand and use the API's endpoints.
- FlywayDB: Manages database schema migrations, ensuring seamless updates to the database structure over time.
- Lombok: Reduces boilerplate code by generating common methods, such as getters, setters, and constructors.
- AssertJ: Provides fluent assertions, enhancing the readability of tests and making them easier to maintain.

The motivation behind using these technologies lies in their ability to boost productivity, enhance security, and streamline the development and maintenance of the API.

## Running the Project Locally

To run the Medapi project locally, follow these instructions:

1. Prerequisites:
   - JDK 17 installed on your machine or container.
   - Maven installed and set up properly.
   - MySQL database with created `vollmed_api_test`
1. Clone this repository: `git clone https://github.com/ecureuill/medapi.git`
1. Navigate to the project directory: `cd medapi`
1. Install the required dependencies: `mvn install`
1. Build the project: `mvn clean package`
1. Run the application: `mvn spring-boot:run`

The application will start locally, and you can access it through `http://localhost:8080`. Make sure to configure any necessary database settings, such as username, password, and connection URL, in the `application.properties` file or through environment variables.


## Feedback and Contributions

If you have any feedback, suggestions, or find any issues with the project, please feel free to create an issue or submit a pull request. 