# payments_processor

This microservice will consume payments from the message broker and process them at its own pace.
In addition, it will communicate via REST API with third parties for validation and logging.

# Modifications to project structure

Modified docker-compose.yml to inlclude 'kafdrop' service, running at port 9000.
so using "http://localhost:9000/" we can see and monitor the kafka, its topics, consumer groups and messages etc.

To accomodate above, I have modified the port of api-producer service from 9000 to 9001

Hence APIs for validation of payment and logging errors use port 9001 (http://localhost:9001/payment, http://localhost:9001/log)

Also the API to view the page will be http://localhost:9001, through which we can initiate tests by producing messages view error logs etc.

Kafka Producer and Consumer configuration can be found at application.yml file

Modified resources can be found in the project under src/main/resources/delivery folder

# How to Run

Run the below command under directory "/payment-processor/src/main/resources/delivery/" of this project
docker-compose up -d 

Then the project can be run as a spring boot application.

Then use below url to generate messages and test the application

http://localhost:9001

# Further improvements

Tests should be written, currently tested it manually

Logs can be improved
