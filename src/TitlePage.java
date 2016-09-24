/**
 * @Description: Summative  - Title Page
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

public class TitlePage extends JFrame implements ActionListener{
	//declaring static JPanel variable
	static JPanel contentPane = new JPanel();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			//run TitlePage Frame on startup
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
	public TitlePage() {
		//creating various JLabels and JButtons
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 419);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("Tower Defense");
		lblTitle.setFont(new Font("Starcraft", Font.PLAIN, 49));
		lblTitle.setBounds(10, 72, 564, 91);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTitle);

		JButton btnStart = new JButton("Start");
		btnStart.setToolTipText("Start Game");
		btnStart.setBounds(52, 242, 113, 69);
		btnStart.setFont(new Font("Starcraft", Font.PLAIN, 10));
		//setting ActionCommand to JButton
		btnStart.addActionListener(this);
		btnStart.setActionCommand("start");
		contentPane.add(btnStart);

		JButton btnInstructions = new JButton("Instructions");
		btnInstructions.setToolTipText("Read Instructions");
		//setting ActionCommand to JButton
		btnInstructions.setActionCommand("instruction");
		btnInstructions.addActionListener(this);
		btnInstructions.setFont(new Font("Starcraft", Font.PLAIN, 10));
		btnInstructions.setBounds(214, 242, 150, 69);
		contentPane.add(btnInstructions);

		JButton btnExit = new JButton("Exit");
		btnExit.setToolTipText("Exit Game");
		//setting ActionCommand to JButton
		btnExit.setActionCommand("exit");
		btnExit.addActionListener(this);
		btnExit.setFont(new Font("Starcraft", Font.PLAIN, 10));
		btnExit.setBounds(421, 242, 113, 69);
		contentPane.add(btnExit);
	}//end of Frame method
	public void actionPerformed(ActionEvent e) {
		//if statement testing for ActionCommand "start"
		if (e.getActionCommand().equals("start")){
			//asking for confirmation of choice
			int start = JOptionPane.showConfirmDialog(null, "Have you read the instructions yet?");
			//if choice is yes continue to game 
			if (start == 0) {
				this.setVisible(false);
				Game frame = new Game();
				frame.setVisible(true);
				//make frame not re-sizable
				frame.setResizable(false);
				Game.btnMain.doClick();
			}
			//if choice is no continue to instructions 
			if (start == 1) {
				this.setVisible(false);
				Instructions instructionFrame  = new Instructions();
				instructionFrame.setVisible(true);
			}
		}
		//if statement testing for ActionCommand "instruction"
		if (e.getActionCommand().equals("instruction")){
			//Continue to instructions
			this.setVisible(false);
			Instructions instructionFrame  = new Instructions();
			instructionFrame.setVisible(true);
		}
		//if statement testing for ActionCommand "exit"
		if (e.getActionCommand().equals("exit")){
			//asking for confirmation of choice
			int exit = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?");
			//if choice is yes continue to exit program
			if (exit == 0)
			System.exit(0);
		}
	}//end of actionPerformed Method
}//end of class