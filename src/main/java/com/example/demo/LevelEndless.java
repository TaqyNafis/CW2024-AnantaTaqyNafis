package com.example.demo;

import com.example.demo.actor.ActiveActorDestructible;
import com.example.demo.levelparent.LevelParentEndless;
import com.example.demo.levelview.LevelViewEndless;
import com.example.demo.object.EnemyPlane;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import java.util.concurrent.ThreadLocalRandom;
/**
 * The `LevelEndless` class represents an endless level in the arcade game.
 * It extends `LevelParentEndless` and includes logic specific to the endless mode,
 * such as continuously spawning enemies, tracking the number of kills, and handling game-over conditions.
 */
public class LevelEndless extends LevelParentEndless {
    /**
     * The file path to the background image for the endless level.
     */
    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background1.jpg";
    /**
     * The maximum number of enemies allowed on the screen at one time.
     */
    private static final int TOTAL_ENEMIES_ON_SCREEN = 6;

    /**
     * The probability of spawning a new enemy on each update.
     */
    private static final double ENEMY_SPAWN_PROBABILITY = .05;

    /**
     * The initial health of the player for the endless level.
     */
    private static final int PLAYER_INITIAL_HEALTH = 5;

    /**
     * The Y-coordinate for displaying the kill count.
     */
    private static final int SCORE_Y_POSITION=20;
    /**
     * The X-coordinate for displaying the kill count.
     */
    private static final int SCORE_X_POSITION = 920;
    /**
     * The text prefix for the kill count display.
     */
    private static final String KILLCOUNT_TEXT="Enemies Destroyed: ";
    /**
     * A label for displaying the number of enemy planes destroyed.
     */
    private final Label killCount = new Label(KILLCOUNT_TEXT);
    /**
     * A random number generator for determining enemy spawn positions.
     */
    private static final ThreadLocalRandom randomForPosition = ThreadLocalRandom.current();
    /**
     * The Minimum X offset for enemy spawning
     */
    private static final double ENEMY_SPAWN_X_OFFSET_MIN= 9;
    /**
     * The Maximum X offset for enemy spawning
     */
    private static final double ENEMY_SPAWN_X_OFFSET_MAX = 201;


    /**
     * Constructs a `LevelEndless` instance with the specified screen dimensions.
     * It initializes the background, sets up the player, and configures the kill count display.
     *
     * @param screenHeight The height of the game screen.
     * @param screenWidth  The width of the game screen.
     */
    public LevelEndless(double screenHeight, double screenWidth) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
        Font pixelFont = Font.loadFont(getClass().getResourceAsStream("/com/example/demo/font/PressStart2P-Regular.ttf"), 16);
        killCount.setFont(pixelFont);
        killCount.setLayoutX(SCORE_X_POSITION);
        killCount.setLayoutY(SCORE_Y_POSITION);
        killCount.setTextFill(javafx.scene.paint.Color.WHITE);

        getTopLayer().getChildren().add(killCount);
    }
    /**
     * Updates the displayed text showing the number of enemy planes destroyed.
     */
    @Override
    protected void updateText(){
        killCount.setText(KILLCOUNT_TEXT + getUser().getNumberOfKills());
    }
    /**
     * Checks if the game is over based on the player's health status.
     * If the player is destroyed, the game is lost.
     */
    @Override
    protected void checkIfGameOver() {
        if (userIsDestroyed()) {
            loseGame();
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
     * Spawns enemy planes for the endless level.
     * New enemies are added to the game if the current number of enemies is below the allowed maximum.
     * Each enemy is assigned a random initial position and is spawned based on a defined probability.
     *
     * <p>Key Details:</p>
     * <ul>
     *   <li>New enemies are created with a random Y-coordinate within the screen's allowed range.</li>
     *   <li>The X-coordinate is set to spawn the enemy slightly off the right edge of the screen.</li>
     *   <li>Enemies are spawned only if a random check passes based on {@code ENEMY_SPAWN_PROBABILITY}.</li>
     *   <li>The method ensures the number of on-screen enemies does not exceed {@code TOTAL_ENEMIES_ON_SCREEN}.</li>
     * </ul>
     *
     * <p>This method creates new enemies and places them on the screen at random positions
     * within the defined spawn area, provided the current number of enemies is less than the
     * defined maximum allowed on screen.</p>
     */
    @Override
    protected void spawnEnemyUnits() {
        int currentNumberOfEnemies = getCurrentNumberOfEnemies();
        while (currentNumberOfEnemies < TOTAL_ENEMIES_ON_SCREEN) {
            if (ThreadLocalRandom.current().nextDouble() < ENEMY_SPAWN_PROBABILITY) {
                double newEnemyInitialYPosition = randomForPosition.nextDouble() * getEnemyMaximumYPosition();
                double newEnemyInitialXPosition = (getScreenWidth() + randomForPosition.nextDouble(ENEMY_SPAWN_X_OFFSET_MIN, ENEMY_SPAWN_X_OFFSET_MAX));
                ActiveActorDestructible newEnemy = new EnemyPlane(newEnemyInitialXPosition, newEnemyInitialYPosition);
                addEnemyUnit(newEnemy);
                currentNumberOfEnemies++;
            }
        }
    }

    /**
     * Instantiates the level view for the endless level.
     *
     * @return A new instance of `LevelViewEndless`.
     */
    @Override
    protected LevelViewEndless instantiateLevelView() {
        return new LevelViewEndless(getTopLayer(), PLAYER_INITIAL_HEALTH);
    }
}
