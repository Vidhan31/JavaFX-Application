package com.javafxapp.javafxapplication;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorLabel;
    @FXML
    private TextField newEmailField;
    @FXML
    private TextField newUsernameField;
    @FXML
    private PasswordField newPasswordField;
    @FXML
    private Label signupLabel;

    @FXML
    protected void checkRegistration() {

        if (newEmailField.getText().contains("@") && newEmailField.getText().length() > 5) {

            signupLabel.setText("Successfully registered!");
            signupLabel.setTextFill(Color.GREEN);
            signupLabel.setAlignment(Pos.CENTER);
        } else if (!newEmailField.getText().isEmpty()){
            passwordField.clear();
            signupLabel.setText("Please enter valid email ID!");
            signupLabel.setTextFill(Color.RED);
            signupLabel.setAlignment(Pos.CENTER);
        }

        if (newUsernameField.getText().isEmpty() || newPasswordField.getText().isEmpty()) {

            passwordField.clear();
            signupLabel.setText("Fill in required details!");
            signupLabel.setTextFill(Color.RED);
            signupLabel.setAlignment(Pos.CENTER);
        }
    }

    @FXML
    protected void checkLogin() throws IOException {

        if (usernameField.getText().equals("111") && passwordField.getText().equals("111")) {
            errorLabel.setText("Success!");
            errorLabel.setTextFill(Color.GREEN);
            errorLabel.setAlignment(Pos.CENTER);
            Main application = new Main();
            application.changeScene("main-page.fxml", 900, 900);
        } else if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            passwordField.clear();
            errorLabel.setText("Fields cannot be empty!");
            errorLabel.setTextFill(Color.ORANGERED);
            errorLabel.setAlignment(Pos.CENTER);
        } else {
            passwordField.clear();
            errorLabel.setText("Wrong credentials!");
            errorLabel.setTextFill(Color.RED);
            errorLabel.setAlignment(Pos.CENTER);
        }
    }

    @FXML
    protected void clearAllFields() {

        usernameField.clear();
        passwordField.clear();
    }
}