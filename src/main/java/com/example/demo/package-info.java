/**
 * The main package of the application, containing all the core classes and packages for game logic, user interface,
 * and level management.
 *
 * <p>
 * This package includes:
 * </p>
 * <ul>
 *     <li>{@link com.example.demo.controller}: Controllers for managing game navigation and user interactions.</li>
 *     <li>{@link com.example.demo.display}: Classes for managing game UI elements, including images and overlays.</li>
 *     <li>{@link com.example.demo.levelparent}: Base classes for defining the structure and logic of various game levels.</li>
 *     <li>{@link com.example.demo.actor}: Active game entities like the player plane, enemy planes, and destructible objects.</li>
 *     <li>{@link com.example.demo.projectile}: Classes for managing projectiles fired by players and enemies.</li>
 * </ul>
 *
 * <p>
 * Specific classes in this package:
 * </p>
 * <ul>
 *     <li>{@link com.example.demo.LevelEndless}: Represents the endless mode of the arcade game, with continuously spawning enemies.</li>
 *     <li>{@link com.example.demo.LevelOne}: The first level in the arcade mode, featuring a series of enemy waves.</li>
 *     <li>{@link com.example.demo.LevelTwo}: The second level in the arcade mode, which introduces the boss enemy and new game mechanics.</li>
 * </ul>
 *
 * <p>
 * Key functionalities in this package include:
 * </p>
 * <ul>
 *     <li>Managing arcade levels, including enemy spawning, player progression, and level transitions.</li>
 *     <li>Handling game-over conditions, scoring, and player lives.</li>
 *     <li>Rendering and updating game visuals using JavaFX components like {@link javafx.scene.Scene}, {@link javafx.scene.image.ImageView}, and {@link javafx.animation.Timeline}.</li>
 * </ul>
 *
 * <p>
 * The classes in this package are tightly integrated to provide a seamless arcade gameplay experience, where players can progress through levels, face increasingly difficult enemies, and strive to achieve high scores.
 * </p>
 */
package com.example.demo;
