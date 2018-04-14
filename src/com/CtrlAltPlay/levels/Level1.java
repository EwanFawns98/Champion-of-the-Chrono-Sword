
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
import com.CtrlAltPlay.objects.Level1Wall;
import com.CtrlAltPlay.objects.Portal;
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
    private Champion player;
    private Caveman[] cavemen;
    private Chieftain chieftain;
    private Background scrollingBackground1;
    private Level1LargePlatform[] largePlatforms;
    private Level1SmallPlatform[] smallPlatforms;
    private HealthPickup[] health;
    private Level1Wall[] wall;
    private Ground ground;
    private Portal portal;
    private HUD hud;
    
    
    public Level1(Game theGame, int newHealth, int newLives, int newPlayerOrbs){
        game = theGame;
        player = new Champion(Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT, newHealth, newLives, newPlayerOrbs);
        orbs = new Orbs[6];
        cavemen = new Caveman[22];
        chieftain = new Chieftain(17152, 716);
        largePlatforms = new Level1LargePlatform[5];
        smallPlatforms = new Level1SmallPlatform[21];
        health = new HealthPickup[2];
        portal = new Portal(17152, 762);
        wall = new Level1Wall[2];
        hud = new HUD(player.getHealth(), player.getOrbs(), newLives);
        init();
    }
    
    private void init()
    {
        try{
            background = ImageIO.read(getClass().getResource("/Images/background_1.png"));
        }catch(Exception ex){
            System.out.println("Error loading background image");
        }
        
        ground = new Ground(0, 890);
        scrollingBackground1 = new Background(background, player.getX());
        
        cavemen[0] = new Caveman(2880, 778, 100, false);
        cavemen[1] = new Caveman(3450, 132, 100, true);
        cavemen[2] = new Caveman(4192, 778, 100, true);
        cavemen[3] = new Caveman(4672, 778, 100, true);
        cavemen[4] = new Caveman(5152, 778, 100, false);
        cavemen[5] = new Caveman(6592, 778, 100, true);
        cavemen[6] = new Caveman(7830, 778, 100, true);
        cavemen[7] = new Caveman(9040, 778, 150, true);
        cavemen[8] = new Caveman(9852, 778, 200, false);
        cavemen[9] = new Caveman(11392, 778, 100, true);
        cavemen[10] = new Caveman(11936, 778, 100, true);
        cavemen[11] = new Caveman(12416, 778, 100, true);
        cavemen[12] = new Caveman(12896, 778, 100, true);
        cavemen[13] = new Caveman(11936, 522, 100, false);
        cavemen[14] = new Caveman(12636, 522, 100, false);
        cavemen[15] = new Caveman(13312, 778, 100, true);
        cavemen[16] = new Caveman(7150, 522);
        cavemen[17] = new Caveman(13870, 122, 200, false);
        cavemen[18] = new Caveman(14350, 122, 200, false);
        cavemen[19] = new Caveman(14830, 122, 200, false);
        cavemen[20] = new Caveman(15310, 122, 200, false);
        cavemen[21] = new Caveman(12286, 322);
        
        orbs[0] = new Orbs(2380, 550);
        orbs[1] = new Orbs(3954, 50);
        orbs[2] = new Orbs(5696, 800);
        orbs[3] = new Orbs(7170, 540);
        orbs[4] = new Orbs(7640, 800);
        orbs[5] = new Orbs(12636, 140);
        
        largePlatforms[0] = new Level1LargePlatform(2254, 650);
        largePlatforms[1] = new Level1LargePlatform(2654, 520);
        largePlatforms[2] = new Level1LargePlatform(3054, 390);
        largePlatforms[3] = new Level1LargePlatform(3454, 260);
        largePlatforms[4] = new Level1LargePlatform(3854, 130);
        
        smallPlatforms[0] = new Level1SmallPlatform(7150, 650);
        smallPlatforms[1] = new Level1SmallPlatform(7530, 770);
        smallPlatforms[2] = new Level1SmallPlatform(7630, 670);
        smallPlatforms[3] = new Level1SmallPlatform(7730, 670);
        smallPlatforms[4] = new Level1SmallPlatform(8740, 770);
        smallPlatforms[5] = new Level1SmallPlatform(8840, 670);
        smallPlatforms[6] = new Level1SmallPlatform(8940, 570);
        smallPlatforms[7] = new Level1SmallPlatform(9040, 470);
        smallPlatforms[8] = new Level1SmallPlatform(9190, 470);
        smallPlatforms[9] = new Level1SmallPlatform(9340, 470);
        smallPlatforms[10] = new Level1SmallPlatform(9490, 470);
        smallPlatforms[11] = new Level1SmallPlatform(11936, 650);
        smallPlatforms[12] = new Level1SmallPlatform(12286, 650);
        smallPlatforms[13] = new Level1SmallPlatform(12636, 650);
        smallPlatforms[14] = new Level1SmallPlatform(11936, 250);
        smallPlatforms[15] = new Level1SmallPlatform(12286, 450);
        smallPlatforms[16] = new Level1SmallPlatform(12636, 250);
        smallPlatforms[17] = new Level1SmallPlatform(13870, 250);
        smallPlatforms[18] = new Level1SmallPlatform(14350, 250);
        smallPlatforms[19] = new Level1SmallPlatform(14830, 250);
        smallPlatforms[20] = new Level1SmallPlatform(15310, 250);
        
        health[0] = new HealthPickup(8850,800);
        health[1] = new HealthPickup(11950, 140);
        
        wall[0] = new Level1Wall(-392, 0);
        wall[1] = new Level1Wall(17280, 0);
        
        
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
        g2d.scale(Game.xScaleFactor, Game.yScaleFactor);
        scrollingBackground1.draw(g2d);
        
        
        for(int i = 0; i < orbs.length; i++)
        {
            orbs[i].draw(g2d, player.getX(), (Game.WINDOW_WIDTH/2));
        }
        
        for(int i = 0; i < cavemen.length; i++)
        {
            cavemen[i].draw(g2d, player.getX(), (Game.WINDOW_WIDTH/2));
        }
        
        for(int i = 0; i < largePlatforms.length; i++)
        {
            largePlatforms[i].draw(g2d, player.getX(), (Game.WINDOW_WIDTH/2));
        }
        
        for(int i = 0; i < smallPlatforms.length; i++)
        {
            smallPlatforms[i].draw(g2d, player.getX(), (Game.WINDOW_WIDTH/2));
        }
        
        for(int i = 0; i < health.length; i++)
        {
            health[i].draw(g2d, player.getX(), (Game.WINDOW_WIDTH/2));
        }
        
        for(int i = 0; i < wall.length; i++)
        {
            wall[i].draw(g2d, player.getX(), (Game.WINDOW_WIDTH/2));
        }
        
        portal.draw(g2d, player.getX(), (Game.WINDOW_WIDTH/2));
        
        
        chieftain.draw(g2d, player.getX(), (Game.WINDOW_WIDTH/2));
        
        player.draw(g2d);
        
        hud.draw(g2d);
        
        g.dispose();
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        checkIsOnScreen();
        checkCollisions();
        updateMove();
        checkIsDead();
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
    
    private void checkIsDead()
    {
        if(player.getHealth() == 0 && player.getLives() > 0)
        {
            timer.stop();
            game.deathScreen(1, player.getLives());
        }else if(player.getHealth() == 0 && player.getLives() == 0)
        {
            timer.stop();
            game.gameOverScreen();
        }
    }
    
    private void checkCollisions()
    {
        player.checkCollision(orbs);
        
        player.checkHeadCollision(largePlatforms);
        player.checkHeadCollision(smallPlatforms);
        
        player.checkRightCollision(largePlatforms);
        player.checkRightCollision(smallPlatforms);
        player.checkRightCollision(wall);
        
        player.checkLeftCollision(largePlatforms);
        player.checkLeftCollision(smallPlatforms);
        player.checkLeftCollision(wall);
        
        
        player.checkCollision(health);
        
        if(player.checkCollision(ground) == false)
        {
            if(player.checkCollision(largePlatforms) == false)
            {
                player.checkCollision(smallPlatforms);
            }
        }
        
        player.checkCollsision(cavemen);
        player.checkCollision(chieftain);
        
        for(int i = 0; i < cavemen.length; i++)
        {
            if(cavemen[i].checkCollision(ground) == false)
        {
            if(cavemen[i].checkCollision(largePlatforms) == false)
            {
                cavemen[i].checkCollision(smallPlatforms);
            }
        }
        }
        
        for(int i = 0; i < cavemen.length; i++)
        {
            cavemen[i].checkAttackCollision(player);
        }
        
        chieftain.checkCollision(ground);
        
        if(chieftain.checkAttackCollision(player) == true)
        {
            portal.setBossIsDefeated(true);
        }
        
        if(player.checkCollision(portal) == true)
        {
            game.cutscene2(player.getHealth(), player.getLives(), player.getOrbs());
        }
    }
    
    private void updateMove()
    {
        player.doMove();
        for(int i = 0; i < cavemen.length; i++)
        {
            cavemen[i].doMove();
        }
        
        chieftain.doMove(player.getX());
        
        scrollingBackground1.updateBackground(player.getX());
        hud.updateHud(player.getHealth(), player.getOrbs());
    }

    private void checkIsOnScreen() {
        
        for(int i = 0; i < largePlatforms.length; i++)
        {
            if(largePlatforms[i].getPosition().getX() <= player.getX() + 960 && largePlatforms[i].getPosition().getX() + 300 >= player.getX() - 960){
                largePlatforms[i].setIsVisible(true);
            }else
            {
                largePlatforms[i].setIsVisible(false);
            }
        }
        
        for(int i = 0; i < cavemen.length; i++)
        {
            if(cavemen[i].getPosition().getX() <= player.getX() + 960 && cavemen[i].getPosition().getX() + cavemen[i].getSpriteWidth() >= player.getX() - 960){
                cavemen[i].setIsVisible(true);
            }else
            {
                cavemen[i].setIsVisible(false);
            }
        }
        
        for(int i = 0; i < orbs.length; i++)
        {
            if(orbs[i].getPosition().getX() <= player.getX() + 960 && orbs[i].getPosition().getX() + orbs[i].getSpriteWidth() >= player.getX() - 960){
                orbs[i].setIsVisible(true);
            }else
            {
                orbs[i].setIsVisible(false);
            }
        }
        
        for(int i = 0; i < smallPlatforms.length; i++)
        {
            if(smallPlatforms[i].getPosition().getX() <= player.getX() + 960 && smallPlatforms[i].getPosition().getX() + 100 >= player.getX() - 960){
                smallPlatforms[i].setIsVisible(true);
            }else
            {
                smallPlatforms[i].setIsVisible(false);
            }
        }
        
        for(int i = 0; i < health.length; i++)
        {
            if(health[i].getPosition().getX() <= player.getX() + 960 && health[i].getPosition().getX() + 100 >= player.getX() - 960){
                health[i].setIsVisible(true);
            }else
            {
                health[i].setIsVisible(false);
            }
        }
        
        for(int i = 0; i < wall.length; i++)
        {
            if(wall[i].getPosition().getX() <= player.getX() + 960 && wall[i].getPosition().getX() + 392 >= player.getX() - 960)
        {
            wall[i].setIsVisible(true);
        }else
        {
            wall[i].setIsVisible(false);
        }
        }
        
        if(portal.getPosition().getX() <= player.getX() + 960 && portal.getPosition().getX() + 100 >= player.getX() - 960)
        {
            portal.setIsVisible(true);
        }else
        {
            portal.setIsVisible(false);
        }
        
        if(chieftain.getPosition().getX() <= player.getX() + 960 && chieftain.getPosition().getX() + chieftain.getSpriteWidth() >= player.getX() - 960)
        {
            chieftain.setIsVisible(true);
        }else
        {
            chieftain.setIsVisible(false);
        }
        
    }
    
    private class TAdapter extends KeyAdapter{
        
        private boolean isPressingW = false;
        
        @Override
        public void keyPressed(KeyEvent e){
            int direction = 0;
            switch(e.getKeyCode()){
                case KeyEvent.VK_W: // Jump
                    if(isPressingW == false){
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
                    player.attack();
                    break;
                    
                case MouseEvent.BUTTON3: // ability for right mouse click
                    player.useSwordRift();
                    player.cavemenSwordRift(cavemen);
                    player.resetOrbs();
                    break;
            }
        }
        
    }
}
