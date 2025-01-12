# Currency Exchange and Discount Application

This Spring Boot application integrates with a currency exchange API to calculate the payable amount for a bill in a specified currency after applying applicable discounts.

Please refer design diagrams here: https://github.com/OmkarMozar/currency-cal-exchange/tree/master/DesignDocs

## Features
- Integrates with a third-party currency exchange API (e.g., Open Exchange Rates) for real-time exchange rates.
- Supports percentage-based discounts (e.g., employee, affiliate, loyal customer).
- Supports flat discounts for every $100 on the bill.(on USD)
- Applies discounts based on item categories (grocery and non-grocery).
- Converts the bill total from one currency to another.
- Secures API endpoints with Basic Authentication.

## Prerequisites
- Java 17 or higher
- Maven 3.8+
- A currency exchange API key (e.g., from Open Exchange Rates or ExchangeRate-API)

## Getting Started

### 1. Clone the Repository
```bash
$ git clone <repository-url>
$ cd currency-cal-exchange
```

### 2. Configure Environment Variables
Create an `application.properties` file in the project root directory with the following content:
```env
CURRENCY_API_BASE_URL=https://open.er-api.com/v6/latest
CURRENCY_API_KEY=your-api-key
```

### 3. Run the Application
To start the application:
```bash
$ mvn spring-boot:run -Dspring-boot.run.profiles=local
```

### 4. Run Tests
To execute tests:
```bash
$ mvn clean test
```

### 5. Generate Code Coverage Report
To generate a coverage report:
```bash
$ mvn clean verify
```
The reports can be found at `target/surefire-reports/`.

## API Endpoint

### POST `/api/calculate`
This endpoint calculates the payable amount for a given bill.

#### Request Body
```json
{
  "items": [
    { "name": "Item1", "price": 50.0, "category": "non-grocery" },
    { "name": "Item2", "price": 30.0, "category": "grocery" }
  ],
  "user": {
    "userType": "employee",
    "tenure": 3
  },
  "originalCurrency": "USD",
  "targetCurrency": "EUR",
  "totalAmount": 80.0
}
```

#### Response
```json
{
  "payableAmount": 63.3165
}
```

#### Authentication
This endpoint is protected by Basic Authentication. Use your username and password in the request.

Example using cURL:
```bash
curl -u employee:password1 -X POST \
-H "Content-Type: application/json" \
-d '{
  "items": [
    { "name": "Item1", "price": 50.0, "category": "non-grocery" },
    { "name": "Item2", "price": 30.0, "category": "grocery" }
  ],
  "user": {
    "userType": "employee",
    "tenure": 3
  },
  "originalCurrency": "USD",
  "targetCurrency": "EUR",
  "totalAmount": 80.0
}' \
http://localhost:8080/api/calculate
```

## Project Structure
- `src/main/java`: Application source code.
- `src/test/java`: Unit tests.
- `src/main/resources`: Configuration files like `application.properties`.

## Notes
- Make sure to replace `your-api-key` with a valid API key in the `application.properties` file.
- Ensure the API endpoint for currency exchange is accessible.
- Authentication is configured using Basic Authentication; update user credentials as needed in `InMemoryAuthConfig`.



