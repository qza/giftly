Giftly app
-

Giftly

Giftly - best recommendations for gifts since ever

Mongo and Redis should be running. Gifts are served from Mongo and Redis acts as backend for messaging between api gateway and service. All connection parameters are configured via properties.

First clean and build the project:

```
mvn clean install
```

Giftly uses git base configuration repository located at: https://github.com/qza/giftly-config-repo.

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

Redis monitor shoud show that both instances are subscribed to queue.gifts

```
redis-cli monitor
```

Giftly service is accessed via client API gateway. Ribbon performs round robin balancing of GET requests between service instances.

Start service client on default port 8080:

```
cd giftly-service-client
mvn spring-boot:run
```

Check if all instances are properly registered and displayed in Eureka http://localhost:8761/

List gifts

curl http://localhost:8080/gifts/names

Add gift

curl -i -X POST -H "Content-Type: application/json" -d '{"name":"start wars t-shirt medium orange"}' http://localhost:8080/gifts/

curl http://localhost:8080/gifts/names