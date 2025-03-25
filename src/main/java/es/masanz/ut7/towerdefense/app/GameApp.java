package es.masanz.ut7.towerdefense.app;

import es.masanz.ut7.towerdefense.controller.GameController;
import javafx.application.Application;
import javafx.stage.Stage;

public class GameApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        GameController gc = new GameController();
        gc.launch(stage);
    }

}

