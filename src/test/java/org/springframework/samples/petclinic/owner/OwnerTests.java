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
 * Test class for {@link Owner}
 *
 * @author GitHub Copilot
 */
class OwnerTests {

	@Test
	void testGetPetByName() {
		Owner owner = new Owner();
		Pet pet = new Pet();
		pet.setName("Max");
		owner.addPet(pet);

		Pet found = owner.getPet("Max");
		assertThat(found).isNotNull();
		assertThat(found.getName()).isEqualTo("Max");
	}

	@Test
	void testGetPetByNameCaseInsensitive() {
		Owner owner = new Owner();
		Pet pet = new Pet();
		pet.setName("Max");
		owner.addPet(pet);

		Pet found = owner.getPet("max");
		assertThat(found).isNotNull();
		assertThat(found.getName()).isEqualTo("Max");
	}

	@Test
	void testGetPetByNameNotFound() {
		Owner owner = new Owner();
		Pet pet = new Pet();
		pet.setName("Max");
		owner.addPet(pet);

		Pet found = owner.getPet("Unknown");
		assertThat(found).isNull();
	}

	@Test
	void testGetPetByNameIgnoreNew() {
		Owner owner = new Owner();
		Pet newPet = new Pet();
		newPet.setName("Buddy");
		owner.addPet(newPet);

		// Manually add a saved pet to the list (simulating persistence)
		Pet savedPet = new Pet();
		savedPet.setName("Max");
		savedPet.setId(1);
		owner.getPets().add(savedPet);

		// Without ignoring new pets
		Pet found = owner.getPet("Buddy", false);
		assertThat(found).isNotNull();
		assertThat(found.getName()).isEqualTo("Buddy");

		// Ignoring new pets
		found = owner.getPet("Buddy", true);
		assertThat(found).isNull();

		// Saved pet should be found regardless
		found = owner.getPet("Max", true);
		assertThat(found).isNotNull();
		assertThat(found.getName()).isEqualTo("Max");
	}

	@Test
	void testGetPetById() {
		Owner owner = new Owner();
		// Manually add a saved pet to the list (simulating persistence)
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
		// Manually add a saved pet to the list
		Pet pet = new Pet();
		pet.setName("Max");
		pet.setId(1);
		owner.getPets().add(pet);

		Pet found = owner.getPet(999);
		assertThat(found).isNull();
	}

	@Test
	void testGetPetByIdIgnoresNewPets() {
		Owner owner = new Owner();
		Pet newPet = new Pet();
		newPet.setName("Buddy");
		owner.addPet(newPet);

		Integer nullId = null;
		Pet found = owner.getPet(nullId);
		assertThat(found).isNull();
	}

	@Test
	void testAddPetOnlyAddsNewPets() {
		Owner owner = new Owner();
		Pet newPet = new Pet();
		newPet.setName("Buddy");
		owner.addPet(newPet);
		assertThat(owner.getPets()).hasSize(1);

		Pet existingPet = new Pet();
		existingPet.setName("Max");
		existingPet.setId(1);
		owner.addPet(existingPet);
		// Should not be added since it's not new
		assertThat(owner.getPets()).hasSize(1);
	}

	@Test
	void testAddVisitSuccess() {
		Owner owner = new Owner();
		// Manually add a saved pet to the list
		Pet pet = new Pet();
		pet.setId(1);
		owner.getPets().add(pet);

		Visit visit = new Visit();
		visit.setDescription("Checkup");
		visit.setDate(LocalDate.now());

		owner.addVisit(1, visit);
		assertThat(pet.getVisits()).hasSize(1);
	}

	@Test
	void testAddVisitNullPetId() {
		Owner owner = new Owner();

		Visit visit = new Visit();
		visit.setDescription("Checkup");

		assertThatThrownBy(() -> owner.addVisit(null, visit))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("Pet identifier must not be null!");
	}

	@Test
	void testAddVisitNullVisit() {
		Owner owner = new Owner();

		assertThatThrownBy(() -> owner.addVisit(1, null))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("Visit must not be null!");
	}

	@Test
	void testAddVisitInvalidPetId() {
		Owner owner = new Owner();
		// Manually add a saved pet to the list
		Pet pet = new Pet();
		pet.setId(1);
		owner.getPets().add(pet);

		Visit visit = new Visit();
		visit.setDescription("Checkup");

		assertThatThrownBy(() -> owner.addVisit(999, visit))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("Invalid Pet identifier!");
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
		assertThat(result).contains("1");
		assertThat(result).contains("Doe");
		assertThat(result).contains("John");
		assertThat(result).contains("123 Main St");
		assertThat(result).contains("Springfield");
		assertThat(result).contains("1234567890");
	}

}
