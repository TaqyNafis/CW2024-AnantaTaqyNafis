package com.example.demo.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	private static final int SCREEN_WIDTH = 640;
	private static final int SCREEN_HEIGHT = 400;
	private static final String TITLE = "Sky Battle";
	private static final String MAIN_MENU = "/fxml/MainMenu.fxml";

	@Override
	public void start(Stage stage) {
		try {
			// Load the MainMenu.fxml
			FXMLLoader loader = new FXMLLoader(getClass().getResource(MAIN_MENU));
			Parent root = loader.load();

			Controller gameController = new Controller(stage);
			MainMenuController mainMenuController = loader.getController();
			mainMenuController.initialize(stage, gameController);

			// Set up the scene and show the stage
			Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
			stage.setTitle(TITLE);
			stage.setResizable(false);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch();
	}
}
