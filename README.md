# Skiffin

Provides who's in and who's out information for The Skiff.

## Running

This is a Lift web app.  

$ ./sbt
sbt> container:start

Then http://127.0.0.1:8080/

If you're hacking, 


## API

curl -v -X GET -d '{}' -H 'Content-type: text/json' http://127.0.0.1:8080/api/v1/people


curl -v -X GET -d '{}' -H 'Content-type: text/json' http://127.0.0.1:8080/api/v1/richard@dallaway.com

curl -v -X POST -d '{ "in" : "true" }' -H 'Content-type: text/json' http://127.0.0.1:8080/api/v1/richard@dallaway.com



