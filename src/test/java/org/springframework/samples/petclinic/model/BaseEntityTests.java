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
import org.springframework.samples.petclinic.owner.PetType;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the {@link BaseEntity} model.
 *
 * @author Copilot
 */
class BaseEntityTests {

	@Test
	void testIdGetterAndSetter() {
		PetType entity = new PetType();
		entity.setId(123);

		assertThat(entity.getId()).isEqualTo(123);
	}

	@Test
	void testIsNewReturnsTrueWhenIdIsNull() {
		PetType entity = new PetType();

		assertThat(entity.isNew()).isTrue();
	}

	@Test
	void testIsNewReturnsFalseWhenIdIsSet() {
		PetType entity = new PetType();
		entity.setId(1);

		assertThat(entity.isNew()).isFalse();
	}

	@Test
	void testIsNewAfterSettingIdToNull() {
		PetType entity = new PetType();
		entity.setId(1);
		assertThat(entity.isNew()).isFalse();

		entity.setId(null);
		assertThat(entity.isNew()).isTrue();
	}

}
