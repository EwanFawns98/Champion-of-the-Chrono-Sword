
package com.CtrlAltPlay.screens;
import com.CtrlAltPlay.game.Game;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class Cutscene2 extends JPanel{

    private Game game;
    private BufferedImage background;
    private int health;
    private int lives;
    private int orbs;
    
    public Cutscene2(Game theGame, int newHealth, int newLives, int newOrbs){
        game = theGame;
        health = newHealth;
        lives = newLives;
        orbs = newOrbs;
        init();
    }
    
    private void init()
    {
        
        try{
            background = ImageIO.read(getClass().getResource("/Images/blackBackground.png"));
        }catch(Exception ex){
            System.out.println("Error loading background image");
        }
        
        setFocusable(true);
        setDoubleBuffered(true);
        addKeyListener(new TAdapter());
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
        g2d.setColor(Color.WHITE);
        g2d.drawString("You have fallen", 750, 265);
        g2d.drawString("Press space to continue", 750, 465);
        g.dispose();
    }
    

    private class TAdapter extends KeyAdapter{
        
        
        @Override
        public void keyPressed(KeyEvent e)
        {
            switch(e.getKeyCode())
            {
                case KeyEvent.VK_SPACE:
                    game.startLevel2(health, lives, orbs);
                    break;
                    
            }
        }
        
    }
}