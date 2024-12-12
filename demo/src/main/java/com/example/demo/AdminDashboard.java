package com.example.demo;


import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.net.ConnectException;
import java.sql.*;

public class AdminDashboard {

        private BorderPane pane;
        private Button selectedButton = null;


        public AdminDashboard() {

            pane = new BorderPane();

            pane.setStyle("-fx-background-color:#C8A97D");

            VBox navigationBar = dashboardmain();
            pane.setLeft(navigationBar);
            pane.setCenter(upperpart());


        }


        private VBox dashboardmain() {
            // vbox to add in centre
            VBox vBox = new VBox();
            vBox.setSpacing(20);
            vBox.setStyle("-fx-background-color:#6F4E37");


            vBox.setPrefWidth(90);


            Image logoImage = new Image(getClass().getResource("/menu-burger.png").toExternalForm()); // Replace with your logo image
            ImageView logoView = new ImageView(logoImage);
            logoView.setFitWidth(50);
            logoView.setFitHeight(50);
            logoView.setClip(new Circle(25, 25, 25)); // Circular clip for logo

            // Create buttons with icons
            Button homeButton = createNavButton("/home.png");
            Button settingsButton = createNavButton("/settings.png");
            Button userButton = createNavButton("/user.png");
            Button toolsButton = createNavButton("/globe.png");
            Button editButton = createNavButton("/edit.png");
            Button appsButton = createNavButton("/user.png");
            Button deleteuserbutton = createNavButton("/trash-xmark.png");
            Button deletefacultybutton = createNavButton("/cross-circle.png");


            // A bottom section button
            Button exitButton = createNavButton("/play.png");
            exitButton.setStyle("-fx-background-color:#6F4E37 ;");


            // Add buttons to the navigation bar
            vBox.getChildren().addAll(logoView, homeButton, settingsButton, userButton, toolsButton, editButton, appsButton, deleteuserbutton, deletefacultybutton, exitButton);
            VBox.setMargin(exitButton, new Insets(18, 0, 0, 0));
            vBox.setPadding(new Insets(40, 0, 0, 25));
            vBox.setSpacing(15);

            userButton.setOnAction(actionEvent -> pane.setCenter(user_part()));
            homeButton.setOnAction(actionEvent -> pane.setCenter(upperpart()));
            editButton.setOnAction(actionEvent -> pane.setCenter(course_part()));
           toolsButton.setOnAction(actionEvent -> pane.setCenter(course_info_part()));
           appsButton.setOnAction(actionEvent -> pane.setCenter(user_info_display()));

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


        private VBox course_part() {
            VBox addcourse = new VBox();
            addcourse.getChildren().addAll(logo(), Add_Course());
            addcourse.setSpacing(30);

            return addcourse;
        }

        public BorderPane getPane() {
            return pane;
        }

        private VBox user_part() {
            VBox user = new VBox();
            user.getChildren().addAll(logo(), Add_User());
            user.setSpacing(30);
            return user;
        }
    private VBox course_info_part() {
        VBox vbox = new VBox();
        vbox.getChildren().addAll(logo(), course_info());
        vbox.setSpacing(30);
        return vbox;
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
            Image logo=new Image(this.getClass().getResource("/481px-COMSATS_new_logo.jpg").toExternalForm());
            ImageView logoview = new ImageView(logo);
            logoview.setFitHeight(50);
            logoview.setFitWidth(50);
            logoview.setPreserveRatio(true);

            //shaping  of logo
            Circle Clip = new Circle(25, 25, 25); // (centerX, centerY, radius)
            logoview.setClip(Clip);


            // uni name
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
            }
         catch(Exception e)

        {
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
        Button addCourseButton = createButton("/edit.png", "Add Course");
        Button addUserButton = createButton("/user.png", "Add User");
        Button coursesButton = createButton("/globe.png", "Courses");
        Button usersButton = createButton("/user.png", "Users");
        Button updateFeeButton = createButton("/settings.png", "Update Fee");
        Button logoutButton = createButton("/play.png", "Logout");
        Button deleteuser = createButton("/trash-xmark.png", "Delete User");
        Button deletefaculty = createButton("/cross-circle.png", "Delete Faculty");

        coursesButton.setTranslateY(15);
        usersButton.setTranslateY(15);
        updateFeeButton.setTranslateY(15);
        deleteuser.setTranslateY(25);
        deletefaculty.setTranslateY(25);

        home.setTranslateX(-80);
        addCourseButton.setTranslateX(-30);
        addUserButton.setTranslateX(20);
        coursesButton.setTranslateX(-80);
        usersButton.setTranslateX(-30);
        updateFeeButton.setTranslateX(20);
        logoutButton.setTranslateX(450);
        logoutButton.setTranslateY(100);
        deletefaculty.setTranslateX(-30);
        deleteuser.setTranslateX(-80);


        home.setPadding(new Insets(30, 50, 30, 50));
        addCourseButton.setPadding(new Insets(30, 50, 30, 50));
        addUserButton.setPadding(new Insets(30, 50, 30, 50));
        coursesButton.setPadding(new Insets(30, 50, 30, 50));
        usersButton.setPadding(new Insets(30, 50, 30, 50));
        updateFeeButton.setPadding(new Insets(30, 50, 30, 50));

        deletefaculty.setPadding(new Insets(30, 50, 30, 50));
        deleteuser.setPadding(new Insets(30, 50, 30, 50));


        home.setOnAction(actionEvent -> pane.setCenter(upperpart()));
        addCourseButton.setOnAction(actionEvent -> pane.setCenter(course_part()));
        addUserButton.setOnAction(actionEvent -> pane.setCenter(user_part()));


        // Add buttons to the GridPane in the required layout

        // Add buttons to the GridPane in the required layout

        gridPane.add(home, 0, 0);
        gridPane.add(addCourseButton, 1, 0);
        gridPane.add(addUserButton, 2, 0);
        gridPane.add(coursesButton, 0, 1);
        gridPane.add(usersButton, 1, 1);
        gridPane.add(updateFeeButton, 2, 1);
        gridPane.add(logoutButton, 1, 2);
        gridPane.add(deleteuser, 0, 2);
        gridPane.add(deletefaculty, 1, 2);

        return gridPane;


    }
        //Connection with database
        String url="jdbc:mysql://localhost:3306/university_system";
        String username="root";
        String password_sql="0348jav.";

    VBox Add_Course() {

        Text txt = new Text("COURSE REGISTRATION");
        txt.setStyle("-fx-font-weight:bold;-fx-font-size:40;-fx-font-style:italic;-fx-font-family:Arial");


        Label course_name = new Label("COURSE NAME ");
        TextField name = new TextField();
        HBox hbox1 = new HBox(20);
        hbox1.getChildren().addAll(course_name, name);

        Label course_code = new Label("COURSE CODE ");
        TextField code = new TextField();
        HBox hbox2 = new HBox(20);
        hbox2.getChildren().addAll(course_code, code);

        Label credit_Hrs = new Label("CREDIT_Hrs ");
        ChoiceBox<Integer> hrs = new ChoiceBox();
        ObservableList<Integer> hrs_list = FXCollections.observableArrayList(1, 2, 3);
        hrs.setItems(hrs_list);
        HBox hbox3 = new HBox(20);
        hbox3.getChildren().addAll(credit_Hrs, hrs);

        Label semester = new Label("SEMESTER ");
        ChoiceBox<Integer> sem = new ChoiceBox<Integer>();
        ObservableList<Integer> sem_list = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8);
        sem.setItems(sem_list);
        HBox hbox4 = new HBox(20);
        hbox4.getChildren().addAll(semester, sem);

        Label course_type = new Label("TYPE ");
        ChoiceBox<String> type = new ChoiceBox<>();
        type.getItems().addAll("CORE", "ELECTIVE");
        HBox hbox5 = new HBox(20);
        hbox5.getChildren().addAll(course_type, type);

        Label instructor_name = new Label("INSTRUCTOR NAME");
        TextField instructor = new TextField();
        HBox hbox6 = new HBox(20);
        hbox6.getChildren().addAll(instructor_name, instructor);


        Label dept_name = new Label("DEPARTMENT");
        TextField dept = new TextField();
        HBox hbox7 = new HBox(20);
        hbox7.getChildren().addAll(dept_name, dept);

        VBox vbox = new VBox(30);
        vbox.setTranslateX(300);
        Region space = new Region();
        Button button = new Button("Add Course");
        button.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 10));
        button.setTranslateX(270);
        button.setPrefHeight(40);
        button.setPrefWidth(80);
        button.setOnAction(e->{
            //Using Databases for saving information
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection= DriverManager.getConnection(url,username,password_sql);
                connection.setAutoCommit(false);
                String sql="insert into Course values(?,?,?,?,?,?,?)";
                PreparedStatement statement=connection.prepareStatement(sql);
                statement.setString(1,code.getText());
                statement.setString(2,name.getText());
                statement.setInt(3,hrs.getValue());
                statement.setInt(4,sem.getValue());
                statement.setString(5,type.getValue());
                statement.setString(6,instructor.getText());
                statement.setString(7,dept.getText());
                statement.executeUpdate();
                connection.commit();
                statement.close();
                connection.close();
            }catch(Exception exception){
                System.out.println(exception.getMessage());
            }
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Course Information");
            alert.setContentText("Course has been saved successfully");
            alert.show();
        });

        Button button3 = new Button("   Remove Course");
        button3.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 10));
        button3.setTranslateX(270);
        button3.setPrefHeight(40);
        button3.setPrefWidth(80);
        button3.setOnAction(e->{
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection= DriverManager.getConnection(url,username,password_sql);
                connection.setAutoCommit(false);
                String sql="delete from Course where Course_code=?";
                PreparedStatement statement=connection.prepareStatement(sql);
                statement.setString(1,code.getText());
                statement.executeUpdate();
                connection.commit();
                statement.close();
                connection.close();
            }catch(Exception exception){
                System.out.println(exception.getMessage());
            }
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Course Information");
                alert.setContentText("Course has been removed successfully");
                alert.show();

        });
        Button button2=new Button("CLEAR INFO");
        vbox.getChildren().addAll(txt, space, hbox1, hbox2, hbox3, hbox4, hbox5, hbox6, hbox7, button,button2,button3);
        FontStyling(vbox);
        button2.setOnAction(e->{
            for(var hbox: vbox.getChildren()){
                if(hbox instanceof HBox){
                    for(var fields: ((HBox)hbox).getChildren()){
                        if(fields instanceof TextField){
                            ((TextField)fields).clear();
                        }
                    }
                }
            }
        });
        return vbox;
    }

    public void FontStyling(VBox vbox) {
        for (var hbox : vbox.getChildren()) {
            if (hbox instanceof HBox) {
                for (var h : ((HBox) hbox).getChildren()) {
                    if (h instanceof Label) {
                        if (!((Label) h).getText().equalsIgnoreCase("CUI PORTAL")) {
                            ((Label) h).setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 15));
                            ((Label) h).setPrefWidth(150);
                        }
                    }
                    if (h instanceof TextField || h instanceof ChoiceBox) {
                        ((Control) h).setPrefWidth(190);

                    }
                }
            }

        }
    }
    private  VBox Add_User() {
        Text txt = new Text("REGISTER USER ");
        txt.setStyle("-fx-font-weight:bold;-fx-font-size:30;-fx-font-style:italic;-fx-font-family:Arial");

        Label first_name = new Label("FIRST NAME ");
        Label last_name = new Label("LAST NAME");
        HBox HBox1 = new HBox(90, first_name, last_name);


        TextField first = new TextField();
        TextField last = new TextField();
        HBox HBox2 = new HBox(50, first, last);

        Label Reg_number = new Label("ID/REGISTRATION NO");
        Label password = new Label("PASSWORD");
        HBox HBox3 = new HBox(95, Reg_number, password);

        TextField reg = new TextField();
        TextField pass = new TextField();
        HBox HBox4 = new HBox(50, reg, pass);

        Label date = new Label("DateOfBirth");
        Label type = new Label("TYPE");
        HBox HBox5 = new HBox(85, date, type);

        DatePicker dob = new DatePicker();
        ChoiceBox<String> types = new ChoiceBox<>();
        types.getItems().addAll("ADMIN","FACULTY","STUDENT");
        HBox HBox6=new HBox(60,dob,types);


        Label department = new Label("DEPARTMENT ");
        Label semester = new Label("SEMESTER ");
        HBox hbox7 = new HBox(85,department,semester);

        TextField dept =new TextField();
        ChoiceBox<Integer> sem = new ChoiceBox<Integer>();
        sem.getItems().addAll(1,2,3,4,5,6,7,8);
        Text text=new Text("Select it if Student/Teacher");
        HBox hbox8 = new HBox(85,dept,sem,text);

        Button button=new Button("REGISTER");
        button.setFont(Font.font("Arial",FontWeight.BOLD,FontPosture.ITALIC,10));
        button.setPrefHeight(40);
        button.setPrefWidth(80);

        Button button2 =new Button("CLEAR INFO");
        button2.setFont(Font.font("Arial",FontWeight.BOLD,FontPosture.ITALIC,10));
        button2.setPrefHeight(40);
        button2.setPrefWidth(80);

        Button button3 =new Button("Remove User");
        button3.setFont(Font.font("Arial",FontWeight.BOLD,FontPosture.ITALIC,10));
        button3.setPrefHeight(40);
        button3.setPrefWidth(80);

        HBox hbox9=new HBox(40,button,button2,button3);

        Region space=new Region();
        space.setPrefWidth(20);

        //saving data inot database
        button.setOnAction(e->{
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection(url, username, password_sql);
                connection.setAutoCommit(false);
                    String sql = "insert into all_users values(?,?,?,?,?,?,?)";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, types.getValue());
                    statement.setString(2, reg.getText());
                    statement.setString(3, first.getText() + " " + last.getText());
                    statement.setString(4, pass.getText());
                    statement.setString(5, dob.getValue().toString());
                    statement.setInt(6, sem.getValue());
                    statement.setString(7,dept.getText());

                statement.executeUpdate();
                connection.commit();
                statement.close();
                connection.close();

            }catch (Exception exception){

            }

        });
        button3.setOnAction(e->{
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection(url, username, password_sql);
                connection.setAutoCommit(false);
                String sql="delete from all_users where id=?and password=?";
                PreparedStatement statement=connection.prepareStatement(sql);
                statement.setString(1,reg.getText());
                statement.setString(2,pass.getText());

                statement.executeUpdate();
                connection.commit();
                statement.close();
                connection.close();

            }catch(Exception exception) {
                System.out.println(exception.getMessage());
            }
            });

        VBox vbox=new VBox(30);
        vbox.setTranslateX(300);
        vbox.setTranslateY(30);
        vbox.getChildren().addAll(txt,space,HBox1,HBox2,HBox3,HBox4,HBox5,HBox6,hbox7,hbox8,hbox9);
        FontStyling(vbox);

        button2.setOnAction(e->{
            for(var hbox: vbox.getChildren()){
                if(hbox instanceof HBox){
                    for(var fields: ((HBox)hbox).getChildren()){
                        if(fields instanceof TextField){
                            ((TextField)fields).clear();
                        }
                    }
                }
            }
        });
        return vbox;
}

Group course_info(){

        TableView course_table=new TableView();
        TableColumn col1=new TableColumn("COURSE CODE");
    TableColumn col2=new TableColumn("COURSE NAME");
    TableColumn col3=new TableColumn("CREDIT Hrs");
    TableColumn col4=new TableColumn("TEACHER Assigned");
    TableColumn col5=new TableColumn("TYPE");
    TableColumn col6=new TableColumn("SEMESTER");
    TableColumn col7=new TableColumn("DEPARTMENT");

    ObservableList<Course> course_list=FXCollections.observableArrayList();
    Button button =new Button("Remove Course");

    //database
    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, username, password_sql);
        connection.setAutoCommit(false);
        Statement statement=connection.createStatement();
        ResultSet resultset=statement.executeQuery("Select *from Course");
        while(resultset.next()){
            course_list.add(
            new Course(
                 resultset.getString(1),
                 resultset.getString(2),
                resultset.getInt(3),
                resultset.getString(6),
               resultset.getString(5),
                resultset.getInt(4),
                resultset.getString(7)));

        }
        //adding values
        col1.setCellValueFactory(new PropertyValueFactory<Course,String>("course_code"));
        col2.setCellValueFactory(new PropertyValueFactory<Course,String>("name"));
        col3.setCellValueFactory(new PropertyValueFactory<Course,Integer>("hrs"));
        col4.setCellValueFactory(new PropertyValueFactory<Course,String>("instructor"));
        col5.setCellValueFactory(new PropertyValueFactory<Course,String>("type"));
        col6.setCellValueFactory(new PropertyValueFactory<Course,Integer>("semester"));
        col7.setCellValueFactory(new PropertyValueFactory<Course,String>("dept"));

        //data in the table
        course_table.setItems(course_list);
        resultset.close();
        statement.close();
        connection.close();


        button.setOnAction(e->{
            Course selected=(Course)course_table.getSelectionModel().getSelectedItem();
            course_list.remove(selected);

            //Action on button
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection2 = DriverManager.getConnection(url, username, password_sql);
                connection2.setAutoCommit(false);
                String sql = "delete from Course where Course_code=?";
                PreparedStatement statement2 = connection2.prepareStatement(sql);
                statement2.setString(1, selected.getCourse_code());
                statement2.executeUpdate();
                connection2.commit();
                statement2.close();
                connection2.close();
            }catch(Exception ex){

            }
        });

    }catch(Exception e){
        e.getStackTrace();
    }
    col1.setPrefWidth(150);
    col2.setPrefWidth(150);
    col3.setPrefWidth(150);
    col4.setPrefWidth(150);
    col5.setPrefWidth(150);
    col6.setPrefWidth(150);
    col7.setPrefWidth(150);

    course_table.getColumns().addAll(col1,col2,col3,col4,col5,col6,col7);
  //  for(var col: course_table.getColumns()){
   // }
    course_table.setPrefSize(1000,400);
    course_table.setTranslateX(100);
    course_table.setTranslateY(70);

   // VBox vbox=new VBox(20,course_table);
    Group grp=new Group();
    grp.getChildren().addAll(course_table,button);
        return grp;
}

//Show User Information
Group user_info_display(){

        TableView user_table=new TableView();

        TableColumn col1=new TableColumn("TYPE");
    TableColumn col2=new TableColumn("ID");
    TableColumn col3=new TableColumn("NAME");
    TableColumn col4=new TableColumn("Password");
    TableColumn col5=new TableColumn("DEPARTMENT");
    TableColumn col6=new TableColumn("SEMESTER");
    TableColumn col7=new TableColumn("DateOfBirth");

    ObservableList<User> user_list=FXCollections.observableArrayList();
    Button button =new Button("Remove User");
    //database
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection=DriverManager.getConnection(url,username,password_sql);
            connection.setAutoCommit(false);
            Statement statement=connection.createStatement();
            ResultSet resultset=statement.executeQuery("Select *from all_users");

            while(resultset.next()) {
                user_list.add(
                        new User(resultset.getString(1),
                                resultset.getString(2),
                                resultset.getString(3),
                                resultset.getString(4),
                                resultset.getString(7),
                                resultset.getInt(6),
                                resultset.getString(5))
                );
            }
            col1.setCellValueFactory(new PropertyValueFactory<User,String>("type"));
            col2.setCellValueFactory(new PropertyValueFactory<User,String>("id"));
            col3.setCellValueFactory(new PropertyValueFactory<User,String>("name"));
            col4.setCellValueFactory(new PropertyValueFactory<User,String>("password"));
            col5.setCellValueFactory(new PropertyValueFactory<User,String>("department"));
            col6.setCellValueFactory(new PropertyValueFactory<User,Integer>("semester"));
            col7.setCellValueFactory(new PropertyValueFactory<User,String>("dob"));

            user_table.setItems(user_list);

            statement.close();
            resultset.close();
            connection.close();

            button.setOnAction(e->{
                User selected=(User)user_table.getSelectionModel().getSelectedItem();
                user_list.remove(selected);
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection2 = DriverManager.getConnection(url, username, password_sql);
                    connection2.setAutoCommit(false);
                    String sql="delete from all_users where id=?";
                    PreparedStatement statement2=connection2.prepareStatement(sql);
                    statement2.setString(1,selected.getId());
                    System.out.println(selected.getId());
                    statement2.executeUpdate();

                    connection2.commit();
                    statement2.close();
                    connection2.close();

                }catch(Exception ex){
                    ex.getStackTrace();
                }
            });

        }catch(Exception exception){

        }

        //button action

    col1.setPrefWidth(150);
    col2.setPrefWidth(150);
    col3.setPrefWidth(150);
    col4.setPrefWidth(150);
    col5.setPrefWidth(150);
    col6.setPrefWidth(150);
        user_table.getColumns().addAll(col1,col2,col3,col4,col5,col6,col7);

    user_table.setPrefSize(1000,400);
    user_table.setTranslateX(100);
    user_table.setTranslateY(70);
        Group grp=new Group();
        grp.getChildren().addAll(user_table,button);
        return grp;
}

}
