
package com.CtrlAltPlay.game;

import com.CtrlAltPlay.levels.Level1;
import com.CtrlAltPlay.levels.Level2;
import com.CtrlAltPlay.levels.Level3;
import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
public class Game {
    
    JFrame window;
    public static final int WINDOW_WIDTH = 1920;
    public static final int WINDOW_HEIGHT = 1080;
    public static final String TITLE = "Champion Of the Chrono Sword";
    private Level1 level1;
    private Level2 level2;
    private Level3 level3;
    
    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
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
    
    private void initScreens(){
        level1 = new Level1(this);
        level1.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        level2 = new Level2(this);
        level2.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        level3 = new Level3(this);
        level3.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        
        
        window.getContentPane().add(level1);
    }
    
    public void startGame(){
        CardLayout c1 = (CardLayout)window.getContentPane().getLayout();
        c1.next(window.getContentPane());
        level1.requestFocus();
        level1.startTimer();
        window.setVisible(true);
    }
    
    public void startLevel2(){
        level1.stopTimer();
        CardLayout c1 = (CardLayout)window.getContentPane().getLayout();
        c1.next(window.getContentPane());
        level2.requestFocus();
        level2.startTimer();
        window.setVisible(true);
    }
    
    public void startLevel3(){
        level2.stopTimer();
        CardLayout c1 = (CardLayout)window.getContentPane().getLayout();
        c1.next(window.getContentPane());
        level3.requestFocus();
        level3.startTimer();
        window.setVisible(true);
    }
    
}
