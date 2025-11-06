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
package org.springframework.samples.petclinic.model;

import org.junit.jupiter.api.Test;
import org.springframework.samples.petclinic.owner.Pet;
import org.springframework.samples.petclinic.owner.PetType;
import org.springframework.samples.petclinic.owner.Visit;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the {@link Pet} model.
 *
 * @author Copilot
 */
class PetTests {

	@Test
	void testGettersAndSetters() {
		Pet pet = new Pet();
		pet.setName("Max");
		LocalDate birthDate = LocalDate.of(2020, 1, 1);
		pet.setBirthDate(birthDate);

		PetType type = new PetType();
		type.setName("Dog");
		pet.setType(type);

		assertThat(pet.getName()).isEqualTo("Max");
		assertThat(pet.getBirthDate()).isEqualTo(birthDate);
		assertThat(pet.getType()).isEqualTo(type);
		assertThat(pet.getType().getName()).isEqualTo("Dog");
	}

	@Test
	void testAddVisit() {
		Pet pet = new Pet();
		Visit visit = new Visit();
		visit.setDescription("Regular checkup");

		pet.addVisit(visit);

		assertThat(pet.getVisits()).hasSize(1);
		assertThat(pet.getVisits()).contains(visit);
	}

	@Test
	void testAddMultipleVisits() {
		Pet pet = new Pet();
		Visit visit1 = new Visit();
		visit1.setDescription("Regular checkup");
		Visit visit2 = new Visit();
		visit2.setDescription("Vaccination");

		pet.addVisit(visit1);
		pet.addVisit(visit2);

		assertThat(pet.getVisits()).hasSize(2);
		assertThat(pet.getVisits()).contains(visit1, visit2);
	}

	@Test
	void testGetVisitsReturnsEmptyCollectionInitially() {
		Pet pet = new Pet();

		assertThat(pet.getVisits()).isEmpty();
	}

	@Test
	void testIsNew() {
		Pet pet = new Pet();

		assertThat(pet.isNew()).isTrue();

		pet.setId(1);

		assertThat(pet.isNew()).isFalse();
	}

}
