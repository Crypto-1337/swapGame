import java.util.Scanner;
import java.util.Arrays;

public class Main {
	static int[][] puzzle;	// Spielfeld
	static int[][] puzzleSolved;
	static int size, x, y, xFilled, yFilled;	// Groesse des Spielfeldes (size x size), x,y-Koordinaten der freien Stelle, xFilled,yFilled-Koordinaten der eingegebenen Zahl
 	
	public static int getSize() {
		return size;
	}
	public static void setSize(int size) {
		Main.size = size;
	}

    public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int number; //die zu tauschende Zahl
		
		int moves = 0; //Spielzüge
		
		createPuzzle(3); //das zweidimensionale Array sortiert erstellen
		
		puzzleSolved = puzzle;
        
		mixPuzzle(60); //Array mit n zügen mischen
        
		System.out.println("OK, es kann losgehen!");

		//solange isGameOver != true wird der Spieler aufgefordert Zahlen einzugeben 
		//und das Puzzel wird auf der Konsole ausgegeben
		while(!isGameOver()){
			printPuzzle();
			
			//Eingabe des Spielers
			System.out.print("Bitte geben sie die zu tauschende Zahl ein: ");
			number = scan.nextInt();
			System.out.println("");
			
			//Methode um die einegegebene Zahl mit dem leeren Feld zu tauschen
			swapFields(number);
			
			moves++;
			
			System.out.print("Zuege: ");
			System.out.print(moves);
			System.out.println("");
		}
		printPuzzle();
		System.out.print("Du hast so viele Zuege gebraucht: ");
		System.out.print(moves);
    }

	public static void createPuzzle(int size) {
		setSize(size);
		puzzle = new int[size][size]; //neues zweidimensionales Array mit der angegebenen grösse erstellen
		for(int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				//das Array wird aufsteigend mit den Zahlen 1-8 befüllt
				puzzle[j][i] = 1 + j*size + i;
			}
		}
		//der Index des leeren Feldes wird in x und y gespeichert
		x = size-1;
		y = size-1;
		puzzle[x][y] = 0;
	}
    
	public static void mixPuzzle(int n) {
		int tmp;
		
		//bis n erreicht wird, wird eine random Zahl von 0-3 ermittelt und das puzzel wird dem Fall entsprechend nach oben, unten, links oder rechts "verschoben"
		for(int i = 0; i <= n; i++){
			switch((int)Math.floor(Math.random()*4)){
				//nach oben
				case 0:{
					if(y < 1) break;
					tmp = puzzle[y][x];
					puzzle[y][x] = puzzle[y-1][x];
					puzzle[y-1][x] = tmp;
					y--;
					break;

				}
				//nach links
				case 1:{
					if(x < 1) break;
					tmp = puzzle[y][x];
					puzzle[y][x] = puzzle[y][x-1];
					puzzle[y][x-1] = tmp;
					x--;
					break;
				}
				//nach unten
				case 2:{
					if(y > 1) break;
					tmp = puzzle[y][x];
					puzzle[y][x] = puzzle[y+1][x];
					puzzle[y+1][x] = tmp;
					y++;
					break;
				}
				//nach rechts
				case 3:{
					if(x > 1) break;
					tmp = puzzle[y][x];
					puzzle[y][x] = puzzle[y][x+1];
					puzzle[y][x+1] = tmp;
					x++;
					break;
				}
			}
		}
	}
	
	public static void printPuzzle() {
		if(puzzle == null){
			System.out.println("Spielfeld kann nicht ausgegeben werden, es wurde noch nicht erstellt!");
			return;
		}
		String str;
		for(int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				str = puzzle[i][j]+" ";
				if(puzzle[i][j] < 10) str = "| "+ str;
				if(puzzle[i][j] == 0) str = "| " + "  ";
				System.out.print(str);
			}
			System.out.println("| ");
		}
		System.out.println();
	}


    public static void swapFields(int number){
		int tmp;
		getFieldIndex(number); //die Position der eingegebenen number wird ermittelt
		//wenn die Zahl über dem leeren Feld steht
		if (y > yFilled && x == xFilled){
			tmp = puzzle[y][x];
			puzzle[y][x] = puzzle[y-1][x];
			puzzle[y-1][x] = tmp;
			y--;
		}

		//wenn die Zahl rechts von dem leeren Feld steht
		else if (x < xFilled && y == yFilled){
			tmp = puzzle[y][x];
			puzzle[y][x] = puzzle[y][x+1];
			puzzle[y][x+1] = tmp;
			x++;
		}

		//wenn die Zahl unter dem leeren
		else if (y < yFilled && x == xFilled){
			tmp = puzzle[y][x];
			puzzle[y][x] = puzzle[y+1][x];
			puzzle[y+1][x] = tmp;
			y++;
		}	
		//wenn die Zahl links von dem leeren Feld steht
		else if (x > xFilled && y == yFilled){
			tmp = puzzle[y][x];
			puzzle[y][x] = puzzle[y][x-1];
			puzzle[y][x-1] = tmp;
			x--;
		}

		else{
			System.out.println("Das war ein ungültiger Spielzug");
		}
    }

	public static boolean isGameOver(){
		//ueberpruefung ob das Array wieder aufsteigend sortiert ist, false wenn der wert nicht i+1 entspricht
		for(int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				if(puzzle[j][i] == 0) continue;
				if(puzzle[j][i] != 1 + j*size + i) return false;
			}
		}
		System.out.println("Sie haben gewonnen.");
		return true;
	}

	public static void getFieldIndex(int number){
		//wenn die uebergebene number dem wert an puzzle[i][j] entspricht, wird der index in xFilled und yFilled gespeichert
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < puzzle[i].length; j++) {
				if (puzzle[i][j] == number){
					yFilled = i;
					xFilled = j;
				}
			}
		}
	}
}