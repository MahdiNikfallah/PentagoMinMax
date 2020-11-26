package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");
        Button btn = new Button("Hard");
        Button btn2 = new Button("Easy");
        btn.setStyle("-fx-background-color: red");
        btn.setTextFill(Paint.valueOf("White"));
        btn2.setStyle("-fx-background-color: lawngreen");
        btn2.setTextFill(Paint.valueOf("White"));
        btn2.setLayoutX(10);
        btn2.setLayoutY(10);
        btn2.setMinWidth(150);
        btn2.setMinHeight(150);

        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                board ezBoard= new board(2);
                ezBoard.start(primaryStage);
            }
        });

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                board hrdBoard= new board(3);
                hrdBoard.start(primaryStage);
            }
        });

        Pane root = new Pane();
        btn.setLayoutX(170);
        btn.setLayoutY(10);
        btn.setMinWidth(150);
        btn.setMinHeight(150);
        root.getChildren().addAll(btn,btn2);
        primaryStage.setScene(new Scene(root, 330, 170));
        primaryStage.show();
    }
//    public void start(Stage primaryStage) throws Exception{
//        StackPane root = new StackPane();
//        Scene scene = new Scene(root, 500, 500);
//        int difficulty= 3;
//        Button button1 = new Button("HARD");
//        Button button2 = new Button("EASY");
//        Button button3 = new Button("Accept");
//        button3.setLayoutX(110);
//        button3.setLayoutY(110);
//        root.getChildren().add(button1);
//        root.getChildren().add(button2);
//        root.getChildren().add(button3);
//
//        scene.setOnMousePressed((MouseEvent event) -> {
//            primaryStage.close();
//            board K = new board(difficulty);
//            K.start(primaryStage);
//        });
//
//        primaryStage.setScene(scene);
//        primaryStage.show();
//
//    }


    public static void main(String[] args) {
        launch(args);
    }
}
