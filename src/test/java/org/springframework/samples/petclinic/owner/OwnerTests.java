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
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Test class for {@link Owner}
 */
class OwnerTests {

	private Owner owner;

	private Pet pet1;

	private Pet pet2;

	@BeforeEach
	void setup() {
		owner = new Owner();
		owner.setId(1);
		owner.setFirstName("John");
		owner.setLastName("Doe");
		owner.setAddress("123 Main St");
		owner.setCity("New York");
		owner.setTelephone("1234567890");

		PetType dog = new PetType();
		dog.setId(1);
		dog.setName("dog");

		PetType cat = new PetType();
		cat.setId(2);
		cat.setName("cat");

		pet1 = new Pet();
		pet1.setId(1);
		pet1.setName("Max");
		pet1.setType(dog);
		pet1.setBirthDate(LocalDate.of(2020, 1, 1));

		pet2 = new Pet();
		pet2.setId(2);
		pet2.setName("Bella");
		pet2.setType(cat);
		pet2.setBirthDate(LocalDate.of(2021, 5, 15));
	}

	@Test
	void testAddPet() {
		Pet newPet = new Pet();
		newPet.setName("Charlie");
		newPet.setType(new PetType());

		owner.addPet(newPet);

		assertThat(owner.getPets()).contains(newPet);
	}

	@Test
	void testAddPetWithExistingIdShouldNotBeAdded() {
		owner.addPet(pet1);

		assertThat(owner.getPets()).doesNotContain(pet1);
	}

	@Test
	void testGetPetByName() {
		owner.getPets().add(pet1);

		Pet found = owner.getPet("Max");

		assertThat(found).isNotNull();
		assertThat(found.getName()).isEqualTo("Max");
	}

	@Test
	void testGetPetByNameCaseInsensitive() {
		owner.getPets().add(pet1);

		Pet found = owner.getPet("max");

		assertThat(found).isNotNull();
		assertThat(found.getName()).isEqualTo("Max");
	}

	@Test
	void testGetPetByNameNotFound() {
		owner.getPets().add(pet1);

		Pet found = owner.getPet("Unknown");

		assertThat(found).isNull();
	}

	@Test
	void testGetPetById() {
		owner.getPets().add(pet1);
		owner.getPets().add(pet2);

		Pet found = owner.getPet(1);

		assertThat(found).isNotNull();
		assertThat(found.getId()).isEqualTo(1);
		assertThat(found.getName()).isEqualTo("Max");
	}

	@Test
	void testGetPetByIdNotFound() {
		owner.getPets().add(pet1);

		Pet found = owner.getPet(999);

		assertThat(found).isNull();
	}

	@Test
	void testGetPetByIdWithNewPet() {
		Pet newPet = new Pet();
		newPet.setName("NewPet");
		owner.getPets().add(newPet);

		Pet found = owner.getPet(newPet.getId());

		assertThat(found).isNull();
	}

	@Test
	void testGetPetByNameIgnoreNew() {
		Pet newPet = new Pet();
		newPet.setName("Charlie");
		owner.getPets().add(newPet);

		Pet found = owner.getPet("Charlie", true);

		assertThat(found).isNull();
	}

	@Test
	void testGetPetByNameWithIgnoreNewFalse() {
		Pet newPet = new Pet();
		newPet.setName("Charlie");
		owner.getPets().add(newPet);

		Pet found = owner.getPet("Charlie", false);

		assertThat(found).isNotNull();
		assertThat(found.getName()).isEqualTo("Charlie");
	}

	@Test
	void testAddVisit() {
		owner.getPets().add(pet1);

		Visit visit = new Visit();
		visit.setDate(LocalDate.now());
		visit.setDescription("Checkup");

		owner.addVisit(1, visit);

		assertThat(pet1.getVisits()).contains(visit);
	}

	@Test
	void testAddVisitWithNullPetId() {
		assertThatThrownBy(() -> owner.addVisit(null, new Visit())).isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("Pet identifier must not be null");
	}

	@Test
	void testAddVisitWithNullVisit() {
		owner.getPets().add(pet1);

		assertThatThrownBy(() -> owner.addVisit(1, null)).isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("Visit must not be null");
	}

	@Test
	void testAddVisitWithInvalidPetId() {
		owner.getPets().add(pet1);

		Visit visit = new Visit();
		visit.setDate(LocalDate.now());

		assertThatThrownBy(() -> owner.addVisit(999, visit)).isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("Invalid Pet identifier");
	}

	@Test
	void testToString() {
		String result = owner.toString();

		assertThat(result).contains("John");
		assertThat(result).contains("Doe");
		assertThat(result).contains("123 Main St");
		assertThat(result).contains("New York");
		assertThat(result).contains("1234567890");
	}

	@Test
	void testGetPetsReturnsEmptyListWhenNoPets() {
		assertThat(owner.getPets()).isNotNull();
		assertThat(owner.getPets()).isEmpty();
	}

	@Test
	void testMultiplePets() {
		owner.getPets().add(pet1);
		owner.getPets().add(pet2);

		assertThat(owner.getPets()).hasSize(2);
		assertThat(owner.getPets()).contains(pet1, pet2);
	}

}
