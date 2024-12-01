/**
 * This is the module descriptor for the Demo game application.
 * The module defines the following dependencies:
 * - javafx.controls: Required for JavaFX GUI components.
 * - javafx.fxml: Required for JavaFX FXML functionality.
 * - java.logging: Required for logging functionality.
 * - java.desktop: Provides desktop-related functionality.
 * The following packages are exported for use by other modules:
 * - com.example.demo
 * - com.example.demo.controller
 * - com.example.demo.actor
 * - com.example.demo.object
 * - com.example.demo.projectile
 * - com.example.demo.display
 * - com.example.demo.levelparent
 * - com.example.demo.levelview
 * This module is the core of the game application, including the main menu, gameplay logic, and more.
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
