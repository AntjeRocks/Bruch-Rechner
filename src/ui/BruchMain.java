package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.IllegalFormatException;

public class BruchMain extends Application {

  public static void main(final String[] args) {
    launch(args);
  }

  @Override
  public void start(final Stage primaryStage) throws Exception {
    final Parent root = FXMLLoader.load(getClass().getResource("/bruchRechner.fxml"));
    primaryStage.setTitle("Bruch-Rechner");
    primaryStage.setScene(new Scene(root, 600, 300));
    primaryStage.show();
  }
}
