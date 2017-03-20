package com.iceze.model;

/**
 * This class contains an implementation of a canvas as a 2d array
 * 
 * @author Miroslav
 *
 */
public class CanvasArray implements Canvas {
	private Pixel[][] canvas;
	private Integer width;
	private Integer height;
	private Pixel horizontalBorder; 
	private Pixel verticalBorder; 
	private Pixel background; 
	
	public CanvasArray() {
		this.canvas = null;
		this.width = null;
		this.height = null;
		this.horizontalBorder = null; 
		this.verticalBorder = null; 
		this.background = null; 
	}
	
	private CanvasArray(Integer width, Integer height, Pixel horizontalBorder, 
										Pixel verticalBorder, Pixel background) {
		this.width = width;
		this.height = height;
		this.canvas = new Pixel[this.width][this.height];
		this.horizontalBorder = horizontalBorder;
		this.verticalBorder = verticalBorder;
		this.background = background;
		this.init();
	}
	
	/**
	 * this method initialises the 2d array with default pixel colour
	 */
	private void init() {
		for(int i=0; i < this.height; i++) {
			for(int j=0; j < this.width; j++) {
				this.canvas[j][i] = this.background;
			}
		}
	}

	public Pixel[][] getCanvas() {
		return canvas;
	}

	/**
	 * {@inheritDoc}
	 */
	public Integer getWidth() {
		return width;
	}

	/**
	 * {@inheritDoc}
	 */
	public Integer getHeight() {
		return height;
	}
	
	public Pixel getHorizontalBorder() {
		return horizontalBorder;
	}

	public Pixel getVerticalBorder() {
		return verticalBorder;
	}

	public Pixel getBackground() {
		return background;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Pixel getPixel(int x, int y) {
		return this.canvas[x][y];
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void drawLine(int x1, int y1, int x2, int y2, Pixel pixel) {
		// check for horizontal line, left to right
		if(y1==y2 && x1<x2) {
			for(int i=0; i<= Math.abs(x2-x1); i++) { 
				this.canvas[x1+i][y1] = pixel;
			}
		}

		// check for horizontal line, right to left
		if(y1==y2 && x1>x2) {
			for(int i=0; i<=Math.abs(x2-x1); i++) {
				this.canvas[x2+i][y1] = pixel;
			}
		}

		// check for vertical line, top to bottom
		if(x1==x2 && y1<y2) {
			for(int i=0; i<=Math.abs(y1-y2); i++) {
				this.canvas[x1][y1+i] = pixel;
			}
		}

		// check for vertical line, bottom to top
		if(x1==x2 && y1>y2)	{
			for(int i=0; i<=Math.abs(y1-y2); i++) {
				this.canvas[x1][y2+i] = pixel;
			}
		} 
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void drawRectangle(int x1, int y1, int x2, int y2, Pixel pixel) {
		if(x1<x2 && y1<y2) {
			this.drawLine(x1, y1, x2, y1, pixel);
			this.drawLine(x2, y1, x2, y2, pixel);
			this.drawLine(x2, y2, x1, y2, pixel);
			this.drawLine(x1, y2, x1, y1, pixel);
		}
		
		if(x1>x2 && y1>y2) {
			this.drawLine(x1, y1, x1, y2, pixel);
			this.drawLine(x1, y2, x2, y2, pixel);
			this.drawLine(x2, y2, x2, y1, pixel);
			this.drawLine(x2, y1, x1, y1, pixel);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void drawBucketFill(int x, int y, Pixel oldPixelColour, Pixel newPixelColour) {
		if(this.canvas[x][y].toString().equals(oldPixelColour.toString())) {
			// change the character at canvas[x][y] to newPixelColour
			this.canvas[x][y] = newPixelColour;
			

		    // make recursive calls
			
			// left
		    if(x > 0)
		    	drawBucketFill( x-1, y, oldPixelColour, newPixelColour);

		    // up
		    if(y > 0)
		    	drawBucketFill(x, y-1, oldPixelColour, newPixelColour);

		    // right
		    if (x < this.width-1)
		    	drawBucketFill(x+1, y, oldPixelColour, newPixelColour);

		    // down
		    if(y < this.height-1)
		    	drawBucketFill(x, y+1, oldPixelColour, newPixelColour);
		}
	}
	
	/**
	 * this method returns horizontal canvas borders as a string.
	 * 
	 * @return String
	 */
	private String horizontalBorderToString() {
		StringBuilder builder = new StringBuilder();
		
		for(int i=0; i < this.width + 2; i++) {
			builder.append(this.horizontalBorder.toString());
			if(i == (this.width + 2) - 1) {
				builder.append("\n");
			}
		}
		
		return builder.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		String horizontalBorderLine = horizontalBorderToString();
		builder.append(horizontalBorderLine);
		
		for(int i=0; i < this.height; i++) {
			for(int j=0; j < this.width; j++) {
				if(j == 0) {
					builder.append(this.verticalBorder.toString());
				}
				builder.append(this.canvas[j][i].toString());
				
				if(j == this.width-1) {
					builder.append(this.verticalBorder.toString());
					builder.append("\n");
				}
			}
		}
		
		builder.append(horizontalBorderLine);
		
		return builder.toString();
	}
	
	/**
	 * A builder class for the CanvasArray
	 * 
	 * @author Miroslav
	 *
	 */
	public static class CanvasArrayBuilder {
		private Integer width;
		private Integer height;
		private Pixel horizontalBorder;
		private Pixel verticalBorder;
		private Pixel background;
		
		public CanvasArrayBuilder withWidth(Integer width) {
			this.width = width;
			return this;
		}
		
		public CanvasArrayBuilder withHeight(Integer height) {
			this.height = height;
			return this;
		}
		
		public CanvasArrayBuilder withHorizontalBorder(Pixel horizontalBorder) {
			this.horizontalBorder = horizontalBorder;
			return this;
		}
		
		public CanvasArrayBuilder withVerticalBorder(Pixel verticalBorder) {
			this.verticalBorder = verticalBorder;
			return this;
		}
		
		public CanvasArrayBuilder withBackground(Pixel background) {
			this.background = background;
			return this;
		}
		
		public CanvasArray build() {
			return new CanvasArray(this.width, this.height, horizontalBorder, verticalBorder, background);
		}
	}
}
