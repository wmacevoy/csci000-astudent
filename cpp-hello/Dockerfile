FROM debian:bullseye
RUN apt-get update \
 && DEBIAN_FRONTEND=noninteractive apt-get install -y \
    				   build-essential curl cmake gdb git nano nodejs python3 time \
 && apt-get clean \
 && rm -rf /var/lib/apt/lists/*

EXPOSE 8000
WORKDIR /app
RUN echo  export PATH='"'/app/bin/$(uname -s)-$(uname -m):/app:'${PATH}''"' >> /root/.bashrc
ENTRYPOINT ["/bin/bash","-i","-c","\"$@\"","--"]
