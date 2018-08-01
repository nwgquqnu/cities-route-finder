# cities-route-finder
Backend microservices example

##cities-storage
This service has following endpoints:

    GET  /api/travel-schedule/find-by-city/{city} - returns all results from DB where city field is {city}
    GET  /api/travel-schedule - returns all information from database
    POST /api/travel-schedule - allows to insert single entity
    DELETE /api/travel-schedule/{id} - deletes entity with id {id}
    POST /api/travel-schedule/list - allows to insert several entities

All these endpoints can be used on swagger UI page 
Here is an example of request body for ```POST /api/travel-schedule/list``` request
```
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

##route-finder
The service uses Dijkstra's algorithm to build search tree from specified city

Endpoints:
    GET /api/find-routes?originCity={city} - returns search result for specified {city}. Response json contains two lists: the first one includes search result based on departure and arrival time difference and the second has results based on number of connections between cities