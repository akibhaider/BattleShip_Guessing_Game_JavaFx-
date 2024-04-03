package org.example.lab06_2b_210041222;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.*;

public class result {
    public String p1_res;
    public String p2_res;
    public Label res_txt;
    public Button show_res;
    public Button new_game;
    public void file_clearer(String filename) throws IOException{
        try (FileWriter writer = new FileWriter(filename)) {
            // Truncate the file to zero bytes
            writer.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void readFromFile(String filename, String filename_2) throws IOException {
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            while ((line = reader.readLine()) != null) {
                p1_res = line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(filename_2))) {
            while ((line = reader.readLine()) != null) {
                p2_res = line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Integer.parseInt(p1_res) > Integer.parseInt(p2_res)) {
            res_txt.setText("Player 1 wins!");
            res_txt.setStyle("-fx-background-color: crimson");
        } else if (Integer.parseInt(p1_res) < Integer.parseInt(p2_res)) {
            res_txt.setText("Player 2 wins!");
            res_txt.setStyle("-fx-background-color: orangered");
        } else {
            res_txt.setText("Match Tied");
            res_txt.setStyle("-fx-background-color: seagreen");
        }
        file_clearer("player_1_battle.txt");
        file_clearer("player_2_battle.txt");
        file_clearer("player_1_info.txt");
        file_clearer("player_2_info.txt");
    }
    public void show_res_clicked() throws IOException {
        try {
            readFromFile("player_1_battle.txt", "player_2_battle.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void new_game_clicked(ActionEvent e) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
