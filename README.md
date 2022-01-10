# Car Parking
Parking Calculating System

1. Requirement for running app
- Java version 8
- Maven for building project
- Database: Postgres, default running schema is 'parking'. You can change your database setting.

2. Build and run unit test: 'mvn clean install'
When you build project with mvn clean install, unit test also run and create jar file
   
3. Docker file build: There is already Docker file for building image.
I already build and push docker image publicly at https://hub.docker.com/repository/docker/vangpro1991/parking-api
   
4. To run app locally, please change 'datasource' at application.yml file. I recommend using intelliJ IDEA.

5. Test the api: We have swagger ui to play with api. Please check at http://localhost:8080/swagger-ui.html.
    - /carparks/nearest: main api for check nearest part
    - /carparks/update-car-availability: API for manually update available data
    - /carparks/update-coordinate-format: API for update coordinate system. Please DO NOT run this API. I already do it.  
5. Testing app: config and run the app is take time, so I already deploy app at AWS. Please play with: http://ec2-13-214-212-220.ap-southeast-1.compute.amazonaws.com/swagger-ui.html#/parking-controller/findNearestPathUsingGET
