package com.example.demo3;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class Main extends Application {

    public static final int Win_Wdh=800;
    public static final int Win_Hgt=600;
    public static final int racket_Wdh=25;
    public static final int racket_Hgt=100;
    public static final int BALL_RADIUS=15;
    private  static Random random;
    private boolean gameStated;
    int ballSpeedX=1;
    int ballSpeedY=1;
    int ballXPos=400;
    int ballYPos=400;
    private double leftPlayerYPos = Win_Hgt/2;
    private double rightPlayerYPos = Win_Hgt/2;
    private int leftScore=0;
    private int rightScore=0;

    private double leftPlayerXPos=10;
    private double rightPlayerXPos = Win_Wdh - racket_Wdh-10;

    @Override
    public void start(Stage primaryStage){
        Canvas canvas = new Canvas(Win_Wdh, Win_Hgt);
        GraphicsContext graphicsContext= canvas.getGraphicsContext2D();
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(17),e ->run(graphicsContext)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        primaryStage.setScene(new Scene(new StackPane(canvas)));
        canvas.setOnMouseClicked(e -> gameStated = true);
        canvas.setOnMouseMoved(e -> leftPlayerYPos =e.getY());
        primaryStage.setScene(new Scene(new StackPane(canvas)));
        primaryStage.show();
        timeline.play();




        Group group = new Group();
        Scene scene= new Scene(group, Win_Wdh,Win_Hgt);
        scene.setFill(Color.MEDIUMPURPLE);
        primaryStage.setTitle("Sample application");
        primaryStage.setScene(scene);

      /*  Line line = new Line();
        line.setStartX(400);
        line.setStartY(0);
        line.setEndX(400);
        line.setEndY(600);
        line.setStrokeWidth(5);
        line.setStroke(Color.WHITE);
        group.getChildren().add(line);*/


      /*  Circle circle = new Circle(Win_Wdh/2, Win_Hgt/2,15.0);
        circle.setStroke(Color.PAPAYAWHIP);
        circle.setFill(Color.DARKSALMON);
        group.getChildren().add(circle);
*/
        Rectangle leftRacket = new Rectangle(racket_Wdh,racket_Hgt);
        leftRacket.setX(racket_Wdh);
        leftRacket.setY((Win_Hgt-racket_Hgt)/2);
        leftRacket.setFill(Color.DARKSLATEGREY);
        group.getChildren().add(leftRacket);


        Rectangle rightRacket = new Rectangle(racket_Wdh,racket_Hgt);
        rightRacket.setX(Win_Wdh-racket_Wdh*2);
        rightRacket.setY((Win_Hgt-racket_Hgt)/2);
        rightRacket.setFill(Color.DARKSLATEGREY);
        group.getChildren().add(rightRacket);

        primaryStage.show();
    }

    private void run(GraphicsContext graphicsContext){
        graphicsContext.moveTo(Win_Wdh/2,0);
        graphicsContext.lineTo(Win_Wdh/2, Win_Hgt);


        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(0,0,Win_Wdh,Win_Hgt);
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillRect(Win_Wdh/2-2,0,4,Win_Hgt);
        if (gameStated){
            ballXPos+=ballSpeedX;
            ballYPos+=ballSpeedY;
            if (ballXPos< leftPlayerXPos){
                rightScore++;
                gameStated=false;
            }
            if (ballXPos>rightPlayerXPos){
                leftScore++;
                gameStated=false;
            }
            if (ballYPos<0 || ballYPos>Win_Hgt){
                ballSpeedY = -ballSpeedY;
            }
            //ball hit racket
            if (((ballXPos + BALL_RADIUS> rightPlayerXPos) && ballYPos>=rightPlayerYPos && ballYPos <= rightPlayerYPos + racket_Hgt)){
                ballSpeedX+=1*Math.signum(ballSpeedY);
                ballSpeedY+=1*Math.signum(ballSpeedX);
                ballSpeedX= -ballSpeedX;
                ballSpeedY= -ballSpeedY;
            }
            if (((ballXPos<leftPlayerXPos + racket_Wdh)&& ballYPos>= leftPlayerYPos && ballYPos<= leftPlayerXPos+ BALL_RADIUS)){
                ballSpeedX+=1*Math.signum(ballSpeedY);
                ballSpeedY+=1*Math.signum(ballSpeedX);
                ballSpeedX= -ballSpeedX;
                ballSpeedY= -ballSpeedY;
            }

            // AI-Player
            if (ballXPos < 3*Win_Wdh / 4 ){
                rightPlayerYPos = ballYPos-racket_Hgt/2;
            }
            else{
                rightPlayerYPos = ballYPos> rightPlayerYPos + racket_Hgt/2 ? rightPlayerYPos +=1: rightPlayerYPos -1;
            }
            graphicsContext.fillOval(ballXPos,ballYPos,BALL_RADIUS,BALL_RADIUS);
        }
        else {
            graphicsContext.setStroke(Color.YELLOW);
            graphicsContext.setTextAlign(TextAlignment.CENTER);
            graphicsContext.strokeText("Click to Start", Win_Wdh/2,Win_Hgt/2);
            ballXPos=Win_Wdh/2;
            ballYPos=Win_Hgt/2;
            if (random.nextInt(2)==0)
                ballSpeedX=1;
            else
                ballSpeedX= -1;
            if (random.nextInt(2)==0)
                ballSpeedY=1;
            else
                ballSpeedY= -1;
        }
        graphicsContext.fillText(leftScore + "\t\t\t\t\t" + rightScore, Win_Wdh/2,100);
        graphicsContext.fillRect(rightPlayerXPos,rightPlayerYPos,racket_Wdh,racket_Hgt);
        graphicsContext.fillRect(leftPlayerXPos, leftPlayerYPos,Win_Wdh,racket_Hgt);


    }

    public static void main(String[] args) {
        launch(args);
    }
}