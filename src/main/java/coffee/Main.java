package coffee;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        getResult(getTownCaseFromFile("input.txt"), true);
    }

    public static List<Answer> getResult(List<String> townCases, boolean show) {

        List<Answer> res = new ArrayList<>();

        int numberOfCase = 1;

        for (String townCase : townCases) {
            boolean isOK = true;
            String[] townCaseLines = townCase.split("\n");
            String[] params = townCaseLines[0].split(" ");
            int dx = Integer.parseInt(params[0]),
                    dy = Integer.parseInt(params[1]),
                    n = Integer.parseInt(params[2]),
                    q = Integer.parseInt(params[3]);

            if (!isValidInputData(dx, dy, n, q)) {
                System.out.println("Invalid first line in town case!");
                continue;
            }

            int[] x = new int[n],
                    y = new int[n],
                    m = new int[q];

            for (int i = 1; i <= n && isOK; i++) {
                params = townCaseLines[i].split(" ");
                x[i - 1] = Integer.parseInt(params[0]);
                y[i - 1] = Integer.parseInt(params[1]);

                if (!(1 <= x[i - 1] && x[i - 1] <= dx &&
                        1 <= y[i - 1] && y[i - 1] <= dy)) {
                    isOK = false;
                    break;
                }
            }

            for (int i = 0; i < q && isOK; i++) {
                m[i] = Integer.parseInt(townCaseLines[i + n + 1]);

                if (!(0 <= m[i] && m[i] <= 1000000)) {
                    isOK = false;
                    break;
                }
            }

            if (!isOK)
                continue;

            Town town = new Town(dx, dy, x, y);
            res.addAll(town.getAnswer(m));
            if (show) {
                System.out.println("Case " + numberOfCase++ + ":");
                System.out.println(town.getAnswerAsString(m));
            } else {
                numberOfCase++;
            }
        }

        return res;
    }

    private static boolean isValidInputData(int dx, int dy, int n, int q) {
        return 1 <= dx && dx <= 1000 &&
                1 <= dy && dy <= 1000 &&
                0 <= n && n <= 500000 &&
                1 <= q && q <= 20;
    }


    public static List<String> getTownCaseFromFile(String fileName) {

        String endCase = "0 0 0 0";

        List<String> townCases = new ArrayList<>();
        File inputFile = new File(fileName);

        if (inputFile.exists()) {
            try {
                List<String> lines = Files.readAllLines(inputFile.toPath());
                StringBuilder currentCase = new StringBuilder();
                int endCounter = 0;
                for (String line : lines) {
                    if (line.equals(endCase)) {
                        townCases.add(currentCase.toString());
                        currentCase = new StringBuilder();
                        endCounter++;
                    } else {
                        currentCase.append(line).append('\n');
                    }
                }
                if (endCounter == 0) {
                    return townCases = new ArrayList<>();
                }
            } catch (Exception exception) {
                System.err.println(exception.getMessage());
            }
        } else {
            System.err.println(fileName + " does not exist!");
        }

        return townCases;
    }

}
