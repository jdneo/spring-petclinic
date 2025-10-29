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
 *
 * @author Copilot
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
		owner.setCity("Springfield");
		owner.setTelephone("1234567890");

		// Create first pet
		pet1 = new Pet();
		pet1.setId(10);
		pet1.setName("Max");
		PetType dog = new PetType();
		dog.setId(1);
		dog.setName("dog");
		pet1.setType(dog);
		pet1.setBirthDate(LocalDate.of(2020, 1, 1));

		// Create second pet
		pet2 = new Pet();
		pet2.setId(20);
		pet2.setName("Bella");
		PetType cat = new PetType();
		cat.setId(2);
		cat.setName("cat");
		pet2.setType(cat);
		pet2.setBirthDate(LocalDate.of(2021, 6, 15));
	}

	@Test
	void testGetPetByName() {
		owner.getPets().add(pet1);
		owner.getPets().add(pet2);

		Pet foundPet = owner.getPet("Max");
		assertThat(foundPet).isNotNull();
		assertThat(foundPet.getName()).isEqualTo("Max");
	}

	@Test
	void testGetPetByNameCaseInsensitive() {
		owner.getPets().add(pet1);

		Pet foundPet = owner.getPet("max");
		assertThat(foundPet).isNotNull();
		assertThat(foundPet.getName()).isEqualTo("Max");
	}

	@Test
	void testGetPetByNameNotFound() {
		owner.getPets().add(pet1);

		Pet foundPet = owner.getPet("NonExistent");
		assertThat(foundPet).isNull();
	}

	@Test
	void testGetPetByNameIgnoreNew() {
		// Create a new pet (without ID)
		Pet newPet = new Pet();
		newPet.setName("Charlie");
		owner.getPets().add(newPet);

		// Should find the new pet when ignoreNew is false
		Pet foundPet = owner.getPet("Charlie", false);
		assertThat(foundPet).isNotNull();
		assertThat(foundPet.getName()).isEqualTo("Charlie");

		// Should not find the new pet when ignoreNew is true
		Pet notFoundPet = owner.getPet("Charlie", true);
		assertThat(notFoundPet).isNull();
	}

	@Test
	void testGetPetById() {
		owner.getPets().add(pet1);
		owner.getPets().add(pet2);

		Pet foundPet = owner.getPet(10);
		assertThat(foundPet).isNotNull();
		assertThat(foundPet.getId()).isEqualTo(10);
		assertThat(foundPet.getName()).isEqualTo("Max");
	}

	@Test
	void testGetPetByIdNotFound() {
		owner.getPets().add(pet1);

		Pet foundPet = owner.getPet(999);
		assertThat(foundPet).isNull();
	}

	@Test
	void testGetPetByIdIgnoresNewPets() {
		// Create a new pet without ID
		Pet newPet = new Pet();
		newPet.setName("Charlie");
		owner.getPets().add(newPet);

		// Should not find new pets by ID
		Pet foundPet = owner.getPet((Integer) null);
		assertThat(foundPet).isNull();
	}

	@Test
	void testAddPet() {
		Pet newPet = new Pet();
		newPet.setName("Charlie");

		owner.addPet(newPet);
		assertThat(owner.getPets()).hasSize(1);
		assertThat(owner.getPets()).contains(newPet);
	}

	@Test
	void testAddPetDoesNotAddExistingPet() {
		pet1.setId(10); // Already has an ID
		owner.getPets().add(pet1);

		owner.addPet(pet1);
		// Should still only have one pet, not duplicate
		assertThat(owner.getPets()).hasSize(1);
	}

	@Test
	void testAddVisit() {
		owner.getPets().add(pet1);

		Visit visit = new Visit();
		visit.setDescription("Checkup");
		visit.setDate(LocalDate.now());

		owner.addVisit(10, visit);

		assertThat(pet1.getVisits()).hasSize(1);
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

		assertThatThrownBy(() -> owner.addVisit(10, null)).isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("Visit must not be null");
	}

	@Test
	void testAddVisitWithInvalidPetId() {
		owner.getPets().add(pet1);

		Visit visit = new Visit();
		visit.setDescription("Checkup");

		assertThatThrownBy(() -> owner.addVisit(999, visit)).isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("Invalid Pet identifier");
	}

	@Test
	void testToString() {
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
		owner.setCity("Portland");
		owner.setTelephone("9876543210");

		assertThat(owner.getAddress()).isEqualTo("456 Oak Ave");
		assertThat(owner.getCity()).isEqualTo("Portland");
		assertThat(owner.getTelephone()).isEqualTo("9876543210");
	}

}
