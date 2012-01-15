# Skiffin

Provides who's in and who's out information for The Skiff.

## TODO

* Add persistence backend (it's all in memory for now)
* Deploy to Heuroku
* Add a UI
* Think about security - the end points are open to all
* Wire up to 4sq?
* Wire up to RFID and put reading in the door
* Add location (WERKS or SKIFF)


## Running

This is a Lift web app.  

    $ ./sbt
    sbt> container:start

Then `http://127.0.0.1:8080/`


## API v1

All urls prefixed with `/api/v1` (apologies to REST purists)

### Get a list of everyone

	GET /api/vi/people

E.g.,

    curl -v -X GET -H 'Content-type: text/json' http://127.0.0.1:8080/api/v1/people

Returns:

    {
      "count":2,
      "people":
      [ { "email":"richard@dallaway.com",
          "in":true,
          "when":1326562010157
        }, 
        { … }
      ]
    }

The `{email:string,in:bool,when:utc}` JSON is referred to as the "person representation" below.


## Get a specific person

    GET /api/v1/{email}

E.g.,

    curl -v -X GET -H 'Content-type: text/json' http://127.0.0.1:8080/api/v1/richard@dallaway.com

Returns the person representation shown above or 404 if the person is not known.

## Post a status change

    POST /api/v1/{email} with data of {"in": true or false}

E.g.,

    curl -v -X POST -d '{ "in" : true }' -H 'Content-type: text/json' http://127.0.0.1:8080/api/v1/richard@dallaway.com

Returns the person representation, creating the person if they don't already exist.




