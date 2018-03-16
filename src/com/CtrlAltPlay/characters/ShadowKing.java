
package com.CtrlAltPlay.characters;

import com.CtrlAltPlay.levels.Vector;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


public class ShadowKing {
    
    Vector position;
    Vector displacement;
    private BufferedImage sprite;
    private int spriteWidth;
    private int spriteHeight;
    
    public ShadowKing(){
        
        
        try{
            sprite = ImageIO.read(getClass().getResource("/Images/Chieftain.png"));
        }catch(Exception ex)
        {
            System.out.println("Error loading Chieftain image");
        }
        
        spriteWidth = sprite.getWidth();
        spriteHeight = sprite.getHeight();
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
    
    public void attack()
    {
        
    }
    
    public void rangedAttack()
    {
        
    }
}
