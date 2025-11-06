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
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.owner.Pet;
import org.springframework.samples.petclinic.owner.PetType;
import org.springframework.samples.petclinic.owner.Visit;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Test class for the {@link Owner} model.
 *
 * @author Copilot
 */
class OwnerTests {

	@Test
	void testGettersAndSetters() {
		Owner owner = new Owner();
		owner.setFirstName("John");
		owner.setLastName("Doe");
		owner.setAddress("123 Main St");
		owner.setCity("Springfield");
		owner.setTelephone("1234567890");

		assertThat(owner.getFirstName()).isEqualTo("John");
		assertThat(owner.getLastName()).isEqualTo("Doe");
		assertThat(owner.getAddress()).isEqualTo("123 Main St");
		assertThat(owner.getCity()).isEqualTo("Springfield");
		assertThat(owner.getTelephone()).isEqualTo("1234567890");
	}

	@Test
	void testAddPet() {
		Owner owner = new Owner();
		Pet pet = new Pet();
		pet.setName("Max");

		owner.addPet(pet);

		assertThat(owner.getPets()).hasSize(1);
		assertThat(owner.getPets()).contains(pet);
	}

	@Test
	void testAddPetOnlyWhenNew() {
		Owner owner = new Owner();
		Pet pet = new Pet();
		pet.setName("Max");
		pet.setId(1);

		owner.addPet(pet);

		assertThat(owner.getPets()).isEmpty();
	}

	@Test
	void testGetPetByName() {
		Owner owner = new Owner();
		Pet pet = new Pet();
		pet.setName("Max");
		pet.setId(1);
		owner.getPets().add(pet);

		Pet found = owner.getPet("Max");

		assertThat(found).isNotNull();
		assertThat(found.getName()).isEqualTo("Max");
	}

	@Test
	void testGetPetByNameCaseInsensitive() {
		Owner owner = new Owner();
		Pet pet = new Pet();
		pet.setName("Max");
		pet.setId(1);
		owner.getPets().add(pet);

		Pet found = owner.getPet("max");

		assertThat(found).isNotNull();
		assertThat(found.getName()).isEqualTo("Max");
	}

	@Test
	void testGetPetByNameNotFound() {
		Owner owner = new Owner();
		Pet pet = new Pet();
		pet.setName("Max");
		pet.setId(1);
		owner.getPets().add(pet);

		Pet found = owner.getPet("Buddy");

		assertThat(found).isNull();
	}

	@Test
	void testGetPetByNameIgnoreNew() {
		Owner owner = new Owner();
		Pet newPet = new Pet();
		newPet.setName("Max");
		owner.getPets().add(newPet);

		Pet found = owner.getPet("Max", true);

		assertThat(found).isNull();
	}

	@Test
	void testGetPetByNameIncludeNew() {
		Owner owner = new Owner();
		Pet newPet = new Pet();
		newPet.setName("Max");
		owner.getPets().add(newPet);

		Pet found = owner.getPet("Max", false);

		assertThat(found).isNotNull();
		assertThat(found.getName()).isEqualTo("Max");
	}

	@Test
	void testGetPetById() {
		Owner owner = new Owner();
		Pet pet = new Pet();
		pet.setName("Max");
		pet.setId(1);
		owner.getPets().add(pet);

		Pet found = owner.getPet(1);

		assertThat(found).isNotNull();
		assertThat(found.getId()).isEqualTo(1);
	}

	@Test
	void testGetPetByIdNotFound() {
		Owner owner = new Owner();
		Pet pet = new Pet();
		pet.setName("Max");
		pet.setId(1);
		owner.getPets().add(pet);

		Pet found = owner.getPet(2);

		assertThat(found).isNull();
	}

	@Test
	void testGetPetByIdIgnoresNewPets() {
		Owner owner = new Owner();
		Pet newPet = new Pet();
		newPet.setName("Max");
		owner.getPets().add(newPet);

		Pet found = owner.getPet(1);

		assertThat(found).isNull();
	}

	@Test
	void testAddVisit() {
		Owner owner = new Owner();
		Pet pet = new Pet();
		pet.setName("Max");
		pet.setId(1);
		owner.getPets().add(pet);

		Visit visit = new Visit();
		visit.setDescription("Checkup");

		owner.addVisit(1, visit);

		assertThat(pet.getVisits()).hasSize(1);
		assertThat(pet.getVisits()).contains(visit);
	}

	@Test
	void testAddVisitWithNullPetId() {
		Owner owner = new Owner();

		Visit visit = new Visit();
		visit.setDescription("Checkup");

		assertThatThrownBy(() -> owner.addVisit(null, visit)).isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("Pet identifier must not be null");
	}

	@Test
	void testAddVisitWithNullVisit() {
		Owner owner = new Owner();
		Pet pet = new Pet();
		pet.setId(1);
		owner.getPets().add(pet);

		assertThatThrownBy(() -> owner.addVisit(1, null)).isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("Visit must not be null");
	}

	@Test
	void testAddVisitWithInvalidPetId() {
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

		String toString = owner.toString();

		assertThat(toString).contains("id = 1");
		assertThat(toString).contains("new = false");
		assertThat(toString).contains("lastName = 'Doe'");
		assertThat(toString).contains("firstName = 'John'");
		assertThat(toString).contains("address = '123 Main St'");
		assertThat(toString).contains("city = 'Springfield'");
		assertThat(toString).contains("telephone = '1234567890'");
	}

}
