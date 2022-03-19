import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.text.html.CSS;
import java.util.Arrays;
import java.util.function.Consumer;

public class Main extends Application {
    CSS c = new CSS();
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
    Button[] sudokuButtons = new Button[sudoku.length*sudoku.length];
    public static void main(String[] args) {
        launch(args);
    }
    //TODO:remove "9" references
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sudoku Manager");
        CheckBox x = new CheckBox();
        x.setTranslateY(-150);
        Text title = new Text("SUDOKU SOLVER");
        title.setId("title");
        title.setTranslateY(-175);
        Text xLabel = new Text("include diagonals");
        xLabel.setTranslateY(-150);
        xLabel.setTranslateX(-75);
        Button solveButton = new Button();
        solveButton.setText("Solve Sudoku");
        solveButton.setTranslateY(-125);
        solveButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Solver s = new Solver(x.isSelected(), 9);
                int[][] solved = s.solve(sudoku);
                System.out.println(Arrays.deepToString(solved));
                for (int i = 0; i < solved.length; i++) {
                    for (int j = 0; j < solved[0].length; j++) {
                        if (!String.valueOf(solved[i][j]).equals(sudokuButtons[j + (i * 9)].getText())) {
                            sudokuButtons[j+(i*9)].setText(String.valueOf(solved[i][j]));
                            sudokuButtons[j+(i*9)].setTextFill(Color.RED);
                        }
                    }
                }
            }
        });
        Button resetButton = new Button();
        resetButton.setStyle("");
        resetButton.setText("Reset Sudoku");
        resetButton.setId("resetButton");
        //resetButton.setTextFill(Color.RED);
        resetButton.setTranslateY(125);
        resetButton.setTranslateX(0);
        resetButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                resetSudoku();
            }
        });

        /*
        ADD NODES HERE
         */

        StackPane root = new StackPane();
        root.getChildren().add(title);
        root.getChildren().add(solveButton);
        root.getChildren().add(resetButton);
        root.getChildren().add(x);
        root.getChildren().add(xLabel);
        //root.getChildren().remove(find(root,x));
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
                sudokuButtons[j+(i*9)] = k;
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
                });
                root.getChildren().add(k);
            }
            translateY+=25;
            translateX = -100;
        }
        Scene scene = new Scene(root,600,500);
        scene.getStylesheets().add("main.css");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.getIcons().add(new Image("file:icon.png"));
    }
    private int find(StackPane p, Object check) {
        Object[] nodes = p.getChildren().toArray();
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] == check) {
                return i;
            }
        }
        return -1;
    }
    //TODO:RESET BUTTONS
    private void resetSudoku() {
        for (Button i : sudokuButtons) {
            i.setText("0");
            i.setTextFill(Color.BLACK);
        }
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