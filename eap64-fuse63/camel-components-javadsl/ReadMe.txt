http://localhost:8080/camel-java-1.0.0-SNAPSHOT/rest/ally/customer/search/1/2
Please compile and generate the jar file for project webservice before running this project

Compile the webservice project and create the jar
Compile the allyservice project and create the jar
Compile the ally-rest-poc and creat the jar
change the activemq borker url and user and password as you see fit

GET 
http://localhost:8080/ally-poc-rest-1.0.0-SNAPSHOT/rest/ally/customer/search

Will demo the SoapFault

POST

http://localhost:8080/ally-poc-rest-1.0.0-SNAPSHOT/rest/ally/customer


<customer>
<customerId>8091</customerId>
<customerName>Prasanth</customerName>
<companyName>Ally</companyName>
</customer>

Will create a customer and send it to AMQ

GET
http://localhost:8080/ally-poc-rest-1.0.0-SNAPSHOT/rest/ally/customer/search/8091/Max

Will search and return a customer

