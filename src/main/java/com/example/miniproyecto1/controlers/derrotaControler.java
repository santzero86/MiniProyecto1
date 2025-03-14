package com.example.miniproyecto1.controlers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class derrotaControler {
        @FXML
        private ImageView image1;

        @FXML
        private Label datosDerrota;

        @FXML
        private Button backButtom;

        @FXML
        public void playerName(String name,int score){
                datosDerrota.setText("Player: "+ name+ "\n" + "Tuviste 4 errores"+ "\n" + "Superaste el nivel: "+ (score-1) + "\n" + "Fallaste en el nivel: " + score );
                image1.setImage(new Image(
                        getClass().getResourceAsStream("/com/example/miniproyecto1/fases de la luna/11fe03534b2f42de8cc0a2f94d309952.jpg")
                ));
        }

        @FXML
        void regresarMenuPrincipal(ActionEvent event) {
                try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/miniproyecto1/MiniProyectoInterfaz-FXML-Entrada.fxml"));
                        Parent root = loader.load();
                        Stage stage = new Stage();
                        stage.setTitle("My Game");
                        stage.setScene(new Scene(root));
                        stage.show();
                        //Cierra  la ventana donde se ubica el event, pero si ponemos cualquiera de los otros atributos, el programa funcionaria de la misma manera
                        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        currentStage.close();
                } catch (IOException e) {
                        e.printStackTrace();
                }

        }

}
