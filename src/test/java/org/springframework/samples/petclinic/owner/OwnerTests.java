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
		owner.setFirstName("John");
		owner.setLastName("Doe");
		owner.setAddress("123 Main St");
		owner.setCity("Springfield");
		owner.setTelephone("1234567890");

		pet1 = new Pet();
		pet1.setName("Max");
		pet1.setId(1);
		pet1.setBirthDate(LocalDate.of(2020, 1, 1));

		pet2 = new Pet();
		pet2.setName("Bella");
		pet2.setId(2);
		pet2.setBirthDate(LocalDate.of(2021, 6, 15));
	}

	@Test
	void testAddPet() {
		Pet newPet = new Pet();
		newPet.setName("Charlie");

		owner.addPet(newPet);

		assertThat(owner.getPets()).contains(newPet);
	}

	@Test
	void testAddPetWithExistingIdShouldNotAdd() {
		owner.addPet(pet1);

		int initialSize = owner.getPets().size();
		owner.addPet(pet1);

		assertThat(owner.getPets()).hasSize(initialSize);
	}

	@Test
	void testGetPetByName() {
		Pet newPet1 = new Pet();
		newPet1.setName("Max");
		owner.addPet(newPet1);
		newPet1.setId(1); // Set ID after adding to simulate persistence

		Pet newPet2 = new Pet();
		newPet2.setName("Bella");
		owner.addPet(newPet2);
		newPet2.setId(2);

		Pet foundPet = owner.getPet("Max");

		assertThat(foundPet).isNotNull();
		assertThat(foundPet.getName()).isEqualTo("Max");
	}

	@Test
	void testGetPetByNameCaseInsensitive() {
		Pet newPet = new Pet();
		newPet.setName("Max");
		owner.addPet(newPet);
		newPet.setId(1);

		Pet foundPet = owner.getPet("max");

		assertThat(foundPet).isNotNull();
		assertThat(foundPet.getName()).isEqualTo("Max");
	}

	@Test
	void testGetPetByNameNotFound() {
		owner.addPet(pet1);

		Pet foundPet = owner.getPet("NonExistent");

		assertThat(foundPet).isNull();
	}

	@Test
	void testGetPetById() {
		Pet newPet1 = new Pet();
		newPet1.setName("Max");
		owner.addPet(newPet1);
		newPet1.setId(1);

		Pet newPet2 = new Pet();
		newPet2.setName("Bella");
		owner.addPet(newPet2);
		newPet2.setId(2);

		Pet foundPet = owner.getPet(1);

		assertThat(foundPet).isNotNull();
		assertThat(foundPet.getId()).isEqualTo(1);
		assertThat(foundPet.getName()).isEqualTo("Max");
	}

	@Test
	void testGetPetByIdNotFound() {
		owner.addPet(pet1);

		Pet foundPet = owner.getPet(999);

		assertThat(foundPet).isNull();
	}

	@Test
	void testGetPetByIdWithNewPet() {
		Pet newPet = new Pet();
		newPet.setName("NewPet");
		owner.addPet(newPet);

		Pet foundPet = owner.getPet(1);

		assertThat(foundPet).isNull();
	}

	@Test
	void testGetPetByNameIgnoreNew() {
		Pet newPet = new Pet();
		newPet.setName("NewPet");
		owner.addPet(newPet);

		Pet foundPet = owner.getPet("NewPet", true);

		assertThat(foundPet).isNull();
	}

	@Test
	void testGetPetByNameIncludeNew() {
		Pet newPet = new Pet();
		newPet.setName("NewPet");
		owner.addPet(newPet);

		Pet foundPet = owner.getPet("NewPet", false);

		assertThat(foundPet).isNotNull();
		assertThat(foundPet.getName()).isEqualTo("NewPet");
	}

	@Test
	void testAddVisit() {
		Pet newPet = new Pet();
		newPet.setName("Max");
		owner.addPet(newPet);
		newPet.setId(1);

		Visit visit = new Visit();
		visit.setDate(LocalDate.now());
		visit.setDescription("Checkup");

		owner.addVisit(1, visit);

		assertThat(newPet.getVisits()).contains(visit);
	}

	@Test
	void testAddVisitWithNullPetIdThrowsException() {
		Visit visit = new Visit();
		visit.setDate(LocalDate.now());
		visit.setDescription("Checkup");

		assertThatThrownBy(() -> owner.addVisit(null, visit)).isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("Pet identifier must not be null");
	}

	@Test
	void testAddVisitWithNullVisitThrowsException() {
		owner.addPet(pet1);

		assertThatThrownBy(() -> owner.addVisit(1, null)).isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("Visit must not be null");
	}

	@Test
	void testAddVisitWithInvalidPetIdThrowsException() {
		Pet newPet = new Pet();
		newPet.setName("Max");
		owner.addPet(newPet);
		newPet.setId(1);

		Visit visit = new Visit();
		visit.setDate(LocalDate.now());
		visit.setDescription("Checkup");

		assertThatThrownBy(() -> owner.addVisit(999, visit)).isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("Invalid Pet identifier");
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

		owner.setFirstName("Jane");
		owner.setLastName("Smith");
		owner.setAddress("456 Oak Ave");
		owner.setCity("Portland");
		owner.setTelephone("0987654321");

		assertThat(owner.getFirstName()).isEqualTo("Jane");
		assertThat(owner.getLastName()).isEqualTo("Smith");
		assertThat(owner.getAddress()).isEqualTo("456 Oak Ave");
		assertThat(owner.getCity()).isEqualTo("Portland");
		assertThat(owner.getTelephone()).isEqualTo("0987654321");
	}

}
