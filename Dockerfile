# sshd
#
# VERSION               0.0.2

FROM ubuntu:18.04
# MAINTAINER Sven Dowideit <SvenDowideit@docker.com>
MAINTAINER James Parker <jprider@cs.umd.edu>

ENV DEBIAN_FRONTEND "noninteractive"

RUN adduser --disabled-password --gecos "" ubuntu; usermod -a -G sudo ubuntu; adduser --disabled-password --gecos "" server; adduser --disabled-password --gecos "" client; adduser --disabled-password --gecos "" builder; adduser --disabled-password --gecos "" breaker

RUN apt-get update ; apt-get install -y gnupg software-properties-common apt-utils
# Ashwin
# RUN apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys 4B7C549A058F8B6B; echo "deb http://repo.mongodb.org/apt/ubuntu bionic/mongodb-org/4.2 multiverse" | tee /etc/apt/sources.list.d/mongodb-org-4.2.list
# RUN gpg --keyserver hkp://keys.gnupg.net --recv-keys 409B6B1796C275462A1703113804BB82D39DC0E3
RUN apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 7F0CEB10; apt-add-repository -y ppa:deadsnakes/ppa; apt-add-repository -y ppa:pypy/ppa;
RUN apt-get update
RUN apt-get install -y openssh-server build-essential ocaml python python3 ruby php mono-complete fsharp curl ruby openssl erlang rebar python-protobuf libprotobuf-c0-dev libprotobuf-java libprotobuf-dev libprotoc-dev protobuf-compiler python-zmq haveged rng-tools python-pip python-gevent python3-pip clang golang-go cmake  zlib1g-dev libcppunit-dev llvm python-demjson python3-crypto python-zmq libzmq5-dev python3-requests python-virtualenv python-pytest python3-openssl build-essential gnupg build-essential  libffi-dev python python-dev pypy pypy-dev python-virtualenv libcrypto++-dev cython default-jdk clang golang-go cmake zlib1g-dev libcppunit-dev llvm python-demjson python3-crypto python-zmq libcrypto++-dev libseccomp-dev seccomp libc++-dev libc++abi-dev libboost-all-dev flex bison python-pyparsing golang maven nodejs npm node-typescript node-gyp nodejs-dev libssl1.0-dev libjson-simple-java postgresql postgresql-contrib libasio-dev libboost-all-dev python-pip python-dev build-essential mongodb python3-pyparsing clang-9 python3-pip scala-library scala python-ply socket python-mysqldb python-psycopg2 mono-complete golang cpanminus fp-compiler bcrypt uthash-dev libjansson-dev libgcrypt11-dev htop nano python3.7 python-crypto erlang-reltool python3-crypto python-gnupg python3-dev php-cli libjson-c-dev libsodium-dev sudo openjfx
RUN mkdir /var/run/sshd
RUN cat rustup.sh | sh -s -- -y # which rustc || curl -sSf https://static.rust-lang.org/rustup.sh | sh -s -- -y

# RUN echo 'root:screencast' | chpasswd
RUN sed -i 's/PermitRootLogin without-password/PermitRootLogin no/' /etc/ssh/sshd_config; sed -i 's/#PasswordAuthentication yes/PasswordAuthentication no/' /etc/ssh/sshd_config

# SSH login fix. Otherwise user is kicked off after login
RUN sed 's@session\s*required\s*pam_loginuid.so@session optional pam_loginuid.so@g' -i /etc/pam.d/sshd

ENV NOTVISIBLE "in users profile"
RUN echo "export VISIBLE=now" >> /etc/profile

# Setup ssh keys for login.
#RUN mkdir -p /home/ubuntu/.ssh
#COPY authentication/key.pem.pub /home/ubuntu/.ssh/authorized_keys
#RUN chmod 644 /home/ubuntu/.ssh/authorized_keys

# Install build tools.
RUN cd
COPY dependencies/* ./

RUN pip3 install simplejson

# added missing dependencies
RUN pip install pyparsing &&\
pip install appdirs && pip install --upgrade setuptools &&\
pip install --ignore-installed six &&\
pip install cryptography &&\
pip install pynacl && pip3 install pynacl &&\
pip install pyopenssl && pip3 install pyopenssl &&\
pip install pyzmq &&\
pip install pyelliptic
RUN pip3 install pyelliptic &&\
pip3 install pycrypto &&\
pip install pycrypto &&\
pip3 install pytest &&\
pip install pytest
RUN pip install oslo.concurrency
RUN pip3 install oslo.concurrency
# Ashwin
# RUN tar -C /usr/local -xzf go1.13.8.linux-amd64.tar.gz
RUN pip3 install pyinstaller &&\
pip install ptvsd &&\
pip3 install ptvsd &&\
pip install docopt &&\
pip3 install docopt
RUN pip install libnacl && pip3 install libnacl &&\
pip install simplejson &&\
pip install ipy

# v2
#RUN stack setup --resolver lts-7.0 && stack install aeson
#RUN stack install network-simple

# v3
RUN sudo apt-get update
RUN sudo pip install --upgrade pip
RUN sudo pip install --upgrade virtualenv
RUN sudo pip install flake8 nose coverage grako pyrser nose2 setuptools cov-core passlib antlr4-python2-runtime==4.9.3 pydblite
RUN sudo mkdir -p /opt/packages/gradle; cd /opt/packages/gradle; sudo wget https://services.gradle.org/distributions/gradle-3.1-bin.zip; sudo unzip gradle-3.1-bin.zip; sudo ln -s /opt/packages/gradle/gradle-3.1/bin/gradle /usr/bin/gradle; cd -
RUN sudo pip3 install networkx pyrser utility pytest twisted simplejson
RUN sudo cpanm install Marpa::R2
RUN sudo pip install asteval SQLAlchemy Cython bcrypt
RUN sudo pip3 install ujson==3.2.0
#RUN stack install network parsec attoparsec lens json

# v4
#RUN sudo -i -u builder stack install async

RUN npm install typescript@latest -g

# CMSC414
RUN rm /dev/random; ln -s /dev/urandom /dev/random

# Run ssh server.
EXPOSE 22
CMD ["/usr/sbin/sshd", "-D"]

