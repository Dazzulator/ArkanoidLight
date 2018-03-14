package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("/sample/arkanoidLightLayout.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 1024, 768);
        primaryStage.setTitle("Arkanoid Light");
        primaryStage.resizableProperty().setValue(Boolean.FALSE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
