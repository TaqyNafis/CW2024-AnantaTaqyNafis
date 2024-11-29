package com.example.demo;

import com.example.demo.actor.ActiveActorDestructible;
import com.example.demo.levelparent.LevelParentArcade;
import com.example.demo.levelview.LevelView;
import com.example.demo.object.EnemyPlane;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import java.util.concurrent.ThreadLocalRandom;
/**
 * The `LevelOne` class represents the first level in the arcade game.
 * It extends `LevelParentArcade` and includes logic specific to Level One, such as spawning enemy planes,
 * tracking the number of kills required to advance, and handling the transition to the next level.
 */
public class LevelOne extends LevelParentArcade {
	/**
	 * The file path to the background image for Level One.
	 */
	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background1.jpg";
	/**
	 * The fully qualified name of the next level to transition to.
	 */
	private static final String NEXT_LEVEL = "com.example.demo.LevelTwo";
	/**
	 * The maximum number of enemies allowed on the screen at one time.
	 */
	private static final int TOTAL_ENEMIES_ON_SCREEN = 5;
	/**
	 * The number of kills required to advance to the next level.
	 */
	private static final int KILLS_TO_ADVANCE = 1;
	/**
	 * The probability of spawning a new enemy on each update.
	 */
	private static final double ENEMY_SPAWN_PROBABILITY = .002;
	/**
	 * The initial health of the player for Level One.
	 */
	private static final int PLAYER_INITIAL_HEALTH = 5;
	/**
	 * The number of enemies have been spawned
	 */
	private int enemiesSpawned;
	/**
	 * The Y-coordinate for displaying the kill count.
	 */
	private static final int SCORE_Y_POSITION=20;
	/**
	 * The X-coordinate for displaying the kill count.
	 */
	private static final int SCORE_X_POSITION = 1100;

	/**
	 * The text prefix for the kill count display.
	 */
	private static final String KILLCOUNT_TEXT="Planes Left: ";
	/**
	 * A label for displaying the number of enemy planes left to destroy.
	 */
	private final Label killCount = new Label(KILLCOUNT_TEXT);
	/**
	 * A random number generator for determining enemy spawn positions.
	 */
	private static final ThreadLocalRandom randomForPosition = ThreadLocalRandom.current();
	/**
	 * Constructs a `LevelOne` instance with the specified screen dimensions.
	 * It initializes the background, sets up the player, and configures the kill count display.
	 *
	 * @param screenHeight The height of the game screen.
	 * @param screenWidth  The width of the game screen.
	 */
    public LevelOne(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);

		killCount.setFont(new Font("Arial", 18));
		killCount.setLayoutX(SCORE_X_POSITION);
		killCount.setLayoutY(SCORE_Y_POSITION);

		getTopLayer().getChildren().add(killCount);

	}
	/**
	 * Checks if the game is over based on the player's or kill target's status.
	 * If the player is destroyed, the game is lost.
	 * If the player reaches the required kill target, the game advances to the next level.
	 */
	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		}
		else if (userHasReachedKillTarget()) {
			goToNextLevel(NEXT_LEVEL);
		}
	}
	/**
	 * Initializes the friendly units for this level, adding the player (user) to the mid-layer.
	 */
	@Override
	protected void initializeFriendlyUnits() {
		getMidLayer().getChildren().add(getUser());
	}
	/**
	 * Spawns enemy planes for this level.
	 * New enemies are added if the current number of enemies is below the allowed maximum,
	 * and the total number of enemies spawned has not exceeded the kill target.
	 */
	@Override
	protected void spawnEnemyUnits() {
		int currentNumberOfEnemies = getCurrentNumberOfEnemies();
		while (currentNumberOfEnemies< TOTAL_ENEMIES_ON_SCREEN && enemiesSpawned < KILLS_TO_ADVANCE) {
			if (ThreadLocalRandom.current().nextDouble() < ENEMY_SPAWN_PROBABILITY) {
				double newEnemyInitialYPosition = randomForPosition.nextDouble() * getEnemyMaximumYPosition();
				ActiveActorDestructible newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition);
				addEnemyUnit(newEnemy);

				enemiesSpawned++;
				currentNumberOfEnemies++;
			}
		}
	}
	/**
	 * Updates the displayed text showing the number of enemy planes left to destroy.
	 */
	@Override
	protected void updateText() {
		int x= getUser().getNumberOfKills();
		int sum= KILLS_TO_ADVANCE - x;
		killCount.setText(KILLCOUNT_TEXT + sum);
	}
	/**
	 * Instantiates the level view for Level One.
	 *
	 * @return A new instance of `LevelView`.
	 */
	@Override
	protected LevelView instantiateLevelView() {
        return new LevelView(getTopLayer(), PLAYER_INITIAL_HEALTH);
	}

	/**
	 * Checks if the player has reached the required number of kills to advance to the next level.
	 *
	 * @return `true` if the player's kill count is equal to or greater than the required number, otherwise `false`.
	 */
	private boolean userHasReachedKillTarget() {
		return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
	}
}
