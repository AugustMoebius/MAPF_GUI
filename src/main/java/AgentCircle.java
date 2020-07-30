import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

public class AgentCircle extends StackPane {

    Circle circle;
    Text label;
    double circlrR;
    Integer agentID;

    public AgentCircle(double circlrR, Integer agentChar) {
        this.circlrR = circlrR;
        this.agentID = agentChar;
        this.circle = new Circle();
        circle.setRadius(circlrR);
        circle.setFill(ColorResources.getColor(agentID));
        /*this.label = new Text(String.valueOf(agentChar));
        label.setBoundsType(TextBoundsType.VISUAL);
        label.setStyle(
                "-fx-font-family: \"Helvetica Neue\";" +
                "-fx-font-size: 15px;"
        );*/
        getChildren().addAll(circle);
    }
}
