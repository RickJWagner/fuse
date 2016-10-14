package com.sundar.examples.rest;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


/**
 * 
 * @author smunirat
 *  The application path if not specified here , has to go in to the 
 *  web.xml , this way of writing code will eliminate the need to 
 *  specify the listeners in web.xml
 *  The alternate is to put the path here and modify the web.xml as below
 */
public class AllyRestApplication extends Application{
	 public Set<Class<?>> getClasses() {
		 final Set<Class<?>> classes = new HashSet<>();
		 classes.add(CustomerServiceImpl.class);
		 return classes;
	 }
}
