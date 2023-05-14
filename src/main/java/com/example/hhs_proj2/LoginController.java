package com.example.hhs_proj2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Hyperlink;


public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private CheckBox showPasswordCheckbox;

    @FXML
    private Hyperlink createAccountLink;



    @FXML
    private void initialize() {
        showPasswordCheckbox.setOnAction(event -> {
            if (showPasswordCheckbox.isSelected()) {
                passwordField.setPromptText(passwordField.getText());
                passwordField.setText("");
            } else {
                passwordField.setText(passwordField.getPromptText());
                passwordField.setPromptText("");
            }
        });

        createAccountLink.setOnAction(event -> {
            try {
                createAccount();
            } catch (IOException e) {
                e.printStackTrace();
                showAlert("Error!", "Failed to load the scene", "An error occurred while loading the signup scene...");
            }
        });
    }

    @FXML
    private void userLogIn() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (!isValidLogin(username, password)) {
            // show error message
            showAlert("Login Failed", "Invalid Username or Password", "Please try again.");
        }
    }

    private boolean isValidLogin(String username, String password) throws IOException {
        // Read the registered user data from the file or database
        List<User> registeredUsers = readRegisteredUsers();

        // Check if the user details are correct
        for (User user : registeredUsers) {
            if ((user.getEmail().equals(username) || user.getUserID().equals(username))
                    && user.getPassword().equals(password)) {
                // User login successful
                System.out.println("User login successful! :)");
//                showUserScene();
                showAlert("Nice work bro", "GGs", ":D");
                return true;
            }
        }

        // Invalid login credentials
        return false;
    }


    private List<User> readRegisteredUsers() throws IOException {
        List<User> registeredUsers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length == 3) {
                    String email = userData[0];
                    String password = userData[1];
                    String userID = userData[2];
                    User user = new User(email, password, userID);
                    registeredUsers.add(user);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception or display an error message
        }

        return registeredUsers;
    }




    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void showUserScene() throws IOException {
        try {
            // Load the FXML of the next scene for the admin
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminScene.fxml"));
            Parent root = loader.load();

            // Create a new stage for the next scene
            Stage stage = new Stage();
            stage.setTitle("Admin Scene");
            stage.setScene(new Scene(root));
            stage.show();

            // Close the current login scene
            Stage currentStage = (Stage) loginButton.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error!", "Failed to load the scene", "An error occurred while loading the next scene...");
        }
    }

    @FXML
    private void createAccount() throws IOException {
        // Load the FXML of the signup scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("signup.fxml"));
        Parent root = loader.load();

        // Create a new stage for the signup scene
        Stage stage = new Stage();
        stage.setTitle("Sign Up");
        stage.setResizable(false);
        stage.setScene(new Scene(root,800,600));
        stage.show();

        // Close the current login scene
        Stage currentStage = (Stage) createAccountLink.getScene().getWindow();
        currentStage.close();
    }
}
