import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    double width = 1000, height = 550;
    int nsnake = 5, nladder = 5, nplayers = 1;
    int n[][] = new int[nsnake][4], d[][] = new int[nladder][4];
    Board board = new Board(width, height);
    Dice dice = new Dice(100, 100);
    Snake snakes[] = new Snake[nsnake];
    Ladder ladders[] = new Ladder[nladder];
    Player players[] = new Player[1];
    Circle sprites[], playerColor[];
    MButton controls[];
    ProgressIndicator indicator[];
    Color colors[] = new Color[8];

    @Override
    public void start(Stage primaryStage) {
        

        colors[0] = Color.RED;
        colors[1] = Color.ORANGE;
        colors[2] = Color.GREEN;
        colors[3] = Color.rgb(30, 170, 255);
        colors[4] = Color.INDIGO;
        colors[5] = Color.VIOLET;
        colors[6] = Color.DARKRED;
        colors[7] = Color.GREY;
        nplayers = 4;
        players = new Player[nplayers];
        sprites = new Circle[nplayers];
        playerColor = new Circle[nplayers];
        controls = new MButton[nplayers];
        indicator = new ProgressIndicator[nplayers];
        StackPane root = new StackPane();
        BorderPane rootPane = new BorderPane();
        BorderPane leftBorderPane = new BorderPane();
        BorderPane centralBorderPane = new BorderPane();
        centralBorderPane.setPadding(new Insets(0, 10, 20, 10));
        HBox titleBox = new HBox();
        titleBox.setId("titlebox");
        titleBox.setAlignment(Pos.TOP_CENTER);
        titleBox.setPadding(new Insets(10));
        HBox controlsBox = new HBox();
        controlsBox.setAlignment(Pos.CENTER_LEFT);
        controlsBox.setPadding(new Insets(0, 0, 0, 10));
        VBox primaryControlsBox = new VBox(10);
        primaryControlsBox.setAlignment(Pos.CENTER);
        HBox resetContainer = new HBox();
        resetContainer.setPadding(new Insets(0, 10, 20, 10));
        resetContainer.setAlignment(Pos.CENTER_LEFT);
        VBox graphContainer = new VBox();
        graphContainer.setAlignment(Pos.CENTER_LEFT);
        dice.setTranslateX(115); 
        graphContainer.getChildren().add(dice);
        leftBorderPane.setCenter(graphContainer);
        Text title = new Text("Snakes And Ladders by Kel 7");
        title.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BOLD, 35));
        title.setId("title");
        titleBox.getChildren().add(title);
        
        n[0][0] = 9;
        n[0][1] = 8;
        n[0][2] = 0;
        n[0][3] = 7;
        n[1][0] = 9;
        n[1][1] = 4;
        n[1][2] = 5;
        n[1][3] = 1;
        n[2][0] = 8;
        n[2][1] = 9;
        n[2][2] = 2;
        n[2][3] = 7;
        n[3][0] = 5;
        n[3][1] = 9;
        n[3][2] = 0;
        n[3][3] = 2;
        n[4][0] = 7;
        n[4][1] = 3;
        n[4][2] = 4;
        n[4][3] = 2;
        for (int i = 0; i < snakes.length; ++i) {
            snakes[i] = new Snake(board.box[n[i][0]][n[i][1]], board.box[n[i][2]][n[i][3]], width / 20);
            snakes[i].setColor(board.getRandomColor(Color.WHITE));
            snakes[i].drawOn(board);
            snakes[i].setI1(n[i][0]);
            snakes[i].setJ1(n[i][1]);
            snakes[i].setI2(n[i][2]);
            snakes[i].setJ2(n[i][3]);
        }
        d[0][0] = 6;
        d[0][1] = 1;
        d[0][2] = 0;
        d[0][3] = 1;
        d[1][0] = 4;
        d[1][1] = 4;
        d[1][2] = 1;
        d[1][3] = 6;
        d[2][0] = 9;
        d[2][1] = 1;
        d[2][2] = 1;
        d[2][3] = 0;
        d[3][0] = 6;
        d[3][1] = 7;
        d[3][2] = 3;
        d[3][3] = 2;
        d[4][0] = 9;
        d[4][1] = 6;
        d[4][2] = 6;
        d[4][3] = 4;
        for (int i = 0; i < d.length; ++i) {
            ladders[i] = new Ladder(board.box[d[i][0]][d[i][1]], board.box[d[i][2]][d[i][3]], width / 20);
            ladders[i].setColor(board.getRandomColor(Color.WHITE));
            ladders[i].drawOn(board);
            ladders[i].setI1(d[i][0]);
            ladders[i].setJ1(d[i][1]);
            ladders[i].setI2(d[i][2]);
            ladders[i].setJ2(d[i][3]);
        }
        for (int i = 0; i < nplayers; ++i) {
            HBox controlSet = new HBox(20);
            controlSet.setAlignment(Pos.TOP_CENTER);
            controls[i] = new MButton(i);
            controls[i].setText("P".concat(String.valueOf(i + 1)));
            players[i] = new Player(board);
            players[i].setController(controls[i]);
            controls[i].setDisable(!(i == 0));
            controls[i].setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent ae) {
                    MButton mb = (MButton) ae.getSource();
                    int in = mb.getPlayerIndex();
                    if (!players[in].isLocked()) {
                        players[in].rollDice();
                        dice.setShow(true);
                        dice.update(players[in].getDiceNumber());
                        if (players[in].getLaststep() + 1 >= 95) {
                            switch (players[in].getLaststep() + players[in].getDiceNumber() + 1) {
                                case 101:
                                    players[in].reachedEnd(true);
                                    if (players[in].getLaststep() == 99) {
                                        sprites[in].setFill(Color.rgb(0, 0, 0, 0.0));
                                        controls[in].setDisable(true);
                                        indicator[in].setProgress(100 / 100);
                                    }
                                    move(players[in].getLaststep(), players[in].getLaststep() + players[in].getDiceNumber() - 1, in);
                                    break;
                                case 100:
                                case 99:
                                case 98:
                                case 97:
                                case 96:
                                case 95:
                                    move(players[in].getLaststep(), players[in].getLaststep() + players[in].getDiceNumber(), in);
                                    players[in].setLaststep(players[in].getLaststep() + players[in].getDiceNumber());
                                    break;
                                default:
                                    if (players[in].hasReachedEnd() == false) {
                                    }
                                    break;
                            }

                        } else {
                            move(players[in].getLaststep(), players[in].getLaststep() + players[in].getDiceNumber(), in);
                            players[in].setLaststep(players[in].getLaststep() + players[in].getDiceNumber());
                        }

                    }
                    if (players[in].isLocked()) {
                        players[in].rollDice();
                        dice.setShow(true);
                        dice.update(players[in].getDiceNumber());
                        if (players[in].getDiceNumber() == 6 || players[in].getDiceNumber() == 1) {
                            players[in].setLaststep(0);
                            players[in].unlock();
                            sprites[in].setFill(colors[in]);
                            sprites[in].setCenterX(board.box[0][0].getCenterX());
                            sprites[in].setCenterY(board.box[0][0].getCenterY());
                        }
                    }
                    if (in == controls.length - 1) {
                        controls[0].setDisable(false);
                        players[0].controller.requestFocus();
                        controls[in].setDisable(true);
                    } else {
                        controls[in + 1].setDisable(false);
                        controls[in].setDisable(true);
                    }
                }
            });
            sprites[i] = new Circle();
            sprites[i].setRadius(height / 30);
            sprites[i].setFill(Color.rgb(0, 0, 0, 0.0));
            DropShadow innerShadow = new DropShadow(3, Color.BLACK);
            innerShadow.setInput(new InnerShadow(2, Color.BLACK));
            sprites[i].setEffect(innerShadow);
            sprites[i].setCenterX(board.box[0][0].getCenterX());
            sprites[i].setCenterY(board.box[0][0].getCenterY());
            playerColor[i] = new Circle();
            playerColor[i].setRadius(height / 40);
            playerColor[i].setFill(colors[i]);
            indicator[i] = new ProgressIndicator(0);
            indicator[i].setPrefSize(50, 50);
            controls[i].setPrefSize(height / 20 + 10, height / 20);
            VBox indicatorContainer = new VBox();
            indicatorContainer.setAlignment(Pos.TOP_LEFT);
            indicatorContainer.getChildren().add(indicator[i]);
            controlSet.getChildren().addAll(controls[i], playerColor[i], indicatorContainer);
            controlSet.getStyleClass().add("controls");
            primaryControlsBox.getChildren().add(controlSet);
            board.getChildren().add(sprites[i]);
            XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
            series.setName("P".concat(Integer.toString(i + 1)));
        }
        controlsBox.getChildren().add(primaryControlsBox);
        leftBorderPane.setTop(controlsBox);
        Button reset = new Button("Reset");
        reset.setMinWidth(300);
        reset.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                for (int i = 0; i < nplayers; ++i) {
                    players[i].controller.setDisable(!(i == 0));
                    Timeline timeline = new Timeline();
                    Color cl = (Color) sprites[i].getFill();
                    KeyValue value1 = new KeyValue(sprites[i].fillProperty(), Color.color(cl.getRed(), cl.getGreen(), cl.getBlue(), 0.0));
                    KeyValue value2 = new KeyValue(indicator[i].progressProperty(), 0d);
                    KeyFrame frame = new KeyFrame(Duration.seconds(1), value1, value2);
                    timeline.getKeyFrames().add(frame);
                    PathTransition pt = new PathTransition();
                    Path path = new Path();
                    path.getElements().add(new MoveTo(board.box[players[i].getI()][players[i].getJ()].getCenterX(), board.box[players[i].getI()][players[i].getJ()].getCenterY()));
                    path.getElements().add(new LineTo(board.box[0][0].getCenterX(), board.box[0][0].getCenterY()));
                    pt.setPath(path);
                    pt.setNode(sprites[i]);
                    pt.setDuration(Duration.seconds(1));
                    pt.play();
                    timeline.play();
                    players[i].reset();
                }
                dice.setShow(false);
            }
        });
        reset.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BOLD, 10));
        reset.setId("reset");
        resetContainer.getChildren().add(reset);
        leftBorderPane.setBottom(resetContainer);
        centralBorderPane.setCenter(board);
