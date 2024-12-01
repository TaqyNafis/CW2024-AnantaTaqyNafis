package com.example.demo.display;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;
/**
 * The `PauseMenuImage` class represents an image that is displayed as a pause menu in the game.
 * The image is initially hidden and can be toggled to show or hide the pause menu.
 */
public class PauseMenuImage extends ImageView {
    /**
     * The file path to the pause menu image resource.
     */
    private static final String IMAGE_NAME = "/com/example/demo/images/pauseMenu.png";
    /**
     * The height of the pause menu image.
     */
    private static final int HEIGHT = 268;
    /**
     * The width of the pause menu image.
     */
    private static final int WIDTH = 604;
    /**
     * Constructs a `PauseMenuImage` instance and sets its position, size, and visibility.
     *
     * @param xPosition The X-coordinate of the image's position on the screen.
     * @param yPosition The Y-coordinate of the image's position on the screen.
     * @throws NullPointerException If the image resource cannot be found.
     */
    public PauseMenuImage(double xPosition, double yPosition) {
        this.setImage(new Image(Objects.requireNonNull(getClass().getResource(IMAGE_NAME)).toExternalForm()));
        this.setVisible(false);
        this.setFitHeight(HEIGHT);
        this.setFitWidth(WIDTH);
        this.setLayoutX(xPosition);
        this.setLayoutY(yPosition);
    }
    /**
     * Shows the pause menu by making the image visible.
     */
    public void showPauseMenu(){
        this.setVisible(true);
    }
    /**
     * Hides the pause menu by making the image invisible.
     */
    public void hidePauseMenu(){
        this.setVisible(false);
    }
}
