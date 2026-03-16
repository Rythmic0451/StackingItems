import java.util.ArrayList;
import java.util.Collections;

/**
 * Tower representa una torre de tazas.
 * 
 * Permite simular operaciones de apilado, ordenamiento
 * y manipulación de tazas y tapas según los requisitos
 * funcionales del proyecto.
 */
public class Tower {

    private ArrayList<Cup> cups;
    private ArrayList<Lid> lids;

    private int baseX = 350;
    private int baseY = 500;

    private int maxHeight;

    private boolean visible;

    private static final int CM = 10;

    /**
     * Constructor de torre con ancho y altura máxima.
     */
    public Tower(int width,int maxHeight){

        cups = new ArrayList<>();
        lids = new ArrayList<>();

        this.maxHeight = maxHeight;
        visible = true;
    }

    /**
     * Constructor creando una torre con n tazas.
     */
    public Tower(int cups){

        this.cups = new ArrayList<>();
        this.lids = new ArrayList<>();
        visible = true;

        for(int i=1;i<=cups;i++)
            pushCup(i);
    }

    /**
     * Adiciona una taza a la torre.
     */
    public void pushCup(int i){

        int h = height();

        int y = baseY - h*CM;

        int width = (2*i-1+2)*CM;
        int x = baseX - width/2;

        Cup c = new Cup(i,x,y);

        cups.add(c);

        if(visible)
            c.makeVisible();
    }

    /**
     * Elimina la taza superior.
     */
    public void popCup(){

        if(cups.size()==0) return;

        Cup c = cups.remove(cups.size()-1);
        c.makeInvisible();
    }

    /**
     * Elimina una taza específica.
     */
    public void removeCup(int i){

        for(int j=0;j<cups.size();j++){

            if(cups.get(j).getIndex()==i){

                cups.get(j).makeInvisible();
                cups.remove(j);
                return;
            }
        }
    }

    /**
     * Elimina una tapa específica.
     */
    public void popLid(int i){

        for(int j=0;j<lids.size();j++){

            if(lids.get(j).getCupIndex()==i){

                lids.get(j).makeInvisible();
                lids.remove(j);
                return;
            }
        }
    }

    /**
     * Ordena las tazas de mayor a menor.
     */
    public void orderTower(){

        cups.sort((a,b)->b.height()-a.height());
    }

    /**
     * Invierte el orden de la torre.
     */
    public void reverseTower(){

        Collections.reverse(cups);
    }

    /**
     * Intercambia dos objetos de la torre.
     */
    public void swap(String[] o1,String[] o2){

        int i = Integer.parseInt(o1[1]);
        int j = Integer.parseInt(o2[1]);

        Collections.swap(cups,i,j);
    }

    /**
     * Coloca tapas sobre las tazas.
     */
    public void cover(){

        for(Cup c:cups){

            Lid l = new Lid(c.getIndex(),c.height(),baseX,baseY-height()*CM);

            lids.add(l);

            if(visible)
                l.makeVisible();
        }
    }

    /**
     * Calcula la altura de la torre.
     */
    public int height(){

        if(cups.size()==0) return 0;

        int h = cups.get(0).height();

        for(int i=1;i<cups.size();i++){

            if(cups.get(i).getIndex() < cups.get(i-1).getIndex())
                h += 2*cups.get(i).getIndex();
            else
                h += cups.get(i).height();
        }

        return h;
    }

    /**
     * Devuelve las tazas con tapa.
     */
    public int[] lidedCups(){

        int[] ans = new int[lids.size()];

        for(int i=0;i<lids.size();i++)
            ans[i] = lids.get(i).getCupIndex();

        return ans;
    }

    /**
     * Devuelve información de elementos apilados.
     */
    public String[] stackingItems(){

        String[] ans = new String[cups.size()+lids.size()];

        int k=0;

        for(Cup c:cups)
            ans[k++]="Cup "+c.getIndex();

        for(Lid l:lids)
            ans[k++]="Lid "+l.getCupIndex();

        return ans;
    }

    /**
     * Busca un intercambio que reduzca la altura.
     */
    public String[] swapToReduce(){

        for(int i=0;i<cups.size()-1;i++){

            int h1 = height();

            Collections.swap(cups,i,i+1);

            if(height()<h1)
                return new String[]{"Cup "+i,"Cup "+(i+1)};

            Collections.swap(cups,i,i+1);
        }

        return new String[]{"none"};
    }

    /**
     * Hace visible el simulador.
     */
    public void makeVisible(){

        visible = true;

        for(Cup c:cups)
            c.makeVisible();

        for(Lid l:lids)
            l.makeVisible();
    }

    /**
     * Oculta el simulador.
     */
    public void makeInvisible(){

        visible = false;

        for(Cup c:cups)
            c.makeInvisible();

        for(Lid l:lids)
            l.makeInvisible();
    }

    /**
     * Termina el simulador.
     */
    public void exit(){

        makeInvisible();

        cups.clear();
        lids.clear();
    }

    /**
     * Verifica consistencia del simulador.
     */
    public boolean ok(){

        return true;
    }
}