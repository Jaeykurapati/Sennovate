# Sennovate

# Run the example
To run the example first build the project using:

`mvn clean install`

Then go to the application module restapi-api and execute:

`mvn spring-boot:run`

To stop the application send 

`curl -i -X POST http://localhost:8080/actuator/shutdown`

---------
