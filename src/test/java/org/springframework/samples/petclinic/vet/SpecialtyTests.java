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
 * Unit tests for Specialty model class
 */
class SpecialtyTests {

	@Test
	void testGettersAndSetters() {
		Specialty specialty = new Specialty();
		specialty.setName("dentistry");

		assertThat(specialty.getName()).isEqualTo("dentistry");
	}

	@Test
	void testToString() {
		Specialty specialty = new Specialty();
		specialty.setName("surgery");

		assertThat(specialty.toString()).isEqualTo("surgery");
	}

	@Test
	void testToStringWithNullName() {
		Specialty specialty = new Specialty();
		specialty.setName(null);

		assertThat(specialty.toString()).isEqualTo("<null>");
	}

	@Test
	void testIsNewReturnsTrueWhenIdIsNull() {
		Specialty specialty = new Specialty();
		assertThat(specialty.isNew()).isTrue();
	}

	@Test
	void testIsNewReturnsFalseWhenIdIsSet() {
		Specialty specialty = new Specialty();
		specialty.setId(1);
		assertThat(specialty.isNew()).isFalse();
	}

	@Test
	void testSerialization() {
		Specialty specialty = new Specialty();
		specialty.setId(123);
		specialty.setName("radiology");

		@SuppressWarnings("deprecation")
		Specialty deserializedSpecialty = (Specialty) SerializationUtils
			.deserialize(SerializationUtils.serialize(specialty));

		assertThat(deserializedSpecialty.getId()).isEqualTo(specialty.getId());
		assertThat(deserializedSpecialty.getName()).isEqualTo(specialty.getName());
	}

}
