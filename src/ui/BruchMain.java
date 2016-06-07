package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BruchMain extends Application {

    @Override
    public void start(final Stage primaryStage) throws Exception{
        final Parent root = FXMLLoader.load(getClass().getResource("/bruchRechner.fxml"));
        primaryStage.setTitle("Bruch-Rechner");
        primaryStage.setScene(new Scene(root, 600, 300));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

