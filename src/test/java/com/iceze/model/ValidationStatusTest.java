package com.iceze.model;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.iceze.model.ValidationStatus;
import com.iceze.model.ValidationStatus.ValidationStatusBuilder;

public class ValidationStatusTest {
	public static final String MESSAGE_VALUE = "OK";
	public static final Boolean VALID_VALIE = new Boolean(true);
	
	private ValidationStatus status;
	
	@Before
	public void setup() {
		this.status = new ValidationStatusBuilder()
								.withValid(VALID_VALIE)
								.withMessage(MESSAGE_VALUE)
								.build();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void statusRetrunsValid() {
		Object valid = this.status.getValid();
		assertThat(valid, is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(VALID_VALIE))));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void statusRetrunsMessage() {
		Object message = this.status.getMessage();
		assertThat(message, is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(MESSAGE_VALUE))));
	}
	
	@Test
	public void statusReturnsDefaultConstructorValid() {
		this.status = new ValidationStatus();
		Object valid = this.status.getValid();
		
		assertThat(valid, is(nullValue()));
	}
	
	@Test
	public void statusReturnsDefaultConstructorMessage() {
		this.status = new ValidationStatus();
		Object message = this.status.getMessage();
		
		assertThat(message, is(nullValue()));
	}

}
