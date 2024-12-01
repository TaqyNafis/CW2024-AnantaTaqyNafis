package com.example.demo.display;

import com.example.demo.controller.ArcadeController;
import com.example.demo.controller.MainMenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
/**
 * The `MainMenu` class is a utility class that provides functionality to display the main menu of the application.
 * It sets up the main menu scene with the specified FXML layout and initializes the `MainMenuController`.
 */
public class MainMenu {
    /**
     * The width of the main menu screen.
     */
    private static final int SCREEN_WIDTH = 640;
    /**
     * The height of the main menu screen.
     */
    private static final int SCREEN_HEIGHT = 430;
    /**
     * The title of the main menu window.
     */
    private static final String TITLE = "Space Invaders";
    /**
     * The file path to the FXML layout for the main menu.
     */
    private static final String MAIN_MENU_FXML = "/fxml/MainMenu.fxml";
    /**
     * Private constructor to prevent instantiation of the utility class.
     *
     * @throws UnsupportedOperationException if an attempt is made to instantiate the class.
     */
    private MainMenu() {
        throw new UnsupportedOperationException("MainMenu is a utility class and cannot be instantiated.");
    }
    /**
     * Displays the main menu on the specified stage.
     *
     * @param stage The stage on which the main menu should be displayed.
     * @param gameArcadeController The `ArcadeController` used to manage arcade gameplay logic.
     * @throws IOException If there is an error loading the FXML file for the main menu.
     */
    public static void showMainMenu(Stage stage, ArcadeController gameArcadeController) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainMenu.class.getResource(MAIN_MENU_FXML));
        Parent root = loader.load();

        MainMenuController mainMenuController = loader.getController();
        mainMenuController.initialize(stage, gameArcadeController);

        Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
        stage.setTitle(TITLE);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setWidth(SCREEN_WIDTH);
        stage.setHeight(SCREEN_HEIGHT);
        stage.show();
    }
}


