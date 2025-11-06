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
 * Unit tests for Pet model class
 *
 * @author Copilot Agent
 */
class PetTests {

	private Pet pet;

	@BeforeEach
	void setUp() {
		pet = new Pet();
	}

	@Test
	void testSetAndGetName() {
		pet.setName("Fluffy");

		assertThat(pet.getName()).isEqualTo("Fluffy");
	}

	@Test
	void testSetAndGetBirthDate() {
		LocalDate birthDate = LocalDate.of(2020, 5, 15);
		pet.setBirthDate(birthDate);

		assertThat(pet.getBirthDate()).isEqualTo(birthDate);
	}

	@Test
	void testSetAndGetType() {
		PetType type = new PetType();
		type.setName("dog");
		pet.setType(type);

		assertThat(pet.getType()).isEqualTo(type);
		assertThat(pet.getType().getName()).isEqualTo("dog");
	}

	@Test
	void testGetVisitsReturnsEmptyCollectionInitially() {
		assertThat(pet.getVisits()).isEmpty();
	}

	@Test
	void testAddVisit() {
		Visit visit = new Visit();
		visit.setDate(LocalDate.of(2023, 10, 1));
		visit.setDescription("Annual checkup");

		pet.addVisit(visit);

		assertThat(pet.getVisits()).hasSize(1);
		assertThat(pet.getVisits()).contains(visit);
	}

	@Test
	void testAddMultipleVisits() {
		Visit visit1 = new Visit();
		visit1.setDate(LocalDate.of(2023, 10, 1));
		visit1.setDescription("First visit");

		Visit visit2 = new Visit();
		visit2.setDate(LocalDate.of(2023, 11, 1));
		visit2.setDescription("Second visit");

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
	void testPetWithCompleteData() {
		PetType type = new PetType();
		type.setName("cat");

		pet.setName("Whiskers");
		pet.setBirthDate(LocalDate.of(2019, 3, 20));
		pet.setType(type);
		pet.setId(5);

		assertThat(pet.getName()).isEqualTo("Whiskers");
		assertThat(pet.getBirthDate()).isEqualTo(LocalDate.of(2019, 3, 20));
		assertThat(pet.getType().getName()).isEqualTo("cat");
		assertThat(pet.getId()).isEqualTo(5);
		assertThat(pet.isNew()).isFalse();
	}

}
