
POST : to create a customer
http://localhost:8080/cxfjavadsl-0.0.1-SNAPSHOT/rest/ally/customer
post body : 

<customer>
<customerId>80911</customerId>
<customerName>Sundar</customerName>
<companyName>Redhat</companyName>
</customer>

GET 
http://localhost:8080/cxfjavadsl-0.0.1-SNAPSHOT/rest/ally/customer/search/80911/Max
Should return 

<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<customer>
    <customerId>80911</customerId>
    <customerName>Sundar</customerName>
    <companyName>Redhat</companyName>
</customer>

The project will invoke a soap service hosted at http://www.predic8.com:8080/crm/CustomerService?wsdl

The issue is that unless we have the dependencies mentioned in the

jboss-deployment-structure.xml , this project will throw class not found exception / class cast exception

Since Fuse is being used on EAP , the deployment should automatically enable the dependecies in EAP. 
