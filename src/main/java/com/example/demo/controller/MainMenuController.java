package com.example.demo.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {

    private Stage stage;
    private Controller gameController;

    private static final String HOW_TO_PLAY_FXML = "/fxml/HowToPlay.fxml";

    // Initialize method to set the stage and game controller
    public void initialize(Stage stage, Controller gameController) {
        this.stage = stage;
        this.gameController = gameController;
    }

    @FXML
    public void startArcadeMode() {
        try {
            stage.setWidth(1300);
            stage.setHeight(750);
            gameController.launchGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void showHowToPlay() {
        try {
            // Load fxml file for how to play
            FXMLLoader loader = new FXMLLoader(getClass().getResource(HOW_TO_PLAY_FXML));
            Parent root = loader.load();

            // Initialize the HowToPlayController with the current stage and game controller
            HowToPlayController howToPlayController = loader.getController();
            howToPlayController.initialize(stage, gameController);

            // Set the new scene to the stage
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
