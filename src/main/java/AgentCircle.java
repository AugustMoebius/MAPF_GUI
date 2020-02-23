import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

public class AgentCircle extends StackPane {

    Circle circle;
    Text label;
    double circlrR;
    Character agentChar;

    public AgentCircle(double circlrR, Character agentChar) {
        this.circlrR = circlrR;
        this.agentChar = agentChar;
        this.circle = new Circle();
        circle.setRadius(circlrR);
        circle.setFill(ColorResources.getAgentColor(agentChar));

        this.label = new Text(String.valueOf(agentChar));
        label.setBoundsType(TextBoundsType.VISUAL);
        label.setStyle(
                "-fx-font-family: \"Helvetica Neue\";" +
                "-fx-font-size: 25px;"
        );
        getChildren().addAll(circle, label);
    }
}
