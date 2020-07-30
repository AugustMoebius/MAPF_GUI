import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.awt.*;
import java.util.HashMap;

public class Grid extends Pane {

    private LevelDetails level;
    int rows;
    int columns;

    double width;
    double height;

    int cellSize;

    private Cell[][] cells;
    HashMap<Integer, AgentCircle> agentMap = new HashMap();

    public Grid(int columns, int rows, int cellSize, LevelDetails level) {
        this.cellSize = cellSize;
        this.columns = columns;
        this.rows = rows;
        this.width = cellSize * columns;
        this.height = cellSize * rows;
        this.level = level;
        cells = new Cell[rows][columns];

    }
    /**
     * Add cell to array and to the UI.
     */
    public void add(Cell cell, int column, int row) {

        cells[row][column] = cell;

        double w = width / columns;
        double h = height / rows;
        double x = w * column;
        double y = h * row;

        cell.setLayoutX(x);
        cell.setLayoutY(y);
        cell.setPrefWidth(w);
        cell.setPrefHeight(h);

        getChildren().add(cell);
    }


    public void addAgent(Integer agent, Cell cell){
        double r = ((height / rows) / 2 - 1);
        Point center = cell.getCenter();
        double x = center.getX();
        double y = center.getY();
        AgentCircle agentCircle = new AgentCircle(r, agent);
        agentMap.put(agent, agentCircle);
        agentCircle.setLayoutX(x);
        agentCircle.setLayoutY(y);
        getChildren().add(agentCircle);
    }

    public Cell getCell(Point point){
        return cells[point.y][point.x];
    }

    public void updateCellStyles(Integer agent, Point point){
        level.agentPos.put(agent, point);
        //Check if goal position - Will most likely fail if an egent
        if (level.agentInGoal(agent)) {
            cells[point.y][point.x].getStyleClass().clear();
            cells[point.y][point.x].getStyleClass().add("cell-goal-completed");
        } else {
            point = level.goalPosByAgent.get(agent);
            cells[point.y][point.x].getStyleClass().clear();
            cells[point.y][point.x].setBackground(new Background(new BackgroundFill(ColorResources.getColor(agent), CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }

    public Point getAgentPixelPos(Point point){
        Cell cell = cells[point.y][point.x];
        return cell.getCenter();
    }

}