# TestTaskRESTful
Easy task Spring RESTful

After the application is deployed, "tasttask" database has the "user" and "room" collections.

"user" collection has:

{ "username" : "admin", "password" : "$2a$08$pBtFmplNh.k.f76Jc7hvsOXVc1b4k1SmbGwzMF6aAPDfmeXs9p/Vm", "roles" : [ "USER", "ADMINISTRATOR" ], "_class" : "by.babanin.testtask.entity.User" } - password: a

{ "username" : "user", "password" : "$2a$08$MYGk61Ew7BPVO6L9grtPZ.2AX7j93bk20u1JRc3NnQOaD8t4D8.qy", "roles" : [ "USER" ], "_class" : "by.babanin.testtask.entity.User" } - password: u

Username and password can be changed before deploying in file application.properties

Requests to the server:

1 Administrator Functions

1.1 Setting a work schedule of the hotel. If you do not set the schedule of the hotel, then some functions of the application will not be available. Change the schedule of the hotel is possible only after end of the hotel.

http://localhost:8080/workschedule
Mathod: POST
Header: Content-Type="application/json"
Body: {"workSchedule":{"start":"2019-07-02 12:00:00","finish":"2019-07-15 13:00:00"}}

1.2 Create a room. Required fields are number and price. If there are no required fields, you will get an error. It is forbidden to create rooms with the same numbers.

http://localhost:8080/room
Mathod: POST
Header: Content-Type="application/json"
Body: {"number":"1002", "amountOfBeds":2, "price":15.2}

1.3 Update a room. Required fields are number and price. If there are no required fields, you will get an error. It is forbidden to update rooms with the same numbers.

http://localhost:8080/room/{_id}
Mathod: PUT
Header: Content-Type="application/json"
Body: {"number":"1002", "amountOfBeds":3, "price":30}

The administrator has user functions.

2 User Functions

2.1 User registration

http://localhost:8080/registration
Mathod: POST
Header: Content-Type="application/json"
Body: {"username":"Babanin Dmitry","password":"dima"}

2.2 Login

http://localhost:8080/login?username=user&password=u
Mathod: POST

2.3 Getting a work schedule of the hotel.

http://localhost:8080/workschedule
Mathod: GET

2.4 Getting a free rooms.

http://localhost:8080/rooms
Mathod: GET
