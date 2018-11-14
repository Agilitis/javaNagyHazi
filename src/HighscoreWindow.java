import com.sun.tools.javac.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class HighscoreWindow extends Scene {
    Button backButton = new Button("Back to Menu!");
    static FlowPane mainStackPane = new FlowPane(Orientation.HORIZONTAL);
    private static HighscoreWindow instance = null;

    private HighscoreWindow(Parent root, double width, double height, Stage primaryStage) {
        super(root, width, height);
        mainStackPane.setPadding(new Insets(200, 500, 30, 350));
        mainStackPane.getChildren().addAll(backButton);

        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(MainMenu.getInstance());
            }
        });



    }




    static HighscoreWindow getInstance(){
        if(instance==null){
            instance = new HighscoreWindow(mainStackPane, 800, 600, Game.getStage());
        }
        return instance;
    }

}
