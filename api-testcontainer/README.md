### API Endpoint


## Output (With docker running)
    * docker run -p 8080:8080 slabstech/api-endpoints
      * In browser 
      * http://localhost:8080/swagger-ui/index.html

* Build Docker file 
  * .\gradlew build 
  * docker build --rm . -t slabstech/api-endpoints -f Dockerfile

* Running the project 
  * CommandLine 
    * .\gradlew build 
    * java -jar .\build\libs\parcelshop-0.0.1-SNAPSHOT.jar 
  * Docker 
    * docker run -p 8080:8080 slabstech/parcelshop

* Swagger UI 
  * springdoc: api-docs: path: /api-docs

* Dependency updates check 
  * .\gradlew dependencyUpdates -Drevision=release

* Gradle update
  .\gradlew wrapper --gradle-version 8.1.1


* Reference
  * https://www.baeldung.com/javax-validation
  * https://reflectoring.io/bean-validation-with-spring-boot/
  * https://reflectoring.io/spring-boot-execute-on-startup/
  * https://github.com/slabstech/docker
  * https://github.com/ben-manes/gradle-versions-plugin
  * https://www.baeldung.com/spring-rest-openapi-documentation
  * https://www.baeldung.com/spring-webflux
  * https://gls-ecl.jobs.personio.de/job/197131?display=en
  
