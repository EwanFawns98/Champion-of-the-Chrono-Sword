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
public class ShockWave {
    
    private Vector position;
    private Vector displacement;
    private BufferedImage shockWave;
    private BufferedImage sprite;
    private int spriteWidth;
    private int spriteHeight;
    private boolean isRight;
    private boolean isCollected;
    private boolean isVisible;
    
    public ShockWave(int x, int y, boolean newIsRight)
    {
        //constructer method
        position = new Vector(x, y);
        displacement = new Vector(0, 0);
        isRight = newIsRight;
        try{
            shockWave = ImageIO.read(getClass().getResource("/Images/shockWave.png"));
        }catch(Exception ex){
            System.out.println("Error loading shockwave sprite");
        }
        
        if(isRight == false){
            sprite = shockWave.getSubimage(0, 0, 92, 90);
        }else
        {
            sprite = shockWave.getSubimage(92, 0, 92, 90);
        }
        
        spriteWidth = sprite.getWidth();
        spriteHeight = sprite.getHeight();
        
        
        isCollected = false;
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
    
    public boolean getIsCollected(){
        return isCollected;
    }
    
    public void setIsCollected(boolean newIsCollected){
        isCollected = newIsCollected;
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
    
    public void doMove()
    {
        //used to update the position of the shockwave
        move();
        position.add(displacement);
    }
    
    private void move()
    {
        //used to move the shockwave depending on whether it is shot right or left
        if(isRight == true)
        {
            displacement.setX(20);
        }else
        {
            displacement.setX(-20);
        }
    }
    
    public Rectangle getBounds()
    {
        Rectangle orbRect = new Rectangle(position.getX() + 35, position.getY() + 10, spriteWidth - 70, spriteHeight - 20);
        return orbRect;
    }
    
    public void draw(Graphics2D g2d, int playerX, int screenPosition)
    {
        //used to draw on screen
        if(isVisible == true && isCollected == false)
        {
            g2d.drawImage(sprite, (position.getX() - (playerX - screenPosition)), position.getY(), null);
        }
        
    }
    
    public void drawForMenu(Graphics2D g2d)
    {
        //used to draw for menus
        if(isVisible == true && isCollected == false)
        {
            g2d.drawImage(sprite, position.getX(), position.getY(), null);
        }
        
    }
}
