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
import javafx.scene.Node;


public class entryControler {

        @FXML
        private Label principalLabel;

        @FXML
        private TextField principalTextField;

        @FXML
        private Label secondaryLabel;

        @FXML
        private Button startButton;
        //La necesidad de ese parametro, es un poco extraño, pero al parecer ese muestra cualquier evento sobre ese boton, mientras que si no lo ponemos, solo reacciona  al pulsarse de froma directa
        @FXML
        void initialize(){

        }
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
                //Cierra  la ventana donde se ubica el event, pero si ponemos cualquiera de los otros atributos, el programa funcionaria de la misma manera
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentStage.close();
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }
}


