package com.example.demo.levelparent;

import javafx.scene.Group;
import javafx.scene.input.KeyCode;


public abstract class LevelParentArcade extends LevelParent {

    private static final String LEVEL_ONE_CLASS_NAME = "com.example.demo.LevelOne";

    public LevelParentArcade(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth) {
        super(backgroundImageName, screenHeight, screenWidth, playerInitialHealth);

        Group root = new Group();
        Group topLayer = new Group();
        Group midLayer = new Group();
        Group bottomLayer = new Group();
        root.getChildren().addAll(bottomLayer, midLayer, topLayer);
        KeyListeners();

    }

    private void KeyListeners(){
        this.getScene().setOnKeyPressed(e -> {
            KeyCode kc = e.getCode();
            if (kc == KeyCode.R) {resetLevel(LEVEL_ONE_CLASS_NAME);}

        });
    }



}
