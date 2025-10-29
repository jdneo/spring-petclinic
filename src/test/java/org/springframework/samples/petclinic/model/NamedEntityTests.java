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
import org.springframework.samples.petclinic.owner.PetType;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for {@link NamedEntity}
 *
 * @author Copilot
 */
class NamedEntityTests {

	@Test
	void testGetAndSetName() {
		PetType entity = new PetType();
		entity.setName("dog");

		assertThat(entity.getName()).isEqualTo("dog");
	}

	@Test
	void testNameCanBeNull() {
		PetType entity = new PetType();
		entity.setName(null);

		assertThat(entity.getName()).isNull();
	}

	@Test
	void testNameCanBeUpdated() {
		PetType entity = new PetType();
		entity.setName("dog");
		assertThat(entity.getName()).isEqualTo("dog");

		entity.setName("cat");
		assertThat(entity.getName()).isEqualTo("cat");
	}

	@Test
	void testToStringWithName() {
		PetType entity = new PetType();
		entity.setName("dog");

		assertThat(entity.toString()).isEqualTo("dog");
	}

	@Test
	void testToStringWithNullName() {
		PetType entity = new PetType();
		entity.setName(null);

		assertThat(entity.toString()).isEqualTo("<null>");
	}

}
