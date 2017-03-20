package com.iceze.service;

import com.iceze.model.Canvas;
import com.iceze.model.ValidationStatus;

/**
 * This interface contains common functionality for user commands.
 * 
 * @author Miroslav
 *
 */
public interface Command {
	public static final String OK_MESSAGE = "OK!\n";
	
	/**
	 * This method validates a user input for the create command.
	 * 
	 * @param canvas Canvas representation
	 * @param arguments String[] containing user arguments.
	 * 
	 * @return ValidationStatus
	 */
	ValidationStatus validate(Canvas canvas, String... arguments);
	
	/**
	 * This method executes a command against the given canvas.
	 * 
	 * @param canvas Canvas representation
	 * @param arguments String[] containing user arguments.
	 * @return Canvas
	 */
	Canvas execute(Canvas canvas, String... arguments);
}
