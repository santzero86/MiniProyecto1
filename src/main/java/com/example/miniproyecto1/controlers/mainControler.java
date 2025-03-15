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
/**
 * @author Santiago Guerrero
 * @version 1.0.0
 */
public class mainControler {
    /**
     * Controles de la interfaz grafica
     */
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
    private Label errorLabel;
    /**
     * @param palabras una lista que almacena todas las palabras disponibles en el juego
     */
    private List<String> palabras = Arrays.asList(
        "manzana", "perro", "casa", "sol", "luna", "bosque", "nube", "mar", "río", "montaña",
        "cielo", "estrella", "planeta", "flor", "árbol", "hoja", "tierra", "fuego", "agua", "viento",
        "roca", "arena", "camino", "puente", "ciudad", "pueblo", "torre", "castillo", "ventana", "puerta",
        "techo", "pared", "silla", "mesa", "cama", "almohada", "espejo", "reloj", "lámpara", "sombrero",
        "zapato", "camisa", "pantalón", "chaqueta", "bufanda", "guante", "paraguas", "cinturón", "bolso", "mochila",
        "bicicleta", "coche", "tren", "avión", "barco", "moto", "camión", "autobús", "semaforo", "carretera",
        "escuela", "universidad", "biblioteca", "hospital", "farmacia", "supermercado", "mercado", "panadería", "restaurante", "hotel",
        "juguete", "muñeca", "pelota", "rompecabezas", "cometa", "dado", "cartas", "ajedrez", "dinosaurio", "robot",
        "guitarra", "piano", "violín", "tambor", "flauta", "trompeta", "micrófono", "altavoz", "disco", "radio",
        "teléfono", "televisión", "computadora", "tableta", "teclado", "ratón", "pantalla", "internet", "correo", "mensaje"
    );

    /*
    private List<String> palabras = Arrays.asList(
            "manzana", "perro", "casa", "sol", "luna", "bosque", "nube"
    );
     */
    /**
     * @param palabra variable donde se almacena la palabra que ingresa el usuario en el textField
     * @param nivel variable que determina el nivel del juego en el que se encuentra el usuario
     * @param error variable que almacena la cantidad de errores cometidos por el usuario
     * @param tiempo variable que declara los segundos iniciales del juego
     * @param timeline clase que permite realizar acciones en instancias repetitivas
     * @param playerName variable donde se almacena el nombre del usuario
     */
    String palabra;
    int nivel=0;
    int error=0;
    int tiempo= 20;
    Timeline timeline;
    String playerName;

    /**
     * Función que inicializa el temporizador por nivel y lanza un número aleatorio, además que tiene el poder para cambiar las imagenes según la cantidad de errores y desplegar la interfaz gráfica si se pierden las oportunidades
     */
    @FXML
    public void initialize() {
        mostrarPalabraAleatoria();
        iniciarTemporizador();
        inputField.setOnKeyPressed(event -> {
            if (event.getCode().toString().equals("ENTER")) {
                handleSubmit();
            }
        });
        /**
         * Segmento que controla el cambio de las imagenes según los errores
         */
        if (error == 0) {
            moonImages.setImage(new Image(
                    getClass().getResourceAsStream("/com/example/miniproyecto1/fases de la luna/Luna1.png"))
            );
        }else if(error==1) {
            moonImages.setImage(new Image(
                    getClass().getResourceAsStream("/com/example/miniproyecto1/fases de la luna/Luna5.png")
            ));
        }else if(error==2) {
            moonImages.setImage(new Image(
                    getClass().getResourceAsStream("/com/example/miniproyecto1/fases de la luna/Luna8.png")
            ));
        }else if(error==3) {
            moonImages.setImage(new Image(
                    getClass().getResourceAsStream("/com/example/miniproyecto1/fases de la luna/Luna11.png")
            ));
        }else if(error==4) {
            moonImages.setImage(new Image(
                    getClass().getResourceAsStream("/com/example/miniproyecto1/fases de la luna/Luna14.png")
            ));
        }
        /**
         * Desplega una interfaz grafica si se consigue el numero máximo de errores por partida
         */
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
                /**
                 * Función que permite traer una función desde derrotaControler
                 */
                derrotaControler derrotaController = loader.getController();
                derrotaController.playerName(playerName,(nivel+1));
                /**
                 * Toma una referencia de la ventana y cierra la actual antes de desplegar la siguiente
                 */
                Stage currentStage = (Stage) errorLabel.getScene().getWindow();
                currentStage.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Función que agrega el nombre del jugador en esta ventana
     * @param name nombre del usuario
     */
    @FXML
    public void playerName(String name){
        playerName = name;
        outputLabel3.setText("PLAYER: "+ name);
    }

    /**
     * Función que escoje una palabra random de la lista suministrada
     */
    private void mostrarPalabraAleatoria() {
        label5Output.setText(String.valueOf("Nivel: "+ (nivel+1)));
        labelOutput2.setText("La Palabra es: ");
        errorLabel.setText("Error: " + error);
        Random random = new Random();
        String palabraAleatoria = palabras.get(random.nextInt(palabras.size()));
        outputLabel.setText(palabraAleatoria);
        palabra = palabraAleatoria;
    }

    /**
     * FFuncion que controla el tiempo de los niveles
     */
    private void iniciarTemporizador() {
        if (timeline != null) {
            timeline.stop();
        }
        /**
         * Esta línea se encarga de manipular el tiempo de los niveles a partir del uso de los datos de tipo int en java, dado que al ser enteros no pueden
         * poseer decimales, por lo que si la division no es exacta, hace que los niveles tampoco cambien
         */
        tiempo = Math.max(2, 20 - (nivel / 5) * 2);
        timerLabel.setText("Tiempo: " + tiempo + "s");
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            tiempo--;
            timerLabel.setText("Tiempo: " + tiempo + "s");
            String userInput = inputField.getText();
            /**
             * Condicionales que permiten administrar la condición de derrota por tiempo límite, además que tiran mensajes
             * para informar que sucedieron
             */
            if (tiempo <= 0 && !userInput.replaceAll("\\s", "").equals(palabra.replaceAll("\\s", ""))) {
                error ++;
                timeline.stop();
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.getDialogPane().setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");
                alert.setTitle("Tiempo Acabado");
                alert.setHeaderText(null);
                alert.setContentText("Se te acabo el tiempo \n " + "Nivel: "+ (nivel+1));
                alert.show();
                inputField.clear();
                alert.setOnHidden(evento -> initialize());
            } else if(tiempo <= 0 && userInput.replaceAll("\\s", "").equals(palabra.replaceAll("\\s", ""))){
                nivel++;
                timeline.stop();
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.getDialogPane().setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");
                alert.setTitle("Tiempo Acabado");
                alert.setHeaderText(null);
                alert.setContentText("Se te acabo el tiempo\n " + "Lo lograste a tiempo " + "\n" +"Nivel Actual: "+ (nivel+1));
                alert.show();
                inputField.clear();
                alert.setOnHidden(evento -> initialize());
            }
        }));
        timeline.setCycleCount(tiempo);
        timeline.play();
    }

    /**
     * Función asociada al evento del botón, esta permite subir de niveles si la palabra es igual o ganar errores si esta es diferente
     */
    @FXML
    private void handleSubmit() {
        String userInput = inputField.getText();
        if (!userInput.replaceAll("\\s", "").equals(palabra.replaceAll("\\s", ""))) {
            error++;
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
            inputField.clear();
            delay.play();
        } else if(userInput.replaceAll("\\s", "").equals(palabra.replaceAll("\\s", ""))) {
            if (timeline != null) {
                timeline.stop();
            }
            nivel++;
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.getDialogPane().setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");
            alert.setTitle("Victoria");
            alert.setHeaderText(null);
            alert.setContentText("Subes de Nivel \n " + "Nivel: "+ (nivel+1));
            alert.show();
            inputField.clear();
            alert.setOnHidden(event -> initialize());
        }
    }
}

