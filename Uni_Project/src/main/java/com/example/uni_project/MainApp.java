package com.example.uni_project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    private StackPane root;
    private Scene scene;

    @Override
    public void start(Stage primaryStage) throws IOException {
        root = new StackPane();
        MainLayout mainLayout = new MainLayout(this); // Pass MainApp instance
        root.getChildren().add(mainLayout.getPane());

        scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("University System");
        primaryStage.show();
    }

    public void switchLayout(BorderPane newLayout) {
        root.getChildren().clear();
        root.getChildren().add(newLayout);
    }

    public static void main(String[] args) {
     launch();
    }
}