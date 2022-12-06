package coffee;

public class Answer {
    private int maxCoffeShopCount;
    private int x, y;

    public Answer(int maxCSCount, int x, int y) {
        this.maxCoffeShopCount = maxCSCount;
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return this.maxCoffeShopCount + " (" + this.x + "," + this.y + ")";
    }

    @Override
    public boolean equals(Object ob) {

        if (ob instanceof Answer) {
            Answer answer = (Answer) ob;
            return this.x == answer.x &&
                this.y == answer.y &&
                this.maxCoffeShopCount == answer.maxCoffeShopCount;
        }

        return false;
    }

}
