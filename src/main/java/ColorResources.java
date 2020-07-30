import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Random;

public class ColorResources {

    private static HashMap<Integer, Color> colorMap = new HashMap<>();

    public static Color getColor(Integer agent){
        if(!colorMap.containsKey(agent)) {
            Random rand = new Random();
            double r = rand.nextDouble();
            double g = rand.nextDouble();
            double b = rand.nextDouble();
            Color color = Color.color(r, g, b);
            colorMap.put(agent, color);
        }
        return colorMap.get(agent);
    }

/*
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
            case 'I': return Color.GOLD;
            case 'J': return Color.LIME;
            case 'K': return Color.TEAL;
            case 'L': return Color.LIGHTBLUE;
            case 'M': return Color.DEEPPINK;
            case 'N': return Color.LINEN;
            case 'O': return Color.SILVER;
            case 'P': return Color.HONEYDEW;
            case 'Q': return Color.TAN;
            case 'R': return Color.PERU;
            case 'S': return Color.LAVENDER;
            case 'T': return Color.SLATEBLUE;
            case 'U': return Color.NAVY;
            case 'V': return Color.DARKGREEN;
            case 'X': return Color.DARKRED;
            case 'Y': return Color.SALMON;
            case 'Z': return Color.BLUEVIOLET;


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
            case 'I': return "cell-goal-I";
            case 'J': return "cell-goal-J";
            case 'K': return "cell-goal-K";
            case 'L': return "cell-goal-L";
            case 'M': return "cell-goal-M";
            case 'N': return "cell-goal-N";
            case 'O': return "cell-goal-O";
            case 'P': return "cell-goal-P";
            case 'Q': return "cell-goal-Q";
            case 'R': return "cell-goal-R";
            case 'S': return "cell-goal-S";
            case 'T': return "cell-goal-T";
            case 'U': return "cell-goal-U";
            case 'V': return "cell-goal-V";
            case 'X': return "cell-goal-X";
            case 'Y': return "cell-goal-Y";
            case 'Z': return "cell-goal-Z";

        }
        return "cell";
    }*/
}
