import java.util.Random;

public class Cup {
    private Rectangle leftWall;
    private Rectangle rightWall;
    private Rectangle base;
    private int index;
    private String color;
    private static final int CM = 20;

    private int leftX,  leftY;
    private int rightX, rightY;
    private int baseX,  baseY;

    public Cup(int i, int cx, int bottomY) {
        this.index = i;
        this.color = randomColor();

        leftWall  = new Rectangle();
        rightWall = new Rectangle();
        base      = new Rectangle();

        leftWall.changeColor(color);
        rightWall.changeColor(color);
        base.changeColor(color);

        int h = i * CM;
        int w = (2 * i - 1) * CM;

        leftWall.changeSize(h, CM);
        rightWall.changeSize(h, CM);
        base.changeSize(CM, w);

        leftX  = 70; leftY  = 15;
        rightX = 70; rightY = 15;
        baseX  = 70; baseY  = 15;

        moveTo(cx, bottomY);
    }

    public void moveTo(int cx, int bottomY) {
        int h = index * CM;
        int w = (2 * index - 1) * CM;

        int wantLeftX  = cx - w / 2;
        int wantLeftY  = bottomY - h - CM;

        int wantRightX = cx + w / 2 - CM;
        int wantRightY = bottomY - h - CM;

        int wantBaseX  = cx - w / 2;
        int wantBaseY  = bottomY - CM;

        leftWall.moveHorizontal (wantLeftX  - leftX);
        leftWall.moveVertical   (wantLeftY  - leftY);
        rightWall.moveHorizontal(wantRightX - rightX);
        rightWall.moveVertical  (wantRightY - rightY);
        base.moveHorizontal     (wantBaseX  - baseX);
        base.moveVertical       (wantBaseY  - baseY);

        leftX  = wantLeftX;  leftY  = wantLeftY;
        rightX = wantRightX; rightY = wantRightY;
        baseX  = wantBaseX;  baseY  = wantBaseY;
    }

    public void makeVisible() {
        leftWall.makeVisible();
        rightWall.makeVisible();
        base.makeVisible();
    }

    public void makeInvisible() {
        leftWall.makeInvisible();
        rightWall.makeInvisible();
        base.makeInvisible();
    }

    public int getIndex()       { return index; }
    public int getHeightUnits() { return index * CM; }
    public int getTotalHeight() { return index * CM + CM; }

    private String randomColor() {
        String[] colors = {"red", "blue", "green", "yellow", "magenta"};
        return colors[new Random().nextInt(colors.length)];
    }
}