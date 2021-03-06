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
public class Level3Platform {
    
    private Vector position;
    private BufferedImage sprite;
    private int spriteWidth;
    private int spriteHeight;
    private boolean isVisible;
    
    public Level3Platform(int x, int y)
    {
        //constructer
        position = new Vector(x, y);
        try{
            sprite = ImageIO.read(getClass().getResource("/Images/small_platform3.png"));
        }catch(Exception ex){
            System.out.println("Error loading orb sprite");
        }
        
        spriteWidth = sprite.getWidth();
        spriteHeight = sprite.getHeight();
        
        isVisible = true;
    }
    
    //getters/setters
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
        Rectangle orbRect = new Rectangle(position.getX(), position.getY(), 100, 100);
        return orbRect;
    }
    
    public void draw(Graphics2D g2d, int playerX, int screenPosition)
    {
        //used to draw on screen
        if(isVisible == true)
        {
            g2d.drawImage(sprite, (position.getX() - (playerX - screenPosition)), position.getY(), null);
        }
        
    }
}
