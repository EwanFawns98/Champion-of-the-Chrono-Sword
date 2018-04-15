
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
import com.CtrlAltPlay.screens.Tutorial;
import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.event.KeyEvent;
public class Game {
    
    JFrame window;
    public static final int WINDOW_WIDTH = 1920;
    public static final int WINDOW_HEIGHT = 1080;
    public static double xScaleFactor = 0.70833333; // Used for passing a value into the levels to scale them down for different resolutions
    public static double yScaleFactor = 0.7111111; // Used for passing a value into the levels to scale them down for different resolutions
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
    public static boolean musicIsPlaying = false;
    public static float gain = 0;
    
    public static void main(String[] args) {
        Game game = new Game();
        game.startMainMenu();
    }
    
    public Game(){
        initPanel();
        initScreens();
    }
    
    
    private void initPanel(){
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
    
    public void options()
    {
        window.getContentPane().add(options);
        mainMenu.stopTimer();
        CardLayout c1 = (CardLayout)window.getContentPane().getLayout();
        c1.next(window.getContentPane());
        options.requestFocus();
        options.startTimer();
        window.setVisible(true);
    }
    
    public void tutorial()
    {
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
        window.getContentPane().add(mainMenu);
        CardLayout c1 = (CardLayout)window.getContentPane().getLayout();
        c1.next(window.getContentPane());
        mainMenu.requestFocus();
        mainMenu.startTimer();
        window.setVisible(true);
    }
    
    public void deathScreen(int level, int lives)
    {
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
        window.getContentPane().add(gameOver);
        CardLayout c1 = (CardLayout)window.getContentPane().getLayout();
        c1.next(window.getContentPane());
        gameOver.requestFocus();
        window.setVisible(true);
    }
    
    
    public void startGame(int health, int lives, int orbs)
    {
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
        level3 = new Level3(this, health, lives, orbs);
        level3.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        window.getContentPane().add(level3);
        level3.startTimer();
        CardLayout c1 = (CardLayout)window.getContentPane().getLayout();
        c1.next(window.getContentPane());
        level3.requestFocus();
        window.setVisible(true);
    }
    
}
