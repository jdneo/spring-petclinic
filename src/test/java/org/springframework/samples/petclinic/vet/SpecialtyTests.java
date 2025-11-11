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

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for the Specialty model class.
 *
 * @author Copilot
 */
class SpecialtyTests {

	@Test
	void testGetName() {
		Specialty specialty = new Specialty();
		specialty.setName("Dentistry");
		assertThat(specialty.getName()).isEqualTo("Dentistry");
	}

	@Test
	void testSetName() {
		Specialty specialty = new Specialty();
		specialty.setName("Surgery");
		assertThat(specialty.getName()).isEqualTo("Surgery");
	}

	@Test
	void testToString() {
		Specialty specialty = new Specialty();
		specialty.setName("Radiology");
		assertThat(specialty.toString()).isEqualTo("Radiology");
	}

	@Test
	void testIsNew() {
		Specialty specialty = new Specialty();
		assertThat(specialty.isNew()).isTrue();
		specialty.setId(1);
		assertThat(specialty.isNew()).isFalse();
	}

}
