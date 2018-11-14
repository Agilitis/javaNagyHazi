import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;


public class GameWindow extends Scene {

    Button playButton = new Button("Play!");
    Button highscoreButton = new Button("Highscore");
    static FlowPane mainStackPane = new FlowPane(Orientation.HORIZONTAL);
    private static GameWindow instance = null;

    private GameWindow(Parent root, double width, double height, Stage primaryStage) {
        super(root, width, height);
        mainStackPane.setPadding(new Insets(200, 500, 30, 350));
        mainStackPane.getChildren().addAll(playButton, highscoreButton);
        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(instance);
            }
        });

        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(instance);
            }
        });

    }





    static GameWindow getInstance(){
        if(instance==null){
            instance = new GameWindow(mainStackPane, 800, 600, Game.getStage());
        }
        return instance;
    }

}
