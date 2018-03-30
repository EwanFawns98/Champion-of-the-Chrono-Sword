
package com.CtrlAltPlay.levels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


public class HUD {
    private int health;
    private int orbs;
    private BufferedImage shield;
    private BufferedImage orb;
    public HUD(int newHealth, int newOrb){
        
        health = newHealth;
        orbs = newOrb;
        
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
        
        g2d.drawImage(orb, orb.getWidth() - 50, 130, null);
        g2d.drawString(String.valueOf(orbs), 80, 180);
    }
}
