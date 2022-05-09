FROM debian:bullseye

RUN apt-get update \
 && DEBIAN_FRONTEND=noninteractive apt-get install -y \
    				   time build-essential gdb cmake git nano \
				   openjdk-11-jdk-headless maven \
 && apt-get clean \
 && rm -rf /var/lib/apt/lists/*

VOLUME ["/root/.m2"]
WORKDIR /app
RUN echo  export PATH='"'/app/bin/$(uname -s)-$(uname -m):/app:'${PATH}''"' >> /root/.bashrc

ENTRYPOINT ["/bin/bash","-i","-c","\"$@\"","--"]
