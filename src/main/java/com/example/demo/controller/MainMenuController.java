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
    private EndlessController endlessController;

    private static final String HOW_TO_PLAY_FXML = "/fxml/HowToPlay.fxml";
    private static final String CONTROL_FXML = "/fxml/Controls.fxml";

    // Initialize method to set the stage and game controller
    public void initialize(Stage stage, Controller gameController) {
        this.stage = stage;
        this.gameController = gameController;
        this.endlessController = new EndlessController(stage);
    }

    @FXML
    public void startArcadeMode() {
        try {
            stage.setWidth(1300);
            stage.setHeight(750);
            gameController.launchArcadeMode();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void startEndlessMode() {
        try {
            stage.setWidth(1300);
            stage.setHeight(750);
            endlessController.launchEndlessMode();
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

            // Initialize HowToPlayController
            HowToPlayController howToPlayController = loader.getController();
            howToPlayController.initialize(stage, gameController);

            // Set the new scene to the stage
            Scene scene = new Scene(root);
            stage.setWidth(520);
            stage.setHeight(520);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goToControl() {
        try {
            // Load fxml file for how to play
            FXMLLoader loader = new FXMLLoader(getClass().getResource(CONTROL_FXML));
            Parent root = loader.load();

            // Initialize Control
            ControlController controlController = loader.getController();
            controlController.initialize(stage, gameController);

            // Set the new scene to the stage
            Scene scene = new Scene(root);
            stage.setWidth(390);
            stage.setHeight(600);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
