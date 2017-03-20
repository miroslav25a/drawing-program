package com.iceze.service;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.iceze.model.ValidationStatus;
import com.iceze.service.QuitCommand;

public class QuitCommandTest {
	public static final String ERROR_MESSAGE_VALUE = "Error: To Quit the program enter Q\n";
	private QuitCommand command;
	
	@Before
	public void setup() {
		this.command = new QuitCommand();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void commandReturnsStatusOnArgumentsSize() {
		ValidationStatus status = this.command.validate(null, "Q", "1");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(null);
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(null, "Q");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(true)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(CreateCommandTest.OK_MESSAGE_VALUE))));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void commandReturnsStatusOnFirstArgument() {
		ValidationStatus status = this.command.validate(null, "C");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(null, "q");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(null, "Q");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(true)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(CreateCommandTest.OK_MESSAGE_VALUE))));
	}
}
