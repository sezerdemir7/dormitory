# Dormitory Management System

This project is a Spring Boot application for managing a dormitory, providing functionality for user authentication, student management, hall management, room management, and more. It integrates services like PostgreSQL, RabbitMQ, Redis, and WebSocket.

## Table of Contents

- [Technologies](#technologies)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
    - [Authentication](#authentication)
    - [Student](#student)
    - [Hall](#hall)
    - [Room](#room)
    - [Contact Info](#contact-info)
    - [Access Log](#access-log)
    - [Cafeteria](#cafeteria)
    - [Reservation](#reservation)
    - [Teacher](#teacher)
    - [Playground](#playground)
    - [Log](#log)
    - [Image](#image)

## Technologies

- Java
- Spring Boot
- PostgreSQL
- RabbitMQ
- Redis
- WebSocket
- Docker

## Prerequisites

- Docker installed
- Java 11 or higher

## Installation

1. Clone the repository:

    ```bash
    git clone <repository-url>
    cd dormitory-management-system
    ```

2. The project is already set up to use `docker-compose` to run the necessary services: PostgreSQL, RabbitMQ, Redis, and the Spring Boot application.

## Running the Application

1. Ensure Docker is installed and running on your machine.

2. Use the provided `docker-compose.yml` file to start all the required services and the application:

    ```bash
    docker-compose up
    ```

3. The application and its dependencies will start up in the following ports:
    - **Backend**: `http://localhost:8080`
    - **PostgreSQL**: `localhost:5432`
    - **RabbitMQ**: `localhost:15672` (default user: `guest`, password: `guest`)
    - **Redis**: `localhost:6379`

4. To stop the application and services:

    ```bash
    docker-compose down
    ```

## API Endpoints

### Authentication

- **POST** `/auth/generate-verify-code` – Generate a verification code.
- **POST** `/auth/login` – Login a user.
- **POST** `/auth/register` – Register a new user.
- **POST** `/auth/verify/mail` – Verify user email.
- **GET** `/auth/welcome` – Welcome page after successful login.

### Student

- **GET** `/v1/student/all` – Retrieve all students.
- **POST** `/v1/student/contact-info` – Save contact info for a student.
- **DELETE** `/v1/student/delete/{id}` – Delete a student by ID.
- **POST** `/v1/student/image` – Upload a student's image.
- **GET** `/v1/student/oneStudent` – Retrieve details of a specific student.
- **POST** `/v1/student/save` – Save a new student.
- **POST** `/v1/student/save/contact-info` – Save contact info for a student.
- **POST** `/v1/student/save/image` – Save a student's image.
- **PUT** `/v1/student/update` – Update a student's information.

### Hall

- **GET** `/v1/hall/all` – Retrieve all halls.
- **DELETE** `/v1/hall/delete` – Delete a hall.
- **GET** `/v1/hall/oneHall` – Retrieve a specific hall.
- **POST** `/v1/hall/save` – Save a new hall.
- **PUT** `/v1/hall/update` – Update a hall's information.

### Room

- **GET** `/v1/room/all` – Retrieve all rooms.
- **DELETE** `/v1/room/delete` – Delete a room.
- **GET** `/v1/room/oneRoom` – Retrieve details of a specific room.
- **POST** `/v1/room/save` – Save a new room.
- **PUT** `/v1/room/update` – Update room information.

### Contact Info

- **DELETE** `/v1/contact-info/delete` – Delete contact info.
- **GET** `/v1/contact-info/oneContactInfo` – Retrieve contact info.
- **PUT** `/v1/contact-info/update` – Update contact info.

### Access Log

- **POST** `/v1/access-log/check-in` – Log check-in.
- **POST** `/v1/access-log/check-out` – Log check-out.
- **GET** `/v1/access-log/top10/check-in` – Get the top 10 check-ins.
- **GET** `/v1/access-log/top10/check-out` – Get the top 10 check-outs.

### Cafeteria

- **GET** `/v1/cafeteria/all` – Retrieve all cafeterias.
- **DELETE** `/v1/cafeteria/delete` – Delete a cafeteria.
- **GET** `/v1/cafeteria/oneCafeteria` – Retrieve a specific cafeteria.
- **POST** `/v1/cafeteria/save` – Save a new cafeteria.
- **PUT** `/v1/cafeteria/update` – Update cafeteria information.

### Reservation

- **GET** `/v1/reservation/all` – Retrieve all reservations.
- **POST** `/v1/reservation/approve/{id}` – Approve a reservation.
- **GET** `/v1/reservation/approved` – Retrieve approved reservations.
- **DELETE** `/v1/reservation/delete` – Delete a reservation.
- **GET** `/v1/reservation/oneReservation` – Retrieve a specific reservation.
- **POST** `/v1/reservation/save` – Save a new reservation.
- **GET** `/v1/reservation/unapproved` – Retrieve unapproved reservations.
- **PUT** `/v1/reservation/update` – Update a reservation.

### Teacher

- **GET** `/v1/teacher/all` – Retrieve all teachers.
- **DELETE** `/v1/teacher/delete` – Delete a teacher.
- **POST** `/v1/teacher/image` – Upload a teacher's image.
- **GET** `/v1/teacher/oneTeacher` – Retrieve a specific teacher.
- **POST** `/v1/teacher/save` – Save a new teacher.
- **POST** `/v1/teacher/save/contact-info` – Save a teacher's contact info.
- **POST** `/v1/teacher/save/image` – Save a teacher's image.
- **PUT** `/v1/teacher/update` – Update a teacher's information.

### Playground

- **GET** `/v1/playground/all` – Retrieve all playgrounds.
- **DELETE** `/v1/playground/delete` – Delete a playground.
- **GET** `/v1/playground/onePlayGround` – Retrieve a specific playground.
- **POST** `/v1/playground/save` – Save a new playground.
- **PUT** `/v1/playground/update` – Update playground information.

### Log

- **GET** `/v1/log/all` – Retrieve all logs.
- **GET** `/v1/log/oneLog` – Retrieve a specific log.

### Image

- **POST** `/v1/image` – Upload an image.

## License

This project is licensed under the MIT License.
