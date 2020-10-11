## FibServer

### Overview
This program is a simple Spring Boot application that calculates and returns numbers from  the Fibonacci sequence. 

It provides 3 REST endpoints using HTTP GET (on port 8080): 
* /current   -- returns the current number in the Fibonacci sequence. The returned payload is JSON, eg: ```{"fibonacci":55}```  
* /next  -- calculates and returns the next number in the sequence.  As above, the returned payload is JSON.  If the next number would overflow a Java long, an error is returned: ```{"status":400,"path":"/next","errorMessage":"Bad Request.  Number would overflow Java long datatype.","timeStamp":"Sat Oct 10 12:07:23 EDT 2020"}``` 
* /previous  -- calculates and returns the previous number in the sequence. If the current number in the sequence is 0, this call returns an error: ```{"status":400,"path":"/previous","errorMessage":"Bad Request.  No previous number in the Fibonacci sequence.","timeStamp":"Sat Oct 10 12:35:21 EDT 2020"}```

Since the program is only maintaining one sequence (not one per client application), there may be gaps in the sequence when multiple programs are accessing the endpoints. 

### Download, Compile, Run

```
git clone http://github.com/rcprcp/FibServer.git 
cd FibServer
mvn clean package spring-boot:repackage
```

### Test
You can run the tests from mvn: 
```
mvn test 
```
or 
```
mvn test -Dtest=TestFibonacci
mvn test -Dtest=TestSaveRestoreUsingFile
```

**Note:** The restart "state" is found in ./checkpoint.data - remove this file to start the Fibonacci sequence from 0.

Start the server: 
```java -jar target/fibserver-0.0.1-SNAPSHOT.jar```

The server opens port 8080.

If desired, test it from your browser: 
```
http://localhost:8080/current
http://localhost:8080/next
http://localhost:8080/previous
```
