# build & run the application

mvnw clean install
cd target
java -jar football-microservice-0.0.1-SNAPSHOT.jar --server.port=9000

# add a team
curl -u football:4a923980-baa8-4f59-9dfc-53be9943bfd0 -i -X POST -H 'Content-Type: application/json' -d '{"name":"Newcastle","city":"Newcastle","owner":"Mike Ashley","capacity":75000,"competition":"FA Cup","noOfPlayer":100,"dateCreated":"2015-04-01"}' 'localhost:9000/teams'
curl -u football:4a923980-baa8-4f59-9dfc-53be9943bfd0 -i -X POST -H 'Content-Type: application/json' -d '{"name":"Manchester United","city":"Manchest","owner":"Malcom Glazer","capacity":89000,"competition":"FA Cup","noOfPlayer":100,"dateCreated":"2014-03-01"}' 'localhost:9000/teams'

# get all teams
curl -u football:4a923980-baa8-4f59-9dfc-53be9943bfd0 'localhost:9000/teams'

# get all teams sorted by capacity
curl -u football:4a923980-baa8-4f59-9dfc-53be9943bfd0 'localhost:9000/teams?sort=capacity&direction=asc'
curl -u football:4a923980-baa8-4f59-9dfc-53be9943bfd0 'localhost:9000/teams?sort=capacity&direction=desc'

# get a specific team
curl -u football:4a923980-baa8-4f59-9dfc-53be9943bfd0 'localhost:9000/teams/newcastle'