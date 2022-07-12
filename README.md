To run the docker image
--------------------------------

1. go to current path of the project and run below command to build docker image.
2. docker build -t recipedemo .
3. to run use command => docker run -p8080:8080 recipedemo

Health Check url
-------------------------
1. http://localhost:8080/recipe/actuator
2. http://localhost:8080/recipe/actuator/custom-health