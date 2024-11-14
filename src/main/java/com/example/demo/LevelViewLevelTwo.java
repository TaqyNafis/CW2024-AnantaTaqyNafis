package com.example.demo;

import javafx.scene.Group;

public class LevelViewLevelTwo extends LevelView {

	public LevelViewLevelTwo(Group root, int heartsToDisplay) {
		super(root, heartsToDisplay);
		Group bottomLayer = new Group();
		Group midLayer = new Group();
		Group topLayer = new Group();

		root.getChildren().addAll(bottomLayer, midLayer, topLayer);

	}


}
