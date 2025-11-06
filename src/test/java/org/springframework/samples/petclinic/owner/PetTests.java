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

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for {@link Pet}.
 *
 * @author Wick Dynex
 */
class PetTests {

	private Pet pet;

	private PetType petType;

	@BeforeEach
	void setup() {
		pet = new Pet();
		pet.setId(1);
		pet.setName("Max");

		petType = new PetType();
		petType.setId(1);
		petType.setName("dog");
	}

	@Test
	void testSetAndGetBirthDate() {
		LocalDate birthDate = LocalDate.of(2020, 5, 15);
		pet.setBirthDate(birthDate);

		assertThat(pet.getBirthDate()).isEqualTo(birthDate);
	}

	@Test
	void testSetAndGetType() {
		pet.setType(petType);

		assertThat(pet.getType()).isNotNull();
		assertThat(pet.getType().getName()).isEqualTo("dog");
	}

	@Test
	void testAddVisit() {
		Visit visit1 = new Visit();
		visit1.setDescription("Checkup");

		Visit visit2 = new Visit();
		visit2.setDescription("Vaccination");

		pet.addVisit(visit1);
		pet.addVisit(visit2);

		assertThat(pet.getVisits()).hasSize(2);
		assertThat(pet.getVisits()).contains(visit1, visit2);
	}

	@Test
	void testGetVisitsReturnsEmptyCollectionInitially() {
		assertThat(pet.getVisits()).isNotNull();
		assertThat(pet.getVisits()).isEmpty();
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
