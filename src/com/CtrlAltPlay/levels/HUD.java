
package com.CtrlAltPlay.levels;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


public class HUD {
    private int health;
    private BufferedImage shield;
    public HUD(int newHealth){
        health = newHealth;
        
        try
        {
            shield = ImageIO.read(getClass().getResource("/Images/shield.png"));
        }catch(Exception ex)
        {
            System.out.println("Error loading shield UI");
        }
    }
    
    public void updateHud(int newHealth)
    {
        health = newHealth;
    }
    
    public void draw(Graphics2D g2d)
    {
        if(health > 0)
        {
            for(int i = 0; i < health; i++)
            {
                g2d.drawImage(shield, shield.getWidth()*(i+1) - 50, 30, null);
            }
        }
    }
}
