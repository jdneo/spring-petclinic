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

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for {@link Pet}
 */
class PetTests {

	private Pet pet;

	private PetType petType;

	@BeforeEach
	void setup() {
		petType = new PetType();
		petType.setId(1);
		petType.setName("dog");

		pet = new Pet();
		pet.setId(1);
		pet.setName("Max");
		pet.setType(petType);
		pet.setBirthDate(LocalDate.of(2020, 6, 15));
	}

	@Test
	void testSetAndGetBirthDate() {
		LocalDate birthDate = LocalDate.of(2019, 3, 10);
		pet.setBirthDate(birthDate);

		assertThat(pet.getBirthDate()).isEqualTo(birthDate);
	}

	@Test
	void testSetAndGetType() {
		PetType cat = new PetType();
		cat.setId(2);
		cat.setName("cat");

		pet.setType(cat);

		assertThat(pet.getType()).isEqualTo(cat);
		assertThat(pet.getType().getName()).isEqualTo("cat");
	}

	@Test
	void testAddVisit() {
		Visit visit = new Visit();
		visit.setId(1);
		visit.setDate(LocalDate.now());
		visit.setDescription("Annual checkup");

		pet.addVisit(visit);

		assertThat(pet.getVisits()).contains(visit);
		assertThat(pet.getVisits()).hasSize(1);
	}

	@Test
	void testAddMultipleVisits() {
		Visit visit1 = new Visit();
		visit1.setId(1);
		visit1.setDate(LocalDate.of(2023, 1, 10));
		visit1.setDescription("Vaccination");

		Visit visit2 = new Visit();
		visit2.setId(2);
		visit2.setDate(LocalDate.of(2023, 6, 20));
		visit2.setDescription("Checkup");

		pet.addVisit(visit1);
		pet.addVisit(visit2);

		assertThat(pet.getVisits()).hasSize(2);
		assertThat(pet.getVisits()).contains(visit1, visit2);
	}

	@Test
	void testGetVisitsReturnsEmptyCollectionWhenNoVisits() {
		assertThat(pet.getVisits()).isNotNull();
		assertThat(pet.getVisits()).isEmpty();
	}

	@Test
	void testVisitsOrderedByDate() {
		Visit visit1 = new Visit();
		visit1.setDate(LocalDate.of(2023, 6, 20));
		visit1.setDescription("Second visit");

		Visit visit2 = new Visit();
		visit2.setDate(LocalDate.of(2023, 1, 10));
		visit2.setDescription("First visit");

		pet.addVisit(visit1);
		pet.addVisit(visit2);

		assertThat(pet.getVisits()).hasSize(2);
	}

	@Test
	void testPetWithNullBirthDate() {
		pet.setBirthDate(null);

		assertThat(pet.getBirthDate()).isNull();
	}

	@Test
	void testPetWithNullType() {
		pet.setType(null);

		assertThat(pet.getType()).isNull();
	}

}
