# Spring Boot API demoing Retail Price Algorithm

## How to run

Navigate to the root of the project in your terminal and enter the following command
#### MacOS or Linux: 
`./mvnw spring-boot:run`
#### Windows: 
`mvnw.cmd spring-boot:run`

## Ensuring correctness of Algorithm

Once the app is running, you may test the REST API responses for correctness. In the main code, an SQL file named `data.sql` is loaded on startup. The app runs by default on port `8080`. 

In order to test the algorithm you have to execute a GET HTTP Request to the following URL: `http://localhost:8080/api/v1/prices/priority-pricing/{brandId}/{productId}` 

Substitute parameters `brandId` and `productId` from the path above with the values you want to test, as well as add a request parameter of the date in format of [datetime](https://es.wikipedia.org/wiki/ISO_8601) (example: `http://localhost:8080/api/v1/prices/priority-pricing/1/35455?dateTime=2020-06-15T16:00:00`).

## Testing
 
The app has been tested with a Unit Test for the main method of the algorithm (`PriceService#PriceDto getMaxPriorityPrice(long brandId, long productId, LocalDateTime date)`), as well as an Integration Test with SQL insertion on startup. 

## Wholeness

The app's REST API also has OpenAPI/Swagger Descriptions for its methods and also has a full CRUD functionality. 


