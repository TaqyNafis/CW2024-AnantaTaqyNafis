/**
 * This is the module descriptor for the Demo game application.
 * The module defines the following dependencies:
 * <ul>
 *     <li><code>javafx.controls</code>: Required for JavaFX GUI components.</li>
 *     <li><code>javafx.fxml</code>: Required for JavaFX FXML functionality.</li>
 *     <li><code>java.logging</code>: Required for logging functionality.</li>
 *     <li><code>java.desktop</code>: Provides desktop-related functionality.</li>
 * </ul>
 *
 * The following packages are exported for use by other modules:
 * <ul>
 *     <li><code>com.example.demo</code>: Core package of the game application.</li>
 *     <li><code>com.example.demo.controller</code>: Package for controllers (handling user input and transitions).</li>
 *     <li><code>com.example.demo.actor</code>: Package for active game entities (player, enemies, etc.).</li>
 *     <li><code>com.example.demo.object</code>: Package for game objects like projectiles and planes.</li>
 *     <li><code>com.example.demo.projectile</code>: Package for projectiles (user and enemy fire).</li>
 *     <li><code>com.example.demo.display</code>: Package for the UI and game visuals (images, overlays).</li>
 *     <li><code>com.example.demo.levelparent</code>: Package for the base game levels (game environment and logic).</li>
 *     <li><code>com.example.demo.levelview</code>: Package for specific level views and UI layout.</li>
 * </ul>
 *
 * The module is the core of the game application, which includes the main menu, gameplay logic, and more.
 */
module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.desktop;

    exports com.example.demo;
    exports com.example.demo.controller;
    exports com.example.demo.actor;
    exports com.example.demo.object;
    exports com.example.demo.projectile;
    exports com.example.demo.display;
    exports com.example.demo.levelparent;
    exports com.example.demo.levelview;

    opens com.example.demo to javafx.fxml;
    opens com.example.demo.actor to javafx.fxml;
    opens com.example.demo.object to javafx.fxml;
    opens com.example.demo.projectile to javafx.fxml;
    opens com.example.demo.display to javafx.fxml;
    opens com.example.demo.levelparent to javafx.fxml;
    opens com.example.demo.levelview to javafx.fxml;
    opens com.example.demo.controller to javafx.fxml;
}