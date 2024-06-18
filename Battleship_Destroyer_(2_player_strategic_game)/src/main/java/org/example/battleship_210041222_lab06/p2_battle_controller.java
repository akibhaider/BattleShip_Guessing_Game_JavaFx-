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

import static org.example.battleship_210041222_lab06.p_1_set_ship_controller.player1;

public class p2_battle_controller {
    p1_battle_controller p1_battle_copy = new p1_battle_controller();
    p_1_set_ship_controller p1_set_ship_copy = new p_1_set_ship_controller();
    p_2_set_ship_controller p2_set_ship_copy = new p_2_set_ship_controller();
    @FXML
    public GridPane grid_t2;
    static public int[][]play2 = new int[9][9];

    static public boolean t1;
    static public boolean t2;
    static public int move2 = 0;
    static public int hit2 = 0;
    public Label m2;
    public Label b2;
    public int button_no = 6;
    public Button turn1;
    public boolean battle = true;
    public int battle_btn = 0;
    public int destroy_btn = 0;
    public int d_num = 0;
    public int submarine_btn = 0;
    public boolean destroy = false;
    public boolean submarine = false;
    public void bool_reset(){
        battle = true;
        battle_btn = 0;
        destroy_btn = 0;
        d_num = 0;
        submarine_btn = 0;
        destroy = false;
        submarine = false;
    }
    public void game_over(ActionEvent e,String file) throws  IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(file));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Battleship");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public void handleButtont2(Button b){
        if(t1){
            return;
        }
        int row = grid_t2.getRowIndex(b);
        int col = grid_t2.getColumnIndex(b);
        move2++;
        m2.setText("Move count: "+move2);
        if(!player1[row][col]){
            b.setStyle("-fx-background-color: black;");
            play2[row][col] = 1;
        }
        else if(player1[row][col]){
            b.setStyle("-fx-background-color: seagreen;");
            play2[row][col] = 2;
            hit2++;
            b2.setText("Buttons left: "+(button_no-hit2));
            if(hit2 == button_no){
                turn1.setText("END");
                turn1.setOnAction(event -> {
                    try {
                        game_over(event, "game_over2.fxml");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        }
        t1 = true;
        t2 = false;
    }
    public void turn1(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(p1_battle_controller.class.getResource("p1_battle.fxml"));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Battleship");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public void turn2_board(ActionEvent e) throws IOException{
        bool_reset();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Button button = new Button();
                button.setPrefSize(70, 70);

                // Add event handler to change color on click
                button.setOnAction(event ->
                        handleButtont2(button)
                );
                if(play2[i][j] == 1){
                    button.setStyle("-fx-background-color: black;");
                } else if (play2[i][j] == 2) {
                    button.setStyle("-fx-background-color: seagreen;");
                }
                // Add button to the GridPane
                grid_t2.add(button, j, i);
            }
        }
        t1 = false;
        t2 = true;
        m2.setText("Move count: " + move2);
        b2.setText("Buttons left: "+(button_no-hit2));
    }
}
