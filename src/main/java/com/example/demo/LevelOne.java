package com.example.demo;

import com.example.demo.actor.ActiveActorDestructible;
import com.example.demo.levelparent.LevelParentArcade;
import com.example.demo.levelview.LevelView;
import com.example.demo.object.EnemyPlane;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Random;

public class LevelOne extends LevelParentArcade {

	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background1.jpg";
	private static final String NEXT_LEVEL = "com.example.demo.LevelTwo";
	private static final int TOTAL_ENEMIES_ON_SCREEN = 5;
	private static final int KILLS_TO_ADVANCE = 10;
	private static final double ENEMY_SPAWN_PROBABILITY = .002;
	private static final int PLAYER_INITIAL_HEALTH = 5;
	private int enemiesSpawned= 0;

	private static final int SCORE_Y_POSITION=20;
	private static final int SCORE_X_POSITION = 1100;
	private static final String KILLCOUNT_TEXT="Planes Left: ";
	private final Label killCount = new Label(KILLCOUNT_TEXT);

	private static final Random randomForPosition = new Random();
	private static final ThreadLocalRandom randomForSpawn = ThreadLocalRandom.current();


    public LevelOne(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);

		killCount.setFont(new Font("Arial", 18));
		killCount.setLayoutX(SCORE_X_POSITION);
		killCount.setLayoutY(SCORE_Y_POSITION);

		getTopLayer().getChildren().add(killCount);

	}


	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		}
		else if (userHasReachedKillTarget()) {
			goToNextLevel(NEXT_LEVEL);
		}
	}

	@Override
	protected void initializeFriendlyUnits() {
		getMidLayer().getChildren().add(getUser());
	}

	@Override
	protected void spawnEnemyUnits() {
		int currentNumberOfEnemies = getCurrentNumberOfEnemies();
		while (currentNumberOfEnemies< TOTAL_ENEMIES_ON_SCREEN && enemiesSpawned < KILLS_TO_ADVANCE) {
			if (randomForSpawn.nextDouble() < ENEMY_SPAWN_PROBABILITY) {
				double newEnemyInitialYPosition = randomForPosition.nextDouble() * getEnemyMaximumYPosition();
				ActiveActorDestructible newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition);
				addEnemyUnit(newEnemy);

				enemiesSpawned++;
				currentNumberOfEnemies++;
			}
		}
	}


	@Override
	protected void updateText() {
		int x= getUser().getNumberOfKills();
		int sum= KILLS_TO_ADVANCE - x;
		killCount.setText(KILLCOUNT_TEXT + sum);
	}

	@Override
	protected LevelView instantiateLevelView() {
        return new LevelView(getTopLayer(), PLAYER_INITIAL_HEALTH);
	}



	private boolean userHasReachedKillTarget() {
		return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
	}
}
