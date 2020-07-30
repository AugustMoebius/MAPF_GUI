import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Solution {

    Map<Integer, ArrayList<Point>> solByPoint = new HashMap<>();
    Map<Integer, ArrayList<Direction>> solByDir = new HashMap<>();
    int length = 1;
    public int noOfAgent;

    public Solution(File solutionFile, Map<Integer, Point> initialAgentPos) {
        ArrayList<Integer> agents = new ArrayList<>();
        agents.addAll(initialAgentPos.keySet());
        java.util.Collections.sort(agents);

        Scanner scanner = null;
        try {
            scanner = new Scanner(solutionFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while(scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] dirs = line.split(",");
            noOfAgent = dirs.length;
            if (length == 1) {
                for(int i = 0; i < noOfAgent; i++){
                    // Create list and add initial state
                    solByPoint.put(i, new ArrayList<>());
                    solByDir.put(i, new ArrayList<>());
                    Point initPoint = initialAgentPos.get(i);
                    solByPoint.get(i).add(new Point(initPoint.x, initPoint.y));
                }
            }
            length += 1;

            for(int i = 0; i < dirs.length; i++) {
                Integer agent = agents.get(i);
                Point curPoint = initialAgentPos.get(agent);
                switch (dirs[i]){
                    case "EAST":
                        curPoint.move(curPoint.x + 1, curPoint.y);
                        solByDir.get(agent).add(Direction.EAST);
                        break;
                    case "WEST":
                        curPoint.move(curPoint.x - 1, curPoint.y);
                        solByDir.get(agent).add(Direction.WEST);
                        break;
                    case "NORTH":
                        curPoint.move(curPoint.x, curPoint.y - 1);
                        solByDir.get(agent).add(Direction.NORTH);
                        break;
                    case "SOUTH":
                        curPoint.move(curPoint.x, curPoint.y + 1);
                        solByDir.get(agent).add(Direction.SOUTH);
                        break;
                    case "NOOP":
                        solByDir.get(agent).add(Direction.NOOP);
                        break;
                }
                // Add new point to avoid modification by loop
                int x = curPoint.x;
                int y = curPoint.y;
                Point newPos = new Point(x, y);
                //newPos.setLocation(curPoint);
                solByPoint.get(agent).add(newPos);
            }

        }
    }
}
