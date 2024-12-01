package com.example.demo.controller;

import com.example.demo.display.MainMenu;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The {@code HowToPlayController} class manages the "How to Play" screen in the game.
 * It provides the functionality for displaying instructions or help on how to play the game.
 * The controller allows users to navigate back to the main menu from the "How to Play" screen.
 * <p>
 * This class is used when the player clicks the "How to Play" button from the main menu.
 * It handles user interactions with the "Back to Main Menu" button, allowing for seamless
 * navigation between screens.
 * </p>
 */
public class HowToPlayController {
    /**
     * The button that allows the user to return to the main menu from the "How to Play" screen.
     */
    public Button backtomenuButton;
    /**
     * The primary stage for the JavaFX application, used for displaying the scenes.
     */

    private Stage stage;
    /**
     * The primary stage for the JavaFX application, used for displaying the scenes.
     */

    private ArcadeController gameArcadeController;
    /**
     * Logger for recording errors and important events in the HowToPlayController class.
     */
    private static final Logger logger = Logger.getLogger(HowToPlayController.class.getName());
    /**
     * Initializes the HowToPlayController with the provided stage and game arcade controller.
     * This method is called to set up the controller with its necessary dependencies.
     *
     * @param stage The primary stage for the application.
     * @param gameArcadeController The controller for managing the arcade mode.
     */
    public void initialize(Stage stage, ArcadeController gameArcadeController) {
        this.stage = stage;
        this.gameArcadeController = gameArcadeController;
    }
    /**
     * Navigates back to the main menu from the "How to Play" screen.
     * This method is triggered by clicking a "Back" button.
     */
    @FXML
    public void backToMainMenu() {
        try {
            // Call the MainMenu.showMainMenu method to show the main menu
            MainMenu.showMainMenu(stage, gameArcadeController);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error Showing Main Menu", e);
        }
    }

}
