import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.lang.*;

public class GUI extends JFrame{
	
	JButton submit = new JButton("Submit");		//inisiasi button submit
	private static JTextField[][] index = new JTextField[9][9];	//array texfield
	public static JMenuBar menu;
	public GUI (){
		JFrame frame = new JFrame("Sudoku");	//buat jframe sudoku
		frame.setSize(500,500);	//set size frame
		JPanel board = new JPanel();		//set panel untuk board sudoku
        board.setLayout(new GridLayout(3, 3));	//board mempunyai grid 3x3
        board.setBorder(BorderFactory.createLineBorder(Color.black));	//memberikan border hitam pada board
        for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
        JPanel subgrid = new JPanel();	//membuat panel subgrid
            subgrid.setLayout(new GridLayout(3, 3));	//set panel subgrid  3x3 tiap grid
            for (int i2 = 0; i2 < 3; i2++) {
            for (int j2 = 0; j2 < 3; j2++) {
                index[i][j]=new JTextField(1);	//memberikan text field untuk meletakkan angka
                subgrid.add(index[i][j]);
            }
            }
            subgrid.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
            subgrid.setBorder(BorderFactory.createLineBorder(Color.black));	//memberikan border hitam pada tiap subgrid
            board.add(subgrid);	//memasukkan subgrid pada grid
            }
			}
			
			menu = new JMenuBar();	//inisiasi menu bar
			JMenu easy = new JMenu("Easy");	//inisiasi menu
			menu.add(easy);
			JMenu medium = new JMenu("Medium");
			menu.add(medium);
			JMenu hard = new JMenu("Hard");
			menu.add(hard);
			setJMenuBar(menu);
		frame.getContentPane().add(board);	//menampilkan board sudoku
		frame.getContentPane().add(menu, "North");	
		frame.getContentPane().add(submit,"South");	//menampilkan tombol submit
	    frame.setVisible(true);
	}
	public static void main(String[] args) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();	//jika terdapat exception
		}
		new GUI();	//inisiasi gui
	}

}