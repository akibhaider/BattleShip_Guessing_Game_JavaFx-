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

public class p_2_set_ship_controller {
    @FXML
    public Label b_cnt_p2;
    @FXML
    public Label d_cnt_p2;
    @FXML
    public Label s_cnt_p2;
    public boolean battle = true;
    public int battle_btn = 0;
    static public boolean[][] player2 = new boolean[9][9];
    public int destroy_btn = 0;
    public int d_num = 0;
    public int submarine_btn = 0;
    public boolean destroy = false;
    public boolean submarine = false;
    public GridPane grid_p2;
    public void handleButtonp2(Button b){
        int row = grid_p2.getRowIndex(b);
        int col = grid_p2.getColumnIndex(b);
        if(battle){
            if(battle_btn == 0){
                b.setStyle("-fx-background-color: navy;");
                player2[row][col] = true;
                battle_btn++;
            }
            else if(battle_btn == 1){
                if((row > 0 && player2[row-1][col]) || (row < 8 && player2[row+1][col]) || (col > 0 && player2[row][col-1]) || (col < 8 && player2[row][col+1])){
                    b.setStyle("-fx-background-color: navy;");
                    player2[row][col] = true;
                    battle_btn++;
                }
            }
            else if(battle_btn == 2){
                int r1 = row -2,r2 = row + 2, c3 = col-2, c4 = col+2;
                if(row > 1 && player2[row-1][col] && player2[r1][col] || (row < 7 && player2[row+1][col] && player2[r2][col]) || (col > 1 && player2[row][col-1] && player2[row][c3]) || (col < 7 && player2[row][col+1] && player2[row][c4])){
                    b.setStyle("-fx-background-color: navy;");
                    player2[row][col] = true;
                    battle_btn++;
                    b_cnt_p2.setText("Battleship: 0");
                    b_cnt_p2.setStyle("-fx-background-color: SEASHELL");
                    battle = false;
                    destroy = true;
                }
            }
        }// When destroy bool var is true????
        else if (destroy) {
            if(destroy_btn == 0){
                b.setStyle("-fx-background-color: DODGERBLUE;");
                player2[row][col] = true;
                destroy_btn++;
            }
            else if(destroy_btn == 1){
                if(player2[row-1][col] || player2[row+1][col] || player2[row][col-1] || player2[row][col+1]){
                    b.setStyle("-fx-background-color: DODGERBLUE;");
                    player2[row][col] = true;
                    destroy_btn++;
                    d_num++;
                    d_cnt_p2.setText("Destroyer: " + (2-d_num));
                    d_cnt_p2.setStyle("-fx-background-color: SEASHELL");
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
                b.setStyle("-fx-background-color: slateblue;");
                player2[row][col] = true;
                submarine_btn++;
                s_cnt_p2.setText("Submarine: " + (3-submarine_btn));
                s_cnt_p2.setStyle("-fx-background-color: SEASHELL");
            }
            else{
                submarine = false;
            }
        }
    }
    public void p2_set_ship_grid(ActionEvent e) throws IOException {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Button button = new Button();
                button.setPrefSize(75, 75);
                button.setStyle("-fx-background-color: SNOW;");
                // Add event handler to change color on click
                button.setOnAction(event -> handleButtonp2(button));

                // Add button to the GridPane
                grid_p2.add(button, i, j);
                player2[i][j] = false;
            }
        }
    }
    public void turn1(ActionEvent e) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(p1_battle_controller.class.getResource("p1_battle.fxml"));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Battleship");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
