package com.example.demo;

import com.example.demo.Actor.ActiveActorDestructible;
import com.example.demo.LevelParent.LevelParentArcade;
import com.example.demo.LevelView.LevelView;
import com.example.demo.Object.EnemyPlane;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class LevelOne extends LevelParentArcade {

	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background1.jpg";
	private static final String NEXT_LEVEL = "com.example.demo.LevelTwo";
	private static final int TOTAL_ENEMIES = 5;
	private static final int KILLS_TO_ADVANCE = 1;
	private static final double ENEMY_SPAWN_PROBABILITY = .15;
	private static final int PLAYER_INITIAL_HEALTH = 5;

	private static final int SCORE_Y_POSITION=20;
	private static final int SCORE_X_POSITION = 1000;
	private final String KillCountText="Plane Destroyed to Advance: ";
	private final Label killCount = new Label(KillCountText);

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
		for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
			if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
				double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
				ActiveActorDestructible newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition);
				addEnemyUnit(newEnemy);
			}
		}
	}

	@Override
	protected void updateKillCountCounter() {
		int x= getUser().getNumberOfKills();
		int sum= KILLS_TO_ADVANCE - x;
		killCount.setText(KillCountText + sum);
	}

	@Override
	protected LevelView instantiateLevelView() {
		return new LevelView(getTopLayer(), PLAYER_INITIAL_HEALTH);
	}

	private boolean userHasReachedKillTarget() {
		return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
	}
}
