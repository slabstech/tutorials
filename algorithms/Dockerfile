FROM tensorflow/tensorflow:latest

RUN apt-get update && yes | apt-get upgrade

RUN apt-get install -y git python3-pip
RUN pip install --upgrade pip
RUN pip install numpy

COPY main.py .

CMD python main.py