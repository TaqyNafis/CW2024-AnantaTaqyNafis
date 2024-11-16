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
import com.example.demo.LevelParent.LevelParentEndless;

public class EndlessController implements PropertyChangeListener  {

    private final Stage stage;
    private static final String ENDLESS_CLASS_NAME = "com.example.demo.LevelEndless";

    public EndlessController(Stage stage) {
        this.stage = stage;

        // Add listeners to center the stage on resize
        stage.widthProperty().addListener((obs, oldVal, newVal) -> centerWindow());
        stage.heightProperty().addListener((obs, oldVal, newVal) -> centerWindow());
    }

    public void launchEndlessMode() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
            InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException  {

        stage.show();
        goToLevel(ENDLESS_CLASS_NAME);
    }

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
