package com.example.uni_project;

import javafx.beans.property.SimpleStringProperty;

    public class Course {
        SimpleStringProperty course_code;
        SimpleStringProperty name;
        int hrs;
        SimpleStringProperty instructor;
        SimpleStringProperty type;
        int semester;
        SimpleStringProperty dept;
        SimpleStringProperty class_name;

        public Course(String course_code,String course_name,int hrs,String instructor,String class_name){
            this.course_code = new SimpleStringProperty(course_code);
            this.name = new SimpleStringProperty(course_name);
            this.hrs = hrs;
            this.instructor = new SimpleStringProperty(instructor);
            this.class_name=new SimpleStringProperty(class_name);

        }
        public Course(String course_code, String name, int hrs, String instructor,
                      String type, int semester, String dept) {
            this.course_code = new SimpleStringProperty(course_code);
            this.name = new SimpleStringProperty(name);
            this.hrs = hrs;
            this.instructor = new SimpleStringProperty(instructor);
            this.type = new SimpleStringProperty(type);
            this.semester = semester;
            this.dept = new SimpleStringProperty(dept);
        }

        public String getCourse_code() {
            return course_code.get();
        }

        public SimpleStringProperty course_codeProperty() {
            return course_code;
        }

        public String getName() {
            return name.get();
        }

        public SimpleStringProperty nameProperty() {
            return name;
        }

        public int getHrs() {
            return hrs;
        }


        public String getInstructor() {
            return instructor.get();
        }

        public SimpleStringProperty instructorProperty() {
            return instructor;
        }

        public String getType() {
            return type.get();
        }

        public SimpleStringProperty typeProperty() {
            return type;
        }

        public int getSemester() {
            return semester;
        }

        public String getDept() {
            return dept.get();
        }

        public SimpleStringProperty deptProperty() {
            return dept;
        }

        public String getClass_name() {
            return class_name.get();
        }

        public SimpleStringProperty class_nameProperty() {
            return class_name;
        }

        public void setClass_name(String class_name) {
            this.class_name.set(class_name);
        }
    }

