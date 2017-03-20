package com.iceze.service;

import com.iceze.model.Canvas;
import com.iceze.model.ValidationStatus;

public class QuitCommand extends CommonCommand {
	public static final String ERROR_MESSAGE = "Error: To Quit the program enter Q\n";

	public ValidationStatus validate(Canvas canvas, String... arguments) {
		// check the size of the arguments. should be 1
		if(arguments.length != 1) {
			return createValidationStatus(false, ERROR_MESSAGE);
		}
		// check the the first arguments is Q (meaning quit).
		if(!arguments[0].equals("Q")) {
			return createValidationStatus(false, ERROR_MESSAGE);
		}
		
		return createValidationStatus(true, OK_MESSAGE);
	}

	public Canvas execute(Canvas canvas, String... arguments) {
		System.out.print("Bye!\n");
		
		// exit the application
		System.exit(0);
		
		return null;
	}

}
