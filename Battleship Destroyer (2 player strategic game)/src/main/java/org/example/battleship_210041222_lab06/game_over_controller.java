package org.example.battleship_210041222_lab06;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class game_over_controller {
    public Label win;

    public void change_text(String s){
        win.setText(s);
    }

    public void new_game_btn_clk(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(p_1_set_ship_controller.class.getResource("p1_set_ship.fxml"));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Player 1: Plan Your Battleship Rumble!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
