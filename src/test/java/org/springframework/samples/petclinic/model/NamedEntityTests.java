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
 * Unit tests for the NamedEntity model class.
 *
 * @author Copilot
 */
class NamedEntityTests {

	@Test
	void testGetName() {
		NamedEntity entity = new PetType();
		entity.setName("Dog");
		assertThat(entity.getName()).isEqualTo("Dog");
	}

	@Test
	void testSetName() {
		NamedEntity entity = new PetType();
		entity.setName("Cat");
		assertThat(entity.getName()).isEqualTo("Cat");
	}

	@Test
	void testToString() {
		NamedEntity entity = new PetType();
		entity.setName("Bird");
		assertThat(entity.toString()).isEqualTo("Bird");
	}

	@Test
	void testToStringWithNullName() {
		NamedEntity entity = new PetType();
		assertThat(entity.toString()).isEqualTo("<null>");
	}

}
