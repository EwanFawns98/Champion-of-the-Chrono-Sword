
package com.CtrlAltPlay.characters;

import com.CtrlAltPlay.levels.Vector;
import com.CtrlAltPlay.objects.Ground;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import com.CtrlAltPlay.animation.Animation;
import com.CtrlAltPlay.sounds.Sounds;
import java.util.Random;

public class Pharaoh {
    
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
    private boolean isThrowing;
    private boolean throwing;
    private final int THROW_CHANCE = 100;
    
    public Pharaoh(int newX, int newY){
        //constructer method
        position = new Vector(newX, newY);
        displacement = new Vector(0, 0);
        health = 7;
        invulnerableTimer = 0;
        try{
            walkingAnim = ImageIO.read(getClass().getResource("/Images/Pharaoh walking.png"));
        }catch(Exception ex)
        {
            System.out.println("Error loading Pharaoh walking animation");
        }
        
        try{
            attackingAnim = ImageIO.read(getClass().getResource("/Images/Pharaoh attacking.png"));
        }catch(Exception ex)
        {
            System.out.println("Error loading Pharaoh attacking animation");
        }
        
        sprite = walkingAnim.getSubimage(0, 0, 240, 190);
        
        spriteWidth = 240;
        spriteHeight = 190;
        attackTimer = 0;
        
        isAlive = true;
        isVisible = true;
        isFalling = false;
        isThrowing = false;
        throwing = false;
        isAttackingR = false;
        isAttackingL = false;
        showAttackBoxL = false;
        showAttackBoxR = false;
        initAnimation();
    }
    
    
    private void initAnimation()
    {
        //initialises animations
        walkL = new Animation(4, 6, walkingAnim, 1, 1, spriteWidth, spriteHeight, false);
        walkR = new Animation(4, 6, walkingAnim, 7, 1, spriteWidth, spriteHeight, true);
        attackL = new Animation(20, 4, attackingAnim, 1, 1, spriteWidth, spriteHeight, false);
        attackR = new Animation(20, 4, attackingAnim, 5, 1, spriteWidth, spriteHeight, true);
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
    
    public boolean getIsThrowing()
    {
        return isThrowing;
    }
    
    public void setIsThrowing(boolean newIsThrowing)
    {
        isThrowing = newIsThrowing;
    }
    
    public boolean getThrowing()
    {
        return throwing;
    }
    
    public void setThrowing(boolean newThrowing)
    {
        throwing = newThrowing;
    }
    
    public boolean getIsAttackingR()
    {
        return isAttackingR;
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
        Rectangle bossRect = new Rectangle(position.getX() + 80, position.getY() + 30, spriteWidth - 150, spriteHeight - 60);
        return bossRect;
    }
    
    public Rectangle getLeftBounds()
    {
        Rectangle leftBounds = new Rectangle(position.getX() + 50, position.getY() + 30, 50, 150);
        return leftBounds;
    }
    
    public Rectangle getRightBounds()
    {
        Rectangle rightBounds = new Rectangle(position.getX() + 190, position.getY() + 30, 50, 150);
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
    {//used to draw the character on screen based on the position of the player
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
        if(isVisible == true && isAlive == true)
        {
            //used to determine if the character is falling
            if(isFalling == true)
            {
                fall();
            }else if(isFalling == false)
            {
               displacement.setY(0);
            }
            
            move(playerX);
            if(invulnerableTimer > 0)
            {
                invulnerableTimer++;
            }
            
            if(invulnerableTimer == 50)
            {
                invulnerableTimer = 0;
            }
            //update the position
            position.add(displacement);
        }
    }
    
    private void move(int playerX)
    {
        int throwDecision;
        Random rand = new Random();
        
        //random chance of the pharaoh throwing his spear
        if(isThrowing == false && isAttackingL == false && isAttackingR == false)
        {
            throwDecision = rand.nextInt(THROW_CHANCE)+1;
            if(throwDecision == 1)
            {
                isThrowing = true;
            }
        }
        
        //moves the boss towards the player and checks if they are in range or if they are throwing a weapon
        if(position.getX() > playerX + 20 && isAttackingL == false && isAttackingR == false && isThrowing == false)
        {
            displacement.setX(-8);
            sprite = walkL.getCurrentSprite();
            walkL.run();
        }else if(position.getX() + 165 < playerX && isAttackingL == false && isAttackingR == false && isThrowing == false)
        {
            displacement.setX(8);
            sprite = walkR.getCurrentSprite();
            walkR.runBackwards();
        }else if(isThrowing == true)
        {
            if(isAttackingL == false && isAttackingR == false)
            {
                checkPosition(playerX);
            }
            throwSpear();
        }else
        {
            if(isAttackingL == false && isAttackingR == false)
            {
                checkPosition(playerX);
            }
            attack();
        }
    }
    
    private void checkPosition(int playerX)
    {
        //checks if the player is to the left or right of the boss
        if(position.getX() + 95 > playerX + 64)
        {
            isAttackingL = true;
            
        }else
        {
            isAttackingR = true;
        }
    }
    
    private void attack()
    {
        //used for attacking the player. has a delay to allow the player to move out of range
        if(isAttackingL == true)
        {
            if(attackTimer >= 0 && attackTimer <= 40 || attackTimer >= 60 && attackTimer <= 103)
            {
                sprite = attackL.getCurrentSprite();
                attackL.run();
                
                if(attackTimer >= 60 && attackTimer <= 103)
                {
                    showAttackBoxL = true;
                }
                
                if(attackTimer == 60)
                {
                    Sounds.play(getClass().getResourceAsStream("/Sounds/spear-strike.wav"), false);
                }
            }
            
        }else if(isAttackingR == true)
        {
            if(attackTimer >= 0 && attackTimer <= 40 || attackTimer >= 60 && attackTimer <= 103)
            {
                sprite = attackR.getCurrentSprite();
                attackR.runBackwards();
                
                if(attackTimer >= 60 && attackTimer <= 103)
                {
                    showAttackBoxR = true;
                }
                
                if(attackTimer == 60)
                {
                    Sounds.play(getClass().getResourceAsStream("/Sounds/spear-strike.wav"), false);
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
    
    private void throwSpear()
    {
        //used to throw the spear at the player
        if(isAttackingL == true)
        {
            if(attackTimer >= 0 && attackTimer <= 40 || attackTimer >= 60 && attackTimer <= 103)
            {
                sprite = attackL.getCurrentSprite();
                attackL.run();
                
                if(attackTimer == 60)
                {
                    throwing = true;
                }
                
                if(attackTimer == 60)
                {
                    Sounds.play(getClass().getResourceAsStream("/Sounds/spear-throw.wav"), false);
                }
            }
            
        }else if(isAttackingR == true)
        {
            if(attackTimer >= 0 && attackTimer <= 40 || attackTimer >= 60 && attackTimer <= 103)
            {
                sprite = attackR.getCurrentSprite();
                attackR.runBackwards();
                
                if(attackTimer == 60)
                {
                    throwing = true;
                }
                
                if(attackTimer == 60)
                {
                    Sounds.play(getClass().getResourceAsStream("/Sounds/spear-throw.wav"), false);
                }
            }
        }
        
        if(attackTimer > 60)
        {
            throwing = false;
        }
        
        attackTimer++;
        displacement.setX(0);
        
        
        
        if(attackTimer == 103)
        {
            attackTimer = 0;
            isAttackingL = false;
            isAttackingR = false;
            isThrowing = false;
        }
    }
    
    //collision checks
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
