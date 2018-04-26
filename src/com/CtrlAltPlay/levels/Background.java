
package com.CtrlAltPlay.levels;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
public class Background {
    
    private BufferedImage background1; // image of the background
    private int startX; // the x value that the background should start based on the position of the player
    private int playerPosition;
    private int backgroundWidth;
    private int updater;
    
    public Background(BufferedImage newBackground, int newStartX)
    {
        //constructer method
        background1 = newBackground;
        startX = newStartX;
        backgroundWidth = newBackground.getWidth();
        playerPosition = newStartX - (backgroundWidth/2);
        updater = playerPosition ;
    }
    

    public void draw(Graphics2D g2d)
    {
        // used to draw the background images
        g2d.drawImage(background1, ((playerPosition*-1) + startX), 0, null);
        g2d.drawImage(background1, ((playerPosition*-1) + (backgroundWidth + startX)), 0, null);
    }
    
    public void updateBackground(int newPlayerX)
    {
        /* Used to update the postion of the background based on where the player is in the level.
        It also shifts the background at certain intervals creating the illusion that the background
        is continuous.
        */
        playerPosition = newPlayerX; 
        
        if((playerPosition - (backgroundWidth/2)) % (backgroundWidth) == 0 && updater != (playerPosition - (backgroundWidth/2)) && updater < (playerPosition - (backgroundWidth/2))){
            startX += backgroundWidth;
            updater = playerPosition - (backgroundWidth/2);
        }
        
        if((playerPosition - (backgroundWidth/2)) < updater)
        {
            startX -= backgroundWidth;
            updater -= backgroundWidth;
        }
    }
}
