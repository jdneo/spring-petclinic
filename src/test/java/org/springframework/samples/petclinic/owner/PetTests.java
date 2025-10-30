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
	void setUp() {
		pet = new Pet();
		petType = new PetType();
		petType.setName("dog");
	}

	@Test
	void testSetAndGetName() {
		pet.setName("Max");
		assertThat(pet.getName()).isEqualTo("Max");
	}

	@Test
	void testSetAndGetBirthDate() {
		LocalDate birthDate = LocalDate.of(2020, 1, 1);
		pet.setBirthDate(birthDate);
		assertThat(pet.getBirthDate()).isEqualTo(birthDate);
	}

	@Test
	void testSetAndGetType() {
		pet.setType(petType);
		assertThat(pet.getType()).isEqualTo(petType);
		assertThat(pet.getType().getName()).isEqualTo("dog");
	}

	@Test
	void testAddVisit() {
		Visit visit1 = new Visit();
		visit1.setDescription("First checkup");
		visit1.setDate(LocalDate.now());

		Visit visit2 = new Visit();
		visit2.setDescription("Second checkup");
		visit2.setDate(LocalDate.now().plusDays(7));

		pet.addVisit(visit1);
		pet.addVisit(visit2);

		assertThat(pet.getVisits()).hasSize(2);
		assertThat(pet.getVisits()).contains(visit1, visit2);
	}

	@Test
	void testGetVisitsReturnsEmptyCollectionWhenNoVisits() {
		assertThat(pet.getVisits()).isEmpty();
	}

}
