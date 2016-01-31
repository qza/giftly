Giftly
-

Giftly tracks what is popular and recommends

There are some apps that should be up and running. Gifts are stored in [MongoDB](https://github.com/inlight-media/docker-mongodb-replica-set). [Elasticsearch](https://github.com/dockerfile/elasticsearch) acts as search engine and relies on [Mongo connector](https://github.com/mongodb-labs/mongo-connector) to keep data up to date. Events are logged to [Kafka](https://github.com/wurstmeister/kafka-docker) . [Zipkin](https://github.com/openzipkin/zipkin) is used for tracing.

### build project

```
mvn clean install
```

### start services with spring-boot maven

Start configuration server:

```
cd giftly-config-server
mvn spring-boot:run
```

Start discovery service:

```
cd giftly-eureka-server
mvn spring-boot:run
```

Start one service instance on port 8091:

```
cd giftly-service
mvn spring-boot:run -Dserver.port=8091
```

Start another service instance on port 8092:

```
cd giftly-service
mvn spring-boot:run -Dserver.port=8092
```

Start gateway on default port 8080:

```
cd giftly-gateway
mvn spring-boot:run
```

Start Hystrix dashboard:

```
cd giftly-hystrix-dashboard
mvn spring-boot:run
```

### run some actions

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

Simulate user activity:

```
curl -i -X POST -H "Content-Type: application/json" -d '{"giftId":"68090775", "eventType":"gift-like"}' http://localhost:8080/events/
```

Check if events are written to kafka events topic:

```
./${KAFKA_HOME}/kafka-console-consumer.sh --topic events --zookeeper $ZK
```

### monitor and trace

Check if all instances are properly registered and displayed in Eureka on port 8761.

Check Hystrix dashboard in browser to track stream from service client:

```
http://localhost:8010/hystrix/monitor?stream=http://localhost:8080/hystrix.stream
```

Use Zipkin web to trace spans that are sent by Sleuth to the Zipkin collector using Thrift.


### about core services

Giftly relies on Spring cloud and Netflix OSS components for core services. [Eureka](https://github.com/Netflix/eureka) server enables instance discovery. [Zuul](https://github.com/Netflix/zuul) acts as gateway for main service instances that process requests in backend. [Ribbon](https://github.com/Netflix/ribbon) performs round-robin balancing of GET requests between service instances.