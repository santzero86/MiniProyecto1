package com.example.miniproyecto1.controlers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import com.example.miniproyecto1.controlers.mainControler;

public class entryControler {

        @FXML
        private Label principalLabel;

        @FXML
        private TextField principalTextField;

        @FXML
        private Label secondaryLabel;

        @FXML
        private Button startButton;

        @FXML
        void buttonStartGame(ActionEvent event) {

                try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/miniproyecto1/MiniProyectoInterfaz-FXML.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setTitle("My Game");
                stage.setScene(new Scene(root));
                stage.show();
                mainControler mainController = loader.getController();
                String text = principalTextField.getText();
                mainController.playerName(text);
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }
}


