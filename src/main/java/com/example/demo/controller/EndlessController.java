package com.example.demo.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.Screen;
import javafx.application.Platform;
import com.example.demo.levelparent.LevelParentEndless;

/**
 * The EndlessController class manages the Endless Mode in the game.
 * It handles the initialization of levels, stage resizing, and transitions between levels in the Endless Mode.
 * This class also ensures that the game window is centered whenever the stage's dimensions change.
 */
public class EndlessController implements PropertyChangeListener  {

    private final Stage stage;
    /**
     * Base location of Level Endless
     */
    private static final String ENDLESS_CLASS_NAME = "com.example.demo.LevelEndless";

    /**
     * Constructs an EndlessController with the given stage.
     * Sets up listeners to ensure the stage is centered on resize.
     *
     * @param stage The primary stage for the application.
     */
    public EndlessController(Stage stage) {
        this.stage = stage;

        // Add listeners to center the stage on resize
        stage.widthProperty().addListener((obs, oldVal, newVal) -> centerWindow());
        stage.heightProperty().addListener((obs, oldVal, newVal) -> centerWindow());
    }

    /**
     * Launches the Endless Mode by loading the initial level.
     *
     * @throws ClassNotFoundException if the level class cannot be found.
     * @throws NoSuchMethodException if the level class does not have the required constructor.
     * @throws InvocationTargetException if an exception occurs during level instantiation.
     * @throws InstantiationException if the level class cannot be instantiated.
     * @throws IllegalAccessException if the level class constructor is inaccessible.
     * @throws IllegalArgumentException if invalid arguments are provided to the constructor.
     */
    public void launchEndlessMode() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
            InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException  {

        stage.show();
        goToLevel(ENDLESS_CLASS_NAME);
    }

    /**
     * Loads and initializes a new level for Endless Mode.
     *
     * @param className The fully qualified name of the level class.
     * @throws ClassNotFoundException if the level class cannot be found.
     * @throws NoSuchMethodException if the level class does not have the required constructor.
     * @throws InvocationTargetException if an exception occurs during level instantiation.
     * @throws InstantiationException if the level class cannot be instantiated.
     * @throws IllegalAccessException if the level class constructor is inaccessible.
     * @throws IllegalArgumentException if invalid arguments are provided to the constructor.
     */
    private void goToLevel(String className) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
            InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class<?> myClass = Class.forName(className);
        Constructor<?> constructor = myClass.getConstructor(double.class, double.class);
        LevelParentEndless myLevel = (LevelParentEndless) constructor.newInstance(stage.getHeight(), stage.getWidth());
        myLevel.addPropertyChangeListener(this);
        Scene scene = myLevel.initializeScene();
        stage.setScene(scene);
        myLevel.startGame();

    }


    /**
     * Handles property change events to transition to a new level when notified.
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
     * Centers the stage on the screen whenever the stage's dimensions change.
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
