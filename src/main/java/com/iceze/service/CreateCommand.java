package com.iceze.service;

import com.iceze.model.Canvas;
import com.iceze.model.PixelString;
import com.iceze.model.ValidationStatus;
import com.iceze.model.CanvasArray.CanvasArrayBuilder;
import com.iceze.model.PixelString.PixelStringBuilder;

/**
 * This class represents an implementation of a create a new canvas command.
 * 
 * @author Miroslav
 *
 */
public class CreateCommand extends CommonCommand {
	public static final String ERROR_MESSAGE = 
			"Error: Canvas should be minimum 4x3 and width greater than the height\n";
	public static final String CANVAS_HORIZONTAL_BORDER = "-";
	public static final String CANVAS_VERTICAL_BORDER = "|";

	/**
	 * {@inheritDoc}
	 */
	public ValidationStatus validate(Canvas canvas, String... arguments) {
		if(arguments.length != 3) {
			return createValidationStatus(false, ERROR_MESSAGE);
		}
		
		if(arguments[0] == null || !arguments[0].equals("C")) {
			return createValidationStatus(false, ERROR_MESSAGE);
		}
		
		if(!validateNumberArgumentMinimum(arguments[1], 4) || 
		   !validateNumberArgumentMinimum(arguments[2], 3)) {
			return createValidationStatus(false, ERROR_MESSAGE);
		}
		
		if(Integer.parseInt(arguments[1]) <= Integer.parseInt(arguments[2])) {
			return createValidationStatus(false, ERROR_MESSAGE);
		}

		return createValidationStatus(true, OK_MESSAGE);
	}

	/**
	 * {@inheritDoc}
	 */
	public Canvas execute(Canvas canvas, String... arguments) {
		Integer width = Integer.parseInt(arguments[1]);
		Integer height = Integer.parseInt(arguments[2]);
		
		Canvas newCanvas = new CanvasArrayBuilder()
						.withWidth(width)
						.withHeight(height)
						.withBackground(new PixelStringBuilder()
													.withColour(PixelString.DEFAULT_COLOUR)
													.build())
						.withHorizontalBorder(new PixelStringBuilder()
													.withColour(CANVAS_HORIZONTAL_BORDER)
													.build())
						.withVerticalBorder(new PixelStringBuilder()
													.withColour(CANVAS_VERTICAL_BORDER)
													.build())
						.build();
		return newCanvas;
	}
}
