
package com.CtrlAltPlay.screens;
import com.CtrlAltPlay.game.Game;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class GameOver extends JPanel{

    private Game game;
    private BufferedImage background;
    
    public GameOver(Game theGame){
        //constructer method
        game = theGame;
        init();
    }
    
    private void init()
    {
        //used to initialise the backgorund
        try{
            background = ImageIO.read(getClass().getResource("/Images/blackBackground.png"));
        }catch(Exception ex){
            System.out.println("Error loading background image");
        }
        
        setFocusable(true);
        setDoubleBuffered(true);
        addKeyListener(new TAdapter());
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        //used to draw everything on screen
        Font font = new Font("Arial", Font.PLAIN, 40);
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.scale(Game.xScaleFactor, Game.yScaleFactor);
        g2d.setFont(font);
        g2d.drawImage(background, 0, 0, null);
        g2d.setColor(Color.WHITE);
        g2d.drawString("You have fallen", 750, 265);
        g2d.drawString("Press space to return to main menu", 750, 465);
        g.dispose();
    }
    

    private class TAdapter extends KeyAdapter{
        
        
        @Override
        public void keyPressed(KeyEvent e)
        {
            switch(e.getKeyCode())
            {
                case KeyEvent.VK_SPACE:
                    game.startMainMenu();//used to return to the main menu
                    break;
                    
            }
        }
        
    }
}
