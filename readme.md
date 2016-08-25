# Tools & libraries used

1) Spring Boot - most configuration is done automatically out of the box with boot wiring together all the common configuration classes you would have to create.

2) Spring Libraries (JPA/Hibernate, Security, MVC) - Spring libraries integrate well with each other, they are commonly used, have good documentation (spring & community) and easy to use and understand.

3) Embedded DB - Much easier to use the native filtering tools supplied by a database than using a flat file and writing sorting logic.

4) JUnit / Hamcrest / TDD - Much easier to write unit tests up front, and let the code mostly be generated from the tests.  End up with better, more tested code, than going back and adding retrospective tests.

5) SLF4J / Logback - The standard logging libraries, logback is an improvement of log4j v1 and slf4j ties together all available logging frameworks.  

# Build & Run the application

mvnw clean install

cd target

java -jar football-microservice-0.0.1-SNAPSHOT.jar --server.port=9000

# Add a team

curl -u football:4a923980-baa8-4f59-9dfc-53be9943bfd0 -i -X POST -H 'Content-Type: application/json' -d '{"name":"Newcastle","city":"Newcastle","owner":"Mike Ashley","capacity":75000,"competition":"FA Cup","noOfPlayer":100,"dateCreated":"2015-04-01"}' 'localhost:9000/teams'

curl -u football:4a923980-baa8-4f59-9dfc-53be9943bfd0 -i -X POST -H 'Content-Type: application/json' -d '{"name":"Manchester United","city":"Manchest","owner":"Malcom Glazer","capacity":89000,"competition":"FA Cup","noOfPlayer":100,"dateCreated":"2014-03-01"}' 'localhost:9000/teams'

# Get all teams
curl -u football:4a923980-baa8-4f59-9dfc-53be9943bfd0 'localhost:9000/teams'

# Get all teams sorted by capacity

curl -u football:4a923980-baa8-4f59-9dfc-53be9943bfd0 'localhost:9000/teams?sort=capacity&direction=asc'

curl -u football:4a923980-baa8-4f59-9dfc-53be9943bfd0 'localhost:9000/teams?sort=capacity&direction=desc'

# Get a specific team
curl -u football:4a923980-baa8-4f59-9dfc-53be9943bfd0 'localhost:9000/teams/newcastle'

# docker

This build creates a docker image by running

mvnw package docker:build 

You can pull the docker file from my docker hub, just change the ports above to match the one mapped by docker

https://hub.docker.com/r/a1dutch/football-microservice/

Alternatively you can run the command below with the correct mapped ports as above

docker run -p 9000:8080 -t a1dutch/football-microservice