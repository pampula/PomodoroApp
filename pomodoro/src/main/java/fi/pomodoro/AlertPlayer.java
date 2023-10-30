package fi.pomodoro;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AlertPlayer implements Runnable {

    private final String MAGIC_ALERT = "resources/alerts/magic_alert.wav";

    private String alertSound = MAGIC_ALERT;

    public AlertPlayer() {}

    public void play() {
        Thread t = new Thread(this);
        t.start();
    }

    public void run() {
        playAlert();
    }

    public void playAlert() {
        try {
            File soundFile = new File(alertSound);
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
