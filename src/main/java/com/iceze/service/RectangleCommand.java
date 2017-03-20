package com.iceze.service;

import com.iceze.model.Canvas;
import com.iceze.model.Pixel;
import com.iceze.model.ValidationStatus;
import com.iceze.model.PixelString.PixelStringBuilder;

public class RectangleCommand extends CommonCommand {
	public static final String ERROR_MESSAGE = 
			"Error: Rectangle should within the Canvas\n";
	public static final String DEFAULT_RECTANGLE_COLOUR = "x";

	/**
	 * {@inheritDoc}
	 */
	public ValidationStatus validate(Canvas canvas, String... arguments) {
		// check that canvas is not null
		if(canvas == null) {
			return createValidationStatus(false, ERROR_MESSAGE);
		}
		// check the size of the arguments. should be 5
		if(arguments.length != 5) {
			return createValidationStatus(false, ERROR_MESSAGE);
		}
		// check the the first arguments is R (meaning rectangle).
		if(!arguments[0].equals("R")) {
			return createValidationStatus(false, ERROR_MESSAGE);
		}
		// check that x1 argument is an int and within the range.
		if(!this.validateNumberArgumentMinimum(arguments[1], 1) || 
				!this.validateNumberArgumentMaximum(arguments[1], canvas.getWidth())) {
			return createValidationStatus(false, ERROR_MESSAGE);
		}
		// check that y1 argument is an int and within the range.
		if(!this.validateNumberArgumentMinimum(arguments[2], 1) ||
				!this.validateNumberArgumentMaximum(arguments[2], canvas.getHeight())) {
			return createValidationStatus(false, ERROR_MESSAGE);
		}
		// check that x2 argument is an int and within the range.
		if(!this.validateNumberArgumentMinimum(arguments[3], 1) ||
				!this.validateNumberArgumentMaximum(arguments[3], canvas.getWidth())) {
			return createValidationStatus(false, ERROR_MESSAGE);
		}
		// check that y2 argument is an int and within the range.
		if(!this.validateNumberArgumentMinimum(arguments[4], 1) ||
				!this.validateNumberArgumentMaximum(arguments[4], canvas.getHeight())) {
			return createValidationStatus(false, ERROR_MESSAGE);
		}
		// check that its a rectangle.
		if(arguments[1].equals(arguments[3]) || arguments[2].equals(arguments[4])) {
			return createValidationStatus(false, ERROR_MESSAGE);
		}
		
		return createValidationStatus(true, OK_MESSAGE);
	}

	/**
	 * {@inheritDoc}
	 */
	public Canvas execute(Canvas canvas, String... arguments) {
		Canvas newCanvas = canvas;
		
		int x1 = Integer.parseInt(arguments[1]) - 1;
		int y1 = Integer.parseInt(arguments[2]) - 1;
		int x2 = Integer.parseInt(arguments[3]) - 1;
		int y2 = Integer.parseInt(arguments[4]) - 1;
		
		Pixel pixel = new PixelStringBuilder()
				.withColour(DEFAULT_RECTANGLE_COLOUR)
				.build();
		
		newCanvas.drawRectangle(x1, y1, x2, y2, pixel);
		return newCanvas;
	}

}
