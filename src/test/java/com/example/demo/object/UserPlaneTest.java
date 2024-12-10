package com.example.demo.object;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserPlaneTest {
    private UserPlane user;

    @BeforeAll
    public static void setUpBeforeClass() {
        if (!Platform.isFxApplicationThread()) {
            Application.launch(JavaFXTestApp.class);
        }
    }

    @BeforeEach
    public void setUp() {
        user = new UserPlane(5); // Initialize with health of 5
    }

    @Test
    void testTakeDamage() {
        int initialHealth = user.getHealth();
        user.takeDamage();
        System.out.println("\n**User Take Damage Test**");
        assertEquals(initialHealth-1, user.getHealth(), "User  plane should have health reduced by 1 after taking damage");
        System.out.println("Plane health reduce by 1 after taking damage ");
    }

    @Test
    public void testInvincibilityFrame() {
        int initialHealth = user.getHealth();
        user.takeDamage();
        user.takeDamage();

        System.out.println("\n**Invincibility Frame Test**");
        assertEquals(initialHealth - 1, user.getHealth(), "Boss health should only decrease Once");
        System.out.println("Boss health only decrease Once");
    }

    @Test
    void testUpdateInvincibilityFrame() {
        user.takeDamage();
        for (int i = 0; i < UserPlane.MAX_INVINCIBILITY_FRAME; i++) {
            user.updateInvincibilityFrame(); // Update invincibility frames
        }
        System.out.println("\n**Update Invincibility Frame Test**");
        assertFalse(user.hasInvincibility(), "User  plane should not be invincible after invincibility frames expire");
        System.out.println("Invincibility frame expire");
    }


    @Test
    void testIncrementKillCount() {
        user.incrementKillCount();
        System.out.println("\n**Increment Kill Count Test**");
        assertEquals(1, user.getNumberOfKills(), "Kill count should increment by 1");
        System.out.println("Kill count increment by 1");
    }

    @Test
    void testDecrementKillCount() {
        user.incrementKillCount();
        user.decrementKillCount();
        System.out.println("\n**Decrement Kill Count Test**");
        assertEquals(0, user.getNumberOfKills(), "Kill count should decrement by 1");
        System.out.println("Kill count increment by 1 and decrement by 1");
    }

    public static class JavaFXTestApp extends Application {
        @Override
        public void start(Stage stage) {
            stage.setTitle("Test JavaFX");
            stage.show();
        }
    }
}