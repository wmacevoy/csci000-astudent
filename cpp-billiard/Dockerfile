FROM gcc:11.2.0

RUN apt-get update && apt-get install -y cmake time build-essential gdb
WORKDIR /app
RUN echo  export PATH='"'/app/bin/$(uname -s)-$(uname -m):/app:'${PATH}''"' >> /root/.bashrc

CMD [ "/bin/bash" "-i" ]
