package com.example.demo;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;

public class FacultyDashboard {

    private BorderPane pane;
    private Button selectedButton = null;

    public FacultyDashboard() {

        pane = new BorderPane();

        pane.setStyle("-fx-background-color:#C8A97D");

        VBox navigation = dashboardmain();
        pane.setLeft(navigation);
        pane.setCenter(upperpart());

    }

    private VBox dashboardmain() {
        // vbox to add in centre
        VBox vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setStyle("-fx-background-color:#6F4E37");

        vBox.setPrefWidth(90);


        Image logoImage = new Image(this.getClass().getResource("/oop images.jpg").toExternalForm());
        ImageView logoView = new ImageView(logoImage);
        logoView.setFitWidth(50);
        logoView.setFitHeight(50);
        logoView.setClip(new Circle(25, 25, 25)); // Circular clip for logo

        // Create buttons with icons
        Button homeButton = createNavButton("/home.png");
        Button settingsButton = createNavButton("/graduation-cap.png");
        Button userButton = createNavButton("/bookmark.png");
        Button attendButton = createNavButton("/apps.png");
        Button editButton = createNavButton("/settings.png");


        // A bottom section button
        Button exitButton = createNavButton("/play.png");
        exitButton.setStyle("-fx-background-color:#6F4E37 ;");


        // Add buttons to the navigation bar
        vBox.getChildren().addAll(logoView, homeButton, settingsButton, userButton, attendButton, editButton, exitButton);
        VBox.setMargin(exitButton, new Insets(60, 0, 0, 0));
        vBox.setPadding(new Insets(40, 0, 0, 25));
        vBox.setSpacing(15);


        homeButton.setOnAction(actionEvent -> pane.setCenter(upperpart()));
        attendButton.setOnAction(actionEvent -> pane.setCenter(Attendance()));

        return vBox;

    }

    private VBox upperpart() {
        // Vbox for dashboard centre part
        VBox dashcentre = new VBox();
        dashcentre.getChildren().addAll(logo(), dashboardcentre());
        dashcentre.setMargin(logo(), new Insets(50, 0, 0, 0));
        dashcentre.setSpacing(100);
        return dashcentre;

    }

    public BorderPane getPane() {
        return pane;
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

    private HBox logo() {
        Image logo = new Image(this.getClass().getResource("/481px-COMSATS_new_logo.jpg").toExternalForm());
        ImageView logoview = new ImageView(logo);
        logoview.setFitHeight(50);
        logoview.setFitWidth(50);
        logoview.setPreserveRatio(true);

        //shaping  of logo
        Circle Clip = new Circle(25, 25, 25); // (centerX, centerY, radius)
        logoview.setClip(Clip);

        // uni name
        Label uni = new Label("CUI PORTAL");

        uni.setFont(Font.font("Brush Script MT", FontWeight.BOLD, FontPosture.ITALIC, 36));
        uni.setPadding(new Insets(5, 0, 0, 0));
        HBox topbox = new HBox();
        topbox.getChildren().addAll(logoview, uni);
        topbox.setSpacing(10);
        topbox.setPadding(new Insets(50, 0, 0, 50));

        return topbox;

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

    private GridPane dashboardcentre() {

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(30);
        gridPane.setVgap(30);
        gridPane.setAlignment(Pos.CENTER);


        // Buttons
        Button home = createButton("/home.png", "Home");
        Button addCourseButton = createButton("/graduation-cap.png", "Study Plan");
        Button addUserButton = createButton("/bookmark.png", "Marks");
        Button attendanceButton = createButton("/apps.png", "Attendence");
        Button usersButton = createButton("/settings.png", "Transcript");
        Button logoutButton = createButton("/play.png", "Logout");


        attendanceButton.setTranslateY(15);
        usersButton.setTranslateY(15);


        home.setTranslateX(-80);
        addCourseButton.setTranslateX(-30);
        addUserButton.setTranslateX(20);
        attendanceButton.setTranslateX(-80);
        usersButton.setTranslateX(-30);

        logoutButton.setTranslateX(450);
        logoutButton.setTranslateY(100);


        home.setPadding(new Insets(30, 50, 30, 50));
        addCourseButton.setPadding(new Insets(30, 50, 30, 50));
        addUserButton.setPadding(new Insets(30, 50, 30, 50));
        attendanceButton.setPadding(new Insets(30, 50, 30, 50));
        usersButton.setPadding(new Insets(30, 50, 30, 50));

        home.setOnAction(actionEvent -> pane.setCenter(upperpart()));

        attendanceButton.setOnAction(actionEvent -> pane.setCenter(Attendance()));


        // Add buttons to the GridPane in the required layout

        gridPane.add(home, 0, 0);
        gridPane.add(addCourseButton, 1, 0);
        gridPane.add(addUserButton, 2, 0);
        gridPane.add(attendanceButton, 0, 1);
        gridPane.add(usersButton, 1, 1);
        gridPane.add(logoutButton, 1, 2);


        return gridPane;

    }
    String url="jdbc:mysql://localhost:3306/university_system";
    String username="root";
    String password_sql="0348jav.";

    VBox Attendance(){

        Label course_label=new Label("Course_Code");
        TextField course_field=new TextField();
        DatePicker date_attendance=new DatePicker();
        HBox hbox=new HBox(20,course_label,course_field,date_attendance);
        Button attend=new Button("Show Attendance Sheet");
        Button upload=new Button("UPLOAD");
        HBox hbox2=new HBox(30,attend,upload);

        VBox vbox1=new VBox(20,hbox,hbox2);


        TableView<Student> table=new TableView();
        // three colums to be added
        TableColumn<Student,String> c1=new TableColumn("STUDENT Name");
        TableColumn <Student,String>c2=new TableColumn("Registration no");
        TableColumn<Student,Void> c3=new TableColumn("Attendance");

        ObservableList<Student> student_list = FXCollections.observableArrayList();

        attend.setOnAction(e->{
        //connection with database
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection= DriverManager.getConnection(url,username,password_sql);
            connection.setAutoCommit(false);
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("Select *from course inner join all_users " +
                    "on course.Semester=all_users.semester and course.dept=all_users.department and all_users.type='STUDENT'");

            int count=0;
            while(resultSet.next()) {
                if (course_field.getText().equalsIgnoreCase(resultSet.getString("Course_code"))) {
                    student_list.add(new Student(
                    resultSet.getString("user_name"),
                            resultSet.getString("id")
                          //  saved_st.setString(1,resultSet.getString("user_name"));
                    ));
                    count++;
                }
            }
            connection.commit();
            statement.close();
            resultSet.close();
            connection.close();

        }catch(Exception exception){
            exception.getStackTrace();
        }
            c1.setCellValueFactory(new PropertyValueFactory("studentname"));
            c2.setCellValueFactory(new PropertyValueFactory("registration"));
            c3.setCellFactory( col -> new RadioCell());

            table.setItems(student_list);

            Group grp=new Group();
            grp.prefWidth(40);
            grp.getChildren().addAll(table);
            VBox tab_vbox=new VBox(vbox1,grp);
            pane.setCenter(tab_vbox);

        });

        upload.setOnAction(actionEvent->{
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection2= DriverManager.getConnection(url,username,password_sql);
                connection2.setAutoCommit(false);

                String sql="insert into attendance_info values(?,?,?,?,?)";
                PreparedStatement saved_st=connection2.prepareStatement(sql);

                for (Student list : student_list) {

                    saved_st.setString(1, list.getStudentname());
                    saved_st.setString(2, list.getRegistration());
                    saved_st.setString(3,course_field.getText());
                    saved_st.setString(4,list.isPresent()?"Present":"Absent");
                    saved_st.setString(5,date_attendance.getValue().toString());

                    saved_st.addBatch();
                }
                saved_st.executeBatch();
                connection2.commit();
                saved_st.close();
                connection2.close();
                System.out.println("Attendance data uploaded successfully!");
            }catch(Exception excep){
                excep.getStackTrace();
            }
        });


        table.setPrefWidth(300);
        table.setPrefHeight(300);

        c1.setPrefWidth(150);
        c2.setPrefWidth(150);
        c3.setPrefWidth(200);


        table.getColumns().addAll(c1,c2,c3);
        table.setPrefSize(800,400);
        table.setTranslateX(100);
       // table.setTranslateY(70);
       // VBox vbox=new VBox(vbox1,table);
        vbox1.setSpacing(30);
       vbox1.setAlignment(Pos.TOP_LEFT);


        VBox vbox=new VBox(30,vbox1);
        return vbox;
    }
}
