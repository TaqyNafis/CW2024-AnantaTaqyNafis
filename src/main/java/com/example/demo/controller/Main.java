package com.example.demo.controller;

import com.example.demo.display.MainMenu;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;


import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Main class serves as the entry point for the JavaFX application.
 * It initializes and launches the main menu of the game.
 */
public class Main extends Application {

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
