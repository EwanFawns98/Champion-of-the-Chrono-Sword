
package com.CtrlAltPlay.screens;

import com.CtrlAltPlay.characters.Caveman;
import com.CtrlAltPlay.characters.Champion;
import com.CtrlAltPlay.game.Game;
import com.CtrlAltPlay.levels.Vector;
import com.CtrlAltPlay.objects.Ground;
import com.CtrlAltPlay.objects.Orbs;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Tutorial extends JPanel implements ActionListener{

    private Game game;
    private BufferedImage background;
    private Timer timer;
    private Champion moveChamp;
    private Champion damageChamp;
    private Champion attackChamp;
    private Champion swordRiftChamp;
    private Caveman[] caveman;
    private Orbs[] orb;
    private int moveTimer;
    private int damageTimer;
    private int attackTimer;
    private int swordRiftTimer;
    private Ground ground;
    private boolean isOnPickup;
    private boolean isOnAttacking;
    private boolean isOnDamage;
    private boolean isOnSwordRift;
            
    public Tutorial(Game theGame){
        game = theGame;
        ground = new Ground(0, 653);
        moveChamp = new Champion(1500, 1080, 5, 3, 0);
        damageChamp = new Champion(2000, 1080, 5, 3, 0);
        attackChamp = new Champion(2000, 1080, 5, 3, 0);
        swordRiftChamp = new Champion(2000, 1080, 5, 3, 0);
        caveman = new Caveman[4];
        orb = new Orbs[1];
        init();
    }
    
    private void init()
    {
        
        try{
            background = ImageIO.read(getClass().getResource("/Images/blackBackground.png"));
        }catch(Exception ex){
            System.out.println("Error loading background image");
        }
        
        isOnPickup = true;
        isOnAttacking = false;
        isOnDamage = false;
        isOnSwordRift = false;
        
        moveTimer = 0;
        damageTimer = 0;
        attackTimer = 0;
        swordRiftTimer = 0;
        
        caveman[0] = new Caveman(1090, 540);
        caveman[1] = new Caveman(1150, 540, 50, false);
        caveman[2] = new Caveman(1090, 540);
        caveman[3] = new Caveman(900, 540);
        
        orb[0] = new Orbs(1100, 570);
        
        swordRiftChamp.setOrbs(5);
        
        setFocusable(true);
        setDoubleBuffered(true);
        addMouseListener(new MAdapter());
        timer = new Timer(10, this);
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        Font font = new Font("Arial", Font.PLAIN, 40);
        Font fontSmall = new Font("Arial", Font.PLAIN, 25);
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.scale(Game.xScaleFactor, Game.yScaleFactor);
        g2d.setFont(font);
        
        g2d.drawImage(background, 0, 0, null);
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 200, 500, 100);
        g2d.fillRect(0, 400, 500, 100);
        g2d.fillRect(0, 600, 500, 100);
        g2d.fillRect(0, 800, 500, 100);
        g2d.fillRect(0, 1000, 500, 100);
        g2d.fillRect(700, 200, 800, 700);
        
        
        g2d.setColor(Color.WHITE);
        g2d.drawString("picking up objects", 90, 265);
        g2d.drawString("Attacking enemies", 90, 465);
        g2d.drawString("Taking Damage", 100, 665);
        g2d.drawString("Use sword rift on enemies", 30, 865);
        g2d.drawString("Return to main menu", 100, 1065);
        
        
        g2d.setFont(fontSmall);
        if(isOnPickup == true)
        {
            moveChamp.drawForMenu(g2d);
            orb[0].drawForMenu(g2d);
            g2d.drawString("Walk over an item to pick it up", 950, 800);
        }
        
        if(isOnDamage == true)
        {
            damageChamp.drawForMenu(g2d);
            caveman[1].drawForMenus(g2d);
            g2d.drawString("Touching an enemy will cause you", 900, 800);
            g2d.drawString("to take damage and get knocked back", 880, 830);
        }
        
        if(isOnAttacking == true)
        {
            attackChamp.drawForMenu(g2d);
            caveman[0].drawForMenus(g2d);
            g2d.drawString("Attacking enemies will kill them", 975, 800);
        }
        
        if(isOnSwordRift == true)
        {
            swordRiftChamp.drawForMenu(g2d);
            caveman[2].drawForMenus(g2d);
            caveman[3].drawForMenus(g2d);
            g2d.drawString("When you have 5 orbs you can use", 900, 800);
            g2d.drawString("sword rift to vanquish all enemies on screen", 850, 830);
        }
        g.dispose();
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        checkCollision();
        updateMove();
        resetAnimations();
        repaint();
    }
    
    private void checkCollision()
    {
        damageChamp.checkCollision(ground);
        damageChamp.checkCollsision(caveman);
        moveChamp.checkCollision(orb);
    }
    
    public void startTimer()
    {
        timer.start();
    }
    
    public void stopTimer()
    {
        timer.stop();
    }
    
    
    private void updateMove()
    {
        if(isOnPickup == true)
        {
            moveChamp.move(3);
            moveTimer++;
        }else
        {
            moveChamp.stopX();
        }
        
        if(isOnDamage == true)
        {
            damageTimer++;
        }
        
        if(isOnAttacking == true)
        {
            if(attackTimer == 50)
            {
                caveman[0].setIsAlive(false);
                attackChamp.attack();
            }
            attackTimer++;
        }
        
        if(isOnSwordRift == true)
        {
            if(swordRiftTimer == 50)
            {
                swordRiftChamp.useSwordRift();
                caveman[2].setIsAlive(false);
                caveman[3].setIsAlive(false);
            }
            swordRiftTimer++;
        }
        
        
        moveChamp.doMove();
        attackChamp.doMove();
        swordRiftChamp.doMove();
        
        if(isOnDamage == true)
        {
            caveman[1].doMove();
            damageChamp.doMove();
        }
    }
    
    private void resetAnimations()
    {
        
        if(moveTimer == 125)
        {
            orb[0].setIsCollected(false);
            moveChamp.setX(750);
            moveChamp.setY(540);
            moveTimer = 0;
        }
        
        if(damageTimer == 100)
        {
            damageChamp.setPosition(new Vector(1000, 540));
            damageChamp.setHealth(5);
            damageTimer = 0;
        }
        
        if(attackTimer == 100)
        {
            caveman[0].setIsAlive(true);
            attackTimer = 0;
        }
        
        if(swordRiftTimer == 150)
        {
            swordRiftTimer = 0;
            caveman[2].setIsAlive(true);
            caveman[3].setIsAlive(true);
        }
    }
    
    private class MAdapter extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e){
            switch(e.getButton()){
                case MouseEvent.BUTTON1:
                    if(e.getX() <= (500 * Game.xScaleFactor) && e.getX() >= (0 * Game.xScaleFactor) && e.getY() >= (200 * Game.yScaleFactor) && e.getY() <= (300 * Game.yScaleFactor))
                    {
                        isOnPickup = true;
                        isOnAttacking = false;
                        isOnDamage = false;
                        isOnSwordRift = false;
                    }
                    
                    if(e.getX() <= (500 * Game.xScaleFactor) && e.getX() >= (0 * Game.xScaleFactor) && e.getY() >= (400 * Game.yScaleFactor) && e.getY() <= (500 * Game.yScaleFactor))
                    {
                        isOnPickup = false;
                        isOnAttacking = true;
                        isOnDamage = false;
                        isOnSwordRift = false;
                    }
                    
                    if(e.getX() <= (500 * Game.xScaleFactor) && e.getX() >= (0 * Game.xScaleFactor) && e.getY() >= (600 * Game.yScaleFactor) && e.getY() <= (700 * Game.yScaleFactor))
                    {
                        isOnPickup = false;
                        isOnAttacking = false;
                        isOnDamage = true;
                        isOnSwordRift = false;
                    }
                    
                    if(e.getX() <= (500 * Game.xScaleFactor) && e.getX() >= (0 * Game.xScaleFactor) && e.getY() >= (800 * Game.yScaleFactor) && e.getY() <= (900 * Game.yScaleFactor))
                    {
                        isOnPickup = false;
                        isOnAttacking = false;
                        isOnDamage = false;
                        isOnSwordRift = true;
                    }
                    
                    if(e.getX() <= (500 * Game.xScaleFactor) && e.getX() >= (0 * Game.xScaleFactor) && e.getY() >= (1000 * Game.yScaleFactor) && e.getY() <= (1100 * Game.yScaleFactor))
                    {
                        game.startMainMenu();
                        timer.stop();
                    }
                    break;
                    
            }
        }
        
    }
}