FROM java:8
VOLUME /tmp
ADD giftly-hystrix-dashboard-0.0.1-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 8010
ENTRYPOINT ["java","-jar","/app.jar"]
