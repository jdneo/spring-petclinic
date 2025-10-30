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
 * Unit tests for {@link Owner}
 *
 * @author Copilot
 */
class OwnerTests {

	private Owner owner;

	private Pet pet1;

	private Pet pet2;

	@BeforeEach
	void setUp() {
		owner = new Owner();
		owner.setFirstName("John");
		owner.setLastName("Doe");
		owner.setAddress("123 Main St");
		owner.setCity("Springfield");
		owner.setTelephone("1234567890");

		pet1 = new Pet();
		pet1.setName("Max");
		PetType dog = new PetType();
		dog.setName("dog");
		pet1.setType(dog);
		pet1.setBirthDate(LocalDate.of(2020, 1, 1));

		pet2 = new Pet();
		pet2.setName("Bella");
		PetType cat = new PetType();
		cat.setName("cat");
		pet2.setType(cat);
		pet2.setBirthDate(LocalDate.of(2021, 6, 15));
	}

	@Test
	void testGetPetByName() {
		owner.addPet(pet1);
		Pet found = owner.getPet("Max");
		assertThat(found).isNotNull();
		assertThat(found.getName()).isEqualTo("Max");
	}

	@Test
	void testGetPetByNameCaseInsensitive() {
		owner.addPet(pet1);
		Pet found = owner.getPet("max");
		assertThat(found).isNotNull();
		assertThat(found.getName()).isEqualTo("Max");
	}

	@Test
	void testGetPetByNameNotFound() {
		owner.addPet(pet1);
		Pet found = owner.getPet("Unknown");
		assertThat(found).isNull();
	}

	@Test
	void testGetPetById() {
		pet1.setId(1);
		owner.getPets().add(pet1);
		Pet found = owner.getPet(1);
		assertThat(found).isNotNull();
		assertThat(found.getId()).isEqualTo(1);
	}

	@Test
	void testGetPetByIdNotFound() {
		pet1.setId(1);
		owner.getPets().add(pet1);
		Pet found = owner.getPet(999);
		assertThat(found).isNull();
	}

	@Test
	void testGetPetByIdWithNewPet() {
		Pet newPet = new Pet();
		newPet.setName("Charlie");
		owner.addPet(newPet);
		Pet found = owner.getPet(1);
		assertThat(found).isNull();
	}

	@Test
	void testGetPetByNameIgnoreNew() {
		Pet newPet = new Pet();
		newPet.setName("Charlie");
		owner.addPet(newPet);

		Pet found = owner.getPet("Charlie", true);
		assertThat(found).isNull();

		found = owner.getPet("Charlie", false);
		assertThat(found).isNotNull();
		assertThat(found.getName()).isEqualTo("Charlie");
	}

	@Test
	void testAddPet() {
		assertThat(owner.getPets()).isEmpty();
		owner.addPet(pet1);
		assertThat(owner.getPets()).hasSize(1);
		assertThat(owner.getPets()).contains(pet1);
	}

	@Test
	void testAddPetOnlyAddsNewPets() {
		owner.addPet(pet1);
		assertThat(owner.getPets()).hasSize(1);
		// Adding same new pet again still adds it because the check is isNew(), not
		// duplicate checking
		owner.addPet(pet1);
		assertThat(owner.getPets()).hasSize(2);
	}

	@Test
	void testAddPetDoesNotAddExistingPet() {
		pet1.setId(1);
		owner.addPet(pet1);
		assertThat(owner.getPets()).isEmpty();
	}

	@Test
	void testAddMultiplePets() {
		owner.addPet(pet1);
		owner.addPet(pet2);
		assertThat(owner.getPets()).hasSize(2);
	}

	@Test
	void testAddVisit() {
		pet1.setId(1);
		owner.getPets().add(pet1);
		Visit visit = new Visit();
		visit.setDescription("Annual checkup");
		visit.setDate(LocalDate.now());

		owner.addVisit(1, visit);

		assertThat(pet1.getVisits()).hasSize(1);
		assertThat(pet1.getVisits()).contains(visit);
	}

	@Test
	void testAddVisitWithNullPetId() {
		Visit visit = new Visit();
		assertThatThrownBy(() -> owner.addVisit(null, visit)).isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("Pet identifier must not be null!");
	}

	@Test
	void testAddVisitWithNullVisit() {
		owner.addPet(pet1);
		assertThatThrownBy(() -> owner.addVisit(1, null)).isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("Visit must not be null!");
	}

	@Test
	void testAddVisitWithInvalidPetId() {
		pet1.setId(1);
		owner.getPets().add(pet1);
		Visit visit = new Visit();
		assertThatThrownBy(() -> owner.addVisit(999, visit)).isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("Invalid Pet identifier!");
	}

	@Test
	void testToString() {
		owner.setId(1);
		String result = owner.toString();
		assertThat(result).contains("id = 1");
		assertThat(result).contains("lastName = 'Doe'");
		assertThat(result).contains("firstName = 'John'");
		assertThat(result).contains("address = '123 Main St'");
		assertThat(result).contains("city = 'Springfield'");
		assertThat(result).contains("telephone = '1234567890'");
	}

	@Test
	void testGettersAndSetters() {
		assertThat(owner.getFirstName()).isEqualTo("John");
		assertThat(owner.getLastName()).isEqualTo("Doe");
		assertThat(owner.getAddress()).isEqualTo("123 Main St");
		assertThat(owner.getCity()).isEqualTo("Springfield");
		assertThat(owner.getTelephone()).isEqualTo("1234567890");

		owner.setAddress("456 Oak Ave");
		owner.setCity("Shelbyville");
		owner.setTelephone("9876543210");

		assertThat(owner.getAddress()).isEqualTo("456 Oak Ave");
		assertThat(owner.getCity()).isEqualTo("Shelbyville");
		assertThat(owner.getTelephone()).isEqualTo("9876543210");
	}

}
