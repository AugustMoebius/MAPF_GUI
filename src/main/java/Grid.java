import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Grid extends Pane {

    int rows;
    int columns;

    double width;
    double height;

    Cell[][] cells;

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


//    public void addAgent(Point point, Character agent, String style){
//        Circle circle = new Circle();
//        double r = (height / rows) - 5;
//        double x =  width * point.getX();
//        double y =  height * point.getY();
//
//        circle.setLayoutX(x);
//        circle.setLayoutY(y);
//        circle.setRadius(r);
//        circle.setFill(javafx.scene.paint.Color.RED);
//        agentMap.put(agent, circle);
//        getChildren().add(circle);
//    }

}