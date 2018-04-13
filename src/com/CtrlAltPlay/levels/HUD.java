
package com.CtrlAltPlay.levels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


public class HUD {
    private int health;
    private int orbs;
    private int lives;
    private BufferedImage shield;
    private BufferedImage orb;
    private BufferedImage championSprite;
    public HUD(int newHealth, int newOrb, int newLives){
        
        health = newHealth;
        orbs = newOrb;
        lives = newLives;
        try
        {
            shield = ImageIO.read(getClass().getResource("/Images/shield.png"));
        }catch(Exception ex)
        {
            System.out.println("Error loading shield UI");
        }
        
        try
        {
            orb = ImageIO.read(getClass().getResource("/Images/orb.png"));
        }catch(Exception ex)
        {
            System.out.println("Error loading orb UI");
        }
        
        try
        {
            championSprite = ImageIO.read(getClass().getResource("/Images/Champion sprite.png"));
        }catch(Exception ex)
        {
            System.out.println("Error loading champion UI");
        }
    }
    
    public void updateHud(int newHealth, int newOrb)
    {
        health = newHealth;
        orbs = newOrb;
    }
    
    public void draw(Graphics2D g2d)
    {
        Font font = new Font("Ariel", Font.PLAIN, 50);
        g2d.setFont(font);
        g2d.setColor(Color.black);
        if(health > 0)
        {
            for(int i = 0; i < health; i++)
            {
                g2d.drawImage(shield, shield.getWidth()*(i+1) - 50, 30, null);
            }
        }
        
        g2d.drawImage(orb, 14, 130, null);
        g2d.drawString(String.valueOf(orbs), 100, 180);
        g2d.drawString(String.valueOf(lives), 100, 280);
        g2d.scale(0.75, 0.75);
        g2d.drawImage(championSprite, 0, 275, null);
    }
}
