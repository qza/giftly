FROM java:8
VOLUME /tmp
ADD giftly-search-service-0.0.1-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 7600-7699
ENTRYPOINT ["java","-jar","/app.jar"]
