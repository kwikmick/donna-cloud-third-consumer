package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;

/**
 */
@AutoConfigureStubRunner(stubsMode = StubRunnerProperties.StubsMode.CLASSPATH, ids = "com.example:donna-cloud-contract", stubsPerConsumer = true, consumerName = "beer-api-consumer")
public abstract class AbstractTest {

	public JacksonTester<Person> json;

	@Before
	public void setup() {
		ObjectMapper objectMappper = new ObjectMapper();
		// Possibly configure the mapper
		JacksonTester.initFields(this, objectMappper);
	}
}
