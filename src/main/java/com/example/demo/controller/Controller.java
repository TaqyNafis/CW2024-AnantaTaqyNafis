package com.example.demo.controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.Screen;
import javafx.application.Platform;
import com.example.demo.LevelParent;

public class Controller implements Observer {

	private final Stage stage;
	private static final String LEVEL_ONE_CLASS_NAME = "com.example.demo.LevelOne";

	public Controller(Stage stage) {
		this.stage = stage;

		// Add listeners to center the stage on resize
		stage.widthProperty().addListener((obs, oldVal, newVal) -> centerWindow());
		stage.heightProperty().addListener((obs, oldVal, newVal) -> centerWindow());
	}

	public void launchArcadeMode() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException  {

			stage.show();
			goToLevel(LEVEL_ONE_CLASS_NAME);
	}

	private void goToLevel(String className) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
			Class<?> myClass = Class.forName(className);
			Constructor<?> constructor = myClass.getConstructor(double.class, double.class);
			LevelParent myLevel = (LevelParent) constructor.newInstance(stage.getHeight(), stage.getWidth());
			myLevel.addObserver(this);
			Scene scene = myLevel.initializeScene();
			stage.setScene(scene);
			myLevel.startGame();

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		try {
			goToLevel((String) arg1);
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText(e.getClass().toString());
			alert.show();
		}
	}

	//function to centered window whenever screen change sized
	private void centerWindow() {
		Platform.runLater(() -> {
			// Get the screen bounds
			double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
			double screenHeight = Screen.getPrimary().getVisualBounds().getHeight();

			// Calculate the new position
			double newX = (screenWidth - stage.getWidth()) / 2;
			double newY = (screenHeight - stage.getHeight()) / 2;

			// Set the new position of the stage
			stage.setX(newX);
			stage.setY(newY);
		});
	}

}
