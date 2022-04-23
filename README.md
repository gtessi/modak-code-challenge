# Modak code challenge

---

## Design Friends Lessons System

We want to build a service that allows a user to know for what courses their friends have taken any lesson. For example:

Joe has the following friends in the system: Mark, Jody, and Rachel.

Joe can see through the system that Rachel has taken 3 math lessons and 2 spanish lessons. A user can take more than one lesson on the same topic.

We would like to build an API that exposes endpoints for:

- List all users in the system

- List all friendships registered in the system.

- List of all friends for a specific user.

- List of lessons that a specific user has taken.

We would also like to expose the data returned by the API via a modern front-end UI.

---

## Project settings

This project must be compiled with [Java 1.8 JDK](https://www.oracle.com/ar/java/technologies/javase/javase8-archive-downloads.html), with [Maven](https://maven.apache.org/download.cgi) as dependency manager.

It also uses [Spring Boot framework](https://spring.io/projects/spring-boot) and [Jackson Project](https://github.com/FasterXML/jackson) for JSON handling.

---

## Domain entities

**Student**

    Id: Integer
    Name: String

**Lesson**

    Id: Integer
    Name: String

**Friendship**

    Student: Student
    Friends: Collection of Students

---

## Endpoints

There are 4 main endpoints that perform the required tasks:

### List all users in the system

Returns a list with all users in the system. The Student entity is as follows:

    {
        "id": 1,
        "name": "Joe"
    }

The mocked list can be obtained with the following cURL:

    curl --location --request GET 'localhost:8080/students/list'

The result of the request is as follows:

    [
        {
            "id": 1,
            "name": "Joe"
        },
        {
            "id": 2,
            "name": "Mark"
        },
        {
            "id": 3,
            "name": "Jody"
        },
        {
            "id": 4,
            "name": "Rachel"
        },
        {
            "id": 5,
            "name": "Michael"
        },
        {
            "id": 6,
            "name": "Jenny"
        }
    ]

### List all friendships registered in the system.

Returns a list with all friendships registered in the system. The Friendship entity is as follows:

    {
        "student": {
            "id": 1,
            "name": "Joe"
        },
        "friends": [
            {
                "id": 2,
                "name": "Mark"
            },
            ...
        ]
    }

The mocked list can be obtained with the following cURL:

    curl --location --request GET 'localhost:8080/friends/list'

The result of the request is as follows:

    [
        {
            "student": {
                "id": 1,
                "name": "Joe"
            },
            "friends": [
                {
                    "id": 2,
                    "name": "Mark"
                },
                {
                    "id": 3,
                    "name": "Jody"
                },
                {
                    "id": 4,
                    "name": "Rachel"
                }
            ]
        },
        {
            "student": {
                "id": 2,
                "name": "Mark"
            },
            "friends": [
                {
                    "id": 1,
                    "name": "Joe"
                }
            ]
        },
        {
            "student": {
                "id": 3,
                "name": "Jody"
            },
            "friends": [
                {
                    "id": 1,
                    "name": "Joe"
                }
            ]
        },
        {
            "student": {
                "id": 4,
                "name": "Rachel"
            },
            "friends": [
                {
                    "id": 1,
                    "name": "Joe"
                }
            ]
        },
        {
            "student": {
                "id": 5,
                "name": "Michael"
            },
            "friends": []
        },
        {
            "student": {
                "id": 6,
                "name": "Jenny"
            },
            "friends": []
        }
    ]

### List of all friends for a specific user.

Returns a list of all friends for a specific user. The Friendship entity is as follows:

    {
        "student": {
            "id": 1,
            "name": "Joe"
        },
        "friends": [
            {
                "id": 2,
                "name": "Mark"
            },
            ...
        ]
    }

The mocked list can be obtained with the following cURL:

    curl --location --request GET 'localhost:8080/friends/{{studentId}}'

The result of the request with `studentId = 1` is as follows:

    {
        "student": {
            "id": 1,
            "name": "Joe"
        },
        "friends": [
            {
                "id": 2,
                "name": "Mark"
            },
            {
                "id": 3,
                "name": "Jody"
            },
            {
                "id": 4,
                "name": "Rachel"
            }
        ]
    }

### List of lessons that a specific user has taken.

Returns a list of lessons that specific user has taken. The StudentLessonHistory DTO is as follows:

    {
        "student": {
            "id": 1,
            "name": "Joe"
        },
        "lessons_count": [
            {
                "lesson": {
                    "id": 1,
                    "name": "math"
                },
                "count": 3
            },
            ...
        ]
    }

This DTO is supported by the helper DTO `LessonCount` which relates a Lesson entity with the times a Student has taken it.

The mocked list can be obtained with the following cURL:

    curl --location --request GET 'localhost:8080/lesson/{{studentId}}'

The result of the request with `studentId = 4` is as follows:

    {
        "student": {
            "id": 4,
            "name": "Rachel"
        },
        "lessons_count": [
            {
                "lesson": {
                    "id": 1,
                    "name": "math"
                },
                "count": 3
            },
            {
                "lesson": {
                    "id": 2,
                    "name": "spanish"
                },
                "count": 2
            }
        ]
    }
