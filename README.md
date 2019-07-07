# TestTaskRESTful
Easy task Spring RESTful

Task content: https://github.com/LordDetson/TestTaskRESTful/blob/master/TestTask.pdf

After the application is deployed, "tasttask" database has the "user" and "room" collections.

"user" collection has:

{ "username" : "admin", "password" : "$2a$08$pBtFmplNh.k.f76Jc7hvsOXVc1b4k1SmbGwzMF6aAPDfmeXs9p/Vm", "roles" : [ "USER", "ADMINISTRATOR" ], "_class" : "by.babanin.testtask.entity.User" } - password: a

{ "username" : "user", "password" : "$2a$08$MYGk61Ew7BPVO6L9grtPZ.2AX7j93bk20u1JRc3NnQOaD8t4D8.qy", "roles" : [ "USER" ], "_class" : "by.babanin.testtask.entity.User" } - password: u

Username and password can be changed before deploying in file application.properties

"room" collection:

{ "number" : "1000", "price" : 25.6, "_class" : "by.babanin.testtask.entity.Room" }

{ "number" : "1001", "price" : 12.84, "reservation" : { "5d220cc1f036421d98ed1286" : { "start" : ISODate("2019-07-05T15:16:18.062Z"), "finish" : ISODate("2019-07-09T15:16:18.062Z") } }, "_class" : "by.babanin.testtask.entity.Room" }

Number and price can be changed before deploying in file application.properties

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

2.4 Getting a free rooms. If the range goes beyond the schedule of the hotel, we get an error.

http://localhost:8080/rooms
Mathod: GET
Header: Content-Type="application/json"
Body: {"start":"2019-07-08 00:00:00","finish":"2019-07-12 00:00:00"}

2.5 Getting a booking fee. If the range goes beyond the schedule of the hotel, we get an error.

http://localhost:8080/room/bookingFee?number=1002&start=2019-07-08%2000:00:00&finish=2019-07-12%2000:00:00
Mathod: POST

2.6 Reservation a room. If the amount is not equal a booking fee, then we get an error.

http://localhost:8080/room/bookingFee?number=1002&start=2019-07-08%2000:00:00&finish=2019-07-12%2000:00:00&amount=120
Mathod: POST

Conclusion: The project was completed in accordance with the task. To test the application used Postman tool.
