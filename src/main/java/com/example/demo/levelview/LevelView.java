package com.example.demo.levelview;

import com.example.demo.display.GameOverImage;
import com.example.demo.display.HeartDisplay;
import com.example.demo.display.PauseMenuImage;
import com.example.demo.display.WinImage;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;

/**
 * The `LevelView` class provides a visual representation of a game level.
 * It manages and displays UI elements such as hearts, win and game-over images,
 * pause menus, and overlays.
 */
public class LevelView {

	// Constants for positioning and sizing
	private static final double HEART_DISPLAY_X_POSITION = 5;
	private static final double HEART_DISPLAY_Y_POSITION = 25;
	private static final int GAME_OVER_IMAGE_X_POSITION = 335;
	private static final int GAME_OVER_IMAGE_Y_POSITION = 95;
	private static final int WIN_GAME_IMAGE_X_POSITION = 335;
	private static final int WIN_GAME_IMAGE_Y_POSITION = 95;
	private static final int PAUSED_IMAGE_X_POSITION = 355;
	private static final int PAUSED_IMAGE_Y_POSITION = 185;
	private static final double SCREEN_WIDTH = Screen.getPrimary().getBounds().getWidth();
	private static final double SCREEN_HEIGHT = Screen.getPrimary().getBounds().getHeight();

	private final Group topLayer;

	private final WinImage winImage;
	private final GameOverImage gameOverImage;
	private final HeartDisplay heartDisplay;
	private final PauseMenuImage pauseMenuImage;
	private final Rectangle darkOverlay;

	/**
	 * The Y-coordinate for displaying text.
	 */
	private static final int TEXT_Y_POSITION=700;
	/**
	 * The X-coordinate for displaying text.
	 */
	private static final int TEXT_X_POSITION = 380;


	/**
	 * Constructs a `LevelView` instance.
	 *
	 * @param root             The root group to hold all layers of the level view.
	 * @param heartsToDisplay  The number of hearts to display in the heart UI.
	 */
	public LevelView(Group root, int heartsToDisplay) {
		this.topLayer = new Group();
		Group midLayer = new Group();
		Group bottomLayer = new Group();

		// Add layers to the root
		root.getChildren().addAll(bottomLayer, midLayer, topLayer);

		// Initialize UI elements
		this.heartDisplay = new HeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);
		this.winImage = new WinImage(WIN_GAME_IMAGE_X_POSITION, WIN_GAME_IMAGE_Y_POSITION);
		this.gameOverImage = new GameOverImage(GAME_OVER_IMAGE_X_POSITION, GAME_OVER_IMAGE_Y_POSITION);
		this.pauseMenuImage = new PauseMenuImage(PAUSED_IMAGE_X_POSITION, PAUSED_IMAGE_Y_POSITION);
		this.darkOverlay = new Rectangle(SCREEN_WIDTH, SCREEN_HEIGHT, Color.rgb(0, 0, 0, 0.6));

		// Add pause menu overlay
		topLayer.getChildren().add(darkOverlay);
		topLayer.getChildren().add(pauseMenuImage);
		darkOverlay.setVisible(false);

		// Add control information
		Font pixelFont = Font.loadFont(getClass().getResourceAsStream("/com/example/demo/font/PressStart2P-Regular.ttf"), 14);
		Text controlInformation = new Text("[ESC] to pause\t[R] to restart\t[ENTER] to go back to main menu");
		controlInformation.setFont(pixelFont);
		controlInformation.setFill(Color.WHITE);
		controlInformation.setX(TEXT_X_POSITION);
		controlInformation.setY(TEXT_Y_POSITION);
		// Add control information to the top layer
		if (!topLayer.getChildren().contains(controlInformation)) {
			topLayer.getChildren().add(controlInformation);
		}
	}

	/**
	 * Displays the heart UI on the screen.
	 */
	public void showHeartDisplay() {
		topLayer.getChildren().add(heartDisplay.getContainer());
	}

	/**
	 * Displays the win image on the screen.
	 */
	public void showWinImage() {
		darkOverlay.setVisible(true);
		topLayer.getChildren().add(winImage);
	}

	/**
	 * Displays the game-over image on the screen.
	 */
	public void showGameOverImage() {
		darkOverlay.setVisible(true);
		topLayer.getChildren().add(gameOverImage);
	}

	/**
	 * Removes hearts from the heart display, reflecting the current health.
	 *
	 * @param heartsRemaining The number of hearts to remain displayed.
	 */
	public void removeHearts(int heartsRemaining) {
		int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
		for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
			heartDisplay.removeHeart();
		}
	}

	/**
	 * Displays the pause menu and activates the dark overlay.
	 */
	public void showPauseMenuImage() {
		darkOverlay.setVisible(true);
		pauseMenuImage.showPauseMenu();
	}

	/**
	 * Hides the pause menu and deactivates the dark overlay.
	 */
	public void hidePauseMenuImage() {
		darkOverlay.setVisible(false);
		pauseMenuImage.hidePauseMenu();
	}
}
