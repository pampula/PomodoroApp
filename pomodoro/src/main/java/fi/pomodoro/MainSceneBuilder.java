package fi.pomodoro;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Builds the main scene and adds functionalities.
 */
public class MainSceneBuilder {

    private final double WIDTH = 1000;
    private final double HEIGHT = 600;
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
        PomodoroTimer timerObj = new PomodoroTimer(25, 0, 5, 0, 25, 0);
        VBox timer = timerObj.getTimer();
        HBox mainLayout = new HBox(timer);
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
