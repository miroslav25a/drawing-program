package com.iceze.model;

/**
 * An interface that contains common functionality for canvas.
 * 
 * @author Miroslav
 *
 */
public interface Canvas {
	
	/**
	 * This method returns a string representation of a canvas.
	 * 
	 * @return String
	 */
	String toString();
	
	/**
	 * The method returns a width of a canvas.
	 * 
	 * @return Integer
	 */
	Integer getWidth();
	
	/**
	 * The method returns a height of a canvas.
	 * 
	 * @return Integer
	 */
	Integer getHeight();
	
	/**
	 * The method draws a new line, with the given pixel, 
	 * on a canvas from the x1,y1 point to x2,y2 point.
	 *  
	 * @param x1 int represents horizontal value of the first point.
	 * @param y1 int represents vertical value of the first point.
	 * @param x2 int represents horizontal value of the second point.
	 * @param y2 int represents vertical value of the second point.
	 * @param pixel Pixel represents pixel colour of a new line.
	 */
	void drawLine(int x1, int y1, int x2, int y2, Pixel pixel);
	
	/**
	 * The method draws a new rectangle, with the given pixel, 
	 * on a canvas from the x1,y1 point to x2,y2 point.
	 *  
	 * @param x1 int represents horizontal value of the first point.
	 * @param y1 int represents vertical value of the first point.
	 * @param x2 int represents horizontal value of the second point.
	 * @param y2 int represents vertical value of the second point.
	 * @param pixel Pixel represents pixel colour of a new rectangle.
	 */
	void drawRectangle(int x1, int y1, int x2, int y2, Pixel pixel);
	
	/**
	 * The method draws a new bucket fill, with the given pixel, 
	 * on a canvas from the x, y point.
	 *  
	 * @param x int represents horizontal value of the point.
	 * @param y int represents vertical value of the point.
	 * @param oldPixelColour Pixel represents an old pixel colour.
	 * @param newPixelColour Pixel represents a new pixel colour.
	 */
	void drawBucketFill(int x, int y, Pixel oldPixelColour, Pixel newPixelColour);
	
	/**
	 * Get a pixel for a canvas for given location.
	 * @param x int represents a horizontal coordinate of a pixel.
	 * @param y int represents a vertical coordinate of a pixel.
	 * 
	 * @return Pixel
	 */
	Pixel getPixel(int x, int y);
}
