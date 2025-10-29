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

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for {@link Visit}
 *
 * @author Copilot
 */
class VisitTests {

	@Test
	void testConstructorSetsCurrentDate() {
		Visit visit = new Visit();

		assertThat(visit.getDate()).isNotNull();
		assertThat(visit.getDate()).isEqualTo(LocalDate.now());
	}

	@Test
	void testGettersAndSetters() {
		Visit visit = new Visit();
		LocalDate visitDate = LocalDate.of(2023, 10, 15);

		visit.setDescription("Annual checkup");
		visit.setDate(visitDate);

		assertThat(visit.getDescription()).isEqualTo("Annual checkup");
		assertThat(visit.getDate()).isEqualTo(visitDate);
	}

	@Test
	void testIsNew() {
		Visit visit = new Visit();
		assertThat(visit.isNew()).isTrue();

		visit.setId(1);
		assertThat(visit.isNew()).isFalse();
	}

	@Test
	void testDescriptionCanBeUpdated() {
		Visit visit = new Visit();
		visit.setDescription("Initial description");
		assertThat(visit.getDescription()).isEqualTo("Initial description");

		visit.setDescription("Updated description");
		assertThat(visit.getDescription()).isEqualTo("Updated description");
	}

	@Test
	void testDateCanBeUpdated() {
		Visit visit = new Visit();
		LocalDate originalDate = visit.getDate();

		LocalDate newDate = LocalDate.of(2023, 1, 1);
		visit.setDate(newDate);

		assertThat(visit.getDate()).isNotEqualTo(originalDate);
		assertThat(visit.getDate()).isEqualTo(newDate);
	}

}
