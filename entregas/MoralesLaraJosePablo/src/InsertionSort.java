public class InsertionSort {

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

    private static String ind() {
        return "  ".repeat(nivel);
    }

    public static void ordenar(int[] array) {
        System.out.println("=== INSERTION SORT ITERATIVO ===");
        System.out.println("Estado inicial: " + formatArray(array));

        for (int i = 1; i < array.length; i++) {
            int actual = array[i];
            System.out.println("\n--- Iteracion i=" + i + ", actual=" + actual + " ---");

            int j = i - 1;
            while (j >= 0 && array[j] > actual) {
                System.out.println("  Comparar array[" + j + "]=" + array[j] + " > " + actual + " -> true, desplazar");
                array[j + 1] = array[j];
                System.out.println("  Desplazar: array[" + (j + 1) + "] <- " + array[j + 1] + "   " + formatArray(array));
                j--;
            }

            if (j >= 0) {
                System.out.println("  Comparar array[" + j + "]=" + array[j] + " > " + actual + " -> false, detener");
            } else {
                System.out.println("  j=" + j + " < 0, llegamos al inicio");
            }

            array[j + 1] = actual;
            System.out.println("  Insertar: array[" + (j + 1) + "] <- " + actual + "   " + formatArray(array));
        }

        System.out.println("\nEstado final: " + formatArray(array));
    }

    public static void ordenar(int[] array, int n) {
        if (n == array.length) {
            System.out.println("=== INSERTION SORT RECURSIVO ===");
            System.out.println("Estado inicial: " + formatArray(array));
        }

        System.out.println("\n" + ind() + "ordenar(n=" + n + ")");

        if (n <= 1) {
            System.out.println(ind() + "  CASO BASE: n<=1 -> retornar");
            return;
        }

        nivel++;
        ordenar(array, n - 1);
        nivel--;

        int ultimo = array[n - 1];
        System.out.println(ind() + "  Volver a n=" + n + ", insertar ultimo=" + ultimo);

        int j = n - 2;
        while (j >= 0 && array[j] > ultimo) {
            System.out.println(ind() + "  Comparar array[" + j + "]=" + array[j] + " > " + ultimo + " -> true, desplazar");
            array[j + 1] = array[j];
            System.out.println(ind() + "  Desplazar: array[" + (j + 1) + "] <- " + array[j + 1] + "   " + formatArray(array));
            j--;
        }

        if (j >= 0) {
            System.out.println(ind() + "  Comparar array[" + j + "]=" + array[j] + " > " + ultimo + " -> false, detener");
        } else {
            System.out.println(ind() + "  j<0, llegamos al inicio");
        }

        array[j + 1] = ultimo;
        System.out.println(ind() + "  Insertar: array[" + (j + 1) + "] <- " + ultimo + "   " + formatArray(array));

        if (n == array.length) {
            System.out.println("\nEstado final: " + formatArray(array));
        }
    }

    public static void main(String[] args) {
        int[] array1 = {5, 2, 8, 1, 9, 3};
        ordenar(array1);

        System.out.println("\n" + "=".repeat(50) + "\n");

        nivel = 0;
        int[] array2 = {5, 2, 8, 1, 9, 3};
        ordenar(array2, array2.length);
    }
}