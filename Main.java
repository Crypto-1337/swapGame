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
		int move;
		
		int moves = 0;
		
		createPuzzle(3);
		
		puzzleSolved = puzzle;
        
		mixPuzzle(60);
        
		System.out.println("OK, es kann losgehen!");

		while(!isGameOver()){
			printPuzzle();
			
			System.out.print("Bitte geben sie die zu tauschende Zahl ein: ");
			move = scan.nextInt();
			System.out.println("");
						
			swapFields(move);
			
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
		puzzle = new int[size][size];
		for(int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				puzzle[j][i] = 1 + j*size + i;
			}
		}
		x = size-1;
		y = size-1;
		puzzle[x][y] = 0;
	}
    
	public static void mixPuzzle(int n) {
		int tmp;
		
		for(int i = 0; i <= n; i++){
			switch((int)Math.floor(Math.random()*4)){
				case 0:{
					if(y < 1) break;
					tmp = puzzle[y][x];
					puzzle[y][x] = puzzle[y-1][x];
					puzzle[y-1][x] = tmp;
					y--;
					break;

				}
				case 1:{
					if(x < 1) break;
					tmp = puzzle[y][x];
					puzzle[y][x] = puzzle[y][x-1];
					puzzle[y][x-1] = tmp;
					x--;
					break;
				}
				case 2:{
					if(y > 1) break;
					tmp = puzzle[y][x];
					puzzle[y][x] = puzzle[y+1][x];
					puzzle[y+1][x] = tmp;
					y++;
					break;
				}
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
		getFieldIndex(number);
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