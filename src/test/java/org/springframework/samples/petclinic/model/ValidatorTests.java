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

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Locale;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

/**
 * @author Michael Isvy Simple test to make sure that Bean Validation is working (useful
 * when upgrading to a new version of Hibernate Validator/ Bean Validation)
 */
class ValidatorTests {

	private Validator createValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
	}

	@Test
	void shouldNotValidateWhenFirstNameEmpty() {

		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Person person = new Person();
		person.setFirstName("");
		person.setLastName("smith");

		Validator validator = createValidator();
		Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person);

		assertThat(constraintViolations).hasSize(1);
		ConstraintViolation<Person> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath()).hasToString("firstName");
		assertThat(violation.getMessage()).isEqualTo("must not be blank");
	}

	@Test
	void shouldNotValidateWhenLastNameEmpty() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Person person = new Person();
		person.setFirstName("John");
		person.setLastName("");

		Validator validator = createValidator();
		Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person);

		assertThat(constraintViolations).hasSize(1);
		ConstraintViolation<Person> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath()).hasToString("lastName");
		assertThat(violation.getMessage()).isEqualTo("must not be blank");
	}

	@Test
	void shouldNotValidateWhenFirstNameNull() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Person person = new Person();
		person.setFirstName(null);
		person.setLastName("Doe");

		Validator validator = createValidator();
		Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person);

		assertThat(constraintViolations).hasSize(1);
		ConstraintViolation<Person> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath()).hasToString("firstName");
		assertThat(violation.getMessage()).isEqualTo("must not be blank");
	}

	@Test
	void shouldNotValidateWhenLastNameNull() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Person person = new Person();
		person.setFirstName("Jane");
		person.setLastName(null);

		Validator validator = createValidator();
		Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person);

		assertThat(constraintViolations).hasSize(1);
		ConstraintViolation<Person> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath()).hasToString("lastName");
		assertThat(violation.getMessage()).isEqualTo("must not be blank");
	}

	@Test
	void shouldValidateWhenPersonValid() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Person person = new Person();
		person.setFirstName("John");
		person.setLastName("Doe");

		Validator validator = createValidator();
		Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person);

		assertThat(constraintViolations).isEmpty();
	}

	@Test
	void shouldNotValidateOwnerWhenTelephoneInvalid() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Owner owner = new Owner();
		owner.setFirstName("John");
		owner.setLastName("Doe");
		owner.setAddress("123 Main St");
		owner.setCity("New York");
		owner.setTelephone("123");

		Validator validator = createValidator();
		Set<ConstraintViolation<Owner>> constraintViolations = validator.validate(owner);

		assertThat(constraintViolations).hasSize(1);
		ConstraintViolation<Owner> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath()).hasToString("telephone");
	}

	@Test
	void shouldValidateOwnerWhenTelephoneValid() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Owner owner = new Owner();
		owner.setFirstName("John");
		owner.setLastName("Doe");
		owner.setAddress("123 Main St");
		owner.setCity("New York");
		owner.setTelephone("1234567890");

		Validator validator = createValidator();
		Set<ConstraintViolation<Owner>> constraintViolations = validator.validate(owner);

		assertThat(constraintViolations).isEmpty();
	}

	@Test
	void shouldNotValidateOwnerWhenAddressEmpty() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Owner owner = new Owner();
		owner.setFirstName("John");
		owner.setLastName("Doe");
		owner.setAddress("");
		owner.setCity("New York");
		owner.setTelephone("1234567890");

		Validator validator = createValidator();
		Set<ConstraintViolation<Owner>> constraintViolations = validator.validate(owner);

		assertThat(constraintViolations).hasSize(1);
		ConstraintViolation<Owner> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath()).hasToString("address");
		assertThat(violation.getMessage()).isEqualTo("must not be blank");
	}

	@Test
	void shouldNotValidateOwnerWhenCityEmpty() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Owner owner = new Owner();
		owner.setFirstName("John");
		owner.setLastName("Doe");
		owner.setAddress("123 Main St");
		owner.setCity("");
		owner.setTelephone("1234567890");

		Validator validator = createValidator();
		Set<ConstraintViolation<Owner>> constraintViolations = validator.validate(owner);

		assertThat(constraintViolations).hasSize(1);
		ConstraintViolation<Owner> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath()).hasToString("city");
		assertThat(violation.getMessage()).isEqualTo("must not be blank");
	}

	@Test
	void shouldNotValidateOwnerWhenMultipleFieldsInvalid() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Owner owner = new Owner();
		owner.setFirstName("");
		owner.setLastName("");
		owner.setAddress("");
		owner.setCity("");
		owner.setTelephone("123");

		Validator validator = createValidator();
		Set<ConstraintViolation<Owner>> constraintViolations = validator.validate(owner);

		assertThat(constraintViolations).hasSize(5);
	}

}
