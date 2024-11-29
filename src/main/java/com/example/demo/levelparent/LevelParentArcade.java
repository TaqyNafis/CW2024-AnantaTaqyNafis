package com.example.demo.levelparent;

import javafx.scene.Group;
import javafx.scene.input.KeyCode;

/**
 * The `LevelParentArcade` class serves as an abstract base class for arcade-style game levels.
 * It extends `LevelParent`, adding specific functionality such as key listeners and
 * layer initialization for arcade gameplay.
 */
public abstract class LevelParentArcade extends LevelParent {

    /**
     * The Base path for level One
     */
    private static final String LEVEL_ONE_CLASS_NAME = "com.example.demo.LevelOne";

    /**
     * Constructs a new `LevelParentArcade` instance.
     *
     * @param backgroundImageName   The file path for the background image of the level.
     * @param screenHeight          The height of the game screen.
     * @param screenWidth           The width of the game screen.
     * @param playerInitialHealth   The initial health of the player.
     */
    protected LevelParentArcade(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth) {
        super(backgroundImageName, screenHeight, screenWidth, playerInitialHealth);

        // Initialize root and layers for the arcade level.
        Group root = new Group();
        Group topLayer = new Group();
        Group midLayer = new Group();
        Group bottomLayer = new Group();

        // Add layers to the root group.
        root.getChildren().addAll(bottomLayer, midLayer, topLayer);

        // Set up key listeners specific to arcade mode.
        arcadeKeyListeners();
    }

    /**
     * Configures key listeners for arcade levels.
     * Specifically listens for:
     * - The `R` key to reset the level, returning to Level One.
     */
    private void arcadeKeyListeners() {
        this.getScene().setOnKeyPressed(e -> {
            KeyCode kc = e.getCode();
            if (kc == KeyCode.R) {
                resetLevel(LEVEL_ONE_CLASS_NAME);
            }
        });
    }
}
