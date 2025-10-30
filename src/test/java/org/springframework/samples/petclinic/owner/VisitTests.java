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
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for {@link Visit}
 */
class VisitTests {

	private Validator createValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
	}

	@Test
	void testDefaultDateIsSetToToday() {
		Visit visit = new Visit();
		assertThat(visit.getDate()).isEqualTo(LocalDate.now());
	}

	@Test
	void testSetAndGetDate() {
		Visit visit = new Visit();
		LocalDate visitDate = LocalDate.of(2023, 6, 15);
		visit.setDate(visitDate);
		assertThat(visit.getDate()).isEqualTo(visitDate);
	}

	@Test
	void testSetAndGetDescription() {
		Visit visit = new Visit();
		visit.setDescription("Annual checkup");
		assertThat(visit.getDescription()).isEqualTo("Annual checkup");
	}

	@Test
	void testValidationFailsWhenDescriptionIsEmpty() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Visit visit = new Visit();
		visit.setDescription("");

		Validator validator = createValidator();
		Set<ConstraintViolation<Visit>> constraintViolations = validator.validate(visit);

		assertThat(constraintViolations).hasSize(1);
		ConstraintViolation<Visit> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath()).hasToString("description");
		assertThat(violation.getMessage()).isEqualTo("must not be blank");
	}

	@Test
	void testValidationPassesWithValidDescription() {
		Visit visit = new Visit();
		visit.setDescription("Routine checkup");

		Validator validator = createValidator();
		Set<ConstraintViolation<Visit>> constraintViolations = validator.validate(visit);

		assertThat(constraintViolations).isEmpty();
	}

}
