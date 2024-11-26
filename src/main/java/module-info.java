module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.desktop;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo.controller;
    exports com.example.demo;
    exports com.example.demo.actor;
    opens com.example.demo.actor to javafx.fxml;
    exports com.example.demo.Object;
    opens com.example.demo.Object to javafx.fxml;
    exports com.example.demo.projectile;
    opens com.example.demo.projectile to javafx.fxml;
    exports com.example.demo.display;
    opens com.example.demo.display to javafx.fxml;
    exports com.example.demo.levelparent;
    opens com.example.demo.levelparent to javafx.fxml;
    exports com.example.demo.levelview;
    opens com.example.demo.levelview to javafx.fxml;
}