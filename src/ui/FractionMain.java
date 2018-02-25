package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FractionMain extends Application {

  public static void main(final String[] args) {
    launch(args);
  }

  @Override
  public void start(final Stage primaryStage) throws Exception {
    final Parent root = FXMLLoader.load(getClass().getResource("/fractionCalculator.fxml"));
    primaryStage.setTitle("Bruchrechner");
    primaryStage.setScene(new Scene(root, 600, 300));
    primaryStage.show();
  }
}
