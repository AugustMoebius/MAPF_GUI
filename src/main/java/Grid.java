import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.awt.*;
import java.util.HashMap;

public class Grid extends Pane {

    int rows;
    int columns;

    double width;
    double height;

    private Cell[][] cells;
    private HashMap<Cell, Circle> agentMap = new HashMap();

    public Grid(int columns, int rows, double width, double height, LevelDetails level) {

        this.columns = columns;
        this.rows = rows;
        this.width = width;
        this.height = height;
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


    public void addAgent(Character agent, Cell cell){
        Circle circle = new Circle();
        double r = ((height / rows) / 2) - 2;
        Point center = cell.getCenter();
        double x = center.getX();
        double y = center.getY();

        circle.setLayoutX(x);
        circle.setLayoutY(y);
        circle.setRadius(r);
        circle.setFill(ColorResources.getAgentColor(agent));
        agentMap.put(cell, circle);
        getChildren().add(circle);
    }

    public Cell getCell(Point point){
        return cells[(int)point.getX()][(int)point.getY()];
    }

}