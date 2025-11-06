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
package org.springframework.samples.petclinic.vet;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Additional test class for {@link Vet}
 *
 * @author GitHub Copilot
 */
class VetModelTests {

	@Test
	void testGetNrOfSpecialtiesWithNoSpecialties() {
		Vet vet = new Vet();
		assertThat(vet.getNrOfSpecialties()).isEqualTo(0);
	}

	@Test
	void testAddSpecialty() {
		Vet vet = new Vet();
		Specialty specialty = new Specialty();
		specialty.setName("Dentistry");
		vet.addSpecialty(specialty);
		assertThat(vet.getNrOfSpecialties()).isEqualTo(1);
	}

	@Test
	void testAddMultipleSpecialties() {
		Vet vet = new Vet();
		Specialty dentistry = new Specialty();
		dentistry.setName("Dentistry");
		Specialty surgery = new Specialty();
		surgery.setName("Surgery");

		vet.addSpecialty(dentistry);
		vet.addSpecialty(surgery);

		assertThat(vet.getNrOfSpecialties()).isEqualTo(2);
	}

	@Test
	void testGetSpecialtiesSortedByName() {
		Vet vet = new Vet();

		Specialty surgery = new Specialty();
		surgery.setName("Surgery");

		Specialty dentistry = new Specialty();
		dentistry.setName("Dentistry");

		Specialty radiology = new Specialty();
		radiology.setName("Radiology");

		// Add in non-alphabetical order
		vet.addSpecialty(surgery);
		vet.addSpecialty(dentistry);
		vet.addSpecialty(radiology);

		List<Specialty> specialties = vet.getSpecialties();
		assertThat(specialties).hasSize(3);
		// Should be sorted alphabetically
		assertThat(specialties.get(0).getName()).isEqualTo("Dentistry");
		assertThat(specialties.get(1).getName()).isEqualTo("Radiology");
		assertThat(specialties.get(2).getName()).isEqualTo("Surgery");
	}

	@Test
	void testGetSpecialtiesReturnsEmptyListWhenNoSpecialties() {
		Vet vet = new Vet();
		List<Specialty> specialties = vet.getSpecialties();
		assertThat(specialties).isNotNull();
		assertThat(specialties).isEmpty();
	}

	@Test
	void testAddSameSpecialtyTwice() {
		Vet vet = new Vet();
		Specialty dentistry = new Specialty();
		dentistry.setName("Dentistry");

		vet.addSpecialty(dentistry);
		vet.addSpecialty(dentistry);

		// Set should not contain duplicates
		assertThat(vet.getNrOfSpecialties()).isEqualTo(1);
	}

	@Test
	void testVetWithNameAndSpecialties() {
		Vet vet = new Vet();
		vet.setFirstName("James");
		vet.setLastName("Carter");

		Specialty surgery = new Specialty();
		surgery.setName("Surgery");
		vet.addSpecialty(surgery);

		assertThat(vet.getFirstName()).isEqualTo("James");
		assertThat(vet.getLastName()).isEqualTo("Carter");
		assertThat(vet.getNrOfSpecialties()).isEqualTo(1);
	}

}
