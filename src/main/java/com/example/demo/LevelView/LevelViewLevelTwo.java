package com.example.demo.LevelView;

import javafx.scene.Group;

public class LevelViewLevelTwo extends LevelView {


	private final Group topLayer;

	public LevelViewLevelTwo(Group root, int heartsToDisplay){
		super(root, heartsToDisplay);
		Group bottomLayer = new Group();
		Group midLayer = new Group();
		this.topLayer = new Group();

		root.getChildren().addAll(bottomLayer, midLayer, topLayer);
	}

}
