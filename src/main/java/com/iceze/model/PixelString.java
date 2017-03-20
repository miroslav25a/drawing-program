package com.iceze.model;

/**
 * This class represents a pixel implementation as a string.
 * 
 * @author Miroslav
 *
 */
public class PixelString implements Pixel {
	public static final String DEFAULT_COLOUR = " ";
	
	private String colour;
	
	public PixelString() {
		this.colour = null;
	}
	
	private PixelString(String colour) {
		this.colour = colour;
	}

	public String getColour() {
		return colour;
	}
	
	public String toString() {
		return this.colour;
	}
	
	/**
	 * A builder class for PixelString
	 * 
	 * @author Miroslav
	 *
	 */
	public static class PixelStringBuilder {
		private String colour;
		
		public PixelStringBuilder withColour(String colour) {
			this.colour = colour;
			return this;
		}
		
		public PixelString build() {
			return new PixelString(this.colour);
		}
	}
}
