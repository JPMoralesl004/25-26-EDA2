import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("java:S1220")
public class Criptoaritmetica {

    private static final Logger LOGGER = Logger.getLogger(Criptoaritmetica.class.getName());

    public static void main(String[] args) {
        if (args.length > 0) LOGGER.log(Level.INFO, "Iniciando búsqueda...");

        ejecutarAcertijo("ODD", "ODD", null, "EVEN");
        ejecutarAcertijo("FORTY", "TEN", "TEN", "SIXTY");
    }

    private static void ejecutarAcertijo(String s1, String s2, String s3, String res) {
        String todasLasPalabras = s1 + s2 + (s3 != null ? s3 : "") + res;
        String letrasUnicas = extraerLetrasUnicas(todasLasPalabras);
        String[] palabras = {s1, s2, s3, res};
        
        backtracking(0, letrasUnicas, new boolean[10], new LinkedHashMap<>(), palabras);
    }

    private static void backtracking(int idx, String letras, boolean[] usados,
                                    Map<Character, Integer> mapa, String[] p) {
        if (idx == letras.length()) {
            if (validarSuma(p[0], p[1], p[2], p[3], mapa)) {
                imprimirResultado(p[0], p[1], p[2], p[3], mapa);
            }
            return;
        }

        char letraActual = letras.charAt(idx);

        for (int d = 0; d <= 9; d++) {
            if (!usados[d] && !(d == 0 && esInicial(letraActual, p))) {
                mapa.put(letraActual, d);
                usados[d] = true;

                backtracking(idx + 1, letras, usados, mapa, p);

                usados[d] = false;
                mapa.remove(letraActual);
            }
        }
    }

    private static boolean validarSuma(String s1, String s2, String s3, String res, Map<Character, Integer> mapa) {
        long suma = convertir(s1, mapa) + convertir(s2, mapa);
        if (s3 != null) suma += convertir(s3, mapa);
        return suma == convertir(res, mapa);
    }

    private static long convertir(String s, Map<Character, Integer> mapa) {
        long num = 0;
        for (char c : s.toCharArray()) num = num * 10 + mapa.get(c);
        return num;
    }

    private static String extraerLetrasUnicas(String ecuacion) {
        StringBuilder sb = new StringBuilder();
        for (char c : ecuacion.toCharArray()) {
            if (sb.indexOf(String.valueOf(c)) == -1) sb.append(c);
        }
        return sb.toString();
    }

    private static boolean esInicial(char c, String[] palabras) {
        for (String p : palabras) {
            if (p != null && p.charAt(0) == c) return true;
        }
        return false;
    }

    private static void imprimirResultado(String s1, String s2, String s3, String res, Map<Character, Integer> mapa) {
        if (LOGGER.isLoggable(Level.INFO)) {
            StringBuilder sb = new StringBuilder();
            sb.append("\nSolución:");
            for (Map.Entry<Character, Integer> e : mapa.entrySet()) {
                sb.append("\n").append(e.getKey()).append(" = ").append(e.getValue());
            }
            sb.append("\n").append(s1).append(" = ").append(convertir(s1, mapa));
            sb.append("\n").append(s2).append(" = ").append(convertir(s2, mapa));
            if (s3 != null) sb.append("\n").append(s3).append(" = ").append(convertir(s3, mapa));
            sb.append("\nResultado = ").append(convertir(res, mapa)).append("\n------");
            LOGGER.log(Level.INFO, sb.toString());
        }
    }
}