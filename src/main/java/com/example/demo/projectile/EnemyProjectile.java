package com.example.demo.projectile;
/**
 * The `EnemyProjectile` class represents a projectile fired by an enemy in the game.
 * It moves horizontally at a constant velocity and is visually represented by an enemy fire image.
 */
public class EnemyProjectile extends Projectile {
	/**
	 * The base path for image
	 */
	private static final String IMAGE_NAME = "enemyFire.png";
	/**
	 * The name of the image file used to represent the projectile.
	 */
	private static final int IMAGE_HEIGHT = 17;
	/**
	 * The horizontal velocity of the projectile, indicating its speed and direction.
	 */
	private static final double HORIZONTAL_VELOCITY = -10;

	/**
	 * Constructs an `EnemyProjectile` at a specified initial X and Y position.
	 *
	 * @param initialXPos The initial X-coordinate of the projectile's position.
	 * @param initialYPos The initial Y-coordinate of the projectile's position.
	 */
	public EnemyProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
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
