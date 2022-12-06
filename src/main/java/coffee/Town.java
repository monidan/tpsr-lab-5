package coffee;

import java.util.ArrayList;
import java.util.List;

public class Town {

    private final static int COFFEE_SHOP_CODE = 1;
    private int m, n;
    private int[][] square;

    public Town(int dx, int dy, int[] x, int[] y) {
        n = dx;
        m = dy;
        square = new int[m][n];

        for (int i = 0; i < x.length; i++) {
            square[y[i] - 1][x[i] - 1] = COFFEE_SHOP_CODE;
        }
    }

    public String getAnswerAsString(int[] maxDistance) {
        List<Answer> answers = this.getAnswer(maxDistance);
        String res = "";

        for (Answer answer : answers) {
            res += answer.toString() + '\n';
        }

        return res;
    }

    public List<Answer> getAnswer(int[] maxDistance) {
        List<Answer> res = new ArrayList<>();
        int q = maxDistance.length;

        for (int k = 0; k < q; k++) {
            int maxCoffeeShop = 0,
                    x = 0, y = 0;

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (square[i][j] != COFFEE_SHOP_CODE) {
                        int coffeShop = getCountCoffeShop(j, i, maxDistance[k]);

                        if (coffeShop > maxCoffeeShop) {
                            maxCoffeeShop = coffeShop;
                            x = j;
                            y = i;
                        }
                    }
                }
            }
            res.add(new Answer(maxCoffeeShop, x + 1, y + 1));
        }

        return res;
    }

    private int getCountCoffeShop(int x, int y, int maxDist) {
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (square[i][j] == COFFEE_SHOP_CODE &&
                        getDistance(x, y, j, i) <= maxDist) {
                    count++;
                }
            }
        }

        return count;
    }

    public static int getDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    @Override
    public String toString() {
        String res = "";

        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                res += square[i][j];
            }
            res += (i != 0 ? '\n' : "");
        }

        return res;
    }

}
