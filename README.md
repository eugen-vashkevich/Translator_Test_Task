# Translation Service Application

## Overview

This is a web application for translating a set of words from one language to another using a third-party translation
service (Google). The application is developed in Java using the Spring Boot framework. It supports translation of each
word in a string separately in several threads, with the number of concurrently running threads not exceeding 10.

## How To Run with Docker

### Steps

1. **Build Docker-image**:

    - Go to the project directory and execute the command:
   
    ```bash
   docker build -t translator_test_task_app:latest . 
   ```
   
2. **Start Docker Compose**:

   - Run in the terminal:
   
    ```bash
   docker-compose up 
   ```

## [Endpoints](https://documenter.getpostman.com/view/37402449/2sA3rzJCfr)

