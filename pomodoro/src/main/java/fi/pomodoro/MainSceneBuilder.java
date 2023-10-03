package fi.pomodoro;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Builds the main scene and adds functionalities.
 */
public class MainSceneBuilder {

    private final int workMin = 25;
    private final int workSec = 0;
    private final int shortBreakMin = 5;
    private final int shortBreakSec = 0;
    private final int longBreakMin = 25;
    private final int longBreakSec = 0;

    private final double WIDTH = 1000;
    private final double HEIGHT = 600;
    private final BackgroundFill MAIN_BACKGROUND =
        new BackgroundFill(Color.rgb(140, 184, 166), null, null);

    private Scene mainScene;

    /**
     * Main scene constructor
     */
    MainSceneBuilder() {
        buildScene();
    }

    /**
     * Builds scene with contents
     */
    private void buildScene() {
        PomodoroTimer timerObj = new PomodoroTimer(workMin, workSec, 
            shortBreakMin, shortBreakSec, longBreakMin, longBreakSec);
        VBox timer = timerObj.getTimer();
        GridPane mainLayout = new GridPane();
        mainLayout.add(timer, 1, 1);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setBackground(new Background(MAIN_BACKGROUND));
        mainScene = new Scene(mainLayout, WIDTH, HEIGHT);
    }

    /**
     * Gets the main scene.
     * @return main scene with contents
     */
    public Scene getScene() {
        return this.mainScene;
    }
}
