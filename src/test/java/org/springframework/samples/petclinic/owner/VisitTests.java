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

/**
 * Unit tests for Visit model class
 *
 * @author Copilot Agent
 */
class VisitTests {

	private Visit visit;

	@BeforeEach
	void setUp() {
		visit = new Visit();
	}

	@Test
	void testDefaultDateIsToday() {
		LocalDate today = LocalDate.now();
		assertThat(visit.getDate()).isEqualTo(today);
	}

	@Test
	void testSetAndGetDate() {
		LocalDate testDate = LocalDate.of(2023, 11, 15);
		visit.setDate(testDate);

		assertThat(visit.getDate()).isEqualTo(testDate);
	}

	@Test
	void testSetAndGetDescription() {
		visit.setDescription("Annual checkup");

		assertThat(visit.getDescription()).isEqualTo("Annual checkup");
	}

	@Test
	void testDescriptionDefaultsToNull() {
		Visit newVisit = new Visit();
		assertThat(newVisit.getDescription()).isNull();
	}

	@Test
	void testSetNullDate() {
		visit.setDate(null);

		assertThat(visit.getDate()).isNull();
	}

	@Test
	void testSetNullDescription() {
		visit.setDescription("Checkup");
		visit.setDescription(null);

		assertThat(visit.getDescription()).isNull();
	}

	@Test
	void testIsNewReturnsTrueWhenIdIsNull() {
		assertThat(visit.isNew()).isTrue();
	}

	@Test
	void testIsNewReturnsFalseWhenIdIsSet() {
		visit.setId(1);

		assertThat(visit.isNew()).isFalse();
	}

	@Test
	void testVisitWithCompleteData() {
		LocalDate visitDate = LocalDate.of(2023, 12, 1);
		visit.setDate(visitDate);
		visit.setDescription("Vaccination");
		visit.setId(10);

		assertThat(visit.getDate()).isEqualTo(visitDate);
		assertThat(visit.getDescription()).isEqualTo("Vaccination");
		assertThat(visit.getId()).isEqualTo(10);
		assertThat(visit.isNew()).isFalse();
	}

}
