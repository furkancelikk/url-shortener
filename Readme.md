# URL Shortener

---

<details open>
<summary>Table of Contents</summary>

* [About The Project](#about-the-project)
* [Built With](#built-with)
* [Installation](#installation)
    * [Java ile ayağa kaldırma](#set-up-with-java)
    * [Docker ile ayağa kaldırma](#setup-with-docker)
</details>

---

## About The Project

This project is a URL shortening application designed to make users'long URLs shorter and easier to remember.
Users can save their URLs along with an optional code. If a user does not provide a code,
the system will automatically generate a unique code.
Users can view these URLs using the saved code or access them directly.

---

## Built With

The following technologies, tools etc. have been used in this project:

<img width="60" src="https://user-images.githubusercontent.com/25181517/117201156-9a724800-adec-11eb-9a9d-3cd0f67da4bc.png" alt="Java" title="Java"/>
<img width="60" src="https://user-images.githubusercontent.com/25181517/183891303-41f257f8-6b3d-487c-aa56-c497b880d0fb.png" alt="Spring Boot" title="Spring Boot"/>
<img width="60" src="https://user-images.githubusercontent.com/25181517/183896128-ec99105a-ec1a-4d85-b08b-1aa1620b2046.png" alt="MySQL" title="MySQL"/>
<img width="60" src="https://user-images.githubusercontent.com/25181517/117207330-263ba280-adf4-11eb-9b97-0ac5b40bc3be.png" alt="Docker" title="Docker"/>
<img width="60" src="https://user-images.githubusercontent.com/25181517/117207242-07d5a700-adf4-11eb-975e-be04e62b984b.png" alt="Maven" title="Maven"/>
<img width="60" src="https://user-images.githubusercontent.com/25181517/117533873-484d4480-afef-11eb-9fad-67c8605e3592.png" alt="JUnit" title="JUnit"/>
<img width="60" src="https://user-images.githubusercontent.com/25181517/192107858-fe19f043-c502-4009-8c47-476fc89718ad.png" alt="REST" title="REST"/>
<img width="60" src="https://user-images.githubusercontent.com/25181517/192108890-200809d1-439c-4e23-90d3-b090cf9a4eea.png" alt="IntelliJ" title="IntelliJ"/>
<img width="60" src="https://user-images.githubusercontent.com/25181517/192109061-e138ca71-337c-4019-8d42-4792fdaa7128.png" alt="Postman" title="Postman"/>

---

## Installation

There are two different ways to set up the project.

### Set up with Java

> **Prerequisites**
>
> - **Java 17:** Java 17 must be installed on your local machine.
>
> - **Mysql:** You need to have a MySQL instance running on your local machine and create a schema named **url_shortener**.

1. Clone the repo

   ```shell
   git clone https://github.com/furkancelikk/url-shortener.git
   ```
2. Change the directory

   ```shell
   cd url-shortener
   ```
3. Pull the dependencies and build the JAR file.

   ```shell
   mvnw clean install
   ```
4. Change the directory to the **target**

   ```shell
   cd target
   ```
5. Start the project.

   ```shell
   java -jar app.jar
   ```

You can access the project at port 8080.

**Note:** The following values are defined as environment variables in the project. If you want to customize these values, you can provide them as parameters when starting the project, as shown below:


| Variable Name | Default Value | Description                |
|---------------|---------------|----------------------------|
| DB_HOST       | localhost     | MySQL host                 |
| DB_PORT       | 3306          | MySQL port                 |
| SCHEMA_NAME   | url           | Project schema name        |
| DB_USERNAME   | root          | MySQL username             |
| DB_PASSWORD   | root          | MySQL password             |
| CODE_LENGTH   | 5             | Auto generated code length |

```shell
java -DCODE_LENGTH=10 -jar app.jar
```

### Setup with Docker

> **Prerequisites**
>
> - **Docker**: Docker must be installed on your local machine.

1. Clone the repo

   ```shell
   git clone https://github.com/furkancelikk/url-shortener.git
   ```
2. Change the directory

   ```
   cd url-shortener
   ```
3. Start the project. This command will pull the necessary dependencies, download MySQL as a container, and run the project.

   ```
   docker compose up -d
   ```

You can access the project at port 8080.

**Note:** When starting the project with Docker, the following values are defined as environment variables. If you wish to customize these values, you can modify them in the [.env](.env) file.


| Variable Name          | Value               | Description                |
|------------------------|---------------------|----------------------------|
| SERVICE_IMAGE_NAME     | url-shortener       | Project image name         |
| SERVICE_CONTAINER_NAME | url-shortener       | Project container name     |
| SERVICE_PORT           | 8080                | Project port               |
| DB_CONTAINER_NAME      | url-shortener-mysql | MySQL container name       |
| DB_PORT                | 3306                | MySQL port                 |
| DB_USERNAME            | url-shortener       | MySQL user name            |
| DB_PASSWORD            | p4ssword            | MySQL password             |
| DB_ROOT_PASSWORD       | root                | MySQL root password        |
| SCHEMA_NAME            | url-shortener       | MySQL scheme name          |
| CODE_LENGTH            | 5                   | Auto generated code length |

---

#### [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
