
package com.CtrlAltPlay.levels;

import com.CtrlAltPlay.characters.Caveman;
import com.CtrlAltPlay.characters.Champion;
import com.CtrlAltPlay.characters.Chieftain;
import com.CtrlAltPlay.objects.Orbs;
import com.CtrlAltPlay.objects.HealthPickup;
import com.CtrlAltPlay.objects.Level1LargePlatform;
import com.CtrlAltPlay.objects.Level1SmallPlatform;
import com.CtrlAltPlay.game.Game;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
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
    private Champion player;
    private Caveman[] cavemen;
    private Chieftain chieftain;
    private Background scrollingBackground1;
    private Rectangle ground;
    private Level1LargePlatform[] largePlatforms;
    private Level1SmallPlatform[] smallPlatforms;
    private HealthPickup[] health;
    
    
    public Level1(Game theGame){
        game = theGame;
        player = new Champion(Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT);
        orbs = new Orbs[1];
        orbs[0] = new Orbs(Game.WINDOW_WIDTH, (Game.WINDOW_HEIGHT/2));
        cavemen = new Caveman[1];
        cavemen[0] = new Caveman(1700, 778);
        largePlatforms = new Level1LargePlatform[5];
        health = new HealthPickup[2];
        init();
    }
    
    private void init()
    {
        
        try{
            background = ImageIO.read(getClass().getResource("/Images/Placeholder background.png"));
        }catch(Exception ex){
            System.out.println("Error loading background image");
        }
        
        
        ground = new Rectangle(0, 890, 17280, 190);
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
        g2d.fillRect((0 - (player.getX() - Game.WINDOW_WIDTH/2)), 890, 17280, 190);
        orbs[0].draw(g2d, player.getX(), (Game.WINDOW_WIDTH/2));
        cavemen[0].draw(g2d, player.getX(), (Game.WINDOW_WIDTH/2));
        player.draw(g2d);
        g.dispose();
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        checkCollisions();
        updateMove();
        //checkWinCondition();
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
        player.checkCollision(orbs);
        if(player.getBounds().intersects(ground))
        {
            player.setIsFalling(false);
            if(player.getHasTakenDamge() == true){
                player.setHasTakenDamge(false);
                player.stopX();
            }
        }else
        {
            player.setIsFalling(true);
        }
        player.checkCollsision(cavemen);
        
        
    }
    
    private void checkWinCondition()
    {
        game.startLevel2();
    }
    
    private void updateMove()
    {
        player.doMove();
        scrollingBackground1.updateBackground(player.getX());
    }
    
    private class TAdapter extends KeyAdapter{
        
        private boolean isPressingW = false;
        
        @Override
        public void keyPressed(KeyEvent e){
            int direction = 0;
            switch(e.getKeyCode()){
                case KeyEvent.VK_W: // Jump
                    if(isPressingW == false && player.getIsFalling() == false){
                        direction = 1;
                    }
                    isPressingW = true;
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
                    isPressingW = false;
                    break;
                    
                case KeyEvent.VK_A: // move Left
                    if(player.getIsMovingR() == false)
                    {
                       player.stopX(); 
                    }
                    break;
                    
                case KeyEvent.VK_D: // Move Right
                    if(player.getIsMovingL() == false)
                    {
                       player.stopX(); 
                    }
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
