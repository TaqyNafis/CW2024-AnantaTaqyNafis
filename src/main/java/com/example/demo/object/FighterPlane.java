package com.example.demo.object;

import com.example.demo.actor.ActiveActorDestructible;

/**
 * An abstract class representing a fighter plane in the game.
 * Extends {@link ActiveActorDestructible} to provide functionality for handling health, firing projectiles,
 * and taking damage.
 */
public abstract class FighterPlane extends ActiveActorDestructible {

	/**
	 * The health of the fighter plane. The plane is destroyed when health reaches zero.
	 */
	private int health;

	/**
	 * Constructs a new {@code FighterPlane} instance.
	 *
	 * @param imageName    the name of the image representing the fighter plane
	 * @param imageHeight  the height of the image
	 * @param initialXPos  the initial X position of the fighter plane
	 * @param initialYPos  the initial Y position of the fighter plane
	 * @param health       the initial health of the fighter plane
	 */
	protected FighterPlane(String imageName, int imageHeight, double initialXPos, double initialYPos, int health) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		this.health = health;
	}

	/**
	 * Fires a projectile from the fighter plane.
	 * Implementing classes should define the logic for creating and returning the projectile.
	 *
	 * @return an {@link ActiveActorDestructible} representing the fired projectile
	 */
	public abstract ActiveActorDestructible fireProjectile();

	/**
	 * Reduces the health of the fighter plane by 1. If health reaches zero, the plane is destroyed.
	 */
	@Override
	public void takeDamage() {
		health--;
		if (healthAtZero()) {
			this.destroy();
		}
	}

	/**
	 * Calculates the X position for the projectile based on the fighter plane's position and an offset.
	 *
	 * @param xPositionOffset the horizontal offset for the projectile's position
	 * @return the calculated X position of the projectile
	 */
	protected double getProjectileXPosition(double xPositionOffset) {
		return getLayoutX() + getTranslateX() + xPositionOffset;
	}

	/**
	 * Calculates the Y position for the projectile based on the fighter plane's position and an offset.
	 *
	 * @param yPositionOffset the vertical offset for the projectile's position
	 * @return the calculated Y position of the projectile
	 */
	protected double getProjectileYPosition(double yPositionOffset) {
		return getLayoutY() + getTranslateY() + yPositionOffset;
	}

	/**
	 * Checks whether the fighter plane's health has reached zero.
	 *
	 * @return {@code true} if health is zero; {@code false} otherwise
	 */
	private boolean healthAtZero() {
		return health == 0;
	}

	/**
	 * Retrieves the current health of the fighter plane.
	 *
	 * @return the current health value
	 */
	public int getHealth() {
		return health;
	}
}
