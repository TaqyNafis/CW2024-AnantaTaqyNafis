package com.example.demo.object;

import com.example.demo.actor.ActiveActorDestructible;
import com.example.demo.projectile.UserProjectile;

/**
 * A class representing the user plane in the game, extending the  {@link FighterPlane}.
 * It includes functionality for movement, firing projectiles, taking damage, and managing kills.
 */
public class UserPlane extends FighterPlane {
	/**
	 * Base location of userplane image.
	 */
	private static final String IMAGE_NAME = "userplane.png";
	/**
	 * The maximum Y position of user plane.
	 */
	private static final double Y_UPPER_BOUND = -10;
	/**
	 * The minimum Y position of user plane.
	 */
	private static final double Y_LOWER_BOUND = 675.0;
	/**
	 * The starting X position of user plane.
	 */
	private static final double INITIAL_X_POSITION = 5.0;
	/**
	 * The starting Y position of user plane.
	 */
	private static final double INITIAL_Y_POSITION = 300.0;
	/**
	 * The size of user plane.
	 */
	private static final int IMAGE_HEIGHT = 40;
	/**
	 * The distance to move vertically.
	 */
	private static final int VERTICAL_VELOCITY = 8;
	/**
	 * The starting X position of the user plane's projectile.
	 * This represents the horizontal position from the left edge of the user plane
	 * where the projectile will appear when fired.
	 */
	private static final int PROJECTILE_X_POSITION = 110;

	/**
	 * The Y-axis offset for the user plane's projectile position.
	 * This value determines how far vertically (up or down) the projectile
	 * is positioned relative to the user plane's center when fired.
	 */
	private static final int PROJECTILE_Y_POSITION_OFFSET = 20;
	/**
	 * A multiplier affecting the speed of the user Planes
	 * Higher values increase the speed of the plane's movement.
	 */
	private int velocityMultiplier;
	/**
	 * Counter for how many enemies plane have been destroyed.
	 */
	private int numberOfKills;
	/**
	 * The current number of invincibility frames remaining for the user plane.
	 * During this period, the user plane is immune to damage.
	 */
	private int invincibilityFrame;

	/**
	 * The maximum number of invincibility frames the user plane can have.
	 * This defines the duration (in frames) of the invincibility period.
	 */
	private static final int MAX_INVINCIBILITY_FRAME = 20;

	/**
	 * A flag indicating whether the user plane is currently in the invincibility state.
	 * If {@code true}, the user plane cannot take damage.
	 */
	private boolean hasInvincibility = false;

	/**
	 * Constructs a new {@code UserPlane} instance.
	 * @param initialHealth the starting health of the user plane
	 */
	public UserPlane(int initialHealth) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
		velocityMultiplier = 0;
	}

	/**
	 * Updates the position of the user plane.
	 * <p>
	 * 		If the user plane is moving, it calculates a new vertical position based on its current speed.
	 * 		If the new position exceeds the predefined boundaries (upper or lower), the position is reset.
	 * </p>
	 */
	@Override
	public void updatePosition() {
		if (isMoving()) {
			double initialTranslateY = getTranslateY();
			this.moveVertically(VERTICAL_VELOCITY * (double) velocityMultiplier);
			double newPosition = getLayoutY() + getTranslateY();
			if (newPosition < Y_UPPER_BOUND || newPosition > Y_LOWER_BOUND) {
				this.setTranslateY(initialTranslateY);
			}
		}
	}
	/**
	 * Handles the process of taking damage for the user plane.
	 * <p>
	 * 		 If the plane is not currently invincible, it takes damage and becomes invincible for a short period.
	 * </p>
	 */
	@Override
	public void takeDamage() {
		if (!hasInvincibility) {
			super.takeDamage();
			hasInvincibility = true;
		}
	}
	/**
	 * Updates the invincibility frame for the user plane.
	 * <p>
	 *     Toggles the visibility of the user plane every 5 frames while invincible,
	 *     and resets the invincibility state once the maximum duration is reached.
	 * </p>
	 */
	public void updateInvincibilityFrame() {
		if (hasInvincibility) {
			invincibilityFrame++;
		}
		super.setVisible(invincibilityFrame % 5 == 0);

		if (invincibilityFrame == MAX_INVINCIBILITY_FRAME) {
			hasInvincibility = false;
			invincibilityFrame = 0;
		}
	}
	/**
	 * Updates the state of the user plane, including position and invincibility frame.
	 */
	@Override
	public void updateActor() {
		updatePosition();
		updateInvincibilityFrame();
	}
	/**
	 * Fires a projectile from the user plane.
	 * <p>
	 *     Creates a new {@link UserProjectile} instance at the correct position based on the user's plane.
	 * </p>
	 *
	 * @return a new {@link UserProjectile} representing the fired projectile.
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		return new UserProjectile(PROJECTILE_X_POSITION, getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET));
	}
	/**
	 * Checks if the user plane is currently moving.
	 *
	 * @return {@code true} if the user plane is moving, otherwise {@code false}.
	 */
	private boolean isMoving() {
		return velocityMultiplier != 0;
	}
	/**
	 * Moves the user plane upward by setting the velocity multiplier to -1.
	 */
	public void moveUp() {
		velocityMultiplier = -1;
	}
	/**
	 * Moves the user plane downward by setting the velocity multiplier to 1.
	 */
	public void moveDown() {
		velocityMultiplier = 1;
	}
	/**
	 * Stops the user plane from moving by setting the velocity multiplier to 0.
	 */
	public void stop() {
		velocityMultiplier = 0;
	}

	/**
	 * Returns the current number of kills the user plane has.
	 *
	 * @return the number of kills.
	 */
	public int getNumberOfKills() {
		return numberOfKills;
	}

	/**
	 * Increases the kill count of the user plane by 1.
	 */
	public void incrementKillCount() {
		numberOfKills++;
	}

	/**
	 * Decreases the kill count of the user plane by 1.
	 */
	public void decrementKillCount(){
		numberOfKills--;
	}

}
