package com.example.demo;

import javafx.scene.Group;

public class LevelViewLevelTwo extends LevelView {

	private static final int SHIELD_X_POSITION = 1150;
	private static final int SHIELD_Y_POSITION = 500;

	private final Group root;
	private final Group topLayer;
	private final Group midLayer;
	private final Group bottomLayer;

	private final ShieldImage shieldImage;
	
	public LevelViewLevelTwo(Group root, int heartsToDisplay) {
		super(root, heartsToDisplay);
		this.root = new Group();
		this.topLayer = new Group();
		this.midLayer = new Group();
		this.bottomLayer = new Group();

		root.getChildren().addAll(bottomLayer, midLayer, topLayer);

		this.shieldImage = new ShieldImage(SHIELD_X_POSITION, SHIELD_Y_POSITION);
		addImagesToRoot();
	}
	
	private void addImagesToRoot() {
		midLayer.getChildren().addAll(shieldImage);
	}

}
