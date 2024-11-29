package com.example.demo.display;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.Objects;
/**
 * The `HeartDisplay` class creates a visual display of hearts in a horizontal layout.
 * Each heart represents a unit of health, displayed as an image.
 */
public class HeartDisplay {
	/**
	 * The file path to the heart image resource.
	 */
	private static final String HEART_IMAGE_NAME = "/com/example/demo/images/heart.png";
	/**
	 * The height of each heart image.
	 */
	private static final int HEART_HEIGHT = 50;
	/**
	 * The index of the first item in the container, used when removing hearts.
	 */
	private static final int INDEX_OF_FIRST_ITEM = 0;
	/**
	 * The container that holds the heart images in a horizontal layout.
	 */
	private HBox container;
	/**
	 * The x-coordinate position of the heart container.
	 */
	private final double containerXPosition;
	/**
	 * The y-coordinate position of the heart container.
	 */
	private final double containerYPosition;
	/**
	 * The y-coordinate position of the heart container.
	 */
	private final int numberOfHeartsToDisplay;

	/**
	 * Constructs a new `HeartDisplay` object with the specified position and number of hearts.
	 *
	 * @param xPosition The x-coordinate of the heart container.
	 * @param yPosition The y-coordinate of the heart container.
	 * @param heartsToDisplay The number of hearts to display initially.
	 */
	public HeartDisplay(double xPosition, double yPosition, int heartsToDisplay) {
		this.containerXPosition = xPosition;
		this.containerYPosition = yPosition;
		this.numberOfHeartsToDisplay = heartsToDisplay;
		initializeContainer();
		initializeHearts();
	}
	/**
	 * Initializes the container for the heart display.
	 * The container is an `HBox` layout positioned at the specified coordinates.
	 */
	private void initializeContainer() {
		container = new HBox();
		container.setLayoutX(containerXPosition);
		container.setLayoutY(containerYPosition);		
	}
	/**
	 * Initializes the hearts within the container.
	 * Each heart is represented as an `ImageView` with a fixed height.
	 */
	private void initializeHearts() {
		for (int i = 0; i < numberOfHeartsToDisplay; i++) {
			ImageView heart = new ImageView(new Image(Objects.requireNonNull(getClass().getResource(HEART_IMAGE_NAME)).toExternalForm()));

			heart.setFitHeight(HEART_HEIGHT);
			heart.setPreserveRatio(true);
			container.getChildren().add(heart);
		}
	}
	/**
	 * Removes one heart from the container, if any hearts are present.
	 * The first heart in the container is removed.
	 */
	public void removeHeart() {
		if (!container.getChildren().isEmpty())
			container.getChildren().remove(INDEX_OF_FIRST_ITEM);
	}
	/**
	 * Returns the container holding the heart images.
	 *
	 * @return The `HBox` container with the heart images.
	 */
	public HBox getContainer() {
		return container;
	}

}
