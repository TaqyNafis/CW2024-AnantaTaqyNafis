package com.example.demo.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The MainMenuController class is responsible for managing the main menu of the game.
 * It provides options to start different game modes, view controls, and learn how to play.
 */
public class MainMenuController {

    /**
     * Button for selecting the arcade mode.
     */
    public Button arcadeButton;
    /**
     * Button for selecting the endless mode.
     */
    public Button endlessButton;
    /**
     * Button for accessing the "How to Play" menu.
     */
    public Button howtoplayButton;
    /**
     * Button for accessing the controls menu.
     */
    public Button controlsButton;
    /**
     * The primary stage used for displaying scenes.
     */
    private Stage stage;
    /**
     * The controller for managing the arcade game mode.
     */
    private ArcadeController gameArcadeController;

    /**
     * The controller for managing the endless game mode.
     */
    private EndlessController endlessController;
    /**
     * Base location of How To Play FXML file
     */
    private static final String HOW_TO_PLAY_FXML = "/fxml/HowToPlay.fxml";
    /**
     * Base location of Controls FXML file
     */
    private static final String CONTROL_FXML = "/fxml/Controls.fxml";
    /**
     * Logger for logging errors and informational messages.
     */
    private static final Logger logger = Logger.getLogger(MainMenuController.class.getName());

    /**
     * Initializes the {@code MainMenuController} with the primary stage and arcade game controller.
     *
     * @param stage                the primary stage for the application
     * @param gameArcadeController the controller for the arcade game mode
     */
    public void initialize(Stage stage, ArcadeController gameArcadeController) {
        this.stage = stage;
        this.gameArcadeController = gameArcadeController;
        this.endlessController = new EndlessController(stage);
    }
    /**
     * Starts the arcade mode by initializing and launching the ArcadeController.
     * This method adjusts the stage size for the arcade mode and handles any errors encountered.
     */
    @FXML
    public void startArcadeMode() {
        try {
            stage.setWidth(1300);
            stage.setHeight(750);
            gameArcadeController.launchArcadeMode();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error starting Arcade Mode", e);
        }
    }

    /**
     * Starts the endless mode by initializing and launching the EndlessController.
     * This method adjusts the stage size for the endless mode and handles any errors encountered.
     */
    @FXML
    public void startEndlessMode() {
        try {
            stage.setWidth(1300);
            stage.setHeight(750);
            endlessController.launchEndlessMode();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error starting Endless Mode", e);
        }
    }
    /**
     * Displays the "How to Play" menu by loading the corresponding FXML file.
     * It initializes the HowToPlayController and sets the new scene to the stage.
     * This method handles any errors encountered during the loading process.
     */
    @FXML
    public void showHowToPlay() {
        try {
            // Load fxml file for how to play
            FXMLLoader loader = new FXMLLoader(getClass().getResource(HOW_TO_PLAY_FXML));
            Parent root = loader.load();

            // Initialize HowToPlayController
            HowToPlayController howToPlayController = loader.getController();
            howToPlayController.initialize(stage, gameArcadeController);

            // Set the new scene to the stage
            Scene scene = new Scene(root);
            stage.setWidth(520);
            stage.setHeight(520);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error starting How to Play Menu", e);
        }
    }
    /**
     * Displays the control menu by loading the corresponding FXML file.
     * It initializes the ControlController and sets the new scene to the stage.
     * This method handles any errors encountered during the loading process.
     */
    @FXML
    public void goToControl() {
        try {
            // Load fxml file for how to play
            FXMLLoader loader = new FXMLLoader(getClass().getResource(CONTROL_FXML));
            Parent root = loader.load();

            // Initialize Control
            ControlController controlController = loader.getController();
            controlController.initialize(stage, gameArcadeController);

            // Set the new scene to the stage
            Scene scene = new Scene(root);
            stage.setWidth(400);
            stage.setHeight(600);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error starting Control Menu", e);
        }
    }


}
