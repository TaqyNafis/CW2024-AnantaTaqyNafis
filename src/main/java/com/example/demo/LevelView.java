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

	private final Group root;
	private final WinImage winImage;
	private final GameOverImage gameOverImage;
	private final HeartDisplay heartDisplay;
	private final PauseMenuImage pauseMenuImage;
	private final Rectangle darkOverlay;
	
	public LevelView(Group root, int heartsToDisplay) {
		this.root = root;
		this.heartDisplay = new HeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);
		this.winImage = new WinImage(IMAGE_X_POSITION, IMAGE_Y_POSITION+35);
		this.gameOverImage = new GameOverImage(IMAGE_X_POSITION, IMAGE_Y_POSITION);
		this.pauseMenuImage = new PauseMenuImage(IMAGE_X_POSITION, IMAGE_Y_POSITION);
		this.darkOverlay = new Rectangle(SCREEN_WIDTH, SCREEN_HEIGHT, Color.rgb(0, 0, 0, 0.6));
		darkOverlay.setVisible(false);
	}
	
	public void showHeartDisplay() {
		root.getChildren().add(heartDisplay.getContainer());
	}

	public void showWinImage() {
		root.getChildren().add(winImage);
	}
	
	public void showGameOverImage() {
		root.getChildren().add(gameOverImage);
	}
	
	public void removeHearts(int heartsRemaining) {
		int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
		for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
			heartDisplay.removeHeart();
		}
	}

	public void showPauseMenuImage() {
		//added dark overlay
		if (!root.getChildren().contains(darkOverlay)) {
			root.getChildren().add(darkOverlay);
		}
		else{
			root.getChildren().remove(darkOverlay);
			root.getChildren().add(darkOverlay);
		}
		darkOverlay.setVisible(true);

		//added pause menu
		if (!root.getChildren().contains(pauseMenuImage)) {
			root.getChildren().add(pauseMenuImage);
		} else {
			root.getChildren().remove(pauseMenuImage);
			root.getChildren().add(pauseMenuImage);//remove image and re-added it to ensure it is always on top
		}
		pauseMenuImage.showPauseMenu();
	}

	public  void hidePauseMenuImage() {
		darkOverlay.setVisible(false);
		pauseMenuImage.hidePauseMenu();
	}

}
