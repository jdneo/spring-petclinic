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
package org.springframework.samples.petclinic.model;

import org.junit.jupiter.api.Test;
import org.springframework.samples.petclinic.owner.Visit;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the {@link Visit} model.
 *
 * @author Copilot
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
		LocalDate visitDate = LocalDate.of(2024, 11, 6);
		visit.setDate(visitDate);
		visit.setDescription("Annual checkup");

		assertThat(visit.getDate()).isEqualTo(visitDate);
		assertThat(visit.getDescription()).isEqualTo("Annual checkup");
	}

	@Test
	void testIsNew() {
		Visit visit = new Visit();

		assertThat(visit.isNew()).isTrue();

		visit.setId(1);

		assertThat(visit.isNew()).isFalse();
	}

}
