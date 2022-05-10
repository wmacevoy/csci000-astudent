FROM debian:bullseye
RUN apt-get update \
 && DEBIAN_FRONTEND=noninteractive apt-get install -y \
    				   time build-essential gdb cmake git nano \
 && apt-get clean \
 && rm -rf /var/lib/apt/lists/*

WORKDIR /app
RUN echo  export PATH='"'/app/bin/$(uname -s)-$(uname -m):/app:'${PATH}''"' >> /root/.bashrc
ENTRYPOINT ["/bin/bash","-i","-c","\"$@\"","--"]

