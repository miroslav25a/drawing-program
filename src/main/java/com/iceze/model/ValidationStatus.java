package com.iceze.model;

/**
 * This class represents a validation status of a users input.
 * 
 * @author Miroslav
 *
 */
public class ValidationStatus {
	private Boolean valid;
	private String message;
	
	public ValidationStatus() {
		this.valid = null;
		this.message = null;
	}
	
	private ValidationStatus(Boolean valid, String message) {
		this.valid = valid;
		this.message = message;
	}

	public Boolean getValid() {
		return valid;
	}

	public String getMessage() {
		return message;
	}
	
	/**
	 * A builder for a ValidationStatus
	 * @author Miroslav
	 *
	 */
	public static class ValidationStatusBuilder {
		private Boolean valid;
		private String message;
		
		public ValidationStatusBuilder withValid(Boolean valid) {
			this.valid = valid;
			return this;
		}
		
		public ValidationStatusBuilder withMessage(String message) {
			this.message = message;
			return this;
		}
		
		public ValidationStatus build() {
			return new ValidationStatus(this.valid, this.message);
		}
	}
}
