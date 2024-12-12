package com.example.demo;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class MainLayout {
    private BorderPane pane;
    private MainApp app; // Reference to MainApp

    // Pass MainApp to MainLayout
    public MainLayout(MainApp app) {
        this.app = app; // Assign MainApp instance
        pane = new BorderPane();
        pane.setStyle("-fx-background-color:#C8A97D");

        VBox centrepart = main();
        pane.setCenter(centrepart);
    }

    private VBox main() {
        Image sideimage=new Image(this.getClass().getResource("/oop images.jpg").toExternalForm());
        ImageView imageView = new ImageView(sideimage);
        imageView.setFitWidth(600);
        imageView.setFitHeight(750);

        // Create curves for the image
        Rectangle clip = new Rectangle(600, 750);
        clip.setArcHeight(30);
        clip.setArcWidth(30);

        imageView.setClip(clip);
        VBox imagebox = new VBox();
        imagebox.getChildren().addAll(imageView);
        imagebox.setPadding(new Insets(20, 0, 0, 20));
        pane.setLeft(imagebox);

        // Create form components
        Label type = new Label("Type");
        type.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 15));
        type.setPadding(new Insets(5, 0, 0, 0));

        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("Student", "Faculty", "Admin");

        HBox typebox = new HBox();
        typebox.getChildren().addAll(type, comboBox);
        typebox.setSpacing(10);

        Label Name = new Label("USERNAME");
        Name.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 15));
        TextField textField = new TextField();


        Label Pasword = new Label("PASSWORD");
        Pasword.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 15));
        PasswordField passwordField = new PasswordField();

        textField.setPrefHeight(25); // Fixed width for the TextField
        passwordField.setPrefHeight(25);

        Button login = new Button("LOGIN");
        login.setPadding(new Insets(10, 10, 10, 10));
        login.setFont(Font.font("Arial", FontPosture.ITALIC, 15));

        Button register = new Button("REGISTER");
        register.setFont(Font.font("Arial", FontPosture.ITALIC, 15));
        register.setPadding(new Insets(10, 10, 10, 10));

        HBox button = new HBox();
        button.getChildren().addAll(login, register);
        button.setSpacing(20);

        // Set the Login button's action
        login.setOnAction(e -> {
            String selectedType = comboBox.getValue();
            if ("Admin".equals(selectedType)) {
                AdminDashboard adminPortal=new AdminDashboard();
                if (app != null) {
                    app.switchLayout(adminPortal.getPane());
                } else {
                    System.err.println("MainApp reference is null");
                }

            }
            if ("Faculty".equalsIgnoreCase(selectedType)) {
                FacultyDashboard FacultyPortal=new FacultyDashboard();
                if (app != null) {
                    app.switchLayout(FacultyPortal.getPane());
                } else {
                    System.err.println("MainApp reference is null");
                }

            }
            if ("Student".equalsIgnoreCase(selectedType)) {
                StudentPortal studentportal=new StudentPortal();
                if (app != null) {
                    app.switchLayout(studentportal.getPane());
                } else {
                    System.err.println("MainApp reference is null");
                }

            }
            else{
                System.out.println("no type selected");
            }
        });

        VBox centrepart = new VBox();
        centrepart.getChildren().addAll(typebox, Name, textField, Pasword, passwordField, button);
        centrepart.setSpacing(30);
        centrepart.setAlignment(Pos.CENTER);
        centrepart.setPadding(new Insets(0, 0, 0, 200));

        VBox combinedBox = new VBox();
        combinedBox.getChildren().addAll(logo(), centrepart);
        combinedBox.setSpacing(100);
        combinedBox.setPadding(new Insets(20));
        centrepart.setMaxWidth(450);

        return combinedBox;
    }

    public BorderPane getPane() {
        return pane;
    }

    private HBox logo() {
        Image logo=new Image(this.getClass().getResource("/481px-COMSATS_new_logo.jpg").toExternalForm());

        ImageView logoview = new ImageView(logo);
        logoview.setFitHeight(50);
        logoview.setFitWidth(50);
        logoview.setPreserveRatio(true);

        Circle clip = new Circle(25, 25, 25);
        logoview.setClip(clip);

        Label uni = new Label("CUI PORTAL");
        uni.setFont(Font.font("Brush Script MT", FontWeight.BOLD, FontPosture.ITALIC, 36));
        uni.setPadding(new Insets(5, 0, 0, 0));

        HBox topbox = new HBox();
        topbox.getChildren().addAll(logoview, uni);
        topbox.setSpacing(10);
        topbox.setPadding(new Insets(50, 0, 0, 50));

        return topbox;
}
}