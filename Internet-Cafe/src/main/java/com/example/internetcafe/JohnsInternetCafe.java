package com.example.internetcafe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class JohnsInternetCafe extends Application {
    // The start method is the entry point of the JavaFX application.
    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
        Parent root = loader.load();

        // Title of the primary stage
        primaryStage.setTitle("Jhon’s Internet Café");
        Image favicon =new Image("file:IMAGES\\logo.png");
        primaryStage.getIcons().add(favicon);

        // Create a new Scene with the root node and set its dimensions.
        Scene scene = new Scene(root, 618, 454);
        // Set the created Scene as the content of the primary stage.
        primaryStage.setScene(scene);
        // Display the primary stage.
        primaryStage.show();
    }

    // Launch the JavaFX application.
    public static void main(String[] args) {
        launch(args);
    }

}
