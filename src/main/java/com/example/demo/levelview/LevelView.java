package com.example.demo.levelview;

import com.example.demo.display.GameOverImage;
import com.example.demo.display.HeartDisplay;
import com.example.demo.display.PauseMenuImage;
import com.example.demo.display.WinImage;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.scene.text.Text;
import javafx.scene.text.Font;

public class LevelView {
	
	private static final double HEART_DISPLAY_X_POSITION = 5;
	private static final double HEART_DISPLAY_Y_POSITION = 25;
	private static final int IMAGE_X_POSITION = 335;
	private static final int IMAGE_Y_POSITION = 95;
	private static final double SCREEN_WIDTH = Screen.getPrimary().getBounds().getWidth();
	private static final double SCREEN_HEIGHT = Screen.getPrimary().getBounds().getHeight();

	private final Group topLayer;

    private final WinImage winImage;
	private final GameOverImage gameOverImage;
	private final HeartDisplay heartDisplay;
	private final PauseMenuImage pauseMenuImage;
	private final Rectangle darkOverlay;
	
	public LevelView(Group root, int heartsToDisplay) {
		this.topLayer = new Group();
        Group midLayer = new Group();
        Group bottomLayer = new Group();

		root.getChildren().addAll(bottomLayer, midLayer, topLayer);

		this.heartDisplay = new HeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);
		this.winImage = new WinImage(IMAGE_X_POSITION, IMAGE_Y_POSITION+35.00);
		this.gameOverImage = new GameOverImage(IMAGE_X_POSITION, IMAGE_Y_POSITION);
		this.pauseMenuImage = new PauseMenuImage(IMAGE_X_POSITION, IMAGE_Y_POSITION);
		this.darkOverlay = new Rectangle(SCREEN_WIDTH, SCREEN_HEIGHT, Color.rgb(0, 0, 0, 0.6));

		topLayer.getChildren().add(darkOverlay);
		topLayer.getChildren().add(pauseMenuImage);
		darkOverlay.setVisible(false);

        Text controlInformation = new Text("[ESC] to pause\t[R]to restart to Start\t\t[ENTER]to go back to main menu");
		controlInformation.setFont(new Font("Arial", 18));
		controlInformation.setFill(Color.BLACK);
		controlInformation.setX(600);
		controlInformation.setY(700);

		// Add controlInformation to topLayer for visibility
		topLayer.getChildren().add(controlInformation);

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
		darkOverlay.setVisible(true);
		pauseMenuImage.showPauseMenu();
	}

	public  void hidePauseMenuImage() {
		darkOverlay.setVisible(false);
		pauseMenuImage.hidePauseMenu();
	}

}
