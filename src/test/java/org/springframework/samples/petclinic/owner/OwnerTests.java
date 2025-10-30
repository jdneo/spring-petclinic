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
	void setUp() {
		owner = new Owner();
		owner.setFirstName("John");
		owner.setLastName("Doe");
		owner.setAddress("123 Main St");
		owner.setCity("Springfield");
		owner.setTelephone("1234567890");

		pet1 = new Pet();
		pet1.setName("Max");
		pet1.setId(1);
		PetType dog = new PetType();
		dog.setName("dog");
		pet1.setType(dog);

		pet2 = new Pet();
		pet2.setName("Bella");
		pet2.setId(2);
		PetType cat = new PetType();
		cat.setName("cat");
		pet2.setType(cat);
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
	void testGetPetByNameReturnsNullWhenNotFound() {
		owner.getPets().add(pet1);

		Pet foundPet = owner.getPet("NonExistent");
		assertThat(foundPet).isNull();
	}

	@Test
	void testGetPetById() {
		owner.getPets().add(pet1);
		owner.getPets().add(pet2);

		Pet foundPet = owner.getPet(1);
		assertThat(foundPet).isNotNull();
		assertThat(foundPet.getId()).isEqualTo(1);
		assertThat(foundPet.getName()).isEqualTo("Max");
	}

	@Test
	void testGetPetByIdReturnsNullWhenNotFound() {
		owner.getPets().add(pet1);

		Pet foundPet = owner.getPet(99);
		assertThat(foundPet).isNull();
	}

	@Test
	void testAddPet() {
		Pet newPet = new Pet();
		newPet.setName("Charlie");

		owner.addPet(newPet);
		assertThat(owner.getPets()).contains(newPet);
	}

	@Test
	void testAddPetDoesNotAddExistingPet() {
		owner.getPets().add(pet1);
		int initialSize = owner.getPets().size();

		owner.addPet(pet1);
		assertThat(owner.getPets()).hasSize(initialSize);
	}

	@Test
	void testAddVisit() {
		owner.getPets().add(pet1);

		Visit visit = new Visit();
		visit.setDescription("Checkup");
		visit.setDate(LocalDate.now());

		owner.addVisit(1, visit);

		assertThat(pet1.getVisits()).contains(visit);
	}

	@Test
	void testAddVisitThrowsExceptionForNullPetId() {
		Visit visit = new Visit();
		visit.setDescription("Checkup");

		assertThatThrownBy(() -> owner.addVisit(null, visit)).isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("Pet identifier must not be null");
	}

	@Test
	void testAddVisitThrowsExceptionForNullVisit() {
		owner.getPets().add(pet1);

		assertThatThrownBy(() -> owner.addVisit(1, null)).isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("Visit must not be null");
	}

	@Test
	void testAddVisitThrowsExceptionForInvalidPetId() {
		owner.getPets().add(pet1);

		Visit visit = new Visit();
		visit.setDescription("Checkup");

		assertThatThrownBy(() -> owner.addVisit(99, visit)).isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("Invalid Pet identifier");
	}

	@Test
	void testToString() {
		owner.setId(1);
		String ownerString = owner.toString();

		assertThat(ownerString).contains("id = 1");
		assertThat(ownerString).contains("lastName = 'Doe'");
		assertThat(ownerString).contains("firstName = 'John'");
		assertThat(ownerString).contains("address = '123 Main St'");
		assertThat(ownerString).contains("city = 'Springfield'");
		assertThat(ownerString).contains("telephone = '1234567890'");
	}

	@Test
	void testGetPetWithIgnoreNew() {
		Pet newPet = new Pet();
		newPet.setName("NewPet");
		owner.getPets().add(newPet);

		Pet foundPet = owner.getPet("NewPet", true);
		assertThat(foundPet).isNull();

		foundPet = owner.getPet("NewPet", false);
		assertThat(foundPet).isNotNull();
	}

}
