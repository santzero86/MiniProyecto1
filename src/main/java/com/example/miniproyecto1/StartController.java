package com.example.miniproyecto1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class StartController {

    @FXML
    private Button submitButton;

    @FXML
    private TextField inputField;

    @FXML
    private Label outputLabel;

    @FXML
    private void handleSubmit() {
        String userInput = inputField.getText();
        outputLabel.setText("Ingresaste: " + userInput);
    }
}

