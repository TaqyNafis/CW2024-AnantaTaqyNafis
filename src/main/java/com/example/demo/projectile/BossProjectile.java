package com.example.demo.projectile;

/**
 * The `BossProjectile` class represents a projectile fired by a boss in the game.
 * It moves horizontally at a constant velocity and is visually represented by a fireball image.
 */
public class BossProjectile extends Projectile {
	/**
	 * The Base path of boss projectile image
	 */
	private static final String IMAGE_NAME = "fireball.png";
	/**
	 * The height of the projectile's image.
	 */
	private static final int IMAGE_HEIGHT = 75;

	/**
	 * The horizontal velocity of the projectile, indicating its speed and direction.
	 */
	private static final int HORIZONTAL_VELOCITY = -15;
	/**
	 * The initial X-coordinate for the projectile's position.
	 */
	private static final int INITIAL_X_POSITION = 950;

	/**
	 * Constructs a `BossProjectile` at a specified initial Y-coordinate.
	 *
	 * @param initialYPos The initial Y-coordinate of the projectile's position.
	 */
	public BossProjectile(double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, initialYPos);
	}

	/**
	 * Updates the projectile's position by moving it horizontally.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Updates the projectile's state by invoking its position update logic.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}

}
