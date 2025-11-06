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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.SerializationUtils;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for Pet model class
 */
class PetTests {

	private Pet pet;

	private PetType petType;

	@BeforeEach
	void setUp() {
		pet = new Pet();
		pet.setName("Fluffy");

		petType = new PetType();
		petType.setName("cat");
		petType.setId(1);
	}

	@Test
	void testGettersAndSetters() {
		assertThat(pet.getName()).isEqualTo("Fluffy");

		pet.setType(petType);
		assertThat(pet.getType()).isNotNull();
		assertThat(pet.getType().getName()).isEqualTo("cat");

		LocalDate birthDate = LocalDate.of(2020, 1, 15);
		pet.setBirthDate(birthDate);
		assertThat(pet.getBirthDate()).isEqualTo(birthDate);
	}

	@Test
	void testGetVisitsReturnsEmptyCollectionInitially() {
		assertThat(pet.getVisits()).isEmpty();
	}

	@Test
	void testAddVisit() {
		Visit visit = new Visit();
		visit.setDescription("Annual checkup");
		visit.setDate(LocalDate.of(2024, 1, 15));

		pet.addVisit(visit);

		assertThat(pet.getVisits()).hasSize(1);
		assertThat(pet.getVisits()).contains(visit);
	}

	@Test
	void testAddMultipleVisits() {
		Visit visit1 = new Visit();
		visit1.setDescription("Annual checkup");
		visit1.setDate(LocalDate.of(2024, 1, 15));

		Visit visit2 = new Visit();
		visit2.setDescription("Vaccination");
		visit2.setDate(LocalDate.of(2024, 3, 20));

		pet.addVisit(visit1);
		pet.addVisit(visit2);

		assertThat(pet.getVisits()).hasSize(2);
		assertThat(pet.getVisits()).containsExactly(visit1, visit2);
	}

	@Test
	void testIsNewReturnsTrueWhenIdIsNull() {
		assertThat(pet.isNew()).isTrue();
	}

	@Test
	void testIsNewReturnsFalseWhenIdIsSet() {
		pet.setId(1);
		assertThat(pet.isNew()).isFalse();
	}

	@Test
	void testBirthDateCanBeNull() {
		pet.setBirthDate(null);
		assertThat(pet.getBirthDate()).isNull();
	}

	@Test
	void testTypeCanBeNull() {
		pet.setType(null);
		assertThat(pet.getType()).isNull();
	}

	@Test
	void testSerialization() {
		pet.setId(123);
		pet.setType(petType);
		LocalDate birthDate = LocalDate.of(2020, 1, 15);
		pet.setBirthDate(birthDate);

		@SuppressWarnings("deprecation")
		Pet deserializedPet = (Pet) SerializationUtils.deserialize(SerializationUtils.serialize(pet));

		assertThat(deserializedPet.getId()).isEqualTo(pet.getId());
		assertThat(deserializedPet.getName()).isEqualTo(pet.getName());
		assertThat(deserializedPet.getBirthDate()).isEqualTo(pet.getBirthDate());
		assertThat(deserializedPet.getType()).isNotNull();
		assertThat(deserializedPet.getType().getName()).isEqualTo(pet.getType().getName());
	}

}
