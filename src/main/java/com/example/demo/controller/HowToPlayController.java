package com.example.demo.controller;

import  com.example.demo.MainMenu;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class HowToPlayController {

    private Stage stage;
    private Controller gameController;
    private static final Logger logger = Logger.getLogger(HowToPlayController.class.getName());

    // Initialize method to set the stage and game controller
    public void initialize(Stage stage, Controller gameController) {
        this.stage = stage;
        this.gameController = gameController;
    }
    @FXML
    public void backToMainMenu() {
        try {
            // Call the MainMenu.showMainMenu method to show the main menu
            MainMenu.showMainMenu(stage, gameController);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error Showing Main Menu", e);
        }
    }

}
