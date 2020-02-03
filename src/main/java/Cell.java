import javafx.scene.layout.StackPane;

public class Cell extends StackPane {

    int column;
    int row;

    public Cell(int column, int row, String style) {

        this.column = column;
        this.row = row;

        getStyleClass().add(style);
    }

    public String toString() {
        return this.column + "/" + this.row;
    }
}