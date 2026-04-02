import java.util.Random;

public class Lid {
    private Rectangle lid;
    private int cupIndex;
    private int lidWidth;
    private String color;
    private static final int CM = 20;

    // Esquina superior izquierda actual
    private int currentX;
    private int currentY;

    public Lid(int cupIndex, int widthUnits, int cx, int bottomY) {
        this.cupIndex = cupIndex;
        this.lidWidth = widthUnits * CM;
        this.color = randomColor();

        lid = new Rectangle();
        lid.changeColor(color);
        lid.changeSize(CM / 2, lidWidth);

        // Rectangle siempre inicia en (70, 15)
        currentX = 70;
        currentY = 15;

        moveTo(cx, bottomY);
    }

    public void moveTo(int cx, int bottomY) {
        int wantX = cx - lidWidth / 2;
        int wantY = bottomY - CM / 2;

        lid.moveHorizontal(wantX - currentX);
        lid.moveVertical(wantY - currentY);

        currentX = wantX;
        currentY = wantY;
    }

    public void makeVisible() {
        lid.makeVisible();
    }

    public void makeInvisible() {
        lid.makeInvisible();
    }

    public int getCupIndex() {
        return cupIndex;
    }

    private String randomColor() {
        String[] colors = {"red", "blue", "green", "yellow", "magenta"};
        return colors[new Random().nextInt(colors.length)];
    }
}