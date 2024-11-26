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

	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;
	private static final int MILLISECOND_DELAY = 50;
	private final double screenHeight;
	private final double screenWidth;
	private final double enemyMaximumYPosition;

    private final Group topLayer;
	private final Group midLayer;
	private final Group bottomLayer;

	private final Timeline timeline;
	private final UserPlane user;
	private final Scene scene;
	private final ImageView background;
	private final LevelView levelView;

	public final List<ActiveActorDestructible> friendlyUnits;
	private final List<ActiveActorDestructible> enemyUnits;
	private final List<ActiveActorDestructible> userProjectiles;
	private final List<ActiveActorDestructible> enemyProjectiles;

	private int currentNumberOfEnemies;
	private boolean isSPaceEnabled= true;
	private boolean isPause = false;
	private boolean isESCEnabled= true;

	private static final Logger logger = Logger.getLogger(LevelParent.class.getName());

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

	protected abstract void initializeFriendlyUnits();

	protected abstract void checkIfGameOver();

	protected abstract void spawnEnemyUnits();

	protected abstract void updateText();

	protected abstract LevelView instantiateLevelView();

	public void startGame() {
		background.requestFocus();
		timeline.play();
	}

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

	public Scene initializeScene() {
		initializeBackground();
		initializeFriendlyUnits();
		levelView.showHeartDisplay();
		return scene;
	}

	private void initializeTimeline() {
		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateScene());
		timeline.getKeyFrames().add(gameLoop);
	}

	private void removeAssetsFromScene(List<ActiveActorDestructible> actors) {
		if (actors != null) {
			midLayer.getChildren().removeAll(actors);

			// Clear the list of actors
			actors.clear();
		}
	}


	public void clearAsset() {
		timeline.stop();
		timeline.getKeyFrames().clear();  // Clear any existing keyframes

		removeAssetsFromScene(friendlyUnits);
		removeAssetsFromScene(enemyUnits);
		removeAssetsFromScene(userProjectiles);
		removeAssetsFromScene(enemyProjectiles);

		friendlyUnits.clear();
		enemyUnits.clear();
		userProjectiles.clear();
		enemyProjectiles.clear();

		System.gc();
	}
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	public void goToNextLevel(String levelName) {
		clearAsset();
		pcs.firePropertyChange("level", null, levelName);
	}

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

	private void fireProjectile() {
		ActiveActorDestructible projectile = user.fireProjectile();
		midLayer.getChildren().add(projectile);
		userProjectiles.add(projectile);
	}

	private void generateEnemyFire() {
		enemyUnits.forEach(enemy -> spawnEnemyProjectile(((FighterPlane) enemy).fireProjectile()));
	}

	private void spawnEnemyProjectile(ActiveActorDestructible projectile) {
		if (projectile != null) {
			midLayer.getChildren().add(projectile);
			enemyProjectiles.add(projectile);
			projectile.toBack();
		}
	}

	private void updateActors() {
		friendlyUnits.forEach(ActiveActorDestructible::updateActor);
		enemyUnits.forEach(ActiveActorDestructible::updateActor);
		userProjectiles.forEach(ActiveActorDestructible::updateActor);
		enemyProjectiles.forEach(ActiveActorDestructible::updateActor);
	}

	private void removeAllDestroyedActors() {
		removeDestroyedActors(friendlyUnits);
		removeDestroyedActors(enemyUnits);
		removeDestroyedActors(userProjectiles);
		removeDestroyedActors(enemyProjectiles);
	}

	private void removeDestroyedActors(List<ActiveActorDestructible> actors) {
		List<ActiveActorDestructible> destroyedActors = actors.stream().filter(ActiveActorDestructible::isDestroyed)
				.toList();
		midLayer.getChildren().removeAll(destroyedActors);
		actors.removeAll(destroyedActors);
	}

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

	private void handlePlaneCollisions() {
		handleCollisions(friendlyUnits, enemyUnits);
	}

	private void handleUserProjectileCollisions() {
		handleCollisions(userProjectiles, enemyUnits);
	}

	private void handleEnemyProjectileCollisions() {
		handleCollisions(enemyProjectiles, friendlyUnits);
	}

	private void handleEnemyPenetration() {
		for (ActiveActorDestructible enemy : enemyUnits) {
			if (enemyHasPenetratedDefenses(enemy)) {
				user.takeDamage();
				enemy.destroy();
				user.decrementKillcount();
			}
		}
	}

	private void updateLevelView() {
		levelView.removeHearts(user.getHealth());
	}

		private void updateKillCount() {
		for (int i = 0; i < currentNumberOfEnemies - enemyUnits.size(); i++) {
			user.incrementKillCount();
		}
	}

	private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy) {
		return Math.abs(enemy.getTranslateX()) > screenWidth;
	}

	protected void winGame() {
		isESCEnabled= false;
		isSPaceEnabled = false;
		timeline.stop();
		levelView.showWinImage();
	}

	protected void loseGame() {
		isESCEnabled= false;
		isSPaceEnabled=false;
		timeline.stop();
		levelView.showGameOverImage();
	}

	protected int getCurrentNumberOfEnemies() {
		return enemyUnits.size();
	}

	protected void addEnemyUnit(ActiveActorDestructible enemy) {
		enemyUnits.add(enemy);
		midLayer.getChildren().add(enemy);
	}


	private void updateNumberOfEnemies() {
		currentNumberOfEnemies = enemyUnits.size();
	}

	/*new function*/
	//pause function
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

	//go to main menu function
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
	protected Group getTopLayer() {
		return topLayer;
	}
	protected Group getMidLayer() {
		return midLayer;
	}

	protected double getEnemyMaximumYPosition() {
		return enemyMaximumYPosition;
	}

	protected double getScreenWidth() {
		return screenWidth;
	}

	protected boolean userIsDestroyed() {
		return user.isDestroyed();
	}

	protected Scene getScene(){
		return scene;
	}



}
