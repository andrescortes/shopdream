# Spring Boot E-Commerce Application

## Overview
This is a Spring Boot application designed for an e-commerce platform. It includes features for managing products, categories, and images, along with robust security mechanisms.

## Features
- **Product Management**: Create, read, update, and delete products.
- **Category Management**: Organize products into categories.
- **Image Handling**: Upload and manage product images.
- **Security**: User authentication and authorization using Spring Security.

## Getting Started

### Prerequisites
- Java 17 or higher
- Gradle 10 or higher
- Docker and Docker Compose

### Installation

1. **Clone the repository**:
    ```bash
    git clone https://github.com/andrescortes/shopdream.git
    cd shopdream
    ```

2. **Configure the database**:
    Update the `application.properties` file with your database settings:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/shop_db
    spring.datasource.username=root
    spring.datasource.password=yourpassword
    spring.jpa.hibernate.ddl-auto=update
    ```

3. **Build the project**:
    ```bash
    ./gradlew build
    ```

4. **Run the application**:
    ```bash
    ./gradlew bootRun
    ```

### Docker Setup

1. **Create a Dockerfile**:
    ```dockerfile
    FROM openjdk:17-jdk-alpine
    VOLUME /tmp
    ARG JAR_FILE=build/libs/*.jar
    COPY ${JAR_FILE} app.jar
    ENTRYPOINT ["java","-jar","/app.jar"]
    ```

2. **Create a docker-compose.yml file**:
    ```yaml
    services:
      app:
        image: springboot-ecommerce
        build:
          context: .
          dockerfile: Dockerfile
        ports:
          - "8080:8080"
        environment:
          - SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/shop_db
          - SPRING_DATASOURCE_USERNAME=root
          - SPRING_DATASOURCE_PASSWORD=yourpassword
      db:
        image: postgres:16-alpine
        environment:
          POSTGRES_PASSWORD: yourpassword
          POSTGRES_DB: ecommerce
        ports:
          - "5432:5432"
    ```

3. **Build and run the Docker containers**:
    ```bash
    docker compose up -d
    ```

## Usage

### API Endpoints

#### Products
- **GET /api/products**: Retrieve all products
- **POST /api/products**: Create a new product
- **GET /api/products/{id}**: Retrieve a product by ID
- **PUT /api/products/{id}**: Update a product by ID
- **DELETE /api/products/{id}**: Delete a product by ID

#### Categories
- **GET /api/categories**: Retrieve all categories
- **POST /api/categories**: Create a new category
- **GET /api/categories/{id}**: Retrieve a category by ID
- **PUT /api/categories/{id}**: Update a category by ID
- **DELETE /api/categories/{id}**: Delete a category by ID

#### Images
- **POST /api/products/{id}/images**: Upload an image for a product
- **GET /api/products/{id}/images**: Retrieve images for a product
- **DELETE /api/products/{id}/images/{imageId}**: Delete an image by ID

### Security
The application uses Spring Security for authentication and authorization. Users must log in to access the API endpoints. Roles and permissions are managed to ensure that only authorized users can perform certain actions.

## Contributing
Contributions are welcome! Please fork the repository and create a pull request with your changes.

