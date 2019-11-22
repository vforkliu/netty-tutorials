# netty-tutorials
demo for netty

## echo server
```bash
java -jar build/libs/EchoServer-1.0-SNAPSHOT.jar 9000
java -jar build/libs/EchoClient-1.0-SNAPSHOT.jar localhost 9000
```

## Cat

HTTP Server
- support RESTFul

Test Method
```bash
curl -X post 'localhost:8899'
```

## Gradle package
```Groovy
id 'com.github.johnrengelman.shadow' version '5.2.0'
```
