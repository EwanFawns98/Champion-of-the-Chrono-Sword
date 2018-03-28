
package com.CtrlAltPlay.levels;

import com.CtrlAltPlay.characters.Mummy;
import com.CtrlAltPlay.characters.Champion;
import com.CtrlAltPlay.characters.Pharaoh;
import com.CtrlAltPlay.objects.Orbs;
import com.CtrlAltPlay.objects.HealthPickup;
import com.CtrlAltPlay.game.Game;
import com.CtrlAltPlay.objects.Ground;
import com.CtrlAltPlay.objects.Level2LargePlatform;
import com.CtrlAltPlay.objects.Level2SmallPlatform;
import com.CtrlAltPlay.objects.Level2Wall;
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


public class Level2 extends JPanel implements ActionListener{

    private Game game;
    private BufferedImage background;
    private Timer timer;
    private Orbs[] orbs;
    private Champion player;
    private Mummy[] mummy;
    private Pharaoh pharaoh;
    private Background scrollingBackground1;
    private Level2LargePlatform[] largePlatforms;
    private Level2SmallPlatform[] smallPlatforms;
    private HealthPickup[] health;
    private Level2Wall[] wall;
    private Ground ground;
    private Portal portal;
    
    public Level2(Game theGame){
        game = theGame;
        player = new Champion(Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT);
        orbs = new Orbs[5];
        mummy = new Mummy[22];
        largePlatforms = new Level2LargePlatform[5];
        smallPlatforms = new Level2SmallPlatform[21];
        health = new HealthPickup[2];
        portal = new Portal(17152, 762);
        wall = new Level2Wall[2];
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
        
        mummy[0] = new Mummy(2880, 778, 100, false);
        mummy[1] = new Mummy(3450, 132, 100, true);
        mummy[2] = new Mummy(4192, 778, 100, true);
        mummy[3] = new Mummy(4672, 778, 100, true);
        mummy[4] = new Mummy(5152, 778, 100, false);
        mummy[5] = new Mummy(6592, 778, 100, true);
        mummy[6] = new Mummy(7830, 778, 100, true);
        mummy[7] = new Mummy(9040, 778, 150, true);
        mummy[8] = new Mummy(9852, 778, 200, false);
        mummy[9] = new Mummy(11392, 778, 100, true);
        mummy[10] = new Mummy(11936, 778, 100, true);
        mummy[11] = new Mummy(12416, 778, 100, true);
        mummy[12] = new Mummy(12896, 778, 100, true);
        mummy[13] = new Mummy(11936, 522, 100, false);
        mummy[14] = new Mummy(12636, 522, 100, false);
        mummy[15] = new Mummy(13312, 778, 100, true);
        mummy[16] = new Mummy(7150, 522);
        mummy[17] = new Mummy(13870, 122, 200, false);
        mummy[18] = new Mummy(14350, 122, 200, false);
        mummy[19] = new Mummy(14830, 122, 200, false);
        mummy[20] = new Mummy(15310, 122, 200, false);
        mummy[21] = new Mummy(12286, 322);
        
        orbs[0] = new Orbs(2380, 550);
        orbs[1] = new Orbs(3954, 50);
        orbs[2] = new Orbs(5696, 800);
        orbs[3] = new Orbs(7170, 540);
        orbs[4] = new Orbs(7640, 800);
        
        largePlatforms[0] = new Level2LargePlatform(2254, 650);
        largePlatforms[1] = new Level2LargePlatform(2654, 520);
        largePlatforms[2] = new Level2LargePlatform(3054, 390);
        largePlatforms[3] = new Level2LargePlatform(3454, 260);
        largePlatforms[4] = new Level2LargePlatform(3854, 130);
        
        smallPlatforms[0] = new Level2SmallPlatform(7150, 650);
        smallPlatforms[1] = new Level2SmallPlatform(7530, 790);
        smallPlatforms[2] = new Level2SmallPlatform(7630, 690);
        smallPlatforms[3] = new Level2SmallPlatform(7730, 690);
        smallPlatforms[4] = new Level2SmallPlatform(8740, 790);
        smallPlatforms[5] = new Level2SmallPlatform(8840, 690);
        smallPlatforms[6] = new Level2SmallPlatform(8940, 590);
        smallPlatforms[7] = new Level2SmallPlatform(9040, 490);
        smallPlatforms[8] = new Level2SmallPlatform(9190, 490);
        smallPlatforms[9] = new Level2SmallPlatform(9340, 490);
        smallPlatforms[10] = new Level2SmallPlatform(9490, 490);
        smallPlatforms[11] = new Level2SmallPlatform(11936, 650);
        smallPlatforms[12] = new Level2SmallPlatform(12286, 650);
        smallPlatforms[13] = new Level2SmallPlatform(12636, 650);
        smallPlatforms[14] = new Level2SmallPlatform(11936, 250);
        smallPlatforms[15] = new Level2SmallPlatform(12286, 450);
        smallPlatforms[16] = new Level2SmallPlatform(12636, 250);
        smallPlatforms[17] = new Level2SmallPlatform(13870, 250);
        smallPlatforms[18] = new Level2SmallPlatform(14350, 250);
        smallPlatforms[19] = new Level2SmallPlatform(14830, 250);
        smallPlatforms[20] = new Level2SmallPlatform(15310, 250);
        
        health[0] = new HealthPickup(8850,800);
        health[1] = new HealthPickup(11950, 140);
        
        wall[0] = new Level2Wall(-100, 0);
        wall[1] = new Level2Wall(17280, 0);
        
        
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
        
        ground.draw(g2d, player.getX(), (Game.WINDOW_WIDTH/2));
        
        for(int i = 0; i < orbs.length; i++)
        {
            orbs[i].draw(g2d, player.getX(), (Game.WINDOW_WIDTH/2));
        }
        
        for(int i = 0; i < mummy.length; i++)
        {
            mummy[i].draw(g2d, player.getX(), (Game.WINDOW_WIDTH/2));
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
        
        player.draw(g2d);
        
        
        g.dispose();
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        checkIsOnScreen();
        checkCollisions();
        updateMove();
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
        
        player.checkCollsision(mummy);
        
        for(int i = 0; i < mummy.length; i++)
        {
            if(mummy[i].checkCollision(ground) == false)
            {
                if(mummy[i].checkCollision(largePlatforms) == false)
                {
                    mummy[i].checkCollision(smallPlatforms);
                }
            }
        }
        
        for(int i = 0; i < mummy.length; i++)
        {
            mummy[i].checkAttackCollision(player);
        }
        
        if(player.checkCollision(portal) == true)
        {
            game.startLevel3();
        }
    }
    
    private void updateMove()
    {
        player.doMove();
        for(int i = 0; i < mummy.length; i++)
        {
            mummy[i].doMove();
        }
        scrollingBackground1.updateBackground(player.getX());
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
        
        for(int i = 0; i < mummy.length; i++)
        {
            if(mummy[i].getPosition().getX() <= player.getX() + 960 && mummy[i].getPosition().getX() + mummy[i].getSpriteWidth() >= player.getX() - 960){
                mummy[i].setIsVisible(true);
            }else
            {
                mummy[i].setIsVisible(false);
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
            if(wall[i].getPosition().getX() <= player.getX() + 960 && wall[i].getPosition().getX() + 100 >= player.getX() - 960)
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
                    player.attack();
                    break;
                    
                case MouseEvent.BUTTON3: // ability for right mouse click
                    player.mummySwordRift(mummy);
                    player.resetOrbs();
                    break;
            }
        }
        
    }
}