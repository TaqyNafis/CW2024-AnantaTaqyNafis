package com.example.demo.Display;

import com.example.demo.controller.Controller;
import com.example.demo.controller.MainMenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class MainMenu {
    private static final int SCREEN_WIDTH = 640;
    private static final int SCREEN_HEIGHT = 430;
    private static final String TITLE = "Sky Battle";
    private static final String MAIN_MENU_FXML = "/fxml/MainMenu.fxml";

    public static void showMainMenu(Stage stage, Controller gameController) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainMenu.class.getResource(MAIN_MENU_FXML));
        Parent root = loader.load();

        MainMenuController mainMenuController = loader.getController();
        mainMenuController.initialize(stage, gameController);

        Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
        stage.setTitle(TITLE);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setWidth(SCREEN_WIDTH);
        stage.setHeight(SCREEN_HEIGHT);
        stage.show();
    }
}


