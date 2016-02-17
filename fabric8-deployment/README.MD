Generally in enterprise projects we will have to create multiple bundles which might then be deployed to a single profile
in the fabric envrionment. This project tweaks the approach of fabric8:zip and fabric8:branch to facilitate the process

Limitations
1. The dependencies directory for each profile will not be created.
2. The dependencies json for the profile will not be attached.


fabric8-deployment

This project contains two profile commonprofile and webmodule .

CommonProfile has two bundles
	1. DBCP 
	2. MySQL connector
	
WebModule has a single bundle
	1. Camel Examples
	

The target is now to create two profiles which will encapsulate all the required bundles for the project and deploy it to 
a newer version profile on the fabric git.


camel-fabric-deploy

this project is an example for the fabric8:branch plugin where by on running mvn:compile the profile artifacts declared 
as dependencies will now be pushed to the git repository.