/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.CtrlAltPlay.sounds;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.CtrlAltPlay.game.Game;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Clip;
public class Sounds{
        public static synchronized void play(InputStream soundResource, boolean isMusic){
            new Thread(new Runnable(){
                
                
                @Override
                public void run(){
                    try{
                        Clip clip = AudioSystem.getClip();
                        AudioInputStream ais = AudioSystem.getAudioInputStream(soundResource);
                        clip.open(ais);
                        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                        gainControl.setValue(Game.gain);
                        clip.start();
                        
                        if(isMusic == true){
                            clip.loop(Clip.LOOP_CONTINUOUSLY);
                        }
                    }catch(Exception ex){
                        System.out.println("Error loading sound");
                    }
                }
            }).start();
        }
    }