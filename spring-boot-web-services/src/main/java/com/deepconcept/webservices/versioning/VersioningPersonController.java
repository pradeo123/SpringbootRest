package com.deepconcept.webservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {
	
	
	//URI Versioning 4 types--Twitter using
	//-Twitter
	//	1.http://localhost:8080/v1/person  
	//  2.http://localhost:8080/v2/person
	
	//-Amazon-Request Parameter Versioning
		//	1.http://localhost:8080/person?version=1 
		//  2.http://localhost:8080/person?version=2
	
	//Microsoft-Custom header  Versioning --
			//X_API_VERSION=1
			//X_API_VERSION=2
	//GitHub-Media type Versioning 
	
	
	
	//Factor To Consider
	//URI Pollution
	//misuse of heders
	//cahing
	//can we execute the request on the browser
	//api documentation
	//Sumurry: no perfect soluation//
	
	@GetMapping("/v1/person")
	public Personv1 getFirstVersionOfPerson() {
		return new Personv1("Superman");
	}
	
	@GetMapping("/v2/person")
	public Personv2 getSecondVersionOfPerson() {
		return new Personv2(new Name("Super","Man"));
	}
	
	//http://localhost:8080/person?version=1
	@GetMapping(path="/person",params="version=1")
	public Personv1 getFirstVersionOfPersonRequestParameter() {
		return new Personv1("Superman");
	}
	
	
	//http://localhost:8080/person?version=2
	@GetMapping(path="/person",params="version=2")
	public Personv2 getSecondVersionOfPersonRequestParameter() {
		return new Personv2(new Name("Super","Man"));
	}
	
	//http://localhost:8080/person/header
	//add Header key as -X-API-VERSION and value 1
	@GetMapping(path="/person/header",headers = "X-API-VERSION=1")
	public Personv1 getFirstVersionOfPersonRequestHeader() {
		return new Personv1("Superman");
	}
	
	@GetMapping(path="/person/header",headers = "X-API-VERSION=2")
	public Personv2 getSecondtVersionOfPersonRequestHeader() {
		return new Personv2(new Name("Super","Man"));
	}
	
	@GetMapping(path="/accept",produces = "application/vnd.company.app-v1+json")
	public Personv1 getFirstVersionOfPersonAcceptHeader() {
		return new Personv1("Superman");
	}
	
	@GetMapping(path="/accept",produces = "application/vnd.company.app-v2+json")
	public Personv2 getSecondVersionOfPersonAcceptHeader() {
		return new Personv2(new Name("Super","Man"));
	}
}
