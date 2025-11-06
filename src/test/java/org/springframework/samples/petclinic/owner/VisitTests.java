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
 * @author GitHub Copilot
 */
class VisitTests {

	@Test
	void testDefaultConstructorSetsCurrentDate() {
		Visit visit = new Visit();
		assertThat(visit.getDate()).isEqualTo(LocalDate.now());
	}

	@Test
	void testSetAndGetDate() {
		Visit visit = new Visit();
		LocalDate customDate = LocalDate.of(2024, 6, 15);
		visit.setDate(customDate);
		assertThat(visit.getDate()).isEqualTo(customDate);
	}

	@Test
	void testSetAndGetDescription() {
		Visit visit = new Visit();
		String description = "Annual vaccination";
		visit.setDescription(description);
		assertThat(visit.getDescription()).isEqualTo(description);
	}

	@Test
	void testDescriptionCanBeNull() {
		Visit visit = new Visit();
		visit.setDescription(null);
		assertThat(visit.getDescription()).isNull();
	}

	@Test
	void testDateCanBeNull() {
		Visit visit = new Visit();
		visit.setDate(null);
		assertThat(visit.getDate()).isNull();
	}

}
