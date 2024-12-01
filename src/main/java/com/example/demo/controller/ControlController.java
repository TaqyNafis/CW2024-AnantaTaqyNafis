package com.example.demo.controller;

import com.example.demo.display.MainMenu;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The {@code ControlController} class is responsible for managing the "Controls" screen in the game.
 * It provides functionality to navigate back to the main menu from the "Controls" screen.
 * This class is used when the user wants to see the controls or return to the main menu.
 * <p>
 * It handles interactions with the user through a button to return to the main menu.
 * </p>
 */
public class ControlController {
    /**
     * The button that allows the user to navigate back to the main menu from the "Controls" screen.
     */
    public Button backtomenuButton;
    /**
     * The main stage used for displaying the various scenes in the application.
     * This stage is passed to the controller to manage scene transitions.
     */
    private Stage stage;
    /**
     * The ArcadeController that manages the gameplay in the arcade mode.
     * This controller is used to facilitate transitions and gameplay management.
     */
    private ArcadeController gameArcadeController;
    /**
     * Logger for logging errors related to the ControlController class.
     * It logs any issues that occur while interacting with the controls screen.
     */
    private static final Logger logger = Logger.getLogger(ControlController.class.getName());

    /**
     * Initializes the controller with the main application stage and the game arcade controller.
     *
     * @param stage The main application stage used for displaying scenes.
     * @param gameArcadeController The controller managing arcade gameplay.
     */
    public void initialize(Stage stage, ArcadeController gameArcadeController) {
        this.stage = stage;
        this.gameArcadeController = gameArcadeController;
    }

    /**
     * Navigates back to the main menu from the "Controls" screen.
     * This method is triggered by clicking a "Back" button.
     */
    @FXML
    public void backToMainMenu() {
        try {
            // Call the MainMenu.showMainMenu method to show the main menu
            MainMenu.showMainMenu(stage, gameArcadeController);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error Starting Main Menu", e);
        }
    }

}
