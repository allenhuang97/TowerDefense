/**
 * @Description: Summative  - Gameover and Credits
 * @author: Allen Huang
 * @version  v1.0
 * Date: June 14, 2014
 */

//import java classes
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


public class GameoverCredits extends JFrame implements ActionListener{
	//declaring static JPanel variable
	static JPanel contentPane;

	public static void main(String[] args) {
		//run TitlePage Frame on startup
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TitlePage frame = new TitlePage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}//end of main
	//Frame method
	public GameoverCredits() {
		//creating various JLabels and JButtons
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 511, 382);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("Game Over");
		lblTitle.setFont(new Font("Starcraft", Font.PLAIN, 49));
		lblTitle.setBounds(-31, 44, 564, 91);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTitle);

		JButton btnExit = new JButton("Exit");
		btnExit.setToolTipText("Exit Game");
		//setting ActionCommand to JButton
		btnExit.setActionCommand("exit");
		btnExit.addActionListener(this);
		btnExit.setFont(new Font("Starcraft", Font.PLAIN, 10));
		btnExit.setBounds(179, 239, 113, 69);
		contentPane.add(btnExit);
		
		JLabel lblCreatedBy = new JLabel("Created By: Allen Huang");
		lblCreatedBy.setBounds(161, 146, 149, 14);
		contentPane.add(lblCreatedBy);
		
		JLabel lblWave = new JLabel("You Made it to wave: " + Game.level);
		lblWave.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblWave.setBounds(130, 186, 188, 14);
		contentPane.add(lblWave);
	}//end of Frame method
	
	public void actionPerformed(ActionEvent e) {
		//if statement testing for ActionCommand "exit"
		if (e.getActionCommand().equals("exit")){
			int exit = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?");
			if (exit == 0)
			System.exit(0);
		}
	}//end of actionPerformd Method
}//end of class
