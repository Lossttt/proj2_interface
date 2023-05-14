package com.example.hhs_proj2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SignupController {

    @FXML
    private TextField emailField;

    @FXML
    private TextField idField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Button signupButton;

    @FXML
    private void initialize() {
        // Add any initialization code if needed
    }

    @FXML
    private void registerUser(ActionEvent event) {
        String email = emailField.getText();
        String userID = idField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (!password.equals(confirmPassword)) {
            showAlert("Registration Failed", "Passwords do not match", "Please make sure the passwords match.");
            return;
        }

        User user = new User(email, userID, password);

        // Save user data to a file or database
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true))) {
            writer.write(user.getEmail() + "," + user.getUserID()+ "," + user.getPassword() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error!", "Failed to register user", "An error occurred while registering the user...");
            return;
        }

        showAlert("Success", "User Registered", "User registration successful!");
        openLoginForm();
    }

    private void openLoginForm() {
        try {
            // Load the FXML of the login form
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login2.fxml"));
            Parent root = loader.load();

            // Create a new stage for the login form
            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setScene(new Scene(root));
            stage.show();

            // Close the current registration form
            Stage currentStage = (Stage) signupButton.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception or display an error message
        }
    }


    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
