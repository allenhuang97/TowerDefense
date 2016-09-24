/**
 * @Description: Summative  - Instructions
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

public class Instructions extends JFrame implements ActionListener{
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
	public Instructions() {
		//creating various JLabels and JButtons
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 419);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitleInstructios = new JLabel("Tower Defense Instructions");
		lblTitleInstructios.setBounds(10, 11, 564, 91);
		lblTitleInstructios.setFont(new Font("Starcraft", Font.PLAIN, 27));
		lblTitleInstructios.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTitleInstructios);

		JButton btnStart = new JButton("Start");
		btnStart.setToolTipText("Start Game");
		btnStart.setBounds(444, 290, 113, 69);
		btnStart.setFont(new Font("Starcraft", Font.PLAIN, 10));
		//setting ActionCommand to JButton
		btnStart.addActionListener(this);
		btnStart.setActionCommand("start");
		contentPane.add(btnStart);

		JButton btnMenu = new JButton("Menu");
		btnMenu.setToolTipText("Return to Menu");
		btnMenu.setBounds(28, 290, 113, 69);
		//setting ActionCommand to JButton
		btnMenu.setActionCommand("menu");
		btnMenu.addActionListener(this);
		btnMenu.setFont(new Font("Starcraft", Font.PLAIN, 10));
		contentPane.add(btnMenu);
		
		//JLabels to display Instructions
		JLabel lblInstructionsLine1 = new JLabel("You start with $200 and with that money you can buy towers to defend against waves of enemies");
		lblInstructionsLine1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblInstructionsLine1.setBounds(10, 110, 564, 14);
		contentPane.add(lblInstructionsLine1);
		
		JLabel lblInstructionsLine2 = new JLabel("The goal of the game is to survive as long as you can against the waves of enemies");
		lblInstructionsLine2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblInstructionsLine2.setBounds(10, 135, 564, 14);
		contentPane.add(lblInstructionsLine2);
		
		JLabel lblInstructionsLine3 = new JLabel("After buying towers you can start the next wave, killing enemies will reward you with gold");
		lblInstructionsLine3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblInstructionsLine3.setBounds(10, 160, 564, 14);
		contentPane.add(lblInstructionsLine3);
		
		JLabel lblInstructionsLine4 = new JLabel("If the enemies slip pass your defense and make it to the end of the track, you will lose a life");
		lblInstructionsLine4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblInstructionsLine4.setBounds(10, 185, 564, 14);
		contentPane.add(lblInstructionsLine4);
		
		JLabel lblInstructionsLine5 = new JLabel("During the waves you can buy or sell towers, so make sure to buy before starting the next wave ");
		lblInstructionsLine5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblInstructionsLine5.setBounds(10, 210, 564, 14);
		contentPane.add(lblInstructionsLine5);
		
		JLabel lblInstructionsLine6 = new JLabel("Every wave will get harder, more enemies will appear and their health will increase");
		lblInstructionsLine6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblInstructionsLine6.setBounds(10, 235, 564, 14);
		contentPane.add(lblInstructionsLine6);
		
		JLabel lblTipTowersSell = new JLabel("Tip: Towers sell less for how much you bought them. So be sure when buying.");
		lblTipTowersSell.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTipTowersSell.setBounds(10, 260, 564, 14);
		contentPane.add(lblTipTowersSell);
	}//end of Frame method

	public void actionPerformed(ActionEvent e) {
		//if statement testing for ActionCommand "start"
		if (e.getActionCommand().equals("start")){
			//asking for confirmation of choice
			int start = JOptionPane.showConfirmDialog(null, "Are you ready to start?");
			//if choice is yes continue to game
			if (start == 0) {
				Instructions instructionFrame  = new Instructions();
				instructionFrame.setVisible(false);
				Game gameFrame = new Game();
				gameFrame.setVisible(true);
				Game.btnMain.doClick();
			}
		}
		//if statement testing for ActionCommand "menu"
		if (e.getActionCommand().equals("menu")){
			//asking for confirmation of choice
			int menu = JOptionPane.showConfirmDialog(null, "Are you done reading the Instructions?");
			//if choice is yes continue to menu
			if (menu == 0) {
				this.setVisible(false);
				TitlePage titleFrame = new TitlePage();
				titleFrame.setVisible(true);
			}
		}
	}//end of actionPerformed Method
}//end of class
