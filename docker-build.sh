#!/usr/bin/env bash

cd giftly-config-server
mvn clean package docker:build
cd -

cd giftly-discovery-service
mvn clean package docker:build
cd -

cd giftly-service
mvn clean package docker:build
cd -

cd giftly-gateway
mvn clean package docker:build
cd -

cd giftly-hystrix-dashboard
mvn clean package docker:build
cd -

echo "images built"