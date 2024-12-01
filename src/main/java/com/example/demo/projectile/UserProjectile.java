package com.example.demo.projectile;
/**
 * The `UserProjectile` class represents a projectile fired by the user in the game.
 * It extends the `Projectile` class and defines specific behavior for user-fired projectiles.
 */
public class UserProjectile extends Projectile {
	/**
	 * The base image path for image.
	 */
	private static final String IMAGE_NAME = "userfire.png";
	/**
	 * The height of the user projectile's image.
	 */
	private static final int IMAGE_HEIGHT = 7;
	/**
	 * The horizontal velocity at which the user projectile moves.
	 */
	private static final int HORIZONTAL_VELOCITY = 15;

	/**
	 * Constructs a `UserProjectile` with the specified initial position.
	 *
	 * @param initialXPos The initial X-coordinate for the user projectile.
	 * @param initialYPos The initial Y-coordinate for the user projectile.
	 */
	public UserProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
	}
	/**
	 * Updates the position of the user projectile by moving it horizontally.
	 * The horizontal velocity is defined by the constant `HORIZONTAL_VELOCITY`.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}
	/**
	 * Updates the state of the user projectile by calling the `updatePosition` method.
	 * This method is invoked to apply the movement behavior of the projectile.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
	
}
