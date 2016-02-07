Giftly
-

Giftly tracks what is popular and recommends

There are some apps that should be up and running.

Gifts are stored in [MongoDB](https://github.com/inlight-media/docker-mongodb-replica-set). [Elasticsearch](https://github.com/dockerfile/elasticsearch) relies on [Mongo connector](https://github.com/mongodb-labs/mongo-connector) to keep search data up to date. Likes are logged to [Kafka](https://github.com/wurstmeister/kafka-docker). [Spark](https://github.com/sequenceiq/docker-spark) moves the data and performs runtime computations. [Cassandra](https://github.com/pokle/cassandra) holds data prepared for reporting. [Zipkin](https://github.com/openzipkin/zipkin) is used for tracing.

### build project

```
mvn clean install
```

### quick start with mvn spring-boot

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

### gift actions

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

### like actions

Start ingesting data:

```
./spark-submit --class org.koko.like.LikesIngest /home/ec2-user/giftly-data-stream-0.0.1-SNAPSHOT.jar "127.0.0.1" "local" "ec2-52-28-34-63.eu-central-1.compute.amazonaws.com:32791"
```

Place some likes:

```
curl -i -X POST -H "Content-Type: application/json" -d '{"giftId":"gift1", "userId":"user1", "like_time":"2016-01-02 20:10:12", "liked":"1"}' http://localhost:8080/likes/
curl -i -X POST -H "Content-Type: application/json" -d '{"giftId":"gift2", "userId":"user1", "like_time":"2016-01-02 20:10:42", "liked":"1"}' http://localhost:8080/likes/
curl -i -X POST -H "Content-Type: application/json" -d '{"giftId":"gift1", "userId":"user2", "like_time":"2016-01-02 20:20:12", "liked":"1"}' http://localhost:8080/likes/
```

Run daily batch:

```
./spark-submit --class org.koko.like.LikesDaily /home/ec2-user/giftly-data-stream-0.0.1-SNAPSHOT.jar "2016-01-02" "127.0.0.1" "local"
```

Check current likes, produced top and daily stats in Cassandra.

### monitor and trace

Check if all instances are properly registered and displayed in Eureka on port 8761.

Check Hystrix dashboard in browser to track stream from service client:

```
http://localhost:8010/hystrix/monitor?stream=http://localhost:8080/hystrix.stream
```

Use Zipkin web to trace spans that are sent by Sleuth to the Zipkin collector using Thrift.


### about core services

Giftly uses Spring cloud and Netflix OSS components as core services. [Eureka](https://github.com/Netflix/eureka) server enables instance discovery. [Zuul](https://github.com/Netflix/zuul) acts as gateway for main service instances that process requests in backend. [Ribbon](https://github.com/Netflix/ribbon) performs round-robin balancing of GET requests between service instances.