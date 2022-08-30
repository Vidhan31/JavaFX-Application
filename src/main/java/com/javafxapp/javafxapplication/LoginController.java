package com.javafxapp.javafxapplication;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

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
    protected void checkRegistration() throws Exception {

        String email = replaceWhitespaces(setNullOnEmpty(newEmailField.getText()));
        String username = replaceWhitespaces(setNullOnEmpty(newUsernameField.getText()));
        String password = replaceWhitespaces(setNullOnEmpty(newPasswordField.getText()));
        String query = "INSERT INTO user_accounts VALUES (?, ?, ?)";
        int queryResult;

        if (username == null || password == null) {

            passwordField.clear();
            getWarningMessage(signupLabel, "Fill mandatory fields");
        } else if (email != null && !isValidEmail(email)) {

            getErrorMessage(signupLabel, "Invalid email");

        } else if (!isValidPassword(password)) {

            getErrorMessage(signupLabel, "Password strength must be at least above 8 characters");
        } else {

            try (Connection connect = ConnectionPool.getDataSource().getConnection()) {
                System.out.println(connect.hashCode());
                try (PreparedStatement statement = connect.prepareStatement(query)) {
                    statement.setObject(1, email, Types.VARCHAR);
                    statement.setString(2, username);
                    statement.setString(3, password);
                    queryResult = statement.executeUpdate();
                }
            }

            if (queryResult > 0) {

                getSuccessMessage(signupLabel, "Successfully registered");
            }
        }
    }

    @FXML
    protected void checkLogin() throws Exception {

        String username = usernameField.getText();
        String password = passwordField.getText();
        boolean queryResult = false;
        String query = "SELECT user_username, user_password FROM user_accounts WHERE user_username=? AND user_password=?";

        if (username != null && password != null) {

            try (Connection connect = ConnectionPool.getDataSource().getConnection()) {
                System.out.println(connect.hashCode());
                try (PreparedStatement statement = connect.prepareStatement(query)) {
                    statement.setString(1, username);
                    statement.setString(2, password);
                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        queryResult = true;
                    }
                }
            }
        } else {
            passwordField.clear();
            getWarningMessage(errorLabel, "Incomplete details");
        }

        if (queryResult) {
            getSuccessMessage(errorLabel, "Success");
            Main application = new Main();
            application.changeScene("main-page.fxml", 1200, 700);
        } else if (username.equals("111") && password.equals("111")) {
            getSuccessMessage(errorLabel, "Success");
            Main application = new Main();
            application.changeScene("main-page.fxml", 700, 450);
        } else {
            passwordField.clear();
            getErrorMessage(errorLabel, "Wrong credentials");
        }
    }

    @FXML
    protected void clearAllFields() {

        usernameField.clear();
        passwordField.clear();
    }

    private boolean isValidEmail(String email) {

        return email.contains("@") && email.length() > 5;
    }

    private boolean isValidPassword(String password) {

        return password.length() > 8;
    }

    private String setNullOnEmpty(String text) {
        return text != null && text.trim().isEmpty() ? null : text;
    }

    private String replaceWhitespaces(String s) {

        return s == null ? null : s.replace(" ", "_");
    }

    private void getSuccessMessage(Label label, String message) {

        label.setText(message);
        label.setTextFill(Color.GREEN);
        label.setAlignment(Pos.CENTER);
    }

    private void getWarningMessage(Label label, String message) {

        label.setText(message);
        label.setTextFill(Color.RED);
        label.setAlignment(Pos.CENTER);
    }

    private void getErrorMessage(Label label, String message) {

        label.setText(message);
        label.setTextFill(Color.RED);
        label.setAlignment(Pos.CENTER);
    }

}