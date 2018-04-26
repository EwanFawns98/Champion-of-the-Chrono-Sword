
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
import com.CtrlAltPlay.objects.Spear;
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
    private Spear spear;
    private Background scrollingBackground1;
    private Level2LargePlatform[] largePlatforms;
    private Level2SmallPlatform[] smallPlatforms;
    private HealthPickup[] health;
    private Level2Wall[] wall;
    private Ground ground;
    private Portal portal;
    private HUD hud;
    
    public Level2(Game theGame, int newHealth, int newLives, int newPlayerOrbs){
        //constructer method
        game = theGame;
        player = new Champion(Game.WINDOW_WIDTH, 1500, newHealth, newLives, newPlayerOrbs);
        orbs = new Orbs[5];
        spear = new Spear(-200, -100, false);
        pharaoh = new Pharaoh(17152, 716);
        mummy = new Mummy[28];
        largePlatforms = new Level2LargePlatform[3];
        smallPlatforms = new Level2SmallPlatform[19];
        health = new HealthPickup[3];
        portal = new Portal(17152, 762);
        wall = new Level2Wall[2];
        hud = new HUD(player.getHealth(), player.getOrbs(), newLives);
        init();
    }
    
    private void init()
    {
        //used with constructer to initialise background, objects and enemies
        try{
            background = ImageIO.read(getClass().getResource("/Images/background_2.png"));
        }catch(Exception ex){
            System.out.println("Error loading background image");
        }
        
        ground = new Ground(0, 890);
        scrollingBackground1 = new Background(background, player.getX());
        
        mummy[0] = new Mummy(2220, 778, 100, false);
        mummy[1] = new Mummy(2580, 122, 150, false);
        mummy[2] = new Mummy(2680, 122, 150, true);
        mummy[3] = new Mummy(2780, 122, 100, true);
        mummy[4] = new Mummy(3360, 778, 100, false);
        mummy[5] = new Mummy(4040, 778, 100, false);
        mummy[6] = new Mummy(4800, 778, 100, false);
        mummy[7] = new Mummy(5360, 778, 100, false);
        mummy[8] = new Mummy(5360, 22, 100, true);
        mummy[9] = new Mummy(6570, 562, 75, true);
        mummy[10] = new Mummy(6770, 778, 100, false);
        mummy[11] = new Mummy(7000, 778, 100, false);
        mummy[12] = new Mummy(7400, 778, 100, false);
        mummy[13] = new Mummy(8190, 322, 100, false);
        mummy[14] = new Mummy(8790, 322, 100, false);
        mummy[15] = new Mummy(9390, 322, 100, false);
        mummy[16] = new Mummy(9920, 778, 100, false);
        mummy[17] = new Mummy(10240, 778, 200, false);
        mummy[18] = new Mummy(10560, 778, 200, false);
        mummy[19] = new Mummy(10880, 778, 200, false);
        mummy[20] = new Mummy(11200, 778, 200, false);
        mummy[21] = new Mummy(11520, 778, 100, false);
        mummy[22] = new Mummy(11840, 778, 100, false);
        mummy[23] = new Mummy(12480, 322, 100, false);
        mummy[24] = new Mummy(13140, 562, 75, true);
        mummy[25] = new Mummy(14000, 778, 100, false);
        mummy[26] = new Mummy(14300, 778, 100, false);
        mummy[27] = new Mummy(15000, 778, 100, false);
        
        orbs[0] = new Orbs(3840, 80);
        orbs[1] = new Orbs(6580, 800);
        orbs[2] = new Orbs(7910, 540);
        orbs[3] = new Orbs(8510, 540);
        orbs[4] = new Orbs(9110, 540);
        
        largePlatforms[0] = new Level2LargePlatform(2580, 250);
        largePlatforms[1] = new Level2LargePlatform(5360, 150);
        largePlatforms[2] = new Level2LargePlatform(6570, 660);
        
        smallPlatforms[0] = new Level2SmallPlatform(3840, 150);
        smallPlatforms[1] = new Level2SmallPlatform(3940, 400);
        smallPlatforms[2] = new Level2SmallPlatform(4040, 650);
        smallPlatforms[3] = new Level2SmallPlatform(5160, 650);
        smallPlatforms[4] = new Level2SmallPlatform(5260, 400);
        smallPlatforms[5] = new Level2SmallPlatform(6470, 760);
        smallPlatforms[6] = new Level2SmallPlatform(7890, 650);
        smallPlatforms[7] = new Level2SmallPlatform(8190, 450);
        smallPlatforms[8] = new Level2SmallPlatform(8490, 650);
        smallPlatforms[9] = new Level2SmallPlatform(8790, 450);
        smallPlatforms[10] = new Level2SmallPlatform(9090, 650);
        smallPlatforms[11] = new Level2SmallPlatform(9390, 450);
        smallPlatforms[12] = new Level2SmallPlatform(12480, 450);
        smallPlatforms[13] = new Level2SmallPlatform(13040, 760);
        smallPlatforms[14] = new Level2SmallPlatform(13140, 660);
        smallPlatforms[15] = new Level2SmallPlatform(13240, 660);
        smallPlatforms[16] = new Level2SmallPlatform(14400, 450);
        smallPlatforms[17] = new Level2SmallPlatform(14600, 650);
        smallPlatforms[18] = new Level2SmallPlatform(200, 650);
        
        health[0] = new HealthPickup(200, 580);
        health[1] = new HealthPickup(5560, 80);
        health[2] = new HealthPickup(14420, 380);
        
        wall[0] = new Level2Wall(-392, 0);
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
        //used to draw everything on screen
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.scale(Game.xScaleFactor, Game.yScaleFactor);
        scrollingBackground1.draw(g2d);
        
        
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
        
        pharaoh.draw(g2d, player.getX(), (Game.WINDOW_WIDTH/2));
        
        spear.draw(g2d, player.getX(), (Game.WINDOW_WIDTH/2));
        
        player.draw(g2d);
        
        hud.draw(g2d);
        
        g.dispose();
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        //runs off the swing timer every 10ms
        checkIsOnScreen();
        checkCollisions();
        updateMove();
        checkIsDead();
        repaint();
    }
    
    public void startTimer()
    {
        //used to start the swing timer
        timer.start();
    }
    
    public void stopTimer()
    {
        //used to stop the swing timer
        timer.stop();
    }
    
    private void checkIsDead()
    {
        //used to check if the player is dead and if they are they will be transferred to either the death or game over screen based on lives
        if(player.getHealth() == 0 && player.getLives() > 0)
        {
            timer.stop();
            game.deathScreen(2, player.getLives());
        }else if(player.getHealth() == 0 && player.getLives() == 0)
        {
            timer.stop();
            game.gameOverScreen();
        }
    }
    
    private void checkCollisions()
    {
        //contains all collisions
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
        
        player.checkCollision(mummy);
        player.checkCollision(spear);
        player.checkCollision(pharaoh);
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
        
        if(pharaoh.checkAttackCollision(player) == true)
        {
            portal.setBossIsDefeated(true);
        }
        
        if(player.checkCollision(portal) == true)
        {
            game.cutscene3(player.getHealth(), player.getLives(), player.getOrbs());
        }
    }
    
    private void updateMove()
    {
        //updates characters, objects, background and ui
        player.doMove();
        pharaoh.doMove(player.getX());
        for(int i = 0; i < mummy.length; i++)
        {
            mummy[i].doMove();
        }
        if(pharaoh.getThrowing() == true && pharaoh.getIsAttackingR() == true)
        {
            spear = new Spear(pharaoh.getPosition().getX(), 810, true);
        }else if(pharaoh.getThrowing() == true && pharaoh.getIsAttackingR() == false)
        {
            spear = new Spear(pharaoh.getPosition().getX(), 810, false);
        }
        spear.doMove();
        scrollingBackground1.updateBackground(player.getX());
        hud.updateHud(player.getHealth(), player.getOrbs());
    }

    private void checkIsOnScreen() 
    {
        //checks to see if objects and characters are in sight of the player and if they are they will be drawn. Saves on memory
        for(int i = 0; i < largePlatforms.length; i++)
        {
            if(largePlatforms[i].getPosition().getX() <= player.getX() + 960 && largePlatforms[i].getPosition().getX() + 300 >= player.getX() - 960)
            {
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
        
        if(pharaoh.getPosition().getX() <= player.getX() + 960 && pharaoh.getPosition().getX() + pharaoh.getSpriteWidth() >= player.getX() - 960)
        {
            pharaoh.setIsVisible(true);
        }else
        {
            pharaoh.setIsVisible(false);
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
                    case KeyEvent.VK_ESCAPE: // pause
                        timer.stop();
                        game.Pause(2);
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
                    player.mummySwordRift(mummy);
                    player.resetOrbs();
                    break;
            }
        }
        
    }
}
