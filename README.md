# VlogVerse-SpringBoot
---

## Overview
Implemented RESTful APIs for social media web application to facilitate content sharing and user engagement.

## Project Technical Overview
This is a web application involves MVC design pattern and allows users to register accounts, search for multi-Media posts by hashtag and give a like. Frameworks and tools used includ Java, Spring Boot, Restful Web Services, Hibernate, Spring Data JPA, PostgreSQL, servlet, JWT, Git, Maven, Tomcat, Docker, and AWS (S3, RDS, Code Pipeline).

## Business Rules

```plaintext
1. Objects: User, Post, Like, Hashtag, Role

2. Relationships: 
    - One user can publish many posts.
    - One user can like many posts.
    - One posts can have many hashtags. 
    - One hashtags can have many related posts. 
    - One user can have multiple roles. 
    - One role can be assigned to many users. 

3. Only role admin could assign manager roles to specific users. 

4. Authorization for each role: 
    - allowed_resources: demonstrated by URL. 
    - allowed_read, allowed_created, allowed_update, allowed_delete: Boolean value. 
    - Admin has access to all resources and to allocate resources. 
```


## Code Structure
---
This is a multi-model project managed by Maven. 

```plaintext
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── com
│   │   │   │   ├── yourprojectname
│   │   │   │   │   ├── config               # Configuration classes (e.g. DataSource, JWT, Swagger)
│   │   │   │   │   ├── controller           # REST API endpoints and web request handlers
│   │   │   │   │   ├── dao                  # JPA Repositories and Hibernate Dao
│   │   │   │   │   ├── dto                  # Data Transfer Objects for API request/response
│   │   │   │   │   ├── entity               # Entity classes representing database tables
│   │   │   │   │   ├── exception            # Custom exception handling classes and error handling
│   │   │   │   │   ├── dao           
│   │   │   │   │   ├── service              # Business logic services
│   │   │   │   │   ├── filter               # JWT Filters for security
│   │   │   │   │   └── utils                # Helper classes and utilities
│   │   ├── resources
│   │   │   ├── static                      # CSS, JavaScript, and other static files
│   │   │   ├── templates                   # HTML templates (if using Thymeleaf or similar)
│   │   │   ├── application.properties      # Spring Boot configuration file
│   │   │   └── ...
│   ├── test                                # Unit and integration tests
├── .gitignore                              # Specifies files and directories to be ignored by Git
├── Ops                                     # DevOps files including DockerFile, yaml for aws code pipeline
├── pom.xml                                 # Maven dependency and build management file
└── README.md                               # This file
```


## Configure Local Environment
---

### 1. Setup local database
Refer to [postgres docker image](https://hub.docker.com/_/postgres) for environment option.
```bash
docker pull postgres
docker run --name ${PostgresContainerName} -e POSTGRES_USER=${username} -e POSTGRES_PASSWORD=${password} -e POSTGRES_DB=${databaseName} -p ${hostport}:${containerport} -d postgres
```
${...} is the part you define on your own. 

### 2. Database, data manipulation and storage service configuration

It is essential to ensure sensitive data are kept not exposed. 
```plaintext
spring.datasource.url=${ds_url}
spring.datasource.driverClassName=org.postgresql.Driver
spring.datasource.username=${user_name}
spring.datasource.password=${password}
```

#### Options to pass value
* Pass value using JVM option
* Setting environment variable
* Use different profile and specify profile while running the app
* ...

In our project, we utilize Maven's capability to handle different profiles. Specifically, we have two main profiles defined in our pom.xml: dev and prod. These profiles allow us to manage and segregate configurations based on the environment we're working in. Please refer to my application.properties `src/main/resources/application-prod.properties`

```bash
#mvn clean package -Pdev    # for the development environment
mvn clean package -Pprod   # for the production environment
```

Notice: utilized .gitignore to make sure sensitive data will never be pushed to the repo. 

### 3. Migrate database schema and data point changes according to sql script
Refer to [flyway setup documentation](https://documentation.red-gate.com/fd/).
```bash
mvn flyway:clean    # used for clean all the existing schema
mvn flyway:migrate
```

### 4.Setup Logger
refer to `src/main/resources/logback.xml`


## Deploy and Production
----

### 1. Compile the project and run the rest
```bash
mvn compile
mvn test
```

### 2. Package the app
Refer [Apache Maven](https://maven.apache.org/plugins-archives/maven-surefire-plugin-2.12.4/examples/skipping-test.html) for more skipping options. 
```bash
mvn clean package -Pdev -DskipTests=true   # This command will bypass the test part in packaging
```

### 3. Deploy this project on Tomcat Server.
Remember that environment variable must be set up. [This could be a good reference. ](https://howtoprogram.xyz/2020/05/11/apache-tomcat-how-to-set-up-environment-variables/)

### 4. CICD
1- Use `cp` to fetch `setenv.sh` and `.war` files to Ops folder. 
2- Leverage Dockerfile for containerization. 
3- Setup codepipeline.yaml. 