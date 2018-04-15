
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


public class Cutscene3 extends JPanel implements ActionListener{

    private Game game;
    private BufferedImage background;
    private String[] text;
    private TextTyper textTyper;
    private int health;
    private int lives;
    private int orbs;
    Timer timer;
    
    public Cutscene3(Game theGame, int newHealth, int newLives, int newOrbs){
        game = theGame;
        text = new String[10];
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
        
        text[0] = "As the Pharaoh inhaled in his last breath, he spoke to the champion telling him that he";
        text[1] = "will fall against the power of the Shadow King. Confused, the champion contemplated as";
        text[2] = "to how the Shadow King was able to travel through time without the Chrono Sword.";
        text[3] = "It was then he realised that these timelines were corrupted not as a result of him travelling";
        text[4] = "through time from a corrupted era. These timelines were corrupted before the champion even";
        text[5] = "left Titan. He then realised that the Shadow King had done this during the war 100 years ago";
        text[6] = "when he held both the Sword of Light and the Chrono Sword as a means of returning.";
        text[7] = "The champion then realised what he needed to do. The champion needed to go to the time where";
        text[8] = "the Shadow King first travelled through time and vanquish him before he could travel through";
        text[9] = "and ravage the timelines. He will now face his final enemy";
        
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
        g2d.drawString(text[6].substring(0, textTyper.getSubStringIndex()[6]), 50, 365);
        g2d.drawString(text[7].substring(0, textTyper.getSubStringIndex()[7]), 50, 465);
        g2d.drawString(text[8].substring(0, textTyper.getSubStringIndex()[8]), 50, 515);
        g2d.drawString(text[9].substring(0, textTyper.getSubStringIndex()[9]), 50, 565);
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
                    game.startLevel3(health, lives, orbs);
                    break;
                    
            }
        }
        
    }
}
