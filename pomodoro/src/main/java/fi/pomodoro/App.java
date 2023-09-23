package fi.pomodoro;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * TODO
 *
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("PomodoroApp");
        MainSceneBuilder msb = new MainSceneBuilder();
        stage.setScene(msb.getScene());
        stage.show();
    }
    public static void main( String[] args ) {
        launch(args);
    }
}
