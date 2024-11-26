package com.example.demo.levelparent;

import javafx.scene.Group;
import javafx.scene.input.KeyCode;


public abstract class LevelParentEndless extends LevelParent {

    private static final String ENDLESS_CLASS_NAME = "com.example.demo.LevelEndless";

    protected LevelParentEndless(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth) {
        super(backgroundImageName, screenHeight, screenWidth, playerInitialHealth);

        Group root = new Group();
        Group topLayer = new Group();
        Group midLayer = new Group();
        Group bottomLayer = new Group();
        root.getChildren().addAll(bottomLayer, midLayer, topLayer);
        keyListeners();

    }

    private void keyListeners(){
        this.getScene().setOnKeyPressed(e -> {
            KeyCode kc = e.getCode();
            if (kc == KeyCode.R) {resetLevel(ENDLESS_CLASS_NAME);}

        });
    }


}
