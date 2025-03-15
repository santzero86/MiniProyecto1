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
/**
 * @author Santiago Guerrero
 * @version 1.0.0
 */
public class derrotaControler {
        /**
         * Controles de la interfaz gráfica
         */
        @FXML
        private ImageView image1;

        @FXML
        private Label datosDerrota;

        @FXML
        private Button backButtom;

        /**
         *
         * @param name Nombre del usuario ingresado por medio del textField
         * @param score Niveles completados
         */
        @FXML
        public void playerName(String name,int score){
                datosDerrota.setText("Player: "+ name+ "\n" + "Tuviste 4 errores"+ "\n" + "Superaste el nivel: "+ (score-1) + "\n" + "Fallaste en el nivel: " + score );
                image1.setImage(new Image(
                        getClass().getResourceAsStream("/com/example/miniproyecto1/fases de la luna/Luna14.png")
                ));
        }
        /**
         * Función diseñada para mantener la recursividad, al cerrar esta ventana se vuelve a abrir la ventana de entrada, de forma que el usuario puede volver a jugar
         * @param event evento asociado al boton
         */
        @FXML
        void regresarMenuPrincipal(ActionEvent event) {
                try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/miniproyecto1/MiniProyectoInterfaz-FXML-Entrada.fxml"));
                        Parent root = loader.load();
                        Stage stage = new Stage();
                        stage.setTitle("My Game");
                        stage.setScene(new Scene(root));
                        stage.show();
                        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        currentStage.close();
                } catch (IOException e) {
                        e.printStackTrace();
                }

        }

}
