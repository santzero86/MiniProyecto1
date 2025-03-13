package com.example.miniproyecto1.controlers;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;

public class derrotaControler {
        @FXML
        private ImageView image1;

        @FXML
        private Label datosDerrota;

        @FXML
        public void playerName(String name,int score){
                datosDerrota.setText("Player: "+ name+ "\n" + "Tuviste 3 errores"+ "\n" + "Superaste el nivel: "+ score );

        }
}
