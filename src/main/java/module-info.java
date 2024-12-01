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
    requires javafx.controls;  // For JavaFX GUI components
    requires javafx.fxml;     // For JavaFX FXML functionality
    requires java.logging;    // For logging functionality
    requires java.desktop;    // For desktop-related functionality

    // Exported packages
    exports com.example.demo;             // Core package of the game
    exports com.example.demo.controller;   // Package for controllers (handling user input and transitions)
    exports com.example.demo.actor;        // Package for active game entities (player, enemies, etc.)
    exports com.example.demo.object;       // Package for game objects like projectiles and planes
    exports com.example.demo.projectile;   // Package for projectiles (user and enemy fire)
    exports com.example.demo.display;      // Package for the UI and game visuals (images, overlays)
    exports com.example.demo.levelparent;  // Package for the base game levels (game environment and logic)
    exports com.example.demo.levelview;    // Package for specific level views and UI layout

    // Opening packages for FXML introspection
    opens com.example.demo to javafx.fxml;
    opens com.example.demo.actor to javafx.fxml;
    opens com.example.demo.object to javafx.fxml;
    opens com.example.demo.projectile to javafx.fxml;
    opens com.example.demo.display to javafx.fxml;
    opens com.example.demo.levelparent to javafx.fxml;
    opens com.example.demo.levelview to javafx.fxml;
    opens com.example.demo.controller to javafx.fxml;
}
