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
 * Unit tests for PetType
 */
class PetTypeTests {

	@Test
	void testNameGetterAndSetter() {
		PetType petType = new PetType();
		assertThat(petType.getName()).isNull();

		petType.setName("Dog");
		assertThat(petType.getName()).isEqualTo("Dog");
	}

	@Test
	void testToString() {
		PetType petType = new PetType();
		petType.setName("Cat");
		assertThat(petType.toString()).isEqualTo("Cat");
	}

	@Test
	void testIdFromBaseEntity() {
		PetType petType = new PetType();
		assertThat(petType.isNew()).isTrue();

		petType.setId(1);
		assertThat(petType.getId()).isEqualTo(1);
		assertThat(petType.isNew()).isFalse();
	}

}
