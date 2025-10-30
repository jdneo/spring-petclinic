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
 * Unit tests for {@link Visit}
 *
 * @author Copilot
 */
class VisitTests {

	private Visit visit;

	@BeforeEach
	void setUp() {
		visit = new Visit();
	}

	@Test
	void testNewVisitHasCurrentDate() {
		LocalDate today = LocalDate.now();
		Visit newVisit = new Visit();
		assertThat(newVisit.getDate()).isEqualTo(today);
	}

	@Test
	void testSetAndGetDescription() {
		visit.setDescription("Annual checkup");
		assertThat(visit.getDescription()).isEqualTo("Annual checkup");
	}

	@Test
	void testSetAndGetDate() {
		LocalDate visitDate = LocalDate.of(2024, 6, 15);
		visit.setDate(visitDate);
		assertThat(visit.getDate()).isEqualTo(visitDate);
	}

	@Test
	void testVisitWithAllAttributes() {
		visit.setId(1);
		visit.setDescription("Vaccination");
		LocalDate visitDate = LocalDate.of(2024, 7, 20);
		visit.setDate(visitDate);

		assertThat(visit.getId()).isEqualTo(1);
		assertThat(visit.getDescription()).isEqualTo("Vaccination");
		assertThat(visit.getDate()).isEqualTo(visitDate);
	}

	@Test
	void testDescriptionCanBeNull() {
		visit.setDescription(null);
		assertThat(visit.getDescription()).isNull();
	}

	@Test
	void testDateCanBeNull() {
		visit.setDate(null);
		assertThat(visit.getDate()).isNull();
	}

}
