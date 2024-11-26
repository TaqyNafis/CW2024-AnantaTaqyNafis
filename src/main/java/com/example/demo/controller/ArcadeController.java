package com.example.demo.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.application.Platform;
import com.example.demo.levelparent.LevelParentArcade;

public class ArcadeController implements PropertyChangeListener {

	private final Stage stage;
	private static final String LEVEL_ONE_CLASS_NAME = "com.example.demo.LevelOne";

	public ArcadeController(Stage stage) {
		this.stage = stage;

		// Listener when stage width and height change
		stage.widthProperty().addListener((obs, oldVal, newVal) -> centerWindow());
		stage.heightProperty().addListener((obs, oldVal, newVal) -> centerWindow());
	}

	public void launchArcadeMode() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		stage.show();
		goToLevel(LEVEL_ONE_CLASS_NAME);
	}

	private void goToLevel(String className) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		Class<?> myClass = Class.forName(className);
		Constructor<?> constructor = myClass.getConstructor(double.class, double.class);
		LevelParentArcade myLevel = (LevelParentArcade) constructor.newInstance(stage.getHeight(), stage.getWidth());

		myLevel.addPropertyChangeListener(this);

		Scene scene = myLevel.initializeScene();
		stage.setScene(scene);
		myLevel.startGame();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		try {
			goToLevel((String) evt.getNewValue());
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				 | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText(e.getClass().toString());
			alert.show();
		}
	}

	// Function to center the window whenever the screen size changes
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
