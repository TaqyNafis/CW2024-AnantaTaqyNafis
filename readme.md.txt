GITHUB REPOSITORY LINK:
https://github.com/TaqyNafis/CW2024-AnantaTaqyNafis

IMPLEMENTED FEATURE AND CHANGE=
main menu
how to play menu
pause function and pause menu
automatically centered window when screen size change



planned feature=
properly clean asset when changing level
endless mode
more enemy variety



NEW JAVA CLASSES:
PauseMenuImage class =
 classes to show pause menu image during game time

MainMenucontroller class = 
classes for main menu control

How to play controller class= 
classes for how to play menu control



MODIFIED FEATURE AND CHANGES=
Main class=
change the main class so that when game is launched it showed main menu first instead of level
change starting screen width of game into 640 x 400


controller class=
added function so that when game is resize it will center the game window to the center of window name centerWindow() and listener for window size change

ShieldImage class=
Update the ShieldImage class by setting the correct path for the shield image. Replace "shield.jpg" with "shield.png", and assign the image path using the IMAGE_NAME string variable via the getResource method."

WinImage class=
Remove showWinImage() function since the win image will be shown by default

GameOverImage class=

added set width and height of 500 x 600 


LevelParent class=
Remove showWinImage() function since the win image will be shown by default

Added Boolean variable of isSpaceEnabled, isPause and isEscEnabled

change handleEnemyPenetration() function to decrease kill amount whenever it destroy enemy

change winGame() function to disable ESC button and Space button whenever it is showing win screen

change loseGame() function to disable ESC button and Space button whenever it is showing Game Over screen

Added pauseLevel() function that is binded to Escape that whenever is called will disable Space button and change the isPause state to true, it will pause gameplay  and show the Pause menu image. and if it is called while game is paused it will allow use of space again, change isPause to falase and resume gameplay

Added reset level function that is binded to "R" and when called it will go back to level 1

change the requirement to fire projectile instead of only pressing space it will also check 
wether space is currently enabled or not

LeveLView class=
remove WIN_IMAGE_X_POSITION, WIN_IMAGE_Y_POSITION, LOSS_SCREEN_X_POSITION and  LOSS_SCREEN_Y_POSITION and replace them into IMAGE_X_POSITION and  IMAGE_Y_POSITION since there is no need to have different variable for each image better to just have 1 standarized 

added dark overlay background and pause Menu to level view 

Added SCREEN_WIDTH AND SCREEN_HEIGHT Variable it is used for the height and width of dark overlay


removed "winImage.showWinImage();" from showHeart Display since it has been removed from WinImage class

Added ShowPauseMenuImage() Function.
it is a function when first called will add both darkOverlay effect and pause menu image to root nodes on first call and if not ensure that both will always be on top of other asset 

Added hidePauseMenuImage() function.
It is a function that when called will set visibility of pause menu image and dark overlay to false


LevelViewLevelTwo class=
remove showShield() and hideShield() function since function is already made in ShieldImage class

UserPlane class=
change upper bound and lower bound of user plane
change image_height of user plane
add decrementKillCount() function

Boss Class=
Change upper bound and lower boundd of boss plane
change image_height of boss plane
change activateShield() and deactivateShield() function where respectively called showShield() and hideShield() function to properly spawn and despawn shield from boss





UNEXPECTED PROBLEM=
PROBLEM:
The collision hitboxes for each asset do not align with their visual representations.
SOLUTION:
Update the image for each asset and adjust the hitbox to match the actor's visual boundaries more accurately.

PROBLEM:
User Plane and boss plane can go out of screen
solution:
change y position upper bound and lower bound for userPlane and Boss class

PROBLEM:
boss not showing shield properly
SOLUTION:
Integrated the boss's shield image into the root node upon spawning in Level Two.  Updated the updateShield() function to accurately display the shield when the boss is shielded and to remove it when the boss is no longer shielded.

PROBLEM:
when transitioning from level 1 to level 2 memory leak happen if transition of level happened because enemy plane is destroyed
SOLUTION:
properly clear userPlane when calling goToNextLevel function

PROBLEM:
On game over and win screen if space is pressed bullet will spawn 
SOLUTION:
Added isSpaceEnabled flag and is turn to false when game over screen and win game screen is called

PROBLEM: 
when enemy is destroyed after moving out of screen it increase kill count
SOLUTION:
whenever is plane is destroyed because of going out of screen it  decrease kill count.

PROBLEM:
when game is being unpause the pause menu image didn't disappear
SOLUTION:
instead of removing image from root node and adding image to root node each time the pause menu  is called it instead only add image when first called and hide and show image when it is needed

PROBLEM:
when goin to level 2 it gave error of "class.java.lang.reflect.InvocationTargetException"
SOLUTION:
change the shield pathing to its proper path and change "shield.jpg" to "shield.png"

PROBLEM:
Game Over image doesn't fit the screen properly
SOLUTION:
changes the GameOverImage.class to be more similar to WinImage class where it have set width and height to be 500 x 600









