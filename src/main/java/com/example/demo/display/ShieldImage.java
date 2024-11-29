package com.example.demo.display;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

/**
 * The `ShieldImage` class represents a visual shield that can be displayed on the screen.
 * The shield image is initially hidden and can be toggled to show or hide it.
 */
public class ShieldImage extends ImageView {
	/**
	 * The file path to the shield image resource.
	 */
	private static final String IMAGE_NAME = "/com/example/demo/images/shield.png";
	/**
	 * The size of the shield image (both width and height).
	 */
	private static final int SHIELD_SIZE = 200;
	/**
	 * Constructs a `ShieldImage` instance and sets its position, size, and visibility.
	 *
	 * @param xPosition The X-coordinate of the shield's position on the screen.
	 * @param yPosition The Y-coordinate of the shield's position on the screen.
	 * @throws NullPointerException If the shield image resource cannot be found.
	 */
	public ShieldImage(double xPosition, double yPosition) {
		this.setLayoutX(xPosition);
		this.setLayoutY(yPosition);
		this.setImage(new Image(Objects.requireNonNull(getClass().getResource(IMAGE_NAME)).toExternalForm()));
		this.setVisible(false);
		this.setFitHeight(SHIELD_SIZE);
		this.setFitWidth(SHIELD_SIZE);
	}
	/**
	 * Displays the shield by making the image visible.
	 */
	public void showShield() {
		this.setVisible(true);
	}
	/**
	 * Hides the shield by making the image invisible.
	 */
	public void hideShield() {

		this.setVisible(false);
	}

}
