package com.example.demo.object;

import com.example.demo.actor.ActiveActorDestructible;
import com.example.demo.projectile.BossProjectile;
import com.example.demo.display.ShieldImage;
import java.util.concurrent.ThreadLocalRandom;

import java.util.*;

/**
 * A class representing the Boss plane in the game, extending the {@link FighterPlane}.
 * it included functionality for shielded state, firing projectile, taking damage and shielded state.
 *
 * <p>The Boss plane behaves differently from regular planes, including random movement patterns,
 * the ability to enter a shielded state, and a special fire rate. It has more health and a unique
 * movement cycle compared to regular enemies.</p>
 *
 * <p>Key features of the Boss plane:</p>
 * <ul>
 *     <li>Shielded state with a low probability of activation.</li>
 *     <li>Unique movement pattern, moving vertically in cycles with varying velocities.</li>
 *     <li>Fires projectiles based on a specific fire rate.</li>
 *     <li>Increased health compared to regular planes.</li>
 *     <li>Special shielded state that activates for a set number of frames.</li>
 * </ul>
 */
public class Boss extends FighterPlane {
	/**
	 * Base location of boss plane image.
	 */
	private static final String IMAGE_NAME = "bossplane.png";
	/**
	 * Initial X position of Boss plane
	 */
	private static final double INITIAL_X_POSITION = 1000.0;
	/**
	 * Initial Y position of Boss plane
	 */
	private static final double INITIAL_Y_POSITION = 200;
	/**
	 * The Y-axis offset for the boss plane's projectile position.
	 * This value determines how far vertically (up or down) the projectile
	 * is positioned relative to the user plane's center when fired.
	 */
	private static final double PROJECTILE_Y_POSITION_OFFSET = 150;
	/**
	 * The fire rate for boss plane
	 */
	private static final double BOSS_FIRE_RATE = 0.08;
	/**
	 * The chances of boss to be in shielded state
	 */
	private static final double BOSS_SHIELD_PROBABILITY = 0.005;
	/**
	 * The size of boss plane image
	 */
	private static final int IMAGE_HEIGHT = 350;
	/**
	 * The distance of boss moving vertically in a cycle
	 */
	private static final int VERTICAL_VELOCITY = 8;
	/**
	 * The Initial Health of boss plane
	 */
	private static final int HEALTH = 5;
	/**
	 * The frequency of boss plane to move in  a cycle
	 */
	private static final int MOVE_FREQUENCY_PER_CYCLE = 5;
	/**
	 * Constant value representing no movement.
	 * This is used to indicate that no movement should occur in the direction specified.
	 */
	private static final int NO_MOVEMENT = 0;
	/**
	 * The maximum number of frame the boss can do the same movement in one direction
	 */
	private static final int MAX_FRAMES_WITH_SAME_MOVE = 10;
	/**
	 * The maximum Y position of boss plane
	 */
	private static final int Y_POSITION_UPPER_BOUND = -130;
	/**
	 * The minimum Y position of boss plane
	 */
	private static final int Y_POSITION_LOWER_BOUND = 480;
	/**
	 * The Maximum amount of frame boss can be in a shielded state
	 */
	private static final int MAX_FRAMES_WITH_SHIELD = 250;
	/**
	 * List to store the movement pattern of the boss plane.
	 * The pattern determines how the boss plane moves vertically in a cycle.
	 */
	private final List<Integer> movePattern;
	/**
	 * Flag indicating whether the boss plane is currently shielded.
	 */
	private boolean isShielded;
	/**
	 * The X offset for shield position
	 */
	private static final int SHIELD_OFFSET_X = 200;
	/**
	 * The Y offset for shield position
	 */
	private static final int SHIELD_OFFSET_Y = 70;

	/**
	 * Flag indicating whether the boss plane is currently shielded.
	 */
	private int consecutiveMovesInSameDirection;
	/**
	 * The current index of the boss plane's move pattern.
	 */
	private int indexOfCurrentMove;
	/**
	 * The number of frames the boss has been shielded.
	 */
	private int framesWithShieldActivated;
	/**
	 * Shield image representing the boss plane's shield when activated.
	 */
	private final ShieldImage shieldImage;
	/**
	 * Represents the maximum number of frames the Boss can remain in an invincibility state.
	 */
	private static final int MAX_INVINCIBILITY_FRAME=30;
	/**
	 * Tracks the current frame count during the Boss's invincibility state.
	 * Resets when the invincibility period ends.
	 */
	private int invincibilityFrame=0;
	/**
	 * Indicates whether the Boss is currently in an invincibility state.
	 * If true, the Boss cannot take damage.
	 */
	private boolean hasInvincibility = false;
	/**
	 * Constructs a new {@code Boss} object with predefined attributes and behaviors.
	 * <p>
	 * Initializes the Boss with:
	 * <ul>
	 *     <li>A movement pattern for vertical positioning.</li>
	 *     <li>A shield image for visual and functional protection.</li>
	 *     <li>Base health, position, and size inherited from {@link FighterPlane}.</li>
	 * </ul>
	 */
	public Boss() {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, HEALTH);
		movePattern = new ArrayList<>();
		consecutiveMovesInSameDirection = 0;
		indexOfCurrentMove = 0;
			framesWithShieldActivated = 0;
		isShielded = false;
		shieldImage = new ShieldImage(INITIAL_X_POSITION-SHIELD_OFFSET_X, INITIAL_Y_POSITION+SHIELD_OFFSET_Y);
		initializeMovePattern();
	}

	/**
	 * Updates the position of the boss plane based on its movement pattern.
	 * It also ensures that the shield image follows the boss plane's position.
	 */
	@Override
	public void updatePosition() {
		double initialTranslateY = getTranslateY();
		moveVertically(getNextMove());
		double currentPosition = getLayoutY() + getTranslateY();
		shieldImage.setLayoutY(currentPosition+SHIELD_OFFSET_Y);
		if (currentPosition < Y_POSITION_UPPER_BOUND || currentPosition > Y_POSITION_LOWER_BOUND) {
			setTranslateY(initialTranslateY);
		}
	}
	/**
	 * Updates the state of the boss plane, including movement, shield, and invincibility.
	 */
	@Override
	public void updateActor() {
		updatePosition();
		updateShield();
		updateInvincibilityFrame();
	}
	/**
	 * Fires a projectile from the boss plane if the fire condition is met.
	 *
	 * @return The fired projectile, or null if the boss does not fire.
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		return bossFiresInCurrentFrame() ? new BossProjectile(getProjectileInitialPosition()) : null;
	}
	/**
	 * Makes the boss plane take damage, considering its shield and invincibility state.
	 */
	@Override
	public void takeDamage() {
		if (!isShielded && !hasInvincibility) {
			super.takeDamage();
			hasInvincibility = true;
		}
	}
	/**
	 * Updates the invincibility state of the boss plane.
	 * It toggles the visibility of the boss and resets invincibility after a set duration.
	 */
	public void updateInvincibilityFrame() {
		if(hasInvincibility) {
			invincibilityFrame++;
		}
        super.setVisible(invincibilityFrame % 4 == 0);

		if(invincibilityFrame==MAX_INVINCIBILITY_FRAME) {
			hasInvincibility = false;
			invincibilityFrame = 0;
		}
	}
	/**
	 * Initializes the boss plane's vertical movement pattern.
	 */
	private void initializeMovePattern() {
		for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++) {
			movePattern.add(VERTICAL_VELOCITY);
			movePattern.add(-VERTICAL_VELOCITY);
			movePattern.add(NO_MOVEMENT);
		}
		Collections.shuffle(movePattern);
	}
	/**
	 * Updates the shield state of the boss plane.
	 * The shield may activate randomly or deactivate after a set duration.
	 */
	private void updateShield() {
		if (isShielded) {
			framesWithShieldActivated++;
			shieldImage.showShield();
		}
		else if (shieldShouldBeActivated()){
			activateShield();
		}
		if (shieldExhausted()) {
			deactivateShield();
		}

	}
	/**
	 * Determines the next vertical movement for the boss plane based on its movement pattern.
	 *
	 * @return The vertical velocity for the next move.
	 */
	private int getNextMove() {
		int currentMove = movePattern.get(indexOfCurrentMove);
		consecutiveMovesInSameDirection++;
		if (consecutiveMovesInSameDirection == MAX_FRAMES_WITH_SAME_MOVE) {
			Collections.shuffle(movePattern);
			consecutiveMovesInSameDirection = 0;
			indexOfCurrentMove++;
		}
		if (indexOfCurrentMove == movePattern.size()) {
			indexOfCurrentMove = 0;
		}
		return currentMove;
	}
	/**
	 * Determines if the boss should fire a projectile in the current frame based on its fire rate.
	 *
	 * @return True if the boss fires, false otherwise.
	 */
	private boolean bossFiresInCurrentFrame() {
		return ThreadLocalRandom.current().nextDouble() < BOSS_FIRE_RATE;
	}
	/**
	 * Gets the initial position for the projectile based on the boss plane's position.
	 *
	 * @return The initial Y position of the projectile.
	 */
	private double getProjectileInitialPosition() {
		return getLayoutY() + getTranslateY() + PROJECTILE_Y_POSITION_OFFSET;
	}
	/**
	 * Determines whether the boss's shield should be activated based on the shield activation probability.
	 *
	 * @return True if the shield should be activated, false otherwise.
	 */
	private boolean shieldShouldBeActivated() {
		if(!isShielded) {
			 return ThreadLocalRandom.current().nextDouble() < BOSS_SHIELD_PROBABILITY;
		}
		return false;
	}
	/**
	 * Determines whether the boss's shield should be activated based on the shield activation probability.
	 *
	 * @return True if the shield should be activated, false otherwise.
	 */
	private boolean shieldExhausted() {
		return framesWithShieldActivated == MAX_FRAMES_WITH_SHIELD;
	}
	/**
	 * Activates the shield for the boss plane.
	 */
	private void activateShield() {
		isShielded = true;
		shieldImage.showShield();
	}
	/**
	 * Deactivates the shield for the boss plane.
	 */
	private void deactivateShield() {
		isShielded = false;
		framesWithShieldActivated = 0;
		shieldImage.hideShield();
	}

	/**
	 * Returns the shield image associated with the boss plane.
	 *
	 * @return The {@link ShieldImage} object representing the shield image of the boss plane.
	 */
	public ShieldImage getShieldImage() {
		return shieldImage;
	}
}
