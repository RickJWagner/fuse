Deploying

Prerequisites

1. Start the jboss server in standalone-mode.

2. Create a application user as below

${AS}/bin/add-user.sh -a --user jboss --password jboss123# --group restsecurity


3. Build the project by running mvn:clean install

4. Copy the jar file to the deployments folder

5. Run the class HttpBasicAuth in the test directory 

6. Change the password and try again you should receive an error

java.io.IOException: Server returned HTTP response code: 401 for URL: http://localhost:8080/rest/order
	at sun.net.www.protocol.http.HttpURLConnection.getInputStream(HttpURLConnection.java:1627)
	at org.switchyard.quickstarts.rest.binding.HttpBasicAuth.main(HttpBasicAuth.java:24)
