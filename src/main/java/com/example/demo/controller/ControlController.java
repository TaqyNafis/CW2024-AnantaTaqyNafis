package com.example.demo.controller;

import com.example.demo.display.MainMenu;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The HowToPlayController class is responsible for managing the "Controls" screen in the game.
 * It provides functionality to navigate back to the main menu from the "Controls" screen.
 */
public class ControlController {
    private Stage stage;
    private ArcadeController gameArcadeController;
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
