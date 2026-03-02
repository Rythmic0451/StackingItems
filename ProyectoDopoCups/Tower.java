import java.util.ArrayList;
import java.util.Collections;

/**
 * Torre con forma de vaso grande que administra
 * Cups y Lids usando listas separadas.
 */
public class Tower {

    private int width;
    private int maxHeight;
    private int currentHeight;
    private boolean isVisible;

    private Rectangle leftWall;
    private Rectangle rightWall;
    private Rectangle base;

    private int x = 200;
    private int y = 350;

    private ArrayList<Cup> cups;
    private ArrayList<Lid> lids;
    private ArrayList<String> order;

    /**
     * Constructor principal.
     */
    public Tower(int width, int maxHeight) {

        this.width = width;
        this.maxHeight = maxHeight;
        this.currentHeight = 0;
        this.isVisible = true;

        cups = new ArrayList<>();
        lids = new ArrayList<>();
        order = new ArrayList<>();

        buildTower();
    }

    /**
     * Constructor que crea n tazas.
     */
    public Tower(int cupsNumber) {
        this(120, 400);
        for(int i = 1; i <= cupsNumber; i++) {
            pushCup(i);
        }
    }

    /**
     * Construye el vaso grande de la torre.
     */
    private void buildTower() {

        leftWall = new Rectangle();
        leftWall.changeSize(maxHeight, 5);
        leftWall.moveHorizontal(x);
        leftWall.moveVertical(y - maxHeight);

        rightWall = new Rectangle();
        rightWall.changeSize(maxHeight, 5);
        rightWall.moveHorizontal(x + width);
        rightWall.moveVertical(y - maxHeight);

        base = new Rectangle();
        base.changeSize(5, width);
        base.moveHorizontal(x);
        base.moveVertical(y);

        if(isVisible) {
            leftWall.makeVisible();
            rightWall.makeVisible();
            base.makeVisible();
        }
    }

    /**
     * Agrega una taza si hay espacio.
     */
    public void pushCup(int id) {

        if(currentHeight + 60 > maxHeight) return;

        int baseY = y - currentHeight;
        Cup cup = new Cup(id, x + 10, baseY);

        if(isVisible) cup.makeVisible();

        cups.add(cup);
        order.add("C" + id);
        currentHeight += cup.getHeight();
    }

    /**
     * Agrega una tapa si hay espacio.
     */
    public void pushLid(int id) {

        if(currentHeight + 5 > maxHeight) return;

        int baseY = y - currentHeight;
        Lid lid = new Lid(id, x + 5, baseY);

        if(isVisible) lid.makeVisible();

        lids.add(lid);
        order.add("L" + id);
        currentHeight += lid.getHeight();
    }

    /**
     * Retorna una taza sin eliminarla.
     */
    public Cup popCup(int id) {
        for(Cup c : cups)
            if(c.getId() == id)
                return c;
        return null;
    }

    /**
     * Retorna una tapa sin eliminarla.
     */
    public Lid popLid(int id) {
        for(Lid l : lids)
            if(l.getId() == id)
                return l;
        return null;
    }

    /**
     * Elimina una taza y su tapa asociada.
     */
    public void removeCup(int id) {
        removeLid(id);
        cups.removeIf(c -> c.getId() == id);
        order.removeIf(s -> s.equals("C" + id));
        redraw();
    }

    /**
     * Elimina una tapa.
     */
    public void removeLid(int id) {
        lids.removeIf(l -> l.getId() == id);
        order.removeIf(s -> s.equals("L" + id));
        redraw();
    }

    /**
     * Ordena la torre por altura descendente.
     */
    public void orderTower() {
        order.sort((a,b) -> getHeight(b) - getHeight(a));
        redraw();
    }

    /**
     * Invierte el orden actual.
     */
    public void reverseTower() {
        Collections.reverse(order);
        redraw();
    }

    /**
     * Intercambia dos posiciones.
     */
    public void swap(int i, int j) {
        Collections.swap(order, i, j);
        redraw();
    }

    /**
     * Marca como cubiertas las tazas que tengan su tapa.
     */
    public void cover() {
        for(Cup c : cups)
            for(Lid l : lids)
                if(c.getId() == l.getId())
                    c.setCovered(true);
    }

    /**
     * Retorna la altura actual apilada.
     */
    public int height() {
        return currentHeight;
    }

    /**
     * Retorna ids de tazas cubiertas.
     */
    public int[] LidCups() {
        ArrayList<Integer> ids = new ArrayList<>();
        for(Cup c : cups)
            if(c.isCovered())
                ids.add(c.getId());
        return ids.stream().mapToInt(i -> i).toArray();
    }

    /**
     * Retorna el orden actual de apilado.
     */
    public String[] stackingitems() {
        return order.toArray(new String[0]);
    }

    /**
     * Verifica que la torre no exceda la altura máxima.
     */
    public boolean ok() {
        return currentHeight <= maxHeight;
    }

    /**
     * Busca un intercambio que reduzca altura.
     */
    public String[] swapToReduce() {

        for(int i = 0; i < order.size(); i++)
            for(int j = i+1; j < order.size(); j++)
                if(getHeight(order.get(i)) <
                   getHeight(order.get(j)))
                    return new String[]{order.get(i), order.get(j)};

        return new String[0];
    }

    /**
     * Hace visible toda la torre.
     */
    public void makeVisible() {

        isVisible = true;
        leftWall.makeVisible();
        rightWall.makeVisible();
        base.makeVisible();

        for(Cup c : cups) c.makeVisible();
        for(Lid l : lids) l.makeVisible();
    }

    /**
     * Hace invisible toda la torre.
     */
    public void makeInvisible() {

        isVisible = false;
        leftWall.makeInvisible();
        rightWall.makeInvisible();
        base.makeInvisible();

        for(Cup c : cups) c.makeInvisible();
        for(Lid l : lids) l.makeInvisible();
    }

    /**
     * Vacía completamente la torre.
     */
    public void exit() {
        makeInvisible();
        cups.clear();
        lids.clear();
        order.clear();
        currentHeight = 0;
    }

    /*MÉTODOS AUXILIARES  */

    private int getHeight(String code) {

        if(code.startsWith("C")) {
            for(Cup c : cups)
                if(c.getId() ==
                   Integer.parseInt(code.substring(1)))
                    return c.getHeight();
        }

        if(code.startsWith("L")) {
            for(Lid l : lids)
                if(l.getId() ==
                   Integer.parseInt(code.substring(1)))
                    return l.getHeight();
        }

        return 0;
    }

    private void redraw() {

        currentHeight = 0;

        for(String s : order) {

            int baseY = y - currentHeight;

            if(s.startsWith("C")) {
                for(Cup c : cups)
                    if(s.equals("C" + c.getId())) {
                        c.moveVertical(-1000);
                        c.moveVertical(baseY);
                        currentHeight += c.getHeight();
                    }
            }

            if(s.startsWith("L")) {
                for(Lid l : lids)
                    if(s.equals("L" + l.getId())) {
                        l.moveVertical(-1000);
                        l.moveVertical(baseY);
                        currentHeight += l.getHeight();
                    }
            }
        }
    }
}