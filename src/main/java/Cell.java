import javafx.scene.layout.StackPane;

import java.awt.*;

public class Cell extends StackPane {

    private int column;
    private int row;
    private int size;

    public Cell(int column, int row, int size, String style) {

        this.column = column;
        this.row = row;
        this.size = size;
        getStyleClass().add(style);
    }

    public Point getCenter(){
        int x = column * size + 2;
        int y = row * size + 2;
        return new Point(x,y);
    }

    public String toString() {
        return this.column + "/" + this.row;
    }
}