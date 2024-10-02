
# SpringAI Simple RAG Demo

This is a simple **Retrieval-Augmented Generation (RAG)** example built using **Spring Boot** and **Spring AI**. The project demonstrates how to integrate OpenAI's capabilities with vector stores to enhance query responses using RAG.

## Overview

In this demo, the application uses a **VectorStore** for enhanced question-answering, leveraging OpenAI’s capabilities with the help of a RAG (Retrieval-Augmented Generation) model. It provides a single API endpoint that takes a user query and generates responses by searching stored vectors and providing answers with context.

## Technologies Used

- **Java**: 21
- **Spring Boot**: 3.2.10
- **Spring AI (OpenAI Integration)**
- **VectorStore**: For storing and searching vectorized data.
- **Maven**: For dependency management and building the project.

## Prerequisites

- **Java 21** installed on your system.
- **Maven** installed.
- A valid **OpenAI API Key**.
- Vector data prepared for the RAG process.

## Setup and Installation

### Step 1: Clone the Repository

```bash
git clone https://github.com/LegPro/springai-simple-rag-demo.git
cd springai-simple-rag-demo
```

### Step 2: Set the OpenAI API Key

The application needs your OpenAI API key to connect with the OpenAI API. You can set this key as an environment variable or in the `application.properties` file.

#### Option 1: Set as an Environment Variable

On **Linux/macOS**:
```bash
export SPRING_AI_OPENAI_API_KEY=your-openai-api-key
```

On **Windows**:
```bash
set SPRING_AI_OPENAI_API_KEY=your-openai-api-key
```

#### Option 2: Add to `application.properties`

Alternatively, add the API key directly in `src/main/resources/application.properties`:

```properties
spring.ai.openai.api-key=your-openai-api-key
```

### Step 3: Build the Project

Use Maven to build the project:

```bash
mvn clean install
```

### Step 4: Run the Application

Once the build is successful, you can run the application using:

```bash
mvn spring-boot:run
```

The application will be accessible at `http://localhost:8080`.

## API Endpoints

### 1. `/newproducts`

- **Method**: `GET`
- **Description**: Takes a user query as input and returns a response using the RAG model.
- **Query Parameter**:
  - `message`: The input message/query to the model (default: "Give me Information about Bank of America").
- **Example**:
    ```bash
    curl "http://localhost:8080/newproducts?message=Tell me about Farsana"
    ```

- **Response**: 
    The response will be generated based on the vector store and OpenAI's model, providing context-aware information.

## Project Structure

```bash
springai-simple-rag-demo/
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── ragdemo
│   │   │               ├── controller
│   │   │               │   └── AppController.java
│   └── resources
│       └── application.properties
├── pom.xml
└── README.md
```

- **AppController.java**: Contains the REST API that handles the RAG process.
- **application.properties**: Holds the configuration for the application, including the OpenAI API key.

## Key Components

- **ChatClient**: This client is responsible for interacting with the OpenAI API.
- **VectorStore**: Used for storing and searching through vectorized data, which improves the context and accuracy of responses.
- **QuestionAnswerAdvisor**: An advisor that assists in using the vector store for RAG, augmenting query results with relevant vector-based data.

## Maven Dependencies

The project includes the following dependencies:

```xml
<dependencies>
    <!-- Spring Boot Web Dependency -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- OpenAI and VectorStore dependencies -->
    <dependency>
        <groupId>org.springframework.ai</groupId>
        <artifactId>spring-ai-openai</artifactId>
        <version>1.0.0</version>
    </dependency>

    <!-- Spring Boot Test -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

## Additional Configuration

To customize the behavior of vector search and retrieval in the application, you can modify the following in the `AppController`:

- **Vector Search Request Configuration**: Adjust the search parameters used by `SearchRequest.defaults()` to fine-tune retrieval behavior.
- **Template for Custom User Prompts**: Customize the default message or provide dynamic prompt generation.

