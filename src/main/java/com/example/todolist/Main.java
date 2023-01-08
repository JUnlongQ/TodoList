package com.example.todolist;

import dataModel.TodoData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("todoList.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 900, 500);
    stage.setTitle("Todo List");
    stage.setScene(scene);
    stage.show();
  }

  @Override
  public void init() throws Exception {
    try{
      TodoData.getInstance().loadTodoItems();
    } catch (IOException e){
      System.out.println(e.getMessage());
    }
  }

  @Override
  public void stop() throws Exception {
    try{
      TodoData.getInstance().storeTodoItems();
    } catch (IOException e){
      System.out.println(e.getMessage());
    }
  }

  public static void main(String[] args) {
    launch();
  }
}