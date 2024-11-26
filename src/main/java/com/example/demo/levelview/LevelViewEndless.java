package com.example.demo.levelview;

import javafx.scene.Group;

public class LevelViewEndless extends LevelView {

    public LevelViewEndless(Group root, int heartsToDisplay) {
        super(root, heartsToDisplay);
        Group topLayer = new Group();
        Group midLayer = new Group();
        Group bottomLayer = new Group();

        root.getChildren().addAll(bottomLayer, midLayer, topLayer);

    }

}
