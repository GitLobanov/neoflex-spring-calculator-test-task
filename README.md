# Vacation Pay Calculator

## Overview

The Vacation Pay Calculator is a Java-based application built with Spring Boot. It calculates the vacation pay for employees based on their average salary and vacation days. The application supports various scenarios including simple vacation pay calculations and calculations considering weekends and holidays.

## Features

- **Simple Vacation Pay Calculation**: Computes vacation pay based on average salary and number of vacation days.
- **Advanced Calculation**: Includes weekends and holidays in the vacation pay calculation if start and end dates are provided.
- **Flexible API**: Allows you to specify various parameters for vacation pay calculation through a RESTful API.

## Getting Started

### Prerequisites

- **Java 11**: Ensure you have Java 11 installed.
- **Maven**: For building and managing the project dependencies.

### Installation

1. **Clone the repository**:
    ```bash
    git clone https://github.com/GitLobanov/neoflex-spring-calculator-test-task.git
    cd vacation-pay-calculator
    ```

2. **Build the project**:
    ```bash
    mvn clean install
    ```

3. **Run the application**:
    ```bash
    mvn spring-boot:run
    ```

The application will start and listen on port `8080` by default.

## API Usage

### Endpoints

1. **Calculate Vacation Pay**
   - **URL**: `/calculate`
   - **Method**: `GET`
   - **Parameters**:
     - `averageSalary` (required, `BigDecimal`): The average monthly salary of the employee.
     - `vacationDays` (optional, `Integer`): Number of vacation days.
     - `startDay` (optional, `LocalDate`): The start date of the vacation (format: `yyyy-MM-dd`).
     - `endDay` (optional, `LocalDate`): The end date of the vacation (format: `yyyy-MM-dd`).

   - **Response**:
     - **200 OK**: Returns the calculated vacation pay.
     - **400 Bad Request**: If invalid parameters are provided.

   - **Example Requests**:
     ```http
     GET /calculate?averageSalary=5000&vacationDays=10
     ```
     ```http
     GET /calculate?averageSalary=25000&startDay=2024-01-01&endDay=2024-09-10
     ```

   - **Example Responses**:
     ```json
     {
       "vacationPayAmount": "1666.67"
     }
     ```

     ```json
     {
       "vacationPayAmount": 143808
     }
     ```

## Code Structure

- **Controller**: Handles HTTP requests and maps them to appropriate services.
- **Service**: Contains business logic and calculates vacation pay based on different strategies.
- **Strategy**: Implements various vacation pay calculation strategies.
- **Factory**: Provides the appropriate strategy based on request parameters.

## Error Handling

- **Invalid Parameters**: Returns a `400 Bad Request` status with a description of the error.
- **Internal Errors**: Returns a `500 Internal Server Error` status with a description of the error.

## Test Cases

