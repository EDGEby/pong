package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.PortUnreachableException;

public class Main extends Application {

    public static final int Win_Wdh=800;
    public static final int Win_Hgt=600;
    public static final int racket_Wdh=25;
    public static final int racket_Hgt=100;


    @Override
    public void start(Stage primaryStage){
        Canvas canvas = new Canvas(Win_Wdh, Win_Hgt);
        GraphicsContext graphicsContext= canvas.getGraphicsContext2D();
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(17),e ->run(graphicsContext)));
        timeline.setCycleCount(Timeline.INDEFINITE);
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



    }

    public static void main(String[] args) {
        launch(args);
    }
}
