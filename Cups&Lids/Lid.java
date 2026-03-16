/**
 * Lid representa la tapa de una taza.
 * 
 * Cada tapa se dibuja como un rectángulo horizontal
 * colocado sobre la taza correspondiente.
 * 
 * Cumple los requisitos de tapas del Ciclo 1.
 */
public class Lid {

    private Rectangle lid;
    private int cupIndex;

    private boolean visible;

    private static final int CM = 10;

    /**
     * Constructor de tapa.
     */
    public Lid(int cupIndex,int width,int x,int y){

        this.cupIndex = cupIndex;

        lid = new Rectangle();
        lid.changeSize(CM,width*CM);

        lid.moveHorizontal(x-70);
        lid.moveVertical(y-15);

        lid.changeColor("black");
    }

    /**
     * Hace visible la tapa.
     */
    public void makeVisible(){

        visible = true;
        lid.makeVisible();
    }

    /**
     * Hace invisible la tapa.
     */
    public void makeInvisible(){

        visible = false;
        lid.makeInvisible();
    }

    /**
     * Retorna el índice de la taza cubierta.
     */
    public int getCupIndex(){
        return cupIndex;
    }
}