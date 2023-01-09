package com.example.todolist;

import dataModel.TodoData;
import dataModel.TodoItem;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;


public class DialogController {
  @FXML
  private TextField shortDescriptionField;
  @FXML
  private TextArea detailArea;
  @FXML
  private DatePicker deadlinePicker;

  public TodoItem processResult(){
    String shortDescription = shortDescriptionField.getText().trim();
    String details = detailArea.getText().trim();
    LocalDate date = deadlinePicker.getValue();
    TodoItem newItem = new TodoItem(shortDescription, details, date);
    TodoData.getInstance().addTodoItem(newItem);
    return newItem;

  }
}
