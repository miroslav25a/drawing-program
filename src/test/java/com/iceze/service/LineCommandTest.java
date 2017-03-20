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
import com.iceze.model.Pixel;
import com.iceze.model.PixelString;
import com.iceze.model.ValidationStatus;
import com.iceze.model.CanvasArray.CanvasArrayBuilder;
import com.iceze.model.PixelString.PixelStringBuilder;
import com.iceze.service.LineCommand;

public class LineCommandTest {
	public static final String ERROR_MESSAGE_VALUE = 
			"Error: Line should be straight within the Canvas\n";
	
	private LineCommand command;
	private Canvas canvas;
	
	@Before
	public void setup() {
		this.command = new LineCommand();
		Pixel background = new PixelStringBuilder()
									.withColour(PixelString.DEFAULT_COLOUR)
									.build();
		Pixel horizontalBorder = new PixelStringBuilder()
									.withColour("-")
									.build(); 
		Pixel verticalBorder = new PixelStringBuilder()
									.withColour("|")
									.build(); 
		this.canvas = new CanvasArrayBuilder()
									.withBackground(background)
									.withHeight(4)
									.withHorizontalBorder(horizontalBorder)
									.withVerticalBorder(verticalBorder)
									.withWidth(20)
									.build();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void commandReturnsStatusOnCanvas() {
		ValidationStatus status = this.command.validate(null, "L", "1", "2", "6", "2");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "L", "1", "2", "6", "2");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(true)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(CreateCommandTest.OK_MESSAGE_VALUE))));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void commandReturnsStatusOnArgumentsSize() {
		ValidationStatus status = this.command.validate(this.canvas, "L", "1", "2", "6");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "L", "1", "2", "6", "2", "3");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "L", "1", "2", "6", "2");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(true)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(CreateCommandTest.OK_MESSAGE_VALUE))));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void commandReturnsStatusOnFirstArgument() {
		ValidationStatus status = this.command.validate(this.canvas, "C", "1", "2", "6", "2");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "l", "1", "2", "6", "2");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "L", "1", "2", "6", "2");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(true)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(CreateCommandTest.OK_MESSAGE_VALUE))));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void commandReturnsStatusOnSecondArgument() {
		ValidationStatus status = this.command.validate(this.canvas, "L", "i", "2", "6", "2");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "L", "0", "2", "6", "2");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "L", "21", "2", "6", "2");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "L", "20", "2", "6", "2");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(true)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(CreateCommandTest.OK_MESSAGE_VALUE))));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void commandReturnsStatusOnThirdArgument() {
		ValidationStatus status = this.command.validate(this.canvas, "L", "1", "i", "6", "2");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "L", "1", "0", "6", "2");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "L", "1", "5", "6", "2");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "L", "1", "4", "6", "4");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(true)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(CreateCommandTest.OK_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "L", "1", "1", "6", "1");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(true)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(CreateCommandTest.OK_MESSAGE_VALUE))));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void commandReturnsStatusOnFourthArgument() {
		ValidationStatus status = this.command.validate(this.canvas, "L", "1", "2", "i", "2");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "L", "1", "2", "0", "2");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "L", "1", "2", "21", "2");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "L", "1", "2", "20", "2");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(true)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(CreateCommandTest.OK_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "L", "1", "2", "1", "2");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(true)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(CreateCommandTest.OK_MESSAGE_VALUE))));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void commandReturnsStatusOnFifthArgument() {
		ValidationStatus status = this.command.validate(this.canvas, "L", "1", "2", "6", "i");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "L", "1", "2", "6", "0");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "L", "1", "2", "6", "5");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "L", "1", "4", "6", "4");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(true)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(CreateCommandTest.OK_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "L", "1", "1", "6", "1");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(true)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(CreateCommandTest.OK_MESSAGE_VALUE))));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void commandReturnsStatusOnStraightLine() {
		ValidationStatus status = this.command.validate(this.canvas, "L", "1", "2", "3", "4");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "L", "4", "3", "2", "1");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "L", "1", "2", "6", "2");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(true)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(CreateCommandTest.OK_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "L", "6", "2", "1", "2");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(true)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(CreateCommandTest.OK_MESSAGE_VALUE))));

		status = this.command.validate(this.canvas, "L", "6", "3", "6", "4");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(true)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(CreateCommandTest.OK_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "L", "6", "4", "6", "3");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(true)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(CreateCommandTest.OK_MESSAGE_VALUE))));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void commandReturnsCanvasWithLineLeftToRight() {
		Canvas canvas = this.command.execute(this.canvas, "L", "1", "2", "6", "2");
		String stringRepresentation = canvas.toString();
		assertThat(stringRepresentation, is(
				allOf(notNullValue(), instanceOf(String.class), equalTo("----------------------\n" +
																		"|                    |\n" + 
																		"|xxxxxx              |\n" +
																		"|                    |\n" +
																		"|                    |\n" +
																		"----------------------\n"))));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void commandReturnsCanvasWithLineRightToLeft() {
		Canvas canvas = this.command.execute(this.canvas, "L", "6", "2", "1", "2");
		String stringRepresentation = canvas.toString();
		assertThat(stringRepresentation, is(
				allOf(notNullValue(), instanceOf(String.class), equalTo("----------------------\n" +
																		"|                    |\n" + 
																		"|xxxxxx              |\n" +
																		"|                    |\n" +
																		"|                    |\n" +
																		"----------------------\n"))));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void commandReturnsCanvasWithLineUpToDown() {
		Canvas canvas = this.command.execute(this.canvas, "L", "6", "2", "6", "4");
		String stringRepresentation = canvas.toString();
		assertThat(stringRepresentation, is(
				allOf(notNullValue(), instanceOf(String.class), equalTo("----------------------\n" +
																		"|                    |\n" + 
																		"|     x              |\n" +
																		"|     x              |\n" +
																		"|     x              |\n" +
																		"----------------------\n"))));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void commandReturnsCanvasWithLineDownToUp() {
		Canvas canvas = this.command.execute(this.canvas, "L", "6", "4", "6", "2");
		String stringRepresentation = canvas.toString();
		assertThat(stringRepresentation, is(
				allOf(notNullValue(), instanceOf(String.class), equalTo("----------------------\n" +
																		"|                    |\n" + 
																		"|     x              |\n" +
																		"|     x              |\n" +
																		"|     x              |\n" +
																		"----------------------\n"))));
	}
}
