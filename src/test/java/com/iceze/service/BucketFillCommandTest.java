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
import com.iceze.service.BucketFillCommand;

public class BucketFillCommandTest {
	public static final String ERROR_MESSAGE_VALUE = 
			"Error: Bucket fill starting point should be within the Canvas\n";
	
	private BucketFillCommand command;
	private Canvas canvas;
	
	@Before
	public void setup() {
		this.command = new BucketFillCommand();
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
		
		Pixel rectangleColour = new PixelStringBuilder()
				.withColour("x")
				.build();
		canvas.drawRectangle(7, 3, 5, 1, rectangleColour);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void commandReturnsStatusOnCanvas() {
		ValidationStatus status = this.command.validate(null, "B", "16", "1", "c");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "B", "16", "1", "c");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(true)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(CreateCommandTest.OK_MESSAGE_VALUE))));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void commandReturnsStatusOnArgumentsSize() {
		ValidationStatus status = this.command.validate(this.canvas, "B", "16", "1");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "B", "16", "1", "c", "3");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "B", "16", "1", "c");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(true)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(CreateCommandTest.OK_MESSAGE_VALUE))));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void commandReturnsStatusOnFirstArgument() {
		ValidationStatus status = this.command.validate(this.canvas, "L", "16", "1", "c");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "b", "16", "1", "c");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "B", "16", "1", "c");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(true)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(CreateCommandTest.OK_MESSAGE_VALUE))));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void commandReturnsStatusOnSecondArgument() {
		ValidationStatus status = this.command.validate(this.canvas, "B", "i", "1", "c");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "B", "0", "1", "c");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "R", "21", "1", "c");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "B", "1", "1", "c");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(true)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(CreateCommandTest.OK_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "B", "20", "1", "c");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(true)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(CreateCommandTest.OK_MESSAGE_VALUE))));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void commandReturnsStatusOnThirdArgument() {
		ValidationStatus status = this.command.validate(this.canvas, "B", "16", "i", "c");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "B", "16", "0", "c");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "B", "16", "5", "c");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "B", "16", "4", "c");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(true)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(CreateCommandTest.OK_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "B", "1", "1", "c");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(true)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(CreateCommandTest.OK_MESSAGE_VALUE))));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void commandReturnsStatusOnFourthArgument() {
		ValidationStatus status = this.command.validate(this.canvas, "B", "16", "1", "");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "B", "16", "1", "cc");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(false)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(ERROR_MESSAGE_VALUE))));
		
		status = this.command.validate(this.canvas, "B", "16", "1", "c");
		assertThat(status, is(notNullValue()));
		assertThat(status.getValid(), is(
				allOf(notNullValue(), instanceOf(Boolean.class), equalTo(new Boolean(true)))));
		assertThat(status.getMessage(), is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(CreateCommandTest.OK_MESSAGE_VALUE))));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void commandReturnsCanvasWithBucketFillOutside() {
		Canvas canvas = this.command.execute(this.canvas, "B", "1", "1", "c");
		String stringRepresentation = canvas.toString();
		assertThat(stringRepresentation, is(
				allOf(notNullValue(), instanceOf(String.class), equalTo("----------------------\n" +
																		"|cccccccccccccccccccc|\n" + 
																		"|cccccxxxcccccccccccc|\n" +
																		"|cccccx xcccccccccccc|\n" +
																		"|cccccxxxcccccccccccc|\n" +
																		"----------------------\n"))));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void commandReturnsCanvasWithBucketFillInside() {
		Canvas canvas = this.command.execute(this.canvas, "B", "7", "3", "c");
		String stringRepresentation = canvas.toString();
		assertThat(stringRepresentation, is(
				allOf(notNullValue(), instanceOf(String.class), equalTo("----------------------\n" +
																		"|                    |\n" + 
																		"|     xxx            |\n" +
																		"|     xcx            |\n" +
																		"|     xxx            |\n" +
																		"----------------------\n"))));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void commandReturnsCanvasWithBucketFillBorder() {
		Canvas canvas = this.command.execute(this.canvas, "B", "6", "3", "c");
		String stringRepresentation = canvas.toString();
		assertThat(stringRepresentation, is(
				allOf(notNullValue(), instanceOf(String.class), equalTo("----------------------\n" +
																		"|                    |\n" + 
																		"|     ccc            |\n" +
																		"|     c c            |\n" +
																		"|     ccc            |\n" +
																		"----------------------\n"))));
	}
}
