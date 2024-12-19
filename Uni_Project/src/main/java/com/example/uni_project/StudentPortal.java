package com.example.uni_project;

import com.mysql.cj.xdevapi.Table;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class StudentPortal {
    private Button selectedButton = null;
    private BorderPane pane;
    MainLayout mainLayout;
    String selected_code;
    String selected_course;
    private MainApp app;

    public StudentPortal(MainLayout mainLayout) {
        this.mainLayout=mainLayout;
        pane = new BorderPane();

        pane.setStyle("-fx-background-color:#C8A97D");

        VBox navigation =Dashbordtable();
        pane.setCenter(navigation);

    }
    public StudentPortal(MainApp app){
        this.app=app;

        pane = new BorderPane();

        pane.setStyle("-fx-background-color:#C8A97D");

        VBox navigation =Dashbordtable();
        pane.setCenter(navigation);


    }


    private VBox dashboardmain()  {
        // vbox to add in centre
        VBox vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setStyle("-fx-background-color:#6F4E37");

        vBox.setPrefWidth(90);


        Image logoImage = new Image(this.getClass().getResource("/menu-burger.png").toExternalForm());
        ImageView logoView = new ImageView(logoImage);
        logoView.setFitWidth(50);
        logoView.setFitHeight(50);
        logoView.setClip(new Circle(25, 25, 25)); // Circular clip for logo

        // Create buttons with icons
        Button homeButton = createNavButton("/home.png");
        Button userButton = createNavButton("/bookmark.png");
        Button editButton = createNavButton("/edit.png");
        Button appsButton = createNavButton("/apps.png");



        // A bottom section button
        Button exitButton = createNavButton("/play.png");

        exitButton.setStyle("-fx-background-color:#6F4E37 ;");



        // Add buttons to the navigation bar
        vBox.getChildren().addAll(logoView, homeButton, userButton, editButton, appsButton, exitButton);
        VBox.setMargin(exitButton, new Insets(18, 0, 0, 0));
        vBox.setPadding(new Insets(40, 0, 0, 25));
        vBox.setSpacing(20);

        appsButton.setOnAction(actionEvent -> {pane.setCenter(attend());
            pane.setLeft(dashboardmain());
        });
        homeButton.setOnAction(actionEvent -> pane.setCenter(mainbord()));
        userButton.setOnAction(actionEvent -> pane.setCenter(mark()));
        exitButton.setOnAction(actionEvent -> app.switchLayout(new MainLayout(app).getPane()));




        return vBox;

    }
    private HBox nav(){

        // vbox to add in centre
        HBox hBox = new HBox();
        // hBox.setPadding(new Insets(,0,0,120));


        HBox logoBox = logo();
        logoBox.setPadding(new Insets(0,0,5,0));

        hBox.setPrefWidth(400);

        Button Dasboard=createButton("/menu-burger.png","DashBoard");

        Button exitButton = createButton("/play.png", "Logout");

        Button Fee=createButton("/user-add.png","Fee");

        Dasboard.setPadding(new Insets(20,20,20,20));
        exitButton.setPadding(new Insets(20,20,20,30));
        Fee.setPadding(new Insets(20,20,20,30));




        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(30);
        gridPane.setVgap(50);

        gridPane.add(Dasboard, 0, 0);
        gridPane.add(Fee, 1, 0);
        gridPane.add(exitButton, 2, 0);

        hBox.getChildren().addAll(logoBox,gridPane,studentpic());
        hBox.setMargin(logo(),new Insets(0,0,0,100));
        hBox.setPadding(new Insets(70,0,0,150));
        hBox.setSpacing(100);


        Dasboard.setOnAction(actionEvent -> {pane.setCenter(Dashbordtable());pane.setLeft(null);});
        Fee.setOnAction(actionEvent -> {pane.setCenter(fee());});
        exitButton.setOnAction(actionEvent -> app.switchLayout(new MainLayout(app).getPane()));


        return hBox;
    }
    public VBox fee(){
        VBox fee=new VBox();
        fee.getChildren().addAll(nav(),Fee());
        fee.setSpacing(30);

        return fee;
    }
    private VBox Dashbordtable(){

        VBox table =new VBox();
        table.getChildren().addAll(nav(),Registered_course());
        table.setSpacing(50);


        return table;

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
        Button addUserButton = createButton("/bookmark.png", "Marks");
        Button coursesButton = createButton("/apps.png", "Attendence");







        home.setTranslateX(-80);

        addUserButton.setTranslateX(20);
        coursesButton.setTranslateX(40);

        home.setPadding(new Insets(30,50,30,50));

        addUserButton.setPadding(new Insets(30,50,30,50));
        coursesButton.setPadding(new Insets(30,50,30,50));




        home.setOnAction(actionEvent -> pane.setCenter(dashboardcentre()));
        addUserButton.setOnAction(actionEvent -> pane.setCenter(mark()));
        coursesButton.setOnAction(actionEvent -> pane.setCenter(attend()));








        // Add buttons to the GridPane in the required layout

        gridPane.add(home, 0, 0);
        gridPane.add(addUserButton, 1, 0);
        gridPane.add(coursesButton, 2, 0);





        return gridPane;


    }

    private VBox attend(){
        VBox attend =new VBox();
        attend.getChildren().addAll(nav(),Student_attendance(selected_code,selected_course));
        attend.setSpacing(70);

        return attend;


    }

    private VBox mark(){
        VBox mark=new VBox();
        mark.getChildren().addAll(nav(),marks());
        mark.setSpacing(30);

        return mark;

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
    public HBox studentpic(){

        HBox studentbox=new HBox();
        Image logo=new Image(this.getClass().getResource("/student.png").toExternalForm());
        ImageView logoview=new ImageView(logo);
        logoview.setFitHeight(100);
        logoview.setFitWidth(100);
        logoview.setPreserveRatio(true);

        studentbox.getChildren().addAll(logoview);


        return studentbox;
    }

    public BorderPane getPane() {
        return pane;
}
//showing attendance of particular student
    String url="jdbc:mysql://localhost:3306/university_system";
    String username="root";
    String password="0348jav.";

BorderPane Student_attendance(String selected_code,String selected_course){

    Text course_code=new Text(selected_code);
    course_code.setFont(Font.font("Arial",FontWeight.SEMI_BOLD,FontPosture.ITALIC,15));
    Text course_name=new Text(selected_course);
    course_name.setFont(Font.font("Arial",FontWeight.SEMI_BOLD,FontPosture.ITALIC,15));
    VBox vbox=new VBox(30,course_name,course_code);

    TableView tableView=new TableView();
    TableColumn col1=new TableColumn("Topic Name");
    TableColumn col2=new TableColumn("Status");
    TableColumn col3=new TableColumn("Date");

    ObservableList<Student> attendance_list=FXCollections.observableArrayList();
   // TableColumn
    //connection with database
    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection= DriverManager.getConnection(url,username,password);
        connection.setAutoCommit(false);
        Statement statement= connection.createStatement();
        ResultSet resultSet=statement.executeQuery("select *from attendance_info");

        while(resultSet.next()){
            if(selected_code.equalsIgnoreCase(resultSet.getString(3))&&
                    selected_course.equalsIgnoreCase(resultSet.getString(4))&&
                    mainLayout.getLogin_id().equalsIgnoreCase(resultSet.getString(2)))
            {
                attendance_list.add(new Student(
                    resultSet.getString(7),
                    resultSet.getString(5),
                    resultSet.getString(6)
            ));
            }
        }
        connection.commit();
        statement.close();
        resultSet.close();
        connection.close();
    }catch(Exception exception){

    }

    col1.setCellValueFactory(new PropertyValueFactory<>("topic"));
    col2.setCellValueFactory(new PropertyValueFactory<>("status"));
    col3.setCellValueFactory(new PropertyValueFactory<>("date"));


    if(attendance_list==null){
        System.out.println(" null attendance list");
    }
    tableView.setItems(attendance_list);

    col1.setPrefWidth(200);
    col2.setPrefWidth(200);
    col3.setPrefWidth(200);

    tableView.getColumns().addAll(col1,col2,col3);





    tableView.setPrefSize(250,300);

    tableView.setMinSize(300, 200); // Minimum size
    tableView.setMaxSize(700, 400);
       BorderPane borderpane=new BorderPane();
    borderpane.setCenter(tableView);
       BorderPane.setMargin(tableView,new Insets(100,100,100,100));
       borderpane.setTop(vbox);
    return borderpane;
}

//Registered Courses List
    VBox Registered_course(){
    TableView tableView=new TableView();
    TableColumn col1=new TableColumn("Course Code");
    TableColumn col2=new TableColumn("Course Name");
    TableColumn col3=new TableColumn("Credit Hrs");
    TableColumn col4=new TableColumn("Teacher");
    TableColumn col5=new TableColumn("Class");
    ObservableList <Course> reg_list=FXCollections.observableArrayList();
     try{
         Class.forName("com.mysql.cj.jdbc.Driver");
         Connection connection=DriverManager.getConnection(url,username,password);
         connection.setAutoCommit(false);
         Statement statement= connection.createStatement();
         ResultSet resultSet=statement.executeQuery("select *from Course inner join all_users on " +
                 "course.Semester=all_users.semester and course.dept=all_users.department and all_users.type='STUDENT'");
         while(resultSet.next()){
             if(mainLayout.getLogin_id().equalsIgnoreCase(resultSet.getString("id"))&&
                     mainLayout.getLogin_password().equalsIgnoreCase(resultSet.getString("password"))){
                 reg_list.add(new Course(
                         resultSet.getString(1),
                         resultSet.getString(2),
                         resultSet.getInt(3),
                         resultSet.getString(6),
                         resultSet.getString("section")
                 ));
             }
         }
         connection.commit();
         resultSet.close();
         statement.close();
         connection.close();


     }catch(Exception exception){
         exception.printStackTrace();
     }
     col1.setCellValueFactory(new PropertyValueFactory<>("course_code"));
     col2.setCellValueFactory(new PropertyValueFactory<>("name"));
     col3.setCellValueFactory(new PropertyValueFactory<>("hrs"));
     col4.setCellValueFactory(new PropertyValueFactory<>("instructor"));
     col5.setCellValueFactory(new PropertyValueFactory<>("class_name"));
     if(reg_list!=null){
         System.out.println("reg list is not null");
     }

        tableView.setItems(reg_list);

        col1.setPrefWidth(150);
        col2.setPrefWidth(150);
        col3.setPrefWidth(150);
        col4.setPrefWidth(150);
        col5.setPrefWidth(150);

        tableView.getColumns().addAll(col1,col2,col3,col4,col5);
        tableView.setPrefSize(600,400);
        tableView.setMinSize(300, 200); // Minimum size
        tableView.setMaxSize(750, 400);



        //  row specific behaviour
        tableView.setRowFactory(table->{
            TableRow<Course>tableRow=new TableRow<>();
            tableRow.setOnMouseClicked(event->{
                if(event.getClickCount()==2&&!tableRow.isEmpty()){
                     selected_code=tableRow.getItem().course_code.getValue().toString();
                     selected_course=tableRow.getItem().name.getValue().toString();

                    pane.setCenter(mainbord());
                    pane.setLeft(dashboardmain());

                }
            });
            return tableRow;
        });

    VBox vbox=new VBox(tableView);
    vbox.setAlignment(Pos.CENTER);
    return vbox;
    }
    private VBox mainbord(){
      VBox vbox=new VBox();
      vbox.getChildren().addAll(nav(),dashboardcentre());
      vbox.setSpacing(80);

      return vbox;

    }

    //Displaying marks
    VBox marks(){
        Text course_code=new Text(selected_code);
        course_code.setFont(Font.font("Arial",FontWeight.SEMI_BOLD,FontPosture.ITALIC,15));
        Text course_name=new Text(selected_course);
        course_name.setFont(Font.font("Arial",FontWeight.SEMI_BOLD,FontPosture.ITALIC,15));
        VBox vbox1=new VBox(30,course_name,course_code);


        TableView table=new TableView();
        TableColumn col1=new TableColumn("Type");
        TableColumn col2=new TableColumn("Title");
        TableColumn col3=new TableColumn("Total Marks");
        TableColumn col4=new TableColumn("Obtained Marks");
        TableColumn col5=new TableColumn("Date");

        ObservableList<Student> marks=FXCollections.observableArrayList();
        // TableColumn
        //connection with database
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select *from marks_table");

            while (resultSet.next()) {
                if (selected_code.equalsIgnoreCase(resultSet.getString(3)) &&
                        selected_course.equalsIgnoreCase(resultSet.getString(4)) &&
                        mainLayout.getLogin_id().equalsIgnoreCase(resultSet.getString(2))) {
                    marks.add(new Student(
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getDouble(7),
                            resultSet.getDouble(8),
                            resultSet.getString(9)
                    ));
                }
            }

            connection.commit();
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch(Exception exception){
            exception.printStackTrace();
        }
        col1.setCellValueFactory(new PropertyValueFactory<>("marks_type"));
        col2.setCellValueFactory(new PropertyValueFactory<>("title"));
        col3.setCellValueFactory(new PropertyValueFactory<>("total_m"));
        col4.setCellValueFactory(new PropertyValueFactory<>("obtained_m"));
        col5.setCellValueFactory(new PropertyValueFactory<>("date_marks"));
        table.setItems(marks);

        col1.setPrefWidth(200);
        col2.setPrefWidth(200);
        col3.setPrefWidth(200);
        col4.setPrefWidth(200);
        col5.setPrefWidth(200);

        table.getColumns().addAll(col1,col2,col3,col4,col5);

    VBox vbox=new VBox(vbox1,table);
    return vbox;
    }

    BorderPane Fee(){

    Label login_label=new Label("STUDENT ID");
    Label dept_label=new Label("STUDENT DEPARTMENT");
    Label fee_label=new Label("Fee Per Semester");

    HBox hBox=new HBox(40);
    hBox.getChildren().addAll(login_label,dept_label,fee_label);
       // VBox vbox=new VBox(30);
        HBox hbox2=new HBox(130);
    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection =DriverManager.getConnection(url,username,password);
        connection.setAutoCommit(false);
        Statement statement=connection.createStatement();
        ResultSet resultSet= statement.executeQuery("select *from fee_table inner join " +
                "all_users on all_users.department=fee_table.dept and all_users.type='STUDENT'");
        while(resultSet.next()){
            if(mainLayout.getLogin_id().equalsIgnoreCase(resultSet.getString(4))&&
            resultSet.getString(1).equalsIgnoreCase(resultSet.getString(9))){
                 Text login=new Text(mainLayout.getLogin_id());
                Text dept_txt=new Text(resultSet.getString(1));
                Text display_txt=new Text(String.valueOf(resultSet.getInt(2)));

              hbox2.getChildren().addAll(login,dept_txt,display_txt);
            }
        }
        connection.commit();
        resultSet.close();
        statement.close();
        connection.close();

    }catch(Exception exception){
        exception.printStackTrace();
    }
    VBox vbox1=new VBox(40);
    vbox1.setAlignment(Pos.CENTER);
        vbox1.setPadding(new Insets(100,100,100,100));

    vbox1.getChildren().addAll(hBox,hbox2);

    vbox1.setStyle("-fx-font-weight:bold;-fx-font-size:30px");

    BorderPane borderPane=new BorderPane();
    borderPane.setCenter(vbox1);
    return borderPane;
    }

}

