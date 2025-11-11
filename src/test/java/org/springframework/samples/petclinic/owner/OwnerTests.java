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
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Unit tests for Owner
 */
class OwnerTests {

	@Test
	void testAddressGetterAndSetter() {
		Owner owner = new Owner();
		assertThat(owner.getAddress()).isNull();

		owner.setAddress("123 Main St");
		assertThat(owner.getAddress()).isEqualTo("123 Main St");
	}

	@Test
	void testCityGetterAndSetter() {
		Owner owner = new Owner();
		assertThat(owner.getCity()).isNull();

		owner.setCity("Springfield");
		assertThat(owner.getCity()).isEqualTo("Springfield");
	}

	@Test
	void testTelephoneGetterAndSetter() {
		Owner owner = new Owner();
		assertThat(owner.getTelephone()).isNull();

		owner.setTelephone("1234567890");
		assertThat(owner.getTelephone()).isEqualTo("1234567890");
	}

	@Test
	void testGetPetsReturnsEmptyList() {
		Owner owner = new Owner();
		assertThat(owner.getPets()).isEmpty();
	}

	@Test
	void testAddNewPet() {
		Owner owner = new Owner();
		Pet pet = new Pet();
		pet.setName("Fluffy");

		owner.addPet(pet);
		assertThat(owner.getPets()).hasSize(1);
		assertThat(owner.getPets().get(0)).isEqualTo(pet);
	}

	@Test
	void testAddPetWithIdDoesNotAdd() {
		Owner owner = new Owner();
		Pet pet = new Pet();
		pet.setId(123);
		pet.setName("Fluffy");

		owner.addPet(pet);
		assertThat(owner.getPets()).isEmpty();
	}

	@Test
	void testGetPetByName() {
		Owner owner = new Owner();
		Pet pet = new Pet();
		pet.setName("Max");
		owner.addPet(pet);

		Pet found = owner.getPet("Max");
		assertThat(found).isEqualTo(pet);
	}

	@Test
	void testGetPetByNameCaseInsensitive() {
		Owner owner = new Owner();
		Pet pet = new Pet();
		pet.setName("Max");
		owner.addPet(pet);

		Pet found = owner.getPet("max");
		assertThat(found).isEqualTo(pet);
	}

	@Test
	void testGetPetByNameNotFound() {
		Owner owner = new Owner();
		Pet pet = new Pet();
		pet.setName("Max");
		owner.addPet(pet);

		Pet found = owner.getPet("Fluffy");
		assertThat(found).isNull();
	}

	@Test
	void testGetPetById() {
		Owner owner = new Owner();
		Pet pet = new Pet();
		pet.setId(42);
		pet.setName("Max");
		owner.getPets().add(pet);

		Pet found = owner.getPet(42);
		assertThat(found).isEqualTo(pet);
	}

	@Test
	void testGetPetByIdNotFound() {
		Owner owner = new Owner();
		Pet pet = new Pet();
		pet.setId(42);
		pet.setName("Max");
		owner.getPets().add(pet);

		Pet found = owner.getPet(999);
		assertThat(found).isNull();
	}

	@Test
	void testGetPetIgnoreNew() {
		Owner owner = new Owner();
		Pet pet = new Pet();
		pet.setName("Max");
		owner.addPet(pet);

		Pet found = owner.getPet("Max", true);
		assertThat(found).isNull();
	}

	@Test
	void testGetPetDoNotIgnoreNew() {
		Owner owner = new Owner();
		Pet pet = new Pet();
		pet.setName("Max");
		owner.addPet(pet);

		Pet found = owner.getPet("Max", false);
		assertThat(found).isEqualTo(pet);
	}

	@Test
	void testAddVisitToPet() {
		Owner owner = new Owner();
		Pet pet = new Pet();
		pet.setId(1);
		pet.setName("Max");
		owner.getPets().add(pet);

		Visit visit = new Visit();
		visit.setDescription("Checkup");

		owner.addVisit(1, visit);
		assertThat(pet.getVisits()).hasSize(1);
		assertThat(pet.getVisits()).contains(visit);
	}

	@Test
	void testAddVisitThrowsExceptionWhenPetIdIsNull() {
		Owner owner = new Owner();
		Visit visit = new Visit();

		assertThatThrownBy(() -> owner.addVisit(null, visit)).isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("Pet identifier must not be null");
	}

	@Test
	void testAddVisitThrowsExceptionWhenVisitIsNull() {
		Owner owner = new Owner();

		assertThatThrownBy(() -> owner.addVisit(1, null)).isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("Visit must not be null");
	}

	@Test
	void testAddVisitThrowsExceptionWhenPetNotFound() {
		Owner owner = new Owner();

		Visit visit = new Visit();
		visit.setDescription("Checkup");

		assertThatThrownBy(() -> owner.addVisit(999, visit)).isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("Invalid Pet identifier");
	}

	@Test
	void testToString() {
		Owner owner = new Owner();
		owner.setId(1);
		owner.setFirstName("John");
		owner.setLastName("Doe");
		owner.setAddress("123 Main St");
		owner.setCity("Springfield");
		owner.setTelephone("1234567890");

		String result = owner.toString();
		assertThat(result).contains("id = 1");
		assertThat(result).contains("new = false");
		assertThat(result).contains("lastName = 'Doe'");
		assertThat(result).contains("firstName = 'John'");
		assertThat(result).contains("address = '123 Main St'");
		assertThat(result).contains("city = 'Springfield'");
		assertThat(result).contains("telephone = '1234567890'");
	}

}
