package sample;

import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.util.Random;

public class circle extends StackPane{

    private Random random = new Random();
    //circle with radius = 35 place for marbles
    Circle c = new Circle(35);
    static AudioClip As, Alose;

    public circle() {

        Alose = new AudioClip(this.getClass().getResource("/lose.mp3").toString());
        As = new AudioClip(this.getClass().getResource("/s.mp3").toString());

        c.setFill(null);
        c.setFill(Color.RED);
        getChildren().add(c);

        setOnMouseClicked(event -> {
            //left click
            if (event.getButton() == MouseButton.PRIMARY) {
                //check that circles are empty to put marbles
                if (c.getFill() != Color.BLACK & c.getFill() != Color.WHITE) {
                    //check end game / check if black turn / check for rotation (if rotation happend can fill circles)
                    if (!board.end & board.BlackTurn & board.rotation) {
                        board.BlackTurn = false;
                        //sound of fill circles
                        AudioClip a = new AudioClip(this.getClass().getResource("/s.mp3").toString());
                        a.play();

                        drawBlack();
                        fillCircle();
                        //check if black win
                        if (board.checkwin(1)) {
                            //sound of winning
                            a = new AudioClip(this.getClass().getResource("win.mp3").toString());
                            a.play();
                            board.end = true;
                            return;
                        }
                        board.rotation = false;
                        board.rotateflag = true;
                    }
                }
            }
//            if (event.getButton() == MouseButton.SECONDARY) {
//                white_turn();
//            }
        });
    }

    //draw black circle
    public void drawBlack() {
        c.setFill(Color.BLACK);
    }

    //draw white circle
    public void drawWhite() {
        c.setFill(Color.WHITE);
    }

    //return color of circle
    public Paint getcolor() {
        return c.getFill();
    }

    //set marble 1 if color of circle is black or 2 if color of circle is white
    public void fillCircle() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (board.c[j][i].getcolor() == Color.BLACK) {
                    board.marble[i][j] = 1;
                }
            }
        }
    }

    public static void white_turn() {
        //check end game / check if white turn / check for rotation (if rotation happend can fill circles)
        if (!board.end & !board.BlackTurn & board.rotation) {
//                        root.setStyle("-fx-background-color: black");
//                    } else {
//                        root.setStyle("-fx-background-color: white");
//                    }
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {
                    board.marbleflag[i][j] = 0;
                }
            }
            board.a = Integer.MIN_VALUE;
            board.b = Integer.MAX_VALUE;
            board.ohold = Integer.MAX_VALUE;
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {
                    if (board.marble[i][j] == 0 & board.marbleflag[i][j] == 0) {
                        board.marble[i][j] = 2;
                        board.marbleflag[i][j] = 1;
                        for (int k = 0; k < 8; k++) {
                            board.rot(k);
                            board.BlackTurn = true;
                            board.v = board.minimax_alpha(0, board.a, board.b);
                            if (board.v < board.b) {
                                board.b = board.v;
                            }
                            //////////////////////
                            if (k % 2 == 0) {
                                board.rot(k + 1);
                            } else {
                                board.rot(k - 1);
                            }

                            if (board.v < board.ohold) {
                                board.khold = k;
                                board.ihold = i;
                                board.jhold = j;
                                board.ohold = board.v;
                            }
                            if (board.a >= board.b) {
                                break;
                            }
                        }
                        board.marble[i][j] = 0;
                    }
                    if (board.a >= board.b) {
                        break;
                    }
                }
                if (board.a >= board.b) {
                    break;
                }
            }
            board.marble[board.ihold][board.jhold] = 2;
            //sound of fill circles
            As.play();
            board.c[board.jhold][board.ihold].drawWhite();
            //check if white win
            if (board.checkwin(2)) {
                //sound of winning
                Alose.play();

                board.end = true;
            }
            board.BlackTurn = true;
            board.rotateflag = true;
            board.rotation = false;
            switch (board.khold) {
                case 0:
                    board.rot_animation(0);
                    break;
                case 1:
                    board.rot_animation(1);
                    break;
                case 2:
                    board.rot_animation(2);
                    break;
                case 3:
                    board.rot_animation(3);
                    break;
                case 4:
                    board.rot_animation(4);
                    break;
                case 5:
                    board.rot_animation(5);
                    break;
                case 6:
                    board.rot_animation(6);
                    break;
                case 7:
                    board.rot_animation(7);
                    break;
                default:
                    break;
            }

        }
    }


}
