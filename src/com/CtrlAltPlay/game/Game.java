
package com.CtrlAltPlay.game;

import com.CtrlAltPlay.levels.Level1;
import com.CtrlAltPlay.levels.Level2;
import com.CtrlAltPlay.levels.Level3;
import com.CtrlAltPlay.screens.MainMenu;
import com.CtrlAltPlay.screens.Options;
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
    public static int attack;
    public static int swordRift;
    public static int goLeft;
    public static int goRight;
    public static int jump;
    public static final String TITLE = "Champion Of the Chrono Sword";
    private Level1 level1;
    private Level2 level2;
    private Level3 level3;
    private MainMenu mainMenu;
    private Options options;
    
    public static void main(String[] args) {
        Game game = new Game();
        game.startMainMenu();
    }
    
    public Game(){
        initPanel();
        initControls();
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
    
    private void initControls(){
        attack = KeyEvent.VK_W;
        swordRift = 3;
        goLeft = 0x41;
        goRight = 0x44;
        jump = 0x57;
    }
    
    public void initScreens()
    {
        mainMenu = new MainMenu(this);
        mainMenu.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        options = new Options(this);
        options.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        level1 = new Level1(this);
        level1.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        level2 = new Level2(this);
        level2.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        level3 = new Level3(this);
        level3.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        
                
    }
    
    public void Options()
    {
        window.getContentPane().add(options);
        mainMenu.stopTimer();
        CardLayout c1 = (CardLayout)window.getContentPane().getLayout();
        c1.next(window.getContentPane());
        options.requestFocus();
        options.startTimer();
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
