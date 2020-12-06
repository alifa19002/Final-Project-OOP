import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class InputListener implements ActionListener {
	private static final Color OPEN_CELL_TEXT_YES = new Color(0, 255, 0); // warna font ketika jawaban user benar
	private static final Color OPEN_CELL_TEXT_NO = Color.RED; // warna font ketika jawaban user salah
	private static final Color CLOSED_CELL_BGCOLOR = new Color(240, 240, 240); // warna background untuk cell yang berisi angka clue
    
    @Override
    public void actionPerformed(ActionEvent e) {
        int rowSelected = -1;
        int colSelected = -1;

        // mengambil objek sumber yang menyebabkan event tsb
        JTextField source = (JTextField) e.getSource();
        // Pindai semua baris dan kolom, dan cocokkan dengan objek sumber
        boolean found = false;
        for (int row = 0; row < Sudoku.UK_GRID && !found; row++) {
            for (int col = 0; col < Sudoku.UK_GRID && !found; col++) {
                if (Sudoku.tfDisplays[row][col] == source) {
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
        // membandingkan antara angka yang diinputkan dan angka yang ada di Sudoku.puzzle
        // jika benar, maka angka yang diinputkan akan berwarna hijau
        // namun jika tidak, maka warna angka menjadi merah 
        int subgridFirstRowNum = rowSelected / Sudoku.UK_SUBGRID * Sudoku.UK_SUBGRID; // 0, 3 atau 6
        int subgridFirstColNum = colSelected / Sudoku.UK_SUBGRID * Sudoku.UK_SUBGRID; // 0, 3 atau 6
    
        if (input == Sudoku.puzzle[rowSelected][colSelected]) {
            Sudoku.tfDisplays[rowSelected][colSelected] .setForeground(OPEN_CELL_TEXT_YES);
            Sudoku.tfDisplays[rowSelected][colSelected].setEditable(false);
            Sudoku.masks[rowSelected][colSelected] = false;
            for (int row = subgridFirstRowNum; row < subgridFirstRowNum + Sudoku.UK_SUBGRID; row++) {
                for (int col = subgridFirstColNum; col < subgridFirstColNum + Sudoku.UK_SUBGRID; col++) {
                    if (!Sudoku.masks[row][col]) {
                        Sudoku.tfDisplays[row][col] .setBackground(CLOSED_CELL_BGCOLOR);
                    }
                }
            }
            
            // mengurangi jumlah cells remaining (cell yang belum terisi dengan benar)
            Sudoku.statusLabel.setText("Cells remaining: " + --Sudoku.closedCellsNum);
        } else {
            Sudoku.tfDisplays[rowSelected][colSelected]
                    .setForeground(OPEN_CELL_TEXT_NO);
            // mengubah background angka pada 1 subgrid yang mempunyai value yang sama dengan angka yang diinputkan user
            for (int row = subgridFirstRowNum; row < subgridFirstRowNum
                    + Sudoku.UK_SUBGRID; row++) {
                for (int col = subgridFirstColNum; col < subgridFirstColNum
                        + Sudoku.UK_SUBGRID; col++) {
                    if (Sudoku.puzzle[row][col] == input && !Sudoku.masks[row][col]) {
                        Sudoku.tfDisplays[row][col]
                                .setBackground(OPEN_CELL_TEXT_NO);
                    }
                }
            }
        }
        
        // cek apakah permainan sudah berhasil diselesaikan
        boolean win = true;
        for (int row = 0; row < Sudoku.UK_GRID && win; row++) {
            for (int col = 0; col < Sudoku.UK_GRID && win; col++) {
                win = !Sudoku.masks[row][col];
            }
        }

        // setelah semua tile berhasil diisi dengan benar dan permainan dianggap selesai
        // maka, akan ditampilkan dialog window Congratulations
        if (win) {
            JOptionPane.showMessageDialog(null, "Congratulations, you finished all tiles!");
        }
    }

}