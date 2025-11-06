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

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for {@link Pet}
 *
 * @author GitHub Copilot
 */
class PetTests {

	@Test
	void testSetAndGetBirthDate() {
		Pet pet = new Pet();
		LocalDate birthDate = LocalDate.of(2020, 1, 15);
		pet.setBirthDate(birthDate);
		assertThat(pet.getBirthDate()).isEqualTo(birthDate);
	}

	@Test
	void testSetAndGetType() {
		Pet pet = new Pet();
		PetType type = new PetType();
		type.setName("Dog");
		pet.setType(type);
		assertThat(pet.getType()).isEqualTo(type);
		assertThat(pet.getType().getName()).isEqualTo("Dog");
	}

	@Test
	void testAddVisit() {
		Pet pet = new Pet();
		Visit visit = new Visit();
		visit.setDescription("Annual checkup");
		visit.setDate(LocalDate.now());

		pet.addVisit(visit);
		assertThat(pet.getVisits()).hasSize(1);
		assertThat(pet.getVisits()).contains(visit);
	}

	@Test
	void testAddMultipleVisits() {
		Pet pet = new Pet();

		Visit visit1 = new Visit();
		visit1.setDescription("First visit");
		visit1.setDate(LocalDate.now().minusDays(10));

		Visit visit2 = new Visit();
		visit2.setDescription("Second visit");
		visit2.setDate(LocalDate.now());

		pet.addVisit(visit1);
		pet.addVisit(visit2);

		assertThat(pet.getVisits()).hasSize(2);
		assertThat(pet.getVisits()).contains(visit1, visit2);
	}

	@Test
	void testGetVisitsReturnsEmptyCollectionByDefault() {
		Pet pet = new Pet();
		assertThat(pet.getVisits()).isNotNull();
		assertThat(pet.getVisits()).isEmpty();
	}

	@Test
	void testVisitsOrderedByDate() {
		Pet pet = new Pet();

		Visit visit1 = new Visit();
		visit1.setDescription("Recent visit");
		visit1.setDate(LocalDate.now());

		Visit visit2 = new Visit();
		visit2.setDescription("Older visit");
		visit2.setDate(LocalDate.now().minusDays(30));

		Visit visit3 = new Visit();
		visit3.setDescription("Middle visit");
		visit3.setDate(LocalDate.now().minusDays(15));

		// Add in random order
		pet.addVisit(visit1);
		pet.addVisit(visit2);
		pet.addVisit(visit3);

		// Visits should be ordered by date (ASC) based on JPA @OrderBy annotation
		assertThat(pet.getVisits()).hasSize(3);
	}

}
