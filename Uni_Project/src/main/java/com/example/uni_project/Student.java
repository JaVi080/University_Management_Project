package com.example.uni_project;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.ToggleGroup;

public class Student {
     SimpleStringProperty studentname;
   SimpleStringProperty registration;
    SimpleBooleanProperty present =new SimpleBooleanProperty(false);
    SimpleStringProperty status;
    SimpleStringProperty date;
    SimpleStringProperty topic;
    SimpleDoubleProperty marks;
    //for marks
    SimpleStringProperty marks_type;
    SimpleStringProperty title;
    SimpleDoubleProperty total_m;
    SimpleDoubleProperty obtained_m;
    SimpleStringProperty date_marks;

    public Student(String marks_type,String title,Double total_m,Double obtained_m,String date_marks){
        this.marks_type=new SimpleStringProperty(marks_type);
        this.title=new SimpleStringProperty(title);
        this.total_m=new SimpleDoubleProperty(total_m);
        this.obtained_m=new SimpleDoubleProperty(obtained_m);
        this.date_marks=new SimpleStringProperty(date_marks);
    }

    public Student(String topic, String status, String date){
        this.topic=new SimpleStringProperty(topic);
        this.status=new SimpleStringProperty(status);
        this.date=new SimpleStringProperty(date);
    }
    public Student(String studentname,String registration) {
        this.studentname =new SimpleStringProperty(studentname);
        this.registration=new SimpleStringProperty(registration);
        this.marks=new SimpleDoubleProperty(0.0);

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
    public boolean isPresent() {
        return present.get();
    }

    public void setPresent(boolean isPresent) {
        this.present.set(isPresent);
    }

    public SimpleBooleanProperty presentProperty() {
        return present;
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getTopic() {
        return topic.get();
    }

    public SimpleStringProperty topicProperty() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic.set(topic);
    }

    public SimpleDoubleProperty getMarks() {
        return marks;
    }

    public void setMarks(SimpleDoubleProperty marks) {
        this.marks = marks;
    }

    public SimpleDoubleProperty marksProperty() {
        return marks;
    }

    public String getMarks_type() {
        return marks_type.get();
    }

    public SimpleStringProperty marks_typeProperty() {
        return marks_type;
    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public double getTotal_m() {
        return total_m.get();
    }

    public SimpleDoubleProperty total_mProperty() {
        return total_m;
    }

    public double getObtained_m() {
        return obtained_m.get();
    }

    public SimpleDoubleProperty obtained_mProperty() {
        return obtained_m;
    }

    public String getDate_marks() {
        return date_marks.get();
    }

    public SimpleStringProperty date_marksProperty() {
        return date_marks;
    }
}



