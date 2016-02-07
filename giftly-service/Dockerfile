FROM java:8
VOLUME /tmp
ADD giftly-service-0.0.1-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 7790-7799
ENTRYPOINT ["java","-jar","/app.jar"]
