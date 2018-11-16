import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainMenu extends Scene {
    Button playButton = new Button("Play!");
    Button highscoreButton = new Button("Highscore");
    static FlowPane mainStackPane = new FlowPane(Orientation.HORIZONTAL);
    private static MainMenu instance = null;

    private MainMenu(Parent root, double width, double height, Stage primaryStage) {
        super(root, width, height);
        mainStackPane.setPadding(new Insets(200, 500, 30, 350));
        mainStackPane.getChildren().addAll(playButton, highscoreButton);
        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Game.getStage().show();
                Game.getTimeline().play();
                primaryStage.setScene(new Scene(new StackPane(Game.getCanvas())));
            }
        });

        highscoreButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(HighscoreWindow.getInstance());
            }
        });

    }




    static MainMenu getInstance(){
        if(instance==null){
            instance = new MainMenu(mainStackPane, 800, 600, Game.getStage());
        }
        return instance;
    }

}
