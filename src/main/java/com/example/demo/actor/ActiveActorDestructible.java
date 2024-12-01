package com.example.demo.actor;

/**
 * An abstract class representing a destructible active actor in the game.
 * This class extends {@link ActiveActor} and implements the {@link Destructible} interface.
 * It provides basic functionality for managing the destruction state of the actor.
 */
public abstract class ActiveActorDestructible extends ActiveActor implements Destructible {

	/**
	 * A flag indicating whether the actor has been destroyed.
	 */
	private boolean isDestroyed;

	/**
	 * Constructs a new {@code ActiveActorDestructible} instance.
	 *
	 * @param imageName    the name of the image representing the actor
	 * @param imageHeight  the height of the image
	 * @param initialXPos  the initial X position of the actor
	 * @param initialYPos  the initial Y position of the actor
	 */
	protected ActiveActorDestructible(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		isDestroyed = false;
	}

	/**
	 * Updates the position of the actor.
	 * This method is abstract and must be implemented by subclasses.
	 */
	@Override
	public abstract void updatePosition();

	/**
	 * Updates the state of the actor, including its logic and appearance.
	 * This method is abstract and must be implemented by subclasses.
	 */
	public abstract void updateActor();

	/**
	 * Marks the actor as destroyed.
	 * Once destroyed, the actor is considered inactive or removed from the game.
	 */
	@Override
	public void destroy() {
		isDestroyed = true;

	}

	/**
	 * Checks whether the actor has been destroyed.
	 *
	 * @return {@code true} if the actor is destroyed; {@code false} otherwise
	 */
	public boolean isDestroyed() {
		return isDestroyed;
	}
}
