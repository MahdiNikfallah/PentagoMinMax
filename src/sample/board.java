package sample;

import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class board {

    private static int difficulty;

    public board(int difficulty){
        this.difficulty= difficulty;
    }

    circle c1 = new circle();
    static circle[][] c = new circle[6][6];
    static int[][] marble = new int[6][6];//2 = white, 1 = black
    public static int[][] marbleflag = new int[6][6];
    public static int[][] Bflag = new int[6][6];
    public static int[][] Wflag = new int[6][6];
    private Random random = new Random();

    static boolean BlackTurn = true;
    static boolean end = false;
    static boolean rotation = true;
    static boolean rotateflag;
    static boolean flag = true;
    static int v, a, b;
    static int ihold, jhold, ohold, khold;
    static int counter = 0;

    static AnchorPane root = new AnchorPane();
    static Pane p1 = new Pane();
    static Pane p2 = new Pane();
    static Pane p3 = new Pane();
    static Pane p4 = new Pane();

    static AudioClip Ac, Awin, Alose;

    public void start(Stage stage) {

        //sound
        Ac = new AudioClip(this.getClass().getResource("c.mp3").toString());
        Awin = new AudioClip(this.getClass().getResource("lose.mp3").toString());
        Alose = new AudioClip(this.getClass().getResource("win.mp3").toString());

        root.setPrefSize(700, 750);
        root.setStyle("-fx-background-color: black");


        p1.setPrefSize(300, 300);
        p1.setStyle("-fx-background-color: #329; -fx-background-radius: 20;");
        p1.setLayoutX(48.0);
        p1.setLayoutY(48.0);

        p2.setPrefSize(300, 300);
        p2.setStyle("-fx-background-color: #329; -fx-background-radius: 20;");
        p2.setLayoutX(352.0);
        p2.setLayoutY(48.0);

        p3.setPrefSize(300, 300);
        p3.setStyle("-fx-background-color: #329; -fx-background-radius: 20;");
        p3.setLayoutX(48.0);
        p3.setLayoutY(352.0);

        p4.setPrefSize(300, 300);
        p4.setStyle("-fx-background-color: #329; -fx-background-radius: 20;");
        p4.setLayoutX(352.0);
        p4.setLayoutY(352.0);

        //*******
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                c[i][j] = new circle();
            }
        }

        //
        drawboard();

        Button b1 = new Button("<<<<<<");
        Button b2 = new Button(">>>>>>");
        Button b3 = new Button("<<<<<<");
        Button b4 = new Button(">>>>>>");
        Button b5 = new Button("<<<<<<");
        Button b6 = new Button(">>>>>>");
        Button b7 = new Button("<<<<<<");
        Button b8 = new Button(">>>>>>");

        b1.setStyle("-fx-background-color: red");
        b1.setTextFill(Paint.valueOf("White"));
        b1.setRotate(-90);
        b1.setLayoutX(0);
        b1.setLayoutY(186);

        b2.setStyle("-fx-background-color: red");
        b2.setTextFill(Paint.valueOf("White"));
        b2.setLayoutX(174);
        b2.setLayoutY(17);

        b3.setStyle("-fx-background-color: red");
        b3.setTextFill(Paint.valueOf("White"));
        b3.setLayoutX(478);
        b3.setLayoutY(17);

        b4.setStyle("-fx-background-color: red");
        b4.setTextFill(Paint.valueOf("White"));
        b4.setRotate(90);
        b4.setLayoutX(635);
        b4.setLayoutY(186);

        b5.setStyle("-fx-background-color: red");
        b5.setTextFill(Paint.valueOf("White"));
        b5.setRotate(90);
        b5.setLayoutX(0);
        b5.setLayoutY(490);

        b6.setStyle("-fx-background-color: red");
        b6.setTextFill(Paint.valueOf("White"));
        b6.setLayoutX(174);
        b6.setLayoutY(658);

        b7.setStyle("-fx-background-color: red");
        b7.setTextFill(Paint.valueOf("White"));
        b7.setLayoutX(478);
        b7.setLayoutY(658);

        b8.setStyle("-fx-background-color: red");
        b8.setTextFill(Paint.valueOf("White"));
        b8.setRotate(-90);
        b8.setLayoutX(635);
        b8.setLayoutY(490);

        Button enter = new Button("             GO              \n");
        enter.setStyle("-fx-background-color: darkorange");
        enter.setTextFill(Paint.valueOf("White"));
        enter.setLayoutX(10);
        enter.setLayoutY(680);
        enter.minWidth(680);
        enter.prefWidth(680);
        enter.prefHeight(180);
        enter.minHeight(180);
        enter.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.PRIMARY){
                circle.white_turn();
            }
        });
        b1.setOnAction((ActionEvent event) -> {
            rot_animation(0);
        });

        b2.setOnAction((ActionEvent event) -> {
            rot_animation(1);
        });

        b3.setOnAction((ActionEvent event) -> {
            rot_animation(2);
        });

        b4.setOnAction((ActionEvent event) -> {
            rot_animation(3);
        });

        b5.setOnAction((ActionEvent event) -> {
            rot_animation(4);
        });

        b6.setOnAction((ActionEvent event) -> {
            rot_animation(5);
        });

        b7.setOnAction((ActionEvent event) -> {
            rot_animation(6);
        });

        b8.setOnAction((ActionEvent event) -> {
            rot_animation(7);
        });

        root.getChildren().addAll(p1, p2, p3, p4, b1, b2, b3, b4, b5, b6, b7, b8,enter);
        Scene s = new Scene(root);
        stage.setScene(s);
        stage.show();
    }

    public static void rot_animation(int x) {
        switch (x) {
            case 0:
                //rotate one time
                if (!end & rotateflag) {
                    //sound of rotation
                    Ac.play();
                    //animation of rotation
                    RotateTransition rotate = new RotateTransition(javafx.util.Duration.seconds(1.2), p1);
                    rotate.setByAngle(-90);
                    rotate.play();
                    rot(0);
                    cir1_270();

                    if (checkwin(1)) {
                        //sound of winning
                        Awin.play();
                        end = true;
                        return;
                    } else if (checkwin(2)) {
                        //sound of winning
                        Alose.play();
                        end = true;
                        return;
                    }

                    rotation = true;
                    rotateflag = false;
                    //change background color to show turn
                    if (BlackTurn) {
                        root.setStyle("-fx-background-color: black");
                    } else {
                        root.setStyle("-fx-background-color: white");
                    }
                }
                break;
            case 1:
                if (!end & rotateflag) {
                    Ac.play();
                    RotateTransition rotate = new RotateTransition(javafx.util.Duration.seconds(1.2), p1);
                    rotate.setByAngle(90);
                    rotate.play();
                    rot(1);
                    cir1_90();
                    if (checkwin(1)) {
                        Awin.play();
                        end = true;
                        return;
                    } else if (checkwin(2)) {
                        Alose.play();
                        end = true;
                        return;
                    }
                    rotation = true;
                    rotateflag = false;
                    if (BlackTurn) {
                        root.setStyle("-fx-background-color: black");
                    } else {
                        root.setStyle("-fx-background-color: white");
                    }
                }
                break;
            case 2:
                if (!end & rotateflag) {
                    Ac.play();
                    RotateTransition rotate = new RotateTransition(javafx.util.Duration.seconds(1.2), p2);
                    rotate.setByAngle(-90);
                    rotate.play();
                    rot(2);
                    cir2_270();
                    if (checkwin(1)) {
                        Awin.play();
                        end = true;
                        return;
                    } else if (checkwin(2)) {
                        Alose.play();
                        end = true;
                        return;
                    }
                    rotation = true;
                    rotateflag = false;
                    if (BlackTurn) {
                        root.setStyle("-fx-background-color: black");
                    } else {
                        root.setStyle("-fx-background-color: white");
                    }
                }
                break;
            case 3:
                if (!end & rotateflag) {
                    Ac.play();
                    RotateTransition rotate = new RotateTransition(javafx.util.Duration.seconds(1.2), p2);
                    rotate.setByAngle(90);
                    rotate.play();
                    rot(3);
                    cir2_90();
                    if (checkwin(1)) {
                        Awin.play();
                        end = true;
                        return;
                    } else if (checkwin(2)) {
                        Alose.play();
                        end = true;
                        return;
                    }
                    rotation = true;
                    rotateflag = false;
                    if (BlackTurn) {
                        root.setStyle("-fx-background-color: black");
                    } else {
                        root.setStyle("-fx-background-color: white");
                    }
                }
                break;
            case 4:
                if (!end & rotateflag) {
                    Ac.play();
                    RotateTransition rotate = new RotateTransition(javafx.util.Duration.seconds(1.2), p3);
                    rotate.setByAngle(90);
                    rotate.play();
                    rot(4);
                    cir3_90();
                    if (checkwin(1)) {
                        Awin.play();
                        end = true;
                        return;
                    } else if (checkwin(2)) {
                        Alose.play();
                        end = true;
                        return;
                    }
                    rotation = true;
                    rotateflag = false;
                    if (BlackTurn) {
                        root.setStyle("-fx-background-color: black");
                    } else {
                        root.setStyle("-fx-background-color: white");
                    }
                }
                break;
            case 5:
                if (!end & rotateflag) {
                    Ac.play();
                    RotateTransition rotate = new RotateTransition(javafx.util.Duration.seconds(1.2), p3);
                    rotate.setByAngle(-90);
                    rotate.play();
                    rot(5);
                    cir3_270();
                    if (checkwin(1)) {
                        Awin.play();
                        end = true;
                        return;
                    } else if (checkwin(2)) {
                        Alose.play();
                        end = true;
                        return;
                    }
                    rotation = true;
                    rotateflag = false;
                    if (BlackTurn) {
                        root.setStyle("-fx-background-color: black");
                    } else {
                        root.setStyle("-fx-background-color: white");
                    }
                }
                break;
            case 6:
                if (!end & rotateflag) {
                    Ac.play();
                    RotateTransition rotate = new RotateTransition(javafx.util.Duration.seconds(1.2), p4);
                    rotate.setByAngle(90);
                    rotate.play();
                    rot(6);
                    cir4_90();
                    if (checkwin(1)) {
                        Awin.play();
                        end = true;
                        return;
                    } else if (checkwin(2)) {
                        Alose.play();
                        end = true;
                        return;
                    }
                    rotation = true;
                    rotateflag = false;
                    if (BlackTurn) {
                        root.setStyle("-fx-background-color: black");
                    } else {
                        root.setStyle("-fx-background-color: white");
                    }
                }
                break;
            case 7:
                if (!end & rotateflag) {
                    Ac.play();
                    RotateTransition rotate = new RotateTransition(javafx.util.Duration.seconds(1.2), p4);
                    rotate.setByAngle(-90);
                    rotate.play();
                    rot(7);
                    cir4_270();
                    if (checkwin(1)) {
                        Awin.play();
                        end = true;
                        return;
                    } else if (checkwin(2)) {
                        Alose.play();
                        end = true;
                        return;
                    }
                    rotation = true;
                    rotateflag = false;
                    if (BlackTurn) {
                        root.setStyle("-fx-background-color: black");
                    } else {
                        root.setStyle("-fx-background-color: white");
                    }
                }
                break;
            default:
                break;
        }
    }

    public static int minimax_alpha(int depth, int a, int b) {
        if (checkwin(1)) {
            int ahh;
            ahh= Integer.MAX_VALUE;
            return ahh;
        } else if (checkwin(2)) {
            return Integer.MIN_VALUE;
        } else {
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {
                    if (marble[i][j] == 0) {
                        flag = false;
                    }
                }
            }
            if (flag) {
                System.out.println("DRAW BABY");
                return 0;
            }
            flag = true;
        }
        if (depth == difficulty) {
            return findValue();
        }

        if (BlackTurn) {
            BlackTurn = false;
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {
                    if (marble[i][j] == 0 & Bflag[i][j] == 0) {
                        marble[i][j] = 1;
                        Bflag[i][j] = 1;
                        for (int k = 0; k < 8; k++) {
                            rot(k);
                            BlackTurn = false;
                            v = minimax_alpha(depth + 1, a, b);
                            if (v > a) {
                                a = v;
                            }
                            //////////////////////
                            if (k % 2 == 0) {
                                rot(k + 1);
                            } else {
                                rot(k - 1);
                            }

                            if (a >= b) {
                                break;
                            }
                        }
                        marble[i][j] = 0;
                    }
                    if (a >= b) {
                        break;
                    }
                }
                if (a >= b) {
                    break;
                }
            }
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {
                    Bflag[i][j] = 0;
                }
            }
            return a;
        } else {
            BlackTurn = true;
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {
                    if (marble[i][j] == 0 & Wflag[i][j] == 0) {
                        marble[i][j] = 2;
                        Wflag[i][j] = 1;
                        for (int k = 0; k < 8; k++) {
                            rot(k);
                            BlackTurn = true;
                            v = minimax_alpha(depth + 1, a, b);
                            if (v < b) {
                                b = v;
                            }
                            ///////////////////
                            if (k % 2 == 0) {
                                rot(k + 1);
                            } else {
                                rot(k - 1);
                            }

                            if (a >= b) {
                                break;
                            }
                        }
                        marble[i][j] = 0;
                    }
                    if (a >= b) {
                        break;
                    }
                }
                if (a >= b) {
                    break;
                }
            }
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {
                    Wflag[i][j] = 0;
                }
            }
            return b;
        }
    }

    //draw pentago board
    public void drawboard() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (i < 3 & j < 3) {
                    c[i][j].setLayoutX(92.5 * i + 22.5);
                    c[i][j].setLayoutY(92.5 * j + 22.5);
                    p1.getChildren().add(c[i][j]);
                }
                if (i > 2 & i < 6 & j < 3) {
                    c[i][j].setLayoutX(92.5 * (i - 3) + 22.5);
                    c[i][j].setLayoutY(92.5 * j + 22.5);
                    p2.getChildren().add(c[i][j]);
                }
                if (i < 3 & j > 2 & j < 6) {
                    c[i][j].setLayoutX(92.5 * i + 22.5);
                    c[i][j].setLayoutY(92.5 * (j - 3) + 22.5);
                    p3.getChildren().add(c[i][j]);
                }
                if (i > 2 & i < 6 & j > 2 & j < 6) {
                    c[i][j].setLayoutX(92.5 * (i - 3) + 22.5);
                    c[i][j].setLayoutY(92.5 * (j - 3) + 22.5);
                    p4.getChildren().add(c[i][j]);
                }
            }
        }
    }

    //check winning of black if x = 1 and white if x = 2
    public static boolean checkwin(int x) {
        //check row
        for (int i = 0; i < 6; i++) {
            if ((marble[i][0] == x & marble[i][1] == x & marble[i][2] == x & marble[i][3] == x & marble[i][4] == x)
                    | (marble[i][1] == x & marble[i][2] == x & marble[i][3] == x & marble[i][4] == x & marble[i][5] == x)) {
                return true;
            }
        }
        //check column
        for (int i = 0; i < 6; i++) {
            if ((marble[0][i] == x & marble[1][i] == x & marble[2][i] == x & marble[3][i] == x & marble[4][i] == x)
                    | (marble[1][i] == x & marble[2][i] == x & marble[3][i] == x & marble[4][i] == x & marble[5][i] == x)) {
                return true;
            }
        }
        //check diameters
        if (marble[0][0] == x & marble[1][1] == x & marble[2][2] == x & marble[3][3] == x & marble[4][4] == x) {
            return true;
        }
        if (marble[1][1] == x & marble[2][2] == x & marble[3][3] == x & marble[4][4] == x & marble[5][5] == x) {
            return true;
        }
        if (marble[1][0] == x & marble[2][1] == x & marble[3][2] == x & marble[4][3] == x & marble[5][4] == x) {
            return true;
        }
        if (marble[0][1] == x & marble[1][2] == x & marble[2][3] == x & marble[3][4] == x & marble[4][5] == x) {
            return true;
        }
        if (marble[5][0] == x & marble[4][1] == x & marble[3][2] == x & marble[2][3] == x & marble[1][4] == x) {
            return true;
        }
        if (marble[4][1] == x & marble[3][2] == x & marble[2][3] == x & marble[1][4] == x & marble[0][5] == x) {
            return true;
        }
        if (marble[4][0] == x & marble[3][1] == x & marble[2][2] == x & marble[1][3] == x & marble[0][4] == x) {
            return true;
        }
        if (marble[5][1] == x & marble[4][2] == x & marble[3][3] == x & marble[2][4] == x & marble[1][5] == x) {
            return true;
        }
        return false;
    }

    public static boolean chech_4_in_a_row(int x) {
        //check row
        for (int i = 0; i < 6; i++) {
            if ((marble[i][0] == x & marble[i][1] == x & marble[i][2] == x & marble[i][3] == x)
                    | (marble[i][1] == x & marble[i][2] == x & marble[i][3] == x & marble[i][4] == x)
                    | (marble[i][2] == x & marble[i][3] == x & marble[i][4] == x & marble[i][5] == x)) {
                return true;
            }
        }
        //check column
        for (int i = 0; i < 6; i++) {
            if ((marble[0][i] == x & marble[1][i] == x & marble[2][i] == x & marble[3][i] == x)
                    | (marble[1][i] == x & marble[2][i] == x & marble[3][i] == x & marble[4][i] == x)
                    | (marble[2][i] == x & marble[3][i] == x & marble[4][i] == x & marble[5][i] == x)) {
                return true;
            }
        }
        //check diameters
        if (marble[0][0] == x & marble[1][1] == x & marble[2][2] == x & marble[3][3] == x) {
            return true;
        }
        if (marble[1][1] == x & marble[2][2] == x & marble[3][3] == x & marble[4][4] == x) {
            return true;
        }
        if (marble[2][2] == x & marble[3][3] == x & marble[4][4] == x & marble[5][5] == x) {
            return true;
        }
        if (marble[1][0] == x & marble[2][1] == x & marble[3][2] == x & marble[4][3] == x) {
            return true;
        }
        if (marble[2][1] == x & marble[3][2] == x & marble[4][3] == x & marble[5][4] == x) {
            return true;
        }
        if (marble[0][1] == x & marble[1][2] == x & marble[2][3] == x & marble[3][4] == x) {
            return true;
        }
        if (marble[1][2] == x & marble[2][3] == x & marble[3][4] == x & marble[4][5] == x) {
            return true;
        }
        if (marble[5][0] == x & marble[4][1] == x & marble[3][2] == x & marble[2][3] == x) {
            return true;
        }
        if (marble[4][1] == x & marble[3][2] == x & marble[2][3] == x & marble[1][4] == x) {
            return true;
        }
        if (marble[3][2] == x & marble[2][3] == x & marble[1][4] == x & marble[0][5] == x) {
            return true;
        }
        if (marble[4][0] == x & marble[3][1] == x & marble[2][2] == x & marble[1][3] == x) {
            return true;
        }
        if (marble[3][1] == x & marble[2][2] == x & marble[1][3] == x & marble[0][4] == x) {
            return true;
        }
        if (marble[5][1] == x & marble[4][2] == x & marble[3][3] == x & marble[2][4] == x) {
            return true;
        }
        if (marble[4][2] == x & marble[3][3] == x & marble[2][4] == x & marble[1][5] == x) {
            return true;
        }
        return false;
    }

    public static boolean chech_3_in_a_row(int x) {
        //check row
        for (int i = 0; i < 6; i++) {
            if ((marble[i][0] == x & marble[i][1] == x & marble[i][2] == x)
                    | (marble[i][1] == x & marble[i][2] == x & marble[i][3] == x)
                    | (marble[i][2] == x & marble[i][3] == x & marble[i][4] == x)
                    | (marble[i][3] == x & marble[i][4] == x & marble[i][5] == x)) {
                return true;
            }
        }
        //check column
        for (int i = 0; i < 6; i++) {
            if ((marble[0][i] == x & marble[1][i] == x & marble[2][i] == x)
                    | (marble[1][i] == x & marble[2][i] == x & marble[3][i] == x)
                    | (marble[2][i] == x & marble[3][i] == x & marble[4][i] == x)
                    | (marble[3][i] == x & marble[4][i] == x & marble[5][i] == x)) {
                return true;
            }
        }
        //check diameters
        if (marble[0][0] == x & marble[1][1] == x & marble[2][2] == x) {
            return true;
        }
        if (marble[1][1] == x & marble[2][2] == x & marble[3][3] == x) {
            return true;
        }
        if (marble[2][2] == x & marble[3][3] == x & marble[4][4] == x) {
            return true;
        }
        if (marble[3][3] == x & marble[4][4] == x & marble[5][5] == x) {
            return true;
        }
        if (marble[1][0] == x & marble[2][1] == x & marble[3][2] == x) {
            return true;
        }
        if (marble[2][1] == x & marble[3][2] == x & marble[4][3] == x) {
            return true;
        }
        if (marble[3][2] == x & marble[4][3] == x & marble[5][4] == x) {
            return true;
        }
        if (marble[0][1] == x & marble[1][2] == x & marble[2][3] == x) {
            return true;
        }
        if (marble[1][2] == x & marble[2][3] == x & marble[3][4] == x) {
            return true;
        }
        if (marble[2][3] == x & marble[3][4] == x & marble[4][5] == x) {
            return true;
        }
        if (marble[5][0] == x & marble[4][1] == x & marble[3][2] == x) {
            return true;
        }
        if (marble[4][1] == x & marble[3][2] == x & marble[2][3] == x) {
            return true;
        }
        if (marble[3][2] == x & marble[2][3] == x & marble[1][4] == x) {
            return true;
        }
        if (marble[2][3] == x & marble[1][4] == x & marble[0][5] == x) {
            return true;
        }
        if (marble[4][0] == x & marble[3][1] == x & marble[2][2] == x) {
            return true;
        }
        if (marble[3][1] == x & marble[2][2] == x & marble[1][3] == x) {
            return true;
        }
        if (marble[2][2] == x & marble[1][3] == x & marble[0][4] == x) {
            return true;
        }
        if (marble[5][1] == x & marble[4][2] == x & marble[3][3] == x) {
            return true;
        }
        if (marble[4][2] == x & marble[3][3] == x & marble[2][4] == x) {
            return true;
        }
        if (marble[3][3] == x & marble[2][4] == x & marble[1][5] == x) {
            return true;
        }
        return false;
    }

    public static void rot(int x) {
        int temp, temp1;
        switch (x) {
            case 0:
                //rot1_270
                temp = marble[0][0];
                marble[0][0] = marble[0][2];
                temp1 = marble[2][0];
                marble[2][0] = temp;
                marble[0][2] = marble[2][2];
                marble[2][2] = temp1;
                //
                temp = marble[1][0];
                marble[1][0] = marble[0][1];
                temp1 = marble[2][1];
                marble[2][1] = temp;
                marble[0][1] = marble[1][2];
                marble[1][2] = temp1;
                break;
            case 1:
                //rot1_90
                temp = marble[0][0];
                marble[0][0] = marble[2][0];
                temp1 = marble[0][2];
                marble[0][2] = temp;
                marble[2][0] = marble[2][2];
                marble[2][2] = temp1;
                //
                temp = marble[0][1];
                marble[0][1] = marble[1][0];
                temp1 = marble[1][2];
                marble[1][2] = temp;
                marble[1][0] = marble[2][1];
                marble[2][1] = temp1;
                break;
            case 2:
                //rot2_270
                temp = marble[0][3];
                marble[0][3] = marble[0][5];
                temp1 = marble[2][3];
                marble[2][3] = temp;
                marble[0][5] = marble[2][5];
                marble[2][5] = temp1;
                //
                temp = marble[0][4];
                marble[0][4] = marble[1][5];
                temp1 = marble[1][3];
                marble[1][3] = temp;
                marble[1][5] = marble[2][4];
                marble[2][4] = temp1;
                break;
            case 3:
                //rot2_90
                temp = marble[0][5];
                marble[0][5] = marble[0][3];
                temp1 = marble[2][5];
                marble[2][5] = temp;
                marble[0][3] = marble[2][3];
                marble[2][3] = temp1;
                //
                temp = marble[1][5];
                marble[1][5] = marble[0][4];
                temp1 = marble[2][4];
                marble[2][4] = temp;
                marble[0][4] = marble[1][3];
                marble[1][3] = temp1;
                break;
            case 4:
                //rot3_90
                temp = marble[3][2];
                marble[3][2] = marble[3][0];
                temp1 = marble[5][2];
                marble[5][2] = temp;
                marble[3][0] = marble[5][0];
                marble[5][0] = temp1;
                //
                temp = marble[5][1];
                marble[5][1] = marble[4][2];
                temp1 = marble[4][0];
                marble[4][0] = temp;
                marble[4][2] = marble[3][1];
                marble[3][1] = temp1;
                break;
            case 5:
                //rot3_270
                temp = marble[3][0];
                marble[3][0] = marble[3][2];
                temp1 = marble[5][0];
                marble[5][0] = temp;
                marble[3][2] = marble[5][2];
                marble[5][2] = temp1;
                //
                temp = marble[5][1];
                marble[5][1] = marble[4][0];
                temp1 = marble[4][2];
                marble[4][2] = temp;
                marble[4][0] = marble[3][1];
                marble[3][1] = temp1;
                break;
            case 6:
                //rot4_90
                temp = marble[3][5];
                marble[3][5] = marble[3][3];
                temp1 = marble[5][5];
                marble[5][5] = temp;
                marble[3][3] = marble[5][3];
                marble[5][3] = temp1;
                //
                temp = marble[4][5];
                marble[4][5] = marble[3][4];
                temp1 = marble[5][4];
                marble[5][4] = temp;
                marble[3][4] = marble[4][3];
                marble[4][3] = temp1;
                break;
            case 7:
                //rot4_270
                temp = marble[3][5];
                marble[3][5] = marble[5][5];
                temp1 = marble[3][3];
                marble[3][3] = temp;
                marble[5][5] = marble[5][3];
                marble[5][3] = temp1;
                //
                temp = marble[4][5];
                marble[4][5] = marble[5][4];
                temp1 = marble[3][4];
                marble[3][4] = temp;
                marble[5][4] = marble[4][3];
                marble[4][3] = temp1;
                break;
            default:
                break;
        }
    }

    public static void cir1_270() {
        circle t = c[0][0];
        c[0][0] = c[2][0];
        circle t1 = c[0][2];
        c[0][2] = t;
        c[2][0] = c[2][2];
        c[2][2] = t1;
        //
        t = c[1][0];
        c[1][0] = c[2][1];
        t1 = c[0][1];
        c[0][1] = t;
        c[2][1] = c[1][2];
        c[1][2] = t1;
    }

    public static void cir1_90() {
        circle t = c[0][0];
        c[0][0] = c[0][2];
        circle t1 = c[2][0];
        c[2][0] = t;
        c[0][2] = c[2][2];
        c[2][2] = t1;
        //
        t = c[1][0];
        c[1][0] = c[0][1];
        t1 = c[2][1];
        c[2][1] = t;
        c[0][1] = c[1][2];
        c[1][2] = t1;
    }

    public static void cir2_270() {
        circle t = c[3][2];
        c[3][2] = c[3][0];
        circle t1 = c[5][2];
        c[5][2] = t;
        c[3][0] = c[5][0];
        c[5][0] = t1;
        //
        t = c[5][1];
        c[5][1] = c[4][2];
        t1 = c[4][0];
        c[4][0] = t;
        c[4][2] = c[3][1];
        c[3][1] = t1;
    }

    public static void cir2_90() {
        circle t = c[3][0];
        c[3][0] = c[3][2];
        circle t1 = c[5][0];
        c[5][0] = t;
        c[3][2] = c[5][2];
        c[5][2] = t1;
        //
        t = c[5][1];
        c[5][1] = c[4][0];
        t1 = c[4][2];
        c[4][2] = t;
        c[4][0] = c[3][1];
        c[3][1] = t1;
    }

    public static void cir3_270() {
        circle t = c[0][3];
        c[0][3] = c[2][3];
        circle t1 = c[0][5];
        c[0][5] = t;
        c[2][3] = c[2][5];
        c[2][5] = t1;
        //
        t = c[0][4];
        c[0][4] = c[1][3];
        t1 = c[1][5];
        c[1][5] = t;
        c[1][3] = c[2][4];
        c[2][4] = t1;
    }

    public static void cir3_90() {
        circle t = c[0][3];
        c[0][3] = c[0][5];
        circle t1 = c[2][3];
        c[2][3] = t;
        c[0][5] = c[2][5];
        c[2][5] = t1;
        //
        t = c[0][4];
        c[0][4] = c[1][5];
        t1 = c[1][3];
        c[1][3] = t;
        c[1][5] = c[2][4];
        c[2][4] = t1;
    }

    public static void cir4_270() {
        circle t = c[3][5];
        c[3][5] = c[3][3];
        circle t1 = c[5][5];
        c[5][5] = t;
        c[3][3] = c[5][3];
        c[5][3] = t1;
        //
        t = c[4][5];
        c[4][5] = c[3][4];
        t1 = c[5][4];
        c[5][4] = t;
        c[3][4] = c[4][3];
        c[4][3] = t1;
    }

    public static void cir4_90() {
        circle t = c[3][5];
        c[3][5] = c[5][5];
        circle t1 = c[3][3];
        c[3][3] = t;
        c[5][5] = c[5][3];
        c[5][3] = t1;
        //
        t = c[4][5];
        c[4][5] = c[5][4];
        t1 = c[3][4];
        c[3][4] = t;
        c[5][4] = c[4][3];
        c[4][3] = t1;
    }

    private static final int BLACK = 2;
    private static final int WHITE = 1;
    private static final int EMPTY = 0;

    private static int checkRow(int row) {
        int[][] board = marble;
        ArrayList<Integer> wb = new ArrayList<>();
        int b = 0, w = 0;
        int bInRow = 1;
        int wInRow = 1;
        for (int i = 1; i < board.length; i++) {
            int current = board[row][i], previous = board[row][i - 1];
            if (current == previous && current != EMPTY) {
                if (current == BLACK) {
                    bInRow++;
                    if (bInRow == 3) {
                        b += 100;
                    } else if (bInRow == 4) {
                        b += 900;
                    } else if (bInRow == 5) {
                        b += 99000;
                    }
                } else {
                    wInRow++;
                    if (wInRow == 3) {
                        w += 100;
                    } else if (wInRow == 4) {
                        w += 900;
                    } else if (wInRow == 5) {
                        w += 99000;
                    }
                }
            } else {
                if (current == BLACK) {
                    bInRow = 1;
                    wInRow = 0;
                } else if (current == WHITE) {
                    wInRow = 1;
                    bInRow = 0;
                } else {
                    bInRow = 0;
                    wInRow = 0;
                }
            }
        }

        return w - b;
    }

    /*
        Helper method that check a column to update the value of the node.
     */
    private static int checkColumn(int column) {
        int board[][] = marble;
        ArrayList<Integer> wb = new ArrayList<>();
        int b = 0, w = 0;
        int bInRow = 1;
        int wInRow = 1;
        for (int i = 1; i < board.length; i++) {
            int current = board[i][column], previous = board[i - 1][column];
            if (current == previous && current != EMPTY) {
                if (current == BLACK) {
                    bInRow++;
                    if (bInRow == 3) {
                        b += 100;
                    } else if (bInRow == 4) {
                        b += 900;
                    } else if (bInRow == 5) {
                        b += 99000;
                    }
                } else {
                    wInRow++;
                    if (wInRow == 3) {
                        w += 100;
                    } else if (wInRow == 4) {
                        w += 900;
                    } else if (wInRow == 5) {
                        w += 99000;
                    }
                }
            } else {
                if (current == BLACK) {
                    bInRow = 1;
                    wInRow = 0;
                } else if (current == WHITE) {
                    wInRow = 1;
                    bInRow = 0;
                } else {
                    bInRow = 0;
                    wInRow = 0;
                }
            }
        }
        return w - b;
    }

    /*
        Helper method that check a diagonal to update the value of the node.
     */
    private static int checkDiag(int start, int stop, int offset) {
        ArrayList<Integer> wb = new ArrayList<>();
        int[][] board = marble;
        int b = 0, w = 0;
        int bInRow = 1;
        int wInRow = 1;

        for (int i = start; i <= stop; i++) {
            int current = board[i + (1 - offset)][i], previous = board[i - offset][i - 1];
            if (current == previous && current != EMPTY) {
                if (current == BLACK) {
                    bInRow++;
                    if (bInRow == 3) {
                        b += 100;
                    } else if (bInRow == 4) {
                        b += 900;
                    } else if (bInRow == 5) {
                        b += 99000;
                    }
                } else {
                    wInRow++;
                    if (wInRow == 3) {
                        w += 100;
                    } else if (wInRow == 4) {
                        w += 900;
                    } else if (wInRow == 5) {
                        w += 99000;
                    }
                }
            } else {
                if (current == BLACK) {
                    bInRow = 1;
                    wInRow = 0;
                } else if (current == WHITE) {
                    wInRow = 1;
                    bInRow = 0;
                } else {
                    bInRow = 0;
                    wInRow = 0;
                }
            }
        }
        return w - b;
    }

    /*
        Helper method that check an anti diagonal to update the value of the node.
     */
    private static int checkAnti(int start, int stop, int offset) {
        int[][] board = marble;
        ArrayList<Integer> wb = new ArrayList<>();
        int b = 0, w = 0;
        int bInRow = 1;
        int wInRow = 1;
        for (int i = start; i <= stop; i++) {
            int current = board[offset - i][i], previous = board[offset + 1 - i][i - 1];
            if (current == previous && current != EMPTY) {
                if (current == BLACK) {
                    bInRow++;
                    if (bInRow == 3) {
                        b += 100;
                    } else if (bInRow == 4) {
                        b += 900;
                    } else if (bInRow == 5) {
                        b += 99000;
                    }
                } else {
                    wInRow++;
                    if (wInRow == 3) {
                        w += 100;
                    } else if (wInRow == 4) {
                        w += 900;
                    } else if (wInRow == 5) {
                        w += 99000;
                    }
                }
            } else {
                if (current == BLACK) {
                    bInRow = 1;
                    wInRow = 0;
                } else if (current == WHITE) {
                    wInRow = 1;
                    bInRow = 0;
                } else {
                    bInRow = 0;
                    wInRow = 0;
                }
            }
        }

        return w - b;
    }

    private static int checkMid(int quad) {
        int[][] board = marble;
        ArrayList<Integer> wb = new ArrayList<>();
        int b = 0;
        int w = 0;
        if (quad == 1) {
            if (board[1][1] == BLACK) {
                b += 5;
            }
            if (board[1][1] == WHITE) {
                w += 5;
            }
        } else if (quad == 2) {
            if (board[1][4] == BLACK) {
                b += 5;
            }
            if (board[1][4] == WHITE) {
                w += 5;
            }
        } else if (quad == 3) {
            if (board[4][1] == BLACK) {
                b += 5;
            }
            if (board[4][1] == WHITE) {
                w += 5;
            }
        } else if (quad == 4) {
            if (board[4][4] == BLACK) {
                b += 5;
            }
            if (board[4][4] == WHITE) {
                w += 5;
            }
        }

        return w - b;
    }

    /*
        Check all 18 ways a player can win for values, 10 for 3 in a row, 20 for 4 in a row and 100 for 5 in a row
     */
    private static int findValue() {

        int value=0;


        value += checkMid(1);

        value += checkMid(2);

        value += checkMid(3);

        value += checkMid(4);


        value += checkRow(0);

        value += checkRow(1);

        value += checkRow(2);

        value += checkRow(3);

        value += checkRow(4);

        value += checkRow(5);


        value += checkColumn(0);

        value += checkColumn(1);

        value += checkColumn(2);

        value += checkColumn(3);

        value += checkColumn(4);

        value += checkColumn(5);


        value += checkDiag(1, 5, 1);

        value += checkDiag(2, 5, 2);

        value += checkDiag(1, 4, 0);

        value += checkAnti(1, 5, 5);

        value += checkAnti(1, 4, 4);

        value += checkAnti(2, 5, 6);

        return value;
    }

}
