package com.example.demo;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class faculty_attendance extends Application {

    public void start(Stage primaryStage) throws Exception{

        BorderPane pane=new BorderPane();
        TableView<Student> table=new TableView();// it is for viewing a simple table

        // three colums to be added
        TableColumn<Student,String> c1=new TableColumn("FirstName");
        TableColumn <Student,String>c2=new TableColumn("Registration no");
        TableColumn<Student,Void> c3=new TableColumn("Present");
        TableColumn <Student,Void>c4=new TableColumn("Absent");


        c1.setCellValueFactory(new PropertyValueFactory("studentname"));
        c2.setCellValueFactory(new PropertyValueFactory("registration"));
        c3.setCellFactory( col -> new Radiocell());
        c4.setCellFactory( col -> new Radiocell());





        table.setPrefWidth(300);
        table.setPrefHeight(300);

        c1.setPrefWidth(150);
        c2.setPrefWidth(150);
        c3.setPrefWidth(100);
        c4.setPrefWidth(100);

        table.getColumns().addAll(c1,c2,c3,c4);

        ObservableList<Student> data = FXCollections.observableArrayList(
                new Student("Alice", "SP24-BSE-058"),
                new Student("Bob", "SP23-BSE-056"),
                new Student("Charlie", "FSE-BGT-086")
        );
        table.setItems(data);


        pane.setCenter(table);



        Scene scene = new Scene(pane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("University System");
        primaryStage.show();
    }

    private class Radiocell extends TableCell<Student,Void>{
        private final RadioButton radiobutton;
        private final ToggleGroup toggleGroups=new ToggleGroup();

        public Radiocell(){
            this.radiobutton=new RadioButton();
            this.setGraphic(radiobutton);
            this.selectedProperty().addListener((obs, oldItem, newItem) -> {
                if (isSelected()) {
                    int selectedIndex = getIndex();
                    System.out.println("Selected row index: " + selectedIndex);
                }

                radiobutton.setToggleGroup(toggleGroups);


            });

        }
        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null); // Remove content if cell is empty
            } else {
                setGraphic(radiobutton); // Display the RadioButton if cell is not empty
                radiobutton.setSelected(toggleGroups.getSelectedToggle() == radiobutton);
            }
        }



    }

    private class Student{
        private SimpleStringProperty studentname;
        private SimpleStringProperty registration;
        public Student(String studentname,String registration) {
            this.studentname =new SimpleStringProperty(studentname);
            this.registration=new SimpleStringProperty(registration);
        }

        public void setRegistration(String registration) {
            this.registration.set(registration);
        }

        public void setStudentname(String studentname) {
            this.studentname.set(studentname);
        }

        public String getRegistration() {
            return registration.get();
        }

        public SimpleStringProperty registrationProperty() {
            return registration;
        }

        public String getStudentname() {
            return studentname.get();
        }

        public SimpleStringProperty studentnameProperty() {
            return studentname;
    }
}

}
