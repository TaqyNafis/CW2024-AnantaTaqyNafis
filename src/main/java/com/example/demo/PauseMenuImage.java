package com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class PauseMenuImage extends ImageView {
    private static final String IMAGE_NAME = "/com/example/demo/images/pauseMenu.gif";
    private static final int HEIGHT = 500;
    private static final int WIDTH = 600;

    public PauseMenuImage(double xPosition, double yPosition) {
        this.setImage(new Image(Objects.requireNonNull(getClass().getResource(IMAGE_NAME)).toExternalForm()));
        this.setVisible(false);
        this.setFitHeight(HEIGHT);
        this.setFitWidth(WIDTH);
        this.setLayoutX(xPosition);
        this.setLayoutY(yPosition);
    }

    public void showPauseMenu(){
        this.setVisible(true);
    }

    public void hidePauseMenu(){
        this.setVisible(false);
    }
}
