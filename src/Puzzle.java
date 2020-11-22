// import class untuk mengacak angka dalam tile Sudoku
import java.util.Random;

public class Puzzle {
    // deklarasi jumlah grid
    public static final int UK_GRID = 9;    // ukuran grid 9x9
    public static final int UK_SUBGRID = 3; // ukuran subgrid 3x3

    public static Random random = new Random();     // instansiasi objek dari class Random

    // inisialisasi elemen dari setiap tile
    // array dg index puzzle 0-8
    private static int[][] puzzle = {
        { 5, 3, 4, 6, 7, 8, 9, 1, 2 },
        { 6, 7, 2, 1, 9, 5, 3, 4, 8 },
        { 1, 9, 8, 3, 4, 2, 5, 6, 7 },
        
        { 8, 5, 9, 7, 6, 1, 4, 2, 3 },
        { 4, 2, 6, 8, 5, 3, 7, 9, 1 },
        { 7, 1, 3, 9, 2, 4, 8, 5, 6 },
        
        { 9, 6, 1, 5, 3, 7, 2, 8, 4 },
        { 2, 8, 7, 4, 1, 9, 6, 3, 5 },
        { 3, 4, 5, 2, 8, 6, 1, 7, 9 } 
    };

    private static int[][] generatePuzzle() {
        int[] tempRow;
        for(int i=0; i<10; i++) {
            // acak nomor baris 1-9
            // utk i=0. misal dirandom dan didapat rowNum = 1
            // utk i=1. misal dirandom dan didapat rowNum = 2
            int rowNum = (random.nextInt(UK_GRID));
            // utk i=0. newRow = 1/9*3 = 0
            // utk i=1. newRow = 2/9*3 = 0
            int newRow = (rowNum/UK_GRID) * UK_SUBGRID;
            // tukar baris
            // utk i=0. swap baris index 1 dan 0
            // utk i=1. swap baris index 2 dan 0 (yg tdnya baris 1)
            tempRow = puzzle[rowNum];
            puzzle[rowNum] = puzzle[newRow];
            puzzle[newRow] = tempRow;
        }
        return puzzle;
    }

    public static int[][] getPuzzle() {
        return generatePuzzle();
    }

    private static int[][] generateBlankPuzzle() {
        for(int i=0; i<UK_SUBGRID*UK_SUBGRID; i++){
            for(int j=0; j<UK_SUBGRID*UK_SUBGRID; j++) {
                puzzle[i][j] = (i*UK_SUBGRID + i/UK_SUBGRID + j) % (UK_SUBGRID*UK_SUBGRID) + 1;
            }
        }
        return puzzle;
    }

}