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
 * Unit tests for Owner model class
 *
 * @author Copilot Agent
 */
class OwnerTests {

	private Owner owner;

	@BeforeEach
	void setUp() {
		owner = new Owner();
		owner.setFirstName("John");
		owner.setLastName("Doe");
		owner.setAddress("123 Main St");
		owner.setCity("Madison");
		owner.setTelephone("6085551023");
	}

	@Test
	void testOwnerGettersAndSetters() {
		assertThat(owner.getFirstName()).isEqualTo("John");
		assertThat(owner.getLastName()).isEqualTo("Doe");
		assertThat(owner.getAddress()).isEqualTo("123 Main St");
		assertThat(owner.getCity()).isEqualTo("Madison");
		assertThat(owner.getTelephone()).isEqualTo("6085551023");
	}

	@Test
	void testGetPetsReturnsEmptyListInitially() {
		assertThat(owner.getPets()).isEmpty();
	}

	@Test
	void testAddNewPet() {
		Pet pet = new Pet();
		pet.setName("Fluffy");
		pet.setBirthDate(LocalDate.of(2020, 1, 1));

		owner.addPet(pet);

		assertThat(owner.getPets()).hasSize(1);
		assertThat(owner.getPets().get(0)).isEqualTo(pet);
	}

	@Test
	void testAddExistingPetDoesNotAddAgain() {
		Pet pet = new Pet();
		pet.setName("Fluffy");
		pet.setBirthDate(LocalDate.of(2020, 1, 1));
		pet.setId(1);

		owner.addPet(pet);

		assertThat(owner.getPets()).isEmpty();
	}

	@Test
	void testGetPetByName() {
		Pet pet = new Pet();
		pet.setName("Fluffy");
		pet.setBirthDate(LocalDate.of(2020, 1, 1));
		owner.addPet(pet);

		Pet foundPet = owner.getPet("Fluffy");

		assertThat(foundPet).isNotNull();
		assertThat(foundPet.getName()).isEqualTo("Fluffy");
	}

	@Test
	void testGetPetByNameCaseInsensitive() {
		Pet pet = new Pet();
		pet.setName("Fluffy");
		pet.setBirthDate(LocalDate.of(2020, 1, 1));
		owner.addPet(pet);

		Pet foundPet = owner.getPet("fluffy");

		assertThat(foundPet).isNotNull();
		assertThat(foundPet.getName()).isEqualTo("Fluffy");
	}

	@Test
	void testGetPetByNameReturnsNullWhenNotFound() {
		Pet foundPet = owner.getPet("NonExistent");

		assertThat(foundPet).isNull();
	}

	@Test
	void testGetPetByNameWithIgnoreNew() {
		Pet newPet = new Pet();
		newPet.setName("NewPet");
		newPet.setBirthDate(LocalDate.of(2020, 1, 1));
		owner.addPet(newPet);

		Pet foundPet = owner.getPet("NewPet", true);

		assertThat(foundPet).isNull();
	}

	@Test
	void testGetPetByNameWithIgnoreNewFalse() {
		Pet newPet = new Pet();
		newPet.setName("NewPet");
		newPet.setBirthDate(LocalDate.of(2020, 1, 1));
		owner.addPet(newPet);

		Pet foundPet = owner.getPet("NewPet", false);

		assertThat(foundPet).isNotNull();
		assertThat(foundPet.getName()).isEqualTo("NewPet");
	}

	@Test
	void testGetPetById() {
		Pet pet = new Pet();
		pet.setName("Fluffy");
		pet.setBirthDate(LocalDate.of(2020, 1, 1));
		pet.setId(5);
		owner.getPets().add(pet);

		Pet foundPet = owner.getPet(5);

		assertThat(foundPet).isNotNull();
		assertThat(foundPet.getId()).isEqualTo(5);
	}

	@Test
	void testGetPetByIdReturnsNullWhenNotFound() {
		Pet foundPet = owner.getPet(999);

		assertThat(foundPet).isNull();
	}

	@Test
	void testGetPetByIdIgnoresNewPets() {
		Pet newPet = new Pet();
		newPet.setName("NewPet");
		newPet.setBirthDate(LocalDate.of(2020, 1, 1));
		owner.getPets().add(newPet);

		Pet foundPet = owner.getPet((Integer) null);

		assertThat(foundPet).isNull();
	}

	@Test
	void testToString() {
		owner.setId(1);

		String result = owner.toString();

		assertThat(result).contains("id = 1");
		assertThat(result).contains("new = false");
		assertThat(result).contains("lastName = 'Doe'");
		assertThat(result).contains("firstName = 'John'");
		assertThat(result).contains("address = '123 Main St'");
		assertThat(result).contains("city = 'Madison'");
		assertThat(result).contains("telephone = '6085551023'");
	}

	@Test
	void testAddVisit() {
		Pet pet = new Pet();
		pet.setName("Fluffy");
		pet.setBirthDate(LocalDate.of(2020, 1, 1));
		pet.setId(1);
		owner.getPets().add(pet);

		Visit visit = new Visit();
		visit.setDate(LocalDate.now());
		visit.setDescription("Checkup");

		owner.addVisit(1, visit);

		assertThat(pet.getVisits()).hasSize(1);
		assertThat(pet.getVisits()).contains(visit);
	}

	@Test
	void testAddVisitThrowsExceptionWhenPetIdIsNull() {
		Visit visit = new Visit();
		visit.setDate(LocalDate.now());
		visit.setDescription("Checkup");

		assertThatThrownBy(() -> owner.addVisit(null, visit)).isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("Pet identifier must not be null");
	}

	@Test
	void testAddVisitThrowsExceptionWhenVisitIsNull() {
		assertThatThrownBy(() -> owner.addVisit(1, null)).isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("Visit must not be null");
	}

	@Test
	void testAddVisitThrowsExceptionWhenPetNotFound() {
		Visit visit = new Visit();
		visit.setDate(LocalDate.now());
		visit.setDescription("Checkup");

		assertThatThrownBy(() -> owner.addVisit(999, visit)).isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("Invalid Pet identifier");
	}

}
