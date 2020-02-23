import javafx.scene.paint.Color;

public class ColorResources {
    public static Color getAgentColor(Character c){
        switch (c){
            case 'A': return Color.RED;
            case 'B': return Color.GREEN;
            case 'C': return Color.YELLOW;
            case 'D': return Color.ORANGE;
            case 'E': return Color.BROWN;
            case 'F': return Color.PINK;
            case 'G': return Color.CYAN;
            case 'H': return Color.MAGENTA;

        }
        return Color.BLACK;
    }

    public static String getGoalCellStyle(Character c){
        switch (c){
            case 'A': return "cell-goal-A";
            case 'B': return "cell-goal-B";
            case 'C': return "cell-goal-C";
            case 'D': return "cell-goal-D";
            case 'E': return "cell-goal-E";
            case 'F': return "cell-goal-F";
            case 'G': return "cell-goal-G";
            case 'H': return "cell-goal-H";

        }
        return "cell";
    }
}
