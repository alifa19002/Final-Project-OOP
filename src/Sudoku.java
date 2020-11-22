import java.util.Random;    //untuk membuat random angka
import javax.swing.JPanel;  //untuk membuat panel
import javax.swing.JTextField;  //untuk membuat text field (bisa diisi user)

public class Sudoku {

    public static final int UK_GRID = Puzzle.UK_GRID;
    public static final int UK_SUBGRID = Puzzle.UK_SUBGRID;
    public static JPanel board= new JPanel();

    JTextField[][] tfDisplays = new JTextField[UK_GRID][UK_GRID];

	//Teka-teki yang harus dipecahkan dan mask (yang dapat digunakan untuk mengontrol
    //tingkat kesulitan)
	private int[][] puzzle = Puzzle.getPuzzle();

	private static boolean[][] masks = {
            //set angka yang akan dimask dan tidak, sementara masking secara manual
            //karena belum dilanjutkan ke masing2 level
            //untuk contoh, saat ini yang di masking ada 4x9 (default)
			{ true, true, false, true, true, false, false, true, true },
			{ false, false, true, true, false, false, true, true, false },
			{ true, true, false, false, true, true, false, true, true },
			{ false, true, true, false, false, false, true, true, false },
			{ false, false, true, true, false, false, false, true, true },
			{ true, true, false, true, true, false, true, true, false },
			{ true, true, false, true, false, true, true, false, false },
			{ true, true, false, false, true, true, false, true, true },
			{ false, false, true, true, false, false, true, true, false } };
            public void initGame(JPanel board) {

                for (int row = 0; row < UK_GRID; ++row) {
                    for (int col = 0; col < UK_GRID; ++col) {
                        tfDisplays[row][col] = new JTextField(); // Alokasikan elemen array
                        tfDisplays[row][col].setHorizontalAlignment(JTextField.CENTER); //supaya tulisan di tengah
                        board.add(tfDisplays[row][col]); // ContentPane menambahkan JTextField
                        if (masks[row][col]) {
                            tfDisplays[row][col].setText(""); //    setel ke string kosong
                            tfDisplays[row][col].setEditable(true); //
                          
                        } else {
                            tfDisplays[row][col].setText(puzzle[row][col] + "");    //nampilkan angka
                            tfDisplays[row][col].setEditable(false);    //tidak dapat diedit
                        }
                    }
                }
            }
}

    