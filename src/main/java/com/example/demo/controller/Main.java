package com.example.demo.controller;

import com.example.demo.MainMenu;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

	@Override
	public void start(Stage stage) {
		try {
			Controller gameController = new Controller(stage);
			MainMenu.showMainMenu(stage, gameController);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch();
	}
}
