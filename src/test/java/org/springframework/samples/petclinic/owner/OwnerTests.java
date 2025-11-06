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
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Test class for {@link Owner}.
 *
 * @author Wick Dynex
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

		pet1 = new Pet();
		pet1.setId(1);
		pet1.setName("Max");
		pet1.setBirthDate(LocalDate.of(2020, 1, 1));

		pet2 = new Pet();
		pet2.setId(2);
		pet2.setName("Bella");
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
	void testGetPetByNameIgnoreCase() {
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
		Pet newPet = new Pet();
		newPet.setName("Charlie");
		owner.getPets().add(newPet);
		owner.getPets().add(pet1);

		// Should find the new pet when ignoreNew is false
		Pet foundPet = owner.getPet("Charlie", false);
		assertThat(foundPet).isNotNull();
		assertThat(foundPet.getName()).isEqualTo("Charlie");

		// Should not find the new pet when ignoreNew is true
		foundPet = owner.getPet("Charlie", true);
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
	void testGetPetByIdNotFound() {
		owner.getPets().add(pet1);

		Pet foundPet = owner.getPet(999);
		assertThat(foundPet).isNull();
	}

	@Test
	void testGetPetByIdIgnoresNewPets() {
		Pet newPet = new Pet();
		newPet.setName("Charlie");
		owner.getPets().add(newPet);

		Pet foundPet = owner.getPet((Integer) null);
		assertThat(foundPet).isNull();
	}

	@Test
	void testAddPet() {
		Pet newPet = new Pet();
		newPet.setName("Charlie");

		owner.addPet(newPet);
		assertThat(owner.getPets()).hasSize(1);
		assertThat(owner.getPets().get(0).getName()).isEqualTo("Charlie");
	}

	@Test
	void testAddPetWithExistingId() {
		owner.addPet(pet1);
		// Pet with ID should not be added
		assertThat(owner.getPets()).isEmpty();
	}

	@Test
	void testAddVisit() {
		owner.getPets().add(pet1);

		Visit visit = new Visit();
		visit.setDescription("Regular checkup");

		owner.addVisit(1, visit);
		assertThat(pet1.getVisits()).hasSize(1);
		assertThat(pet1.getVisits().iterator().next().getDescription()).isEqualTo("Regular checkup");
	}

	@Test
	void testAddVisitWithNullPetId() {
		assertThatThrownBy(() -> owner.addVisit(null, new Visit())).isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("Pet identifier must not be null");
	}

	@Test
	void testAddVisitWithNullVisit() {
		assertThatThrownBy(() -> owner.addVisit(1, null)).isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("Visit must not be null");
	}

	@Test
	void testAddVisitWithInvalidPetId() {
		owner.getPets().add(pet1);

		Visit visit = new Visit();
		assertThatThrownBy(() -> owner.addVisit(999, visit)).isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("Invalid Pet identifier");
	}

	@Test
	void testToString() {
		String result = owner.toString();
		assertThat(result).contains("id = 1");
		assertThat(result).contains("firstName = 'John'");
		assertThat(result).contains("lastName = 'Doe'");
		assertThat(result).contains("address = '123 Main St'");
		assertThat(result).contains("city = 'Springfield'");
		assertThat(result).contains("telephone = '1234567890'");
	}

	@Test
	void testSettersAndGetters() {
		Owner newOwner = new Owner();
		newOwner.setAddress("456 Elm St");
		newOwner.setCity("Shelbyville");
		newOwner.setTelephone("9876543210");

		assertThat(newOwner.getAddress()).isEqualTo("456 Elm St");
		assertThat(newOwner.getCity()).isEqualTo("Shelbyville");
		assertThat(newOwner.getTelephone()).isEqualTo("9876543210");
	}

}
