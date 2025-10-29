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
 *
 * @author Copilot
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
	void testGettersAndSetters() {
		assertThat(pet.getId()).isEqualTo(1);
		assertThat(pet.getName()).isEqualTo("Max");

		pet.setName("Buddy");
		assertThat(pet.getName()).isEqualTo("Buddy");
	}

	@Test
	void testBirthDate() {
		LocalDate birthDate = LocalDate.of(2020, 5, 15);
		pet.setBirthDate(birthDate);

		assertThat(pet.getBirthDate()).isEqualTo(birthDate);
	}

	@Test
	void testPetType() {
		pet.setType(petType);

		assertThat(pet.getType()).isNotNull();
		assertThat(pet.getType().getName()).isEqualTo("dog");
		assertThat(pet.getType().getId()).isEqualTo(1);
	}

	@Test
	void testAddVisit() {
		Visit visit1 = new Visit();
		visit1.setDescription("Annual checkup");
		visit1.setDate(LocalDate.now());

		Visit visit2 = new Visit();
		visit2.setDescription("Vaccination");
		visit2.setDate(LocalDate.now().plusDays(1));

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
	void testVisitsAreOrdered() {
		Visit visit1 = new Visit();
		visit1.setDescription("First visit");
		visit1.setDate(LocalDate.of(2023, 1, 1));

		Visit visit2 = new Visit();
		visit2.setDescription("Second visit");
		visit2.setDate(LocalDate.of(2023, 6, 1));

		Visit visit3 = new Visit();
		visit3.setDescription("Third visit");
		visit3.setDate(LocalDate.of(2023, 12, 1));

		// Add in non-chronological order
		pet.addVisit(visit2);
		pet.addVisit(visit1);
		pet.addVisit(visit3);

		assertThat(pet.getVisits()).hasSize(3);
		// Visits should be maintained in insertion order (Set maintains insertion order
		// via LinkedHashSet)
		assertThat(pet.getVisits()).containsExactly(visit2, visit1, visit3);
	}

	@Test
	void testIsNew() {
		Pet newPet = new Pet();
		assertThat(newPet.isNew()).isTrue();

		newPet.setId(1);
		assertThat(newPet.isNew()).isFalse();
	}

}
