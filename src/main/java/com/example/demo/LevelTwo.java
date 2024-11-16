package com.example.demo;

import com.example.demo.LevelParent.LevelParentArcade;
import com.example.demo.LevelView.LevelView;
import com.example.demo.LevelView.LevelViewLevelTwo;
import com.example.demo.Object.Boss;

public class LevelTwo extends LevelParentArcade {

	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background2.jpg";
	private static final int PLAYER_INITIAL_HEALTH = 5;
	private final Boss boss;


    public LevelTwo(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
		boss = new Boss();
	}

	@Override
	protected void initializeFriendlyUnits() {
		getMidLayer().getChildren().add(getUser());
	}

	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		}
		else if (boss.isDestroyed()) {
			winGame();
		}
	}

	@Override
	protected void spawnEnemyUnits() {
		if (getCurrentNumberOfEnemies() == 0) {
			addEnemyUnit(boss);
			getMidLayer().getChildren().add(boss.getShieldImage());
		}
	}

	@Override
	protected LevelView instantiateLevelView() {
        return new LevelViewLevelTwo(getTopLayer(), PLAYER_INITIAL_HEALTH);
	}

}
