package com.example.demo.display;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;
/**
 * The `WinImage` class represents a visual display for a win screen image.
 * This image is positioned and sized to be shown when the player wins the game.
 */
public class WinImage extends ImageView {
	/**
	 * The file path to the "You Win" image resource.
	 */
	private static final String IMAGE_NAME = "/com/example/demo/images/youwin.png";
	/**
	 * The height of the win image.
	 */
	private static final int HEIGHT = 500;
	/**
	 * The Width of the win image.
	 */
	private static final int WIDTH = 600;
	/**
	 * Constructs a `WinImage` instance, sets its position, size, and loads the image resource.
	 *
	 * @param xPosition The X-coordinate of the image's position on the screen.
	 * @param yPosition The Y-coordinate of the image's position on the screen.
	 * @throws NullPointerException If the win image resource cannot be found.
	 */
	public WinImage(double xPosition, double yPosition) {
		this.setImage(new Image(Objects.requireNonNull(getClass().getResource(IMAGE_NAME)).toExternalForm()));
		this.setFitHeight(HEIGHT);
		this.setFitWidth(WIDTH);
		this.setLayoutX(xPosition);
		this.setLayoutY(yPosition);
	}


}
