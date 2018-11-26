package controllers;

import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.IOException;

public class SoundPlayer {

    private static SoundPlayer instance;

    private SoundPlayer() {
    }

    public static SoundPlayer getInstance() {
        if (instance == null) {
            instance = new SoundPlayer();
        }
        return instance;
    }

    private final java.net.URL THEME_SONG = getClass().getResource("/sound/adventurers.MP3");
    private final java.net.URL BTN_CLICK = getClass().getResource("/sound/btnClick.mp3");
    private final java.net.URL CREATURE_CARD_SELECT = getClass().getResource("/sound/click.mp3");
    private final java.net.URL MAGIC_CARD_SELECT = getClass().getResource("/sound/magicSelect.wav");
    private final java.net.URL TABLE_PICK = getClass().getResource("/sound/tablePick.wav");
    private final java.net.URL TADA = getClass().getResource("/sound/TADA.mp3");
    private MediaPlayer themePlayer;
    private MediaPlayer btnCLick;
    private MediaPlayer creatureCardCLick;
    private MediaPlayer magicCardCLick;
    private MediaPlayer tableCardPick;
    private MediaPlayer tada;
    private boolean themeSongPlaying = false;

    public boolean isThemeSongPlaying() {
        return themeSongPlaying;
    }

    public void playThemeSong() {
        Media sound = new Media(THEME_SONG.toString());
        themePlayer = new MediaPlayer(sound);
        themePlayer.play();
        themeSongPlaying = true;
    }


    public void buttonClicked() {
        Media sound = new Media(BTN_CLICK.toString());
        btnCLick = new MediaPlayer(sound);
        btnCLick.play();
    }

    public void creatureCardClicked() {
        Media sound = new Media(CREATURE_CARD_SELECT.toString());
        creatureCardCLick = new MediaPlayer(sound);
        creatureCardCLick.play();
    }

    public void magicCardClicked() {
        Media sound = new Media(MAGIC_CARD_SELECT.toString());
        magicCardCLick = new MediaPlayer(sound);
        magicCardCLick.play();
    }

    public void tablePick() {
        Media sound = new Media(TABLE_PICK.toString());
        tableCardPick = new MediaPlayer(sound);
        tableCardPick.play();
    }

    public void playTada() {
        Media sound = new Media(TADA.toString());
        tada = new MediaPlayer(sound);
        tada.play();
    }


    public String themeSongToggle() {
        String value;
        if (this.themeSongPlaying) {
            themePlayer.stop();
            this.themeSongPlaying = !themeSongPlaying;
            value = "soundOff";
        } else {
            themePlayer.play();
            value = "soundOn";
            this.themeSongPlaying = !themeSongPlaying;
        }
        return value;
    }
}
