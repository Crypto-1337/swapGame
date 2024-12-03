import java.util.Scanner;


public class Main {

	static int[][] puzzle;	// Spielfeld
	static int size, x, y;	// Groesse des Spielfeldes (size x size), x,y-Koordinaten der freien Stelle
	
	public static int getSize() {
		return size;
	}
	public static void setSize(int size) {
		Puzzle.size = size;
	}

    public static void main(String[] args) {
		createPuzzle(3);
        
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

    public static int getFieldIndex(int [][] board, int number){
        int [] index = new int [2];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == number){
                    index[0] = i;
                    index[1] = j;
                }
            }
        }
        return index;
    }

    public static int [] getEmptyFieldIndex(int [][] board){
        int [] index = new int [2];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0){
                    index[0] = i;
                    index[1] = j;
                }
            }
        }
        return index;
    }
}