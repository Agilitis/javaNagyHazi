import com.sun.tools.javac.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.util.Iterator;
import java.util.Map;

public class HighscoreWindow extends Scene {
    Button backButton = new Button("Back to Menu!");
    static FlowPane mainStackPane = new FlowPane(Orientation.HORIZONTAL);
    static TextArea textArea = new TextArea();

    private static HighscoreWindow instance = null;

    private HighscoreWindow(Parent root, double width, double height, Stage primaryStage) {
        super(root, width, height);
        mainStackPane.setPadding(new Insets(200, 500, 30, 350));
            backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(MainMenu.getInstance());
            }
        });
        mainStackPane.getChildren().addAll(backButton, textArea);

    }



    static HighscoreWindow getInstance(){
        System.out.println(getHighscoreText());
        textArea.setText(getHighscoreText());
        if(instance==null){
            instance = new HighscoreWindow(mainStackPane, 800, 600, Game.getStage());
        }
        return instance;
    }

    static String getHighscoreText(){
        String highscoreText = new String();
        Iterator it = DataStore.getInstance().getScores().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            highscoreText+=pair.getKey() + "      " + pair.getValue()+"\n";
        }
        return highscoreText;
    }

}
