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
import org.springframework.samples.petclinic.vet.Vet;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for the Person model class.
 *
 * @author Copilot
 */
class PersonTests {

	@Test
	void testGetFirstName() {
		Person person = new Vet();
		person.setFirstName("John");
		assertThat(person.getFirstName()).isEqualTo("John");
	}

	@Test
	void testSetFirstName() {
		Person person = new Vet();
		person.setFirstName("Jane");
		assertThat(person.getFirstName()).isEqualTo("Jane");
	}

	@Test
	void testGetLastName() {
		Person person = new Vet();
		person.setLastName("Doe");
		assertThat(person.getLastName()).isEqualTo("Doe");
	}

	@Test
	void testSetLastName() {
		Person person = new Vet();
		person.setLastName("Smith");
		assertThat(person.getLastName()).isEqualTo("Smith");
	}

}
