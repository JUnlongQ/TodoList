package com.example.todolist;

import dataModel.TodoData;
import dataModel.TodoItem;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class Controller {
  @FXML
  private ListView<TodoItem> todoItemsListView;

  @FXML
  private TextArea itemDetailsTextArea;

  @FXML
  private Label dealineLabel;
  @FXML
  private BorderPane mainBorderPane;

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

    todoItemsListView.setItems(TodoData.getInstance().getTodoItems());
    todoItemsListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    todoItemsListView.getSelectionModel().selectFirst();
  }
  @FXML
  public void showNewItemDialog() {
    Dialog<ButtonType> dialog = new Dialog<>(); // it is modal by default
    dialog.initOwner(mainBorderPane.getScene().getWindow());
    dialog.setTitle("Add a new event");
    dialog.setHeaderText("Use this dialog to create a new event");
    FXMLLoader fxmlLoader = new FXMLLoader();
    fxmlLoader.setLocation(getClass().getResource("todoItemDialog.fxml"));
    try {
      dialog.getDialogPane().setContent(fxmlLoader.load());
    } catch (IOException e){
      System.out.println("Could not load the dialog");
      e.printStackTrace();
      return;
    }
    dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
    dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
    Optional<ButtonType> result = dialog.showAndWait();
    if(result.isPresent() && result.get() == ButtonType.OK){
      DialogController controller = fxmlLoader.getController();
      TodoItem newItem = controller.processResult();
      todoItemsListView.getSelectionModel().select(newItem);

    }

  }

//  @FXML
//  public void handleClickListView() {
//    TodoItem item = todoItemsListView.getSelectionModel().getSelectedItem();
//    itemDetailsTextArea.setText(item.getDetails());
//    dealineLabel.setText(item.getDeadline().toString());
//  }
}