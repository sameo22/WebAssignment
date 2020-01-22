# WebAssignment

# Importing The Database 
I have provided a mysql dump in the root of the project to be restored.

`Linux`, First create and empty database and then run:
  
```
mysql -u [username] –p[password] [database_name] < [dump_file.sql]

```

`Mysql Workbench UI`, Go to:
```
Server > Data Import > Import Self-Contained > Create new target default schema > Start Import

```
# Database Connection Settings In .Yaml 
```
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/waesdev?useSSL=false
    username: root
    password: admin
```

# Building The Project
The project can be build using maven:

```
mvn clean compile install -Dmaven.test.skip=true

```

# Run App From Jar

```
java -jar -Dspring.profiles.active=development waes-0.0.1-SNAPSHOT.jar

```

# Endpoints

### Save Left Base64 Encoded Json
```
http://localhost:8090/v1/diff/{ID}/left
POST Base64 Encoded Json text
RETURNS String message
```

### Save Right Base64 Encoded Json
```
http://localhost:8090/v1/diff/{ID}/right
POST Base64 Encoded Json text
RETURNS String message
```

### Retrieve Diff 
```
http://localhost:8090/v1/diff/{ID}
GET 
RETURNS JsonObject result
```

### Delete Left 
```
http://localhost:8090/v1/diff/delete/{ID}/left
POST
RETURNS String message
```

### Delete Right
```
http://localhost:8090/v1/diff/delete/{ID}/right
POST
RETURNS String message
```

### Update Left 
```
http://localhost:8090/v1/diff/update/{ID}/left
POST Base64 Encoded Json text
RETURNS String message
```

### Update Right
```
http://localhost:8090/v1/diff/update/{ID}/right
POST Base64 Encoded Json text
RETURNS String message
```

### Retrieve Whole Left Persisted Entity 
```
http://localhost:8090/v1/diff/retrieveEntity/{ID}/left
GET
RETURNS WaesEntityLeftJsons message
```

### Retrieve Whole Right Persisted Entity 
```
http://localhost:8090/v1/diff/retrieveEntity/{ID}/right
GET
RETURNS WaesEntityRightJsons message
```

### Retrieve Decoded Json Left
```
http://localhost:8090/v1/diff/retrieveJson/{ID}/left
GET
RETURNS WaesEntityLeftJsons message
```

### Retrieve Decoded Json Right
```
http://localhost:8090/v1/diff/retrieveJson/{ID}/right
GET
RETURNS WaesEntityRightJsons message
```
