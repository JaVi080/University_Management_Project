package com.example.demo;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.ToggleGroup;

public class Student {
    private SimpleStringProperty studentname;
    private SimpleStringProperty registration;
    SimpleBooleanProperty present = new SimpleBooleanProperty(false);

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
    public boolean isPresent() {
        return present.get();
    }

    public void setPresent(boolean isPresent) {
        this.present.set(isPresent);
    }

    public SimpleBooleanProperty presentProperty() {
        return present;
    }
}



