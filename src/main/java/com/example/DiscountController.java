package com.example;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URI;

/**
 */
@RestController
class DiscountController {

	private final RestTemplate restTemplate;

	int port = 8080;

	DiscountController(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@RequestMapping(method = RequestMethod.POST,
			value = "/discount",
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public String gimmeABeer(@RequestBody Person person) throws MalformedURLException {
		//remove::start[]
		//tag::controller[]
		ResponseEntity<Response> response = this.restTemplate.exchange(
				RequestEntity
						.post(URI.create("http://localhost:" + port + "/check"))
						.contentType(MediaType.APPLICATION_JSON)
						.body(person),
				Response.class);
		switch (response.getBody().status) {
		case OK:
			return "REGULAR PRICE";
		default:
			return "DISCOUNT";
		}
		//end::controller[]
		//remove::end[return]
	}
}

class Person {
	public String name;
	public int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public Person() {
	}
}

class Response {
	public ResponseStatus status;
}

enum ResponseStatus {
	OK, NOT_OK
}
