module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.desktop;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo.controller;
    exports com.example.demo;
    exports com.example.demo.Actor;
    opens com.example.demo.Actor to javafx.fxml;
    exports com.example.demo.Object;
    opens com.example.demo.Object to javafx.fxml;
    exports com.example.demo.Projectiles;
    opens com.example.demo.Projectiles to javafx.fxml;
    exports com.example.demo.Display;
    opens com.example.demo.Display to javafx.fxml;
    exports com.example.demo.LevelParent;
    opens com.example.demo.LevelParent to javafx.fxml;
    exports com.example.demo.LevelView;
    opens com.example.demo.LevelView to javafx.fxml;
}