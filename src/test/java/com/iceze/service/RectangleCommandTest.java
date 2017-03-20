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
import com.iceze.service.RectangleCommand;

public class RectangleCommandTest {
	public static final String ERROR_MESSAGE_VALUE = 
			"Error: Rectangle should within the Canvas\n";
	
	private RectangleCommand command;
	private Canvas canvas;
	
	@Before
	public void setup() {
		this.command = new RectangleCommand();
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
		ValidationStatus status = this.command.validate(null, "R", "16", "1", "20", "3");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "R", "16", "1", "20", "3");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(true)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(CreateCommandTest.OK_MESSAGE_VALUE))));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void commandReturnsStatusOnArgumentsSize() {
		ValidationStatus status = this.command.validate(this.canvas, "R", "16", "1", "20");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "R", "16", "1", "20", "3", "3");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "R", "16", "1", "20", "3");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(true)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(CreateCommandTest.OK_MESSAGE_VALUE))));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void commandReturnsStatusOnFirstArgument() {
		ValidationStatus status = this.command.validate(this.canvas, "L", "16", "1", "20", "3");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "r", "16", "1", "20", "3");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "R", "16", "1", "20", "3");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(true)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(CreateCommandTest.OK_MESSAGE_VALUE))));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void commandReturnsStatusOnSecondArgument() {
		ValidationStatus status = this.command.validate(this.canvas, "R", "i", "1", "20", "3");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "R", "0", "1", "20", "3");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "R", "21", "1", "20", "3");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "R", "16", "1", "20", "3");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(true)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(CreateCommandTest.OK_MESSAGE_VALUE))));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void commandReturnsStatusOnThirdArgument() {
		ValidationStatus status = this.command.validate(this.canvas, "R", "16", "i", "20", "3");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "R", "16", "0", "20", "3");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "R", "16", "5", "20", "3");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "R", "16", "4", "20", "3");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(true)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(CreateCommandTest.OK_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "R", "1", "1", "20", "2");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(true)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(CreateCommandTest.OK_MESSAGE_VALUE))));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void commandReturnsStatusOnFourthArgument() {
		ValidationStatus status = this.command.validate(this.canvas, "R", "16", "1", "i", "3");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "R", "16", "1", "0", "3");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "R", "16", "1", "21", "3");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "R", "16", "1", "20", "3");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(true)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(CreateCommandTest.OK_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "R", "16", "1", "1", "3");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(true)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(CreateCommandTest.OK_MESSAGE_VALUE))));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void commandReturnsStatusOnFifthArgument() {
		ValidationStatus status = this.command.validate(this.canvas, "R", "16", "1", "20", "i");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "R", "16", "1", "20", "0");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "R", "16", "1", "20", "5");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "R", "16", "1", "20", "4");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(true)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(CreateCommandTest.OK_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "R", "16", "2", "20", "1");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(true)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(CreateCommandTest.OK_MESSAGE_VALUE))));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void commandReturnsStatusOnRectangle() {
		ValidationStatus status = this.command.validate(this.canvas, "R", "16", "1", "16", "3");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "R", "16", "1", "20", "1");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "R", "16", "1", "20", "3");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(true)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(CreateCommandTest.OK_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "R", "20", "3", "16", "1");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(true)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(CreateCommandTest.OK_MESSAGE_VALUE))));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void commandReturnsCanvasWithRectangleLeftUpToRightDown() {
		Canvas canvas = this.command.execute(this.canvas, "L", "6", "2", "8", "4");
		String stringRepresentation = canvas.toString();
		assertThat(stringRepresentation, is(
				allOf(notNullValue(), instanceOf(String.class), equalTo("----------------------\n" +
																		"|                    |\n" + 
																		"|     xxx            |\n" +
																		"|     x x            |\n" +
																		"|     xxx            |\n" +
																		"----------------------\n"))));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void commandReturnsCanvasWithRectangleRightDownToLeftUp() {
		Canvas canvas = this.command.execute(this.canvas, "L", "8", "4", "6", "2");
		String stringRepresentation = canvas.toString();
		assertThat(stringRepresentation, is(
				allOf(notNullValue(), instanceOf(String.class), equalTo("----------------------\n" +
																		"|                    |\n" + 
																		"|     xxx            |\n" +
																		"|     x x            |\n" +
																		"|     xxx            |\n" +
																		"----------------------\n"))));
	}
}
