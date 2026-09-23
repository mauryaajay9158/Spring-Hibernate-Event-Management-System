# Event Management System

Simple Event Management REST API built using:

- Spring MVC
- Hibernate
- H2 Database
- Maven
- REST API
- Postman

This is a traditional Spring project. It does NOT use Spring Boot.

## Project structure

src/main/java/com/eventmanagement

    controller
        EventController.java

    service
        EventService.java
        EventServiceImpl.java

    dao
        EventDao.java
        EventDaoImpl.java

    entity
        Event.java

Spring configuration is in:

src/main/webapp/WEB-INF/event-servlet.xml

## Requirements

- Java 8 or newer
- Maven
- Tomcat 9
- Eclipse/IntelliJ/VS Code
- Postman

## Run the project

1. Import the project as an existing Maven project.
2. Run:

   mvn clean package

3. Deploy the generated WAR file to Tomcat 9.

The WAR file will be:

target/event-management.war

Start Tomcat and use:

http://localhost:8080/event-management

## Postman testing

### 1. Add event

POST

http://localhost:8080/event-management/events

Body -> raw -> JSON

{
    "name": "Java Developer Meetup",
    "location": "Mumbai",
    "organizer": "Tech Club",
    "eventDate": "2026-10-10T10:30:00",
    "description": "A meetup for Java developers"
}

Expected response:

Event added successfully

### 2. Get all events

GET

http://localhost:8080/event-management/events

### 3. Get event by id

GET

http://localhost:8080/event-management/events/1

### 4. Update event

PUT

http://localhost:8080/event-management/events/1

Body:

{
    "name": "Java Developer Meetup Updated",
    "location": "Navi Mumbai",
    "organizer": "Tech Club",
    "eventDate": "2026-10-12T11:00:00",
    "description": "Updated Java developer meetup"
}

### 5. Delete event

DELETE

http://localhost:8080/event-management/events/1

## H2

The project uses an in-memory H2 database.

JDBC URL:

jdbc:h2:mem:eventdb;DB_CLOSE_DELAY=-1

Username:

sa

Password:

empty

The database is created when the application starts.

Hibernate creates/updates the EVENT table automatically using:

hibernate.hbm2ddl.auto=update

## Important

Because this is an in-memory database, the data will be lost when the application/Tomcat is restarted.

This project intentionally keeps the structure simple:

Controller -> Service -> DAO -> Hibernate -> H2
