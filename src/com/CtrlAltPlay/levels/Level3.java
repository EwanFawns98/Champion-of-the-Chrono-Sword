
package com.CtrlAltPlay.levels;

import com.CtrlAltPlay.characters.Champion;
import com.CtrlAltPlay.characters.ShadowKing;
import com.CtrlAltPlay.objects.HealthPickup;
import com.CtrlAltPlay.game.Game;
import com.CtrlAltPlay.objects.Ground;
import com.CtrlAltPlay.objects.Level3Platform;
import com.CtrlAltPlay.objects.Level3Wall;
import com.CtrlAltPlay.objects.ShockWave;
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


public class Level3 extends JPanel implements ActionListener{

    private Game game;
    private BufferedImage background;
    private Timer timer;
    private Champion player;
    private ShadowKing shadowKing;
    private ShockWave shockWave;
    private Background scrollingBackground1;
    private Level3Platform[] smallPlatforms;
    private HealthPickup[] health;
    private Level3Wall[] wall;
    private Ground ground;
    private HUD hud;
    
    public Level3(Game theGame, int newHealth, int newLives, int newPlayerOrbs){
        game = theGame;
        player = new Champion(Game.WINDOW_WIDTH, 1500, newHealth, newLives, newPlayerOrbs);
        shadowKing = new ShadowKing(1720, 600);
        shockWave = new ShockWave(-100, -100, false);
        smallPlatforms = new Level3Platform[5];
        health = new HealthPickup[2];
        hud = new HUD(player.getHealth(), player.getOrbs(), newLives);
        wall = new Level3Wall[2];
        init();
    }
    
    private void init()
    {
        try{
            background = ImageIO.read(getClass().getResource("/Images/background_3.png"));
        }catch(Exception ex){
            System.out.println("Error loading background image");
        }
        
        ground = new Ground(0, 900);
        scrollingBackground1 = new Background(background, player.getX());
        
        
        smallPlatforms[0] = new Level3Platform(300, 650);
        smallPlatforms[1] = new Level3Platform(600, 450);
        smallPlatforms[2] = new Level3Platform(900, 650);
        smallPlatforms[3] = new Level3Platform(1200, 450);
        smallPlatforms[4] = new Level3Platform(1500, 650);
        
        health[0] = new HealthPickup(600, 380);
        health[1] = new HealthPickup(1200, 380);
        
        wall[0] = new Level3Wall(-392, 0);
        wall[1] = new Level3Wall(1920, 0);
        
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
        
        shadowKing.draw(g2d, player.getX(), (Game.WINDOW_WIDTH/2));
        shockWave.draw(g2d, player.getX(), (Game.WINDOW_WIDTH/2));
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
            game.deathScreen(3, player.getLives());
        }else if(player.getHealth() == 0 && player.getLives() == 0)
        {
            timer.stop();
            game.gameOverScreen();
        }
    }
    
    private void checkCollisions()
    {
        
        player.checkHeadCollision(smallPlatforms);
        player.checkRightCollision(wall);
        player.checkLeftCollision(wall);
        
        
        if(player.checkCollision(ground) == false)
        {
                player.checkCollision(smallPlatforms);
        }
        
        player.checkRightCollision(smallPlatforms);
        player.checkLeftCollision(smallPlatforms);
        
        player.checkCollision(health);
        
        player.checkCollision(shadowKing);
        
        player.checkCollision(shockWave);
        
        shadowKing.checkCollision(ground);
        
        shadowKing.checkCollision(wall);
        if(shadowKing.checkAttackCollision(player) == true)
        {
            game.cutscene4();
        }
    }
    
    private void updateMove()
    {
        player.doMove();
        shadowKing.doMove(player.getX());
        if(shadowKing.getThrowing() == true && shadowKing.getIsAttackingR() == true)
        {
            shockWave = new ShockWave(shadowKing.getPosition().getX(), 780, true);
        }else if(shadowKing.getThrowing() == true && shadowKing.getIsAttackingR() == false)
        {
            shockWave = new ShockWave(shadowKing.getPosition().getX(), 780, false);
        }
        shockWave.doMove();
        scrollingBackground1.updateBackground(player.getX());
        hud.updateHud(player.getHealth(), player.getOrbs());
    }

    private void checkIsOnScreen() {
        

        
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
                    case KeyEvent.VK_ESCAPE:
                        timer.stop();
                        game.Pause(3);
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
                    player.resetOrbs();
                    break;
            }
        }
        
    }
}
