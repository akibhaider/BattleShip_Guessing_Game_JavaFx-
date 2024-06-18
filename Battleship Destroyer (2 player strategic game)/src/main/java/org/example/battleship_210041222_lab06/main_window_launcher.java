package org.example.battleship_210041222_lab06;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class main_window_launcher extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(main_window_launcher.class.getResource("main_window.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Battleship Destroyer");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}