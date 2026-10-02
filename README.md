# REST Assured API Automation Framework

## 📌 Project Overview

This project is a Java-based API automation testing framework developed using REST Assured, TestNG, Maven, and Jackson.

The framework automates API testing for the Restful-Booker demo API and demonstrates commonly used API testing concepts such as CRUD operations, authentication, request/response specifications, JSONPath, POJO serialization/deserialization, data-driven testing, negative testing, and JSON Schema validation.

---

## 🛠️ Tech Stack

- Java 17
- REST Assured 5.5.6
- TestNG 7.11.0
- Maven 3.9.16
- Jackson Databind 2.20.0
- JSON Schema Validator
- Allure Reporting
- Git & GitHub

---

## 🌐 API Under Test

Restful-Booker Demo API

Base URL:

https://restful-booker.herokuapp.com

---

## 📂 Project Structure

```text
RestAssured-API-Automation
│
├── src
│   ├── main
│   │   └── java
│   │       ├── config
│   │       │   └── ApiConfig.java
│   │       ├── models
│   │       │   ├── Booking.java
│   │       │   └── BookingDates.java
│   │       ├── specifications
│   │       │   ├── RequestSpec.java
│   │       │   └── ResponseSpec.java
│   │       └── utils
│   │           └── AuthUtils.java
│   │
│   └── test
│       ├── java
│       │   ├── dataproviders
│       │   │   └── BookingDataProvider.java
│       │   └── tests
│       │       ├── AuthTest.java
│       │       ├── BookingByIdTest.java
│       │       ├── BookingCreateTest.java
│       │       ├── BookingDataDrivenTest.java
│       │       ├── BookingDeleteTest.java
│       │       ├── BookingGetTest.java
│       │       ├── BookingPatchTest.java
│       │       ├── BookingQueryTest.java
│       │       ├── BookingSchemaTest.java
│       │       ├── BookingUpdateTest.java
│       │       ├── DeserializationTest.java
│       │       ├── FrameworkTest.java
│       │       ├── NegativeAuthTest.java
│       │       ├── NegativeBookingTest.java
│       │       └── SerializationTest.java
│       │
│       └── resources
│           ├── schemas
│           │   └── booking-schema.json
│           └── allure.properties
│
├── .gitignore
├── pom.xml
├── testng.xml
└── README.md