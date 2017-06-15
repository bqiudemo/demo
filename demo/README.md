The project provides a RESTful web service.
The web service accepts a number, n, as input and returns the first n Fibonacci numbers, 
starting from 0. I.e. given n  = 5, appropriate output would represent the sequence [0, 1, 1, 2, 3]. 

It is implemented using Java. It uses an embedded Jetty instance and
utilizes Jersey 2.x for JAX-RS support. 
It follow the micro-service design pattern.

math.util.base defines Application Service interface
math.util.rest defines common REST interface and some utility class for json and http respond
math.util.jetty implements common EmbeddedResetServer by creating an embedded Jetty instance and
utilizes Jersey 2.x for JAX-RS support  

math.service Main has the main entry
math.service MathService has the Math Application Service
math.service Fibonacci implements the Fibonacci functions. It was designed to avoid extra heap memory consumption and in the meanwhile consider performance factors.

math.service.rest DefaultHttpService provide the REST application service.   
math.service.rest.resource MathResource has the JAX-RS resource provides configuration and management for math 

math.service TestFibonacci has the unitest

1. How to build

The build framework is using gradle
install JDK1.7
install gradle
  brew install gradle

From the source checkout

Clean the project
./gradlew clean

Build without test
./gradlew build -x test

Build and run the unitest. Test result is in math/build/test-results/TEST-math.service.TestFibonacci.xml
./gradlew test

Install distributions and all dependancies, the artifacts are installed into math/build/install
./gradlew installDist

2. How to run

cd math/build/install/math
./math

The log is in log/math.log

3. How to play with it

install CocoaRestClient
http://localhost:8080/demo/api/math/fibonacci/800 GET
http://localhost:8080/demo/api/math/fibonacci/8000 GET
http://localhost:8080/demo/api/math/fibonacci/0 GET
http://localhost:8080/demo/api/math/fibonacci/-1 GET
http://localhost:8080/demo/api/math/fibonacci/abc GET
