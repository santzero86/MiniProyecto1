module com.example.miniproyecto1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.miniproyecto1 to javafx.fxml;
    exports com.example.miniproyecto1;
    exports com.example.miniproyecto1.controlers;
    opens com.example.miniproyecto1.controlers to javafx.fxml;
}