package com.example.demo;

import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

public class RadioCell extends TableCell<Student,Void> {
       RadioButton presentRadioButton = new RadioButton("Present");
        RadioButton absentRadioButton = new RadioButton("Absent");


        public RadioCell(){
            presentRadioButton.setOnAction(e -> {
                if (presentRadioButton.isSelected()) {
                    absentRadioButton.setSelected(false);
                    Student student = getTableRow().getItem();
                    student.setPresent(true);
                }
            });

            absentRadioButton.setOnAction(e -> {
                if (absentRadioButton.isSelected()) {
                    presentRadioButton.setSelected(false);
                    Student student = getTableRow().getItem();
                    student.setPresent(false);
                }
            });

            // Add both buttons to an HBox
            HBox container = new HBox(10, presentRadioButton, absentRadioButton);
            setGraphic(container);

        }
        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null); // Remove content if cell is empty
            } else {
                // Display the RadioButton if cell is not empty
                Student student = getTableView().getItems().get(getIndex());
                presentRadioButton.setSelected(student.isPresent());
                absentRadioButton.setSelected(!student.isPresent());
            }
        }
    }
