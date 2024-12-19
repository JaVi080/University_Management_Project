module com.example.uni_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires transitive mysql.connector.j;


    opens com.example.uni_project to javafx.fxml;
    exports com.example.uni_project;
}