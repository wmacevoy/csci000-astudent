FROM maven:3-jdk-12
WORKDIR /usr/src/myapp
COPY pom.xml /usr/src/myapp/
COPY src /usr/src/myapp/src/
VOLUME ["/root/.m2"]
CMD [ "/bin/bash" "-i" ]
