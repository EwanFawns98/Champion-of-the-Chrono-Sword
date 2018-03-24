/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.CtrlAltPlay.objects;

import com.CtrlAltPlay.levels.Vector;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
public class Level1LargePlatform {
    
    private Vector position;
    private BufferedImage sprite;
    private int spriteWidth;
    private int spriteHeight;
    private boolean isVisible;
    
    public Level1LargePlatform(int x, int y)
    {
        position = new Vector(x, y);
        try{
            sprite = ImageIO.read(getClass().getResource("/Images/Orb placeholder.png"));
        }catch(Exception ex){
            System.out.println("Error loading orb sprite");
        }
        
        spriteWidth = sprite.getWidth();
        spriteHeight = sprite.getHeight();
        
        isVisible = true;
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
    
    public boolean getIsVisible(){
        return isVisible;
    }
    
    public void setIsVisible(boolean newIsVisible){
        isVisible = newIsVisible;
    }
    
    public Rectangle getBounds()
    {
        Rectangle orbRect = new Rectangle(position.getX(), position.getY(), 500, 200);
        return orbRect;
    }
    
    public void draw(Graphics2D g2d, int playerX, int screenPosition)
    {
        if(isVisible == true)
        {
            g2d.fillRect((position.getX() - (playerX - screenPosition)), position.getY(), 500, 200);
        }
        
    }
}