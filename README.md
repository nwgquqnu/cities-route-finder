# cities-route-finder
Backend microservices example

## Building applications
run
    mvnw clean package

In order to run docker containers, execute:
```
    docker-compose build &&	\
    docker-compose up -d h2-server && \
    docker-compose up -d cloud-config && sleep 30 && \
    docker-compose up -d service-registry && sleep 30 && \
    docker-compose up -d cities-storage && sleep 20 && \
    docker-compose up -d route-finder && sleep 20 && \
    docker-compose up -d --no-recreate
```

After Docker containers started you can request:

* Eureka http://localhost:8080/discovery
* Route-finder api http://localhost:8080/route-finder/swagger-ui.html
* Cities-storage api http://localhost:8080/cities-storage/swagger-ui.html


## cities-storage
This service has following endpoints:

    GET  /api/travel-schedule/find-by-city/{city} - returns all results from DB where city field is {city}
    GET  /api/travel-schedule - returns all information from database
    POST /api/travel-schedule - allows to insert single entity
    DELETE /api/travel-schedule/{id} - deletes entity with id {id}
    POST /api/travel-schedule/list - allows to insert several entities

All these endpoints can be used on swagger UI page 
Here is an example of request body for ```POST /api/travel-schedule/list``` request
<details><summary>Click to show</summary>
<p>

```javascript
[
    {
        "city": "A",
        "destinationCity": "B",
        "arrivalTime": "2018-08-01T00:30:00.000Z",
        "departureTime": "2018-08-01T00:00:00.000Z"
    },
    {
        "city": "A",
        "destinationCity": "E",
        "arrivalTime": "2018-08-01T02:00:00.000Z",
        "departureTime": "2018-08-01T00:00:00.000Z"
    },
    {
        "city": "A",
        "destinationCity": "C",
        "arrivalTime": "2018-08-01T02:00:00.000Z",
        "departureTime": "2018-08-01T00:00:00.000Z"
    },
    {
        "city": "B",
        "destinationCity": "C",
        "arrivalTime": "2018-08-01T01:00:00.000Z",
        "departureTime": "2018-08-01T00:00:00.000Z"
    },
    {
        "city": "B",
        "destinationCity": "F",
        "arrivalTime": "2018-08-01T01:00:00.000Z",
        "departureTime": "2018-08-01T00:00:00.000Z"
    },
    {
        "city": "C",
        "destinationCity": "D",
        "arrivalTime": "2018-08-01T01:00:00.000Z",
        "departureTime": "2018-08-01T00:00:00.000Z"
    },
    {
        "city": "E",
        "destinationCity": "C",
        "arrivalTime": "2018-08-01T02:00:00.000Z",
        "departureTime": "2018-08-01T00:00:00.000Z"
    },
    {
        "city": "B",
        "destinationCity": "A",
        "arrivalTime": "2018-08-01T00:30:00.000Z",
        "departureTime": "2018-08-01T00:00:00.000Z"
    },
    {
        "city": "E",
        "destinationCity": "A",
        "arrivalTime": "2018-08-01T02:00:00.000Z",
        "departureTime": "2018-08-01T00:00:00.000Z"
    },
    {
        "city": "C",
        "destinationCity": "A",
        "arrivalTime": "2018-08-01T02:00:00.000Z",
        "departureTime": "2018-08-01T00:00:00.000Z"
    },
    {
        "city": "C",
        "destinationCity": "B",
        "arrivalTime": "2018-08-01T01:00:00.000Z",
        "departureTime": "2018-08-01T00:00:00.000Z"
    },
    {
        "city": "F",
        "destinationCity": "B",
        "arrivalTime": "2018-08-01T01:00:00.000Z",
        "departureTime": "2018-08-01T00:00:00.000Z"
    },
    {
        "city": "D",
        "destinationCity": "C",
        "arrivalTime": "2018-08-01T01:00:00.000Z",
        "departureTime": "2018-08-01T00:00:00.000Z"
    },
    {
        "city": "C",
        "destinationCity": "E",
        "arrivalTime": "2018-08-01T02:00:00.000Z",
        "departureTime": "2018-08-01T00:00:00.000Z"
    }
]
```

</p>
</details>

## route-finder
The service uses Dijkstra's algorithm to build search tree from specified city

Endpoints:
```    GET /api/find-routes?originCity={city}``` - returns search result for specified ```{city}```. Response json contains two lists: the first one includes search result based on departure and arrival time difference and the second has results based on number of connections between cities

## Dependencies

Here is a list of dependences and explanation of their perpouse



### crf-parent 
These dependencies are used in all child projects

1. ```org.springframework.boot:spring-boot-starter-aop:jar:1.5.15.RELEASE:compile``` - Enables Spring AOP and AspectJ weaving for inserting pointcuts and aspects. Used by Spring Retry
1. ```org.springframework.boot:spring-boot-starter-test:jar:1.5.15.RELEASE:test``` - Imports both Spring Boot test modules, which are ```spring-boot-test``` contains core items and ```spring-boot-test-autoconfigure``` supports auto-configuration for tests, as well as JUnit, AssertJ, Hamcrest, and a number of other useful libraries.
1. ```org.springframework.retry:spring-retry:jar:1.2.2.RELEASE:compile``` - Allows to repeat method invocation in case of exception. Including this dependency enables retry properties for spring cloud config

### cities-storage 

1. com.h2database:h2:jar:1.4.197:runtime - allows to run either embedded or use remote H2 servers
1. ```io.springfox:springfox-swagger-ui:jar:2.9.2:compile``` - Enables functionality to visualize and try out Json API provided by the application
1. ```io.springfox:springfox-swagger2:jar:2.9.2:compile``` - This dependency allows to parse ```@RequestMapping`` annotations and their derivatives in addition to swagger2 annotations. Creates API docs json
1. ```org.springframework.boot:spring-boot-starter-data-jpa:jar:1.5.15.RELEASE:compile``` - provides Hibernate dependencies which implement JPA specification
1. ```org.springframework.boot:spring-boot-starter-web:jar:1.5.15.RELEASE:compile``` - Enables full stack web development with Tomcat and Spring MVC. Allows us to use ```@RestController``` and ```@RequestMapping``` annotations
1. ```org.springframework.cloud:spring-cloud-starter-config:jar:1.4.4.RELEASE:compile``` - Provides Spring Cloud Config Client which allows us to use remote Spring Boot configurations
1. ```org.springframework.cloud:spring-cloud-starter-netflix-eureka-client:jar:1.4.5.RELEASE:compile``` - Provides client for service discovery, which provides service metadata(e.g. hostname) by logical service name. Also, registers this service inside Eureka

### route-finder 

1. ```io.springfox:springfox-swagger-ui:jar:2.9.2:compile``` - Enables functionality to visualize and try out Json API provided by the application
1. ```io.springfox:springfox-swagger2:jar:2.9.2:compile``` - This dependency allows to parse ```@RequestMapping`` annotations and their derivatives in addition to swagger2 annotations. Creates API docs json
1. ```org.springframework.boot:spring-boot-starter-web:jar:1.5.15.RELEASE:compile``` - Enables full stack web development with Tomcat and Spring MVC. Allows us to use ```@RestController``` and ```@RequestMapping``` annotations
1. ```org.springframework.cloud:spring-cloud-starter-config:jar:1.4.4.RELEASE:compile``` - Provides Spring Cloud Config Client which allows us to use remote Spring Boot configurations
1. ```org.springframework.cloud:spring-cloud-starter-netflix-eureka-client:jar:1.4.5.RELEASE:compile``` - Provides client for service discovery, which provides service metadata(e.g. hostname) by logical service name. Also, registers this service inside Eureka
1. ```org.springframework.cloud:spring-cloud-starter-openfeign:jar:1.4.5.RELEASE:compile``` - Declarative REST clients, just like in Spring Data Repositories implements annotated interfaces. Build-in support of client side load balancing Ribbon. It is used to make calls to ```cities-storage``` service

### api-gateway 

1. ```io.springfox:springfox-swagger-ui:jar:2.9.2:compile``` - Enables functionality to visualize and try out Json API provided by the application
1. ```io.springfox:springfox-swagger2:jar:2.9.2:compile``` - Enables swagger configuration on the project
1. ```org.springframework.cloud:spring-cloud-starter-config:jar:1.4.4.RELEASE:compile``` - Provides Spring Cloud Config Client which allows us to use remote Spring Boot configurations
1. ```org.springframework.cloud:spring-cloud-starter-netflix-eureka-client:jar:1.4.5.RELEASE:compile``` - Provides client for service discovery, which provides service metadata(e.g. hostname) by logical service name. Also, registers this service inside Eureka
1. ```org.springframework.cloud:spring-cloud-starter-netflix-zuul:jar:1.4.5.RELEASE:compile``` - Provides Gateway, programmable routing. Used as client facing application for ```cities-storage```, ```route-finder``` and ```service-registry`` services

### cloud-config 

1. ```org.springframework.cloud:spring-cloud-config-server:jar:1.4.4.RELEASE:compile``` - It enables configuration server which is centralized point for other services for fetching their configurations. Also, can use git repository as a config source(currently this feature is not used)
1. ```org.springframework.cloud:spring-cloud-starter-netflix-eureka-client:jar:1.4.5.RELEASE:compile``` - Provides client for service discovery, which provides service metadata(e.g. hostname) by logical service name. Also, registers this service inside Eureka

### service-registry 

1. ```org.springframework.cloud:spring-cloud-starter-config:jar:1.4.4.RELEASE:compile``` - Provides Spring Cloud Config Client which allows us to use remote Spring Boot configurations
1. ```org.springframework.cloud:spring-cloud-starter-netflix-eureka-server:jar:1.4.5.RELEASE:compile``` - Provides service registry server(Eureka) which stores IP addresses of registered services. Other services can use it to fetch IP list related specific service name.
