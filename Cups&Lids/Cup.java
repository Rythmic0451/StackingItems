/**
 * Cup representa una taza cilíndrica del problema de la torre.
 * 
 * Cada taza tiene altura 2i-1 cm y se representa gráficamente
 * como una "U" formada por tres rectángulos:
 *  - pared izquierda
 *  - pared derecha
 *  - base
 * 
 * La base tiene grosor de 1 cm.
 * 
 * Esta clase cumple los requisitos de simulación visual del
 * Ciclo 1 del simulador.
 */
public class Cup {

    private Rectangle leftWall;
    private Rectangle rightWall;
    private Rectangle base;

    private int index;
    private int height;

    private int x;
    private int y;

    private boolean visible;

    private static final int CM = 10;

    /**
     * Constructor de una taza.
     * @param i índice de la taza
     * @param x posición horizontal
     * @param y posición vertical
     */
    public Cup(int i,int x,int y){

        this.index = i;
        this.height = 2*i-1;

        this.x = x;
        this.y = y;

        int h = height*CM;
        int width = (height+2)*CM;

        leftWall = new Rectangle();
        leftWall.changeSize(h,CM);

        rightWall = new Rectangle();
        rightWall.changeSize(h,CM);

        base = new Rectangle();
        base.changeSize(CM,width);

        leftWall.changeColor("blue");
        rightWall.changeColor("blue");
        base.changeColor("blue");

        moveTo(x,y);
    }

    /**
     * Mueve la taza a una nueva posición.
     */
    public void moveTo(int x,int y){

        this.x = x;
        this.y = y;

        int h = height*CM;
        int width = (height+2)*CM;

        leftWall.moveHorizontal(x-70);
        leftWall.moveVertical(y-15);

        rightWall.moveHorizontal(x + width - CM -70);
        rightWall.moveVertical(y-15);

        base.moveHorizontal(x-70);
        base.moveVertical(y + h - CM -15);
    }

    /**
     * Hace visible la taza.
     */
    public void makeVisible(){

        visible = true;

        leftWall.makeVisible();
        rightWall.makeVisible();
        base.makeVisible();
    }

    /**
     * Hace invisible la taza.
     */
    public void makeInvisible(){

        visible = false;

        leftWall.makeInvisible();
        rightWall.makeInvisible();
        base.makeInvisible();
    }

    /**
     * Retorna la altura de la taza en cm.
     */
    public int height(){
        return height;
    }

    /**
     * Retorna el índice de la taza.
     */
    public int getIndex(){
        return index;
    }
}