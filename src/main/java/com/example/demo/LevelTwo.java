package com.example.demo;

import com.example.demo.levelparent.LevelParentArcade;
import com.example.demo.levelview.LevelView;
import com.example.demo.levelview.LevelViewLevelTwo;
import com.example.demo.object.Boss;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

/**
 * The `LevelTwo` class represents the second level in the arcade game.
 * It extends `LevelParentArcade` and includes logic specific to Level Two, such as handling
 * the boss enemy, displaying the boss's health, and updating the game state.
 */
public class LevelTwo extends LevelParentArcade {

	/**
	 * The file path to the background image for Level Two.
	 */
	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background2.jpg";
	/**
	 * The initial health of the player for Level Two.
	 */
	private static final int PLAYER_INITIAL_HEALTH = 5;
	/**
	 * The boss enemy for this level.
	 */
	private final Boss boss;
	/**
	 * The Y-coordinate for displaying the score (boss health).
	 */
	private static final int SCORE_Y_POSITION=20;
	/**
	 * The X-coordinate for displaying the score (boss health).
	 */
	private static final int SCORE_X_POSITION = 1100;
	/**
	 * The text label to show the boss's health.
	 */
	private static final String  BOSSHEALTH_TEXT="Boss HP: ";
	/**
	 * The label that displays the boss's health.
	 */
	private final Label bossHealth = new Label(BOSSHEALTH_TEXT);

	/**
	 * Constructs a `LevelTwo` instance with the specified screen dimensions.
	 * It initializes the background, sets up the player and boss, and configures the health display.
	 *
	 * @param screenHeight The height of the game screen.
	 * @param screenWidth The width of the game screen.
	 */

    public LevelTwo(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
		boss = new Boss();

		Font pixelFont = Font.loadFont(getClass().getResourceAsStream("/com/example/demo/font/PressStart2P-Regular.ttf"), 16);
		bossHealth.setFont(pixelFont);
		bossHealth.setLayoutX(SCORE_X_POSITION);
		bossHealth.setLayoutY(SCORE_Y_POSITION);
		bossHealth.setTextFill(javafx.scene.paint.Color.WHITE);

		getTopLayer().getChildren().add(bossHealth);
	}
	/**
	 * Initializes the friendly units for this level, adding the player (user).
	 */
	@Override
	protected void initializeFriendlyUnits() {
		getMidLayer().getChildren().add(getUser());
	}
	/**
	 * Checks if the game is over based on the player's or boss's status.
	 * If the player is destroyed, the game is lost.
	 * If the boss is destroyed, the game is won.
	 */
	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		}
		else if (boss.isDestroyed()) {
			winGame();
		}
	}

	/**
	 * Spawns enemy units for this level. If no enemies remain, the boss is added to the game.
	 */
	@Override
	protected void spawnEnemyUnits() {
		if (getCurrentNumberOfEnemies() == 0) {
			addEnemyUnit(boss);
			getMidLayer().getChildren().add(boss.getShieldImage());
		}
	}
	/**
	 * Updates the displayed text showing the boss's health.
	 */
	@Override
	protected void updateText() {
		bossHealth.setText(BOSSHEALTH_TEXT + boss.getHealth());
	}

	/**
	 * Instantiates the level view specific to Level Two.
	 *
	 * @return A new instance of `LevelViewLevelTwo`.
	 */
	@Override
	protected LevelView instantiateLevelView() {
        return new LevelViewLevelTwo(getTopLayer(), PLAYER_INITIAL_HEALTH);
	}

}
