package com.example.demo.controller;

import com.example.demo.display.MainMenu;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;


import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The {@code Main} class serves as the entry point for the JavaFX application.
 * It extends {@link javafx.application.Application} and provides the necessary
 * setup to launch the game. This class initializes the primary stage and displays
 * the main menu of the game when the application starts.
 * <p>
 * The class overrides the {@link #start(Stage)} method to set up the stage with the
 * initial scene, which is the main menu. It also includes the {@link #main(String[])}
 * method to launch the JavaFX runtime.
 * </p>
 */
public class Main extends Application {
	/**
	 * Logger for recording errors and important events in the HowToPlayController class.
	 */
	private static final Logger logger = Logger.getLogger(Main.class.getName());
	/**
	 * The main entry point for all JavaFX applications.
	 * This method is called after the JavaFX runtime has been initialized.
	 *
	 * @param stage The primary stage for this application, onto which
	 *              the application scene can be set.
	 */
	@Override
	public void start(Stage stage) {
		try {
			ArcadeController gameArcadeController = new ArcadeController(stage);
			MainMenu.showMainMenu(stage, gameArcadeController);
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Error starting MainMenu", e);
		}
	}
	/**
	 * The main method is the entry point of the application.
	 * It calls the JavaFX Application launch method to start the JavaFX runtime.
	 *
	 * @param args Command-line arguments passed to the application.
	 */
	public static void main(String[] args) {
		launch();
	}
}
