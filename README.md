# myretail
This is a RESTful service that can retrieve product and price details by ID.

# Technologies

JDK 1.8
Maven 4.13
SpringBoot 2.3.0.RELEASE
Mongo 3.2.10
JUnit 4.13  

# Mongo DB database and collection

database=productdb
mongodb host=localhost
mongodb port=27017
mongo collection name =productprice

# Build, Test and Run application
cd myretail
	Then run
mvn clean install
mvn clean package
	Then run the jar
java -jar target/myretailApp-0.0.1-SNAPSHOT.jar

Application will start running on port 8080

# Calling api services

	GET request on http://localhost:8080/product/13860428 will return json object with product information.

	GET http://localhost:8082/product/13860428

	Response:-

	{
	  "id": 13860428,
	  "name": "The Big Lebowski (Blu-ray)",
	  "productPrice": {
	    "price": 13.49,
	    "currencyCode": "USD"
	  }
	}

	#Use Postman to perform PUT operation

	To perform PUT operation, send JSON object with updated price in request body, it will return 200 OK status code and thus will update product with updated pricing information.

	PUT http://localhost:8082/product/13860428

	Request Body:-

	{
	  "id": 13860428,
	  "productPrice": {
	    "price": 29.49,
	    "currencyCode": "USD"
	  }
	}
