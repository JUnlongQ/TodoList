package com.example.todolist;

import dataModel.TodoData;
import dataModel.TodoItem;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class Controller {
  private List<TodoItem> todoItemList;

  @FXML
  private ListView<TodoItem> todoItemsListView;

  @FXML
  private TextArea itemDetailsTextArea;

  @FXML
  private Label dealineLabel;

  public void initialize(){
    todoItemsListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TodoItem>() {
      @Override
      public void changed(ObservableValue<? extends TodoItem> observableValue, TodoItem todoItem, TodoItem t1) {
        if(t1 != null){
          TodoItem item = todoItemsListView.getSelectionModel().getSelectedItem();
          itemDetailsTextArea.setText(item.getDetails());
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
          dealineLabel.setText(formatter.format(item.getDeadline()));
        }
      }
    });

    todoItemsListView.getItems().setAll(TodoData.getInstance().getTodoItems());
    todoItemsListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    todoItemsListView.getSelectionModel().selectFirst();
  }

  @FXML
  public void handleClickListView() {
    TodoItem item = todoItemsListView.getSelectionModel().getSelectedItem();
    itemDetailsTextArea.setText(item.getDetails());
    dealineLabel.setText(item.getDeadline().toString());
  }
}