package com.sundar.examples.rest;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


public class RestApplication extends Application{
	 public Set<Class<?>> getClasses() {
		 final Set<Class<?>> classes = new HashSet<>();
		 classes.add(CustomerServiceImpl.class);
		 return classes;
	 }
}
