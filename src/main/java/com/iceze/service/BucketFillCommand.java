package com.iceze.service;

import com.iceze.model.Canvas;
import com.iceze.model.Pixel;
import com.iceze.model.ValidationStatus;
import com.iceze.model.PixelString.PixelStringBuilder;

public class BucketFillCommand  extends CommonCommand {
	public static final String ERROR_MESSAGE = 
			"Error: Bucket fill starting point should be within the Canvas\n";

	/**
	 * {@inheritDoc}
	 */
	public ValidationStatus validate(Canvas canvas, String... arguments) {
		// check that canvas is not null
		if(canvas == null) {
			return createValidationStatus(false, ERROR_MESSAGE);
		}
		// check the size of the arguments. should be 4
		if(arguments.length != 4) {
			return createValidationStatus(false, ERROR_MESSAGE);
		}
		// check the the first arguments is B (meaning bucket fill).
		if(!arguments[0].equals("B")) {
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
		
		if(arguments[3].isEmpty() || arguments[3].length() > 1) {
			return createValidationStatus(false, ERROR_MESSAGE);
		}
		
		return createValidationStatus(true, OK_MESSAGE);
	}

	/**
	 * {@inheritDoc}
	 */
	public Canvas execute(Canvas canvas, String... arguments) {
		Canvas newCanvas = canvas;
		int x = Integer.parseInt(arguments[1]) - 1;
		int y = Integer.parseInt(arguments[2]) - 1;
		Pixel oldColour = newCanvas.getPixel(x, y);
		Pixel newColour = new PixelStringBuilder()
								.withColour(arguments[3])
								.build();
		
		newCanvas.drawBucketFill(x, y, oldColour, newColour);
		
		return newCanvas;
	}

}
