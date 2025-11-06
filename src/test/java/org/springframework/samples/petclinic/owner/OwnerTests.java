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
import org.springframework.util.SerializationUtils;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Unit tests for Owner model class
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
		PetType petType1 = new PetType();
		petType1.setName("cat");
		pet1.setType(petType1);

		pet2 = new Pet();
		pet2.setName("Buddy");
		pet2.setId(2);
		PetType petType2 = new PetType();
		petType2.setName("dog");
		pet2.setType(petType2);
	}

	@Test
	void testGettersAndSetters() {
		assertThat(owner.getFirstName()).isEqualTo("John");
		assertThat(owner.getLastName()).isEqualTo("Doe");
		assertThat(owner.getAddress()).isEqualTo("123 Main St");
		assertThat(owner.getCity()).isEqualTo("Springfield");
		assertThat(owner.getTelephone()).isEqualTo("1234567890");
	}

	@Test
	void testGetPetsReturnsEmptyListInitially() {
		assertThat(owner.getPets()).isEmpty();
	}

	@Test
	void testAddPetWithNewPet() {
		Pet newPet = new Pet();
		newPet.setName("Max");
		owner.addPet(newPet);
		assertThat(owner.getPets()).hasSize(1);
		assertThat(owner.getPets().get(0).getName()).isEqualTo("Max");
	}

	@Test
	void testAddPetWithExistingPetDoesNotAdd() {
		owner.getPets().add(pet1);
		assertThat(owner.getPets()).hasSize(1);
		owner.addPet(pet1);
		assertThat(owner.getPets()).hasSize(1);
	}

	@Test
	void testGetPetByName() {
		owner.getPets().add(pet1);
		owner.getPets().add(pet2);

		Pet foundPet = owner.getPet("Fluffy");
		assertThat(foundPet).isNotNull();
		assertThat(foundPet.getName()).isEqualTo("Fluffy");
	}

	@Test
	void testGetPetByNameCaseInsensitive() {
		owner.getPets().add(pet1);

		Pet foundPet = owner.getPet("fluffy");
		assertThat(foundPet).isNotNull();
		assertThat(foundPet.getName()).isEqualTo("Fluffy");
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
		assertThat(foundPet.getName()).isEqualTo("Fluffy");
	}

	@Test
	void testGetPetByIdReturnsNullWhenNotFound() {
		owner.getPets().add(pet1);

		Pet foundPet = owner.getPet(999);
		assertThat(foundPet).isNull();
	}

	@Test
	void testGetPetByIdIgnoresNewPets() {
		Pet newPet = new Pet();
		newPet.setName("NewPet");
		owner.getPets().add(newPet);

		Pet foundPet = owner.getPet((Integer) null);
		assertThat(foundPet).isNull();
	}

	@Test
	void testGetPetByNameWithIgnoreNew() {
		Pet newPet = new Pet();
		newPet.setName("NewPet");
		owner.getPets().add(newPet);
		owner.getPets().add(pet1);

		Pet foundPet = owner.getPet("NewPet", true);
		assertThat(foundPet).isNull();

		foundPet = owner.getPet("NewPet", false);
		assertThat(foundPet).isNotNull();
		assertThat(foundPet.getName()).isEqualTo("NewPet");
	}

	@Test
	void testGetPetByNameWithIgnoreNewReturnsSavedPet() {
		owner.getPets().add(pet1);

		Pet foundPet = owner.getPet("Fluffy", true);
		assertThat(foundPet).isNotNull();
		assertThat(foundPet.getName()).isEqualTo("Fluffy");
	}

	@Test
	void testAddVisit() {
		owner.getPets().add(pet1);

		Visit visit = new Visit();
		visit.setDescription("Annual checkup");

		owner.addVisit(1, visit);

		assertThat(pet1.getVisits()).hasSize(1);
		assertThat(pet1.getVisits().iterator().next().getDescription()).isEqualTo("Annual checkup");
	}

	@Test
	void testAddVisitThrowsExceptionWhenPetIdIsNull() {
		Visit visit = new Visit();
		visit.setDescription("Annual checkup");

		assertThatThrownBy(() -> owner.addVisit(null, visit)).isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("Pet identifier must not be null");
	}

	@Test
	void testAddVisitThrowsExceptionWhenVisitIsNull() {
		owner.getPets().add(pet1);

		assertThatThrownBy(() -> owner.addVisit(1, null)).isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("Visit must not be null");
	}

	@Test
	void testAddVisitThrowsExceptionWhenPetNotFound() {
		Visit visit = new Visit();
		visit.setDescription("Annual checkup");

		assertThatThrownBy(() -> owner.addVisit(999, visit)).isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("Invalid Pet identifier");
	}

	@Test
	void testToString() {
		owner.setId(123);
		String result = owner.toString();

		assertThat(result).contains("id = '123'");
		assertThat(result).contains("lastName = 'Doe'");
		assertThat(result).contains("firstName = 'John'");
		assertThat(result).contains("address = '123 Main St'");
		assertThat(result).contains("city = 'Springfield'");
		assertThat(result).contains("telephone = '1234567890'");
	}

	@Test
	void testSerialization() {
		owner.setId(123);
		@SuppressWarnings("deprecation")
		Owner deserializedOwner = (Owner) SerializationUtils.deserialize(SerializationUtils.serialize(owner));

		assertThat(deserializedOwner.getId()).isEqualTo(owner.getId());
		assertThat(deserializedOwner.getFirstName()).isEqualTo(owner.getFirstName());
		assertThat(deserializedOwner.getLastName()).isEqualTo(owner.getLastName());
		assertThat(deserializedOwner.getAddress()).isEqualTo(owner.getAddress());
		assertThat(deserializedOwner.getCity()).isEqualTo(owner.getCity());
		assertThat(deserializedOwner.getTelephone()).isEqualTo(owner.getTelephone());
	}

}
