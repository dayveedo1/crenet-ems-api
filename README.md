# crenet-ems-api
# Assessment Project for Backend developer role at Crenet
# Project was built using Java 8, Spring Framework, SpringBoot
# Pull this repo, and open with IntelliJ
# Run the program to generate the compiled files which can be deployed on tomcat server. Git ignored the compiled version i committed.
# Second Option, You can change to the option below and run application as shown below
# ![image](https://user-images.githubusercontent.com/62401388/160721860-3bef25d6-ada4-47ed-a75c-de1c76eeb324.png)
# An SQL script to create & populate the Database is also included in the files being committed titled: employee-ms.sql
# Run the application & visit http://localhost:4080/ems/swagger-ui/#/ to load Swagger Documentation
# Open Postman, call the endpoint http://localhost:4080/ems/api/login and enter the payload to generate a token for authentication as shown below
![image](https://user-images.githubusercontent.com/62401388/160813967-5fe10633-918c-4043-a799-d9b30ddd65e5.png)
# Goto browser, open swagger, click on Authorize and input copied access token from postman with "Bearer " prefix
![image](https://user-images.githubusercontent.com/62401388/160814294-4677f8bd-bc66-470d-abe9-06339d13f810.png)
# Make call to endpoint which are all secured & based on authorization implemented by JWT Authentication & Authorization Scheme
