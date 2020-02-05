import javafx.scene.paint.Color;

public class ColorResources {
    public static Color getAgentColor(Character c){
        switch (c){
            case 'A': return Color.RED;
            case 'B': return Color.GREEN;
        }
        return Color.BLACK;
    }

    public static String getGoalCellStyle(Character c){
        switch (c){
            case 'A': return "cell-goal-A";
            case 'B': return "cell-goal-B";
        }
        return "cell";
    }
}
