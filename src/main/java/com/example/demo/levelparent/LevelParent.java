package com.example.demo.levelparent;

import java.io.IOException;
import java.util.*;

import com.example.demo.actor.ActiveActorDestructible;
import com.example.demo.levelview.LevelView;
import com.example.demo.display.MainMenu;
import com.example.demo.object.FighterPlane;
import com.example.demo.object.UserPlane;
import com.example.demo.controller.ArcadeController;
import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class LevelParent {

	/**
	 * A support object for property change listeners.
	 */
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	/**
	 * The adjustment factor for the screen height.
	 * Used to modify the screen height for certain view calculations.
	 */
	private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;

	/**
	 * The delay in milliseconds between game updates.
	 */
	private static final int MILLISECOND_DELAY = 50;

	/**
	 * The height of the game screen.
	 */
	private final double screenHeight;

	/**
	 * The width of the game screen.
	 */
	private final double screenWidth;

	/**
	 * The maximum Y position for enemy units in the game.
	 * Represents the highest vertical point the enemies can occupy.
	 */
	private final double enemyMaximumYPosition;

	/**
	 * The group containing elements displayed in the top visual layer.
	 * Used for organizing scene elements based on their visual priority.
	 */
	private final Group topLayer;

	/**
	 * The group containing elements displayed in the middle visual layer.
	 * Used for organizing scene elements based on their visual priority.
	 */
	private final Group midLayer;

	/**
	 * The group containing elements displayed in the bottom visual layer.
	 * Used for organizing scene elements based on their visual priority.
	 */
	private final Group bottomLayer;

	/**
	 * The timeline used to control the game’s updates and animations.
	 * Responsible for periodic actions such as moving actors, handling collisions, etc.
	 */
	private final Timeline timeline;

	/**
	 * The user-controlled plane (the player character) in the game.
	 */
	private final UserPlane user;

	/**
	 * The scene representing the entire game view.
	 * Contains all visual elements of the game.
	 */
	private final Scene scene;

	/**
	 * The background image of the game.
	 * Displayed as the backdrop for the game elements.
	 */
	private final ImageView background;

	/**
	 * The level view for the current game level.
	 * Handles displaying level-specific elements, such as health, score, etc.
	 */
	private final LevelView levelView;

	/**
	 * The list of friendly units (e.g., user projectiles, friendly aircraft) in the game.
	 */
	public final List<ActiveActorDestructible> friendlyUnits;

	/**
	 * The list of enemy units (e.g., enemy aircraft, enemy projectiles) in the game.
	 */
	private final List<ActiveActorDestructible> enemyUnits;

	/**
	 * The list of projectiles fired by the user.
	 * Represents the user’s projectiles during gameplay.
	 */
	private final List<ActiveActorDestructible> userProjectiles;

	/**
	 * The list of projectiles fired by enemies.
	 * Represents the enemy's projectiles during gameplay.
	 */
	private final List<ActiveActorDestructible> enemyProjectiles;

	/**
	 * The current number of enemy units in the game.
	 */
	private int currentNumberOfEnemies;

	/**
	 * A flag indicating whether the spacebar (for shooting projectiles) is enabled.
	 */
	private boolean isSPaceEnabled = true;

	/**
	 * A flag indicating whether the game is currently paused.
	 */
	private boolean isPause = false;

	/**
	 * A flag indicating whether the ESC key (for pausing the game) is enabled.
	 */
	private boolean isESCEnabled = true;

	/**
	 * Logger for logging messages related to the game’s operation and state changes.
	 */
	private static final Logger logger = Logger.getLogger(LevelParent.class.getName());

	/**
	 * The offset value added to the screen width to determine when an enemy has moved
	 * far enough off-screen to be considered as having penetrated the user's defenses.
	 */
	private static final int ENEMY_DESPAWN_OFFSET=280;


	/**
	 * Constructs a new level with the given parameters.
	 *
	 * @param backgroundImageName the image name for the level's background.
	 * @param screenHeight the height of the screen.
	 * @param screenWidth the width of the screen.
	 * @param playerInitialHealth the initial health of the player.
	 */
	protected LevelParent(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth) {

        Group root = new Group();
		this.topLayer = new Group();
		this.midLayer = new Group();
		this.timeline = new Timeline();
		this.user = new UserPlane(playerInitialHealth);
		this.friendlyUnits = new ArrayList<>();
		this.bottomLayer = new Group();

		root.getChildren().addAll(bottomLayer, midLayer, topLayer);

		this.scene = new Scene(root, screenWidth, screenHeight);
		this.enemyUnits = new ArrayList<>();
		this.userProjectiles = new ArrayList<>();
		this.enemyProjectiles = new ArrayList<>();

		this.background = new ImageView(new Image(Objects.requireNonNull(getClass().getResource(backgroundImageName)).toExternalForm()));
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		this.enemyMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;
		this.levelView = instantiateLevelView();
		this.currentNumberOfEnemies = 0;
		initializeTimeline();
		friendlyUnits.add(user);
	}

	/**
	 * Initializes the friendly units in the level.
	 * This method must be implemented by subclasses.
	 */
	protected abstract void initializeFriendlyUnits();
	/**
	 * Checks whether the game is over.
	 * This method must be implemented by subclasses.
	 */
	protected abstract void checkIfGameOver();
	/**
	 * Spawns the enemy units in the level.
	 * This method must be implemented by subclasses.
	 */
	protected abstract void spawnEnemyUnits();
	/**
	 * Updates the text (such as scores, health, etc.) displayed in the level.
	 * This method must be implemented by subclasses.
	 */
	protected abstract void updateText();
	/**
	 * Instantiates and returns the LevelView object to display level-specific UI elements.
	 * This method must be implemented by subclasses.
	 *
	 * @return the LevelView object.
	 */
	protected abstract LevelView instantiateLevelView();
	/**
	 * Starts the game by playing the timeline animation.
	 */
	public void startGame() {
		background.requestFocus();
		timeline.play();
	}
	/**
	 * Initializes the background, including key event listeners.
	 */
	private void initializeBackground() {
		background.setFocusTraversable(true);
		background.setFitHeight(screenHeight);
		background.setFitWidth(screenWidth);
		background.setOnKeyPressed(e -> {
			KeyCode kc = e.getCode();
			if (kc == KeyCode.UP) user.moveUp();
			if (kc == KeyCode.DOWN) user.moveDown();
			if (kc == KeyCode.SPACE && isSPaceEnabled) fireProjectile();
			if (kc == KeyCode.ESCAPE && isESCEnabled) pauseLevel();
			if (kc == KeyCode.ENTER) {
				goToMainMenu((Stage) scene.getWindow());
			}
		});
		background.setOnKeyReleased(e -> {
			KeyCode kc = e.getCode();
			if (kc == KeyCode.UP || kc == KeyCode.DOWN) user.stop();
		});
		bottomLayer.getChildren().add(background);
	}
	/**
	 * Initializes the scene by setting up the background and friendly units.
	 *
	 * @return the Scene object.
	 */
	public Scene initializeScene() {
		initializeBackground();
		initializeFriendlyUnits();
		levelView.showHeartDisplay();
		return scene;
	}
	/**
	 * Initializes the game loop with a timeline.
	 */
	private void initializeTimeline() {
		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateScene());
		timeline.getKeyFrames().add(gameLoop);
	}
	/**
	 * Removes assets from the scene and clears the respective lists.
	 *
	 * @param actors the list of actors to remove.
	 */
	private void removeAssetsFromScene(List<ActiveActorDestructible> actors) {
		if (actors != null) {
			midLayer.getChildren().removeAll(actors);

			// Clear the list of actors
			actors.clear();
		}
	}
	/**
	 * Clears all assets, stops the timeline, and clears the scene layers.
	 */
	public void clearAsset() {
		timeline.stop();
		timeline.getKeyFrames().clear();

		removeAssetsFromScene(friendlyUnits);
		removeAssetsFromScene(enemyUnits);
		removeAssetsFromScene(userProjectiles);
		removeAssetsFromScene(enemyProjectiles);

		friendlyUnits.clear();
		enemyUnits.clear();
		userProjectiles.clear();
		enemyProjectiles.clear();

		// Clear all layers
		midLayer.getChildren().clear();
		bottomLayer.getChildren().clear();
		topLayer.getChildren().clear();
	}
	/**
	 * Adds a property change listener to this class.
	 *
	 * @param listener the listener to add.
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	/**
	 * Advances to the next level by clearing assets and notifying listeners.
	 *
	 * @param levelName the name of the next level.
	 */
	public void goToNextLevel(String levelName) {
		clearAsset();
		pcs.firePropertyChange("level", null, levelName);
	}

	/**
	 * Updates the scene by spawning enemies, updating actors, handling collisions, etc.
	 */
	private void updateScene() {
		spawnEnemyUnits();
		updateActors();
		generateEnemyFire();
		updateNumberOfEnemies();
		handleEnemyPenetration();
		handleUserProjectileCollisions();
		handleEnemyProjectileCollisions();
		handlePlaneCollisions();
		removeAllDestroyedActors();
		updateKillCount();
		updateText();
		updateLevelView();
		checkIfGameOver();

	}
	/**
	 * Fires a projectile from the user's plane and adds it to the scene.
	 */
	private void fireProjectile() {
		ActiveActorDestructible projectile = user.fireProjectile();
		midLayer.getChildren().add(projectile);
		userProjectiles.add(projectile);
	}
	/**
	 * Generates enemy fire by looping through enemy units.
	 */
	private void generateEnemyFire() {
		enemyUnits.forEach(enemy -> spawnEnemyProjectile(((FighterPlane) enemy).fireProjectile()));
	}
	/**
	 * Spawns an enemy projectile and adds it to the scene.
	 *
	 * @param projectile the projectile to spawn.
	 */
	private void spawnEnemyProjectile(ActiveActorDestructible projectile) {
		if (projectile != null) {
			midLayer.getChildren().add(projectile);
			enemyProjectiles.add(projectile);
			projectile.toBack();
		}
	}
	/**
	 * Updates all actors in the game.
	 */
	private void updateActors() {
		friendlyUnits.forEach(ActiveActorDestructible::updateActor);
		enemyUnits.forEach(ActiveActorDestructible::updateActor);
		userProjectiles.forEach(ActiveActorDestructible::updateActor);
		enemyProjectiles.forEach(ActiveActorDestructible::updateActor);
	}
	/**
	 * Removes all destroyed actors from the scene.
	 */

	private void removeAllDestroyedActors() {
		removeDestroyedActors(friendlyUnits);
		removeDestroyedActors(enemyUnits);
		removeDestroyedActors(userProjectiles);
		removeDestroyedActors(enemyProjectiles);
	}
	/**
	 * Removes destroyed actors from the given list.
	 *
	 * @param actors the list of actors to check.
	 */
	private void removeDestroyedActors(List<ActiveActorDestructible> actors) {
		List<ActiveActorDestructible> destroyedActors = actors.stream().filter(ActiveActorDestructible::isDestroyed)
				.toList();
		midLayer.getChildren().removeAll(destroyedActors);
		actors.removeAll(destroyedActors);
	}

	/**
	 * Updates the kill count.
	 */
	private void handleCollisions(List<ActiveActorDestructible> actors1,
								  List<ActiveActorDestructible> actors2) {
		for (ActiveActorDestructible actor : actors2) {
			for (ActiveActorDestructible otherActor : actors1) {
				if (actor.getBoundsInParent().intersects(otherActor.getBoundsInParent())) {
					actor.takeDamage();
					otherActor.takeDamage();
				}
			}
		}
	}
	/**
	 * Handles collisions between user plane and enemy units.
	 */
	private void handlePlaneCollisions() {
		handleCollisions(friendlyUnits, enemyUnits);
	}
	/**
	 * Handles collisions between user projectiles and enemy units.
	 */
	private void handleUserProjectileCollisions() {
		handleCollisions(userProjectiles, enemyUnits);
	}
	/**
	 * Handles collisions between enemy projectiles and user plane.
	 */
	private void handleEnemyProjectileCollisions() {
		handleCollisions(enemyProjectiles, friendlyUnits);
	}
	/**
	 * Handles enemy penetration (e.g., when enemy projectiles or units go off-screen).
	 */
	private void handleEnemyPenetration() {
		for (ActiveActorDestructible enemy : enemyUnits) {
			if (enemyHasPenetratedDefenses(enemy)) {
				user.takeDamage();
				enemy.destroy();
				user.decrementKillCount();
			}
		}
	}

	/**
	 * Updates the level view by removing hearts based on the user's current health.
	 */
	private void updateLevelView() {
		levelView.removeHearts(user.getHealth());
	}

	/**
	 * Updates the kill count by incrementing the user's kill count for each destroyed enemy.
	 */
	private void updateKillCount() {
		for (int i = 0; i < currentNumberOfEnemies - enemyUnits.size(); i++) {
			user.incrementKillCount();
		}
	}

	/**
	 * Checks if an enemy has penetrated the user's defenses by moving off the screen.
	 * The enemy is considered to have penetrated if its absolute horizontal position
	 * exceeds the screen width plus the {@code ENEMY_DESPAWN_OFFSET}.
	 *
	 * @param enemy the enemy unit to check.
	 * @return {@code true} if the enemy has penetrated the defenses, {@code false} otherwise.
	 */
	private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy) {
		return Math.abs(enemy.getTranslateX()) > screenWidth+ENEMY_DESPAWN_OFFSET;
	}

	/**
	 * Handles the win condition for the game. Stops the timeline and displays the win image.
	 */
	protected void winGame() {
		isESCEnabled = false;
		isSPaceEnabled = false;
		timeline.stop();
		levelView.showWinImage();
	}

	/**
	 * Handles the loss condition for the game. Stops the timeline and displays the game over image.
	 */
	protected void loseGame() {
		isESCEnabled = false;
		isSPaceEnabled = false;
		timeline.stop();
		levelView.showGameOverImage();
	}

	/**
	 * Gets the current number of enemy units.
	 *
	 * @return the current number of enemies.
	 */
	protected int getCurrentNumberOfEnemies() {
		return enemyUnits.size();
	}

	/**
	 * Adds a new enemy unit to the game and to the mid-layer.
	 *
	 * @param enemy the enemy unit to add.
	 */
	protected void addEnemyUnit(ActiveActorDestructible enemy) {
		enemyUnits.add(enemy);
		midLayer.getChildren().add(enemy);
	}

	/**
	 * Updates the current number of enemy units by checking the size of the enemyUnits list.
	 */
	private void updateNumberOfEnemies() {
		currentNumberOfEnemies = enemyUnits.size();
	}


	/**
	 * Pauses the level by stopping the timeline and showing the pause menu.
	 */
	public void pauseLevel (){
		if (!isPause){
		isPause = true;
		isSPaceEnabled=false;
		timeline.pause();
		levelView.showPauseMenuImage();}
		else {
			isPause = false;
			isSPaceEnabled=true;
			timeline.play();
		levelView.hidePauseMenuImage();}
	}

	//reset level function
	public void resetLevel( String level){
		goToNextLevel(level);
	}
	/**
	 * Goes to the main menu when the user presses the enter key.
	 *
	 * @param stage the current stage.
	 */
	public void goToMainMenu(Stage stage) {
		try {
			clearAsset();
			ArcadeController gameArcadeController = new ArcadeController(stage);
			MainMenu.showMainMenu(stage, gameArcadeController);
		} catch (IOException e) {
		logger.log(Level.SEVERE, "Error Starting Main Menu", e);
		}
	}

	protected UserPlane getUser() {
		return user;
	}
	/**
	 * Gets the top layer of the scene.
	 *
	 * @return the top layer group.
	 */
	protected Group getTopLayer() {
		return topLayer;
	}
	/**
	 * Gets the mid layer of the scene.
	 *
	 * @return the mid layer group.
	 */
	protected Group getMidLayer() {
		return midLayer;
	}
	/**
	 * Gets the maximum Y position for enemy units in the game.
	 *
	 * @return the maximum Y position for enemy units.
	 */
	protected double getEnemyMaximumYPosition() {
		return enemyMaximumYPosition;
	}

	/**
	 * Gets the screen width of the game.
	 *
	 * @return the screen width.
	 */
	protected double getScreenWidth() {
		return screenWidth;
	}

	/**
	 * Checks if the user (player) has been destroyed.
	 *
	 * @return true if the user is destroyed, false otherwise.
	 */
	protected boolean userIsDestroyed() {
		return user.isDestroyed();
	}

	/**
	 * Gets the current scene of the game.
	 *
	 * @return the current scene.
	 */
	protected Scene getScene() {
		return scene;
	}


}
