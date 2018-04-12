
package com.CtrlAltPlay.characters;

import com.CtrlAltPlay.levels.Vector;
import com.CtrlAltPlay.objects.Ground;
import com.CtrlAltPlay.objects.Level1LargePlatform;
import com.CtrlAltPlay.objects.Level1SmallPlatform;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import com.CtrlAltPlay.animation.Animation;

public class Chieftain {
    
    Vector position;
    Vector displacement;
    private BufferedImage walkingAnim;
    private BufferedImage attackingAnim;
    private BufferedImage sprite;
    private Animation walkR;
    private Animation walkL;
    private Animation attackL;
    private Animation attackR;
    private int spriteWidth;
    private int spriteHeight;
    private int health;
    private int attackTimer;
    private boolean isVisible;
    private boolean isAlive;
    private boolean isFalling;
    private boolean isAttackingR;
    private boolean isAttackingL;
    
    
    public Chieftain(int newX, int newY){
        
        position = new Vector(newX, newY);
        displacement = new Vector(0, 0);
        health = 5;
        try{
            walkingAnim = ImageIO.read(getClass().getResource("/Images/Chieftain walking.png"));
        }catch(Exception ex)
        {
            System.out.println("Error loading Chieftain walking animation");
        }
        
        try{
            attackingAnim = ImageIO.read(getClass().getResource("/Images/Chieftain attacking.png"));
        }catch(Exception ex)
        {
            System.out.println("Error loading Chieftain attacking animation");
        }
        
        sprite = walkingAnim.getSubimage(0, 0, 128, 128);
        
        spriteWidth = 190;
        spriteHeight = 190;
        attackTimer = 0;
        
        isAlive = true;
        isVisible = true;
        isFalling = false;
        isAttackingR = false;
        isAttackingL = false;
        initAnimation();
    }
    
    
    private void initAnimation()
    {
        walkR = new Animation(6, 6, walkingAnim, 1, 1, spriteWidth, spriteHeight, false);
        walkL = new Animation(6, 6, walkingAnim, 7, 1, spriteWidth, spriteHeight, true);
        attackR = new Animation(20, 4, attackingAnim, 1, 1, spriteWidth, spriteHeight, false);
        attackL = new Animation(20, 4, attackingAnim, 5, 1, spriteWidth, spriteHeight, true);
    }
    
    public boolean getIsVisible()
    {
        return isVisible;
    }
    
    public void setIsVisible(boolean newIsVisible)
    {
        isVisible = newIsVisible;
    }
    
    
    public boolean getIsAlive()
    {
        return isAlive;
    }
    
    public void setIsAlive(boolean newIsAlive)
    {
        isAlive = newIsAlive;
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
        Rectangle bossRect = new Rectangle(position.getX() + 30, position.getY() + 30, spriteWidth - 60, spriteHeight - 60);
        return bossRect;
    }
    
    public void draw(Graphics2D g2d, int playerX, int screenPosition)
    {
        if(isVisible == true && isAlive == true)
        {
            //g2d.fillRect((position.getX() + 95 - (playerX - screenPosition)), position.getY(), 160, 160);
            g2d.drawImage(sprite, (position.getX() - (playerX - screenPosition)), position.getY(), null);
        }
        
    }
    
    public void fall(){
        displacement.addY(1);
        
        if(displacement.getY() > 7)
        {
            displacement.setY(7);
        }
    }
    
    public void doMove(int playerX)
    {
        if(isVisible == true && isAlive == true)
        {
            if(isFalling == true)
            {
                fall();
            }else if(isFalling == false)
            {
               displacement.setY(0);
            }
            
            move(playerX);
            position.add(displacement);
        }
    }
    
    private void move(int playerX)
    {
            if(position.getX() > playerX + 65 && isAttackingL == false && isAttackingR == false)
            {
                displacement.setX(-3);
                walkL.runBackwards();
                sprite = walkL.getCurrentSprite();
            }else if(position.getX() + 115 < playerX && isAttackingL == false && isAttackingR == false)
            {
                displacement.setX(3);
                walkR.run();
                sprite = walkR.getCurrentSprite();
            }else
            {
                if(isAttackingL == false && isAttackingR == false)
                {
                    checkPosition(playerX);
                }
                attack(playerX);
            }
    }
    
    private void checkPosition(int playerX)
    {
        if(position.getX() + 95 > playerX + 64)
        {
            isAttackingL = true;
            
        }else
        {
            isAttackingR = true;
            
        }
    }
    
    private void attack(int playerX)
    {
        
        if(isAttackingL == true)
        {
            if(attackTimer >= 0 && attackTimer <= 40 || attackTimer >= 100 && attackTimer <= 143)
            {
                attackL.runBackwards();
                sprite = attackL.getCurrentSprite();
            }
            
        }else if(isAttackingR == true)
        {
            if(attackTimer >= 0 && attackTimer <= 40 || attackTimer >= 100 && attackTimer <= 143)
            {
                attackR.run();
                sprite = attackR.getCurrentSprite();
            }
        }
        
        attackTimer++;
        displacement.setX(0);
        
        if(attackTimer == 143)
        {
            attackTimer = 0;
            isAttackingL = false;
            isAttackingR = false;
        }
    }
    
    public boolean checkCollision(Ground g)
    {
            if(getBounds().intersects(g.getBounds()))
            {
                isFalling = false;
                return true;
            }else
            {
                isFalling = true;
                return false;
            }
    }
    
    public boolean checkAttackCollision(Champion c)
    {
        if(c.getLeftAttackBounds().intersects(getBounds()) && c.getIsAttackingL() == true || c.getRightAttackBounds().intersects(getBounds()) && c.getIsAttackingR() == true)
        {
            health -= 1;
            if(health == 0)
            {
                isAlive = false;
            }
        }
        return false;
    }
    
}