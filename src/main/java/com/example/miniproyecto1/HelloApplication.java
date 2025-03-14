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

/**
 * initializer class of all the program
 * @author Santiago Guerrero
 * @version 1.0.0
 *
 */
public class HelloApplication extends Application {
    /**
     * @param stage ventana principal del programa
     * @throws Exception lanza una excepcion si hay errores al cargar el archivos fxml
     */
    public void start(Stage stage) throws Exception {
        stage.setTitle("My Game");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/miniproyecto1/MiniProyectoInterfaz-FXML-Entrada.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

}