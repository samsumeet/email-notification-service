#!/bin/sh

mvn clean install

docker build -t mapp/email-notification-service .

docker run -p 8080:8080 mapp/email-notification-service
