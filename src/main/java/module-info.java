module edu.uwplatt.team05 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens edu.uwplatt.team05 to javafx.fxml;
    exports edu.uwplatt.team05;
    exports edu.uwplatt.team05.utils;
    opens edu.uwplatt.team05.utils to javafx.fxml;
    exports edu.uwplatt.team05.pieces;
    opens edu.uwplatt.team05.pieces to javafx.fxml;
    //exports edu.uwplatt.team05.screens;
    //opens edu.uwplatt.team05.screens to javafx.fxml;
    //exports edu.uwplatt.team05.controllers;
    //opens edu.uwplatt.team05.controllers to javafx.fxml;
}