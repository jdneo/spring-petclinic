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
import org.springframework.util.SerializationUtils;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for Visit model class
 */
class VisitTests {

	@Test
	void testDefaultConstructorSetsCurrentDate() {
		LocalDate beforeCreation = LocalDate.now();
		Visit visit = new Visit();
		LocalDate afterCreation = LocalDate.now();

		assertThat(visit.getDate()).isNotNull();
		assertThat(visit.getDate()).isBetween(beforeCreation, afterCreation);
	}

	@Test
	void testGettersAndSetters() {
		Visit visit = new Visit();
		visit.setDescription("Annual checkup");
		LocalDate date = LocalDate.of(2024, 3, 15);
		visit.setDate(date);

		assertThat(visit.getDescription()).isEqualTo("Annual checkup");
		assertThat(visit.getDate()).isEqualTo(date);
	}

	@Test
	void testDescriptionCanBeSet() {
		Visit visit = new Visit();
		visit.setDescription("Vaccination");

		assertThat(visit.getDescription()).isEqualTo("Vaccination");
	}

	@Test
	void testDescriptionCanBeNull() {
		Visit visit = new Visit();
		visit.setDescription(null);

		assertThat(visit.getDescription()).isNull();
	}

	@Test
	void testDateCanBeChanged() {
		Visit visit = new Visit();
		LocalDate newDate = LocalDate.of(2024, 5, 20);
		visit.setDate(newDate);

		assertThat(visit.getDate()).isEqualTo(newDate);
	}

	@Test
	void testDateCanBeNull() {
		Visit visit = new Visit();
		visit.setDate(null);

		assertThat(visit.getDate()).isNull();
	}

	@Test
	void testIsNewReturnsTrueWhenIdIsNull() {
		Visit visit = new Visit();
		assertThat(visit.isNew()).isTrue();
	}

	@Test
	void testIsNewReturnsFalseWhenIdIsSet() {
		Visit visit = new Visit();
		visit.setId(1);
		assertThat(visit.isNew()).isFalse();
	}

	@Test
	void testSerialization() {
		Visit visit = new Visit();
		visit.setId(123);
		visit.setDescription("Annual checkup");
		LocalDate date = LocalDate.of(2024, 3, 15);
		visit.setDate(date);

		@SuppressWarnings("deprecation")
		Visit deserializedVisit = (Visit) SerializationUtils.deserialize(SerializationUtils.serialize(visit));

		assertThat(deserializedVisit.getId()).isEqualTo(visit.getId());
		assertThat(deserializedVisit.getDescription()).isEqualTo(visit.getDescription());
		assertThat(deserializedVisit.getDate()).isEqualTo(visit.getDate());
	}

}
