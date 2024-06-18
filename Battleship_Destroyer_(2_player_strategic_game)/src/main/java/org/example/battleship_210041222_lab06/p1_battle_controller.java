package org.example.battleship_210041222_lab06;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

import static org.example.battleship_210041222_lab06.p_2_set_ship_controller.player2;

public class p1_battle_controller {
    @FXML
    public GridPane grid_t1;
    static public boolean[][] player2 = p_2_set_ship_controller.player2;
    static public int[][]play1 = new int[9][9];

    static public boolean t1;
    static public boolean t2;
    static public int move1 = 0;
    static public int hit1 = 0;
    public Label m1;
    public Label b1;
    public Button swicth_p1;
    public int button_no = 6;
    public void game_over(ActionEvent e,String file) throws  IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(file));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Battleship");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public void handleButtont1(Button b){
        if(t2){
            return;
        }
        int row = GridPane.getRowIndex(b);
        int col = GridPane.getColumnIndex(b);
        move1++;
        m1.setText("Move count: "+move1);
        if(player2[row][col]) System.out.print("T ");
        else System.out.print("F ");
        if(!player2[row][col]){
            b.setStyle("-fx-background-color: black;");
            play1[row][col] = 1;
        }
        else if(player2[row][col]){
            b.setStyle("-fx-background-color: seagreen;");
            play1[row][col] = 2;
            hit1++;
            b1.setText("Buttons left: "+(button_no-hit1));
            if(hit1 == button_no){
                swicth_p1.setText("END");
                swicth_p1.setOnAction(event -> {
                    try {
                        game_over(event, "game_over1.fxml");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        }
        t1 = false;
        t2 = true;
    }
    public void turn2(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(p2_battle_controller.class.getResource("p2_battle.fxml"));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Battleship");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public void turn1_board() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Button button = new Button();
                button.setPrefSize(70, 70);

                button.setStyle("-fx-background-color: SNOW;");
                // Add event handler to change color on click
                button.setOnAction(event ->
                        handleButtont1(button)
                );
                if(play1[i][j] == 1){
                    button.setStyle("-fx-background-color: black;");
                } else if (play1[i][j] == 2) {
                    button.setStyle("-fx-background-color: seagreen;");
                }
                // Add button to the GridPane
                grid_t1.add(button, j, i);
            }
        }
        t1 = true;
        t2 = false;
        m1.setText("Move count: " + move1);
        b1.setText("Buttons left: "+(button_no-hit1));
    }
}
