/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wackamole;

import java.util.ArrayList;
import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class WackAMole extends Application {

    private int score = 0;
    private ArrayList<Button> alist = new ArrayList<>();
    

    Image image = new Image("wackamole/test.jpg");
    Image image2 = new Image("wackamole/testdead.jpg");

        // Background mole image
    BackgroundImage bgim = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
    Background bg = new Background(bgim);

    @Override
    public void start(Stage primaryStage) {
        // background dead image
        bgim = new BackgroundImage(image2, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background bg2 = new Background(bgim);
        makemoles();
        
        //keyframes one is a delay
        KeyFrame delay = new KeyFrame(Duration.millis(200));
        KeyFrame moles = new KeyFrame(Duration.millis(100), ae -> {
            randomMoles();
        });
        
        // When clicking the button/mole
        // change background update score
        EventHandler<javafx.scene.input.MouseEvent> eventHandler = (javafx.scene.input.MouseEvent e) -> {
            Button b = (Button) e.getSource();
            if (b.getBackground().equals(bg)) {
                score++;
            }
            b.setBackground(bg2);
            // debug score
            //System.out.println(score);
        };
        
        // add eventhandlers to all buttons
        for (Button button : alist) {
            button.setOnMouseClicked(eventHandler);
        }

        Timeline timeline = new Timeline();

        timeline.setCycleCount(Timeline.INDEFINITE);

        timeline.getKeyFrames().addAll(delay, moles);
        timeline.play();

            // create the play area
        BorderPane root = new BorderPane();

        root.setCenter(addGridPane());

        Scene scene = new Scene(root, 200, 200);

        primaryStage.setTitle("UI Layouts");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void makemoles() {

//make the buttons and add to list
        for (int i = 0; i < 12; i++) {

            Button bt1 = new Button();
            bt1.setMinSize(25, 25);

            bt1.setBackground(bg);
            alist.add(bt1);

        }

        for (Button button : alist) {
            button.setVisible(false);
        }
    }

    public void randomMoles() {
        
        // reset moles
        for (Button button : alist) {
            button.setBackground(bg);
        }
        for (Button button : alist) {
            button.setVisible(false);
        }
        Random random = new Random();
        int molesAmount = random.nextInt(4) + 1;
        // debug amount of selected moles
        //System.out.println("amount" + molesAmount);

        for (int i = 0; i < molesAmount; i++) {
            int chosenMole = random.nextInt(12);
            alist.get(chosenMole).setVisible(true);
            // debug the chosen moles
            //System.out.println("chosen" + chosenMole+1);

        }

    }

    public GridPane addGridPane() {
//pad the gridpane
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        // add the buttons to the gridpane
        int x = 1;
        int y = 0;
        for (int i = 0; i < alist.size(); i++) {

            grid.add(alist.get(i), x, y);
            y++;
            if (y == 3) {
                y = 0;
                x++;
            }
        }

        return grid;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
