
package com.CtrlAltPlay.screens;

import com.CtrlAltPlay.characters.Champion;
import com.CtrlAltPlay.game.Game;
import com.CtrlAltPlay.levels.Background;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;


public class MainMenu extends JPanel implements ActionListener{

    private Game game;
    private BufferedImage background;
    private Timer timer;
    private Champion player;
    private Background scrollingBackground1;
    int backgroundScroll;
            
    public MainMenu(Game theGame){
        //constructer method
        game = theGame;
        player = new Champion(260, 130, 5, 3, 0);
        backgroundScroll = 960;
        init();
    }
    
    private void init()
    {
        //used to initialise the background
        try{
            background = ImageIO.read(getClass().getResource("/Images/background_1.png"));
        }catch(Exception ex){
            System.out.println("Error loading background image");
        }
        
        scrollingBackground1 = new Background(background, backgroundScroll);
        setFocusable(true);
        setDoubleBuffered(true);
        addMouseListener(new MAdapter());
        timer = new Timer(10, this);
        
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        //used to draw everything on screen
        Font font = new Font("TRAJAN PRO", Font.PLAIN, 60);
        Font font2 = new Font("Arial", Font.PLAIN, 40);
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.scale(Game.xScaleFactor, Game.yScaleFactor);
        g2d.setFont(font);
        
        scrollingBackground1.draw(g2d);
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 200, 500, 100);
        g2d.fillRect(0, 400, 500, 100);
        g2d.fillRect(0, 600, 500, 100);
        g2d.fillRect(0, 800, 500, 100);
        g2d.fillRect(0, 1000, 500, 100);
        g2d.setColor(Color.WHITE);
        g2d.drawString("Champion of the Chrono Sword", 400, 100);
        g2d.setFont(font2);
        g2d.drawString("Start Game", 150, 265);
        g2d.drawString("Controls", 180, 465);
        g2d.drawString("Tutorial", 180, 665);
        g2d.drawString("Options", 180, 865);
        g2d.drawString("Exit game", 180, 1065);
        g2d.scale(5, 5);
        player.draw(g2d);
        g.dispose();
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        //runs off the swing timer every 10ms
        updateMove();
        repaint();
    }
    
    
    public void startTimer()
    {
        //used to start the swing timer
        timer.start();
    }
    
    public void stopTimer()
    {
        //used to stop the swing timer
        timer.stop();
    }
    
    
    private void updateMove()
    {
        //used to update the player for animation and scroll the background
        player.doMove();
        backgroundScroll++;
        scrollingBackground1.updateBackground(backgroundScroll);
    }
    
    

    
    private class MAdapter extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e){
            switch(e.getButton()){
                case MouseEvent.BUTTON1:
                    //used for navigaiton between menus
                    if(e.getX() <= (500 * Game.xScaleFactor) && e.getX() >= (0 * Game.xScaleFactor) && e.getY() >= (200 * Game.yScaleFactor) && e.getY() <= (300 * Game.yScaleFactor))
                    {
                        game.cutscene1();
                    }
                    
                    if(e.getX() <= (500 * Game.xScaleFactor) && e.getX() >= (0 * Game.xScaleFactor) && e.getY() >= (400 * Game.yScaleFactor) && e.getY() <= (500 * Game.yScaleFactor))
                    {
                        game.controls();
                    }
                    
                    if(e.getX() <= (500 * Game.xScaleFactor) && e.getX() >= (0 * Game.xScaleFactor) && e.getY() >= (600 * Game.yScaleFactor) && e.getY() <= (700 * Game.yScaleFactor))
                    {
                        game.tutorial();
                    }
                    
                    if(e.getX() <= (500 * Game.xScaleFactor) && e.getX() >= (0 * Game.xScaleFactor) && e.getY() >= (800 * Game.yScaleFactor) && e.getY() <= (900 * Game.yScaleFactor))
                    {
                        game.options();
                    }
                    
                    if(e.getX() <= (500 * Game.xScaleFactor) && e.getX() >= (0 * Game.xScaleFactor) && e.getY() >= (1000 * Game.yScaleFactor) && e.getY() <= (1100 * Game.yScaleFactor))
                    {
                        System.exit(0);//used to quit the game
                    }
                    
                    break;
                    
            }
        }
        
    }
}
