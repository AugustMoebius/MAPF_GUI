import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LevelDetails {
    List<String> initialLines = new ArrayList();
    Map<Integer, Point> agentPos = new HashMap<>();
    Map<Point, Integer> goalPosByPoint = new HashMap<>();
    Map<Integer, Point> goalPosByAgent = new HashMap<>();
    int MAX_ROWS;
    int MAX_COLS;



    public LevelDetails(File levelFile, File agentFile) {
        try {
            processLevelFile(levelFile, agentFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void processLevelFile(File levelFile, File agentFile) throws IOException {
        List<String> levelLines = Files.lines(Paths.get(levelFile.getPath())).collect(Collectors.toList());
        List<String> agentLines = Files.lines(Paths.get(agentFile.getPath())).collect(Collectors.toList());

        MAX_ROWS = Integer.parseInt(levelLines.get(1).split(" ")[1]);
        MAX_COLS = Integer.parseInt(levelLines.get(2).split(" ")[1]);

        // Remove first 4 lines
        levelLines = levelLines.subList(4, levelLines.size());
        this.initialLines = levelLines;
        int agentID = 0;
        for(String row : agentLines.subList(1, agentLines.size())) {
            String[] split = row.split("\t");
            int startCol = Integer.parseInt(split[4]);
            int startRow = Integer.parseInt(split[5]);
            int goalCol = Integer.parseInt(split[6]);
            int goalRow = Integer.parseInt(split[7]);
            Point init = new Point(startCol, startRow);
            Point goal = new Point(goalCol, goalRow);
            agentPos.put(agentID, init);
            goalPosByAgent.put(agentID, goal);
            goalPosByPoint.put(goal, agentID);
            agentID++;
        }
    }


 /*   private void setPositions() {
        Pattern p = Pattern.compile("[a-zA-Z]");
        for (int row = 0; row < initialLines.size(); row++) {
            for (int col = 0; col < initialLines.get(row).length(); col++) {
                char cInit = initialLines.get(row).charAt(col);
                char cGoal = goalLines.get(row).charAt(col);
                Matcher mInit = p.matcher(String.valueOf(cInit));
                Matcher mGoal = p.matcher(String.valueOf(cGoal));
                Point newPoint1 = new Point(col, row);
                if (mInit.find()) {
                    agentPos.put(cInit, newPoint1);
                }
                Point newPoint2 = new Point(col, row);
                if (mGoal.find()) {
                    goalPosByPoint.put(newPoint2, cGoal);
                    goalPosByAgent.put(cGoal, newPoint2);
                }

            }
        }
    }*/

    public boolean agentInGoal(Integer agent) {
        Point point = agentPos.get(agent);
        return goalPosByAgent.get(agent).equals(point);
    }
}
