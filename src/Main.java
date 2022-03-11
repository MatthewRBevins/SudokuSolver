import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Arrays;

public class Main extends Application {
    int[][] sudoku = {
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0}
    };
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Solver s = new Solver();
        primaryStage.setTitle("Sudoku Manager");
        Button btn = new Button();
        btn.setText("Solve Sudoku");
        btn.setTranslateY(-125);
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println(Arrays.deepToString(s.solve(sudoku)));
                resetSudoku();
            }
        });
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        //root.setBackground(new Background());
        root.setAlignment(Pos.CENTER);
        int translateX = -100;
        int translateY = -100;
        for (int i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku[i].length; j++) {
                Button k = new Button();
                translateX += 20;
                k.setTranslateX(translateX);
                k.setTranslateY(translateY);
                k.setText("0");
                int finalI = i;
                int finalJ = j;
                k.setOnAction(new EventHandler<ActionEvent>() {
                   @Override
                   public void handle(ActionEvent e) {
                       if (Integer.parseInt(k.getText()) == 9) {
                           k.setText("1");
                       }
                       else{
                           k.setText(String.valueOf(Integer.parseInt(k.getText()) + 1));
                       }
                       sudoku[finalI][finalJ] = Integer.parseInt(k.getText());
                   }
                   public void resetB() {
                       k.setText("0");
                   }
                });
                root.getChildren().add(k);
            }
            translateY+=25;
            translateX = -100;
        }
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();
        primaryStage.getIcons().add(new Image("file:icon.png"));
    }
    //TODO:RESET BUTTONS
    private void resetSudoku() {
        sudoku = new int[][]{
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0}
        };
    }
}