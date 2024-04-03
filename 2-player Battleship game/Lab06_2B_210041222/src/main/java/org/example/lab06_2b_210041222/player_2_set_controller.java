package org.example.lab06_2b_210041222;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class player_2_set_controller {
    private Stage stage;
    public String tmp;
    public ArrayList<String> p2_ls=new ArrayList<String>();
    public Label res_p2;
    public int cnt_p2=10;
    public int atmp_p2=0;
    private Scene scene;
    public Label allign_stat_2;
    @FXML
    public int p2_type;
    @FXML
    public int p2_allign;
    public int p2_cnt_submarine=0;
    public int p2_cnt_battleship=0;
    public int p2_cnt_destroyer=0;
    public Button player_2_button_11; public Button player_2_button_12; public Button player_2_button_13; public Button player_2_button_14; public Button player_2_button_15; public Button player_2_button_16; public Button player_2_button_17; public Button player_2_button_18; public Button player_2_button_19;
    public Button player_2_button_21; public Button player_2_button_22; public Button player_2_button_23; public Button player_2_button_24; public Button player_2_button_25; public Button player_2_button_26; public Button player_2_button_27; public Button player_2_button_28; public Button player_2_button_29;
    public Button player_2_button_31; public Button player_2_button_32; public Button player_2_button_33; public Button player_2_button_34; public Button player_2_button_35; public Button player_2_button_36; public Button player_2_button_37; public Button player_2_button_38; public Button player_2_button_39;
    public Button player_2_button_41; public Button player_2_button_42; public Button player_2_button_43; public Button player_2_button_44; public Button player_2_button_45; public Button player_2_button_46; public Button player_2_button_47; public Button player_2_button_48; public Button player_2_button_49;
    public Button player_2_button_51; public Button player_2_button_52; public Button player_2_button_53; public Button player_2_button_54; public Button player_2_button_55; public Button player_2_button_56; public Button player_2_button_57; public Button player_2_button_58; public Button player_2_button_59;
    public Button player_2_button_61; public Button player_2_button_62; public Button player_2_button_63; public Button player_2_button_64; public Button player_2_button_65; public Button player_2_button_66; public Button player_2_button_67; public Button player_2_button_68; public Button player_2_button_69;
    public Button player_2_button_71; public Button player_2_button_72; public Button player_2_button_73; public Button player_2_button_74; public Button player_2_button_75; public Button player_2_button_76; public Button player_2_button_77; public Button player_2_button_78; public Button player_2_button_79;
    public Button player_2_button_81; public Button player_2_button_82; public Button player_2_button_83; public Button player_2_button_84; public Button player_2_button_85; public Button player_2_button_86; public Button player_2_button_87; public Button player_2_button_88; public Button player_2_button_89;
    public Button player_2_button_91; public Button player_2_button_92; public Button player_2_button_93; public Button player_2_button_94; public Button player_2_button_95; public Button player_2_button_96; public Button player_2_button_97; public Button player_2_button_98; public Button player_2_button_99;

    public boolean readFromFile(String filename, String tmp) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if(Objects.equals(tmp, line)){
                    System.out.println(line);
                    cnt_p2-=1;
                    return true;
                }
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static void appendToFile(String content, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(content);
            writer.newLine();  // Add a new line after each appended content
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void switchToStartBattle(ActionEvent e) throws IOException{
        try {
            Parent root = FXMLLoader.load(getClass().getResource("player_2_battle.fxml"));
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    @FXML
    public void p_2_submarine_clicked(){
        p2_type=1;
    }
    @FXML
    public void p_2_destroyer_clicked(){
        p2_type=2;
    }
    @FXML
    public void p_2_battleship_clicked(){
        p2_type=3;
    }
    public void p_2_horizontal_clicked(){
        p2_allign=1;
        allign_stat_2.setText("Horizontal");
    }
    public void p_2_vertical_clicked(){
        p2_allign=2;
        allign_stat_2.setText("Vertical");
    }
    public void p_2_grid_button_clicked(ActionEvent event){
        if(p2_allign==1){
            allign_stat_2.setText("Horizontal");
        }else{
            allign_stat_2.setText("Vertical");
        }
        Button btn = (Button) event.getSource();
        String id = btn.getId();
        if(p2_cnt_destroyer % 2 ==1){
            if(Objects.equals(id, tmp)){
                p2_cnt_destroyer+=1;
                btn.setStyle("-fx-background-color: deeppink");
                appendToFile(id, "player_2_info.txt");
            }
        } else if (p2_cnt_battleship % 3 !=0 && p2_cnt_battleship<3) {
            if(Objects.equals(id, tmp)){
                p2_cnt_battleship+=1;
                btn.setStyle("-fx-background-color: deeppink");
                appendToFile(id, "player_2_info.txt");
                if(p2_allign==1) {
                    char c = id.charAt(id.length() - 1);
                    c = (char) (c + 1);
                    StringBuffer sb = new StringBuffer(id);
                    sb.deleteCharAt(id.length() - 1);
                    id = sb.toString();
                    String n = String.valueOf(c);
                    tmp = id + n;
                }else{
                    char c=id.charAt(id.length()-1);
                    char c1=id.charAt(id.length()-2);
                    c1 = (char) (c1 + 1);
                    StringBuffer sb= new StringBuffer(id);
                    sb.deleteCharAt(id.length()-1);
                    sb.deleteCharAt(id.length()-2);
                    id=sb.toString();
                    String n1=String.valueOf(c1);
                    String n=String.valueOf(c);
                    tmp=id+n1+n;
                }
            }
        } else if(p2_type==1 && p2_allign<3 && p2_cnt_submarine<3){
            btn.setStyle("-fx-background-color: deeppink");
            appendToFile(id, "player_2_info.txt");
            p2_cnt_submarine+=1;
        }else if(p2_type==2 && p2_allign==1 && p2_cnt_destroyer<4){
            btn.setStyle("-fx-background-color: deeppink");
            appendToFile(id, "player_2_info.txt");
            System.out.println(p2_cnt_destroyer);
            char c=id.charAt(id.length()-1);
            c = (char) (c + 1);
            StringBuffer sb= new StringBuffer(id);
            sb.deleteCharAt(id.length()-1);
            id=sb.toString();
            String n=String.valueOf(c);
            tmp=id+n;
            p2_cnt_destroyer+=1;
        }else if(p2_type==2 && p2_allign==2  && p2_cnt_destroyer<4){
            btn.setStyle("-fx-background-color: deeppink");
            appendToFile(id, "player_2_info.txt");
            System.out.println(p2_cnt_destroyer);
            char c=id.charAt(id.length()-1);
            char c1=id.charAt(id.length()-2);
            c1 = (char) (c1 + 1);
            StringBuffer sb= new StringBuffer(id);
            sb.deleteCharAt(id.length()-1);
            sb.deleteCharAt(id.length()-2);
            id=sb.toString();
            String n1=String.valueOf(c1);
            String n=String.valueOf(c);
            tmp=id+n1+n;
            p2_cnt_destroyer+=1;
        }else if(p2_type==3 && p2_allign==1 && p2_cnt_battleship<3){
            btn.setStyle("-fx-background-color: deeppink");
            appendToFile(id, "player_2_info.txt");
            char c=id.charAt(id.length()-1);
            c = (char) (c + 1);
            StringBuffer sb= new StringBuffer(id);
            sb.deleteCharAt(id.length()-1);
            id=sb.toString();
            String n=String.valueOf(c);
            tmp=id+n;
            p2_cnt_battleship+=1;
        }else if(p2_type==3 && p2_allign==2 && p2_cnt_battleship<3){
            btn.setStyle("-fx-background-color: deeppink");
            appendToFile(id, "player_2_info.txt");
            char c=id.charAt(id.length()-1);
            char c1=id.charAt(id.length()-2);
            c1 = (char) (c1 + 1);
            StringBuffer sb= new StringBuffer(id);
            sb.deleteCharAt(id.length()-1);
            sb.deleteCharAt(id.length()-2);
            id=sb.toString();
            String n1=String.valueOf(c1);
            String n=String.valueOf(c);
            tmp=id+n1+n;
            p2_cnt_battleship+=1;
        }
    }
    public void switchToP2BattleScene_(ActionEvent e) throws IOException{
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Result.fxml"));
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    public void p1_battle_grid_clicked (ActionEvent event){
        Button btn = (Button) event.getSource();
        String tmp=btn.getId();
        if(readFromFile("player_2_info.txt", tmp)){
            btn.setStyle("-fx-background-color: navy");
        }else {
            btn.setStyle("-fx-background-color: seagreen");
        }
        atmp_p2+=1;
        res_p2.setText(cnt_p2 + "ships left");
        if(cnt_p2==0){
            res_p2.setText("Completed!");
            appendToFile(String.valueOf(atmp_p2), "player_2_battle.txt");
        }
    }
}
