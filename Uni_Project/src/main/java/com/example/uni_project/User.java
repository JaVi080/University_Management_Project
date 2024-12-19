package com.example.uni_project;

import javafx.beans.property.SimpleStringProperty;

public class User {
    SimpleStringProperty id;
    SimpleStringProperty type;
    SimpleStringProperty name;
    SimpleStringProperty password;
    SimpleStringProperty department;
    int semester;
    SimpleStringProperty dob;

    public User(String type, String id,String name,
                String password, String department,int semester, String dob) {
        this.type = new SimpleStringProperty(type);
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.password = new SimpleStringProperty(password);
        this.department=new SimpleStringProperty(department);
        this.semester = semester;
        this.dob=new SimpleStringProperty(dob);
    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public int getSemester() {
        return semester;
    }

    public String getDob() {
        return dob.get();
    }

    public SimpleStringProperty dobProperty() {
        return dob;
    }

    public String getDepartment() {
        return department.get();
    }

    public SimpleStringProperty departmentProperty() {
        return department;
    }
}
