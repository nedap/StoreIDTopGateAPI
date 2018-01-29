# Renos API v1

Store!D API for !D Top and !D Gate

Java and C# reference implementation and test tool.

## Working directory is java, so go to it
	cd java
	
## To build the code
	mvn clean install

## To run the code
	java -jar target/StoreIdTopGateApiTester-1.22-jar-with-dependencies.jar http://<unit ip address>:8081
	
	Optionally, you can use the hostname of this computer as second parameter to start automatic testing

	Optionally, you can use the "count" as third parameter to count unique EPCs 
		or "log" to log the events in CSV file format

