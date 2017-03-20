package com.iceze;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.iceze.model.Canvas;
import com.iceze.model.ValidationStatus;
import com.iceze.service.BucketFillCommand;
import com.iceze.service.Command;
import com.iceze.service.CreateCommand;
import com.iceze.service.LineCommand;
import com.iceze.service.QuitCommand;
import com.iceze.service.RectangleCommand;

/**
 *This is the main class containing functionality for running the application.
 * 
 * @author Miroslav
 *
 */
public class DrawingApplication {
	public static final String QUIT_COMMAND_VALUE = "Q";
	public static final String CREATE_COMMAND_VALUE = "C";
	public static final String LINE_COMMAND_VALUE = "L";
	public static final String RECTANGLE_COMMAND_VALUE = "R";
	public static final String BUCKET_FILL_COMMAND_VALUE = "B";
	
	/**
	 * the main method of the application.
	 * 
	 * @param args String[] representing arguments
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Map<String, Command> commands = initCommands();
		Canvas canvas = null;
		
		while(true) {
			// prompt for the user's command
		    System.out.print("enter command: ");
		    
		    // get user input 
		    String userInput = scanner.nextLine();
		    String[] arguments = userInput.split(" ");
		    
		    if(commands.containsKey(arguments[0])) {
		    	// get the command from the map
		    	Command command = commands.get(arguments[0]);
		    	// validate users input
		    	ValidationStatus status  = command.validate(canvas, arguments);
		    	if(!status.getValid()) {
		    		// if users input invalid, display an error message
		    		System.out.print(status.getMessage());
		    	} else {
		    		// if users input valid, display the canvas
		    		canvas = command.execute(canvas, arguments);
		    		System.out.println(canvas.toString());
		    	}
		    }
		}
	}
	
	/**
	 * This method creates a map with commands that are available to the user.
	 * 
	 * @return HashMap<String, Command>
	 */
	private static Map<String, Command> initCommands() {
		Map<String, Command> commands = new HashMap<String, Command>();
		
		commands.put(CREATE_COMMAND_VALUE, new CreateCommand());
		commands.put(LINE_COMMAND_VALUE, new LineCommand());
		commands.put(RECTANGLE_COMMAND_VALUE, new RectangleCommand());
		commands.put(BUCKET_FILL_COMMAND_VALUE, new BucketFillCommand());
		commands.put(QUIT_COMMAND_VALUE, new QuitCommand());
		
		return commands;
	}
}
