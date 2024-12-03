package swapGame;

import java.util.Random;

/**
 * Die PuzzleBoard-Klasse repräsentiert das Spielfeld und enthält alle Logiken für das Spielbrett.
 */
public class PuzzleBoard {
    private int[][] board; // Spielfeld
    private int size;      // Größe des Spielfelds
    private int emptyX, emptyY; // Position des leeren Felds

    public PuzzleBoard(int size) {
        this.size = size;
        createBoard();
    }

    // Erstellt ein Spielfeld mit aufsteigenden Zahlen und einem leeren Feld
    private void createBoard() {
        board = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[j][i] = 1 + j * size + i;
            }
        }
        emptyX = size - 1;
        emptyY = size - 1;
        board[emptyX][emptyY] = 0; // Das leere Feld
    }

    // Mischt das Spielfeld durch zufällige Züge
    public void mixBoard(int moves) {
        Random random = new Random();
        for (int i = 0; i < moves; i++) {
            int direction = random.nextInt(4);
            switch (direction) {
                case 0 -> moveEmpty(-1, 0); // oben
                case 1 -> moveEmpty(1, 0);  // unten
                case 2 -> moveEmpty(0, -1); // links
                case 3 -> moveEmpty(0, 1);  // rechts
            }
        }
    }

    // Versucht, die angegebene Zahl zu verschieben
    public boolean move(int number) {
        int[] position = findNumber(number);
        if (position == null) {
            System.out.println("Ungültige Zahl.");
            return false;
        }
        int dx = position[1] - emptyX;
        int dy = position[0] - emptyY;
        if (Math.abs(dx) + Math.abs(dy) == 1) { // Nur direkte Nachbarn dürfen bewegt werden
            board[emptyY][emptyX] = number;
            board[position[0]][position[1]] = 0;
            emptyX = position[1];
            emptyY = position[0];
            return true;
        } else {
            System.out.println("Ungültiger Zug.");
            return false;
        }
    }

    // Überprüft, ob das Puzzle gelöst ist
    public boolean isSolved() {
        int count = 1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == size - 1 && j == size - 1) return true; // Letztes Feld leer
                if (board[i][j] != count++) return false;
            }
        }
        return true;
    }

    // Gibt das Spielfeld auf der Konsole aus
    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.printf("| %2s ", board[i][j] == 0 ? " " : board[i][j]);
            }
            System.out.println("|");
        }
        System.out.println();
    }

    // Findet die Position einer Zahl auf dem Spielfeld
    private int[] findNumber(int number) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == number) return new int[]{i, j};
            }
        }
        return null;
    }

    // Verschiebt das leere Feld (interne Methode)
    private void moveEmpty(int dx, int dy) {
        int newX = emptyX + dx;
        int newY = emptyY + dy;
        if (newX >= 0 && newX < size && newY >= 0 && newY < size) {
            board[emptyY][emptyX] = board[newY][newX];
            board[newY][newX] = 0;
            emptyX = newX;
            emptyY = newY;
        }
    }
}

