package com.example.demo.controller;

import  com.example.demo.MainMenu;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import java.io.IOException;

public class ControlController {
    private Stage stage;
    private Controller gameController;

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
            e.printStackTrace();
        }
    }

}
