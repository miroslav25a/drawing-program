package com.iceze.service;

import com.iceze.model.ValidationStatus;
import com.iceze.model.ValidationStatus.ValidationStatusBuilder;

public abstract class CommonCommand implements Command {
	/**
	 * this method creates a new validation status for given arguments.
	 * 
	 * @param valid boolean representing status of the validation.
	 * @param message String representing the validation message.
	 * 
	 * @return ValidationStatus
	 */
	protected ValidationStatus createValidationStatus(boolean valid, String message) {
		return new ValidationStatusBuilder()
						.withValid(new Boolean(valid))
						.withMessage(message)
						.build();
	}
	
	/**
	 * The method parses the given argument as an int 
	 * and validates it against the given minimum value.
	 * 
	 * @param argument String representing a number to validate
	 * @param minimum int represents the minimum value the can be
	 * 
	 * @return boolean
	 */
	protected boolean validateNumberArgumentMinimum(String argument, int minimum) {
		try {
			if(argument == null || Integer.parseInt(argument) < minimum) {
				return false;
			}
		} catch(NumberFormatException e) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * The method parses the given argument as an int 
	 * and validates it against the given maximum value.
	 * 
	 * @param argument String representing a number to validate
	 * @param maximum int represents the maximum value the argument can be
	 * 
	 * @return boolean
	 */
	protected boolean validateNumberArgumentMaximum(String argument, int maximum) {
		try {
			if(argument == null || Integer.parseInt(argument) > maximum) {
				return false;
			}
		} catch(NumberFormatException e) {
			return false;
		}
		
		return true;
	}
}
