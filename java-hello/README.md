# Hello

This a Netbeans/Maven "hello world" program tested with JUnit 4

![pass.png](pass.png)

## docker 

### create docker

```bash
docker build -t java-hello .
```

### compile maven project
```bash
docker run --rm -t -v `pwd`/maven-cache:/root/.m2 java-hello mvn compile
```

### test maven project
```bash
docker run --rm -t -v `pwd`/maven-cache:/root/.m2 java-hello mvn test
```
