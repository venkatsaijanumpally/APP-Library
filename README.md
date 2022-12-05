The application that we created is of Library Management system. The front end is created using react js and the middleware is written in java and with the database using mongodb.


Before starting the application we have to first run the java middleware main function

The file is present in below location

APP-Library/src/main/java/org/library/Controller/Application.java

The application is a maven project. So we have to have the maven configured in the system

mvn build

will build the maven project 

mvn clean install

will install all the dependencies and create the jar file which will start the application on the port 8000 which we have explicitly mentioned in the code

The above commands establishes connectivity between the database and middleware.

Now we can run the frontend application using

npm start

which will start the application on port 3000 and the complete application will be up and running 
