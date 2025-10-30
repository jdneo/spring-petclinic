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
package org.springframework.samples.petclinic.owner;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for {@link PetType}
 */
class PetTypeTests {

	@Test
	void testSetAndGetName() {
		PetType petType = new PetType();
		petType.setName("dog");

		assertThat(petType.getName()).isEqualTo("dog");
	}

	@Test
	void testToString() {
		PetType petType = new PetType();
		petType.setName("cat");

		assertThat(petType.toString()).isEqualTo("cat");
	}

	@Test
	void testToStringWithNullName() {
		PetType petType = new PetType();

		assertThat(petType.toString()).isEqualTo("<null>");
	}

	@Test
	void testIsNew() {
		PetType petType = new PetType();
		assertThat(petType.isNew()).isTrue();

		petType.setId(3);
		assertThat(petType.isNew()).isFalse();
	}

	@Test
	void testMultiplePetTypes() {
		PetType dog = new PetType();
		dog.setName("dog");
		dog.setId(1);

		PetType cat = new PetType();
		cat.setName("cat");
		cat.setId(2);

		assertThat(dog.getName()).isEqualTo("dog");
		assertThat(cat.getName()).isEqualTo("cat");
		assertThat(dog.getId()).isNotEqualTo(cat.getId());
	}

}
