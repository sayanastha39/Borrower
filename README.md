LMS Borrower

LMS is a Library Management System application built using java as a programming language. In this application I made a microservice for role Borrower. 

The application's concern is separated using MVC architecture. It is a design pattern that separates the business logic, presentation logic and data.

Entities: Entities package has all the plain old java object classes used in this applications. It has generated constructors, getters, setters, toString, hashCodes methods for increasing the readability and re-usability of a program.

DAO: Using Spring boot was very helpful as it is a J2EE framework that takes care of boilerplates and controls the application. I took advantage of this feature and implemented JPA Repository while working with data and objects. I used Hibernate because it is the most common JPA provider. It allowed me to load and save Java objects without writing queries. 

Service: Service class package has the service class and controller method that will communicate with both View and DAO. This is where the business logic of the application is written. CRUD operations for different entities are written here. Cacade functionality of SQL is also done in this service class. For example: if we delete an author then the book associated with that author is aloso deleted. I used Hibernate because it is the most common JPA provider. It allowed me to load and save Java objects without writing queries.I took advantage of Java 8 features like lambda, forEach method, Collections and generics to create classes not tied to one datatype. 

Controller: This package represents the presenation of the application. In the Controller folder, i had the rest endpoints mentioned for the application. I had two different controllers: for checking book out and returning book. To checkout book; it woukld first get cardNo, branchId, bookId and see if they have previously checkout out book and have not returned it. If all these scenarios matches the criteria then the owner of that cardId is allowed to checkout book else will receive the message sent by exception handling.

Similarly to return book, the borrower has to enter cardNo, branchId and bookId and if they have checkoud book they are allowed to return book. The Spring application also had content negotiation. 
