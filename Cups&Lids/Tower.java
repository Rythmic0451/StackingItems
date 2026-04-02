import java.util.ArrayList;

public class Tower {
    private ArrayList<Cup> cups;
    private ArrayList<Lid> lids;
    private Rectangle base;
    private int x;
    private int y;
    private boolean visible;
    private static final int CM = 20;

    public Tower(int width, int maxHeight) {
        cups = new ArrayList<>();
        lids = new ArrayList<>();
        x = 300;
        y = 400;
        int pixelWidth = width * CM;
        base = new Rectangle();
        base.changeColor("black");
        base.changeSize(CM / 2, pixelWidth);
        base.moveHorizontal((x - pixelWidth / 2) - 70);
        base.moveVertical(y - 15);
        visible = false;
    }

    public Tower(int numCups) {
        cups = new ArrayList<>();
        lids = new ArrayList<>();
        x = 300;
        y = 400;
        int pixelWidth = numCups * CM * 2;
        base = new Rectangle();
        base.changeColor("black");
        base.changeSize(CM / 2, pixelWidth);
        base.moveHorizontal((x - pixelWidth / 2) - 70);
        base.moveVertical(y - 15);
        visible = false;
    }

    public void pushCup(int i) {
        Cup newCup = new Cup(i, x, y);
        cups.add(newCup);
        cups.sort((a, b) -> b.getIndex() - a.getIndex());
        repositionAll();
        if (visible) newCup.makeVisible();
    }

    public void popCup() {
        if (!cups.isEmpty()) {
            Cup c = cups.remove(cups.size() - 1);
            c.makeInvisible();
        }
    }

    public void removeCup(int i) {
        for (int idx = 0; idx < cups.size(); idx++) {
            if (cups.get(idx).getIndex() == i) {
                cups.get(idx).makeInvisible();
                cups.remove(idx);
                repositionAll();
                return;
            }
        }
    }

    public void pushLid(int cupIndex) {
        int stackedHeight = 0;
        for (Cup c : cups) {
            stackedHeight += CM;
            if (c.getIndex() == cupIndex) break;
        }
        int lidY = y - stackedHeight - cupIndex * CM;
        Lid lid = new Lid(cupIndex, 2 * cupIndex - 1, x, lidY);
        lids.add(lid);
        if (visible) lid.makeVisible();
    }

    public void popLid(int cupIndex) {
        for (int i = lids.size() - 1; i >= 0; i--) {
            if (lids.get(i).getCupIndex() == cupIndex) {
                lids.get(i).makeInvisible();
                lids.remove(i);
                return;
            }
        }
    }

    public void swap(String[] o1, String[] o2) {
        int idx1 = -1, idx2 = -1;
        for (int i = 0; i < cups.size(); i++) {
            if (String.valueOf(cups.get(i).getIndex()).equals(o1[1])) idx1 = i;
            if (String.valueOf(cups.get(i).getIndex()).equals(o2[1])) idx2 = i;
        }
        if (idx1 != -1 && idx2 != -1) {
            Cup temp = cups.get(idx1);
            cups.set(idx1, cups.get(idx2));
            cups.set(idx2, temp);
            repositionAll();
        }
    }

    public void orderTower() {
        cups.sort((a, b) -> b.getIndex() - a.getIndex());
        repositionAll();
    }

    public void reverseTower() {
        cups.sort((a, b) -> a.getIndex() - b.getIndex());
        repositionAll();
    }

    public void cover() {
        for (Cup c : cups) {
            boolean alreadyCovered = false;
            for (Lid l : lids) {
                if (l.getCupIndex() == c.getIndex()) {
                    alreadyCovered = true;
                    break;
                }
            }
            if (!alreadyCovered) {
                pushLid(c.getIndex());
            }
        }
    }

    public int[] lidedCups() {
        int count = 0;
        for (Lid l : lids) {
            for (Cup c : cups) {
                if (c.getIndex() == l.getCupIndex()) {
                    count++;
                    break;
                }
            }
        }
        int[] result = new int[count];
        int idx = 0;
        for (Lid l : lids) {
            for (Cup c : cups) {
                if (c.getIndex() == l.getCupIndex()) {
                    result[idx++] = c.getIndex();
                    break;
                }
            }
        }
        return result;
    }

    public String[] stackingItems() {
        String[] result = new String[cups.size()];
        for (int i = 0; i < cups.size(); i++) {
            result[i] = "Cup " + cups.get(i).getIndex();
        }
        return result;
    }

    public String[] swapToReduce() {
        for (int i = 0; i < cups.size() - 1; i++) {
            for (int j = i + 1; j < cups.size(); j++) {
                Cup a = cups.get(i);
                Cup b = cups.get(j);
                if (a.getIndex() < b.getIndex()) {
                    return new String[]{
                        "Cup " + a.getIndex(),
                        "Cup " + b.getIndex()
                    };
                }
            }
        }
        return new String[]{};
    }

    public int height() {
        int h = 0;
        for (Cup c : cups) h += CM;
        return h;
    }

    private void repositionAll() {
        int stackedHeight = 0;
        for (Cup c : cups) {
            int bottomY = y - stackedHeight;
            c.moveTo(x, bottomY);
            stackedHeight += CM;
        }
    }

    public void makeVisible() {
        visible = true;
        base.makeVisible();
        for (Cup c : cups) c.makeVisible();
        for (Lid l : lids) l.makeVisible();
    }

    public void makeInvisible() {
        visible = false;
        base.makeInvisible();
        for (Cup c : cups) c.makeInvisible();
        for (Lid l : lids) l.makeInvisible();
    }

    public void exit() {
        makeInvisible();
    }

    public boolean ok() {
        return cups != null && lids != null;
    }
}