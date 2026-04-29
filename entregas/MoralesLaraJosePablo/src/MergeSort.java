public class MergeSort {

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

        int medio = izquierda + (derecha - izquierda) / 2;
        System.out.println(ind() + "  medio=" + medio);

        System.out.println(ind() + "  -> Llamada izquierda [" + izquierda + ".." + medio + "]");
        nivel++;
        ordenar(array, izquierda, medio);
        nivel--;

        System.out.println(ind() + "  -> Llamada derecha [" + (medio + 1) + ".." + derecha + "]");
        nivel++;
        ordenar(array, medio + 1, derecha);
        nivel--;

        System.out.println(ind() + "  -> Fusionar [" + izquierda + ".." + medio + "] y ["
                + (medio + 1) + ".." + derecha + "]");
        fusionar(array, izquierda, medio, derecha);
        System.out.println(ind() + "  Array tras fusion: " + formatArray(array));
    }

    public static void ordenarIterativo(int[] array) {
        System.out.println("=== MERGE SORT ITERATIVO ===");
        System.out.println("Estado inicial: " + formatArray(array));

        int n = array.length;
        for (int tamano = 1; tamano < n; tamano *= 2) {
            System.out.println("\n--- Tamano de sublistas: " + tamano + " ---");

            for (int izquierda = 0; izquierda < n - tamano; izquierda += 2 * tamano) {
                int medio = izquierda + tamano - 1;
                int derecha = Math.min(izquierda + 2 * tamano - 1, n - 1);

                System.out.println("  Segmento [" + izquierda + ".." + derecha + "], medio=" + medio);
                System.out.println("    Mitad izquierda: " + formatSubarray(array, izquierda, medio));
                System.out.println("    Mitad derecha:   " + formatSubarray(array, medio + 1, derecha));

                fusionar(array, izquierda, medio, derecha);

                System.out.println("  Resultado: " + formatArray(array));
            }
        }

        System.out.println("\nEstado final: " + formatArray(array));
    }

    private static void fusionar(int[] array, int izquierda, int medio, int derecha) {
        int tamanoIzquierda = medio - izquierda + 1;
        int tamanoDerecha = derecha - medio;

        int[] mitadIzquierda = new int[tamanoIzquierda];
        int[] mitadDerecha = new int[tamanoDerecha];

        for (int i = 0; i < tamanoIzquierda; i++) {
            mitadIzquierda[i] = array[izquierda + i];
        }
        for (int i = 0; i < tamanoDerecha; i++) {
            mitadDerecha[i] = array[medio + 1 + i];
        }

        System.out.println(ind() + "    Copias - izq: " + formatArray(mitadIzquierda)
                + ", der: " + formatArray(mitadDerecha));

        int i = 0;
        int j = 0;
        int k = izquierda;

        while (i < tamanoIzquierda && j < tamanoDerecha) {
            if (mitadIzquierda[i] <= mitadDerecha[j]) {
                System.out.println(ind() + "    " + mitadIzquierda[i] + " <= " + mitadDerecha[j]
                        + " -> tomar izquierda, array[" + k + "] <- " + mitadIzquierda[i]);
                array[k] = mitadIzquierda[i];
                i++;
            } else {
                System.out.println(ind() + "    " + mitadIzquierda[i] + " > " + mitadDerecha[j]
                        + " -> tomar derecha,   array[" + k + "] <- " + mitadDerecha[j]);
                array[k] = mitadDerecha[j];
                j++;
            }
            k++;
        }

        while (i < tamanoIzquierda) {
            System.out.println(ind() + "    Resto izq -> array[" + k + "] <- " + mitadIzquierda[i]);
            array[k] = mitadIzquierda[i];
            i++;
            k++;
        }

        while (j < tamanoDerecha) {
            System.out.println(ind() + "    Resto der -> array[" + k + "] <- " + mitadDerecha[j]);
            array[k] = mitadDerecha[j];
            j++;
            k++;
        }
    }

    public static void main(String[] args) {
        int[] array1 = {5, 2, 8, 1, 9, 3};
        System.out.println("=== MERGE SORT RECURSIVO ===");
        System.out.println("Estado inicial: " + formatArray(array1));
        nivel = 0;
        ordenar(array1, 0, array1.length - 1);
        System.out.println("\nEstado final: " + formatArray(array1));

        System.out.println("\n" + "=".repeat(50) + "\n");

        int[] array2 = {5, 2, 8, 1, 9, 3};
        ordenarIterativo(array2);
    }
}