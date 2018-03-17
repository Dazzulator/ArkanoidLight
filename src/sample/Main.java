package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    MediaPlayer musicPlayer;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("/sample/arkanoidLightLayout.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 1024, 768);

        Controller controller = fxmlLoader.getController();

        scene.setOnKeyPressed(controller::keyPressed);
        scene.setOnKeyReleased(controller::keyReleased);

        controller.player.translateXProperty().bind(controller.playerX);

        Media music = new Media(Controller.class.getResource("/music.wav").toString());
        musicPlayer = new MediaPlayer(music);
        musicPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                musicPlayer.seek(Duration.ZERO);
            }
        });
        musicPlayer.play();

        primaryStage.setTitle("Arkanoid Light");
        primaryStage.resizableProperty().setValue(Boolean.FALSE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
