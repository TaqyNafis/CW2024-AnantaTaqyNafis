/**
 * Contains the classes representing various types of planes in the game.
 * This package includes both player-controlled and enemy planes, with functionality
 * for movement, firing projectiles, taking damage, and handling special states such as shields.
 *
 * <p>
 * The package includes:
 * </p>
 * <ul>
 *     <li>{@link com.example.demo.object.Boss}: A specialized enemy plane with unique movement patterns, a shielded state, and increased health.</li>
 *     <li>{@link com.example.demo.object.EnemyPlane}: A basic enemy plane that can fire projectiles.</li>
 *     <li>{@link com.example.demo.object.FighterPlane}: An abstract class representing a generic fighter plane, providing common functionality for handling health and firing projectiles.</li>
 *     <li>{@link com.example.demo.object.UserPlane}: A plane controlled by the user, extending {@link com.example.demo.object.FighterPlane}, with additional features like movement and kill management.</li>
 * </ul>
 *
 * <p>
 * Key functionalities in this package include:
 * </p>
 * <ul>
 *     <li>Health and damage management for all types of planes.</li>
 *     <li>Projectile firing mechanisms, including rate control.</li>
 *     <li>Shielded state functionality for specific planes like {@link com.example.demo.object.Boss}.</li>
 *     <li>Custom movement patterns for enemies and the player.</li>
 * </ul>
 *
 * <p>
 * The classes in this package use the inheritance model to share common behavior among different types of planes,
 * extending {@link com.example.demo.actor.ActiveActorDestructible} for health and damage handling.
 * </p>
 *
 */
package com.example.demo.object;
