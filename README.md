Giftly app
-

Gift recommendations

Mongo should be running. One can be started using mongod. Connection parameters are configured via properties.

First clean and build the project:

```
mvn clean install
```

This example is using git base configuration repository located at: https://github.com/qza/giftly-config-repo.

Start config server:

```
cd giftly-config-server
mvn spring-boot:run
```

Start service app:

```
cd giftly-service
mvn spring-boot:run
```

#### List gifts

curl http://localhost:8080/gifts


#### Get example config key

curl http://localhost:8080/config/key