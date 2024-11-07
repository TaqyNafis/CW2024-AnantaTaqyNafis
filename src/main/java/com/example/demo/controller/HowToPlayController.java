package com.example.demo.controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class HowToPlayController {

    private Stage stage;
    private Controller gameController;
    private static final String HOW_TO_PLAY_FXML = "/fxml/MainMenu.fxml";

    // Initialize method to set the stage and game controller
    public void initialize(Stage stage, Controller gameController) {
        this.stage = stage;
        this.gameController = gameController;
    }

    @FXML
    public void backToMainMenu() {
        try {
            // Load the MainMenu.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource(HOW_TO_PLAY_FXML));
            Parent root = loader.load();

            // Initialize the MainMenuController with the current stage and game controller
            MainMenuController mainMenuController = loader.getController();
            mainMenuController.initialize(stage, gameController);

            // Set the new scene to the stage
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
