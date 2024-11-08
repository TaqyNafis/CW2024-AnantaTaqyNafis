=======================================
          GITHUB REPOSITORY LINK
=======================================
https://github.com/TaqyNafis/CW2024-AnantaTaqyNafis
=======================================

IMPLEMENTED FEATURES AND CHANGES
=======================================
- Main Menu
- How to Play Menu
- Pause Functionality and Pause Menu
- Auto-Centered Window When Screen Size Changes
- Properly clean up assets when changing levels
- Control Menu

PLANNED FEATURES
=======================================
- Endless Mode
- More enemy variety

NEW JAVA CLASSES
=======================================
1. **PauseMenuImage Class**  
   - Handles the display of the pause menu image during gameplay.

2. **MainMenuController Class**  
   - Manages control of main menu.

3. **HowToPlayController Class**  
   - Manages control of How to Play menu.

4. **MainMenu Class**  
   - Responsible for displaying the main menu of the game.

5. **ControlController class**
   - Manages control of controls menu

MODIFIED FEATURES AND CHANGES
=======================================

1. **Main Class**  
   - Changed to show the main menu first upon game launch instead of starting the level directly.  
   - Set the starting screen width to 640x400.

2. **Controller Class**  
   - Added `centerWindow()` function to center the game window when resized, with a listener for size changes.
   - rename 'launchgame' function to 'startArcade'

3. **ShieldImage Class**  
   - Updated image path for the shield from "shield.jpg" to "shield.png" using `IMAGE_NAME` variable with the `getResource` method.

4. **WinImage Class**  
   - Removed the `showWinImage()` function, as the win image is now shown by default.

5. **GameOverImage Class**  
   - Set width and height of the image to 500x600.

6. **LevelParent Class**  
   - Added bottom, middle, and top layers to root nodes for better asset separation.  
     - Bottom layer: Background image  
     - Middle layer: Updatable actors (plane, projectiles)  
     - Top layer: GUI elements (life counter, game over image, etc.)  
   - Removed `showWinImage()` as it is shown by default.  
   - Added boolean variables `isSpaceEnabled`, `isPause`, and `isEscEnabled`.  
   - Updated `handleEnemyPenetration()` to decrease kill count when an enemy is destroyed.  
   - Updated `winGame()` and `loseGame()` functions to disable ESC and Space keys on respective screens.  
   - Added `pauseLevel()` function to pause gameplay and show the pause menu image.  
   - Added `resetLevel()` function to reset the level to Level 1.  
   - Modified projectile firing to check if Space key is enabled.

7. **LevelView Class**  
   - Replaced individual win/lose image position variables with standardized `IMAGE_X_POSITION` and `IMAGE_Y_POSITION`.  
   - Added dark overlay background and pause menu to level view.  
   - Added `SCREEN_WIDTH` and `SCREEN_HEIGHT` for dark overlay.  
   - Removed `showWinImage()` from heart display.  
   - Added `showPauseMenuImage()` function to show the pause menu and overlay on first call.  
   - Added `hidePauseMenuImage()` function to hide pause menu and overlay.  
   - Added `removeAssetsFromScene()` to remove selected actor from scene.  
   - Added `clearAsset()` function to clean up assets and free resources.  
   - Added `goToMainMenu()` function to transition from level to main menu.

8. **LevelViewLevelTwo Class**  
   - Removed `showShield()` and `hideShield()` functions, now handled in `ShieldImage` class.

9. **UserPlane Class**  
   - Updated upper and lower bounds for the user plane's position.  
   - Adjusted image height.  
   - Added `decrementKillCount()` function.

10. **Boss Class**  
    - Updated upper and lower bounds for boss plane's position.  
    - Adjusted image height.  
    - Modified `activateShield()` and `deactivateShield()` functions to properly manage shield.

UNEXPECTED PROBLEMS AND SOLUTIONS
=======================================

1. **Problem:** Collision hitboxes do not align with visual representations.  
   **Solution:** Updated images and adjusted hitboxes to match actor visual boundaries.

2. **Problem:** User plane and boss plane can move off-screen.  
   **Solution:** Adjusted upper and lower bounds for Y-position.

3. **Problem:** Boss shield not displaying correctly.  
   **Solution:** Integrated the boss's shield image into the root node. Updated `updateShield()` to show/remove the shield properly.

4. **Problem:** Memory leak when transitioning from Level 1 to Level 2 if an enemy is destroyed.  
   **Solution:** Properly clear assets using `clearAsset()` when calling `goToNextLevel()`.

5. **Problem:** Space bar spawns bullets on Game Over or Win screens.  
   **Solution:** Added `isSpaceEnabled` flag and set to false during Game Over and Win screens.

6. **Problem:** Kill count increases when an enemy plane is destroyed off-screen.  
   **Solution:** Decrease kill count when an enemy plane goes off-screen.

7. **Problem:** Pause menu image does not disappear when unpaused.  
   **Solution:** Use `showPauseMenuImage()` only once and toggle visibility when needed.

8. **Problem:** `java.lang.reflect.InvocationTargetException` error when transitioning to Level 2.  
   **Solution:** Corrected shield image path and changed "shield.jpg" to "shield.png".

9. **Problem:** Game Over image does not fit the screen.  
   **Solution:** Set Game Over image size to 500x600 for proper fitting.
