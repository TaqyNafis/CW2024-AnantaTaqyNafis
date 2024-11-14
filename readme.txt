=======================================
          GITHUB REPOSITORY LINK
=======================================
https://github.com/TaqyNafis/CW2024-AnantaTaqyNafis
=======================================

IMPLEMENTED FEATURES AND CHANGES
=======================================
- 	change image size and blank space so it matches their visual
- Main Menu
- How to Play Menu
- Pause Functionality and Pause Menu
- Auto-Centered Window When Screen Size Changes
- Properly clean up assets when changing levels
- View Control Menu
- Endless Mode(still need polishing)

PLANNED FEATURES
=======================================
- Change to the boss battle system
- add invincibility frame when player take damage
- sound maybe?

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

6. **EndlessController class**
   -Manage control for go to endless mode

7. **LevelEndless Class**
   -Manage the endless mode

8. **LevelParentEndless class**
   -Mange endless level function 

MODIFIED FEATURES AND CHANGES
=======================================

1. **Main Class**  
   - Changed to show the main menu first upon game launch instead of starting the level directly.  
   - Set the starting screen width to 640x400.
   - Using logger import to replace `printStackTrace()`

2. **Controller Class**  
   - Added `centerWindow()` function to center the game window when resized, with a listener for size changes.
   - rename 'launchgame' function to 'startArcade'

3. **ShieldImage Class**  
   - Updated image path for the shield from "shield.jpg" to "shield.png" using `IMAGE_NAME` variable with the `getResource` method.
   - - made sure that function is not able to return Null

4. **WinImage Class**  
   - Removed the `showWinImage()` function, as the win image is now shown by default.
   - made sure that function is not able to return Null

5. **GameOverImage Class**  
   - Set width and height of the image to 500x600.
   - made sure that function is not able to return Null

6. **LevelParent Class**  
   - Added bottom, middle, and top layers to root nodes for better asset separation and remove root variable.  
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
   - Made class to not be able to return NULL
   - replace `EventHandler<KeyEvent>()`with Lambda
   - replace method refrence for `updateActors` and `RemoveDestroyedActors` with lambda
   - simplify 'collect(toList())' to be replaced with 'toList()'

7. **LevelView Class**  
   - Added bottom, middle, and top layers to root nodes for better asset separation and remove root variable.  
     - Bottom layer: Background image  
     - Middle layer: Updatable actors (plane, projectiles)  
     - Top layer: GUI elements (life counter, game over image, etc.)  
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
   - Added bottom, middle, and top layers to root nodes for better asset separation and remove root variable.  
     - Bottom layer: Background image  
     - Middle layer: Updatable actors (plane, projectiles)  
     - Top layer: GUI elements (life counter, game over image, etc.)  
   - Removed `showShield()` and `hideShield()` functions, now handled in `ShieldImage` class.
   -Remove 'addImageToRoot'
     - Change initiateLevelViewLevel() function to make it shorter but still return same


9. **UserPlane Class**  
   - Updated upper and lower bounds for the user plane's position.  
   - Adjusted image height.  
   - Added `decrementKillCount()` function.

10. **Boss Class**  
    - Updated upper and lower bounds for boss plane's position.  
    - Adjusted image height.  
    - Modified `activateShield()` and `deactivateShield()` functions to properly manage shield.
    - Added 'getshield()' function

11. **HeartDisplay**
    - Change "ContainerXposition","ContainerYposition" and numberOfHeartsToDisplay  to be final
    - Change so that `initializeHearts()` wont be able to return NULL

12. **ActiveActor class**
    - chane Active actor class so it won't return nulls

13. **ActiveActorDestructible class**
    -removed `setDestroyed()` function since it is redundant
    -change 'destroy()' function to call isDestroyed instead of setDestroyed

14.**Other**
- added fxml folder under resources
- added fxml file for main menu, how to play menu and control menu
- change module info so that it can access java logging package; to be used to replace` printStackTrace()` usage as a more robust logging)'

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
