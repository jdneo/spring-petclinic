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
 * Test class for {@link Visit}
 */
class VisitTests {

	private Visit visit;

	@BeforeEach
	void setup() {
		visit = new Visit();
		visit.setId(1);
	}

	@Test
	void testSetAndGetDate() {
		LocalDate date = LocalDate.of(2023, 5, 15);
		visit.setDate(date);

		assertThat(visit.getDate()).isEqualTo(date);
	}

	@Test
	void testSetAndGetDescription() {
		String description = "Annual checkup";
		visit.setDescription(description);

		assertThat(visit.getDescription()).isEqualTo(description);
	}

	@Test
	void testVisitWithNullDate() {
		visit.setDate(null);

		assertThat(visit.getDate()).isNull();
	}

	@Test
	void testVisitWithNullDescription() {
		visit.setDescription(null);

		assertThat(visit.getDescription()).isNull();
	}

	@Test
	void testVisitWithEmptyDescription() {
		visit.setDescription("");

		assertThat(visit.getDescription()).isEmpty();
	}

	@Test
	void testVisitWithLongDescription() {
		String longDescription = "This is a very long description that describes the visit in great detail. "
				+ "The pet had multiple symptoms and required extensive examination and treatment. "
				+ "The veterinarian performed several tests and recommended a treatment plan.";
		visit.setDescription(longDescription);

		assertThat(visit.getDescription()).isEqualTo(longDescription);
	}

	@Test
	void testVisitWithDateInPast() {
		LocalDate pastDate = LocalDate.of(2020, 1, 1);
		visit.setDate(pastDate);

		assertThat(visit.getDate()).isBefore(LocalDate.now());
	}

	@Test
	void testVisitWithDateInFuture() {
		LocalDate futureDate = LocalDate.now().plusDays(30);
		visit.setDate(futureDate);

		assertThat(visit.getDate()).isAfter(LocalDate.now());
	}

}
