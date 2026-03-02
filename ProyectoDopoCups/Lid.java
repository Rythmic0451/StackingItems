
/**
 * Representa una tapa como una línea horizontal.
 */
public class Lid {

    private int id;
    private int height;
    private Rectangle top;

    private int x;
    private int y;
    private int width = 45;

    /**
     * Constructor de la tapa.
     * @param id identificador asociado a una taza
     * @param x posición horizontal
     * @param y posición vertical base
     */
    public Lid(int id, int x, int y) {
        this.id = id;
        this.height = 5;
        this.x = x;
        this.y = y;

        buildLid();
    }

    /**
     * Construye gráficamente la tapa.
     */
    private void buildLid() {
        top = new Rectangle();
        top.changeSize(height, width);
        top.moveHorizontal(x);
        top.moveVertical(y - height);
        top.changeColor("red");
    }

    /**
     * Hace visible la tapa.
     */
    public void makeVisible() {
        top.makeVisible();
    }

    /**
     * Hace invisible la tapa.
     */
    public void makeInvisible() {
        top.makeInvisible();
    }

    /**
     * Mueve verticalmente la tapa.
     */
    public void moveVertical(int distance) {
        top.moveVertical(distance);
        y += distance;
    }

    /**
     * Retorna la altura gráfica.
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
}