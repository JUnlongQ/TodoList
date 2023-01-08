package dataModel;

import javafx.collections.FXCollections;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Store the todaData. This is a singleton class
 * It will keep the data for next use. TODO I will update it to database after I know how ^^
 */
public class TodoData {
  private static TodoData instance = new TodoData();
  private static String filename = "TodoListItems.txt";
  private DateTimeFormatter formatter;
  private List<TodoItem> todoItems;

  public static TodoData getInstance(){
    return instance;
  }
  private TodoData(){
    formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
  }

  public List<TodoItem> getTodoItems() {
    return todoItems;
  }

  public void loadTodoItems() throws IOException {
    todoItems = FXCollections.observableArrayList();
    Path path = Paths.get(filename);

    String input;

    try (BufferedReader bufferedReader = Files.newBufferedReader(path)){
      while((input = bufferedReader.readLine()) != null){
        String[] information = input.split("\t");

        String shortDescription = information[0];
        String details = information[1];
        String date = information[2];

        LocalDate localDate = LocalDate.parse(date, formatter);
        TodoItem todoItem = new TodoItem(shortDescription, details, localDate);
        todoItems.add(todoItem);
      }
    }
  }

  /**
   * TODO need to update it to sql after knowing how
   * @throws IOException IOEXCEPTION
   */
  public void storeTodoItems() throws IOException{
    Path path = Paths.get(filename);
    try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path)) {
      for (TodoItem item : todoItems) {
        bufferedWriter.write(String.format("%s\t%s\t%s",
            item.getShortDescription(),
            item.getDetails(),
            item.getDeadline().format(formatter)));
        bufferedWriter.newLine();
      }
    }
  }

}
