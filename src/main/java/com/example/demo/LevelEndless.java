package com.example.demo;

import com.example.demo.actor.ActiveActorDestructible;
import com.example.demo.levelparent.LevelParentEndless;
import com.example.demo.levelview.LevelViewEndless;
import com.example.demo.Object.EnemyPlane;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
public class LevelEndless extends LevelParentEndless {

    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background1.jpg";
    private static final int TOTAL_ENEMIES = 8;
    private static final double ENEMY_SPAWN_PROBABILITY = .05;
    private static final int PLAYER_INITIAL_HEALTH = 5;
    private static final int SCORE_Y_POSITION=20;
    private static final int SCORE_X_POSITION = 1100;
    private final static String KillCountText="Plane Destroyed: ";
    private final Label killCount = new Label(KillCountText);


    public LevelEndless(double screenHeight, double screenWidth) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);

        killCount.setFont(new Font("Arial", 18));
        killCount.setLayoutX(SCORE_X_POSITION);
        killCount.setLayoutY(SCORE_Y_POSITION);

        getTopLayer().getChildren().add(killCount);
    }

    @Override
    protected void updateText(){
        killCount.setText(KillCountText + getUser().getNumberOfKills());
    }

    @Override
    protected void checkIfGameOver() {
        if (userIsDestroyed()) {
            loseGame();
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
    protected LevelViewEndless instantiateLevelView() {
        return new LevelViewEndless(getTopLayer(), PLAYER_INITIAL_HEALTH);
    }
}
