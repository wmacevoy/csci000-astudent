FROM gcc

RUN apt-get update && apt-get install -y cmake time

WORKDIR /usr/src/myapp
COPY Makefile Makefile.googletest /usr/src/myapp/
COPY src /usr/src/myapp/src/
COPY include /usr/src/myapp/include/

RUN make googletest
RUN make all
ENV PATH="/usr/src/myapp/bin:${PATH}"
CMD [ "/bin/bash" "-i" ]
