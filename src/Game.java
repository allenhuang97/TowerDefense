/**
 * @Description: Summative  - Game
 * @author: Allen Huang
 * @version  v1.0
 * Date: June 14, 2014
 */

//import java classes
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class Game extends JFrame implements ActionListener{
	//declaring static JPanel variables
	static JPanel contentPane = new JPanel();
	static JPanel upgradePane = new JPanel();
	static JPanel towerPane = new JPanel();
	static JPanel startPane = new JPanel();
	static JPanel playerPane = new JPanel();

	//declaring static JButton variables
	static JButton btnBuy = new JButton("Buy");
	static JButton btnClear = new JButton("Clear");
	static JButton btnSell = new JButton("Sell");
	static JButton btnDone = new JButton("Done");
	static JButton btnShopBasicTower = new JButton("Basic Tower");
	static JButton btnShopIceTower = new JButton("Ice Tower");
	static JButton btnShopLaserTower = new JButton("Laser Tower");
	static JButton btnShopUltimateTower = new JButton("Ultimate Tower");
	static JButton btnNextWave = new JButton("Next Wave");
	static JButton btnRepaint = new JButton("");
	static JButton btnCollision = new JButton("");
	static JButton btnShotAnimate = new JButton("");
	static JButton btnGameover = new JButton("");
	

	//declaring static JLabel variables
	static JLabel lblMoney = new JLabel("Money: ");
	static JLabel lblLife = new JLabel ("Lives: 50");
	static JLabel lblSlotSelected = new JLabel("");
	static JLabel lblSlotSelected1 = new JLabel("");
	static JLabel lblBasicTowerDisplay = new JLabel("");
	static JLabel lblIceTowerDisplay = new JLabel("");
	static JLabel lblLaserTowerDisplay = new JLabel("");
	static JLabel lblUltimateTowerDisplay = new JLabel("");
	static JLabel lblTowerPaneTower = new JLabel("");
	static JLabel lblTowerPaneBuyValue = new JLabel("");
	static JLabel lblTowerPaneSellValue = new JLabel("");
	static JLabel lblTowerPaneDamagePerShot = new JLabel("");
	static JLabel lblTowerPaneRange = new JLabel("");
	static JLabel lblWave = new JLabel("Wave: 1");
	static JLabel lblShopBasicTowerCost = new JLabel("Cost: $50");
	static JLabel lblShopIceTowerCost = new JLabel("Cost: $75");
	static JLabel lblShopLaserTowerCost = new JLabel("Cost: $150");
	static JLabel lblShopUltimateTowerCost = new JLabel("Cost $400");
	static JLabel lblNextWaveEnemyNum = new JLabel("Number of Enemies: 2");
	static JLabel lblNextWaveEnemyHealth = new JLabel("Enemy Health: 15");
	static JLabel lblNextWaveEnemyMoneyDrop = new JLabel("Enemy Money Drop: 20");

	//declaring static Toolkit variable
	static Toolkit basicTowerToolkit = Toolkit.getDefaultToolkit();

	//declaring static Timer variables
	static Timer shopDisplayTimer = new Timer();
	static Timer enemyAnimateTimer = new Timer();
	static Timer drawShootTimer = new Timer();
	static Timer inGameTowerTimer = new Timer();

	//declaring public static variable
	public static int level = 20;
	public static JButton btnMain = new JButton();
	
	//declaring static int variables
	static int imgCount = 0;
	static int slotCount = 0;
	static int picCount = 1;
	static int buttonNum = -7;
	static int buttonX = 50;
	static int buttonY = 50;
	static int money = 200;
	static int shopImgCount2 = 0;
	static int shopImgCount4 = 0;
	static int seconds = 1;
	static int rangePositionX = 0;
	static int rangePositionY = 0;
	static int rangeSize = 0;
	static int enemyAmount = 2;
	static int enemyImgSet = 0;
	static int enemyImgAnimate4 = 0;
	static int enemyStepCount = 0;
	static int enemyNumMove = 1;
	static int life = 25;
	static int towerTarget = 0;
	static int enemyTarget = 0;
	static int enemyLifeNum = 14;
	static int enemyDead = 0;
	static int gameTowerAnimate2 = 0;
	static int gameTowerAnimate4 = 0;
	static int shopSelect = 0;

	//declaring static JPanel variables
	static String towerTypeSelected = "";
	static String animationArea = "";
	static String playerClick = "";
	static String towerTargetNum = "";
	static String shootTowerNum = "";

	//declaring static Point variable
	static Point slotPoint;

	//declaring static boolean variables
	static boolean rangeCreate = false;
	static boolean showRange = false;
	static boolean showShoot = false;
	static boolean createLine = false;
	static boolean collided = false;
	static boolean drawShot = false;
	static boolean drawShotCalled = false;
	static boolean waveStarted = false;
	static boolean towerSelected = false;
	static boolean towerPresent = false;

	//declaring static array variables
	static JButton btnSlot [] = new JButton [42];
	static int towerSlot [] = new int [42];
	static int targetedEnemy [] = new int [42];
	static boolean towerTargeted [] = new boolean [42];
	static ImageIcon basicTowerImg[] = new ImageIcon [2];
	static ImageIcon iceTowerImg[] = new ImageIcon [4];
	static ImageIcon laserTowerImg[] = new ImageIcon [4];
	static ImageIcon ultimateTowerImg[] = new ImageIcon [4];
	static ImageIcon enemyImg[] = new ImageIcon[4];
	static Rectangle [] range = new Rectangle [42]; 
	static JLabel [] lblEnemyEntities = new JLabel [enemyAmount];
	static Point [] enemyLocation = new Point [enemyAmount];
	static int [] enemyDirection = new int [enemyAmount];
	static Rectangle enemyBox[] = new Rectangle [enemyAmount];
	static int enemyLife[] = new int [enemyAmount];
	static Point shootPointArrayTower[] = new Point [42];
	static Point shootPointArrayEnemy[] = new Point [42];

	//Game method to call schedule timers in other classes
	public Game(int seconds) {
		//initializing basicTowerToolkit
		basicTowerToolkit = Toolkit.getDefaultToolkit();
		//creating new timer
		shopDisplayTimer = new Timer();
		//if the String animatinArea equals "shop" schedule shopDisplayTimer in shopTowerAnimate class at interval of 150 milliseconds
		if (animationArea == ("shop"))
			shopDisplayTimer.schedule(new shopTowerAnimate(), seconds * 1);
		//if the String animatinArea equals "next wave" schedule enemyAnimateTimer in enemyAnimate class and  schedule inGameTowerTimer in gameTowerAnimate class at interval of 150 milliseconds
		if (animationArea == ("next wave")){
			//initializing enemy variables
			enemyNumMove = 1;
			enemyStepCount = 0;
			//create new timers
			enemyAnimateTimer = new Timer();
			inGameTowerTimer = new Timer();
			enemyAnimateTimer.schedule(new enemyAnimate(), seconds * 1);
			inGameTowerTimer.schedule(new gameTowerAnimate(), seconds * 1);
		}
		//if the String animatinArea equals "draw shot" schedule drawShootTimer in drawShot class at interval of 150 milliseconds
		if (animationArea == ("draw shot")){
			//creating new timer
			drawShootTimer = new Timer();
			drawShootTimer.schedule(new drawShot(), seconds * 1);
		}
	}//end of Game method
	public static void main(String[] args) {
		//calling methods to initialize variables
		basicTowerImageSet(basicTowerImg);
		iceTowerImageSet(iceTowerImg);
		laserTowerImageSet(laserTowerImg);
		ultimateTowerImageSet(ultimateTowerImg);
		enemyImageSet(enemyImg);
		initializeTowerSlotArray(towerSlot);
		towerTargeted = initializeTowerTarget();
		//Initialize text in labels
		lblMoney.setText("Money: " + money);
		lblLife.setText("Lives: " + life);

		EventQueue.invokeLater(new Runnable() {
			public void run() {				
				try {
					//run Game on startup
					Game frame = new Game();
					frame.setVisible(true);
					//make frame not re-sizable
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}//end of main
	//Frame method
	public Game() {
		//creating various JLabels, JButtons and JPanels
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 880, 630);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBounds(0, 0, 665, 400);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		upgradePane.setBorder(new TitledBorder(null, "Shop", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		upgradePane.setBounds(5, 400, 665, 200);
		contentPane.add(upgradePane);
		upgradePane.setLayout(null);

		towerPane.setBorder(new TitledBorder(null, "Tower", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		towerPane.setBounds(670, 0, 200, 200);
		contentPane.add(towerPane);
		towerPane.setLayout(null);

		playerPane.setBorder(new TitledBorder(null, "Player", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		playerPane.setBounds(670, 200, 200, 200);
		contentPane.add(playerPane);
		playerPane.setLayout(null);

		startPane.setBorder(new TitledBorder(null, "Next Wave", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		startPane.setBounds(670, 400, 200, 200);
		contentPane.add(startPane);
		startPane.setLayout(null);

		btnBuy.setBounds(570, 110, 85, 40);
		btnBuy.setActionCommand("buy");
		btnBuy.addActionListener(this);
		btnBuy.setEnabled(false);
		upgradePane.add(btnBuy);

		btnClear.setBounds(570, 150, 85, 40);
		btnClear.setActionCommand("clear");
		btnClear.addActionListener(this);
		btnClear.setEnabled(false);
		upgradePane.add(btnClear);

		btnSell.setBounds(570, 150, 85, 40);
		btnSell.setActionCommand("sell");
		btnSell.addActionListener(this);
		btnSell.setVisible(false);
		btnSell.setEnabled(false);
		upgradePane.add(btnSell);

		btnDone.setBounds(570, 17, 85, 40);
		btnDone.setActionCommand("done");
		btnDone.addActionListener(this);
		btnDone.setEnabled(false);
		upgradePane.add(btnDone);

		btnShopBasicTower.setBounds(40, 110, 110, 60);
		btnShopBasicTower.setEnabled(false);
		btnShopBasicTower.setActionCommand("basic tower");
		btnShopBasicTower.addActionListener(this);
		upgradePane.add(btnShopBasicTower);

		lblShopBasicTowerCost.setBounds(40, 175, 110, 14);
		upgradePane.add(lblShopBasicTowerCost);
		lblShopBasicTowerCost.setHorizontalAlignment(SwingConstants.CENTER);

		btnShopIceTower.setBounds(170, 110, 110, 60);
		btnShopIceTower.setEnabled(false);
		btnShopIceTower.setActionCommand("ice tower");
		btnShopIceTower.addActionListener(this);
		upgradePane.add(btnShopIceTower);

		lblShopIceTowerCost.setBounds(170, 175, 110, 14);
		upgradePane.add(lblShopIceTowerCost);
		lblShopIceTowerCost.setHorizontalAlignment(SwingConstants.CENTER);

		btnShopLaserTower.setBounds(300, 110, 110, 60);
		btnShopLaserTower.setEnabled(false);
		btnShopLaserTower.setActionCommand("laser tower");
		btnShopLaserTower.addActionListener(this);
		upgradePane.add(btnShopLaserTower);

		lblShopLaserTowerCost.setBounds(300, 175, 110, 14);
		upgradePane.add(lblShopLaserTowerCost);
		lblShopLaserTowerCost.setHorizontalAlignment(SwingConstants.CENTER);

		btnShopUltimateTower.setBounds(430, 110, 125, 60);
		btnShopUltimateTower.setEnabled(false);
		btnShopUltimateTower.setActionCommand("ultimate tower");
		btnShopUltimateTower.addActionListener(this);
		upgradePane.add(btnShopUltimateTower);	

		lblShopUltimateTowerCost.setBounds(430, 175, 110, 14);
		upgradePane.add(lblShopUltimateTowerCost);
		lblShopUltimateTowerCost.setHorizontalAlignment(SwingConstants.CENTER);

		btnNextWave.setBounds(51, 152, 95, 23);
		btnNextWave.setActionCommand("next level");
		btnNextWave.addActionListener(this);
		startPane.add(btnNextWave);

		lblMoney.setBounds(20, 35, 999, 14);
		playerPane.add(lblMoney);

		lblLife.setBounds(20, 60, 999, 14);
		playerPane.add(lblLife);

		lblWave.setBounds(20, 85, 999, 14);
		playerPane.add(lblWave);

		lblNextWaveEnemyMoneyDrop.setBounds(20, 85, 999, 14);
		startPane.add(lblNextWaveEnemyMoneyDrop);

		lblNextWaveEnemyNum.setBounds(20, 35, 999, 14);
		startPane.add(lblNextWaveEnemyNum);

		lblNextWaveEnemyHealth.setBounds(20, 60, 999, 14);
		startPane.add(lblNextWaveEnemyHealth);

		lblSlotSelected.setBounds(24, 27, 151, 14);
		upgradePane.add(lblSlotSelected);

		lblSlotSelected1.setBounds(10, 15, 151, 14);
		towerPane.add(lblSlotSelected1);

		lblBasicTowerDisplay.setBounds(70 ,50 , 50, 50);
		upgradePane.add(lblBasicTowerDisplay);

		lblIceTowerDisplay.setBounds(202, 50, 50, 50);
		upgradePane.add(lblIceTowerDisplay);

		lblLaserTowerDisplay.setBounds(331, 49, 50, 50);
		upgradePane.add(lblLaserTowerDisplay);

		lblUltimateTowerDisplay.setBounds(467, 50, 50, 50);
		upgradePane.add(lblUltimateTowerDisplay);

		lblTowerPaneTower.setBounds(10, 45, 200, 14);
		towerPane.add(lblTowerPaneTower);

		lblTowerPaneBuyValue.setBounds(10, 70, 151, 14);
		towerPane.add(lblTowerPaneBuyValue);

		lblTowerPaneSellValue.setBounds(9, 95, 151, 14);
		towerPane.add(lblTowerPaneSellValue);

		lblTowerPaneDamagePerShot.setBounds(10, 125, 200, 14);
		towerPane.add(lblTowerPaneDamagePerShot);

		lblTowerPaneRange.setBounds(10, 150, 151, 14);
		towerPane.add(lblTowerPaneRange);

		btnGameover.setActionCommand("gameover");
		btnGameover.addActionListener(this);
		btnGameover.setVisible(true);
		contentPane.add(btnGameover);

		btnRepaint.setActionCommand("repaint");
		btnRepaint.addActionListener(this);
		btnRepaint.setVisible(true);
		contentPane.add(btnRepaint);

		btnCollision.setActionCommand("collision");
		btnCollision.addActionListener(this);
		btnCollision.setVisible(true);
		contentPane.add(btnCollision);

		btnShotAnimate.setActionCommand("shoot");
		btnShotAnimate.addActionListener(this);
		btnShotAnimate.setVisible(true);
		contentPane.add(btnShotAnimate);
		
		btnMain.setActionCommand("main");
		btnMain.addActionListener(this);
		btnMain.setVisible(true);
		contentPane.add(btnMain);
		
		//loop to create JButtons
		for (buttonX = 50; buttonX <= 550; buttonX += 200){
			buttonNum += 7;
			for(buttonY = 50; buttonY <=350; buttonY += 50){
				btnSlot[buttonNum] = new JButton("");
				btnSlot[buttonNum].setBounds(buttonX, buttonY, 50, 50);
				btnSlot[buttonNum].setActionCommand("btnSlot" + buttonNum);
				btnSlot[buttonNum].addActionListener(this);
				contentPane.add(btnSlot[buttonNum]);
				buttonNum++;
			}	  
		}
		//loop to create JButtons
		buttonNum = 0;
		for (buttonX = 150; buttonX <= 600; buttonX += 200){
			buttonNum += 7;
			for(buttonY = 0; buttonY <=300; buttonY += 50){
				btnSlot[buttonNum] = new JButton("");
				btnSlot[buttonNum].setBounds(buttonX, buttonY, 50, 50);
				btnSlot[buttonNum].setActionCommand("btnSlot" + buttonNum);
				btnSlot[buttonNum].addActionListener(this);
				contentPane.add(btnSlot[buttonNum]);
				buttonNum++;
			}
		}
	}	
	//shopTowerAnimate class with shopDisplayTimer scheduled in it, to animate labels in shop.
	class shopTowerAnimate extends TimerTask {
		public void run() {
			//setting JLabels icons to different ImageIcons in an array 
			lblBasicTowerDisplay.setIcon(basicTowerImg[shopImgCount2]);
			lblIceTowerDisplay.setIcon(iceTowerImg[shopImgCount4]);
			lblLaserTowerDisplay.setIcon(laserTowerImg[shopImgCount4]);
			lblUltimateTowerDisplay.setIcon(ultimateTowerImg[shopImgCount4]);
			shopImgCount2++;
			shopImgCount4++;
			//resetting if number gets too high
			if (shopImgCount2 == 2)
				shopImgCount2 = 0;   
			if (shopImgCount4 ==4)
				shopImgCount4 = 0;
			//rescheduling timer at 150 milliseconds
			shopDisplayTimer.schedule(new shopTowerAnimate(), seconds * 150);
		}
	}
	//enemyAnimate class with enemyAnimateTimer scheduled in it, to move enemies, collision detection, animating and timing
	class enemyAnimate extends TimerTask {
		public void run() {
			//for loop, looping n variable
			for (int n = 0; n < enemyNumMove; n++){
				//initializing variables to point array variable enemyLocation with index of n
				double enemyX = enemyLocation[n].getX();
				double enemyY = enemyLocation[n].getY();
				//if statement testing collision between enemyLocation (index n) with top of screen
				if (enemyLocation[n].y <= 0)
					//change int array enemyDirection with index of n to 2, direction right
					enemyDirection[n] = 2;
				//if statement testing collision between enemyLocation (index n) with bottom of screen
				if (enemyLocation[n].y >= 350 && enemyDirection[n] == 3)
					//change int array enemyDirection with index of n to 2, direction right
					enemyDirection[n] = 2;
				//for loop, looping variable at adding 14 each time
				for(int x = 8; x < btnSlot.length; x+=14){
					//if statement testing collision between enemyLocation (index n) with left side of buttons at the top of the screen when enemyDirection with index of n equals 2
					if ((enemyLocation[n].x + 40) == (btnSlot[x].getX()) && enemyDirection[n] == 2 && enemyLocation[n].y <= 5)
						//change int array enemyDirection with index of n to 3, direction down
						enemyDirection[n] = 3;
				}
				//for loop, looping variable at adding 14 each time
				for(int y = 20; y < btnSlot.length; y+=14){
					//if statement testing collision between enemyLocation (index n) with left side of buttons at the bottom of the screen when enemyDirection with index of n equals 1
					if ((enemyLocation[n].x + 40) == (btnSlot[y].getX())  && enemyDirection[n] == 2 && enemyLocation[n].y >= 350)
						//change int array enemyDirection with index of n to 1, direction up
						enemyDirection[n] = 1;
				}
				//if statement testing collision between enemyLocation (index n) with far right of screen
				if (enemyLocation[n].x + 40 == 660)
					//change int array enemyDirection with index of n to 1, direction up
					enemyDirection[n] = 1;
				//if statement testing collision between enemyLocation (index n) with far top, right of the screen meaning that the enemies passed defense and reached the end
				if (enemyLocation[n].x + 40 == 660 && enemyDirection[n] == 1 && enemyLocation[n].y <=5 && lblEnemyEntities[n].isEnabled()==true){
					//change int array enemyDirection with index of n to 4, end of track
					enemyDirection[n] = 4;
					//disable JLabel array lblEnemyEntities with index n
					lblEnemyEntities[n].setEnabled(false);
					//minus life by one
					life--;
					//reset text in JLabel lblLife
					lblLife.setText("Lives: " + life);
				}
				//switch case testing for int array enemyDirection with index n
				switch (enemyDirection[n]){
				//if int array enemyDirection with index n is equal to 1
				case 1:
					//change Icon and Location of JLabel array lblEnemyEntities with index of n
					lblEnemyEntities[n].setIcon(enemyImg[enemyImgAnimate4]);
					lblEnemyEntities[n].setLocation(enemyLocation[n]);
					//changing enemyY variable and setting it to enemyLocation (index n) to move lblEnemyEntities with index of n
					enemyY-=5;
					enemyLocation[n].setLocation(enemyX, enemyY);
					break;
					//if int array enemyDirection with index n is equal to 2
				case 2:
					//change Icon and Location of JLabel array lblEnemyEntities with index of n
					lblEnemyEntities[n].setIcon(enemyImg[enemyImgAnimate4]);
					lblEnemyEntities[n].setLocation(enemyLocation[n]);
					//changing enemyY variable and setting it to enemyLocation (index n) to move lblEnemyEntities with index of n
					enemyX+=5;
					enemyLocation[n].setLocation(enemyX, enemyY);
					break;
					//if int array enemyDirection with index n is equal to 3
				case 3:
					//change Icon and Location of JLabel array lblEnemyEntities with index of n
					lblEnemyEntities[n].setIcon(enemyImg[enemyImgAnimate4]);
					lblEnemyEntities[n].setLocation(enemyLocation[n]);
					//changing enemyY variable and setting it to enemyLocation (index n) to move lblEnemyEntities with index of n
					enemyY+=5;
					enemyLocation[n].setLocation(enemyX, enemyY);
					break;
					//if int array enemyDirection with index n is equal to 4
				case 4:
					//set enemyLife with index n to 0
					enemyLife[n] = 0;
					//add one to enemyDead count
					enemyDead++;
					//change icon and visibility of lblEnemyEntities with index of n
					lblEnemyEntities[n].setIcon(enemyImg[0]);
					lblEnemyEntities[n].setVisible(false);
					enemyDirection[n] = 5;
					//if the enemyDead count equals the amount of total enemies end the wave
					if (enemyDead == enemyAmount){
						//cancel various timers to end wave
						enemyAnimateTimer.cancel();
						drawShootTimer.cancel();
						inGameTowerTimer.cancel();
						//enabling shop and next wave button
						btnNextWave.setEnabled(true);
						enableSlot();
						//set drawShotCalled Variable to false
						drawShotCalled = false;
						waveStarted = false;
						//resetting text in labels to fit with data for next wave
						lblNextWaveEnemyNum.setText("Number of Enemies: " + (enemyAmount +=level));
						lblNextWaveEnemyHealth.setText("Enemy Health: " + (enemyLifeNum +=1));
					}
					break;
				}//end of switch case testing for int array enemyDirection with index n
				//set Rectangle array enemyBox with index of n to location of lblEnemyEntities with index of n
				enemyBox[n].setLocation(lblEnemyEntities[n].getX(), lblEnemyEntities[n].getY());
				//for loop, looping t variable
				for (int t = 0; t < 42; t++){
					//if boolean array towerTargeted (index t) is not attacking and Rectangle array enemyBox index of n intersects with the Rectangle array range index of t and int array enemyLife index of n is more than zero
					if ((towerTargeted[t] == false)&&(towerSlot[t]!=0)&&(enemyBox[n].intersects(range[t])==true)&&(enemyLife[n]>0)){
						//set boolean array towerTargeted (index t) to true, attacking
						towerTargeted[t] = true;
						//set int array targetedEnemy (index t) to n variable
						targetedEnemy[t] = n;
						//if this is the first time calling attack 
						if (drawShotCalled == false){
							//set drawShotCalled (calling attack) to true which means attack has been called
							drawShotCalled = true;
							//press JButton btnCollision to start drawing shot timer
							btnCollision.doClick();
						}
					}
				}
			}//end of for loop, looping n variable
			//add one to enemyStepCount variable
			enemyStepCount++;
			//if the enemyStepCount is divided by ten and the remainder equals 0 and enemyStepCount divided by ten is less than enemyAmount
			if (enemyStepCount % 10 == 0 && enemyStepCount / 10 < lblEnemyEntities.length)
				//add one to enemyNumMove variable
				enemyNumMove++;
			//add one to enemyImgAnimate4 variable to continue image changing for enemies
			enemyImgAnimate4++;
			//if enemyImgAnimate4 reaches 4, reset value back to zero
			if (enemyImgAnimate4 == 4)
				enemyImgAnimate4 = 0;
			//if life equals zero
			if(life == 0){
				//click btnGameover to end game and show gameover Frame
				btnGameover.doClick();
				//cancel enemyAnimateTimer
				enemyAnimateTimer.cancel();
			}
			//if life doesn't equal zero then schedule enemyAnimateTimer in enemyAnimate class at 93 milliseconds
			else
				enemyAnimateTimer.schedule(new enemyAnimate(), seconds * 93);
		}
	}
	//drawShot class with drawShootTimer scheduled in it, to set location of line shots and damage calculations
	class drawShot extends TimerTask {
		public void run() {
			//for loop, looping i variable
			for (int i = 0; i < 42; i++){
				//if boolean array towerTargeted index of i is true, attacking
				if (towerTargeted[i]==true){	
					//set Point array shootPointArrayTower index of i to the middle of JButton array btnSlot index of i
					shootPointArrayTower[i] = new Point (btnSlot[i].getX() + 25, btnSlot[i].getY() + 50);
					//set Point array shootPointArrayEnemy index of i to the middle of JLabel array lblEnemyEntities index of i
					shootPointArrayEnemy[i] = new Point (lblEnemyEntities[targetedEnemy[i]].getX() + 15, lblEnemyEntities[targetedEnemy[i]].getY() + 50);
					//switch case testing int array towerSlot with index of i
					switch (towerSlot[i]){
					//if int array towerSlot with index n is equal to 1
					case 1:
						//decrease int array enemyLife with index of int array targetedEnemy with index of i by one
						enemyLife[targetedEnemy[i]]--;
						break;
						//if int array towerSlot with index n is equal to 2
					case 2:
						//decrease int array enemyLife with index of int array targetedEnemy with index of i by two
						enemyLife[targetedEnemy[i]]-=2;
						break;
						//if int array towerSlot with index n is equal to 3
					case 3:
						//decrease int array enemyLife with index of int array targetedEnemy with index of i by three
						enemyLife[targetedEnemy[i]]-=3;
						break;
						//if int array towerSlot with index n is equal to 4
					case 4:
						//decrease int array enemyLife with index of int array targetedEnemy with index of i by six
						enemyLife[targetedEnemy[i]]-=6;
					}
					//testing if int array enemyBox with index of int array targetedEnemy with index of i doesn't intersect with Rectangle array range with index of i
					if ((towerSlot[i]!=0)&&(enemyBox[targetedEnemy[i]].intersects(range[i])==false)){
						//set boolean array towerTargeted with index of i to false, not attacking
						towerTargeted[i] = false;
					}
					//test if enemyLife with index of int array targetedEnemy with index of i is less than or equal to zero
					if (enemyLife[targetedEnemy[i]] <=0){
						//for loop, looping q variable
						for (int q = 0; q < 42; q++){
							//testing to see i any other towers are targeting enemy with zero health, if so re-target to different enemy
							if(targetedEnemy[q]==targetedEnemy[i]){
								towerTargeted[q] = false;
							}
						}
						//set that enemy with zero health to invisible and off the screen
						//lblEnemyEntities[targetedEnemy[i]].setEnabled(false);
						lblEnemyEntities[targetedEnemy[i]].setIcon(enemyImg[0]);
						lblEnemyEntities[targetedEnemy[i]].setVisible(false);
						lblEnemyEntities[targetedEnemy[i]].setLocation(1000,1000);
						enemyDirection[targetedEnemy[i]] = 5;
						//add money for killing enemy
						money += 20;
						//resetting text in lblMoney
						lblMoney.setText("Money: " + money);
						//increase enemyDead count by one
						enemyDead++;
					}
					else{
						//click JButton btnShotAnimtate to draw lines using graphics g
						btnShotAnimate.doClick();
					}
				}
			}
			
			//if the amount of enemyDead equals the total enemyAmount then end the wave
			if (enemyDead == enemyAmount){
				//cancel various timers to end wave
				enemyAnimateTimer.cancel();
				drawShootTimer.cancel();
				inGameTowerTimer.cancel();
				//enable shot and next wave button
				btnNextWave.setEnabled(true);
				enableSlot();
				//set drawShotCalled and wave Started Variable to false
				drawShotCalled = false;
				waveStarted = false;
				//resetting text in JLabels to fit data for next wave
				lblNextWaveEnemyNum.setText("Number of Enemies: " + (enemyAmount +=level));
				lblNextWaveEnemyHealth.setText("Enemy Health: " + (enemyLifeNum +=1));
			}
			//there are still enemies continue scheduling drawShootTimer in drawShot class with 500 millisecond interval
			else
				drawShootTimer.schedule(new drawShot(), seconds * 500);
		}
	}
	//gameTowerAnimate class with inGameTowerTimer scheduled in it, to animate towers in game during wave
	class gameTowerAnimate extends TimerTask {
		public void run() {
			//for loop, looping i variable
			for (int i = 0; i < 42; i++){
				//if there is a tower present
				if (towerSlot[i]!=0){
					//switch case testing the type of tower in the towerSlot and changing images according to their tower type to animate
					switch (towerSlot[i]){
					case 1:
						btnSlot[i].setIcon(basicTowerImg[gameTowerAnimate2]);
						break;
					case 2:
						btnSlot[i].setIcon(iceTowerImg[gameTowerAnimate4]);
						break;
					case 3:
						btnSlot[i].setIcon(laserTowerImg[gameTowerAnimate4]);
						break;
					case 4:
						btnSlot[i].setIcon(ultimateTowerImg[gameTowerAnimate4]);
						break;
					}
					//increase gameTowerAnimate2 and gameTowerAnimate4 by one to continue changing icons
					gameTowerAnimate2++;
					gameTowerAnimate4++;
					//if gameTowerAnimate2 or gameTowerAnimate4 reach their max then set back to zero to continue animating
					if(gameTowerAnimate2 == 2)
						gameTowerAnimate2 = 0;
					if(gameTowerAnimate4 == 4)
						gameTowerAnimate4 = 0;
				}
			}
			//schedule inGameTowerTimer in gameTowerAnimate class at 150 millisecond interval
			inGameTowerTimer.schedule(new gameTowerAnimate(), seconds * 150);
		}
	}
	//actionPerformed Method, receiving action commands
	public void actionPerformed(ActionEvent evt) {
		//if received "buy" ActionCommand
		if (evt.getActionCommand().equals ("buy")){	
			//buy the tower selected and subtract the amount from total money
			if (towerTypeSelected == "basic"){
				towerSlot[Integer.parseInt(playerClick.substring(7))] = 1;
				money-=50;
			}
			if (towerTypeSelected == "ice"){
				towerSlot[Integer.parseInt(playerClick.substring(7))] = 2;
				money-=75;
			}
			if (towerTypeSelected == "laser"){
				towerSlot[Integer.parseInt(playerClick.substring(7))] = 3;
				money-=150;
			}
			if (towerTypeSelected == "ultimate"){
				towerSlot[Integer.parseInt(playerClick.substring(7))] = 4;
				money-=400;
			}
			//calling methods to enable and disable slot and shop
			disableShop();
			enableSlot();
			//calling method to set information to tower panel
			setTowerPane();
			//create the range for this tower bought
			rangeCreate = true;
			this.repaint();
		}
		//if received "clear" ActionCommand
		if (evt.getActionCommand().equals ("clear")){	
			btnBuy.setEnabled(false);
			towerTypeSelected = "";
			btnSlot[Integer.parseInt(playerClick.substring(7))].setIcon(null);
			clearTowerPane();
		}
		//if received "sell" ActionCommand
		if (evt.getActionCommand().equals ("sell")){
			switch(towerSlot[Integer.parseInt(playerClick.substring(7))]){
			case 1:
				money+=25;
				break;
			case 2:
				money+=40;
				break;
			case 3: 
				money+=75;
				break;
			case 4:
				money+=200;
				break;
			}
			towerSlot[Integer.parseInt(playerClick.substring(7))] = 0;
			btnSlot[Integer.parseInt(playerClick.substring(7))].setIcon(null);
			enableSlot();
			disableShop();
			clearTowerPane();
			this.repaint();
		}
		//if received "done" ActionCommand
		if (evt.getActionCommand().equals ("done")){
			disableShop();
			enableSlot();
			switch(towerSlot[Integer.parseInt(playerClick.substring(7))]){
			case 0:
				btnSlot[Integer.parseInt(playerClick.substring(7))].setIcon(null);
				break;
			case 1:
				btnSlot[Integer.parseInt(playerClick.substring(7))].setIcon(basicTowerImg[0]);
				break;
			case 2:
				btnSlot[Integer.parseInt(playerClick.substring(7))].setIcon(iceTowerImg[0]);
				break;
			case 3:
				btnSlot[Integer.parseInt(playerClick.substring(7))].setIcon(laserTowerImg[0]);
				break;
			case 4:
				btnSlot[Integer.parseInt(playerClick.substring(7))].setIcon(ultimateTowerImg[0]);
				break;
			}
			this.repaint();
			clearTowerPane();
		}
		//if received "basicTower" ActionCommand
		if (evt.getActionCommand().equals ("basic tower")){		
			btnSlot[Integer.parseInt(playerClick.substring(7))].setIcon(basicTowerImg[0]);
			towerTypeSelected = "basic";
			shopSelect = 1;
			setTowerPaneShop();
			btnBuy.setEnabled(true);
		}
		//if received "iceTower" ActionCommand
		if (evt.getActionCommand().equals ("ice tower")){		
			btnSlot[Integer.parseInt(playerClick.substring(7))].setIcon(iceTowerImg[0]);
			towerTypeSelected = "ice";
			shopSelect = 2;
			setTowerPaneShop();
			btnBuy.setEnabled(true);
		}
		//if received "laserTower" ActionCommand
		if (evt.getActionCommand().equals ("laser tower")){		
			btnSlot[Integer.parseInt(playerClick.substring(7))].setIcon(laserTowerImg[0]);
			towerTypeSelected = "laser";
			shopSelect = 3;
			setTowerPaneShop();
			btnBuy.setEnabled(true);
		}
		//if received "ultimateTower" ActionCommand
		if (evt.getActionCommand().equals ("ultimate tower")){		
			btnSlot[Integer.parseInt(playerClick.substring(7))].setIcon(ultimateTowerImg[0]);
			towerTypeSelected = "ultimate";
			shopSelect = 4;
			setTowerPaneShop();
			btnBuy.setEnabled(true);
		}
		//if received "btn" ActionCommand
		if (evt.getActionCommand().substring(0, 3).equals ("btn")){
			if(waveStarted == false){
				playerClick = evt.getActionCommand();
				if (towerSlot[Integer.parseInt(playerClick.substring(7))] == 0){
					clearTowerPane();
					btnClear.setVisible(true);
					btnSell.setVisible(false);
					towerPresent = false;
					range[Integer.parseInt(playerClick.substring(7))]=(null);
					towerSlot[Integer.parseInt(playerClick.substring(7))] = 0;
				}
				if (towerSlot[Integer.parseInt(playerClick.substring(7))] !=0){
					btnSell.setVisible(true);
					btnClear.setVisible(false);
					towerPresent = true;
					setTowerPane();
					showRange = true;
				}
				animationArea = ("shop");
				new Game(1);
				enableShop();
				disableSlot();	
				this.repaint();
			}
		}
		//if received "next level" ActionCommand
		if (evt.getActionCommand().equals ("next level")){
			animationArea = ("next wave");
			enemyAmount +=level-level;
			enemyLifeNum +=1;
			lblEnemyEntities = (JLabel[]) resizeArray(lblEnemyEntities, enemyAmount);
			enemyLocation = (Point[]) resizeArray(enemyLocation, enemyAmount);
			enemyDirection = (int[]) resizeArray(enemyDirection, enemyAmount);
			enemyBox = (Rectangle[]) resizeArray(enemyBox, enemyAmount);
			enemyLife = (int[]) resizeArray(enemyLife, enemyAmount);
			enemyLocation = enemyEntityInitialize(enemyLocation);
			new Game(1);
			waveStarted = true;
			btnNextWave.setEnabled(false);
			level ++;
			lblWave.setText("Wave: " + level);
		}
		//if received "collision" ActionCommand
		if (evt.getActionCommand().equals ("collision")){
			animationArea = "draw shot";
			new Game(1);
		}
		//if received "shoot" ActionCommand
		if (evt.getActionCommand().equals ("shoot")){
			drawShot = true;
			this.repaint();
		}
		//if received "repaint" ActionCommand
		if (evt.getActionCommand().equals ("repaint")){
			this.repaint();
		}
		//if received "gameover" ActionCommand
		if (evt.getActionCommand().equals ("gameover")){
			this.setVisible(false);
			GameoverCredits gameoverFrame = new GameoverCredits();
			gameoverFrame.setVisible(true);
		}
		//if received "main" ActionCommand
		if (evt.getActionCommand().equals("main")){
			basicTowerImageSet(basicTowerImg);
			iceTowerImageSet(iceTowerImg);
			laserTowerImageSet(laserTowerImg);
			ultimateTowerImageSet(ultimateTowerImg);
			enemyImageSet(enemyImg);
			initializeTowerSlotArray(towerSlot);
			towerTargeted = initializeTowerTarget();
			//Initialize text in labels
			lblMoney.setText("Money: " + money);
			lblLife.setText("Lives: " + life);
		}
	}
	static void enableShop (){
		//enable shop by enabling buttons and starting animations
		if (towerPresent == false){
			btnClear.setEnabled(true);
			btnSell.setEnabled(false);
		}
		if (towerPresent == true){	
			btnClear.setEnabled(false);
			btnSell.setEnabled(true);
		}
		btnBuy.setEnabled(false);
		btnDone.setEnabled(true);
		lblMoney.setText("Money: " + money);
		lblSlotSelected.setText("You Have Selected: Slot " + (Integer.parseInt(playerClick.substring(7))+1));
		btnNextWave.setEnabled(false);
		if (towerPresent == false){
			if (money >= 50)
				btnShopBasicTower.setEnabled(true);
			if (money >= 75)
				btnShopIceTower.setEnabled(true);
			if (money >= 150)
				btnShopLaserTower.setEnabled(true);
			if (money >= 400)
				btnShopUltimateTower.setEnabled(true);
		}
	}
	static void disableShop(){
		//disable shop by disabling buttons and stopping animation
		btnBuy.setEnabled(false);
		btnClear.setEnabled(false);
		btnSell.setEnabled(false);
		btnShopBasicTower.setEnabled(false);
		btnShopIceTower.setEnabled(false);
		btnShopLaserTower.setEnabled(false);
		btnShopUltimateTower.setEnabled(false);
		lblSlotSelected.setText(null);
		lblMoney.setText("Money: " + money);
		shopDisplayTimer.cancel();
		lblBasicTowerDisplay.setIcon(null);
		lblIceTowerDisplay.setIcon(null);
		lblLaserTowerDisplay.setIcon(null);
		lblUltimateTowerDisplay.setIcon(null);
		btnDone.setEnabled(false);
		btnNextWave.setEnabled(true);
	}
	static void enableSlot(){
		//enable tower slots, by enabling buttons
		for (buttonNum = 0; buttonNum<=41; buttonNum++)
			btnSlot[buttonNum].setEnabled(true);
	}
	static void disableSlot(){
		//disable tower slots, by disabling buttons
		for (buttonNum = 0; buttonNum<=41; buttonNum++)
			btnSlot[buttonNum].setEnabled(false);
	}
	static void basicTowerImageSet(ImageIcon basicTowerImg[]){
		//method for initializing imageIcons of basicTowerSet to and array
		picCount = 1;
		for (imgCount = 0; imgCount<=1; imgCount++){
			basicTowerImg[imgCount]	= new ImageIcon ("bin\\resources\\Basic tower " + (picCount) + ".png");	
			picCount++;
		}
	}
	static void iceTowerImageSet(ImageIcon iceTowerImg[]){
		//method for initializing imageIcons of iceTowerSet to an array
		picCount = 1;
		for (imgCount = 0; imgCount <=3; imgCount++){
			iceTowerImg[imgCount] = new ImageIcon ("bin\\resources\\Ice tower " + (picCount) + ".png");
			picCount++;
		}
	}
	static void laserTowerImageSet(ImageIcon laserTowerImg[]){
		//method for initializing imageIcons of laserTowerImageSet to an array
		picCount = 1;
		for (imgCount = 0; imgCount <=3; imgCount++){
			laserTowerImg[imgCount] = new ImageIcon("bin\\resources\\Laser tower " + (picCount) + ".png");
			picCount++;
		}
	}
	static void ultimateTowerImageSet(ImageIcon ultimateTowerImg[]){
		//method for initializing imageIcons of ultimateTowerImageSet to an array
		picCount = 1;
		for (imgCount = 0; imgCount <=3; imgCount++){
			ultimateTowerImg[imgCount] = new ImageIcon("bin\\resources\\Ultimate tower " + (picCount) + ".png");
			picCount++;
		}
	}
	static void enemyImageSet(ImageIcon enemyImg[]){
		//method for initializing imageIcons of enemyImageSet to an array
		picCount = 1;
		for (imgCount = 0; imgCount <=3; imgCount++){
			enemyImg[imgCount] = new ImageIcon("bin\\resources\\Enemy " + (picCount) + ".png");
			picCount++;
		}
	}
	static int [] initializeTowerSlotArray(int[] towerSlot){
		//method for initializing imageIcons of TowerSlot to an array
		for (slotCount = 0; slotCount<42; slotCount++){
			towerSlot[slotCount] = 0;
		}
		return towerSlot;	
	}
	static void setTowerPane(){
		//array to display specifications of each tower on tower Panel depending on which tower is choosen
		switch(towerSlot[Integer.parseInt(playerClick.substring(7))]){
		case 1:
			lblTowerPaneTower.setText("Tower Present: Basic Tower");
			lblTowerPaneBuyValue.setText("Bought at: 50 gold");
			lblTowerPaneSellValue.setText("Sells at: 25 gold");
			lblTowerPaneDamagePerShot.setText("Damage Per Shot: 1");
			lblTowerPaneRange.setText("Range: 2 Blocks");
			break;
		case 2:
			lblTowerPaneTower.setText("Tower Present: Ice Tower");
			lblTowerPaneBuyValue.setText("Bought at: 75 gold");
			lblTowerPaneSellValue.setText("Sells at: 40 gold");
			lblTowerPaneDamagePerShot.setText("Damage Per Shot: 2");
			lblTowerPaneRange.setText("Range: 2 Blocks");
			break;
		case 3:
			lblTowerPaneTower.setText("Tower Present: Laser Tower");
			lblTowerPaneBuyValue.setText("Bought at: 150 gold");
			lblTowerPaneSellValue.setText("Sells at: 75 gold");
			lblTowerPaneDamagePerShot.setText("Damage Per Shot: 3");
			lblTowerPaneRange.setText("Range: 4 Blocks");
			break;
		case 4:
			lblTowerPaneTower.setText("Tower Present: Ultimate Tower");
			lblTowerPaneBuyValue.setText("Bought at: 400 gold");
			lblTowerPaneSellValue.setText("Sells at: 200 gold");
			lblTowerPaneDamagePerShot.setText("Damage Per Shot: 6");
			lblTowerPaneRange.setText("Range: 6 Blocks");
			break;
		}
		lblSlotSelected1.setText("You Have Selected: Slot " + (Integer.parseInt(playerClick.substring(7))+1));
	}
	static void setTowerPaneShop(){
		//array to display specifications of each tower on tower Panel depending on which tower is choosen in the shop
		switch(shopSelect){
		case 1:
			lblTowerPaneTower.setText("Tower Present: Basic Tower");
			lblTowerPaneBuyValue.setText("Buy at: 50 gold");
			lblTowerPaneSellValue.setText("Sell at: 25 gold");
			lblTowerPaneDamagePerShot.setText("Damage Per Shot: 1");
			lblTowerPaneRange.setText("Range: 2 Blocks");
			break;
		case 2:
			lblTowerPaneTower.setText("Tower Present: Ice Tower");
			lblTowerPaneBuyValue.setText("Buy at: 75 gold");
			lblTowerPaneSellValue.setText("Sell at: 40 gold");
			lblTowerPaneDamagePerShot.setText("Damage Per Shot: 2");
			lblTowerPaneRange.setText("Range: 2 Blocks");
			break;
		case 3:
			lblTowerPaneTower.setText("Tower Present: Laser Tower");
			lblTowerPaneBuyValue.setText("Buy at: 150 gold");
			lblTowerPaneSellValue.setText("Sell at: 75 gold");
			lblTowerPaneDamagePerShot.setText("Damage Per Shot: 3");
			lblTowerPaneRange.setText("Range: 4 Blocks");
			break;
		case 4:
			lblTowerPaneTower.setText("Tower Present: Ultimate Tower");
			lblTowerPaneBuyValue.setText("Buy at: 400 gold");
			lblTowerPaneSellValue.setText("Sell at: 200 gold");
			lblTowerPaneDamagePerShot.setText("Damage Per Shot: 6");
			lblTowerPaneRange.setText("Range: 6 Blocks");
			break;
		}
		lblSlotSelected1.setText("You Have Selected: Slot " + (Integer.parseInt(playerClick.substring(7))+1));
	}
	static void clearTowerPane(){
		//method for clearing all of the entities and text in tower Panel
		lblSlotSelected1.setText(null);
		lblTowerPaneTower.setText(null);
		lblTowerPaneBuyValue.setText(null);
		lblTowerPaneSellValue.setText(null);
		lblTowerPaneDamagePerShot.setText(null);
		lblTowerPaneRange.setText(null);
	}
	static boolean [] initializeTowerTarget(){
		//setting all toweTargeted indexes to false
		for (int i = 0; i < 42; i++){
			towerTargeted[i] = false;
		}
		return towerTargeted;
	}
	static Point[] enemyEntityInitialize(Point enemyLocation[]){
		//initializing all enemy Entities for start of wave
		for (int i = 0; i < lblEnemyEntities.length; i++){
			lblEnemyEntities[i] = new JLabel("");
			lblEnemyEntities[i].setBounds(5, 345, 50, 50);
			contentPane.add(lblEnemyEntities[i]);
			enemyLocation[i] = lblEnemyEntities[i].getLocation();
			enemyDirection[i] = 1;
			enemyBox[i] = new Rectangle (enemyLocation[i].x, enemyLocation[i].y, 50, 50);
			lblEnemyEntities[i].setEnabled(true);
			lblEnemyEntities[i].setVisible(true);
			lblEnemyEntities[i].setIcon(null);
			enemyLife[i] = enemyLifeNum;
			enemyDead = 0;
		}
		return enemyLocation;
	}
	static Object resizeArray (Object oldArray, int newSize) {
		//resizing arrays, taking in old ones and return new ones
		int oldSize = java.lang.reflect.Array.getLength(oldArray);
		Class elementType = oldArray.getClass().getComponentType();
		Object newArray = java.lang.reflect.Array.newInstance(elementType,newSize);
		int preserveLength = Math.min(oldSize,newSize);
		if (preserveLength > 0)
			System.arraycopy (oldArray, 0,newArray,0,preserveLength);
		return newArray; 
	}
	public Rectangle[] createRange(Rectangle range[], Graphics g){
		//creating range in Rectangle array Range variable 
		rangeCreate = false;
		slotPoint = btnSlot[Integer.parseInt(playerClick.substring(7))].getLocation();
		//switch case testing for type of tower and setting range accordingly
		switch (towerSlot[Integer.parseInt(playerClick.substring(7))]){
		case 1: 
			rangeSize = 144;
			rangePositionX = slotPoint.x - 45;
			rangePositionY = slotPoint.y - 23;
			break;
		case 2: 
			rangeSize = 144;
			rangePositionX = slotPoint.x - 45;
			rangePositionY = slotPoint.y - 23;
			break;
		case 3: 
			rangeSize = 245;
			rangePositionX = slotPoint.x - 95; 
			rangePositionY = slotPoint.y - 73;
			break;
		case 4:	
			rangeSize = 345;
			rangePositionX = slotPoint.x - 145;
			rangePositionY = slotPoint.y - 123;
			break;
		}
		range[Integer.parseInt(playerClick.substring(7))] = new Rectangle (rangePositionX,rangePositionY,rangeSize,rangeSize);
		return range;
	}
	static void showRange(Rectangle range[], Graphics g){
		//drawing range of towers
		g.setColor(Color.BLACK);
		g.drawOval(range[Integer.parseInt(playerClick.substring(7))] .x,range[Integer.parseInt(playerClick.substring(7))] .y,range[Integer.parseInt(playerClick.substring(7))].width,range[Integer.parseInt(playerClick.substring(7))].height);
		showRange = false;
	}
	static void drawShootMethod (Graphics g){
		//drawing shoot lines
		drawShot = false;
		for (int i = 0; i < 42; i++){
			if (towerTargeted[i]==true){
				//different types of towers shoot different colors
				switch (towerSlot[i]){
				case 1:
					g.setColor(Color.BLACK);
					break;
				case 2:
					g.setColor(Color.CYAN);
					break;
				case 3:
					g.setColor(Color.RED);
					break;
				case 4:
					g.setColor(Color.GREEN);
				}
				g.drawLine(shootPointArrayTower[i].x, shootPointArrayTower[i].y ,shootPointArrayEnemy[i].x, shootPointArrayEnemy[i].y);
			}	
		}
		btnRepaint.doClick();
	}
	public void paint(Graphics g) {
		//paint method
		super.paint(g);	
		if (rangeCreate == true)
			range = createRange(range, g);
		if (showRange == true)
			showRange(range, g);
		if (drawShot == true)
			drawShootMethod(g);
	}
}