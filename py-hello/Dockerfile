FROM python:3.7

WORKDIR /usr/src/myapp
COPY hello /usr/src/myapp/hello/
COPY hellotest /usr/src/myapp/hellotest/
ENV PATH="/usr/src/myapp/hello:/usr/src/myapp/hellotest:${PATH}"
CMD [ "/bin/bash" "-i" ]
