package fi.pomodoro;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

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
        HBox mainLayout = new HBox(new Label("pomodoro timer"));
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
