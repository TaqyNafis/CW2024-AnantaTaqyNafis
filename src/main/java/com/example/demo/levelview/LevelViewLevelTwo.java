package com.example.demo.levelview;

import javafx.scene.Group;

/**
 * The `LevelViewLevelTwo` class is a specialized view for the second level of the game.
 * It extends the `LevelView` class, providing a layered structure for displaying game elements.
 */
public class LevelViewLevelTwo extends LevelView {

	/**
	 * Constructs a `LevelViewLevelTwo` instance.
	 * Initializes the level view with three layers: bottom, mid, and top, which are added to the root group.
	 *
	 * @param root           The root group that holds all visual elements of the level.
	 * @param heartsToDisplay The number of hearts (lives) to display in the level UI.
	 */
	public LevelViewLevelTwo(Group root, int heartsToDisplay) {
		super(root, heartsToDisplay);
		Group bottomLayer = new Group();
		Group midLayer = new Group();
		Group topLayer = new Group();

		root.getChildren().addAll(bottomLayer, midLayer, topLayer);
	}
}
