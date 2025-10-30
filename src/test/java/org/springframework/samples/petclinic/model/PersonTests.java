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

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for {@link Person}
 */
class PersonTests {

	private static class TestPerson extends Person {

	}

	@Test
	void testSetAndGetFirstName() {
		TestPerson person = new TestPerson();
		person.setFirstName("John");

		assertThat(person.getFirstName()).isEqualTo("John");
	}

	@Test
	void testSetAndGetLastName() {
		TestPerson person = new TestPerson();
		person.setLastName("Doe");

		assertThat(person.getLastName()).isEqualTo("Doe");
	}

	@Test
	void testInheritsFromBaseEntity() {
		TestPerson person = new TestPerson();
		assertThat(person.isNew()).isTrue();

		person.setId(5);
		assertThat(person.isNew()).isFalse();
		assertThat(person.getId()).isEqualTo(5);
	}

	@Test
	void testSetBothNames() {
		TestPerson person = new TestPerson();
		person.setFirstName("Jane");
		person.setLastName("Smith");

		assertThat(person.getFirstName()).isEqualTo("Jane");
		assertThat(person.getLastName()).isEqualTo("Smith");
	}

}
