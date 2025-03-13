package com.example.miniproyecto1.controlers;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.Time;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import javafx.scene.control.ButtonType;
import com.example.miniproyecto1.controlers.derrotaControler;


public class mainControler {

    @FXML
    private Button submitButton;

    @FXML
    private TextField inputField;

    @FXML
    private Label outputLabel;

    @FXML
    private Label labelOutput2;

    @FXML
    private ImageView moonImages;

    @FXML
    private Label outputLabel3;

    @FXML
    private Label timerLabel;

    @FXML
    private Label label5Output;

    @FXML
    private ImageView errorMoon1;

    @FXML
    private ImageView errorMoon2;

    @FXML
    private ImageView errorMoon3;

    @FXML
    private ImageView errorMoon4;

    @FXML
    private Label errorLabel;

    private List<String> palabras = Arrays.asList(
            "manzana", "perro", "casa", "montaña", "sol", "luna", "avión", "río", "bosque", "nube"
    );

    String palabra;
    int nivel=0;
    int error=0;
    int tiempo= 20;
    Timeline timeline;

    @FXML
    public void initialize() {
        mostrarPalabraAleatoria();
        iniciarTemporizador();
        //submitButton.setOnAction(event -> handleSubmit());
        inputField.setOnKeyPressed(event -> {
            if (event.getCode().toString().equals("ENTER")) {
                handleSubmit();
            }
        });
        if(error==4){
            if (timeline != null) {
                timeline.stop();
            }
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/miniproyecto1/MiniProyectoInterfaz-FXML-MensajeDerrota.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setTitle("My Game");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    @FXML
    public void playerName(String name){
        outputLabel3.setText("PLAYER: "+ name);
    }

    private void mostrarPalabraAleatoria() {
        if(tiempo == 20){
            moonImages.setImage(new Image(
                    getClass().getResourceAsStream("/com/example/miniproyecto1/fases de la luna/Luna1.png"))
            );
        }

        label5Output.setText(String.valueOf("Nivel: "+ (nivel+1)));
        labelOutput2.setText("La Palabra es: ");
        Random random = new Random();
        String palabraAleatoria = palabras.get(random.nextInt(palabras.size()));
        outputLabel.setText(palabraAleatoria);
        palabra = palabraAleatoria;
    }

    private void iniciarTemporizador() {
        if (timeline != null) {
            timeline.stop();
        }
        tiempo = Math.max(2, 20 - (nivel / 5) * 2);
        //String newText= timerLabel.getText();
        //newText = newText + tiempo + "s";
        timerLabel.setText("Tiempo: " + tiempo + "s");
        //timerLabel.setText(newText);
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            tiempo--;
            timerLabel.setText("Tiempo: " + tiempo + "s");

            if (tiempo <= 0 && error < 4 ) {
                timeline.stop();
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.getDialogPane().setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");
                alert.setTitle("Tiempo Acabado");
                alert.setHeaderText(null);
                alert.setContentText("Se te acabo el tiempo \n " + "Nivel: "+ (nivel+1));
                alert.show();
                //mostrarMensaje("¡Tiempo agotado! ", false);
            }
        }));
        timeline.setCycleCount(tiempo);
        timeline.play();
    }

    @FXML
    private void handleSubmit() {
        String userInput = inputField.getText();
        /*
        if(!userInput.replaceAll("\\s", "").equals(palabra.replaceAll("\\s", "")) && error==4){
            if (timeline != null) {
                timeline.stop();
            }
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/miniproyecto1/MiniProyectoInterfaz-FXML-MensajeDerrota.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setTitle("My Game");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        */
        if (!userInput.replaceAll("\\s", "").equals(palabra.replaceAll("\\s", ""))) {
            //Alerta de tipo ERROR
            error++;
            errorLabel.setText("Error: " + error);
            System.out.println(error);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.getDialogPane().lookupButton(ButtonType.OK).setVisible(false);
            alert.getDialogPane().setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");
            alert.setTitle("Incorrecto");
            alert.setHeaderText(null);
            alert.setContentText("¡ERROR! Intentalo de nuevo ");
            alert.show();
            PauseTransition delay = new PauseTransition(Duration.seconds(2.0));
            delay.setOnFinished(event -> {
                alert.close();
                initialize();
            });
            delay.play();
        } else if(userInput.replaceAll("\\s", "").equals(palabra.replaceAll("\\s", ""))) {
            if (timeline != null) {
                timeline.stop();
            }
            //timeline.stop();
            nivel++;
            //error= 0;
            Alert alert = new Alert(AlertType.INFORMATION);
            //alert.getDialogPane().lookupButton(ButtonType.OK).setVisible(false);
            alert.getDialogPane().setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");
            alert.setTitle("Victoria");
            alert.setHeaderText(null);
            alert.setContentText("Subes de Nivel \n " + "Nivel: "+ (nivel+1));
            alert.show();
            //timeline.stop();
            //PauseTransition delay = new PauseTransition(Duration.seconds(0.5));
            //delay.setOnFinished(event -> alert.close());
            //delay.play();

            //initialize();
            alert.setOnHidden(event -> initialize());
        }
    }
}

