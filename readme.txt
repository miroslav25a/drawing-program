# Drawing Program

This project is a simple command line drawing program that allows users to;
1. create a new canvas
2. create a new line
3. create a new rectangle
4. bucket fill with a colour
5. quit the program

## Installation

Import the project into the Eclipse or STS as a maven project.
Right click on the project in the Eclipse and select Run As -> Maven build.
In the Edit Configuration window in the Goals section input "clean install" and click on the Run button.

## Usage

In the Eclipse right click on the project and select Run As -> Java Application.
In the Select Java Application window select the DrawingApplication and the click OK button.

enter command: C w h			- creates a new canvas example: C 10 20
enter command: Q				- quits the program. example: Q
enter command: L x1 y1 x2 y2	- creates a new line. example: L 1 1 1 10
enter command: R x1 y1 x2 y2	- creates a new rectangle. example: R 1 1 3 3
enter command: B x y c			- creates a new bucket fill. example 1 1 o
