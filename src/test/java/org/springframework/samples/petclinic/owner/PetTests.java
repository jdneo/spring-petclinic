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
		pet = new Pet();
		pet.setName("Max");
		pet.setBirthDate(LocalDate.of(2020, 1, 1));

		petType = new PetType();
		petType.setName("Dog");
	}

	@Test
	void testSetAndGetName() {
		assertThat(pet.getName()).isEqualTo("Max");

		pet.setName("Bella");
		assertThat(pet.getName()).isEqualTo("Bella");
	}

	@Test
	void testSetAndGetBirthDate() {
		LocalDate birthDate = LocalDate.of(2020, 1, 1);
		assertThat(pet.getBirthDate()).isEqualTo(birthDate);

		LocalDate newBirthDate = LocalDate.of(2021, 6, 15);
		pet.setBirthDate(newBirthDate);
		assertThat(pet.getBirthDate()).isEqualTo(newBirthDate);
	}

	@Test
	void testSetAndGetType() {
		pet.setType(petType);
		assertThat(pet.getType()).isEqualTo(petType);
		assertThat(pet.getType().getName()).isEqualTo("Dog");
	}

	@Test
	void testAddVisit() {
		Visit visit1 = new Visit();
		visit1.setDate(LocalDate.now());
		visit1.setDescription("Checkup");

		Visit visit2 = new Visit();
		visit2.setDate(LocalDate.now().plusDays(7));
		visit2.setDescription("Vaccination");

		pet.addVisit(visit1);
		pet.addVisit(visit2);

		assertThat(pet.getVisits()).hasSize(2);
		assertThat(pet.getVisits()).contains(visit1, visit2);
	}

	@Test
	void testGetVisitsEmptyInitially() {
		Pet newPet = new Pet();
		assertThat(newPet.getVisits()).isEmpty();
	}

	@Test
	void testVisitsAreOrdered() {
		Visit visit1 = new Visit();
		visit1.setDate(LocalDate.of(2024, 1, 15));
		visit1.setDescription("First visit");

		Visit visit2 = new Visit();
		visit2.setDate(LocalDate.of(2024, 1, 10));
		visit2.setDescription("Earlier visit");

		Visit visit3 = new Visit();
		visit3.setDate(LocalDate.of(2024, 1, 20));
		visit3.setDescription("Latest visit");

		pet.addVisit(visit1);
		pet.addVisit(visit2);
		pet.addVisit(visit3);

		assertThat(pet.getVisits()).hasSize(3);
	}

	@Test
	void testIsNew() {
		Pet newPet = new Pet();
		assertThat(newPet.isNew()).isTrue();

		newPet.setId(1);
		assertThat(newPet.isNew()).isFalse();
	}

}
