Giftly
-

Giftly uses [Redis](https://github.com/dockerfile/redis) for messaging backend between API gateway and services. [MongoDB](https://github.com/dockerfile/mongodb) serves gifts. [Elasticsearch](https://github.com/dockerfile/elasticsearch) acts as search engine and relies on [Mongo connector](https://github.com/mongodb-labs/mongo-connector) to keep data up to date. [Zipkin](https://github.com/openzipkin/zipkin) is used for tracing events.

### Start services with spring-boot maven

Clean and build the project:

```
mvn clean install
```

Giftly uses git base configuration repository

Start config server:

```
cd giftly-config-server
mvn spring-boot:run
```

Giftly uses Eureka server for instance discovery.

Start eureka server:

```
cd giftly-eureka-server
mvn spring-boot:run
```

Giftly service instances are serving gifts from Mongo.

Start service instance on port 8091:

```
cd giftly-service
mvn spring-boot:run -Dserver.port=8091
```

Start another service instance on port 8092:

```
cd giftly-service
mvn spring-boot:run -Dserver.port=8092
```

On Redis server, check with redis-cli monitor or some other tool, two instances should be subscribed to queue.gifts.

Giftly service is accessed via Zuul that acts as API gateway. Ribbon performs round-robin balancing of GET requests between service instances. POST requests are forwarded over Redis queue.

Start service client on default port 8080:

```
cd giftly-service-client
mvn spring-boot:run
```

Check if all instances are properly registered and displayed in Eureka http://localhost:8761

Giftly uses Hystrix to provide fallback when services are down.

```
cd giftly-hystrix-dashboard
mvn spring-boot:run
```

### Start all services with docker compose

TODO

### Run some actions

List gifts

```
curl http://localhost:8080/gifts/names
```

Add gift

```
curl -i -X POST -H "Content-Type: application/json" -d '{"name":"start wars t-shirt medium orange"}' http://localhost:8080/gifts/

curl http://localhost:8080/gifts/names
```

Use elastic search directly:

```
curl "http://localhost:9200/giftly/_search?q=name:orange"
```

Check Hystrix dashboard in browser to track stream from service client:

```
http://localhost:8010/hystrix/monitor?stream=http://localhost:8080/hystrix.stream
```

Use Zipkin web to trace spans that are sent by Sleuth to the Zipkin collector using Thrift.
