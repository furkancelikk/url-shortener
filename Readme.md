# URL Shortener

---

<details open>
<summary>Table of Contents</summary>

* [About The Project](#about-the-project)
* [Built With](#built-with)
* [Installation](#installation)
    * [Java ile ayağa kaldırma](#java-ile-ayağa-kaldırma)
    * [Docker ile ayağa kaldırma](#docker-ile-ayağa-kaldırma)
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

Projeyi ayağa kaldırmak için iki farklı yol var

### Java ile ayağa kaldırma

> **Prerequisites**
>
> - **Java 17:** Local bilgisayarında Java 17 bulunmalıdır.
>
> - **Mysql:** Local bilgisayarınızda bir MySQL'in çalışıyor olması ve **url_shortener** isminde bir schema oluşturmanız gerekmektedir

1. Clone the repo

   ```shell
   git clone https://github.com/furkancelikk/url-shortener.git
   ```
2. Change the directory

   ```shell
   cd url-shortener
   ```
3. Dependency'leri çekin ve jar dosyasını oluşturun

   ```shell
   mvnw clean install
   ```
4. target dizine geçin

   ```shell
   cd target
   ```
5. Projeyi ayağa kaldırın

   ```shell
   java -jar app.jar
   ```

Projeye 8080 portundan erişebilirsiniz

**Note:** Projede aşağıdaki değerler environment variable olarak tanımlanmıştır. Bu alanları kendi isteğinize göre tanımlamak isterseniz projeyi ayağa kaldırırken bu parametreleri aşağıdaki şekilde verebilirsiniz


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

### Docker ile ayağa kaldırma

> **Prerequisites**
>
> - **Docker**: Local bilgisayarınızda Docker bulunması gerekmektedir.

1. Clone the repo

   ```shell
   git clone https://github.com/furkancelikk/url-shortener.git
   ```
2. Change the directory

   ```
   cd url-shortener
   ```
3. Projeyi ayağa kaldırın. Bu komut gerekli dependency'leri çekecek, MySQL'i container olarak indirip projeyi çalıştıracaktır.

   ```
   docker compose up -d
   ```

Projeye 8080 portundan erişebilirsiniz

**Note:** Docker ile ayağa kaldırırken container isimleri


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

### License

Bu proje [MIT Lisansı](LICENSE) ile lisanslanmıştır.
