import java.util.Scanner;

public class BusquedaMatriz {

    public static int[] buscar(int[][] M, int k) {
        int fila = 0;
        int col = M[0].length - 1;

        while (fila < M.length && col >= 0) {
            if (M[fila][col] == k) {
                return new int[]{fila + 1, col + 1};
            } else if (k > M[fila][col]) {
                fila++;
            } else {
                col--;
            }
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        int[][] M = {
            { 2,  5,  9, 14, 21},
            { 4,  7, 11, 17, 25},
            { 8, 12, 15, 20, 30},
            {13, 18, 22, 27, 35},
            {19, 24, 28, 33, 40}
        };

        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce el numero a buscar: ");
        int k = scanner.nextInt();
        scanner.close();

        int[] resultado = buscar(M, k);
        if (resultado[0] != -1) {
            System.out.println("Encontrado en fila " + resultado[0] + ", columna " + resultado[1]);
        } else {
            System.out.println("No encontrado");
        }
    }
}