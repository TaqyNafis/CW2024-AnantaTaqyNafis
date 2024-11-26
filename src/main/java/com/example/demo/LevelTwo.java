package com.example.demo;

import com.example.demo.levelparent.LevelParentArcade;
import com.example.demo.levelview.LevelView;
import com.example.demo.levelview.LevelViewLevelTwo;
import com.example.demo.Object.Boss;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class LevelTwo extends LevelParentArcade {

	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background2.jpg";
	private static final int PLAYER_INITIAL_HEALTH = 5;
	private final Boss boss;

	private static final int SCORE_Y_POSITION=20;
	private static final int SCORE_X_POSITION = 1120;
	private final static String  BossHealthText="Boss HP: ";
	private final Label BossHealth = new Label(BossHealthText);


    public LevelTwo(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
		boss = new Boss();

		BossHealth.setFont(new Font("Arial", 18));
		BossHealth.setLayoutX(SCORE_X_POSITION);
		BossHealth.setLayoutY(SCORE_Y_POSITION);

		getTopLayer().getChildren().add(BossHealth);
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
	protected void updateText() {
		BossHealth.setText(BossHealthText + boss.getHealth());
	}

	@Override
	protected LevelView instantiateLevelView() {
        return new LevelViewLevelTwo(getTopLayer(), PLAYER_INITIAL_HEALTH);
	}

}
