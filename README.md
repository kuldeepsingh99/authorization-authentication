# Spring Authorization and Authentication example

This example can be used to develop Authorization Server and Microservice using JWT Token. This example features Authentication and Authorization. It has two maven project "Auth" and "Customer". Both the project shares a common ke.

# Auth:- project features #
1. Login Service (generating JWT Token) 
2. Refresh Service (generating new JWT Token)
3. Registration Service ( Adding new User)

# Customer:-  Project features #
1. Authorization :- checking whether token expires or not 
2. Authentication :- cheking whether the resource user is trying to access has enough Permiession/Role

## Steps to Install ##

1. Execute tables.sql in MYSQL DB, it will create Database and two tables.
2. Import both the maven project to eclipse or any other tool.
3. Run mvn clean install one by one on both the projects.
4. In Auth Project run this file **com.portal.auto.SpringBootWebApplication.java**  (it will start the server in 8080 port)
5. In Customer Project run this file **com.portal.auto.CustomerApplication.java** (it will start the server in 9090 port)

# End Points #

### Register ###

POST /register HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Cache-Control: no-cache
Postman-Token: 6c4fb8a3-8eae-c3b7-7cfb-3afa56d1acd5

**{"name":"hello","password":"123456","confirmPassword":"123456"}**

In the respose you will get Sucess or Failure JSON Message.

Ex.
{
  "flag": "success",
  "role": null,
  "message": "User Registered Successfully, please login to continue"
}

After Registration you will notice that It will create a record in users table with default ROLE and it will also create two records in usertoken table with two different usertype (WEB, ANDRIOD). 

**Note:- We need to store token for different devices seperately, we can modify this flow if we need.**  
 
### Login ###

POST /login HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Cache-Control: no-cache
Postman-Token: 6e8ba103-3a95-83c3-7a95-694ec2cbfa12

**{"username":"hello","password":"123456","userType":"WEB"}**

Response
{
    "flag": "success",
    "role": "ROLE_USER",
    "message": "eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiI2ZTdjMmU1ZmI0MjY0NDc5YjY2ZmUyOGVhOWUyMDZiZiIsInN1YiI6ImFwb3N0ZWsiLCJpYXQiOjE1MTIxMTcyNTMsImV4cCI6MTUxMjExNzMxMywiaXNzIjoia3VsZGVlcC5jb20iLCJyb2xlIjoiUk9MRV9VU0VSIn0.e-Qdw5H8FpWpN7cNrZxDt7XS7hjL8CH7w9gExxEJzeHNSKYL9RTafWcGO9yhXB3-R3xjKMjmq2gva0fXMJIOOg"
}

It will return the access token on the response message as well in the header also.

### Refresh ### 

POST /refresh HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Cache-Control: no-cache
Postman-Token: 79508fec-630b-c5fb-3a11-3fd65105f1d0

{"username":"hello","token":"eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxZDk4NGI2MTJmYjc0ZDg0YjBjZDEyM2ZlNzIzMDQ0NCIsInN1YiI6ImFkbWluIiwiaWF0IjoxNTEyMTE2MTk1LCJleHAiOjE1MTIxMTYyNTUsImlzcyI6Imt1bGRlZXAuY29tIiwicm9sZSI6IkFETUlOIn0.Ni6c9I12_QI634quXMl_OE1buucV92yLv5tj4bXL-j5KfMk68K3z4r7Q1pHQedMKPllWOvL0Ixw71JH0PWj9Hw","userType":"WEB"}

Here we need to send the current token along with the user, else you won’t be able to get new token.









