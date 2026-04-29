public class QuickSort {

    private static int nivel = 0;

    private static String formatArray(int[] array) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i < array.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    private static String formatSubarray(int[] array, int desde, int hasta) {
        if (desde > hasta) return "[]";
        StringBuilder sb = new StringBuilder("[");
        for (int i = desde; i <= hasta; i++) {
            sb.append(array[i]);
            if (i < hasta) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    private static String ind() {
        return "  ".repeat(nivel);
    }

    public static void ordenar(int[] array, int izquierda, int derecha) {
        System.out.println("\n" + ind() + "ordenar([" + izquierda + ".." + derecha + "]) -> "
                + formatSubarray(array, izquierda, derecha));

        if (izquierda >= derecha) {
            System.out.println(ind() + "  CASO BASE: izquierda(" + izquierda
                    + ") >= derecha(" + derecha + ") -> retornar");
            return;
        }

        System.out.println(ind() + "  -> Particionar [" + izquierda + ".." + derecha + "]");
        int indicePivote = particionar(array, izquierda, derecha);

        System.out.println(ind() + "  Pivote fijo en indice " + indicePivote
                + " (valor " + array[indicePivote] + ")");

        System.out.println(ind() + "  -> Llamada izquierda [" + izquierda + ".." + (indicePivote - 1) + "]");
        nivel++;
        ordenar(array, izquierda, indicePivote - 1);
        nivel--;

        System.out.println(ind() + "  -> Llamada derecha [" + (indicePivote + 1) + ".." + derecha + "]");
        nivel++;
        ordenar(array, indicePivote + 1, derecha);
        nivel--;
    }

    private static int particionar(int[] array, int izquierda, int derecha) {
        int pivote = array[derecha];
        System.out.println(ind() + "    Pivote = array[" + derecha + "] = " + pivote);

        int i = izquierda - 1;
        System.out.println(ind() + "    i inicial = " + i);

        for (int j = izquierda; j < derecha; j++) {
            boolean cumple = array[j] <= pivote;
            System.out.print(ind() + "    j=" + j + ": array[j]=" + array[j]
                    + " <= " + pivote + " -> " + cumple);

            if (array[j] <= pivote) {
                i++;
                System.out.println(", intercambiar array[" + i + "] <-> array[" + j + "]");
                int temporal = array[i];
                array[i] = array[j];
                array[j] = temporal;
                System.out.println(ind() + "    " + formatArray(array));
            } else {
                System.out.println(", no intercambiar");
            }
        }

        System.out.println(ind() + "    Colocar pivote: array[" + (i + 1) + "] <-> array[" + derecha + "]");
        int temporal = array[i + 1];
        array[i + 1] = array[derecha];
        array[derecha] = temporal;
        System.out.println(ind() + "    " + formatArray(array));

        return i + 1;
    }

    public static void main(String[] args) {
        int[] array = {5, 2, 8, 1, 9, 3};
        System.out.println("=== QUICK SORT ===");
        System.out.println("Estado inicial: " + formatArray(array));
        nivel = 0;
        ordenar(array, 0, array.length - 1);
        System.out.println("\nEstado final: " + formatArray(array));
    }
}