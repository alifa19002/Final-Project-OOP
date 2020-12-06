import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.*;

@SuppressWarnings("serial")
public class Sudoku extends JFrame {

	public static final int UK_GRID = Puzzle.UK_GRID; // ukuran grid = 9
	public static final int UK_SUBGRID = Puzzle.UK_SUBGRID; // ukuran subgrid = 3

	public static final int CELL_SIZE = 50; // lebar atau tinggi cell (pixel)
	public static final int CANVAS_WIDTH = CELL_SIZE * UK_GRID;
	public static final int CANVAS_HEIGHT = CELL_SIZE * UK_GRID;

	public static final Color OPEN_CELL_BGCOLOR = Color.PINK;	// warna background untuk cell yang akan diisi user
	public static final Color OPEN_CELL_TEXT_YES = new Color(0, 255, 0); // warna font ketika jawaban user benar
	public static final Color OPEN_CELL_TEXT_NO = Color.RED; // warna font ketika jawaban user salah
	public static final Color CLOSED_CELL_BGCOLOR = new Color(240, 240, 240); // warna background untuk cell yang berisi angka clue
	public static final Color CLOSED_CELL_TEXT = Color.BLACK; // warna font angka clue
	public static final Font FONT_NUMBERS = new Font("Monospaced", Font.BOLD, 18);

	// deklarasi difficultyLevel (untuk set level) dan closedCellsNum (cell yang akan berisi angka clue)
	public static int difficultyLevel; 
	public static int closedCellsNum; // difficultyLevel * 9

	public static JPanel board;
	public static JPanel statusPanel;
	public static JLabel statusLabel;
	public static JMenuBar menu;

	JTextField[][] tfDisplays = new JTextField[UK_GRID][UK_GRID];

	// puzzle (teka-teki) yang harus diselesaikan dan set elemen yang tidak dimasking (sesuai difficulty level : easy, medium, hard)
	// pada bagian ini, valuenya masih false semua karena akan diset selanjutnya mengikuti difficulty level
	private int[][] puzzle = Puzzle.getPuzzle();
    private static boolean[][] restMasks = {    //temp restart
			{ false, false, false, false, false, false, false, false, false },
			{ false, false, false, false, false, false, false, false, false },
			{ false, false, false, false, false, false, false, false, false },
			{ false, false, false, false, false, false, false, false, false },
			{ false, false, false, false, false, false, false, false, false },
			{ false, false, false, false, false, false, false, false, false },
			{ false, false, false, false, false, false, false, false, false },
			{ false, false, false, false, false, false, false, false, false },
			{ false, false, false, false, false, false, false, false, false } };
	private static boolean[][] masks = {
			{ false, false, false, false, false, false, false, false, false },
			{ false, false, false, false, false, false, false, false, false },
			{ false, false, false, false, false, false, false, false, false },
			{ false, false, false, false, false, false, false, false, false },
			{ false, false, false, false, false, false, false, false, false },
			{ false, false, false, false, false, false, false, false, false },
			{ false, false, false, false, false, false, false, false, false },
			{ false, false, false, false, false, false, false, false, false },
			{ false, false, false, false, false, false, false, false, false } };


	public Sudoku() {

		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());

		// membuat game board
		board = new JPanel();
		board.setLayout(new GridLayout(UK_GRID, UK_GRID));
		board.setBorder(BorderFactory.createLineBorder(Color.black));
		cp.add(board, BorderLayout.CENTER);

		// membuat status panel
		statusPanel = new JPanel();
		statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		statusPanel.setPreferredSize(new Dimension(cp.getWidth(), 25));
		statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
		statusLabel = new JLabel("Cells remaining: " + closedCellsNum);
		statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
		statusPanel.add(statusLabel);
		cp.add(statusPanel, BorderLayout.SOUTH);
		
		// membuat menubar dan menu item
		menu = new JMenuBar();
		
		// membuat menu file yang berisi restart, reset, dan exit
		JMenu fileMenu = new JMenu("File");
	    	JMenuItem newGameItem = new JMenuItem("Restart");
	        newGameItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// memanggil method dispose untuk menutup window agar tidak muncul 2 window yang berbeda saat restart dijalankan
					dispose();
					// saat restart, maka difficulty levelnya kembali ke default, dimana akan menampilkan 4x9 tile/grid yang sudah berisi angka
					setDifficultyLevel(5);
                    new Sudoku();
				}
			});
			fileMenu.add(newGameItem);
			
	        JMenuItem resetGameItem = new JMenuItem("Reset game");
	        resetGameItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
                    InputListener listener = new InputListener();
                    for (int row = 0; row < UK_GRID; ++row) {
                        for (int col = 0; col < UK_GRID; ++col) {
                            if (restMasks[row][col]) {
                                tfDisplays[row][col].setText(""); // set ke string kosong
                                tfDisplays[row][col].setEditable(true);	// dapat diedit
                                tfDisplays[row][col].setBackground(OPEN_CELL_BGCOLOR);
                                tfDisplays[row][col].addActionListener(listener);
                            }else {
                                tfDisplays[row][col].setText(puzzle[row][col] + ""); // sudah berisi angka
                                tfDisplays[row][col].setEditable(false); // tidak dapat diedit
                                tfDisplays[row][col].setBackground(CLOSED_CELL_BGCOLOR);
                                tfDisplays[row][col].setForeground(CLOSED_CELL_TEXT);
                            }
                        }
                    }

					
				}
			});
			fileMenu.add(resetGameItem);
			
	        JMenuItem exitItem = new JMenuItem("Exit");
	        exitItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0); // keluar dari keseluruhan program Sudoku
				}
			});
	        fileMenu.add(exitItem);
	        menu.add(fileMenu);
			
			// membuat menu options yang berisi pilihan difficulty level
	        JMenu optionsMenu = new JMenu("Options");
	        JMenu difficultyMenu = new JMenu("Difficulty level");
	        optionsMenu.add(difficultyMenu);
	        DifficultyMenuListener dmlistener = new DifficultyMenuListener();
			JMenuItem level1Item = new JMenuItem("Easy");
	        level1Item.addActionListener(dmlistener);
	        difficultyMenu.add(level1Item);
	        JMenuItem level2Item = new JMenuItem("Medium");
	        level2Item.addActionListener(dmlistener);
	        difficultyMenu.add(level2Item);
	        JMenuItem level3Item = new JMenuItem("Hard");
	        level3Item.addActionListener(dmlistener);
	        difficultyMenu.add(level3Item);
	        menu.add(optionsMenu);
	        
	        setJMenuBar(menu);
	
	    // inisialisasi game
		initGame(board);
       
		// set ukuran content-pane dan kemas semua komponen dibawah container ini
		cp.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
		pack();

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Sudoku");
		setVisible(true);
	}

	private void initGame(JPanel board) {
		// instansiasi objek listener dari class InputListener
		InputListener listener = new InputListener();
        
		for (int row = 0; row < UK_GRID; ++row) {
			for (int col = 0; col < UK_GRID; ++col) {
				tfDisplays[row][col] = new JTextField(); 

				tfDisplays[row][col].setHorizontalAlignment(JTextField.CENTER);
				tfDisplays[row][col].setFont(FONT_NUMBERS);
				board.add(tfDisplays[row][col]); 
				if (masks[row][col]) {
					tfDisplays[row][col].setText(""); // set menjadi string kosong
					tfDisplays[row][col].setEditable(true);	// dapat diedit
					tfDisplays[row][col].setBackground(OPEN_CELL_BGCOLOR);
					tfDisplays[row][col].addActionListener(listener);
				} else {
					tfDisplays[row][col].setText(puzzle[row][col] + ""); // sudah berisi angka
					tfDisplays[row][col].setEditable(false); // tidak dapat diedit
					tfDisplays[row][col].setBackground(CLOSED_CELL_BGCOLOR);
					tfDisplays[row][col].setForeground(CLOSED_CELL_TEXT);
				}
			}
		}
	}

	// pengondisian mask angka sesuai difficulty level
	public static void setDifficultyLevel(int difficultyLevel) {
		for (int row = 0; row < UK_GRID; ++row) {
			for (int col = 0; col < UK_GRID; ++col){
				masks[row][col] = false;
				restMasks[row][col] = false;
			}
		}
		closedCellsNum = difficultyLevel * 9;
		// untuk level easy, difficulty levelnya 5. Sementara, level medium 7 dan level hard 8
		// easy -> closedCellsNum = 5*9 = 45 tile yang harus diisi user
        Random random = new Random();
		int randomRow = 0;
		int randomCol = 0;
		// menutup cell secara random sesuai dengan difficulty level
		for (int i = 0; i < closedCellsNum; i++) {
			randomRow = random.nextInt(UK_GRID);
			randomCol = random.nextInt(UK_GRID);
			if (!masks[randomRow][randomCol]) {
                masks[randomRow][randomCol] = true;
                restMasks[randomRow][randomCol] = true;
			} else {
				i--;
			}
        }   
	}
	private class InputListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int rowSelected = -1;
			int colSelected = -1;

			// mengambil objek sumber yang menyebabkan event tsb
			JTextField source = (JTextField) e.getSource();
			// Pindai semua baris dan kolom, dan cocokkan dengan objek sumber
			boolean found = false;
			for (int row = 0; row < UK_GRID && !found; row++) {
				for (int col = 0; col < UK_GRID && !found; col++) {
					if (tfDisplays[row][col] == source) {
						rowSelected = row;
						colSelected = col;
						found = true; // break inner/outer loops
					}
				}
			}

			// mengambil input dalam bentuk string dan konversi ke integer
			int input = -1;
			try {
				input = Integer.parseInt(source.getText());
			} catch (NumberFormatException ex) {
				// jika input user selain angka
				JOptionPane.showMessageDialog(null, "Enter number from 1 to 9");
			}
			// membandingkan antara angka yang diinputkan dan angka yang ada di puzzle
			// jika benar, maka angka yang diinputkan akan berwarna hijau
			// namun jika tidak, maka warna angka menjadi merah 
			int subgridFirstRowNum = rowSelected / UK_SUBGRID * UK_SUBGRID; // 0, 3 atau 6
            int subgridFirstColNum = colSelected / UK_SUBGRID * UK_SUBGRID; // 0, 3 atau 6
        
			if (input == puzzle[rowSelected][colSelected]) {
				tfDisplays[rowSelected][colSelected] .setForeground(OPEN_CELL_TEXT_YES);
				tfDisplays[rowSelected][colSelected].setEditable(false);
				masks[rowSelected][colSelected] = false;
				for (int row = subgridFirstRowNum; row < subgridFirstRowNum + UK_SUBGRID; row++) {
					for (int col = subgridFirstColNum; col < subgridFirstColNum + UK_SUBGRID; col++) {
						if (!masks[row][col]) {
							tfDisplays[row][col] .setBackground(CLOSED_CELL_BGCOLOR);
						}
					}
                }
                
				// mengurangi jumlah cells remaining (cell yang belum terisi dengan benar)
				statusLabel.setText("Cells remaining: " + --closedCellsNum);
			} else {
				tfDisplays[rowSelected][colSelected]
						.setForeground(OPEN_CELL_TEXT_NO);
				// mengubah background angka pada 1 subgrid yang mempunyai value yang sama dengan angka yang diinputkan user
				for (int row = subgridFirstRowNum; row < subgridFirstRowNum
						+ UK_SUBGRID; row++) {
					for (int col = subgridFirstColNum; col < subgridFirstColNum
							+ UK_SUBGRID; col++) {
						if (puzzle[row][col] == input && !masks[row][col]) {
							tfDisplays[row][col]
									.setBackground(OPEN_CELL_TEXT_NO);
						}
					}
				}
			}
			
			// cek apakah permainan sudah berhasil diselesaikan
			boolean win = true;
			for (int row = 0; row < UK_GRID && win; row++) {
				for (int col = 0; col < UK_GRID && win; col++) {
					win = !masks[row][col];
				}
			}

			// setelah semua tile berhasil diisi dengan benar dan permainan dianggap selesai
			// maka, akan ditampilkan dialog window Congratulations
			if (win) {
				JOptionPane.showMessageDialog(null, "Congratulations, you finished all tiles!");
			}
		}

	}

	// difficulty listener untuk masing-masing level easy, medium, dan hard
	private class DifficultyMenuListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "Easy":
				// ketika level easy dipilih, maka difficultyLevelnya 5
				// panggil method dispose untuk menutup window game sudoku yang sebelumnya
				setDifficultyLevel(5);
				dispose();
				new Sudoku();
				break;
			case "Medium":
				// ketika level medium dipilih, maka difficultyLevelnya 7
				// panggil method dispose untuk menutup window game sudoku yang sebelumnya
				setDifficultyLevel(7);
				dispose();
				new Sudoku();
				break;
			case "Hard":
				// ketika level hard dipilih, maka difficultyLevelnya 8
				// panggil method dispose untuk menutup window game sudoku yang sebelumnya
				setDifficultyLevel(8);
				dispose();
				new Sudoku();
				break;
			default:
				setDifficultyLevel(5);
				dispose();
				new Sudoku();
				break;
			}
		}
	}

	public static void main(String[] args) {
		// command untuk menjalankan file Sudoku.java ini adalah java Sudoku [Difficulty]
		// contoh : java Sudoku hard
		
		// Pemanfaataan argument String[] args untuk difficulty level
		// jika panjang args kurang dari 1 (tidak menyebutkan level)
		if(args.length < 1) {
			System.out.println("Error: Not enough arguments. Please add specific level");
			System.out.println("use command 'java Sudoku easy' to play Sudoku game in EASY Level");
			System.out.println("use command 'java Sudoku medium' to play Sudoku game in MEDIUM Level");
			System.out.println("use command 'java Sudoku hard' to play Sudoku game in HARD Level");
			return;
		}
		// jika args dengan index 0 mengandung kata easy
		if(args[0].contains("easy")) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
				    setDifficultyLevel(5);
					new Sudoku();
				}
			});
		}
		// jika args dengan index 0 mengandung kata medium
		else if(args[0].contains("medium")) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
				    setDifficultyLevel(7);
					new Sudoku();
				}
			});
		}
		// jika args dengan index 0 mengandung kata hard
		else if(args[0].contains("hard")) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
				    setDifficultyLevel(8);
					new Sudoku();
				}
			});
			}
	}
}