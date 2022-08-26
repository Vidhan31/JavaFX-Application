package com.javafxapp.javafxapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    private static Stage secondaryStage;

    @Override
    public void start(Stage stage) throws IOException {

        secondaryStage = stage;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login-page.fxml")));

        stage.setTitle("Authorization");
        stage.setResizable(false);
        stage.setHeight(450);
        stage.setWidth(700);
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void changeScene(String fxml, int width, int height) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml)));
        secondaryStage.getScene().setRoot(root);
        secondaryStage.setHeight(height);
        secondaryStage.setWidth(width);
        secondaryStage.setResizable(false);
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        secondaryStage.setX((primScreenBounds.getWidth() - secondaryStage.getWidth()) / 2);
        secondaryStage.setY((primScreenBounds.getHeight() - secondaryStage.getHeight()) / 2);
    }

    public static void main(String[] args) {

        launch();
    }
}