package com.example.demo;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class LevelView {
	
	private static final double HEART_DISPLAY_X_POSITION = 5;
	private static final double HEART_DISPLAY_Y_POSITION = 25;
	private static final int IMAGE_X_POSITION = 335;
	private static final int IMAGE_Y_POSITION = 95;
	private static final int SCREEN_WIDTH = 1300;
	private static final int SCREEN_HEIGHT = 750;

	private final Group topLayer;
	private final Group midLayer;
	private final Group bottomLayer;

	private final WinImage winImage;
	private final GameOverImage gameOverImage;
	private final HeartDisplay heartDisplay;
	private final PauseMenuImage pauseMenuImage;
	private final Rectangle darkOverlay;
	
	public LevelView(Group root, int heartsToDisplay) {
		this.topLayer = new Group();
		this.midLayer = new Group();
		this.bottomLayer = new Group();

		root.getChildren().addAll(bottomLayer, midLayer, topLayer);

		this.heartDisplay = new HeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);
		this.winImage = new WinImage(IMAGE_X_POSITION, IMAGE_Y_POSITION+35);
		this.gameOverImage = new GameOverImage(IMAGE_X_POSITION, IMAGE_Y_POSITION);
		this.pauseMenuImage = new PauseMenuImage(IMAGE_X_POSITION, IMAGE_Y_POSITION);
		this.darkOverlay = new Rectangle(SCREEN_WIDTH, SCREEN_HEIGHT, Color.rgb(0, 0, 0, 0.6));
		darkOverlay.setVisible(false);
	}
	
	public void showHeartDisplay() {
		topLayer.getChildren().add(heartDisplay.getContainer());
	}

	public void showWinImage() {
		topLayer.getChildren().add(winImage);
	}
	
	public void showGameOverImage() {
		topLayer.getChildren().add(gameOverImage);
	}
	
	public void removeHearts(int heartsRemaining) {
		int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
		for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
			heartDisplay.removeHeart();
		}
	}

	public void showPauseMenuImage() {
		//added dark overlay
		if (!topLayer.getChildren().contains(darkOverlay)) {
			topLayer.getChildren().add(darkOverlay);
		}
		darkOverlay.setVisible(true);

		//added pause menu
		if (!topLayer.getChildren().contains(pauseMenuImage)) {
			topLayer.getChildren().add(pauseMenuImage);
		}
		pauseMenuImage.showPauseMenu();
	}

	public  void hidePauseMenuImage() {
		darkOverlay.setVisible(false);
		pauseMenuImage.hidePauseMenu();
	}

}
