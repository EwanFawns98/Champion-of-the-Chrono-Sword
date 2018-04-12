
package com.CtrlAltPlay.screens;

import com.CtrlAltPlay.characters.Champion;
import com.CtrlAltPlay.game.Game;
import com.CtrlAltPlay.levels.Vector;
import com.CtrlAltPlay.objects.Ground;
import com.CtrlAltPlay.sounds.Sounds;
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


public class Controls extends JPanel implements ActionListener{

    private Game game;
    private BufferedImage background;
    private Timer timer;
    private Champion moveChamp;
    private Champion jumpChamp;
    private Champion attackChamp;
    private Champion swordRiftChamp;
    private int moveTimer;
    private int jumpTimer;
    private int attackTimer;
    private int swordRiftTimer;
    private Ground ground;
    private boolean isOnMove;
    private boolean isOnJump;
    private boolean isOnAttack;
    private boolean isOnSwordRift;
            
    public Controls(Game theGame){
        game = theGame;
        ground = new Ground(0, 653);
        moveChamp = new Champion(1500, 1080);
        jumpChamp = new Champion(2000, 1080);
        attackChamp = new Champion(2000, 1080);
        swordRiftChamp = new Champion(2000, 1080);
        
        init();
    }
    
    private void init()
    {
        
        try{
            background = ImageIO.read(getClass().getResource("/Images/Placeholder background.png"));
        }catch(Exception ex){
            System.out.println("Error loading background image");
        }
        
        isOnMove = true;
        isOnJump = false;
        isOnAttack = false;
        isOnSwordRift = false;
        
        moveTimer = 0;
        jumpTimer = 0;
        attackTimer = 0;
        swordRiftTimer = 0;
        
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
        g2d.drawString("Move", 180, 265);
        g2d.drawString("Jump", 180, 465);
        g2d.drawString("Attack", 180, 665);
        g2d.drawString("Use sword rift", 150, 865);
        g2d.drawString("Return to main menu", 100, 1065);
        
        
        
        if(isOnMove == true)
        {
            moveChamp.drawForMenu(g2d);
        }
        
        if(isOnJump == true)
        {
            jumpChamp.drawForMenu(g2d);
        }
        
        if(isOnAttack == true)
        {
            attackChamp.drawForMenu(g2d);
        }
        
        if(isOnSwordRift == true)
        {
            swordRiftChamp.drawForMenu(g2d);
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
        jumpChamp.checkCollision(ground);
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
        if(isOnMove == true)
        {
            moveChamp.move(3);
            moveTimer++;
        }else
        {
            moveChamp.stopX();
        }
        
        if(isOnJump == true)
        {
            if(jumpTimer == 0)
            {
                jumpChamp.move(1);
            }
            jumpTimer++;
        }
        
        if(isOnAttack == true)
        {
            if(attackTimer == 0)
            {
                attackChamp.attack();
            }
            attackTimer++;
        }
        
        if(isOnSwordRift == true)
        {
            if(swordRiftTimer == 0)
            {
                swordRiftChamp.useSwordRift();
            }
            swordRiftTimer++;
        }
        
        moveChamp.doMove();
        jumpChamp.doMove();
        attackChamp.doMove();
        swordRiftChamp.doMove();
    }
    
    private void resetAnimations()
    {
        
        if(moveTimer == 125)
        {
            
            moveChamp.setX(750);
            moveChamp.setY(540);
            moveTimer = 0;
        }
        
        if(jumpTimer == 100)
        {
            jumpTimer = 0;
        }
        
        if(attackTimer == 100)
        {
            attackTimer = 0;
        }
        
        if(swordRiftTimer == 150)
        {
            swordRiftTimer = 0;
        }
    }
    
    private class MAdapter extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e){
            switch(e.getButton()){
                case MouseEvent.BUTTON1:
                    if(e.getX() <= (500 * Game.xScaleFactor) && e.getX() >= (0 * Game.xScaleFactor) && e.getY() >= (200 * Game.yScaleFactor) && e.getY() <= (300 * Game.yScaleFactor))
                    {
                        isOnMove = true;
                        isOnJump = false;
                        isOnAttack = false;
                        isOnSwordRift = false;
                    }
                    
                    if(e.getX() <= (500 * Game.xScaleFactor) && e.getX() >= (0 * Game.xScaleFactor) && e.getY() >= (400 * Game.yScaleFactor) && e.getY() <= (500 * Game.yScaleFactor))
                    {
                        isOnMove = false;
                        isOnJump = true;
                        isOnAttack = false;
                        isOnSwordRift = false;
                    }
                    
                    if(e.getX() <= (500 * Game.xScaleFactor) && e.getX() >= (0 * Game.xScaleFactor) && e.getY() >= (600 * Game.yScaleFactor) && e.getY() <= (700 * Game.yScaleFactor))
                    {
                        isOnMove = false;
                        isOnJump = false;
                        isOnAttack = true;
                        isOnSwordRift = false;
                    }
                    
                    if(e.getX() <= (500 * Game.xScaleFactor) && e.getX() >= (0 * Game.xScaleFactor) && e.getY() >= (800 * Game.yScaleFactor) && e.getY() <= (900 * Game.yScaleFactor))
                    {
                        isOnMove = false;
                        isOnJump = false;
                        isOnAttack = false;
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