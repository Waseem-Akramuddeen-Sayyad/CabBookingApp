# CabBookingApp

An interactive and secure cab booking application designed using Java and Spring Boot. This project demonstrates robust implementation of core features such as user authentication, role-based access control, and RESTful API communication, ensuring scalability, security, and user-friendliness.

## Available Scripts

In the project directory, you can run:

### `./mvnw spring-boot:run`

- Runs the application in development mode.
- Access the application at `http://localhost:8080`.
- The console will log the server status and any application errors or warnings.

### `./mvnw test`

- Launches the test suite for the application.
- Displays detailed test results in the console.

### `docker build -t cabbookingapp .`

- Builds a Docker image for the application.
- Ensures portability and consistency across different environments.

### `docker run -p 8080:8080 cabbookingapp`

- Runs the application in a Docker container.
- Maps the container's port 8080 to the host machine's port 8080.

### `mvn clean install`

- Cleans and builds the application.
- Packages the application as a deployable `.jar` file located in the `target` directory.

Feel free to refer to the above resources or open an issue in the repository for further assistance.

