package com.example.demo.display;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

/**
 * The {@code GameOverImage} class represents the "Game Over" image displayed when the player loses the game.
 * It extends {@code ImageView} to show an image with a specific size and position on the screen.
 */
public class GameOverImage extends ImageView {
	/**
	 * The file path to the "Game Over" image resource.
	 */
	private static final String IMAGE_NAME = "/com/example/demo/images/gameOver.png";
	/**
	 * The height of the "Game Over" image.
	 */
	private static final int HEIGHT = 500;
	/**
	 * The width of the "Game Over" image.
	 */
	private static final int WIDTH = 600;

	/**
	 * Constructs a new {@code GameOverImage} object with the specified position.
	 * The image is loaded, and its size and position are set.
	 *
	 * @param xPosition The x-coordinate for the top-left corner of the image.
	 * @param yPosition The y-coordinate for the top-left corner of the image.
	 */
	public GameOverImage(double xPosition, double yPosition) {
		this.setImage(new Image(Objects.requireNonNull(getClass().getResource(IMAGE_NAME)).toExternalForm()));
		this.setFitHeight(HEIGHT);
		this.setFitWidth(WIDTH);
		this.setLayoutX(xPosition);
		this.setLayoutY(yPosition);
	}

}
