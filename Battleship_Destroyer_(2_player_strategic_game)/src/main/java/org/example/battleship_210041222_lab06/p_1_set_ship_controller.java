package org.example.battleship_210041222_lab06;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class p_1_set_ship_controller {
    @FXML
    public Label b_cnt_p1;
    @FXML
    public Label d_cnt_p1;
    @FXML
    public Label s_cnt_p1;
    public boolean battle = true;
    public int battle_btn = 0;
    static public boolean[][] player1 = new boolean[9][9];
    public int destroy_btn = 0;
    public int d_num = 0;
    public int submarine_btn = 0;
    public boolean destroy = false;
    public boolean submarine = false;
    // static public boolean[][] player2 = new boolean[9][9];
    public GridPane grid_p1;
    public void main_win_to_p1_set_ship_btn_clk(ActionEvent e) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(p_1_set_ship_controller.class.getResource("p1_set_ship.fxml"));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Player 1: Plan Your Battleship Rumble!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }
    public void handleButtonp1(Button b){
        int row = grid_p1.getRowIndex(b);
        int col = grid_p1.getColumnIndex(b);
        if(battle){
            if(battle_btn == 0){
                b.setStyle("-fx-background-color: LIGHTCORAL;");
                player1[row][col] = true;
                battle_btn++;
            }
            else if(battle_btn == 1){
                if((row > 0 && player1[row-1][col]) || (row < 8 && player1[row+1][col]) || (col > 0 && player1[row][col-1]) || (col < 8 && player1[row][col+1])){
                    b.setStyle("-fx-background-color: LIGHTCORAL;");
                    player1[row][col] = true;
                    battle_btn++;
                }
            }
            else if(battle_btn == 2){
                int r1 = row -2,r2 = row + 2, c3 = col-2, c4 = col+2;
                if(row > 1 && player1[row-1][col] && player1[r1][col] || (row < 7 && player1[row+1][col] && player1[r2][col]) || (col > 1 && player1[row][col-1] && player1[row][c3]) || (col < 7 && player1[row][col+1] && player1[row][c4])){
                    b.setStyle("-fx-background-color: LIGHTCORAL;");
                    player1[row][col] = true;
                    battle_btn++;
                    b_cnt_p1.setText("Battleship: 0");
                    b_cnt_p1.setStyle("-fx-background-color: SEASHELL");
                    battle = false;
                    destroy = true;
                }
            }
        }// When destroy bool var is true????
        else if (destroy) {
            if(destroy_btn == 0){
                b.setStyle("-fx-background-color: tomato;");
                player1[row][col] = true;
                destroy_btn++;
            }
            else if(destroy_btn == 1){
                if(player1[row-1][col] || player1[row+1][col] || player1[row][col-1] || player1[row][col+1]){
                    b.setStyle("-fx-background-color: tomato;");
                    player1[row][col] = true;
                    destroy_btn++;
                    d_num++;
                    d_cnt_p1.setText("Destroyer: " + (2-d_num));
                    d_cnt_p1.setStyle("-fx-background-color: SEASHELL");
                    if(d_num == 2){
                        destroy = false;
                        submarine = true;
                    }
                }
            }
            if(destroy_btn == 2){
                destroy_btn = 0;
            }
        }
        else if(submarine){
            if(submarine_btn < 3){
                b.setStyle("-fx-background-color: red;");
                player1[row][col] = true;
                submarine_btn++;
                s_cnt_p1.setText("Submarine: " + (3-submarine_btn));
                s_cnt_p1.setStyle("-fx-background-color: SEASHELL");
            }
            else{
                submarine = false;
            }
        }
    }

    public void p1_set_ship_grid(ActionEvent e) throws IOException {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Button button = new Button();
                button.setPrefSize(75, 75);
                button.setStyle("-fx-background-color: SNOW;");
                // Add event handler to change color on click
                button.setOnAction(event -> handleButtonp1(button));

                // Add button to the GridPane
                grid_p1.add(button, i, j);
                player1[i][j] = false;
            }
        }
    }
    public void p1_set_to_p2_set_ship(ActionEvent e) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(p_2_set_ship_controller.class.getResource("p2_set_ship.fxml"));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Player 2: Plan Your Battleship Rumble!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
