import java.util.Scanner;


public class Main {

	static int[][] puzzle;	// Spielfeld
	static int size, x, y;	// Groesse des Spielfeldes (size x size), x,y-Koordinaten der freien Stelle
	static boolean success = false;
 	
	public static int getSize() {
		return size;
	}
	public static void setSize(int size) {
		Main.size = size;
	}

    public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		char move;
		
		createPuzzle(3);
        
		mixPuzzle(60);
        
		System.out.println("OK, es kann losgehen!");

		while(!success){
			printPuzzle();
			
			System.out.println("Bitte geben sie den naechsten Move an(w,a,s,d): ");
			
			move = scan.next().charAt(0);
			
			swapFields(move);
		}
		printPuzzle();
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
				if(puzzle[i][j] < 10) str = " "+str;
				System.out.print(str);
			}
			System.out.println();
		}
		System.out.println();
	}


    public static void swapFields(char move){
		int tmp;
		switch (move){
			case 'w':
				if (y < 1)
					break;
				tmp = puzzle[y][x];
				puzzle[y][x] = puzzle[y-1][x];
				puzzle[y-1][x] = tmp;
				y--;
				break;

			case 'd':
				if (x > 1)
					break;
				tmp = puzzle[y][x];
				puzzle[y][x] = puzzle[y][x+1];
				puzzle[y][x+1] = tmp;
				x++;
				break;
			
			case 's':
				if (y > 1) 
					break;
				tmp = puzzle[y][x];
				puzzle[y][x] = puzzle[y+1][x];
				puzzle[y+1][x] = tmp;
				y++;
				break;

			case 'a':
				if (x < 1)
					break;
				tmp = puzzle[y][x];
				puzzle[y][x] = puzzle[y][x-1];
				puzzle[y][x-1] = tmp;
				x--;
				break;

			default:
				break;
		}
    }

    public static void isGameOver(int [][] board){
        for(int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				if(puzzle[j][i] == 0) 
					continue;
				if(puzzle[j][i] != 1 + j*size + i) 
					success = false;;
			}
		}
		System.out.println("Sie haben gewonnen");
		success = true;;
    }
}