package com.patrickpissurno.crackpots;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SoundController {
    private static Clip[][] clips;

    public static final int SPIDER_HIT = 0;
    public static final int POT_CRACK = 1;
    public static final int LOOSE_LIFE = 2;
    public static final int SCORE_BONUS = 3;

    public static void Play(int clip){
        if(clips == null)
            return;

        if(clips[clip] != null) {
            Clip toPlay = null;

            for(Clip c : clips[clip]) {
                if (c.isRunning())
                    c.stop();
                else {
                    toPlay = c;
                    break;
                }
            }

            if(toPlay == null)
                toPlay = clips[clip][0];

            toPlay.setFramePosition(0);
            toPlay.start();
        }
    }

    public static void Load(){
        if(clips != null)
            return;

        clips = new Clip[][]{
                new Clip[]{ LoadClip("sound_spider_hit"), LoadClip("sound_spider_hit"), LoadClip("sound_spider_hit") },
                new Clip[]{ LoadClip("sound_pot_crack"), LoadClip("sound_pot_crack"), LoadClip("sound_pot_crack") },
                new Clip[]{ LoadClip("sound_loose_life"), LoadClip("sound_loose_life"), LoadClip("sound_loose_life") },
                new Clip[]{ LoadClip("sound_score_bonus"), LoadClip("sound_score_bonus"), LoadClip("sound_score_bonus") },
        };
    }

    private static Clip LoadClip(String name){
        try {
            final File soundFile = new File(name + ".wav");
            final AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            final Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            return clip;
        }
        catch(Exception ex)
        {
            return null;
        }
    }
}
