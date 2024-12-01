/**
 * Contains the classes and interfaces related to active actors in the game, including destructible entities.
 * These actors are game entities that are visually represented, can be moved, and may have the ability to be destroyed.
 *
 * <p>
 * The package includes:
 * </p>
 * <ul>
 *     <li>{@link com.example.demo.actor.ActiveActor}: Represents an active actor in the game with movement and image management.</li>
 *     <li>{@link com.example.demo.actor.ActiveActorDestructible}: Extends {@link com.example.demo.actor.ActiveActor} and implements the {@link com.example.demo.actor.Destructible} interface to handle destruction logic.</li>
 *     <li>{@link com.example.demo.actor.Destructible}: Interface for entities that can be destroyed, providing methods for damage and destruction handling.</li>
 * </ul>
 *
 * <p>
 * Key functionalities in this package include:
 * </p>
 * <ul>
 *     <li>Basic movement and image handling for active actors.</li>
 *     <li>Destruction logic for destructible entities, allowing damage and destruction to be applied.</li>
 *     <li>Common functionality across destructible game entities through the {@link com.example.demo.actor.Destructible} interface.</li>
 * </ul>
 *
 * <p>
 * The classes in this package use JavaFX components like {@link javafx.scene.image.ImageView} for rendering and visualizing game entities.
 * </p>
 */
package com.example.demo.actor;
