Giftly app
-

Gift recommendations

Mongo should be running. One can be started using mongod. Connection parameters are configured via properties.

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

There should be 2 instances displayed as running on Eureka site http://localhost:8761/

Giftly service is accessed via client api gateway. Ribbon performs round robin balancing of requests between service instances.

Start service client on default port 8080:

```
cd giftly-service-client
mvn spring-boot:run
```

List gifts

curl http://localhost:8080/gifts