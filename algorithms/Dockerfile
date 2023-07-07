FROM tensorflow/tensorflow:latest

RUN apt-get update && yes | apt-get upgrade

RUN apt-get install -y git python3-pip
RUN pip install --upgrade pip

COPY main.py .

CMD python main.py