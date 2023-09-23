package fi.pomodoro;

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

/**
 * TODO
 */
public class PomodoroTimer {

    private final String START = "Start";
    private final String STOP = "Stop";
    private final String RESET = "Reset";
    private final double BTN_SPACING = 20;
    private final Insets BTN_PADDING = new Insets(BTN_SPACING);

    private final BackgroundFill TIMER_BACKGROUND = 
        new BackgroundFill(Color.WHITE, new CornerRadii(5), null);
    private final String SEPARATOR = ":";
    private final double COUNTER_SPACING = 20;
    private final Insets COUNTER_PADDING = new Insets(COUNTER_SPACING);

    // Times presented as "min:sec"
    private int workMin;
    private int workSec;
    private int shortBreakMin;
    private int shortBreakSec;
    private int longBreakMin;
    private int longBreakSec;

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
        buildTimer();
    }

    /**
     * Builds the graphical timer
     */
    private void buildTimer() {
        Button startBtn = new Button(START);
        Button stopBtn = new Button(STOP);
        Button resetBtn = new Button(RESET);
        HBox buttonLayout = new HBox(BTN_SPACING, resetBtn, stopBtn, startBtn);
        buttonLayout.setPadding(BTN_PADDING);

        Label minutes = new Label(Integer.toString(workMin));
        Label separator = new Label(SEPARATOR);
        Label seconds = new Label(Integer.toString(workSec));
        HBox counterLayout = new HBox(COUNTER_SPACING, minutes, separator, seconds);
        counterLayout.setAlignment(Pos.BASELINE_CENTER);
        counterLayout.setBackground(new Background(TIMER_BACKGROUND));
        counterLayout.setPadding(COUNTER_PADDING);

        timerLayout = new VBox(counterLayout, buttonLayout);
    }

    /**
     * Gets the timer layout with elements.
     * @return Pomodoro timer
     */
    VBox getTimer() {
        return this.timerLayout;
    }
}
