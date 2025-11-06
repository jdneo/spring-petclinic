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

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Additional tests for {@link Vet} specialties.
 *
 * @author Copilot
 */
class VetSpecialtyTests {

	@Test
	void testAddSpecialty() {
		Vet vet = new Vet();
		Specialty specialty = new Specialty();
		specialty.setName("radiology");

		vet.addSpecialty(specialty);

		assertThat(vet.getSpecialties()).hasSize(1);
		assertThat(vet.getSpecialties()).contains(specialty);
		assertThat(vet.getNrOfSpecialties()).isEqualTo(1);
	}

	@Test
	void testAddMultipleSpecialties() {
		Vet vet = new Vet();
		Specialty radiology = new Specialty();
		radiology.setName("radiology");
		Specialty surgery = new Specialty();
		surgery.setName("surgery");

		vet.addSpecialty(radiology);
		vet.addSpecialty(surgery);

		assertThat(vet.getSpecialties()).hasSize(2);
		assertThat(vet.getNrOfSpecialties()).isEqualTo(2);
	}

	@Test
	void testGetSpecialtiesReturnsSortedList() {
		Vet vet = new Vet();
		Specialty surgery = new Specialty();
		surgery.setName("surgery");
		Specialty dentistry = new Specialty();
		dentistry.setName("dentistry");
		Specialty radiology = new Specialty();
		radiology.setName("radiology");

		vet.addSpecialty(surgery);
		vet.addSpecialty(dentistry);
		vet.addSpecialty(radiology);

		assertThat(vet.getSpecialties()).hasSize(3);
		assertThat(vet.getSpecialties().get(0).getName()).isEqualTo("dentistry");
		assertThat(vet.getSpecialties().get(1).getName()).isEqualTo("radiology");
		assertThat(vet.getSpecialties().get(2).getName()).isEqualTo("surgery");
	}

	@Test
	void testGetSpecialtiesReturnsEmptyListInitially() {
		Vet vet = new Vet();

		assertThat(vet.getSpecialties()).isEmpty();
		assertThat(vet.getNrOfSpecialties()).isZero();
	}

	@Test
	void testVetGettersAndSetters() {
		Vet vet = new Vet();
		vet.setFirstName("James");
		vet.setLastName("Carter");

		assertThat(vet.getFirstName()).isEqualTo("James");
		assertThat(vet.getLastName()).isEqualTo("Carter");
	}

}
