package com.example.demo.actor;

import javafx.scene.image.*;

import java.util.Objects;

/**
 * Represents an active actor in the game, with common functionalities
 * such as movement and image management.
 * <p>
 * An active actor is a game entity that has a visual representation
 * (an image) and can be moved around the game scene.
 * </p>
 */
public abstract class ActiveActor extends ImageView {

	/**
	 * The base location for actor images.
	 */
	private static final String IMAGE_LOCATION = "/com/example/demo/images/";

	/**
	 * Constructs an ActiveActor with the specified image and initial position.
	 *
	 * @param imageName    the name of the image file for this actor
	 * @param imageHeight  the height of the image
	 * @param initialXPos  the initial X position of the actor
	 * @param initialYPos  the initial Y position of the actor
	 */
	protected ActiveActor(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		this.setImage(new Image(Objects.requireNonNull(getClass().getResource(IMAGE_LOCATION + imageName)).toExternalForm()));
		this.setLayoutX(initialXPos);
		this.setLayoutY(initialYPos);
		this.setFitHeight(imageHeight);
		this.setPreserveRatio(true);
	}

	/**
	 * Updates the position of the actor.
	 * <p>
	 * This method must be implemented by subclasses to update the position of the actor
	 * based on the specific game logic for that actor.
	 * </p>
	 */
	public abstract void updatePosition();

	/**
	 * Moves the actor horizontally.
	 * <p>
	 * This method updates the actor's horizontal position by adding the specified distance
	 * to its current position.
	 * </p>
	 *
	 * @param horizontalMove the distance to move horizontally
	 */
	protected void moveHorizontally(double horizontalMove) {
		this.setTranslateX(getTranslateX() + horizontalMove);
	}

	/**
	 * Moves the actor vertically.
	 * <p>
	 * This method updates the actor's vertical position by adding the specified distance
	 * to its current position.
	 * </p>
	 *
	 * @param verticalMove the distance to move vertically
	 */
	protected void moveVertically(double verticalMove) {
		this.setTranslateY(getTranslateY() + verticalMove);
	}
}
