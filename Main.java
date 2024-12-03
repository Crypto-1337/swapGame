import java.util.Scanner;


public class Main {

	static int[][] puzzle;	// Spielfeld
	static int size, x, y;	// Groesse des Spielfeldes (size x size), x,y-Koordinaten der freien Stelle
	
	public static int getSize() {
		return size;
	}
	public static void setSize(int size) {
		Main.size = size;
	}

    public static void main(String[] args) {
		createPuzzle(3);
		
		mixPuzzle(60);
        
		System.out.println("OK, es kann losgehen!");

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


    public static void swapFields(int [][] board, int number){

    }

    public static boolean isGameOver(int [][] board){
        for(int i = 0; i < board.length; i++){
			for(int j=0; j < board[i].length; j++){
				if (board[i][j] == board.length + 1) continue;
                else return false;
			}
		}
		return true;
    }
}