import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Solution {

    Map<Character, ArrayList<Direction>> solution = new HashMap<>();

    public Solution(File solutionFile, Set<Character> agents){
        ArrayList<Character> agentsList = new ArrayList<>();
        agentsList.addAll(agents);
        for (Character agent : agents){
            solution.put(agent, new ArrayList<>());
        }
        try {
            processSolutionFile(solutionFile, agentsList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void processSolutionFile(File solutionFIle, ArrayList<Character> agents) throws FileNotFoundException {
        java.util.Collections.sort(agents);
        Scanner scanner = new Scanner(solutionFIle);

        while(scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] dirs = line.split(",");

            for(int i = 0; i < dirs.length; i++) {
                switch (dirs[i]){
                    case "EAST":
                        solution.get(agents.get(i)).add(Direction.EAST);
                        break;
                    case "WEST":
                        solution.get(agents.get(i)).add(Direction.WEST);
                        break;
                    case "NORTH":
                        solution.get(agents.get(i)).add(Direction.NORTH);
                        break;
                    case "SOUTH":
                        solution.get(agents.get(i)).add(Direction.SOUTH);
                        break;
                    default:
                        solution.get(agents.get(i)).add(Direction.NOOP);

                }
            }

        }
    }

}
