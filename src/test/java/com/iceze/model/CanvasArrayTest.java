package com.iceze.model;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

import com.iceze.model.CanvasArray;
import com.iceze.model.Pixel;
import com.iceze.model.CanvasArray.CanvasArrayBuilder;
import com.iceze.model.PixelString.PixelStringBuilder;

import org.junit.Before;
import org.junit.Test;

public class CanvasArrayTest {
	private static final Integer HEIGHT = new Integer(3);
	private static final Integer WIDTH = new Integer(4);
	private static final String BACKGROUND_COLOUR = " ";
	private static final String HORIZONTAL_BORDER_COLOUR = "-";
	private static final String VERTICAL_BORDER_COLOUR = "|";
	
	private CanvasArray canvas;
	
	@Before
	public void setup() {
		this.canvas = new CanvasArrayBuilder()
							.withHeight(HEIGHT)
							.withWidth(WIDTH)
							.withHorizontalBorder(new PixelStringBuilder()
													.withColour(HORIZONTAL_BORDER_COLOUR)
													.build())
							.withVerticalBorder(new PixelStringBuilder()
													.withColour(VERTICAL_BORDER_COLOUR)
													.build())
							.withBackground(new PixelStringBuilder()
													.withColour(BACKGROUND_COLOUR)
													.build())
							.build();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void canvasReturnsHeight() {
		Integer height = this.canvas.getHeight();
		
		assertThat(height, is(
				allOf(notNullValue(), instanceOf(Integer.class), equalTo(HEIGHT))));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void canvasReturnsWidth() {
		Integer width = this.canvas.getWidth();
		
		assertThat(width, is(
				allOf(notNullValue(), instanceOf(Integer.class), equalTo(WIDTH))));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void canvasReturnsBackgroundColour() {
		Pixel background = this.canvas.getBackground();
		assertThat(background, is(notNullValue()));
		Object backgroundColour = background.getColour();
		assertThat(backgroundColour, is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(BACKGROUND_COLOUR))));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void canvasReturnsHorizontalBorderColour() {
		Pixel horizontalBorder = this.canvas.getHorizontalBorder();
		assertThat(horizontalBorder, is(notNullValue()));
		Object horizontalBorderColour = horizontalBorder.getColour();
		assertThat(horizontalBorderColour, is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(HORIZONTAL_BORDER_COLOUR))));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void canvasReturnsVerticalBorderColour() {
		Pixel verticalBorder = this.canvas.getVerticalBorder();
		assertThat(verticalBorder, is(notNullValue()));
		Object verticalBorderColour = verticalBorder.getColour();
		assertThat(verticalBorderColour, is(
				allOf(notNullValue(), instanceOf(String.class), equalTo(VERTICAL_BORDER_COLOUR))));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void canvasReturnsCanvasArray() {
		Pixel[][] canvasArray = this.canvas.getCanvas();
		
		assertThat(canvasArray, is(notNullValue()));
		
		for(int i=0; i < HEIGHT; i++) {
			for(int j=0; j < WIDTH; j++) {
				Pixel p =  canvasArray[j][i];
				Object pColour = p.getColour();
				assertThat(p, is(allOf(notNullValue(), instanceOf(Pixel.class))));
				assertThat(pColour, is(allOf(notNullValue(), instanceOf(String.class), equalTo(BACKGROUND_COLOUR))));
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void canvasReturnsStringRepresentationAfterIntitialization() {
		String stringRepresentation = this.canvas.toString();
		assertThat(stringRepresentation, is(
				allOf(notNullValue(), instanceOf(String.class), equalTo("------\n" +
																		"|    |\n" + 
																		"|    |\n" +
																		"|    |\n" +
																		"------\n"))));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void canvasReturnsBucketFill() {
		Pixel oldPixelColour = new PixelStringBuilder()
				.withColour(BACKGROUND_COLOUR)
				.build();
		Pixel newPixelColour = new PixelStringBuilder()
						.withColour("o")
						.build();
		this.canvas.drawBucketFill(1, 1, oldPixelColour, newPixelColour);
		String stringRepresentation = canvas.toString();
		assertThat(stringRepresentation, is(
				allOf(notNullValue(), instanceOf(String.class), equalTo("------\n" +
																		"|oooo|\n" + 
																		"|oooo|\n" +
																		"|oooo|\n" +
																		"------\n"))));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void canvasReturnsRectangle() {
		Pixel pixelColour = new PixelStringBuilder()
				.withColour("o")
				.build();
		this.canvas.drawRectangle(0, 0, 3, 2, pixelColour);
		String stringRepresentation = canvas.toString();
		assertThat(stringRepresentation, is(
				allOf(notNullValue(), instanceOf(String.class), equalTo("------\n" +
																		"|oooo|\n" + 
																		"|o  o|\n" +
																		"|oooo|\n" +
																		"------\n"))));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void canvasReturnsLine() {
		Pixel pixelColour = new PixelStringBuilder()
				.withColour("o")
				.build();
		this.canvas.drawLine(0, 1, 3, 1, pixelColour);
		String stringRepresentation = canvas.toString();
		assertThat(stringRepresentation, is(
				allOf(notNullValue(), instanceOf(String.class), equalTo("------\n" +
																		"|    |\n" + 
																		"|oooo|\n" +
																		"|    |\n" +
																		"------\n"))));
	}
}
