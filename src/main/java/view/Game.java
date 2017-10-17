package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Game extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("elements.fxml"));
        primaryStage.setTitle("White Knight");
        primaryStage.setResizable(false);

        Scene scene = new Scene(root, 1200, 900);
        primaryStage.setScene(scene);
        scene.getStylesheets().add((Game.class.getResource("view/styles.css").toExternalForm()));
        primaryStage.show();

    }
}