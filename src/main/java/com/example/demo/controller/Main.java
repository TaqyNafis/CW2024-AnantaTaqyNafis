package com.example.demo.controller;

import com.example.demo.display.MainMenu;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;


import java.util.logging.Level;
import java.util.logging.Logger;


public class Main extends Application {

	private static final Logger logger = Logger.getLogger(Main.class.getName());

	@Override
	public void start(Stage stage) {
		try {
			ArcadeController gameArcadeController = new ArcadeController(stage);
			MainMenu.showMainMenu(stage, gameArcadeController);
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Error starting MainMenu", e);
		}
	}

	public static void main(String[] args) {
		launch();
	}
}
