package com.iceze.model;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.nullValue;

import com.iceze.model.PixelString;
import com.iceze.model.PixelString.PixelStringBuilder;

import org.junit.Test;

public class PixelStringTest {
	private PixelString pixel;

	@SuppressWarnings("unchecked")
	@Test
	public void pixelReturnsColour() {
		this.pixel = new PixelStringBuilder()
							.withColour("c")
							.build();
		String colour = this.pixel.getColour();
		
		assertThat(colour, is(allOf(notNullValue(), instanceOf(String.class), equalTo("c"))));
	}
	
	@Test
	public void pixelReturnsDefaultConstructorColour() {
		this.pixel = new PixelString();
		String colour = this.pixel.getColour();
		
		assertThat(colour, is(nullValue()));
	}
}
