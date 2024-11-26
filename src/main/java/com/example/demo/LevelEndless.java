package com.example.demo;

import com.example.demo.actor.ActiveActorDestructible;
import com.example.demo.levelparent.LevelParentEndless;
import com.example.demo.levelview.LevelViewEndless;
import com.example.demo.object.EnemyPlane;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import java.util.concurrent.ThreadLocalRandom;

public class LevelEndless extends LevelParentEndless {

    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background1.jpg";
    private static final int TOTAL_ENEMIES_ON_SCREEN = 8;
    private static final double ENEMY_SPAWN_PROBABILITY = .05;
    private static final int PLAYER_INITIAL_HEALTH = 5;
    private static final int SCORE_Y_POSITION=20;
    private static final int SCORE_X_POSITION = 1100;
    private static final String KILLCOUNT_TEXT="Plane Destroyed: ";
    private final Label killCount = new Label(KILLCOUNT_TEXT);
    private static final ThreadLocalRandom randomForPosition = ThreadLocalRandom.current();


    public LevelEndless(double screenHeight, double screenWidth) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);

        killCount.setFont(new Font("Arial", 18));
        killCount.setLayoutX(SCORE_X_POSITION);
        killCount.setLayoutY(SCORE_Y_POSITION);

        getTopLayer().getChildren().add(killCount);
    }

    @Override
    protected void updateText(){
        killCount.setText(KILLCOUNT_TEXT + getUser().getNumberOfKills());
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
        while (currentNumberOfEnemies< TOTAL_ENEMIES_ON_SCREEN) {
            if (ThreadLocalRandom.current().nextDouble() < ENEMY_SPAWN_PROBABILITY) {
                double newEnemyInitialYPosition = randomForPosition.nextDouble() * getEnemyMaximumYPosition();
                ActiveActorDestructible newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition);
                addEnemyUnit(newEnemy);
                currentNumberOfEnemies++;
            }
        }
    }

    @Override
    protected LevelViewEndless instantiateLevelView() {
        return new LevelViewEndless(getTopLayer(), PLAYER_INITIAL_HEALTH);
    }
}
