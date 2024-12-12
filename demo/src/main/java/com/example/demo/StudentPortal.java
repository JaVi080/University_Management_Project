package com.example.demo;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class StudentPortal {
    private Button selectedButton = null;
    private BorderPane pane;

    public StudentPortal() {
        pane = new BorderPane();

        pane.setStyle("-fx-background-color:#C8A97D");

        VBox navigation =nav();
        pane.setLeft(navigation);



    }


    private VBox nav(){

        // vbox to add in centre
        VBox vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setStyle("-fx-background-color:#6F4E37");

        vBox.setPrefWidth(90);


        Image logoImage = new Image(getClass().getResource("/menu-burger.png").toExternalForm());
        ImageView logoView = new ImageView(logoImage);
        logoView.setFitWidth(50);
        logoView.setFitHeight(50);
        logoView.setClip(new

                Circle(25,25,25)); // Circular clip for logo

        // Create buttons with icons
        Button homeButton = createNavButton("/home.png"); // Replace with your icon
        Button settingsButton = createNavButton("/settings.png");
        Button userButton = createNavButton("/bookmark.png");
        Button toolsButton = createNavButton("/globe.png");
        Button editButton = createNavButton("/edit.png"); // Replace with your icon
        Button appsButton = createNavButton("/apps.png");
        Button shareButton = createNavButton("/graduation-cap.png");

        // A bottom section button
        Button exitButton = createNavButton("/play.png");
        exitButton.setStyle("-fx-background-color:#6F4E37 ;"); // Red button for exit

        // Add buttons to the navigation bar
        vBox.getChildren().addAll(logoView, homeButton, settingsButton, userButton, toolsButton, editButton, appsButton, shareButton, exitButton);
        VBox.setMargin(exitButton,new Insets( 50,0,0,0));
        vBox.setPadding(new Insets(40,0,0,25));
        vBox.setSpacing(20);


        homeButton.setOnAction(actionEvent -> pane.setCenter(upperpart()));

        return vBox;

    }




    private VBox upperpart() {
        // Vbox for dashboard centre part
        VBox dashboardcentre = new VBox();
        dashboardcentre.getChildren().addAll(logo(), dashboardcentre());
        dashboardcentre.setMargin(logo(), new Insets(50, 0, 0, 0));
        dashboardcentre.setSpacing(100);
        return dashboardcentre;

    }

    private Button createButton(String iconPath, String text) {
        Button button = new Button();
        button.setStyle("-fx-background-color:#6F4E37; -fx-text-fill: white; -fx-font-size: 20;");

        try {
            // Load the icon
            Image icon = new Image(getClass().getResource(iconPath).toExternalForm());
            ImageView iconView = new ImageView(icon);
            iconView.setFitHeight(20);
            iconView.setFitWidth(20);

            button.setGraphic(iconView);
            button.setGraphicTextGap(10);
            button.setText(text);
            button.setContentDisplay(ContentDisplay.LEFT);
            button.setPadding(new Insets(10));
            button.setMaxSize(300, 300);
        } catch (Exception e) {
            System.err.println("Could not load icon for: " + iconPath);
        }

        return button;
    }

    private GridPane dashboardcentre(){

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(30);
        gridPane.setVgap(30);
        gridPane.setAlignment(Pos.CENTER);


        // Buttons
        Button home = createButton("/home.png", "Home");
        Button addCourseButton = createButton("/edit.png", "Registration");
        Button addUserButton = createButton("/graduation-cap.png", "Study Plan");
        Button coursesButton = createButton("/globe.png", "Fee Details");
        Button usersButton = createButton("/bookmark.png", "Marks");
        Button updateFeeButton = createButton("/settings.png", "Transcript");
        Button appsbutton=createButton("/apps.png","Attendence");
        Button logoutButton = createButton("/play.png", "Logout");


        coursesButton.setTranslateY(15);
        usersButton.setTranslateY(15);
        updateFeeButton.setTranslateY(15);
        appsbutton.setTranslateX(-80);
        appsbutton.setTranslateY(25);


        home.setTranslateX(-80);
        addCourseButton.setTranslateX(-30);
        addUserButton.setTranslateX(20);
        coursesButton.setTranslateX(-80);
        usersButton.setTranslateX(-30);
        updateFeeButton.setTranslateX(20);
        logoutButton.setTranslateX(450);
        logoutButton.setTranslateY(100);





        home.setPadding(new Insets(30,50,30,50));
        addCourseButton.setPadding(new Insets(30,50,30,50));
        addUserButton.setPadding(new Insets(30,50,30,50));
        coursesButton.setPadding(new Insets(30,50,30,50));
        usersButton.setPadding(new Insets(30,50,30,50));
        updateFeeButton.setPadding(new Insets(30,50,30,50));
        appsbutton.setPadding(new Insets(30,50,30,50));


        home.setOnAction(actionEvent -> pane.setCenter(upperpart()));




        // Add buttons to the GridPane in the required layout

        gridPane.add(home, 0, 0);
        gridPane.add(addCourseButton, 1, 0);
        gridPane.add(addUserButton, 2, 0);
        gridPane.add(coursesButton, 0, 1);
        gridPane.add(usersButton, 1, 1);
        gridPane.add(updateFeeButton, 2, 1);
        gridPane.add(logoutButton, 1, 2);
        gridPane.add(appsbutton,0,2);


        return gridPane;


    }

    private Button createNavButton(String iconPath) {
        // Create a button
        Button button = new Button();
        button.setStyle("-fx-background-color:#6F4E37 ;"); // Match nav background color
        button.setPadding(new Insets(10));
        button.setPrefSize(50, 50); // Square buttons

        // Add an icon to the button
        Image icon = new Image(getClass().getResource(iconPath).toExternalForm());
        ImageView iconView = new ImageView(icon);
        iconView.setFitWidth(30);
        iconView.setFitHeight(30);
        button.setGraphic(iconView);

        // Change button style on hover
        button.setOnMouseClicked(e -> {
            if (selectedButton != null) {
                // Reset the previously selected button
                selectedButton.setStyle("-fx-background-color:#6F4E37;");
            }

            // Set the current button as selected and change its color
            button.setStyle("-fx-background-color: #A0522D;");
            selectedButton = button; // Update the currently selected button
        });

        return button;
    }
    private HBox logo(){
        Image logo=new Image(this.getClass().getResource("/481px-COMSATS_new_logo.jpg").toExternalForm());
        ImageView logoview=new ImageView(logo);
        logoview.setFitHeight(50);
        logoview.setFitWidth(50);
        logoview.setPreserveRatio(true);

        //shaping  of logo
        Circle Clip = new Circle(25, 25, 25); // (centerX, centerY, radius)
        logoview.setClip(Clip);

        // uni name
        Label uni=new Label("CUI PORTAL");

        uni.setFont(Font.font("Brush Script MT", FontWeight.BOLD  , FontPosture.ITALIC, 36));
        uni.setPadding(new Insets(5,0,0,0));
        HBox topbox=new HBox();
        topbox.getChildren().addAll(logoview,uni);
        topbox.setSpacing(10);
        topbox.setPadding(new Insets(50,0,0,50));

        return topbox;

    }

    public BorderPane getPane() {
        return pane;
}
}

