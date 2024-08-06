# Introduction

RS256 and HS256 are two commonly used algorithms for signing and verifying JSON Web Tokens (JWTs). They serve different purposes depending on 
the use case and security requirements. This simple application is about demonstrating how them work.
  
## Tech stack

JDK 11, Gradle


## How to get started?
This project contains two services **JwtHs256Service** and **JwtRs256Service** to implement RS256 and HS256, each service contains 
two methods to create and decode JWT. We can see how they work by running unit tests:
```shell
gradle build
gradle test
```

