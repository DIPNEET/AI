import java.util.*;

class Solution {
    
    int size;
    List<Integer> rows[];
    List<Integer> cols[];
    List<Integer> blocks[];
    
    public void solveSudoku(int board[][]){
        size = board.length;
        
        rows = new ArrayList[size];
        cols = new ArrayList[size];
        blocks = new ArrayList[size];
        
        // block size = m*m
        int m = (int)Math.sqrt(size);
        
        for(int i=0; i<size; i++){
            rows[i] = new ArrayList<Integer>();
            cols[i] = new ArrayList<>();
            blocks[i] = new ArrayList<>();
        }
        
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                if(board[i][j] != 0){
                    rows[i].add(board[i][j]);
                    cols[j].add(board[i][j]);
                    blocks[(i/m)*m+j/m].add(board[i][j]);
                }
            }
        }
        
        
        // System.out.println(Arrays.toString(rows));
        // System.out.println(Arrays.toString(cols));
        // System.out.println(Arrays.toString(blocks));
        
        sudokuUtil(board,0,0);
        
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
        
    }
    
    boolean sudokuUtil(int board[][], int i, int j){
        // System.out.print("util("+i+","+j+")");
        if( i == size) return true;
        if(board[i][j] != 0){
            if(j+1 == size)
            return sudokuUtil(board,i+1,0);
            else 
            return sudokuUtil(board,i,j+1);
        }
        else{
            
            boolean flag = false;
            int m = (int)Math.sqrt(size);
            int b = (i/m)*m + j/m;

            for(int val=1; val<=size && !flag; val++){
            
                if(rows[i].contains(val) || cols[j].contains(val) || blocks[b].contains(val)) continue;
                
                board[i][j] = val;
                
                rows[i].add(val);
                cols[j].add(val);
                blocks[b].add(val);
                
                if(j+1 == size)
                    flag = flag || sudokuUtil(board,i+1,0);
                else 
                    flag = flag || sudokuUtil(board,i,j+1);
                
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

public class SudokuSolver{
    public static void main(String args[]){
        
        //custom input
//         Scanner s = new Scanner(System.in);
//         int size;
        
//         System.out.println("Enter board size(n*n) : value of n ");
//         size = s.nextInt();
        
//         int board[][] = new int[size][size];
    
//         for(int i=0; i<size; i++){
//             for( int j=0; j<size; j++){
//                 board[i][j] = s.nextInt();
//             }
//         }
        
        
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
        
        Solution sol = new Solution();
        sol.solveSudoku(board);
        
        
        
    }
}
