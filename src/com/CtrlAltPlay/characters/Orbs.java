/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.CtrlAltPlay.characters;

import com.CtrlAltPlay.levels.Vector;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
public class Orbs {
    
    private Vector position;
    private BufferedImage sprite;
    private int spriteWidth;
    private int spriteHeight;
    private boolean isVisible;
    
    public Orbs(int x, int y)
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
    
    public Rectangle getBounds()
    {
        Rectangle orbRect = new Rectangle(position.getX(), position.getY(), spriteWidth, spriteHeight);
        return orbRect;
    }
    
    public void draw(Graphics2D g2d, int playerX, int screenPosition)
    {
        g2d.drawImage(sprite, (position.getX() - (playerX - screenPosition)), position.getY(), null);
    }
}
