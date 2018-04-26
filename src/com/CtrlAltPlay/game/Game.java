
package com.CtrlAltPlay.game;

import com.CtrlAltPlay.levels.Level1;
import com.CtrlAltPlay.levels.Level2;
import com.CtrlAltPlay.levels.Level3;
import com.CtrlAltPlay.screens.Controls;
import com.CtrlAltPlay.screens.Cutscene1;
import com.CtrlAltPlay.screens.Cutscene2;
import com.CtrlAltPlay.screens.Cutscene3;
import com.CtrlAltPlay.screens.Cutscene4;
import com.CtrlAltPlay.screens.Death;
import com.CtrlAltPlay.screens.GameOver;
import com.CtrlAltPlay.screens.MainMenu;
import com.CtrlAltPlay.screens.Options;
import com.CtrlAltPlay.screens.Pause;
import com.CtrlAltPlay.screens.Tutorial;
import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.event.KeyEvent;
public class Game {
    
    JFrame window;
    public static final int WINDOW_WIDTH = 1920;
    public static final int WINDOW_HEIGHT = 1080;
    public static double xScaleFactor = 0.666666666; // Used for passing a value into the levels to scale them down for different resolutions
    public static double yScaleFactor = 0.9481481481; // Used for passing a value into the levels to scale them down for different resolutions
    public static final String TITLE = "Champion Of the Chrono Sword";
    private Level1 level1;
    private Level2 level2;
    private Level3 level3;
    private MainMenu mainMenu;
    private Controls controls;
    private Tutorial tutorial;
    private Options options;
    private GameOver gameOver;
    private Death death;
    private Cutscene1 cutscene1;
    private Cutscene2 cutscene2;
    private Cutscene3 cutscene3;
    private Cutscene4 cutscene4;
    private Pause pause;
    public static boolean musicIsPlaying = false;
    public static float gain = 0; // used for the level of the sound
    
    public static void main(String[] args) {
        //main method
        Game game = new Game();
        game.startMainMenu();
    }
    
    public Game(){
        //constructer method
        initPanel();
        initScreens();
    }
    
    
    private void initPanel(){
        //used to initiate the JFrame
        window = new JFrame();
        window.setSize(WINDOW_WIDTH, WINDOW_WIDTH);
        window.setTitle(TITLE);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setUndecorated(true);
        window.getContentPane().setLayout(new CardLayout());
    }
    
    public void initScreens()
    {
        //used to initiate the main menus
        mainMenu = new MainMenu(this);
        mainMenu.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        options = new Options(this);
        options.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        controls = new Controls(this);
        controls.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        tutorial = new Tutorial(this);
        tutorial.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        gameOver = new GameOver(this);
        gameOver.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        
    }
    
    public void Pause(int level)
    {
        //used to create and display the pause screen for levels
        pause = new Pause(this, level);
        pause.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        window.getContentPane().add(pause);
        CardLayout c1 = (CardLayout)window.getContentPane().getLayout();
        c1.next(window.getContentPane());
        pause.requestFocus();
        window.setVisible(true);
    }
    
    public void options()
    {
        //used to display the options screen
        mainMenu.stopTimer();
        window.getContentPane().add(options);
        CardLayout c1 = (CardLayout)window.getContentPane().getLayout();
        c1.next(window.getContentPane());
        options.requestFocus();
        options.startTimer();
        window.setVisible(true);
    }
    
    public void tutorial()
    {
        //used to display the tutorial screen
        window.getContentPane().add(tutorial);
        mainMenu.stopTimer();
        CardLayout c1 = (CardLayout)window.getContentPane().getLayout();
        c1.next(window.getContentPane());
        tutorial.requestFocus();
        tutorial.startTimer();
        window.setVisible(true);
    }   
    
    public void controls()
    {
        //used to display the controls screen
        window.getContentPane().add(controls);
        mainMenu.stopTimer();
        CardLayout c1 = (CardLayout)window.getContentPane().getLayout();
        c1.next(window.getContentPane());
        controls.requestFocus();
        controls.startTimer();
        window.setVisible(true);
    }
    
    public void startMainMenu()
    {
        //used to display the main menu
        window.getContentPane().add(mainMenu);
        CardLayout c1 = (CardLayout)window.getContentPane().getLayout();
        c1.next(window.getContentPane());
        mainMenu.requestFocus();
        mainMenu.startTimer();
        window.setVisible(true);
    }
    
    public void deathScreen(int level, int lives)
    {
        //used to create and display the death screen upon death
        death = new Death(this, level, lives);
        death.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        window.getContentPane().add(death);
        CardLayout c1 = (CardLayout)window.getContentPane().getLayout();
        c1.next(window.getContentPane());
        death.requestFocus();
        window.setVisible(true);
    }
    
    public void cutscene1()
    {
        //used to create and run the first cutscene
        mainMenu.stopTimer();
        cutscene1 = new Cutscene1(this);
        gameOver.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        window.getContentPane().add(cutscene1);
        CardLayout c1 = (CardLayout)window.getContentPane().getLayout();
        c1.next(window.getContentPane());
        cutscene1.requestFocus();
        cutscene1.startTimer();
        window.setVisible(true);
    }
    
    public void cutscene2(int health, int lives, int orbs)
    {
        //used to create and run the second cutscene
        level1.stopTimer();
        cutscene2 = new Cutscene2(this, health, lives, orbs);
        cutscene2.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        window.getContentPane().add(cutscene2);
        CardLayout c1 = (CardLayout)window.getContentPane().getLayout();
        c1.next(window.getContentPane());
        cutscene2.requestFocus();
        cutscene2.startTimer();
        window.setVisible(true);
    }
    
    public void cutscene3(int health, int lives, int orbs)
    {
        //used to create and run the third cutscene
        level2.stopTimer();
        cutscene3 = new Cutscene3(this, health, lives, orbs);
        cutscene3.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        window.getContentPane().add(cutscene3);
        CardLayout c1 = (CardLayout)window.getContentPane().getLayout();
        c1.next(window.getContentPane());
        cutscene3.requestFocus();
        cutscene3.startTimer();
        window.setVisible(true);
    }
    
    public void cutscene4()
    {
        //used to create and run the fourth cutscene
        level3.stopTimer();
        cutscene4 = new Cutscene4(this);
        gameOver.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        window.getContentPane().add(cutscene4);
        CardLayout c1 = (CardLayout)window.getContentPane().getLayout();
        c1.next(window.getContentPane());
        cutscene4.requestFocus();
        cutscene4.startTimer();
        window.setVisible(true);
    }
    
    public void gameOverScreen()
    {
        //used to show the game over screen
        window.getContentPane().add(gameOver);
        CardLayout c1 = (CardLayout)window.getContentPane().getLayout();
        c1.next(window.getContentPane());
        gameOver.requestFocus();
        window.setVisible(true);
    }
    
    
    public void startGame(int health, int lives, int orbs)
    {
        //used to create and display level 1
        level1 = new Level1(this, health, lives, orbs);
        level1.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        window.getContentPane().add(level1);
        level1.startTimer();
        CardLayout c1 = (CardLayout)window.getContentPane().getLayout();
        c1.next(window.getContentPane());
        level1.requestFocus();
        window.setVisible(true);
    }
    
    public void startLevel2(int health, int lives, int orbs)
    {
        //used to create and display level 2
        level2 = new Level2(this, health, lives, orbs);
        level2.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        window.getContentPane().add(level2);
        level2.startTimer();
        CardLayout c1 = (CardLayout)window.getContentPane().getLayout();
        c1.next(window.getContentPane());
        level2.requestFocus();
        window.setVisible(true);
    }
    
    public void startLevel3(int health, int lives, int orbs)
    {
        //used to create and display level 3
        level3 = new Level3(this, health, lives, orbs);
        level3.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        window.getContentPane().add(level3);
        level3.startTimer();
        CardLayout c1 = (CardLayout)window.getContentPane().getLayout();
        c1.next(window.getContentPane());
        level3.requestFocus();
        window.setVisible(true);
    }
    
    public void resumeLevel1()
    {
        //used resume level 1 after pausing
        level1.startTimer();
        window.getContentPane().add(level1);
        CardLayout c1 = (CardLayout)window.getContentPane().getLayout();
        c1.next(window.getContentPane());
        level1.requestFocus();
        window.setVisible(true);
    }
    
    public void resumeLevel2()
    {
        //used resume level 2 after pausing
        level2.startTimer();
        window.getContentPane().add(level2);
        CardLayout c1 = (CardLayout)window.getContentPane().getLayout();
        c1.next(window.getContentPane());
        level2.requestFocus();
        window.setVisible(true);
    }
    
    public void resumeLevel3()
    {
        //used resume level 3 after pausing
        level3.startTimer();
        window.getContentPane().add(level3);
        CardLayout c1 = (CardLayout)window.getContentPane().getLayout();
        c1.next(window.getContentPane());
        level3.requestFocus();
        window.setVisible(true);
    }
    
}
