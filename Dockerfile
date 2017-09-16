FROM java:8
WORKDIR /
ADD product-web-service-0.1.0.jar product-web-service-0.1.0.jar
EXPOSE 8080
CMD java -jar product-web-service-0.1.0.jar

