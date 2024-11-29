package com.example.demo.levelparent;

import javafx.scene.Group;
import javafx.scene.input.KeyCode;

/**
 * The `LevelParentEndless` class provides a base implementation for endless game levels.
 * It extends the `LevelParent` class, adding specific functionality such as key listeners
 * and initialization for endless levels.
 */
public abstract class LevelParentEndless extends LevelParent {

    /**
     * The base path for endless level.
     */
    private static final String ENDLESS_CLASS_NAME = "com.example.demo.LevelEndless";

    /**
     * Constructs a new `LevelParentEndless` instance.
     *
     * @param backgroundImageName   The file path for the background image of the level.
     * @param screenHeight          The height of the game screen.
     * @param screenWidth           The width of the game screen.
     * @param playerInitialHealth   The initial health of the player.
     */
    protected LevelParentEndless(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth) {
        super(backgroundImageName, screenHeight, screenWidth, playerInitialHealth);

        // Initialize root and layers for the endless level
        Group root = new Group();
        Group topLayer = new Group();
        Group midLayer = new Group();
        Group bottomLayer = new Group();

        // Add layers to the root group
        root.getChildren().addAll(bottomLayer, midLayer, topLayer);

        // Set up key listeners specific to the endless mode
        endlessKeyListeners();
    }

    /**
     * Configures key listeners for endless levels.
     * Specifically listens for:
     * - The `R` key to reset the level.
     */
    private void endlessKeyListeners() {
        this.getScene().setOnKeyPressed(e -> {
            KeyCode kc = e.getCode();
            if (kc == KeyCode.R) {
                resetLevel(ENDLESS_CLASS_NAME);
            }
        });
    }
}
