package com.example.uni_project;

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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainLayout {
        String login_id;
        String login_password;
        private BorderPane pane;
        private MainApp app; // Reference to MainApp

    MainLayout(String login_id,String login_password){
        this.login_id=login_id;
        this.login_password=login_password;
    }

        public String getLogin_id() {
            return login_id;
        }
        public String getLogin_password(){
            return login_password;
        }

        public void setLogin_id(String login_id) {
        this.login_id = login_id;
    }

    public void setLogin_password(String login_password) {
        this.login_password = login_password;
    }

    // Pass MainApp to MainLayout
    public MainLayout(MainApp app) {
        this.app = app; // Assign MainApp instance
        pane = new BorderPane();
        pane.setStyle("-fx-background-color:#C8A97D");


        VBox centrepart = main();
        pane.setCenter(centrepart);
    }

    public VBox main() {
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

        Label Name = new Label("USER_ID");
        Name.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 15));
        TextField textField = new TextField();
        //setLogin_id(textField.getText());

        Label Pasword = new Label("PASSWORD");
        Pasword.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 15));
        PasswordField passwordField = new PasswordField();
       // setLogin_password(passwordField.getText());

       // MainLayout main=new MainLayout(textField.getText(), passwordField.getText());
       // System.out.println(main);

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

        String url="jdbc:mysql://localhost:3306/university_system";
        String username_db=//ur username
        String password_db=//ur password
        // Set the Login button's action
        login.setOnAction(e -> {
            try {
                //databse Connection with this file
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection= DriverManager.getConnection(url,username_db,password_db);
                connection.setAutoCommit(false);
                Statement statement= connection.createStatement();
                ResultSet resultSet= statement.executeQuery("select *from all_users");

                MainLayout mainLayout = new MainLayout(textField.getText(), passwordField.getText());
                String selectedType = comboBox.getValue();
                boolean match=false;
                while(resultSet.next()) {
                    if ("Admin".equals(selectedType)&&textField.getText().equalsIgnoreCase(resultSet.getString(2))&&
                    passwordField.getText().equalsIgnoreCase(resultSet.getString(4))) {
                        AdminDashboard adminPortal = new AdminDashboard(app);
                        match=true;
                        if (app != null) {
                            app.switchLayout(adminPortal.getPane());
                        } else {
                            System.err.println("MainApp reference is null");
                        }
                    }
                    if ("Faculty".equalsIgnoreCase(selectedType)&&textField.getText().equalsIgnoreCase(resultSet.getString(2))&&
                            passwordField.getText().equalsIgnoreCase(resultSet.getString(4))) {
                        FacultyDashboard FacultyPortal = new FacultyDashboard(app);
                        match=true;
                        if (app != null) {
                            app.switchLayout(FacultyPortal.getPane());
                        } else {
                            System.err.println("MainApp reference is null");
                        }
                    }
                    if ("Student".equalsIgnoreCase(selectedType)&&textField.getText().equalsIgnoreCase(resultSet.getString(2))&&
                            passwordField.getText().equalsIgnoreCase(resultSet.getString(4))) {
                        StudentPortal studentportal = new StudentPortal(mainLayout);
                        match=true;
                        if (app != null) {
                            app.switchLayout(studentportal.getPane());
                        } else {
                            System.err.println("MainApp reference is null");
                        }

                    } else {
                        System.out.println("no type selected");
                    }
                }
                if(match==false){
                    System.out.println("login info is wrong");
                }
                connection.commit();
                resultSet.close();
                statement.close();
                connection.close();
            }
            catch(Exception exception){
                exception.printStackTrace();
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
