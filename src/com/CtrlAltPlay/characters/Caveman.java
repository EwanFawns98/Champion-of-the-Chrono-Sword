
package com.CtrlAltPlay.characters;

import com.CtrlAltPlay.levels.Vector;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


public class Caveman {
    
    Vector position;
    Vector displacement;
    private BufferedImage sprite;
    private int spriteWidth;
    private int spriteHeight;
    boolean isVisible;
    
    public Caveman(int newX, int newY){
        
        position = new Vector(newX, newY);
        displacement = new Vector(0, 0);
        
        try{
            sprite = ImageIO.read(getClass().getResource("/Images/Sprite-0002.png"));
        }catch(Exception ex)
        {
            System.out.println("Error loading Chieftain image");
        }
        
        spriteWidth = sprite.getWidth();
        spriteHeight = sprite.getHeight();
        
        isVisible = true;
    }
    
    public boolean getIsVisible()
    {
        return isVisible;
    }
    
    public void setIsVisible(boolean newIsVisible)
    {
        isVisible = newIsVisible;
    }
    
    public int getSpriteWidth()
    {
        return spriteWidth;
    }
    
    public int getSpriteHeight()
    {
        return spriteHeight;
    }
    
    public Vector getPosition()
    {
        return position;
    }
    
    public Rectangle getBounds()
    {
        Rectangle bossRect = new Rectangle(position.getX(), position.getY(), spriteWidth, spriteHeight);
        return bossRect;
    }
    
    public void draw(Graphics2D g2d, int playerX, int screenPosition)
    {
        if(isVisible == true)
        {
            g2d.drawImage(sprite, (position.getX() - (playerX - screenPosition)), position.getY(), null);
        }
        
    }
    
    public void attack()
    {
        
    }
    

}