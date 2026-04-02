import java.util.*;

/**
 * TowerContest: resuelve el problema de la maratón de las tazas.
 * 
 * Permite:
 * 1. Calcular si es posible formar una torre de altura h con n tazas.
 * 2. Generar la secuencia de alturas de las tazas si es posible.
 * 3. Simular la torre apilando las tazas en el Canvas.
 */
public class TowerContest {

    /**
     * Resuelve el problema: devuelve la secuencia de alturas de tazas
     * que logra la torre de altura h, o "impossible" si no se puede.
     * 
     * @param n número de tazas
     * @param h altura deseada de la torre
     * @return String con las alturas separadas por espacio, o "impossible"
     */
    public static String solve(int n, long h) {
        // Altura mínima: taza más grande + (n-1) * base (1 cm)
        long minHeight = 2L * n - 1 + (n - 1);
        // Altura máxima: suma de todas las alturas (sin encaje)
        long maxHeight = (long) n * n;

        if (h < minHeight || h > maxHeight) {
            return "impossible";
        }

        // Calculamos cuánto "extra" necesitamos distribuir
        long extra = h - minHeight;

        // Inicializamos la torre en orden decreciente 
        List<Integer> cups = new ArrayList<>();
        for (int i = n; i >= 1; i--) {
            cups.add(i);
        }

        // Distribuimos el "extra" para ajustar la altura exacta
        for (int i = 0; i < n && extra > 0; i++) {
            long move = Math.min(extra, n - i - 1);
            int cup = cups.remove(i);
            cups.add((int) (i + move), cup);
            extra -= move;
        }

        // Convertimos a alturas reales
        StringBuilder ans = new StringBuilder();
        for (int c : cups) {
            ans.append(2 * c - 1).append(" ");
        }

        return ans.toString().trim();
    }

    /**
     * Simula la solución en el Canvas usando Tower y Cup.
     * 
     * @param n número de tazas
     * @param h altura deseada
     */
    public static void simulate(int n, long h) {
        String sol = solve(n, h);

        if (sol.equals("impossible")) {
            System.out.println("impossible");
            return;
        }

        // Crear torre
        Tower t = new Tower(n);

        // Convertir la secuencia de alturas a números de tazas
        String[] arr = sol.split(" ");
        for (String s : arr) {
            int cup = (Integer.parseInt(s) + 1) / 2; // de altura a i
            t.pushCup(cup);
        }

        // Hacer visible la torre
        t.makeVisible();

        // Mostrar altura total
        System.out.println("Height: " + t.height());
    }
}