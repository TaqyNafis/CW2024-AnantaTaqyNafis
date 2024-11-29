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

/**
 * The controller class for managing the arcade mode of the game.
 * It listens for changes to the stage dimensions, handles transitions between levels,
 * and manages the display of the game's scenes.
 */
public class ArcadeController implements PropertyChangeListener {

	private final Stage stage;
	/**
	 * Base location of Level One
	 */
	private static final String LEVEL_ONE_CLASS_NAME = "com.example.demo.LevelOne";

	/**
	 * Constructor to initialize the ArcadeController with the given stage.
	 *
	 * @param stage The main application stage.
	 */
	public ArcadeController(Stage stage) {
		this.stage = stage;

		// Listener when stage width and height change
		stage.widthProperty().addListener((obs, oldVal, newVal) -> centerWindow());
		stage.heightProperty().addListener((obs, oldVal, newVal) -> centerWindow());
	}
	/**
	 * Launches the arcade mode by showing the stage and starting the first level.
	 *
	 * @throws ClassNotFoundException If the level class cannot be found.
	 * @throws NoSuchMethodException If the constructor of the level class cannot be found.
	 * @throws SecurityException If the constructor of the level class cannot be accessed.
	 * @throws InstantiationException If the level class cannot be instantiated.
	 * @throws IllegalAccessException If there is an illegal access to the constructor of the level class.
	 * @throws IllegalArgumentException If the constructor parameters are incorrect.
	 * @throws InvocationTargetException If the constructor throws an exception.
	 */
	public void launchArcadeMode() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		stage.show();
		goToLevel(LEVEL_ONE_CLASS_NAME);
	}
	/**
	 * Transitions to a specific level by dynamically loading the class,
	 * instantiating it, and setting the corresponding scene.
	 *
	 * @param className The fully qualified name of the level class to transition to.
	 * @throws ClassNotFoundException If the level class cannot be found.
	 * @throws NoSuchMethodException If the constructor of the level class cannot be found.
	 * @throws SecurityException If the constructor of the level class cannot be accessed.
	 * @throws InstantiationException If the level class cannot be instantiated.
	 * @throws IllegalAccessException If there is an illegal access to the constructor of the level class.
	 * @throws IllegalArgumentException If the constructor parameters are incorrect.
	 * @throws InvocationTargetException If the constructor throws an exception.
	 */
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
	/**
	 * Handles property change events, which are triggered when the game transitions to a new level.
	 *
	 * @param evt The property change event containing the new level's class name.
	 */
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
	/**
	 * Centers the window on the screen whenever the stage size changes.
	 * This method adjusts the position of the window to keep it centered on the screen.
	 */
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
