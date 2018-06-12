# Stations Microservice
This project implements a Spring Boot Microservice for CRUD and searching of radio station information. It is a Demo application as part of a coding assignment.

Copyright © 2018 Michael S. Hepfer

## Known Issues
- Uses Spring Data instead of MyBatis
- Acctuator is configured to start on port 9001 but it is not starting (need to troubleshoot)
- Does not implement FlyWay for DB Versioning
- Does not use Lombok for Getter/Setter annotations
- Contains Dockerfile but not tested (docker on my laptop is currently broken)

## API Documentation

There is a swagger-UI available here.
http://localhost/swagger-ui.html


## How to build
gradle clean javadoc LicenseFormat build jacocoTestReport

