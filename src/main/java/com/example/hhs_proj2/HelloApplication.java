package com.example.hhs_proj2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login2.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);

        // Voeg css stylesheet toe
        scene.getStylesheets().add(getClass().getResource("/com/example/hhs_proj2/stylesheet.css").toExternalForm());
        // Gebruiker kan de windowgrootte niet aanpassen
        // ..
        stage.setResizable(false);

        stage.setTitle("Welcome to 42's AI assistant!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}