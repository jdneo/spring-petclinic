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

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Dave Syer
 */
class VetTests {

	private Vet vet;

	@BeforeEach
	void setUp() {
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
	void testGetSpecialtiesReturnsEmptyListInitially() {
		assertThat(vet.getSpecialties()).isEmpty();
	}

	@Test
	void testAddSpecialty() {
		Specialty specialty = new Specialty();
		specialty.setName("dentistry");

		vet.addSpecialty(specialty);

		assertThat(vet.getNrOfSpecialties()).isEqualTo(1);
		assertThat(vet.getSpecialties()).hasSize(1);
		assertThat(vet.getSpecialties().get(0).getName()).isEqualTo("dentistry");
	}

	@Test
	void testAddMultipleSpecialties() {
		Specialty specialty1 = new Specialty();
		specialty1.setName("surgery");

		Specialty specialty2 = new Specialty();
		specialty2.setName("radiology");

		vet.addSpecialty(specialty1);
		vet.addSpecialty(specialty2);

		assertThat(vet.getNrOfSpecialties()).isEqualTo(2);
		assertThat(vet.getSpecialties()).hasSize(2);
	}

	@Test
	void testGetSpecialtiesAreSortedByName() {
		Specialty specialty1 = new Specialty();
		specialty1.setName("surgery");

		Specialty specialty2 = new Specialty();
		specialty2.setName("dentistry");

		Specialty specialty3 = new Specialty();
		specialty3.setName("radiology");

		vet.addSpecialty(specialty1);
		vet.addSpecialty(specialty2);
		vet.addSpecialty(specialty3);

		assertThat(vet.getSpecialties()).extracting("name").containsExactly("dentistry", "radiology", "surgery");
	}

	@Test
	void testGetNrOfSpecialties() {
		assertThat(vet.getNrOfSpecialties()).isEqualTo(0);

		Specialty specialty = new Specialty();
		specialty.setName("dentistry");
		vet.addSpecialty(specialty);

		assertThat(vet.getNrOfSpecialties()).isEqualTo(1);
	}

}
