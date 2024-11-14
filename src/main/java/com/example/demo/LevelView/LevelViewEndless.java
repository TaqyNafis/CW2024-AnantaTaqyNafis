package com.example.demo.LevelView;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class LevelViewEndless extends LevelView {

    private static final int SCORE_Y_POSITION=20;

    public LevelViewEndless(Group root, int heartsToDisplay) {
        super(root, heartsToDisplay);
        Group topLayer = new Group();
        Group midLayer = new Group();
        Group bottomLayer = new Group();

        root.getChildren().addAll(bottomLayer, midLayer, topLayer);

        Text killCount = new Text("Number of Kill: i");
        killCount.setFont(new Font("Arial", 18));
        killCount.setFill(Color.BLACK);
        int SCORE_X_POSITION = 1130;
        killCount.setX(SCORE_X_POSITION);
        killCount.setY(SCORE_Y_POSITION);

        topLayer.getChildren().add(killCount);


    }
}
