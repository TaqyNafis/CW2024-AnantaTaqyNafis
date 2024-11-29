package com.example.demo.controller;

import com.example.demo.display.MainMenu;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The HowToPlayController class is responsible for managing the "How to Play" screen in the game.
 * It provides functionality to navigate back to the main menu from the "How to Play" screen.
 */
public class HowToPlayController {

    private Stage stage;
    private ArcadeController gameArcadeController;
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
