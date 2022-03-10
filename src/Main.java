import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
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
        Button[][] sudokuButtons = new Button[sudoku.length][sudoku.length];
        for (Button[] i : sudokuButtons) {
            Arrays.fill(i,new Button());
        }
        System.out.println(Arrays.deepToString(sudokuButtons));
        Solver s = new Solver();
        primaryStage.setTitle("Sudoku Manager");
        Button btn = new Button();
        btn.setText("Solve Sudoku");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                s.solve(sudoku);
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
        System.out.println(primaryStage.getIcons());
        primaryStage.getIcons().add(new Image("file:icon.png"));
    }
}