import java.util.*;

class Sudoku{
    // Instance variables of Sudoku class
    int size;
    List<Integer>[] rows;
    List<Integer>[] cols;
    List<Integer>[] blocks;
    
    public void solveSudoku(int[][] board){
        size = board.length;
        
        rows = new ArrayList[size];
        cols = new ArrayList[size];
        blocks = new ArrayList[size];
        
        int m = (int)Math.sqrt(size); // to extract the block size
        
        for(int i=0; i<size; i++){ 
            rows[i] = new ArrayList<>();
            cols[i] = new ArrayList<>();
            blocks[i] = new ArrayList<>();
        }
        
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                if(board[i][j] != 0){
                    // storing the values present already in the board
                    rows[i].add(board[i][j]);// storing the numbers which are present initially in the sudoku for a particular row
                    cols[j].add(board[i][j]);// storing the numbers which are present initially in the sudoku for a particular column 
                    blocks[(i/m)*m+j/m].add(board[i][j]);// storing the numbers which are present initially in the sudoku for a particular block
                }
            }
        }
         
        sudokuUtil(board,0,0);

        // Printing the sudoku board
        System.out.println("The solved board is:");
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
        
    }
    
    // Recursive function to fill the sudoku
    boolean sudokuUtil(int[][] board, int i, int j){
        
        if( i == size) return true; // Base case for recursion
        if(board[i][j] != 0){ // for the locations of sudoku which are already filled
            if(j+1 == size){ // if all the columns for a particular row are traversed
            return sudokuUtil(board,i+1,0);}// move to next row
            else { // if all the columns for a particular row are not traversed
            return sudokuUtil(board,i,j+1);}// move to next column
        }
        else{ // for the locations of sudoku which are not already filled
            
            boolean flag = false;
            int m = (int)Math.sqrt(size); // to extract the block size
            int b = (i/m)*m + j/m;

            for(int val=1; val<=size && !flag; val++){

                // if the number is already present in the row or column or block
                if(rows[i].contains(val) || cols[j].contains(val) || blocks[b].contains(val)) continue;
                
                board[i][j] = val;// store the number
                
                // make updations in row, col and block
                rows[i].add(val);
                cols[j].add(val);
                blocks[b].add(val);
                
                if(j+1 == size) // if the entire row is traversed
                    flag = flag || sudokuUtil(board,i+1,0);
                else // moving to the next column 
                    flag = flag || sudokuUtil(board,i,j+1);
                
                // backtracking
                rows[i].remove((Integer)val);
                cols[j].remove((Integer)val);
                blocks[b].remove((Integer)val);
                
                if(!flag)
                board[i][j] = 0;

            }
            
            return flag;
        }
    }
}
class SudokuSolver{
    public static void main(String args[]){
        
    int board[][] = {
    {5,3,0,0,7,0,0,0,0},
    {6,0,0,1,9,5,0,0,0},
    {0,9,8,0,0,0,0,6,0},
    {8,0,0,0,6,0,0,0,3},
    {4,0,0,8,0,3,0,0,1},
    {7,0,0,0,2,0,0,0,6},
    {0,6,0,0,0,0,2,8,0},
    {0,0,0,4,1,9,0,0,5},
    {0,0,0,0,8,0,0,7,9}
};

       /*int[][] board = {
            {0,4,0,0,8,0,16,0,11,0,1,0,0,6,0,14},
            {3,0,0,9,0,15,0,4,10,7,0,13,0,0,12,0},
            {0,6,0,0,2,11,0,0,8,0,0,0,3,0,0,0},
            {15,0,0,0,0,0,0,13,0,0,14,0,0,2,0,4},
            {0,0,0,8,0,3,0,0,7,0,0,0,5,0,0,0},
            {0,0,0,0,0,0,14,0,0,0,0,10,0,16,0,13},
            {0,0,9,2,5,0,0,0,0,8,0,0,11,0,0,0},
            {12,0,1,0,0,0,0,16,0,0,13,0,0,0,3,0},
            {0,0,0,12,15,0,0,0,0,6,0,0,13,0,4,7},
            {16,1,13,0,10,0,0,3,0,0,7,0,0,9,0,0},
            {6,0,0,0,0,0,11,0,4,0,0,9,0,0,2,10},
            {0,3,0,0,0,16,0,0,0,14,8,0,0,11,0,0},
            {5,11,3,10,0,0,0,8,0,0,12,4,0,0,0,1},
            {0,14,0,0,11,9,0,0,0,13,15,0,4,0,5,0},
            {13,0,4,0,6,0,0,0,0,0,0,2,0,12,0,9},
            {0,12,0,16,0,13,0,0,0,1,0,0,6,0,15,0}
        };*/
        
        Sudoku sol = new Sudoku();
        sol.solveSudoku(board);
        
        
        
    }
}
