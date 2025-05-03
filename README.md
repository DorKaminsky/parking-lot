# Parking Lot Application

This is a Spring Boot application demonstrating a parking-lot service backed by MySQL via JPA.

## Endpoints

- **POST** `/api/parking/park?plate={number}&zone={BLUE|GREEN|ORANGE|RED}`  
  Parks a car; returns `true` if successful.

- **POST** `/api/parking/leave?plate={number}`  
  Removes a car; returns `true` if successful.

- **GET** `/api/parking/spots`  
  Lists all spots and their `occupiedBy` values.

## Setup

1. **Database**
    - **Docker**:
      ```bash
      docker run --name parking-mysql \
        -e MYSQL_ROOT_PASSWORD=secret \
        -e MYSQL_DATABASE=parking \
        -p 3306:3306 -d mysql:8
      docker exec -it parking-mysql mysql -uroot -psecret -e "
        CREATE USER 'parking_user'@'%' IDENTIFIED BY 'parking_pass';
        GRANT ALL PRIVILEGES ON parking.* TO 'parking_user'@'%';
        FLUSH PRIVILEGES;
      "
      ```
    - **Local MySQL**:
      ```sql
      CREATE DATABASE parking;
      CREATE USER 'parking_user'@'localhost' IDENTIFIED BY 'parking_pass';
      GRANT ALL PRIVILEGES ON parking.* TO 'parking_user'@'localhost';
      FLUSH PRIVILEGES;
      ```

2. **Configure**  
   Edit `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/parking?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC
   spring.datasource.username=parking_user
   spring.datasource.password=parking_pass
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
