
package com.CtrlAltPlay.levels;

import com.CtrlAltPlay.characters.Caveman;
import com.CtrlAltPlay.characters.Champion;
import com.CtrlAltPlay.characters.Chieftain;
import com.CtrlAltPlay.objects.Orbs;
import com.CtrlAltPlay.objects.HealthPickup;
import com.CtrlAltPlay.objects.Level1LargePlatform;
import com.CtrlAltPlay.objects.Level1SmallPlatform;
import com.CtrlAltPlay.game.Game;
import com.CtrlAltPlay.objects.Ground;
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
    private Level1LargePlatform[] largePlatforms;
    private Level1SmallPlatform[] smallPlatforms;
    private HealthPickup[] health;
    private Ground ground;
    
    public Level1(Game theGame){
        game = theGame;
        player = new Champion(Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT);
        orbs = new Orbs[5];
        cavemen = new Caveman[22];
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
        
        ground = new Ground(0, 890);
        scrollingBackground1 = new Background(background, player.getX());
        
        cavemen[0] = new Caveman(2880, 778);
        cavemen[1] = new Caveman(3450, 132);
        cavemen[2] = new Caveman(4192, 778);
        cavemen[3] = new Caveman(4672, 778);
        cavemen[4] = new Caveman(5152, 778);
        cavemen[5] = new Caveman(2880, 778);
        cavemen[6] = new Caveman(2880, 778);
        cavemen[7] = new Caveman(2880, 778);
        cavemen[8] = new Caveman(2880, 778);
        cavemen[9] = new Caveman(2880, 778);
        cavemen[10] = new Caveman(2880, 778);
        cavemen[11] = new Caveman(2880, 778);
        cavemen[12] = new Caveman(2880, 778);
        cavemen[13] = new Caveman(2880, 778);
        cavemen[14] = new Caveman(2880, 778);
        cavemen[15] = new Caveman(2880, 778);
        cavemen[16] = new Caveman(2880, 778);
        cavemen[17] = new Caveman(2880, 778);
        cavemen[18] = new Caveman(2880, 778);
        cavemen[19] = new Caveman(2880, 778);
        cavemen[20] = new Caveman(2880, 778);
        cavemen[21] = new Caveman(2880, 778);
        
        orbs[0] = new Orbs(2380, 550);
        orbs[1] = new Orbs(3954, 50);
        orbs[2] = new Orbs(3954, 50);
        orbs[3] = new Orbs(3954, 50);
        orbs[4] = new Orbs(3954, 50);
        
        largePlatforms[0] = new Level1LargePlatform(2254, 650);
        largePlatforms[1] = new Level1LargePlatform(2654, 520);
        largePlatforms[2] = new Level1LargePlatform(3054, 390);
        largePlatforms[3] = new Level1LargePlatform(3454, 260);
        largePlatforms[4] = new Level1LargePlatform(3854, 130);
        
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
        
        ground.draw(g2d, player.getX(), (Game.WINDOW_WIDTH/2));
        
        for(int i = 0; i < orbs.length; i++)
        {
            orbs[i].draw(g2d, player.getX(), (Game.WINDOW_WIDTH/2));
        }
        
        for(int i = 0; i < 5; i++)
        {
            cavemen[i].draw(g2d, player.getX(), (Game.WINDOW_WIDTH/2));
        }
        
        for(int i = 0; i < largePlatforms.length; i++)
        {
            largePlatforms[i].draw(g2d, player.getX(), (Game.WINDOW_WIDTH/2));
        }
        
        player.draw(g2d);
        g.dispose();
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        checkIsOnScreen();
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
        
        if(player.checkCollision(ground) == false)
        {
            player.checkCollision(largePlatforms);
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

    private void checkIsOnScreen() {
        
        for(int i = 0; i < largePlatforms.length; i++){
            if(largePlatforms[i].getPosition().getX() <= player.getX() + 960 && largePlatforms[i].getPosition().getX() + 300 >= player.getX() - 960){
                largePlatforms[i].setIsVisible(true);
            }else{
                largePlatforms[i].setIsVisible(false);
            }
        }
        
        for(int i = 0; i < cavemen.length; i++){
            if(cavemen[i].getPosition().getX() <= player.getX() + 960 && cavemen[i].getPosition().getX() + cavemen[i].getSpriteWidth() >= player.getX() - 960){
                cavemen[i].setIsVisible(true);
            }else{
                cavemen[i].setIsVisible(false);
            }
        }
        
        for(int i = 0; i < orbs.length; i++){
            if(orbs[i].getPosition().getX() <= player.getX() + 960 && orbs[i].getPosition().getX() + orbs[i].getSpriteWidth() >= player.getX() - 960){
                orbs[i].setIsVisible(true);
            }else{
                orbs[i].setIsVisible(false);
            }
        }
        
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
