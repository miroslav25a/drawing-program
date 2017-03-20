package com.iceze.service;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.iceze.model.Canvas;
import com.iceze.model.CanvasArray;
import com.iceze.model.ValidationStatus;
import com.iceze.service.CreateCommand;

public class CreateCommandTest {
	public static final String ERROR_MESSAGE_VALUE = 
			"Error: Canvas should be minimum 4x3 and width greater than the height\n";
	public static final String OK_MESSAGE_VALUE = "OK!\n";
	
	private CreateCommand command;
	
	@Before
	public void setup() {
		this.command = new CreateCommand();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void commandReturnsStatusOnArgumentsSize() {
		ValidationStatus status = this.command.validate(new CanvasArray(), "C", "20");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(new CanvasArray(), "C", "20", "4", "5");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(new CanvasArray(), "C", "20", "4");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(true)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(OK_MESSAGE_VALUE))));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void commandReturnsStatusOnFirstArgument() {
		ValidationStatus status = this.command.validate(new CanvasArray(), "o", "20", "4");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(new CanvasArray(), "c", "20", "4");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(new CanvasArray(), "C", "20", "4");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(true)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(OK_MESSAGE_VALUE))));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void commandReturnsStatusOnSecondArgument() {
		ValidationStatus status = this.command.validate(new CanvasArray(), "C", "3", "4");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(new CanvasArray(), "C", "4", "3");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(true)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(OK_MESSAGE_VALUE))));
		
		status = this.command.validate(new CanvasArray(), "C", "5", "3");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(true)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(OK_MESSAGE_VALUE))));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void commandReturnsStatusOnThirdArgument() {
		ValidationStatus status = this.command.validate(new CanvasArray(), "C", "20", "2");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(new CanvasArray(), "C", "20", "3");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(true)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(OK_MESSAGE_VALUE))));
		
		status = this.command.validate(new CanvasArray(), "C", "20", "4");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(true)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(OK_MESSAGE_VALUE))));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void commandReturnsStatusOnWidthGreaterThanHeight() {
		ValidationStatus status = this.command.validate(new CanvasArray(), "C", "19", "20");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(new CanvasArray(), "C", "20", "20");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(new CanvasArray(), "C", "21", "20");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(true)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(OK_MESSAGE_VALUE))));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void commandExecutesReturnsCanvas() {
		Canvas canvas = null;
		canvas = this.command.execute(canvas, "C", "4", "3");
		assertThat(canvas, is(notNullValue()));
		
		String canvasString = canvas.toString();
		assertThat(canvasString, is(
				allOf(notNullValue(), instanceOf(String.class), equalTo("------\n" +
																		"|    |\n" + 
																		"|    |\n" +
																		"|    |\n" +
																		"------\n"))));
	}
}
