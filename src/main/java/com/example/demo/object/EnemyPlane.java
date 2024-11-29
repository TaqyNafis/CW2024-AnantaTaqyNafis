package com.example.demo.object;

import com.example.demo.actor.ActiveActorDestructible;
import com.example.demo.projectile.EnemyProjectile;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A class representing the enemy's plane in the game, extending the  {@link FighterPlane}.
 * it included functionality for firing projectiles.
 */

public class EnemyPlane extends FighterPlane {
	/**
	 * Base location of enemy's plane image.
	 */
	private static final String IMAGE_NAME = "enemyplane.png";
	/**
	 * The size of enemy's plane.
	 */
	private static final int IMAGE_HEIGHT = 70;
	/**
	 * the distance of enemy plane move horizontally.
	 */
	private static final int HORIZONTAL_VELOCITY = -6;
	/**
	 * The X-axis offset for the enemy plane's projectile position.
	 * This value determines how far horizontally (left or right) the projectile
	 * is positioned relative to the enemy plane's center when fired.
	 */
	private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;
	/**
	 * The Y-axis offset for the enemy plane's projectile position.
	 * This value determines how far vertically (up or down) the projectile
	 * is positioned relative to the enemy plane's center when fired.
	 */
	private static final double PROJECTILE_Y_POSITION_OFFSET = 50.0;
	/**
	 * The Initial Health of the enemy's plane
	 */
	private static final int INITIAL_HEALTH = 1;

	/**
	 * The fire rate of enemy plane
	 */
	private static final double FIRE_RATE = .01;

	/**
	 * Construct a new{@code EnemyPlane} instance.
	 * @param initialXPos the initial X position of enemy plane.
	 * @param initialYPos the initial Y position of enemy plane.
	 */
	public EnemyPlane(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
	}

	/**
	 * Updates the position of the enemy plane to move horizontally according to its horizontal velocity.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Fires a projectile from the enemy plane based on random chance.
	 * <p>
	 *     if random chance generated is less than fire rate, it will return a new projectile and "fires" it.
	 *     otherwise, {@code null} is returned and no projectile created to be "fired".
	 * </p>
	 * @return a newP{@link ActiveActorDestructible} projectile if "fired", {@code null} if not
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		if (ThreadLocalRandom.current().nextDouble() < FIRE_RATE) {
			double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
			double projectileYPosition = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
			return new EnemyProjectile(projectileXPosition, projectileYPosition);
		}
		return null;
	}

	/**
	 * Update the states of actor
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}

}
