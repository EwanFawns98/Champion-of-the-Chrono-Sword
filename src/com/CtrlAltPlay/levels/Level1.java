
package com.CtrlAltPlay.levels;

import com.CtrlAltPlay.characters.Champion;
import com.CtrlAltPlay.characters.Orbs;
import com.CtrlAltPlay.game.Game;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.image.BufferedImage;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Level1 extends JPanel implements ActionListener{

    private Game game;
    private BufferedImage background;
    private Timer timer;
    private Orbs[] orbs;
    Champion player;
    Background scrollingBackground1;
    
    public Level1(Game theGame){
        game = theGame;
        player = new Champion(Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT);
        init();
    }
    
    private void init()
    {
        
        try{
            background = ImageIO.read(getClass().getResource("/Images/Placeholder background.png"));
        }catch(Exception ex){
            System.out.println("Error loading background image");
        }
        
        orbs = new Orbs[1];
        
        orbs[0] = new Orbs(Game.WINDOW_WIDTH, (Game.WINDOW_HEIGHT/2));
        
        scrollingBackground1 = new Background(background, player.getX());
        
        setFocusable(true);
        setDoubleBuffered(true);
        addKeyListener(new TAdapter());
        addMouseListener(new MAdapter());
        timer = new Timer(10, this);
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        scrollingBackground1.draw(g2d);
        orbs[0].draw(g2d, player.getX(), (Game.WINDOW_WIDTH/2));
        player.draw(g2d);
        g.dispose();
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        checkCollisions();
        updateMove();
        checkWinCondition();
        repaint();
    }
    
    
    public void startTimer()
    {
        timer.start();
    }
    
    public void stopTimer()
    {
        timer.stop();
    }
    
    private void checkCollisions()
    {
        
    }
    
    private void checkWinCondition()
    {

    }
    
    private void updateMove()
    {
        player.doMove();
        scrollingBackground1.updateBackground(player.getX());
    }
    
    private class TAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            int direction = 0;
            switch(e.getKeyCode()){
                case KeyEvent.VK_W: // Jump
                    direction = 1;
                    break;
                    
                case KeyEvent.VK_A: // move Left
                    direction = 2;
                    break;
                    
                case KeyEvent.VK_D: // move Right
                    direction = 3;
                    break;
            }
            player.move(direction);
        }
        
        @Override
        public void keyReleased(KeyEvent e){
            switch(e.getKeyCode()){
                case KeyEvent.VK_W: // Jump
                    break;
                    
                case KeyEvent.VK_A: // move Left
                    player.stopX();
                    break;
                    
                case KeyEvent.VK_D: // Move Right
                    player.stopX();
                    break;
            }
        }
    }
    
    private class MAdapter extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e){
            switch(e.getButton()){
                case MouseEvent.BUTTON1: // attacking for left mouse click
                    break;
                    
                case MouseEvent.BUTTON2: // ability for right mouse click
                    break;
            }
        }
        
    }
}
