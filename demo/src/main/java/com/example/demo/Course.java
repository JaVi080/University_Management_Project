package com.example.demo;

import javafx.beans.property.SimpleStringProperty;

    public class Course {
        SimpleStringProperty course_code;
        SimpleStringProperty name;
        int hrs;
        SimpleStringProperty instructor;
        SimpleStringProperty type;
        int semester;
        SimpleStringProperty dept;

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
    }

