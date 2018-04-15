
package com.CtrlAltPlay.screens;
import com.CtrlAltPlay.animation.TextTyper;
import com.CtrlAltPlay.game.Game;
import com.CtrlAltPlay.sounds.Sounds;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Cutscene2 extends JPanel implements ActionListener{

    private Game game;
    private BufferedImage background;
    private String[] text;
    private TextTyper textTyper;
    private int health;
    private int lives;
    private int orbs;
    Timer timer;
    
    public Cutscene2(Game theGame, int newHealth, int newLives, int newOrbs){
        game = theGame;
        text = new String[6];
        health = newHealth;
        lives = newLives;
        orbs = newOrbs;
        init();
    }
    
    private void init()
    {
        
        try{
            background = ImageIO.read(getClass().getResource("/Images/blackBackground.png"));
        }catch(Exception ex){
            System.out.println("Error loading background image");
        }
        
        text[0] = "Well done champion. You have defeated the chieftain but the prehistoric era still remains";
        text[1] = "corrupted by darkness. As you travel through the portal, you find the next breach in time.";
        text[2] = "You have arrived in ancient egypt. The darkness has spread through the land and has risen";
        text[3] = "ancient egyptian spirits who know plague the Egypt's people. Worst of all, the Shadow";
        text[4] = "Kings influence has corrupted the Pharoah, who now rules his dark army with an iron fist.";
        text[5] = "You must stop the Pharaoh from causing any more chaos in this timeline.";
        
        textTyper = new TextTyper(1, text);
        setFocusable(true);
        setDoubleBuffered(true);
        addKeyListener(new TAdapter());
        timer = new Timer(10, this);
        if(Game.musicIsPlaying == false)
        {
            Sounds.play(getClass().getResourceAsStream("/Sounds/music.wav"), true);
            Game.musicIsPlaying = true;
        }
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        Font font = new Font("Arial", Font.PLAIN, 40);
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.scale(Game.xScaleFactor, Game.yScaleFactor);
        g2d.setFont(font);
        g2d.drawImage(background, 0, 0, null);
        g2d.setColor(Color.WHITE);
        g2d.drawString(text[0].substring(0, textTyper.getSubStringIndex()[0]), 50, 65);
        g2d.drawString(text[1].substring(0, textTyper.getSubStringIndex()[1]), 50, 115);
        g2d.drawString(text[2].substring(0, textTyper.getSubStringIndex()[2]), 50, 165);
        g2d.drawString(text[3].substring(0, textTyper.getSubStringIndex()[3]), 50, 215);
        g2d.drawString(text[4].substring(0, textTyper.getSubStringIndex()[4]), 50, 265);
        g2d.drawString(text[5].substring(0, textTyper.getSubStringIndex()[5]), 50, 315);
        g2d.drawString("Press space to continue", 750, 1000);
        
        
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        textTyper.run();
        repaint();
    }
    
    public void startTimer()
    {
        timer.start();
    }
    
    public void stopTimer()
    {
        timer.stop();
    }

    private class TAdapter extends KeyAdapter{
        
        
        @Override
        public void keyPressed(KeyEvent e)
        {
            switch(e.getKeyCode())
            {
                case KeyEvent.VK_SPACE:
                    timer.stop();
                    game.startLevel2(health, lives, orbs);
                    break;
                    
            }
        }
        
    }
}
