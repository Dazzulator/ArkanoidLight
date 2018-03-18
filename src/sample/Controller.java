package sample;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.util.*;

public class Controller {

    int timer = 0;

    private Random random;

    private ImageView imageViewToRemove;
    private int integerToRemove;

    private MediaPlayer laserPlayer;

    private AnimationTimer playerAnimation;

    public DoubleProperty playerX = new SimpleDoubleProperty(0);
    private DoubleProperty playerVelocity = new SimpleDoubleProperty();
    private LongProperty lastUpdateTime = new SimpleLongProperty();
    private List<ImageView> enemies;
    private Map<ImageView, TranslateTransition> playerPairs;
    private Map<ImageView, TranslateTransition> enemyPairs;

    @FXML
    public Pane gameComponents;

    @FXML
    public ImageView player;

    @FXML
    public ImageView enemy1;

    @FXML
    public ImageView enemy2;

    @FXML
    public ImageView enemy3;

    @FXML
    public ImageView enemy4;

    @FXML
    public ImageView enemy5;

    @FXML
    public ImageView enemy6;

    @FXML
    public ImageView enemy7;

    @FXML
    public ImageView enemy8;

    @FXML
    public ImageView enemy9;

    @FXML
    public ImageView enemy10;

    @FXML
    public ImageView enemy11;

    @FXML
    public ImageView enemy12;

    @FXML
    public ImageView enemy13;

    @FXML
    public ImageView enemy14;

    @FXML
    public ImageView enemy15;

    @FXML
    public ImageView enemy16;

    @FXML
    public ImageView enemy17;

    @FXML
    public ImageView enemy18;

    @FXML
    public ImageView enemy19;

    @FXML
    public ImageView enemy20;

    @FXML
    public ImageView enemy21;

    @FXML
    public ImageView enemy22;

    @FXML
    public ImageView enemy23;

    @FXML
    public ImageView enemy24;

    @FXML
    public ImageView enemy25;

    @FXML
    public ImageView enemy26;

    @FXML
    public ImageView enemy27;

    @FXML
    private void initialize() {
        enemies = new ArrayList<>();
        enemies.add(enemy1);
        enemies.add(enemy2);
        enemies.add(enemy3);
        enemies.add(enemy4);
        enemies.add(enemy5);
        enemies.add(enemy6);
        enemies.add(enemy7);
        enemies.add(enemy8);
        enemies.add(enemy9);
        enemies.add(enemy10);
        enemies.add(enemy11);
        enemies.add(enemy12);
        enemies.add(enemy13);
        enemies.add(enemy14);
        enemies.add(enemy15);
        enemies.add(enemy16);
        enemies.add(enemy17);
        enemies.add(enemy18);
        enemies.add(enemy19);
        enemies.add(enemy20);
        enemies.add(enemy21);
        enemies.add(enemy22);
        enemies.add(enemy23);
        enemies.add(enemy24);
        enemies.add(enemy25);
        enemies.add(enemy26);
        enemies.add(enemy27);
        playerPairs = new HashMap<>();
        enemyPairs = new HashMap<>();
        random = new Random();
        playerAnimation = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (lastUpdateTime.get() > 0) {
                    final double elapsedSeconds = (now - lastUpdateTime.get()) / 1000000000.0;
                    final double deltaX = elapsedSeconds * playerVelocity.get();
                    final double oldX = player.getTranslateX();
                    final double newX = oldX + deltaX;
                    playerX.setValue(newX);
                }
                timer++;
                if (timer % 10 == 0) {
                    for (int i = 0; i < enemies.size(); i++) {
                        if (random.nextInt(100) > 97) {
                            ImageView enemyBeam = new ImageView(Controller.class.getResource("/laser.png").toString());
                            enemyBeam.setX(-100);
                            enemyBeam.setY(-100);
                            gameComponents.getChildren().add(enemyBeam);
                            TranslateTransition translateTransition = new TranslateTransition(new Duration(1000.0), enemyBeam);
                            enemyPairs.put(enemyBeam, translateTransition);
                            translateTransition.setFromX(enemies.get(i).getTranslateX() + enemies.get(i).getLayoutX() + 100);
                            translateTransition.setFromY(enemies.get(i).getTranslateY() + 40 + enemies.get(i).getLayoutY() + 100);
                            translateTransition.setToX(enemies.get(i).getTranslateX() + enemies.get(i).getLayoutX() + 100);
                            translateTransition.setToY(enemies.get(i).getTranslateY() + 800 + enemies.get(i).getLayoutY() + 100);
                            translateTransition.play();
                            translateTransition.setOnFinished((actionEvent) -> {
                                enemyPairs.remove(translateTransition.getNode());
                                gameComponents.getChildren().remove(enemyBeam);
                            });
                        }
                    }
                    timer = 0;
                }
                lastUpdateTime.set(now);
                checkForCollision();
            }
        };
        playerAnimation.start();
    }

    public void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.LEFT) {
            playerVelocity.set(-400);
        } else if (keyEvent.getCode() == KeyCode.RIGHT) {
            playerVelocity.set(400);
        } else if (keyEvent.getCode() == KeyCode.SPACE) {
            Media laser = new Media(Controller.class.getResource("/laser.wav").toString());
            laserPlayer = new MediaPlayer(laser);
            laserPlayer.play();
            ImageView beam = new ImageView(Controller.class.getResource("/beam.png").toString());
            gameComponents.getChildren().add(beam);
            TranslateTransition translateTransition = new TranslateTransition(new Duration(1000.0), beam);
            playerPairs.put(beam, translateTransition);
            translateTransition.setFromX(player.getTranslateX() + player.getLayoutX());
            translateTransition.setFromY(player.getTranslateY() - 40 + player.getLayoutY());
            translateTransition.setToX(player.getTranslateX() + player.getLayoutX());
            translateTransition.setToY(player.getTranslateY() - 800 + player.getLayoutY());
            translateTransition.play();
            translateTransition.setOnFinished((actionEvent) -> {
                playerPairs.remove(translateTransition.getNode());
                gameComponents.getChildren().remove(beam);
            });
        }
    }

    public void keyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.LEFT || keyEvent.getCode() == KeyCode.RIGHT) {
            playerVelocity.set(0);
        }
    }

    private void checkForCollision() {
        boolean flag = false;
        label:
        for (int i = 0; i < enemies.size(); i++) {
            for (ImageView imageView : playerPairs.keySet()) {
                if (imageView.getBoundsInParent().intersects(enemies.get(i).getBoundsInParent())) {
                    playerPairs.get(imageView).stop();
                    gameComponents.getChildren().remove(imageView);
                    gameComponents.getChildren().remove(enemies.get(i));
                    imageViewToRemove = imageView;
                    integerToRemove = i;
                    flag = true;
                    break label;
                }
            }
        }

        if (flag == true) {
            playerPairs.remove(imageViewToRemove);
            enemies.remove(integerToRemove);
        }

        for (ImageView imageView : enemyPairs.keySet()) {
            if (imageView.getBoundsInParent().intersects(player.getBoundsInParent())) {
                enemyPairs.get(imageView).stop();
                playerAnimation.stop();
                Alert gameOver = new Alert(Alert.AlertType.INFORMATION);
                gameOver.setTitle("Game Over");
                gameOver.setHeaderText(null);
                gameOver.setContentText("Oh no, you failed to save the galaxy!");
                gameOver.setX(player.getLayoutX() + 250);
                gameOver.setY(player.getLayoutY() - 250);
                gameOver.show();
                gameOver.setOnCloseRequest((event -> {
                    Platform.exit();
                }));
            }
        }

        if (enemies.size() == 0) {
            playerAnimation.stop();
            Alert gameOver = new Alert(Alert.AlertType.INFORMATION);
            gameOver.setTitle("Game Over");
            gameOver.setHeaderText(null);
            gameOver.setContentText("Congratulations, you saved the galaxy!");
            gameOver.setX(player.getLayoutX() + 250);
            gameOver.setY(player.getLayoutY() - 250);
            gameOver.show();
            gameOver.setOnCloseRequest((event -> {
                Platform.exit();
            }));
        }
    }
}
