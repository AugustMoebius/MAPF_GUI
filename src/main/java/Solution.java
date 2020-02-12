import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Solution {

    Map<Character, ArrayList<Point>> solByPoint = new HashMap<>();
    Map<Character, ArrayList<Direction>> solByDir = new HashMap<>();
    int length = 1;

    public Solution(File solutionFile, Map<Character, Point> initialAgentPos){
        for (Character agent : initialAgentPos.keySet()){
            // Create list and add initial state
            solByPoint.put(agent, new ArrayList<>());
            solByDir.put(agent, new ArrayList<>());
            Point initPoint = initialAgentPos.get(agent);
            solByPoint.get(agent).add(new Point(initPoint.x, initPoint.y));
        }
        try {
            processSolutionFile(solutionFile, initialAgentPos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void processSolutionFile(File solutionFIle, Map<Character, Point> agentPos) throws FileNotFoundException {
        ArrayList<Character> agents = new ArrayList<>();
        agents.addAll(agentPos.keySet());
        java.util.Collections.sort(agents);

        Scanner scanner = new Scanner(solutionFIle);

        while(scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] dirs = line.split(",");
            length += 1;
            for(int i = 0; i < dirs.length; i++) {
                Character agent = agents.get(i);
                Point curPoint = agentPos.get(agent);
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
