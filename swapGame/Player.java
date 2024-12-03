package swapGame;

import java.util.Scanner;

/**
 * Die Player-Klasse verwaltet die Benutzereingaben.
 */
public class Player {
    private Scanner scanner;

    public Player() {
        scanner = new Scanner(System.in);
    }

    // Fragt die nächste Zahl ab, die der Spieler bewegen möchte
    public int getMove() {
        System.out.print("Bitte geben Sie eine Zahl ein: ");
        return scanner.nextInt();
    }

    // Fragt, ob der Spieler erneut spielen möchte
    public boolean playAgain() {
        System.out.print("Möchten Sie nochmal spielen? (y/n): ");
        char response = scanner.next().toLowerCase().charAt(0);
        return response == 'y';
    }
}

