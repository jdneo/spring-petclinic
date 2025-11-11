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
 * Unit tests for the Owner model class.
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
		pet1.setName("Fluffy");
		pet1.setId(1);

		pet2 = new Pet();
		pet2.setName("Spot");
		pet2.setId(2);
	}

	@Test
	void testGetAddress() {
		assertThat(owner.getAddress()).isEqualTo("123 Main St");
	}

	@Test
	void testSetAddress() {
		owner.setAddress("456 Oak Ave");
		assertThat(owner.getAddress()).isEqualTo("456 Oak Ave");
	}

	@Test
	void testGetCity() {
		assertThat(owner.getCity()).isEqualTo("Springfield");
	}

	@Test
	void testSetCity() {
		owner.setCity("Shelbyville");
		assertThat(owner.getCity()).isEqualTo("Shelbyville");
	}

	@Test
	void testGetTelephone() {
		assertThat(owner.getTelephone()).isEqualTo("1234567890");
	}

	@Test
	void testSetTelephone() {
		owner.setTelephone("9876543210");
		assertThat(owner.getTelephone()).isEqualTo("9876543210");
	}

	@Test
	void testGetPets() {
		assertThat(owner.getPets()).isNotNull().isEmpty();
	}

	@Test
	void testAddPet() {
		Pet newPet = new Pet();
		newPet.setName("Buddy");
		owner.addPet(newPet);
		assertThat(owner.getPets()).hasSize(1).contains(newPet);
	}

	@Test
	void testAddPetOnlyAddsNewPets() {
		owner.addPet(pet1);
		assertThat(owner.getPets()).hasSize(0);
	}

	@Test
	void testGetPetByName() {
		Pet newPet = new Pet();
		newPet.setName("Max");
		owner.addPet(newPet);
		Pet found = owner.getPet("Max");
		assertThat(found).isEqualTo(newPet);
	}

	@Test
	void testGetPetByNameCaseInsensitive() {
		Pet newPet = new Pet();
		newPet.setName("Max");
		owner.addPet(newPet);
		Pet found = owner.getPet("max");
		assertThat(found).isEqualTo(newPet);
	}

	@Test
	void testGetPetByNameNotFound() {
		Pet found = owner.getPet("NonExistent");
		assertThat(found).isNull();
	}

	@Test
	void testGetPetByNameWithIgnoreNew() {
		Pet newPet = new Pet();
		newPet.setName("Buddy");
		owner.addPet(newPet);
		Pet found = owner.getPet("Buddy", true);
		assertThat(found).isNull();
	}

	@Test
	void testGetPetById() {
		owner.getPets().add(pet1);
		owner.getPets().add(pet2);
		Pet found = owner.getPet(1);
		assertThat(found).isEqualTo(pet1);
	}

	@Test
	void testGetPetByIdNotFound() {
		owner.getPets().add(pet1);
		Pet found = owner.getPet(999);
		assertThat(found).isNull();
	}

	@Test
	void testGetPetByIdIgnoresNewPets() {
		Pet newPet = new Pet();
		newPet.setName("New");
		owner.getPets().add(newPet);
		Integer nullId = null;
		Pet found = owner.getPet(nullId);
		assertThat(found).isNull();
	}

	@Test
	void testToString() {
		owner.setId(42);
		String result = owner.toString();
		assertThat(result).contains("id", "42", "lastName", "Doe", "firstName", "John", "address", "123 Main St",
				"city", "Springfield", "telephone", "1234567890");
	}

	@Test
	void testAddVisit() {
		owner.getPets().add(pet1);
		Visit visit = new Visit();
		visit.setDescription("Annual checkup");
		visit.setDate(LocalDate.now());
		owner.addVisit(1, visit);
		assertThat(pet1.getVisits()).hasSize(1).contains(visit);
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
		assertThatThrownBy(() -> owner.addVisit(999, new Visit())).isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("Invalid Pet identifier");
	}

}
