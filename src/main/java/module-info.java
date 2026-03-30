module es.masanz.ut7.towerdefense {
    requires javafx.controls;
    requires javafx.fxml;
    
    requires jdk.xml.dom;
    requires java.sql;

    // Abrimos los controladores para que FXML pueda acceder a ellos
    opens es.masanz.ut7.towerdefense.controller to javafx.fxml;

    // Exportamos los paquetes que deben ser accesibles desde otros módulos
    exports es.masanz.ut7.towerdefense.app;
    exports es.masanz.ut7.towerdefense.controller;
    exports es.masanz.ut7.towerdefense.model.base;
    exports es.masanz.ut7.towerdefense.model.impl;
}