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

/**
 * Unit tests for the Pet model class.
 *
 * @author Copilot
 */
class PetTests {

	private Pet pet;

	private PetType petType;

	@BeforeEach
	void setUp() {
		pet = new Pet();
		petType = new PetType();
		petType.setName("Dog");
	}

	@Test
	void testGetName() {
		pet.setName("Fluffy");
		assertThat(pet.getName()).isEqualTo("Fluffy");
	}

	@Test
	void testSetName() {
		pet.setName("Max");
		assertThat(pet.getName()).isEqualTo("Max");
	}

	@Test
	void testGetBirthDate() {
		LocalDate birthDate = LocalDate.of(2020, 1, 15);
		pet.setBirthDate(birthDate);
		assertThat(pet.getBirthDate()).isEqualTo(birthDate);
	}

	@Test
	void testSetBirthDate() {
		LocalDate newDate = LocalDate.of(2021, 5, 20);
		pet.setBirthDate(newDate);
		assertThat(pet.getBirthDate()).isEqualTo(newDate);
	}

	@Test
	void testGetType() {
		pet.setType(petType);
		assertThat(pet.getType()).isEqualTo(petType);
	}

	@Test
	void testSetType() {
		PetType catType = new PetType();
		catType.setName("Cat");
		pet.setType(catType);
		assertThat(pet.getType()).isEqualTo(catType);
	}

	@Test
	void testGetVisits() {
		assertThat(pet.getVisits()).isNotNull().isEmpty();
	}

	@Test
	void testAddVisit() {
		Visit visit = new Visit();
		visit.setDescription("Annual checkup");
		visit.setDate(LocalDate.now());
		pet.addVisit(visit);
		assertThat(pet.getVisits()).hasSize(1).contains(visit);
	}

	@Test
	void testAddMultipleVisits() {
		Visit visit1 = new Visit();
		visit1.setDescription("Checkup");
		Visit visit2 = new Visit();
		visit2.setDescription("Vaccination");
		pet.addVisit(visit1);
		pet.addVisit(visit2);
		assertThat(pet.getVisits()).hasSize(2).containsExactly(visit1, visit2);
	}

	@Test
	void testIsNew() {
		assertThat(pet.isNew()).isTrue();
		pet.setId(1);
		assertThat(pet.isNew()).isFalse();
	}

	@Test
	void testToString() {
		pet.setName("Fluffy");
		String result = pet.toString();
		assertThat(result).isEqualTo("Fluffy");
	}

	@Test
	void testToStringWithNullName() {
		String result = pet.toString();
		assertThat(result).isEqualTo("<null>");
	}

}
