package com.example.demo.levelview;

import javafx.scene.Group;

/**
 * The `LevelViewEndless` class is a specialized view for the endless mode of the game.
 * It extends the `LevelView` class, providing a structured layout with three distinct visual layers.
 */
public class LevelViewEndless extends LevelView {

    /**
     * Constructs a `LevelViewEndless` instance.
     * Initializes the level view with three layers: bottom, mid, and top, which are added to the root group.
     *
     * @param root           The root group that holds all visual elements of the level.
     * @param heartsToDisplay The number of hearts (lives) to display in the level UI.
     */
    public LevelViewEndless(Group root, int heartsToDisplay) {
        super(root, heartsToDisplay);
        Group topLayer = new Group();
        Group midLayer = new Group();
        Group bottomLayer = new Group();

        root.getChildren().addAll(bottomLayer, midLayer, topLayer);
    }
}
