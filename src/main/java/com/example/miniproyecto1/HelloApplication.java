package com.example.miniproyecto1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class HelloApplication extends Application {
    public void start(Stage stage) throws Exception {

        Stage secondStage = new Stage(); //
        secondStage.setTitle("My Game");
        FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("/com/example/miniproyecto1/MiniProyectoInterfaz-FXML-Entrada.fxml"));
        Parent root2 = fxmlLoader2.load();
        Scene scene2 = new Scene(root2);
        secondStage.setScene(scene2);
        secondStage.show();
        /*
        stage.setTitle("My Game");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/miniproyecto1/MiniProyectoInterfaz-FXML.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
         */
    }
    public static void main(String[] args) {
        launch(args);
    }

}