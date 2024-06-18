module org.example.battleship_210041222_lab06 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.battleship_210041222_lab06 to javafx.fxml;
    exports org.example.battleship_210041222_lab06;
}