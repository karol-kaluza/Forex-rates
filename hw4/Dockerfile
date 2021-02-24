FROM openjdk:15
EXPOSE 8080
#WORKDIR /app
COPY target/hw3-0.0.1-SNAPSHOT.jar hw3.jar
COPY DAT_MT_EURUSD_M1_202011.csv DAT_MT_EURUSD_M1_202011.csv
CMD ["java","-jar","hw3.jar"]