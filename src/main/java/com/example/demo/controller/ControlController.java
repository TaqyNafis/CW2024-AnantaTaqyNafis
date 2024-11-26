package com.example.demo.controller;

import com.example.demo.display.MainMenu;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ControlController {
    private Stage stage;
    private ArcadeController gameArcadeController;
    private static final Logger logger = Logger.getLogger(ControlController.class.getName());

    // Initialize method to set the stage and game controller
    public void initialize(Stage stage, ArcadeController gameArcadeController) {
        this.stage = stage;
        this.gameArcadeController = gameArcadeController;
    }

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
