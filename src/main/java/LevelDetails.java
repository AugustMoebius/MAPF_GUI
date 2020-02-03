import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LevelDetails {
    List<String> initialLines = new ArrayList();
    private List<String> goalLines = new ArrayList();
    Map<Character, Point> agentPos = new HashMap<>();
    Map<Character, Point> goalPos = new HashMap<>();
    final int MAX_ROWS;
    final int MAX_COLS;


    public LevelDetails(File levelFile) {
        try {
            processLevelFile(levelFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.MAX_ROWS = initialLines.size();
        // Finds length of longest string
        this.MAX_COLS = initialLines.stream().map(String::length).max(Integer::compareTo).get();
    }

    private void processLevelFile(File levelFile) throws FileNotFoundException {
        boolean readingInitial = false;
        boolean readingGoals = false;

        Scanner scanner = new Scanner(levelFile);
        String line = scanner.nextLine();

        while(!line.equals("#end")) {
            switch (line) {
                case "#initial":
                    readingInitial = true;
                    line = scanner.nextLine();
                    break;
                case "#goal":
                    readingGoals = true;
                    readingInitial = false;
                    line = scanner.nextLine();
                    break;
            }

            if (readingInitial) {
                initialLines.add(line);
            }
            if (readingGoals) {
                goalLines.add(line);
            }

            line = scanner.nextLine();
        }
        setPositions();
    }

    private void setPositions() {
        Pattern p = Pattern.compile("[a-zA-Z]");
        for (int row = 0; row < initialLines.size(); row++) {
            for (int col = 0; col < initialLines.size(); col++) {
                char cInit = initialLines.get(row).charAt(col);
                char cGoal = goalLines.get(row).charAt(col);
                Matcher mInit = p.matcher(String.valueOf(cInit));
                Matcher mGoal = p.matcher(String.valueOf(cGoal));
                if (mInit.find()) {
                    agentPos.put(cInit, new Point(col, row));
                }
                if (mGoal.find()) {
                    agentPos.put(cGoal, new Point(col, row));
                }

            }
        }
    }

}
