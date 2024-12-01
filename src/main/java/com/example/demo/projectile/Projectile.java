package com.example.demo.projectile;

import com.example.demo.actor.ActiveActorDestructible;

/**
 * The `Projectile` class represents a generic projectile in the game.
 * It extends from `ActiveActorDestructible` and is used as the base class for various types of projectiles.
 * The class provides the ability to move the projectile and manage its interaction with other objects.
 */
public abstract class Projectile extends ActiveActorDestructible {
	/**
	 * Constructs a `Projectile` with the specified image, dimensions, and initial position.
	 *
	 * @param imageName     The name of the image file representing the projectile.
	 * @param imageHeight   The height of the projectile's image.
	 * @param initialXPos   The initial X-coordinate for the projectile.
	 * @param initialYPos   The initial Y-coordinate for the projectile.
	 */
	protected Projectile(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
	}
	/**
	 * Handles the projectile taking damage. This implementation destroys the projectile when damage is taken.
	 * It calls the `destroy` method from the superclass to remove the projectile.
	 */
	@Override
	public void takeDamage() {
		this.destroy();
	}
	/**
	 * Updates the projectile's position. This method is abstract and must be implemented by subclasses
	 * to define how the projectile moves.
	 */
	@Override
	public abstract void updatePosition();

}
