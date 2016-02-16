Giftly
-

Example application that tracks what is popular and recommends. Work in progress.

###

Use files from setup folder to compose environment. Use configuration repository to configure required paths.

### Quick start with docker

Build and deploy images to docker host

```
./docker-build.sh
```

Run services with using docker-compose

```
./docker-compose up
```

### gift actions

Add gift

```
curl -i -X POST -H "Content-Type: application/json" -d '{"name":"start wars t-shirt medium orange"}' http://$GIFTLY_GATEWAY_HOST:8080/gifts/

curl http://$GIFTLY_GATEWAY_HOST:8080/gifts/names
```

Use elastic search directly:

```
curl "http://$ELASTIC_HOST:9200/giftly/_search?q=name:orange"
```

### like actions

Start ingesting data:

```
./spark-submit --class org.koko.like.LikesIngest /home/ec2-user/giftly-data-stream-0.0.1-SNAPSHOT.jar $CASSANDRA_HOST $SPARK_HOST $KAFKA_BROKERS
```

Place some likes:

```
curl -i -X POST -H "Content-Type: application/json" -d '{"giftId":"gift1", "userId":"user1", "like_time":"2016-01-01 20:10:12", "liked":"1"}' http://$GIFTLY_GATEWAY_HOST:8080/likes/
curl -i -X POST -H "Content-Type: application/json" -d '{"giftId":"gift2", "userId":"user1", "like_time":"2016-01-01 20:10:42", "liked":"1"}' http://$GIFTLY_GATEWAY_HOST:8080/likes/
curl -i -X POST -H "Content-Type: application/json" -d '{"giftId":"gift1", "userId":"user2", "like_time":"2016-01-01 20:20:12", "liked":"1"}' http://$GIFTLY_GATEWAY_HOST:8080/likes/
```

Run daily batch:

```
./spark-submit --class org.koko.like.LikesDaily /home/ec2-user/giftly-data-stream-0.0.1-SNAPSHOT.jar "2016-01-01" $CASSANDRA_HOST $SPARK_HOST
```

Check current likes, produced top and daily stats in Cassandra.