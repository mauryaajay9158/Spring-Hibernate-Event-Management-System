# Event Management System

A simple Event Management REST API built using:

* Spring MVC
* Hibernate
* H2 Database
* Maven
* REST API
* Postman

This is a **traditional Spring project**. It does **not use Spring Boot**.

---

## Project Structure

```text
EventManagementSpringHibernate
│
├── pom.xml
│
└── src
    └── main
        ├── java
        │   └── com.eventmanagement
        │       │
        │       ├── controller
        │       │   └── EventController.java
        │       │
        │       ├── service
        │       │   ├── EventService.java
        │       │   └── EventServiceImpl.java
        │       │
        │       ├── dao
        │       │   ├── EventDao.java
        │       │   └── EventDaoImpl.java
        │       │
        │       └── entity
        │           └── Event.java
        │
        └── webapp
            └── WEB-INF
                ├── web.xml
                └── event-servlet.xml
```

---

## Technologies Used

| Technology | Purpose               |
| ---------- | --------------------- |
| Java       | Programming language  |
| Spring MVC | Web framework         |
| Hibernate  | ORM                   |
| H2         | Database              |
| Maven      | Dependency management |
| Tomcat 9   | Application server    |
| REST API   | Communication         |
| Postman    | API testing           |

---

## Requirements

Install the following before running the project:

* Java 8 or higher
* Maven
* Apache Tomcat 9
* Eclipse / IntelliJ IDEA / VS Code
* Postman

---

# How the Project Works

The project follows a simple layered architecture:

```text
Postman
   ↓
EventController
   ↓
EventService
   ↓
EventDao
   ↓
Hibernate
   ↓
H2 Database
```

### Controller

Handles HTTP requests such as:

```text
GET
POST
PUT
DELETE
```

### Service

Contains the application/business logic.

### DAO

Handles database operations using Hibernate.

### Entity

Represents the `Event` table in the database.

---

# Event Entity

The Event class contains:

```text
id
name
location
organizer
eventDate
description
```

Example:

```json
{
    "name": "Java Developer Meetup",
    "location": "Mumbai",
    "organizer": "Tech Club",
    "eventDate": "2026-10-10T10:30:00",
    "description": "A meetup for Java developers"
}
```

---

# Database

The project uses an **in-memory H2 database**.

Database URL:

```text
jdbc:h2:mem:eventdb;DB_CLOSE_DELAY=-1
```

Username:

```text
sa
```

Password:

```text
```

The database does not require a separate installation.

Hibernate automatically creates the required table.

Configuration:

```xml
<prop key="hibernate.hbm2ddl.auto">update</prop>
```

Because the database is in-memory, the data will be lost when the application/Tomcat is restarted.

---

# Running the Project

## Step 1: Import the Project

Import the project into Eclipse as:

```text
Existing Maven Project
```

Maven will download the required dependencies from `pom.xml`.

---

## Step 2: Build the Project

Open the terminal inside the project directory and run:

```bash
mvn clean package
```

After successful compilation, Maven will create:

```text
target/event-management.war
```

---

## Step 3: Deploy to Tomcat

Copy:

```text
event-management.war
```

into:

```text
apache-tomcat-9/webapps/
```

Start Tomcat.

The application will be available at:

```text
http://localhost:8080/event-management
```

---

# Postman API Testing

Base URL:

```text
http://localhost:8080/event-management
```

---

## 1. Add Event

### Request

```text
POST /events
```

Full URL:

```text
http://localhost:8080/event-management/events
```

In Postman:

```text
Body
→ raw
→ JSON
```

Send:

```json
{
    "name": "Java Developer Meetup",
    "location": "Mumbai",
    "organizer": "Tech Club",
    "eventDate": "2026-10-10T10:30:00",
    "description": "A meetup for Java developers"
}
```

Expected response:

```text
Event added successfully
```

Status:

```text
201 Created
```

---

# 2. Get All Events

### Request

```text
GET /events
```

URL:

```text
http://localhost:8080/event-management/events
```

Example response:

```json
[
    {
        "id": 1,
        "name": "Java Developer Meetup",
        "location": "Mumbai",
        "organizer": "Tech Club",
        "eventDate": "2026-10-10T10:30:00",
        "description": "A meetup for Java developers"
    }
]
```

---

# 3. Get Event By ID

### Request

```text
GET /events/{id}
```

Example:

```text
GET http://localhost:8080/event-management/events/1
```

If the event exists, the event will be returned.

If it does not exist:

```text
Event not found
```

Status:

```text
404 Not Found
```

---

# 4. Update Event

### Request

```text
PUT /events/{id}
```

Example:

```text
PUT http://localhost:8080/event-management/events/1
```

Body:

```json
{
    "name": "Java Developer Meetup Updated",
    "location": "Navi Mumbai",
    "organizer": "Tech Club",
    "eventDate": "2026-10-12T11:00:00",
    "description": "Updated Java developer meetup"
}
```

Expected response:

```text
Event updated successfully
```

---

# 5. Delete Event

### Request

```text
DELETE /events/{id}
```

Example:

```text
DELETE http://localhost:8080/event-management/events/1
```

Expected response:

```text
Event deleted successfully
```

---

# API Summary

| Method | URL            | Operation       |
| ------ | -------------- | --------------- |
| POST   | `/events`      | Add event       |
| GET    | `/events`      | Get all events  |
| GET    | `/events/{id}` | Get event by ID |
| PUT    | `/events/{id}` | Update event    |
| DELETE | `/events/{id}` | Delete event    |

---

# Hibernate Flow

When a new event is added:

```text
Postman
   ↓
POST /events
   ↓
EventController
   ↓
EventService
   ↓
EventDao
   ↓
Hibernate Session
   ↓
H2 Database
```

For example:

```java
getSession().save(event);
```

Hibernate converts the Java object into an SQL `INSERT` operation.

Similarly:

```java
getSession().get(Event.class, id);
```

is used to retrieve an event.

---

# Important Configuration

The Hibernate configuration is located in:

```text
src/main/webapp/WEB-INF/event-servlet.xml
```

The database configuration is:

```xml
<bean id="dataSource"
      class="org.springframework.jdbc.datasource.DriverManagerDataSource">

    <property name="driverClassName" value="org.h2.Driver"/>

    <property name="url"
              value="jdbc:h2:mem:eventdb;DB_CLOSE_DELAY=-1"/>

    <property name="username" value="sa"/>

    <property name="password" value=""/>

</bean>
```

Hibernate is configured using:

```xml
<bean id="sessionFactory"
      class="org.springframework.orm.hibernate5.LocalSessionFactoryBean">
```

and:

```xml
<prop key="hibernate.dialect">
    org.hibernate.dialect.H2Dialect
</prop>
```

---

# Important Note

This project is intentionally a **simple Spring MVC + Hibernate project**.

It does not use:

```text
Spring Boot
Spring Data JPA
application.properties
application.yml
```

Instead, it uses traditional Spring configuration:

```text
web.xml
event-servlet.xml
```

and Hibernate directly through:

```text
SessionFactory
Session
```

The main flow is:

```text
Spring MVC
    ↓
Controller
    ↓
Service
    ↓
DAO
    ↓
Hibernate
    ↓
H2
```
