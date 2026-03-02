
/**
 * Representa una taza con forma de vaso.
 * Está compuesta por dos lados verticales y una base horizontal.
 */
public class Cup {

    private int id;
    private int height;
    private boolean covered;

    private Rectangle leftSide;
    private Rectangle rightSide;
    private Rectangle base;

    private int x;
    private int y;
    private int width = 40;

    /**
     * Constructor de la taza.
     * @param id identificador único
     * @param x posición horizontal base
     * @param y posición vertical base
     */
    public Cup(int id, int x, int y) {
        this.id = id;
        this.height = 60;
        this.covered = false;
        this.x = x;
        this.y = y;

        buildCup();
    }

    /**
     * Construye gráficamente la taza.
     */
    private void buildCup() {

        leftSide = new Rectangle();
        leftSide.changeSize(height, 5);
        leftSide.moveHorizontal(x);
        leftSide.moveVertical(y - height);

        rightSide = new Rectangle();
        rightSide.changeSize(height, 5);
        rightSide.moveHorizontal(x + width);
        rightSide.moveVertical(y - height);

        base = new Rectangle();
        base.changeSize(5, width);
        base.moveHorizontal(x);
        base.moveVertical(y);
    }

    /**
     * Hace visible la taza.
     */
    public void makeVisible() {
        leftSide.makeVisible();
        rightSide.makeVisible();
        base.makeVisible();
    }

    /**
     * Hace invisible la taza.
     */
    public void makeInvisible() {
        leftSide.makeInvisible();
        rightSide.makeInvisible();
        base.makeInvisible();
    }

    /**
     * Mueve verticalmente la taza completa.
     * @param distance distancia en píxeles
     */
    public void moveVertical(int distance) {
        leftSide.moveVertical(distance);
        rightSide.moveVertical(distance);
        base.moveVertical(distance);
        y += distance;
    }

    /**
     * Retorna la altura gráfica de la taza.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Retorna el identificador.
     */
    public int getId() {
        return id;
    }

    /**
     * Indica si la taza está cubierta.
     */
    public boolean isCovered() {
        return covered;
    }

    /**
     * Marca la taza como cubierta o no.
     */
    public void setCovered(boolean state) {
        covered = state;
    }
}