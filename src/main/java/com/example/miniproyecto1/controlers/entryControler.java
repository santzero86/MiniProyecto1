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
/**
 * @author Santiago Guerrero
 * @version 1.0.0
 */

public class entryControler {
        /**
         * Controles de la interfaz grafica
         */
        @FXML
        private Label principalLabel;

        @FXML
        private TextField principalTextField;

        @FXML
        private Label secondaryLabel;

        @FXML
        private Button startButton;
        /**
         * Metodo diseñado para lograr que el evento relacionado al enter desde teclado
         * se ejecute como el boton diseñado para la ejecucion de la interfaz grafica
         */
        @FXML
        void initialize(){
                principalTextField.setOnKeyPressed(event -> {
                        if (event.getCode().toString().equals("ENTER")) {
                                buttonStartGame(new ActionEvent(startButton, null));
                        }
                });
        }
        /**
         * @param event este parametro sirve para relacionar otros eventos al boton
         */
        @FXML
        public void buttonStartGame(ActionEvent event) {
                /**
                 * Este segmento de codigo permite que al presionar el evento de boton se despliegue mainControler, que es
                 * la interfaz principal del proyecto
                 */
                try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/miniproyecto1/MiniProyectoInterfaz-FXML.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setTitle("My Game");
                stage.setScene(new Scene(root));
                stage.show();
                /**
                 * @param text Texto ingresado por el usuario
                 * @see playerName(String)
                 */
                mainControler mainController = loader.getController();
                String text = principalTextField.getText();
                mainController.playerName(text);
                /**
                 * @param currenStage Se crea un stage que obtiene de event una referencia de la ventana en la que este se ubica, para despues cerrarla con close()
                 * @see close() funcion para cerrar ventanas
                 */
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentStage.close();
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }
}


