import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.util.Map;

public class MapfMain extends Application {

    private Stage primaryStage;
    private BorderPane root;
    double width = 800;
    double height = 600;

    int cellSize = 30;

    private File levelFile;
    private LevelDetails level;
    private File solutionFile;
    private Solution solution;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        try {
            BorderPane root = new BorderPane();
            this.root = root;
            root.getChildren().add(createMenuBar());
            // create scene and stage
            //root.setBackground(new Background(new BackgroundFill(Color.rgb(40, 40, 40), CornerRadii.EMPTY, Insets.EMPTY)));
            Scene scene = new Scene(root, width, height);
            scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void drawGrid() {
        LevelDetails level = new LevelDetails(levelFile);
        this.level = level;
        int lvl_height = level.MAX_ROWS * cellSize;
        int lvl_width = level.MAX_COLS * cellSize;

        Grid grid = new Grid(level.MAX_COLS, level.MAX_ROWS, lvl_width, lvl_height, level);
        for (int row = 0; row < level.MAX_ROWS; row++) {
            for (int column = 0; column < level.MAX_COLS; column++) {
                char c = level.initialLines.get(row).charAt(column);
                Point point = new Point(column, row);
                Cell cell;
                if (c == '+'){
                    cell = new Cell(column, row, cellSize, "cell-wall");
                } else if (level.goalPos.containsKey(point)) {
                    cell = new Cell(column, row, cellSize,ColorResources.getGoalCellStyle(level.goalPos.get(point)));
                } else {
                    cell = new Cell(column, row, cellSize,"cell");
                }
                grid.add(cell, column, row);
            }
        }

        for(Map.Entry<Character, Point> entry : level.agentPos.entrySet()){
            grid.addAgent(entry.getKey(), grid.getCell(entry.getValue()));
        }

         root.setCenter(grid);
    }

    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();
        menuBar.useSystemMenuBarProperty().set(true);

        Menu menu = new Menu("File");
        MenuItem item1 = new MenuItem("Load level file");
        MenuItem item2 = new MenuItem("Load solution file");
        final FileChooser fileChooser1 = new FileChooser();
        fileChooser1.setInitialDirectory(new File("/Users/augustmobius/Library/Mobile Documents/com~apple~CloudDocs/DTU filer/Speciale"));
        fileChooser1.getExtensionFilters().add(new FileChooser.ExtensionFilter("Level Files", "*.lvl"));
        final FileChooser fileChooser2 = new FileChooser();
        fileChooser2.setInitialDirectory(new File("/Users/augustmobius/Library/Mobile Documents/com~apple~CloudDocs/DTU filer/Speciale"));
        fileChooser2.getExtensionFilters().add(new FileChooser.ExtensionFilter("Solution Files", "*.txt"));

        item1.setOnAction(actionEvent -> {
            levelFile = fileChooser1.showOpenDialog(primaryStage);
            this.solution = new Solution(solutionFile, level.agentPos.keySet());
            drawGrid();
        });

        item2.setOnAction(actionEvent -> {
            solutionFile = fileChooser2.showOpenDialog(primaryStage);
            this.solution = new Solution(solutionFile, level.agentPos.keySet());
        });


        menu.getItems().addAll(item1, item2);
        menuBar.getMenus().add(menu);
        return menuBar;
    }


    public static void main (String[]args){
        launch();
    }
}