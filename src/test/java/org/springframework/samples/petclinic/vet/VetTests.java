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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.SerializationUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Dave Syer
 */
class VetTests {

	private Vet vet;

	@BeforeEach
	void setup() {
		vet = new Vet();
		vet.setFirstName("James");
		vet.setLastName("Carter");
	}

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
		Specialty radiology = new Specialty();
		radiology.setName("radiology");

		vet.addSpecialty(radiology);

		assertThat(vet.getNrOfSpecialties()).isEqualTo(1);
		assertThat(vet.getSpecialties()).contains(radiology);
	}

	@Test
	void testAddMultipleSpecialties() {
		Specialty radiology = new Specialty();
		radiology.setName("radiology");

		Specialty surgery = new Specialty();
		surgery.setName("surgery");

		Specialty dentistry = new Specialty();
		dentistry.setName("dentistry");

		vet.addSpecialty(radiology);
		vet.addSpecialty(surgery);
		vet.addSpecialty(dentistry);

		assertThat(vet.getNrOfSpecialties()).isEqualTo(3);
		assertThat(vet.getSpecialties()).hasSize(3);
		assertThat(vet.getSpecialties()).contains(radiology, surgery, dentistry);
	}

	@Test
	void testGetSpecialtiesEmpty() {
		assertThat(vet.getNrOfSpecialties()).isZero();
		assertThat(vet.getSpecialties()).isEmpty();
	}

	@Test
	void testGetSpecialtiesSorted() {
		Specialty surgery = new Specialty();
		surgery.setName("surgery");

		Specialty dentistry = new Specialty();
		dentistry.setName("dentistry");

		Specialty radiology = new Specialty();
		radiology.setName("radiology");

		// Add in non-alphabetical order
		vet.addSpecialty(surgery);
		vet.addSpecialty(dentistry);
		vet.addSpecialty(radiology);

		List<Specialty> specialties = vet.getSpecialties();

		// Should be sorted alphabetically by name
		assertThat(specialties.get(0).getName()).isEqualTo("dentistry");
		assertThat(specialties.get(1).getName()).isEqualTo("radiology");
		assertThat(specialties.get(2).getName()).isEqualTo("surgery");
	}

	@Test
	void testGetNrOfSpecialties() {
		assertThat(vet.getNrOfSpecialties()).isZero();

		Specialty specialty1 = new Specialty();
		specialty1.setName("radiology");
		vet.addSpecialty(specialty1);

		assertThat(vet.getNrOfSpecialties()).isEqualTo(1);

		Specialty specialty2 = new Specialty();
		specialty2.setName("surgery");
		vet.addSpecialty(specialty2);

		assertThat(vet.getNrOfSpecialties()).isEqualTo(2);
	}

	@Test
	void testSetAndGetFirstName() {
		assertThat(vet.getFirstName()).isEqualTo("James");

		vet.setFirstName("Helen");
		assertThat(vet.getFirstName()).isEqualTo("Helen");
	}

	@Test
	void testSetAndGetLastName() {
		assertThat(vet.getLastName()).isEqualTo("Carter");

		vet.setLastName("Leary");
		assertThat(vet.getLastName()).isEqualTo("Leary");
	}

	@Test
	void testIsNew() {
		assertThat(vet.isNew()).isTrue();

		vet.setId(1);
		assertThat(vet.isNew()).isFalse();
	}

}
