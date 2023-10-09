package fi.pomodoro;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

/**
 * Builds and handles the pomodoro timer.
 */
public class PomodoroTimer {

    private AlertPlayer alertPlayer = new AlertPlayer();

    private final String START = "Start";
    private final String STOP = "Stop";
    private final String RESET = "Reset";
    private final double BTN_SPACING = 20;
    private final Insets BTN_PADDING = new Insets(BTN_SPACING);

    private final BackgroundFill TIMER_BACKGROUND = 
        new BackgroundFill(Color.WHITE, new CornerRadii(10), null);
    private final Font TIMER_FONT_SIZE = new Font(20);
    private final Insets COUNTER_PADDING = new Insets(20);

    private final int workMin;
    private final int workSec;
    private final int shortBreakMin;
    private final int shortBreakSec;
    private final int longBreakMin;
    private final int longBreakSec;

    private int minutes;
    private int seconds;
    private boolean isRunning = false;
    private Timeline timeline;
    private Label timerLabel;

    private VBox timerLayout;
    
    /**
     * Timer constructor. Inputs custom lengths for work and break periods.
     * @param workMin Minutes of working
     * @param workSec Seconds of working
     * @param shortBreakMin Minutes of a short break
     * @param shortBreakSec Seconds of a short break
     * @param longBreakMin Minutes of a long break
     * @param longBreakSec Seconds of a long break
     */
    PomodoroTimer(int workMin, int workSec, int shortBreakMin, int shortBreakSec, 
        int longBreakMin, int longBreakSec) {
        this.workMin = workMin;
        this.workSec = workSec;
        this.shortBreakMin = shortBreakMin;
        this.shortBreakSec = shortBreakSec;
        this.longBreakMin = longBreakMin;
        this.longBreakSec = longBreakSec;
        this.minutes = workMin;
        this.seconds = workSec;
        buildTimer();
    }

    /**
     * Builds the timer UI
     */
    private void buildTimer() {
        Button startBtn = new Button(START);
        Button stopBtn = new Button(STOP);
        Button resetBtn = new Button(RESET);
        HBox buttonLayout = new HBox(BTN_SPACING, resetBtn, stopBtn, startBtn);
        buttonLayout.setPadding(BTN_PADDING);

        startBtn.setOnAction(this::startTimer);
        stopBtn.setOnAction(event -> stopTimer());
        resetBtn.setOnAction(this::resetTimer);

        timerLabel = new Label(String.format("%02d:%02d", minutes, seconds));
        timerLabel.setFont(TIMER_FONT_SIZE);
        HBox counterLayout = new HBox(timerLabel);
        counterLayout.setAlignment(Pos.BASELINE_CENTER);
        counterLayout.setBackground(new Background(TIMER_BACKGROUND));
        counterLayout.setPadding(COUNTER_PADDING);

        timerLayout = new VBox(counterLayout, buttonLayout);
    }

    /**
     * Starts the timer countdown.
     * @param event
     */
    private void startTimer(ActionEvent event) {
        if (!isRunning) {
            isRunning = true;
            timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        updateTimer();
                        if (minutes == 0 && seconds == 0) {
                            stopTimer();
                        }
                    }
                })
            );
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        }
    }

    /**
     * Stops the timer countdown.
     */
    private void stopTimer() {
        if (isRunning) {
            isRunning = false;
            if (timeline != null) {
                timeline.stop();
            }
            if(minutes == 0 && seconds == 0) {
                alertPlayer.run();
            }
        }
    }

    /**
     * Stops and resets the timer to default values.
     * @param event
     */
    private void resetTimer(ActionEvent event) {
        stopTimer();
        minutes = workMin;
        seconds = workSec;
        timerLabel.setText(String.format("%02d:%02d", minutes, seconds));
    }

    /**
     * Updates the timer UI when a second is passed. When the time runs out, stops
     * the timer and plays alert sound.
     */
    private void updateTimer() {
        if (seconds == 0) {
            if (minutes == 0) {
                stopTimer();
                return;
            }
            minutes--;
            seconds = 59;
        } else {
            seconds--;
        }

        timerLabel.setText(String.format("%02d:%02d", minutes, seconds));
    }

    /**
     * Gets the timer layout with elements.
     * @return Pomodoro timer
     */
    VBox getTimer() {
        return this.timerLayout;
    }
}
