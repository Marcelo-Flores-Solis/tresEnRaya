import java.util.Scanner;

public class TresEnRaya {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[][] tablero = crearTablero();

        System.out.println("Bienvenido al juego Tres en Raya (Gato)");
        String miLetra = ingresarLetra(sc);
        String letraOponente = miLetra.equals("X") ? "O" : "X";
        String letraActual;

        int turnos = 0;
        boolean hayGanador = false;

        while (turnos < 9 && !hayGanador) {
            letraActual = (turnos % 2 == 0) ? miLetra : letraOponente;
            System.out.println("\nTurno de " + letraActual);
            mostrarTablero(tablero);

            int fila, columna;
            do {
                System.out.print("Ingrese fila (0-2): ");
                fila = sc.nextInt();
                System.out.print("Ingrese columna (0-2): ");
                columna = sc.nextInt();
            } while (!esMovimientoValido(tablero, fila, columna));

            realizarJugada(fila, columna, tablero, letraActual);

            if (esJugadaGanadora(tablero, letraActual)) {
                hayGanador = true;
                mostrarTablero(tablero);
                System.out.println("¡Ganó el jugador con la letra: " + letraActual + "!");
                break;
            }

            turnos++;
        }

        if (!hayGanador) {
            mostrarTablero(tablero);
            System.out.println("Empate. ¡Gracias por jugar!");
        }

        sc.close();
    }

    public static String[][] crearTablero() {
        String[][] tablero = new String[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                tablero[i][j] = "-";
        return tablero;
    }

    public static String ingresarLetra(Scanner sc) {
        String letra;
        do {
            System.out.print("Elija su letra (X / O): ");
            letra = sc.next().toUpperCase();
        } while (!letra.equals("X") && !letra.equals("O"));
        return letra;
    }

    public static boolean esMovimientoValido(String[][] tablero, int fila, int columna) {
        if (fila < 0 || fila > 2 || columna < 0 || columna > 2) {
            System.out.println("Posición fuera del rango. Intente de nuevo.");
            return false;
        }
        if (!tablero[fila][columna].equals("-")) {
            System.out.println("Casilla ocupada. Intente otra.");
            return false;
        }
        return true;
    }

    public static void realizarJugada(int fila, int columna, String[][] tablero, String letra) {
        tablero[fila][columna] = letra;
    }

    public static void mostrarTablero(String[][] tablero) {
        System.out.println("\n    0   1   2");
        System.out.println("  ┌───┬───┬───┐");
        for (int i = 0; i < tablero.length; i++) {
            System.out.print(i + " │");
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.print(" " + tablero[i][j] + " │");
            }
            if (i < tablero.length - 1) {
                System.out.println("\n  ├───┼───┼───┤");
            } else {
                System.out.println("\n  └───┴───┴───┘");
            }
        }
    }

    public static boolean esJugadaGanadora(String[][] tablero, String letra) {
        // Filas y columnas
        for (int i = 0; i < 3; i++) {
            if (tablero[i][0].equals(letra) && tablero[i][1].equals(letra) && tablero[i][2].equals(letra)) return true;
            if (tablero[0][i].equals(letra) && tablero[1][i].equals(letra) && tablero[2][i].equals(letra)) return true;
        }
        // Diagonales
        if (tablero[0][0].equals(letra) && tablero[1][1].equals(letra) && tablero[2][2].equals(letra)) return true;
        if (tablero[0][2].equals(letra) && tablero[1][1].equals(letra) && tablero[2][0].equals(letra)) return true;

        return false;
    }
}
