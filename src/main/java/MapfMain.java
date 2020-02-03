import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.util.Map;

public class MapfMain extends Application {

    private Stage primaryStage;
    private StackPane root;
    double width = 800;
    double height = 600;

    int cellSize = 30;

    private File levelFile;
    private File solutionFile;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        try {
            StackPane root = new StackPane();
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
        Grid grid = new Grid(level.MAX_COLS, level.MAX_ROWS, level.MAX_COLS * cellSize, level.MAX_ROWS * cellSize, level);
        for (int row = 0; row < level.MAX_ROWS; row++) {
            for (int column = 0; column < level.MAX_COLS; column++) {
                char c = level.initialLines.get(row).charAt(column);
                Cell cell;
                if (c == '+'){
                    cell = new Cell(column, row, "cell-wall");
                } else {
                    cell = new Cell(column, row, "cell");
                }
                grid.add(cell, column, row);
            }
        }
        root.getChildren().add(grid);
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
            drawGrid();
        });

        item2.setOnAction(actionEvent -> solutionFile = fileChooser2.showOpenDialog(primaryStage));

        menu.getItems().addAll(item1, item2);
        menuBar.getMenus().add(menu);
        return menuBar;
    }


    public static void main (String[]args){
        launch();
    }
}