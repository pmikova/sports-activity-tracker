Rest commands:

To run rest, run mvn clean install in the top level directory.

Then, go to rest directory and run "mvn" or "mvn cargo:run"

Open another terminal and you are all set.

Get all:
curl -i -X GET http://localhost:8080/pa165/rest/users

Create:
curl -X POST -i -H "Content-Type: application/json" --data '{"userType":"USER","email":"haha@email.com","name":"fake","surname":"grown","weight":75,"gender":"MALE","birthdate":"1998-12-04","passwordHash":"blablabla"}' http://localhost:8080/pa165/rest/users

Update:
curl -i -X PUT -H "Content-Type: application/json" --data '{"name":"Lux", "weight":"70"}' http://localhost:8080/pa165/rest/users/3

Delete:
curl -i -X DELETE http://localhost:8080/pa165/rest/users/3

GetById:
curl -i -X GET http://localhost:8080/pa165/rest/users/3
