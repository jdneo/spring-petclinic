/*
 * Copyright 2012-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for Person model class
 *
 * @author Copilot Agent
 */
class PersonTests {

	private Person person;

	@BeforeEach
	void setUp() {
		person = new Person();
	}

	@Test
	void testSetAndGetFirstName() {
		person.setFirstName("Alice");

		assertThat(person.getFirstName()).isEqualTo("Alice");
	}

	@Test
	void testSetAndGetLastName() {
		person.setLastName("Smith");

		assertThat(person.getLastName()).isEqualTo("Smith");
	}

	@Test
	void testSetBothNames() {
		person.setFirstName("Bob");
		person.setLastName("Johnson");

		assertThat(person.getFirstName()).isEqualTo("Bob");
		assertThat(person.getLastName()).isEqualTo("Johnson");
	}

	@Test
	void testDefaultValuesAreNull() {
		assertThat(person.getFirstName()).isNull();
		assertThat(person.getLastName()).isNull();
	}

	@Test
	void testSetNullValues() {
		person.setFirstName("John");
		person.setLastName("Doe");

		person.setFirstName(null);
		person.setLastName(null);

		assertThat(person.getFirstName()).isNull();
		assertThat(person.getLastName()).isNull();
	}

}
