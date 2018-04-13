
package com.CtrlAltPlay.animation;

import java.awt.image.BufferedImage;


public class Animation {
    private int index; // Used to get the current time
    private int count; // Used to determine the current frame
    private int speed; // Used to set the speed of the animation
    private int frames; // Total number of frames in animation strip
    private int frameWidth; // frame width of the sprite
    private int frameHeight; // frame height of the sprite
    private int startYPosition; // sets how many rows down the animation starts on the sprite sheet
    private int startXPosition; // sets how many columns along the animation starts on the sprite sheet
    private BufferedImage spriteSheet; // the sprite sheet containing all the animations
    private BufferedImage currentSprite; // the frame that the animation is currently on
    
    
    public Animation(int newSpeed, int newFrames, BufferedImage newSpriteSheet, int newXPosition, int newYPosition, int newFrameWidth, int newFrameHeight, boolean backwards){
        // Constructer which is used to get all the imformation in the animation from the character classes.
        speed = newSpeed;
        frames = newFrames;
        frameWidth = newFrameWidth;
        frameHeight = newFrameHeight;
        startYPosition = newYPosition;
        startXPosition = newXPosition;
        index = 0;
        
        if(backwards == false)
        {
            count = 0;
        }else
        {
            count = frames - 1;
        }
        
        
        try{
            spriteSheet = newSpriteSheet;
        }catch(Exception ex){
            System.out.println("Error passing spriteSheet for animation");
        }
        // used to initialise sprite sheet
        if(backwards == false)
        {
            try
            {
                currentSprite = spriteSheet.getSubimage((frameWidth * startXPosition) - frameWidth, (frameHeight * startYPosition) - frameHeight, frameWidth, frameHeight);
            }catch(Exception ex)
            {
                System.out.println("Error loaing first frame for animation");
            }
        }else
        {
            try
            {
                currentSprite = spriteSheet.getSubimage((frameWidth * (startXPosition + (frames - 1))) - frameWidth, (frameHeight * startYPosition) - frameHeight, frameWidth, frameHeight);
            }catch(Exception ex)
            {
                System.out.println("Error loaing first frame for animation");
            }
        }
        // calculation which is used to set the first frame of the animation
    }
    
    public void run(){
        // used to run the animation
        index++;
        if(index > speed){
            index = 0;
            nextFrame();
        }
    }
    
    public void runBackwards(){
        // used for the animations that are flipped vertically
        index++;
        if(index > speed){
            index = 0;
            nextFrameBackwards();
        }
    }
    
    public void nextFrameBackwards(){
        // used to get the next frame in the animation
        count--;
        if (count < 0)
        {
            count = frames -1;
        }
                currentSprite = spriteSheet.getSubimage((frameWidth * (startXPosition + count)) - frameWidth, (frameHeight * startYPosition) - frameHeight, frameWidth, frameHeight);
        
    }
    
    public void nextFrame(){
        // used to get the next frame in the animation
        count++;
            if(count >= frames){
                count = 0;
            }
                currentSprite = spriteSheet.getSubimage((frameWidth * (startXPosition + count)) - frameWidth, (frameHeight * startYPosition) - frameHeight, frameWidth, frameHeight);
    }
    
    public BufferedImage getCurrentSprite(){
        // used to return the current sprite back to the character class so it can draw on screen
        return currentSprite;
}
}
