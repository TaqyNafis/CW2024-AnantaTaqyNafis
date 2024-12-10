package com.example.demo.object;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnemyPlaneTest {

    private EnemyPlane enemy;

    @BeforeAll
    public static void setUpBeforeClass() {
        if (!Platform.isFxApplicationThread()) {
            Application.launch(JavaFXTestApp.class);
        }
    }

    @BeforeEach
    public void setUp() {
        enemy = new EnemyPlane(100.0, 200.0);
    }

    @Test
    void testUpdatePosition() {
        double initialX = enemy.getTranslateX();
        enemy.updatePosition();
        System.out.println("\n**Enemy Plane Update Position Test**");
        assertEquals(initialX + EnemyPlane.HORIZONTAL_VELOCITY, enemy.getTranslateX(), "Enemy plane should move horizontally");
        System.out.println("Enemy plane updated its position correctly");
    }



    @Test
    void testFireProjectile() {
        EnemyPlane enemy = new EnemyPlane(100.0, 200.0) {
            @Override
            protected double getRandomDouble() {
                return 0.005;
            }
        };
        System.out.println("\n**Enemy Plane Successful fire test**");
        assertNotNull(enemy.fireProjectile(), "Enemy plane should fire a projectile");
        System.out.println("Enemy plane fired a projectile with controlled randomness");
    }

    @Test
    void testFireProjectileDoesNotFire() {
        EnemyPlane enemy = new EnemyPlane(100.0, 200.0) {
            @Override
            protected double getRandomDouble() {
                return 0.1;
            }
        };

        System.out.println("\n**Enemy Plane Unsuccessful fire test**");
        assertNull(enemy.fireProjectile(), "Enemy plane should not fire a projectile");
        System.out.println("Enemy plane did not fire a projectile with controlled randomness");
    }

    @Test
    void testUpdateActor() {
        enemy.updateActor();

        System.out.println("\n**Enemy Update actor test**");
        assertNotEquals(100.0, enemy.getTranslateX(), "Enemy plane should have updated its position");
        System.out.println("Enemy plane updated its actor state correctly");
    }

    public static class JavaFXTestApp extends Application {
        @Override
        public void start(Stage stage) {
            stage.setTitle("Test JavaFX");
            stage.show();
        }
    }
}