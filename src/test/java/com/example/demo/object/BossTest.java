package com.example.demo.object;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BossTest {

    private Boss boss;

    @BeforeAll
    public static void setUpBeforeClass() {
        if (!Platform.isFxApplicationThread()) {
            Application.launch(JavaFXTestApp.class);
        }
    }

    @BeforeEach
    public void setUp() {
        boss = new Boss();
    }

    @Test
    public void testBossFiresInCurrentFrame_Fires() {
        System.out.println("Running test: testBossFiresInCurrentFrame_Fires");
        Boss boss = new Boss() {
            @Override
            public double getRandomDouble() {
                return 0.03;
            }
        };

        System.out.println("\n**Boss successful firing test**");
        assertTrue(boss.bossFiresInCurrentFrame(), "Boss should fire a projectile");
        System.out.println("Boss fires in current frame");
    }
    @Test
    public void testBossFiresInCurrentFrame_DoesNotFire() {
        Boss boss = new Boss() {
            @Override
            public double getRandomDouble() {
                return 0.7;
            }
        };

        System.out.println("\n**Boss unsuccessful firing test**");
        assertFalse(boss.bossFiresInCurrentFrame(), "Boss should not fire a projectile");
        System.out.println("BossBoss did not fires");
    }

    @Test
    public void testShieldActivation() {
        System.out.println("\n**Shield Activation Test**");
        assertFalse(boss.isShielded(), "Boss should not be shielded at the start");
        System.out.println("Boss is not in shielded state");

        boss.activateShield();
        assertTrue(boss.isShielded(), "Boss should be shielded after activation");
        System.out.println("Boss shield activated successfully");

        boss.deactivateShield();
        assertFalse(boss.isShielded(), "Boss should not be shielded after deactivation");
        System.out.println("Boss shield deactivated successfully");
    }

    @Test
    public void testTakeDamageWithoutShieldAndInvincibilityFrame() {
        int initialHealth = boss.getHealth();
        boss.takeDamage();

        System.out.println("\n**Taking Damage Test Without Shield And Invincibility Frame**");
        assertEquals(initialHealth - 1, boss.getHealth(), "Boss health should decrease after taking damage");
        System.out.println("Boss did take damage without shield");
    }

    @Test
    public void testTakeDamageWithShield() {
        boss.activateShield();
        int initialHealth = boss.getHealth();

        boss.takeDamage();
        System.out.println("\n**Taking Damage With Shield Test**");
        assertEquals(initialHealth, boss.getHealth(), "Boss should not take damage while shielded");
        System.out.println("Boss did not take damage with shield");

        boss.deactivateShield();
        boss.takeDamage();
        assertEquals(initialHealth - 1, boss.getHealth(), "Boss should take damage after shield is deactivated");
        System.out.println("Boss did take damage without shield after shield deactivated");
    }

    @Test
    public void testInvincibilityFrame() {
        int initialHealth = boss.getHealth();
        boss.takeDamage();
        boss.takeDamage();

        System.out.println("\n**Invincibility Frame Test**");
        assertEquals(initialHealth - 1, boss.getHealth(), "Boss health should only decrease Once");
        System.out.println("Boss health only decrease Once");
    }

    @Test
    public void testShieldImageVisibility() {
        boss.activateShield();
        boss.updateShield();
        System.out.println("\n**Shield Image Visibility Test**");
        assertTrue(boss.getShieldImage().isVisible(), "Shield image should be visible when shield is activated");
        System.out.println("Shield image is visible when shield is activated");
    }


    public static class JavaFXTestApp extends Application {
        @Override
        public void start(Stage stage) {
            stage.setTitle("Test JavaFX");
            stage.show();
        }
    }
}