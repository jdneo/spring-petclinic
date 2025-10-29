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
import org.springframework.util.SerializationUtils;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Dave Syer
 */
class VetTests {

	@Test
	void testSerialization() {
		Vet vet = new Vet();
		vet.setFirstName("Zaphod");
		vet.setLastName("Beeblebrox");
		vet.setId(123);
		@SuppressWarnings("deprecation")
		Vet other = (Vet) SerializationUtils.deserialize(SerializationUtils.serialize(vet));
		assertThat(other.getFirstName()).isEqualTo(vet.getFirstName());
		assertThat(other.getLastName()).isEqualTo(vet.getLastName());
		assertThat(other.getId()).isEqualTo(vet.getId());
	}

	@Test
	void testAddSpecialty() {
		Vet vet = new Vet();
		Specialty dentistry = new Specialty();
		dentistry.setId(1);
		dentistry.setName("dentistry");

		vet.addSpecialty(dentistry);

		assertThat(vet.getNrOfSpecialties()).isEqualTo(1);
		assertThat(vet.getSpecialties()).hasSize(1);
		assertThat(vet.getSpecialties()).contains(dentistry);
	}

	@Test
	void testGetSpecialtiesInitiallyEmpty() {
		Vet vet = new Vet();

		assertThat(vet.getNrOfSpecialties()).isEqualTo(0);
		assertThat(vet.getSpecialties()).isEmpty();
	}

	@Test
	void testGetSpecialtiesSorted() {
		Vet vet = new Vet();

		Specialty surgery = new Specialty();
		surgery.setId(1);
		surgery.setName("surgery");

		Specialty dentistry = new Specialty();
		dentistry.setId(2);
		dentistry.setName("dentistry");

		Specialty radiology = new Specialty();
		radiology.setId(3);
		radiology.setName("radiology");

		// Add in non-alphabetical order
		vet.addSpecialty(surgery);
		vet.addSpecialty(radiology);
		vet.addSpecialty(dentistry);

		assertThat(vet.getNrOfSpecialties()).isEqualTo(3);
		// Should be sorted alphabetically by name
		assertThat(vet.getSpecialties()).containsExactly(dentistry, radiology, surgery);
	}

	@Test
	void testMultipleSpecialties() {
		Vet vet = new Vet();

		Specialty specialty1 = new Specialty();
		specialty1.setId(1);
		specialty1.setName("dentistry");

		Specialty specialty2 = new Specialty();
		specialty2.setId(2);
		specialty2.setName("surgery");

		vet.addSpecialty(specialty1);
		vet.addSpecialty(specialty2);

		assertThat(vet.getNrOfSpecialties()).isEqualTo(2);
		assertThat(vet.getSpecialties()).hasSize(2);
	}

}
