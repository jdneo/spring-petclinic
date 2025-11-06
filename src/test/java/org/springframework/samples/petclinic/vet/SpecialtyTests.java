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
package org.springframework.samples.petclinic.vet;

import org.junit.jupiter.api.Test;
import org.springframework.util.SerializationUtils;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for {@link Specialty}
 */
class SpecialtyTests {

	@Test
	void testSetAndGetName() {
		Specialty specialty = new Specialty();
		specialty.setName("radiology");

		assertThat(specialty.getName()).isEqualTo("radiology");
	}

	@Test
	void testSetAndGetId() {
		Specialty specialty = new Specialty();
		specialty.setId(1);

		assertThat(specialty.getId()).isEqualTo(1);
	}

	@Test
	void testSerialization() {
		Specialty specialty = new Specialty();
		specialty.setId(1);
		specialty.setName("surgery");

		@SuppressWarnings("deprecation")
		Specialty deserialized = (Specialty) SerializationUtils.deserialize(SerializationUtils.serialize(specialty));

		assertThat(deserialized.getId()).isEqualTo(specialty.getId());
		assertThat(deserialized.getName()).isEqualTo(specialty.getName());
	}

	@Test
	void testNewSpecialty() {
		Specialty specialty = new Specialty();

		assertThat(specialty.isNew()).isTrue();
	}

	@Test
	void testSpecialtyWithId() {
		Specialty specialty = new Specialty();
		specialty.setId(1);

		assertThat(specialty.isNew()).isFalse();
	}

}
