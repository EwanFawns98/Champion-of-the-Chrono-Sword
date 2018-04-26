
package com.CtrlAltPlay.characters;

import com.CtrlAltPlay.levels.Vector;
import com.CtrlAltPlay.objects.Ground;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import com.CtrlAltPlay.animation.Animation;
import com.CtrlAltPlay.sounds.Sounds;

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
    private int invulnerableTimer;
    private boolean isVisible;
    private boolean isAlive;
    private boolean isFalling;
    private boolean isAttackingR;
    private boolean isAttackingL;
    private boolean showAttackBoxL;
    private boolean showAttackBoxR;
    
    
    public Chieftain(int newX, int newY){
        // constructer method
        position = new Vector(newX, newY);
        displacement = new Vector(0, 0);
        health = 5;
        invulnerableTimer = 0;
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
        
        sprite = walkingAnim.getSubimage(0, 0, 190, 190);
        
        spriteWidth = 190;
        spriteHeight = 190;
        attackTimer = 0;
        
        isAlive = true;
        isVisible = true;
        isFalling = false;
        isAttackingR = false;
        isAttackingL = false;
        showAttackBoxL = false;
        showAttackBoxR = false;
        initAnimation();
    }
    
    
    private void initAnimation()
    {
        //initialises the animations
        walkR = new Animation(4, 6, walkingAnim, 1, 1, spriteWidth, spriteHeight, false);
        walkL = new Animation(4, 6, walkingAnim, 7, 1, spriteWidth, spriteHeight, true);
        attackR = new Animation(20, 4, attackingAnim, 1, 1, spriteWidth, spriteHeight, false);
        attackL = new Animation(20, 4, attackingAnim, 5, 1, spriteWidth, spriteHeight, true);
    }
    
    //getters/setters
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
        Rectangle bossRect = new Rectangle(position.getX() + 30, position.getY() + 30, spriteWidth - 100, spriteHeight - 60);
        return bossRect;
    }
    
    public Rectangle getLeftBounds()
    {
        Rectangle leftBounds = new Rectangle(position.getX() , position.getY() + 30, 50, 150);
        return leftBounds;
    }
    
    public Rectangle getRightBounds()
    {
        Rectangle rightBounds = new Rectangle(position.getX() + 140, position.getY() + 30, 50, 150);
        return rightBounds;
    }
    
    public boolean getShowAttackBoxL()
    {
        return showAttackBoxL;
    }
    
    public boolean getShowAttackBoxR()
    {
        return showAttackBoxR;
    }
    
    public void setShowAttackBoxL(boolean newShowAttackBoxL)
    {
        showAttackBoxL = newShowAttackBoxL;
    }
    
    public void setShowAttackBoxR(boolean newShowAttackBoxR)
    {
        showAttackBoxR = newShowAttackBoxR;
    }
    
    public void draw(Graphics2D g2d, int playerX, int screenPosition)
    { // used to draw on screen based on the position of the player
        if(isVisible == true && isAlive == true)
        {
            
            g2d.drawImage(sprite, (position.getX() - (playerX - screenPosition)), position.getY(), null);
        }
        
    }
    
    public void fall(){
        //used to make the character fall
        displacement.addY(1);
        
        if(displacement.getY() > 7)
        {
            displacement.setY(7);
        }
    }
    
    public void doMove(int playerX)
    {
        //used to update the position of the character
        
        //used to determine if the character is falling
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
            
            //used to run and reset the invulnerable timer 
            if(invulnerableTimer > 0)
            {
                invulnerableTimer++;
            }
            
            if(invulnerableTimer == 50)
            {
                invulnerableTimer = 0;
            }
            //updates the position
            position.add(displacement);
        }
    }
    
    private void move(int playerX)
    {
        // used to move the boss towards the player and attack when they are in a certain range
            if(position.getX() > playerX + 70 && isAttackingL == false && isAttackingR == false)
            {
                displacement.setX(-8);
                sprite = walkL.getCurrentSprite();
                walkL.runBackwards();
            }else if(position.getX() + 115 < playerX && isAttackingL == false && isAttackingR == false)
            {
                displacement.setX(8);
                sprite = walkR.getCurrentSprite();
                walkR.run();
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
    {// used to check if the player is to the left or right of the boss
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
        //used to attack either left or right. there is a delay to allow the player to move out of the attack range
        if(isAttackingL == true)
        {
            if(attackTimer >= 0 && attackTimer <= 40 || attackTimer >= 60 && attackTimer <= 103)
            {
                sprite = attackL.getCurrentSprite();
                attackL.runBackwards();
                
                if(attackTimer >= 60 && attackTimer <= 103)
                {
                    showAttackBoxL = true;
                }
                
                if(attackTimer == 60)
                {
                    Sounds.play(getClass().getResourceAsStream("/Sounds/sword strike.wav"), false);
                }
            }
            
        }else if(isAttackingR == true)
        {
            if(attackTimer >= 0 && attackTimer <= 40 || attackTimer >= 60 && attackTimer <= 103)
            {
                sprite = attackR.getCurrentSprite();
                attackR.run();
                
                if(attackTimer >= 60 && attackTimer <= 103)
                {
                    showAttackBoxR = true;
                }
                
                if(attackTimer == 60)
                {
                    Sounds.play(getClass().getResourceAsStream("/Sounds/sword strike.wav"), false);
                }
            }
        }
        
        attackTimer++;
        displacement.setX(0);
        
        if(attackTimer == 103)
        {
            attackTimer = 0;
            isAttackingL = false;
            isAttackingR = false;
            showAttackBoxL = false;
            showAttackBoxR = false;
        }
    }
    
    //collisions between objects and characters
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
        if(c.getLeftAttackBounds().intersects(getBounds()) && c.getIsAttackingL() == true && invulnerableTimer == 0 || c.getRightAttackBounds().intersects(getBounds()) && c.getIsAttackingR() == true && invulnerableTimer == 0)
        {
            invulnerableTimer++;
            health -= 1;
            if(health == 0)
            {
                isAlive = false;
                return true;
            }
        }
        return false;
    }
    
}
