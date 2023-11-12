# Simple Microservices Project with Spring and Node.js

This project serves as a comprehensive demonstration of a microservices architecture, seamlessly merging the capabilities of the Spring Boot framework for Java and Node.js. The primary goal is to illustrate the effective communication protocols between diverse services, employing both Node.js and Spring Boot technologies.

## Eureka Configuration

Services are configured to register and discover other services using the Eureka server.

## Node.js to Spring Communication with Axios

The Node.js service communicates with the Spring service using the Axios library.

## Using Feign for Communication (Version 1)

The Spring service optimizes communication with the Node.js service by OpenFeign, a declarative library designed to simplify and enhance HTTP calls.

## Using RestTemplate for Communication (Version 2)

In an alternative approach, the Spring service communicates with the Node.js service using the RestTemplate library, providing flexibility and diverse options for communication strategies.

## Gateway Integration

A gateway has been added to facilitate request management and centralize access to the various services in the project.

