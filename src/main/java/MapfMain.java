import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MapfMain extends Application {

    private Stage primaryStage;
    private BorderPane root;
    double width = 800;
    double height = 600;

    int cellSize = 50;

    private File levelFile;
    private File agentFile;
    private File solutionFile;
    private LevelDetails level;
    private Solution solution;
    private Grid grid;

    //Animation
    private Slider stateSlider = new Slider();
    private int animationSpeed = 400;
    Map<Integer, SequentialTransition> transitionMap;

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
        LevelDetails level = new LevelDetails(levelFile, agentFile);
        this.level = level;

        Grid grid = new Grid(level.MAX_COLS, level.MAX_ROWS, cellSize, level);
        for (int row = 0; row < level.MAX_ROWS; row++) {
            for (int column = 0; column < level.MAX_COLS; column++) {
                char c = level.initialLines.get(row).charAt(column);
                Point point = new Point(column, row);
                Cell cell;
                if (c == '.'){
                    cell = new Cell(column, row, cellSize, "cell");
                } else {
                    cell = new Cell(column, row, cellSize,"cell-wall");
                }
                grid.add(cell, column, row);
            }
        }

        root.setCenter(grid);
        this.grid = grid;
    }

    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();
        menuBar.useSystemMenuBarProperty().set(true);

        Menu menu = new Menu("File");
        MenuItem item1 = new MenuItem("Load level file");
        MenuItem item2 = new MenuItem("Load solution file");
        final FileChooser fileChooser1 = new FileChooser();
        final FileChooser fileChooser2 = new FileChooser();
        final FileChooser fileChooser3 = new FileChooser();
        fileChooser1.setInitialDirectory(new File("/Users/augustmobius/Library/Mobile Documents/com~apple~CloudDocs/DTU filer/Speciale"));
        fileChooser1.getExtensionFilters().add(new FileChooser.ExtensionFilter("Level Files", "*.map"));
        fileChooser2.setInitialDirectory(new File("/Users/augustmobius/Library/Mobile Documents/com~apple~CloudDocs/DTU filer/Speciale"));
        fileChooser2.getExtensionFilters().add(new FileChooser.ExtensionFilter("Level Files", "*.scen"));
        fileChooser3.setInitialDirectory(new File("/Users/augustmobius/Library/Mobile Documents/com~apple~CloudDocs/DTU filer/Speciale"));
        fileChooser3.getExtensionFilters().add(new FileChooser.ExtensionFilter("Solution Files", "*.txt"));

        item1.setOnAction(actionEvent -> {
            levelFile = fileChooser1.showOpenDialog(primaryStage);
            agentFile = fileChooser2.showOpenDialog(primaryStage);
            solutionFile = fileChooser3.showOpenDialog(primaryStage);
            drawGrid();
            this.solution = new Solution(solutionFile, level.agentPos);
            animateSolution();
        });

        item2.setOnAction(actionEvent -> {
            solutionFile = fileChooser3.showOpenDialog(primaryStage);
            this.solution = new Solution(solutionFile, level.agentPos);
            drawGrid();
            animateSolution();
        });


        menu.getItems().addAll(item1, item2);
        menuBar.getMenus().add(menu);
        return menuBar;
    }

    private void animateSolution(){
        Map<Integer, ArrayList<Point>> sol = this.solution.solByPoint;
        transitionMap = new HashMap<>();

        for (int i = 0; i < solution.noOfAgent; i++){
            grid.addAgent(i, grid.getCell(level.agentPos.get(i)));
        }

        for (Integer agent : sol.keySet()){
            transitionMap.put(agent, new SequentialTransition());
            transitionMap.get(agent).setNode(grid.agentMap.get(agent));
        }

        // Start at 1 because we don't want to move to inital position
        for (int i = 0; i < solution.length; i++){
            for (Integer agent : sol.keySet()){
                Point curPos = sol.get(agent).get(i);
                System.out.println("Agent: " + agent + " curpos: " + curPos.toString());
                Point pixelPos = grid.getAgentPixelPos(curPos);
                Timeline timeline = new Timeline();
                AgentCircle circle = grid.agentMap.get(agent);
                KeyValue kvX = new KeyValue(circle.layoutXProperty(), pixelPos.x);
                KeyValue kvY = new KeyValue(circle.layoutYProperty(), pixelPos.y);
                KeyFrame keyFrame = new KeyFrame(Duration.millis(animationSpeed), kvX, kvY);
                timeline.getKeyFrames().add(keyFrame);
                int finalI = i + 1;
                timeline.setOnFinished(e -> {
                    grid.updateCellStyles(agent, curPos);
                    this.stateSlider.setValue(finalI);
                });
                transitionMap.get(agent).getChildren().add(timeline);
            }
        }
        for (SequentialTransition s : transitionMap.values()){
            s.play();
        }
        updateStateSlider();
        addConstrolButtons();
    }

    public void addConstrolButtons(){
        Button replayBtn = new Button();
        replayBtn.setText("Replay");
        replayBtn.setWrapText(true);
        replayBtn.setOnAction(actionEvent ->  {
            for (SequentialTransition s : transitionMap.values()){
                s.playFrom(Duration.ZERO);
            }
        });

        Button playPauseBtn = new Button();
        playPauseBtn.setText("Play/Pause");
        playPauseBtn.setWrapText(true);
        playPauseBtn.setOnAction(actionEvent ->  {
            for (SequentialTransition s : transitionMap.values()){
                if (s.getStatus().equals(Animation.Status.RUNNING)) {
                    s.pause();
                } else if (s.getStatus().equals(Animation.Status.PAUSED)) {
                    s.play();
                }
            }
        });
        VBox box = new VBox(replayBtn, playPauseBtn);
        root.setRight(box);

    }


    public void updateStateSlider() {
        stateSlider.setMin(0);
        stateSlider.setMax(solution.length);
        stateSlider.setShowTickLabels(true);
        stateSlider.setShowTickMarks(true);
        stateSlider.setMajorTickUnit(1);
        //stateSlider.setSnapToTicks(true);
        stateSlider.setOnMouseReleased(e -> {
            for (SequentialTransition s : transitionMap.values()){
                s.playFrom(Duration.millis(animationSpeed * stateSlider.getValue()));
            }
        });
        /*
        stateSlider.setOnMouseDragged(e -> {
            System.out.println(animationSpeed * stateSlider.getValue());
            for (SequentialTransition s : transitionMap.values()){
                s.playFrom(Duration.millis(animationSpeed * stateSlider.getValue()));
                //s.jumpTo(Duration.millis(animationSpeed * stateSlider.getValue()));
                s.pause();
            }

        });
        */
        root.setBottom(stateSlider);
    }


    public static void main (String[]args){
        launch(args);
    }
}