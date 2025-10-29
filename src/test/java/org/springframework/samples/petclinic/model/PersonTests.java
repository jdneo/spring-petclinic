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

import org.junit.jupiter.api.Test;
import org.springframework.samples.petclinic.owner.Owner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for {@link Person}
 *
 * @author Copilot
 */
class PersonTests {

	@Test
	void testGetAndSetFirstName() {
		Owner person = new Owner();
		person.setFirstName("John");

		assertThat(person.getFirstName()).isEqualTo("John");
	}

	@Test
	void testGetAndSetLastName() {
		Owner person = new Owner();
		person.setLastName("Doe");

		assertThat(person.getLastName()).isEqualTo("Doe");
	}

	@Test
	void testFirstNameCanBeNull() {
		Owner person = new Owner();
		person.setFirstName(null);

		assertThat(person.getFirstName()).isNull();
	}

	@Test
	void testLastNameCanBeNull() {
		Owner person = new Owner();
		person.setLastName(null);

		assertThat(person.getLastName()).isNull();
	}

	@Test
	void testFirstNameCanBeUpdated() {
		Owner person = new Owner();
		person.setFirstName("John");
		assertThat(person.getFirstName()).isEqualTo("John");

		person.setFirstName("Jane");
		assertThat(person.getFirstName()).isEqualTo("Jane");
	}

	@Test
	void testLastNameCanBeUpdated() {
		Owner person = new Owner();
		person.setLastName("Doe");
		assertThat(person.getLastName()).isEqualTo("Doe");

		person.setLastName("Smith");
		assertThat(person.getLastName()).isEqualTo("Smith");
	}

}
