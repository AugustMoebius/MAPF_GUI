import javafx.scene.layout.StackPane;

import java.awt.*;

public class Cell extends StackPane {

    int column;
    int row;
    int size;

    public Cell(int column, int row, int size, String style) {

        this.column = column;
        this.row = row;
        this.size = size;
        //setOpacity(0.6);
        getStyleClass().add(style);
    }

    public Point getCenter(){
        int x = size / 2 + column * size;
        int y = size / 2 + row * size;
        return new Point(x,y);
    }

    public String toString() {
        return this.column + "/" + this.row;
    }
}