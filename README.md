# Car Parking
Parking Calculating System

Business description:
* Designing and implementing an API-only application that returns the closest car
parks to a user, together with each parking lot’s availability.
  * Car park information 
    - Dataset: https://data.gov.sg/dataset/hdb-carpark-information
    - This dataset provides detailed information about each car park. This can be treated as static and is to
    be loaded with a task from the CSV file provided in the link above. You can check it into the repository
    and do not have to automate the downloading and updating of this data.
  * Car park availability
    - Dataset: https://data.gov.sg/dataset/carpark-availability
    - The API endpoint in that link provides live updates on the parking lot availability for the car parks.
    Create a task to call the API and save this data in the database. Scheduling the task is out of the scope
    of this project, but if manually run, the same task should be able to update existing data with newer
    data from the API if run periodically.
* Business Requirements
  - The endpoint should take the url parameters latitude and longitude, and return a JSON array of
  car parks sorted by distance ascending with the total and available parking lots.


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
