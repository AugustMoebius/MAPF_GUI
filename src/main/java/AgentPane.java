import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class AgentPane extends StackPane {


    public AgentPane(int height, int width) {
        setMaxHeight(height);
        setMaxWidth(width);
        setOpacity(0.5);
        getStyleClass().add("test");
    }

}
