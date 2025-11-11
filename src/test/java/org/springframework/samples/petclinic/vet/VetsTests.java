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
 * Unit tests for the Vets wrapper class.
 *
 * @author Copilot
 */
class VetsTests {

	@Test
	void testGetVetList() {
		Vets vets = new Vets();
		assertThat(vets.getVetList()).isNotNull().isEmpty();
	}

	@Test
	void testGetVetListReturnsEmptyListWhenNull() {
		Vets vets = new Vets();
		assertThat(vets.getVetList()).isEmpty();
	}

	@Test
	void testGetVetListReturnsSameInstance() {
		Vets vets = new Vets();
		assertThat(vets.getVetList()).isSameAs(vets.getVetList());
	}

}
