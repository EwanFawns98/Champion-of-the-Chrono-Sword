
package com.CtrlAltPlay.game;

import com.CtrlAltPlay.levels.Level1;
import com.CtrlAltPlay.levels.Level2;
import com.CtrlAltPlay.levels.Level3;
import com.CtrlAltPlay.screens.MainMenu;
import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
public class Game {
    
    JFrame window;
    public static final int WINDOW_WIDTH = 1920;
    public static final int WINDOW_HEIGHT = 1080;
    public static double xScaleFactor = 1; // Used for passing a value into the levels to scale them down for different resolutions
    public static double yScaleFactor = 1; // Used for passing a value into the levels to scale them down for different resolutions
    public static final String TITLE = "Champion Of the Chrono Sword";
    private Level1 level1;
    private Level2 level2;
    private Level3 level3;
    private MainMenu mainMenu;
    
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
    
    private void initScreens()
    {
        mainMenu = new MainMenu(this);
        mainMenu.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        level1 = new Level1(this);
        level1.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        level2 = new Level2(this);
        level2.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        level3 = new Level3(this);
        level3.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        
                
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
    
    public void startGame()
    {
        window.getContentPane().add(level1);
        mainMenu.stopTimer();
        level1.startTimer();
        CardLayout c1 = (CardLayout)window.getContentPane().getLayout();
        c1.next(window.getContentPane());
        level1.requestFocus();
        window.setVisible(true);
    }
    
    public void startLevel2()
    {
        window.getContentPane().add(level2);
        level1.stopTimer();
        level2.startTimer();
        CardLayout c1 = (CardLayout)window.getContentPane().getLayout();
        c1.next(window.getContentPane());
        level2.requestFocus();
        window.setVisible(true);
    }
    
    public void startLevel3()
    {
        window.getContentPane().add(level3);
        level2.stopTimer();
        level3.startTimer();
        CardLayout c1 = (CardLayout)window.getContentPane().getLayout();
        c1.next(window.getContentPane());
        level3.requestFocus();
        window.setVisible(true);
    }
    
}
