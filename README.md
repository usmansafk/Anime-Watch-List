# Anime Watch List (Hobby-Web-Application)


This repository withholds my second project with QA-Academy.


This full stack web application allows users to perform CRUD functionalities to create a personalized anime watch list, with an episode tracker and rating system. The application followed the Enterprise Architecture Model, with the utilisation of Java Spring framework in the back end which connected to a MySQL database or h2 instance. 


The front-end was built using HTML and CSS, setting up API calls through JavaScript; thus allowing me to get data to and from the database. 
The overall application was built using Maven as a CI Pipeline and was linked to SonarQube as means for static analysis. Unit and Integration tests were also carried by means of JUnit and Mockito for backend testing, whilst Selenium was the tool for frontend user acceptance tests. 


## Table of Contents

* [Getting Started](#getting-started)
* 



## Getting Started

Below are a set of instructions which will allow you access this project on your local machine to establish a production or development environment:
Prerequisites:

 ### Prerequisites: ### 

Software tools required and where to install them.

* Java SE 14 (or later) [(available here)](https://www.oracle.com/java/technologies/javase-downloads.html#JDK14) 

* Spring Boot [(available here)](https://spring.io/guides/gs/spring-boot/)

* Maven [(available here)](https://maven.apache.org/)

* MySql(Relational database) [(available here)]( https://www.mysql.com/downloads/)

* Eclipse (IDE)  [(available here)](https://www.eclipse.org/downloads/)

* VS Code (IDE) [(available here)](https://code.visualstudio.com/download)

* Google Chrome [(available here)](https://www.google.com/intl/en_uk/chrome/)

* PostMan [(available here)](https://www.postman.com/downloads/)


 ### Installing: ### 
 
1. Clone the repository to your system. You can directly clone this repo from this command on git:

              `git clone https://github.com/usmansafk/HWA.git`

2. Make changes in a separate branch by git checkout -b <BranchName>  (recommended)

3. Open the project with your preferred IDE, i.e. Eclipse or Intellij,  as a Maven project

4. Open front end files residing in `src/main/resources/static`  in a front end specific IDE such as Visual Studio Code (recommended)

5. Visit the /resources file to ensure, to tweak running port, selecting which profile you will like to operate in and (uncomment either test or prod) and manage your database connection  were necessary

6. Your development workspace should now be set up and you can run the application

7. To view changes head to `localhost:PORT/index.html` or to simply check API calls in Postman with the respected API call

8. Conduct tests accordingly by running either on `src/main/resources/java` or a specific class and selecting ‘Run as JUnit’

Once the spring boot application is running, you should the be go on your browser at the link  
localhost:PORT/index.html and be presented with a website similar to this:


<img width="867" alt="Screenshot 2021-05-16 at 18 23 31" src="https://user-images.githubusercontent.com/67691352/118406401-d4a4da80-b673-11eb-994c-ce7a4ec0d75f.png">


 ### Technologies: ### 
 (and Enterprise Architecture)
 
·   	Version Control System: Git

·   	Source Code Management: GitHub

·   	Kanban Board: Jira (Scrum Board)

·   	Database Management System: MySQL Server 5.7 (local or GCP instance)

·   	Back-End Programming Language: Java

·   	API Development Platform: Spring

·   	Front-End Web Technologies: HTML, CSS, JavaScript

·   	Build Tool: Maven

·   	Static Analysis: SonarQube

·   	Unit Testing: JUnit, Mockito

·   	User-Acceptance Testing: Selenium

<img width="929" alt="Screenshot 2021-05-16 at 18 23 10" src="https://user-images.githubusercontent.com/67691352/118406387-c656be80-b673-11eb-93dc-2162469934ba.png">

## Tests
### Running the tests: ### 
Coverage: 78%

Backed was tested using JUnit and Mockito and the frontend was carried out by Selenium. Static Analysis was used to produce more better cleaner code, removing 2 vulnerabilities and 1 bug and a hotspot.

### Unit Testing ###
I tested the Services classes and all the methods within using  unit testing.
Unit tests includes testing the members- attributes and methods - of these respected entities. Mockito was also used to simulate user-input to dissociate other parts of the code for more accurate testing. In this case, Mockito tests were used for the controller classes to ensure successful creation and handling of objects.

### Integration Testing ###
The Controller class required Integration testing on all the methods within. The idea it to prove that each integration of the application is functioning as expected

                INSERT TEST COVERAGE IMG



### User Acceptance Testing ### 
Selenium was essentially used  to test  the CRUD functionalities from the users perspective to ensure that the website is doing what it is doing what it is supposed to do. To launch front end user acceptance testing, initially launch the project as a SpringBoot application and run on the hosted Live Server from VS Code. Alternatively, if you do not have VS Code then you may require to modify the URL port.

                INSERT TEST SELENIUM IMG


## Features
List of features ready and TODOs for future development

### Features: ###
Create a custom anime list by inputting the name of the show

Add which current episode you are on

Provide a rating system

Make changes using edit or remove

SonarQube for static analysis

Jira Board https://usmansajid.atlassian.net/secure/RapidBoard.jspa?rapidView=3&projectKey=HWA

### To-do list: ###
ToDo:
Community Features: User Profile and Login Page

Share Button

AWL Forum

## Status 

Project MVP is complete

## Built With 
Maven - Dependency Management

## Versioning 
We use SemVer for versioning.

## Authors     
Usman Sajid - usmansafk QA Digital Consultant Software Engineer

## License
This project is licensed under the MIT license - see the LICENSE.md file fo details for help in Choosing a license

## Acknowledgments
Thank you to all the trainers at QA


