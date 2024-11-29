package com.example.demo.actor;

/**
 * An interface representing objects that can be destroyed.
 * Provides methods for handling damage and destruction logic.
 */
public interface Destructible {

	/**
	 * Applies damage to the object.
	 * Implementations should define how damage affects the object's state.
	 */
	void takeDamage();

	/**
	 * Destroys the object.
	 * Implementations should define what happens when the object is destroyed.
	 */
	void destroy();
}
