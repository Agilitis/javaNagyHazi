import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;


public class Game extends Application {

    public static final double speedYAcceleration = 0.7;
    public static final double speedXAcceleration = 0.7;

    static Stage getStage() {
        return stage;
    }

    private int playerScore = 0;
    private int speedY = 1;
    private int speedX = 1;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private double playerY = HEIGHT / 2;
    private double AIY = HEIGHT / 2;
    private double ballX = WIDTH / 2;
    private double ballY = HEIGHT / 2;
    private static final int PLAYER_HEIGHT = 100;
    private static final int PLAYER_WIDTH = 15;
    private static final double RADIUS = 15;


    public int getPlayerScore() {
        return playerScore;
    }

    public int getAIScore() {
        return AIScore;
    }

    private int AIScore = 0;
    private boolean isStarted;
    private int playerOneXPos = 0;
    private double playerTwoXPos = WIDTH - PLAYER_WIDTH;


    static Stage stage = null;

    public static Timeline getTimeline() {
        return timeline;
    }

    static Timeline timeline = null;

    public static Canvas getCanvas() {
        return canvas;
    }

    static Canvas canvas;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Hello World!");
        stage = primaryStage;

        canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        canvas.setFocusTraversable(true);
        canvas.addEventFilter(MouseEvent.ANY, (e) -> canvas.requestFocus());
        timeline = new Timeline(new KeyFrame(Duration.millis(10), e -> run(gc)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        canvas.setOnMouseMoved(e ->  playerY = e.getY());
        canvas.setOnMouseClicked(e ->  isStarted = true);
        canvas.setOnKeyPressed(e -> {
            System.out.println(e.getCode());
            if (e.getCode().toString().equals("ESCAPE")) {
                stage.setScene(MainMenu.getInstance());
                isStarted = false;
                DataStore.getInstance().addScore("Player", playerScore);
                DataStore.getInstance().addScore("AI", AIScore);

            }
        });

        stage.setScene(MainMenu.getInstance());

        primaryStage.show();
    }

    private void run(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, WIDTH, HEIGHT);
        gc.setFill(Color.WHITE);
        gc.setFont(Font.font(25));
        if(isStarted) {
            ballX += speedX;
            ballY += speedY;
            if(ballX < WIDTH - WIDTH / 4) {
                AIY = ballY - PLAYER_HEIGHT / 2;
            }  else {
                AIY =  ballY > AIY + PLAYER_HEIGHT / 2 ? AIY += 1.5: AIY - 1.5;
            }
            int random = (int)(Math.random()*1000);
            System.out.println(random);

            if(random%197==0){
                speedY+=1;
            }else if(random%196==0){
                speedY-=1;
            }
            gc.fillOval(ballX, ballY, RADIUS, RADIUS);
        } else {
            gc.setStroke(Color.YELLOW);
            gc.setTextAlign(TextAlignment.CENTER);
            gc.strokeText("Click here to start the fun!", WIDTH / 2, HEIGHT / 2);
            ballX = WIDTH / 2;
            ballY = HEIGHT / 2;
            speedX = new Random().nextInt(2) == 0 ? 1: -1;
            speedY = new Random().nextInt(2) == 0 ? 1: -1;
        }

        //Hitting floor or ground
        if(ballY + RADIUS > HEIGHT || ballY < 0){
            speedY *=-1;
        }

        if(ballX < playerOneXPos - PLAYER_WIDTH) {
            AIScore++;
            isStarted = false;
        }
        if(ballX - RADIUS > playerTwoXPos + PLAYER_WIDTH) {
            playerScore++;
            isStarted = false;
        }
        if (((ballX + RADIUS > playerTwoXPos)
                && ballY >= AIY
                && ballY <= AIY + PLAYER_HEIGHT)
                ||
                ((ballX < playerOneXPos + PLAYER_WIDTH)
                        && ballY >= playerY
                        && ballY <= playerY + PLAYER_HEIGHT)) {
            speedY += speedYAcceleration *Math.signum(speedY);
            speedX += speedXAcceleration *Math.signum(speedX);
            speedX *= -1;
//            speedY *= -1;
        }
        gc.fillText("Player: "+playerScore + "\t\t\tadj sok pontot Balázs\t\t\t" + "AI:"+AIScore , WIDTH / 2, 100);
        gc.fillRect(playerTwoXPos, AIY, PLAYER_WIDTH, PLAYER_HEIGHT);
        gc.fillRect(playerOneXPos, playerY, PLAYER_WIDTH, PLAYER_HEIGHT);
    }




}
